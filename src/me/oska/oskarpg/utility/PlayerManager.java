package me.oska.oskarpg.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.tr7zw.itemnbtapi.NBTItem;
import me.oska.oskarpg.items.ItemUtils;
import me.oska.oskarpg.items.skills.Skill;
import me.oska.oskarpg.saves.FlatFile;

public class PlayerManager 
{
	public static HashMap<Player,Double> Strength;
	public static HashMap<Player,Double> Intelligence;
	public static HashMap<Player,Double> Dexirity;
	public static HashMap<Player,Double> Health;
	public static HashMap<Player,Double> Damage;
	public static HashMap<Player,Double> RangeDamage;
	public static HashMap<Player,Double> Armor;
	public static HashMap<Player, List<Skill>> Skills;
	public static void Load()
	{
		Strength = new HashMap<Player, Double>();
		Intelligence = new HashMap<Player, Double>();
		Dexirity = new HashMap<Player, Double>();
		Health = new HashMap<Player, Double>();
		Damage = new HashMap<Player, Double>();
		RangeDamage = new HashMap<Player, Double>();
		Armor = new HashMap<Player, Double>();
		Skills = new HashMap<Player, List<Skill>>();
	}
	public static void calculateStats(Player p, ItemStack[] items)
	{
		double str = 0;
		double inl = 0;
		double dex = 0;
		double dmg = 0;
		double rdmg = 0;
		double armor = 0;
		double health = 0;
		List<Skill> skills = new ArrayList<Skill>();
		for (ItemStack item : items)
		{
			if (item == null|| item.getType() == Material.AIR)
			{
				continue;
			}
			NBTItem nbti = new NBTItem(item);
			String id = nbti.getString("oskarpg_id");
			if (id.equalsIgnoreCase(""))
			{
				continue;
			}
			str += ItemUtils.getStat(id, "strength");
			inl +=  ItemUtils.getStat(id, "intelligence");
			dex += ItemUtils.getStat(id, "dexirity");
			dmg += ItemUtils.getAttribute(id, "damage");
			rdmg += ItemUtils.getAttribute(id, "range-damage");
			armor += ItemUtils.getAttribute(id, "armor");
			health += ItemUtils.getAttribute(id, "health");
			skills.addAll(ItemUtils.getSkills(id));
		}
		PlayerManager.Strength.put(p, str);
		PlayerManager.Intelligence.put(p, inl);
		PlayerManager.Dexirity.put(p, dex);
		PlayerManager.Damage.put(p, dmg);
		PlayerManager.RangeDamage.put(p, rdmg);
		PlayerManager.Armor.put(p, armor);
		PlayerManager.Health.put(p, FlatFile.getFileConfig("config").getDouble("config.base-health") + health);
		PlayerManager.Skills.put(p, skills);
		p.setHealth(PlayerManager.Health.get(p));
		p.setHealthScale(20);
	}
	public static double statsConversion(Player p, String returntype)
	{
		double stats = 0;
		double current = 0;
		for (String stat : FlatFile.getFileConfig("config").getConfigurationSection("config.items.stats").getKeys(false))
		{
			for (String st : FlatFile.getFileConfig("config").getConfigurationSection("config.items.stats." + stat).getKeys(false))
			{
				switch (stat)
				{
					case "strength":
						current = Strength.get(p);
						break;
					case "intelligence":
						current = Intelligence.get(p);
						break;
					case "dexirity":
						current = Dexirity.get(p);
						break;
				}
				if (st.equals(returntype))
				{
					stats += current * FlatFile.getFileConfig("config").getDouble("config.items.stats." + stat + "." + st);
				}				
			}
		}
		return ValueUtils.round(stats, 1);
	}
}