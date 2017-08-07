package me.oska.oskarpg.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.tr7zw.itemnbtapi.NBTItem;
import me.oska.oskarpg.MainClass;
import me.oska.oskarpg.api.items.ORPGEntityDamageEntity;
import me.oska.oskarpg.items.skills.Skill;
import me.oska.oskarpg.saves.FlatFile;
import me.oska.oskarpg.utility.ValueUtils;

public class ItemUtils 
{
	static MainClass mc = MainClass.getInstance();
	public static ItemStack getItem(String id)
	{
	    @SuppressWarnings("deprecation")
		ItemStack item = new ItemStack(FlatFile.getFileConfig("items/" + id).getInt(id + ".id"));
	    ItemMeta im = item.getItemMeta();
	    // Durability
	    item.setDurability((short)FlatFile.getFileConfig("items/" + id).getInt(id + ".durability"));
	    // Display Name
	    im.setDisplayName(FlatFile.getFileConfig("items/" + id).getString(id + ".name").replaceAll("&", "§"));
	    // Lores
	    List<String> lores = new ArrayList<String>();
	    for (String l : FlatFile.getFileConfig("items/" + id).getStringList(id + ".lore"))
	    {
	    	lores.add(l.replaceAll("&", "§"));
	    }
	    im.setLore(lores);
	    item.setItemMeta(im);
	    
	    NBTItem nbti = new NBTItem(item);
	    // ID
	    nbti.setString("oskarpg_id", id);
	    // unbreakable
	    if (FlatFile.getFileConfig("items/" + id).getBoolean(id + ".nbt.unbreakable"))
	    {
	    	nbti.setInteger("Unbreakable", 1);
	    }
	    else
	    {
	    	nbti.setInteger("Unbreakable", 0);
	    }
	    // hideflags
	    nbti.setInteger("HideFlags", FlatFile.getFileConfig("items/" + id).getInt(id + ".nbt.hideflags"));
	    item = nbti.getItem();
	    return item;
	}
	public static List<Skill> getSkills(String id)
	{
		List<Skill> skills = new ArrayList<Skill>();
		for (String s :FlatFile.getFileConfig("items/" + id).getConfigurationSection(id + ".skills").getKeys(false))
		{
			if (s.equalsIgnoreCase("ComboStrike"))
			{
				int slevel = FlatFile.getFileConfig("items/" + id).getInt(id + ".skills." + s + ".level");
				int chance = FlatFile.getFileConfig("items/" + id).getInt(id + ".skills." + s + ".chance");
				skills.add(new Skill(s, slevel, chance)
					{
						@Override
						public void trigger(ORPGEntityDamageEntity e, Skill s) 
						{
							if (!e.getRange())
							{
								e.setDamage((e.getDamage() * 2 ) + (s.getLevel() * 2));
							}
						}
				
					}
				);
			}
		}
		return skills;
	}
	public static String getPermission(String id)
	{
		return FlatFile.getFileConfig("items/" + id).getString(id + ".permission");
	}
	public static double getAttribute(String id, String value)
	{
		double stat = 0;
		stat = FlatFile.getFileConfig("items/" + id).getDouble(id + ".attributes." + value);
		return ValueUtils.round(stat, 1);
	}
	public static double getStat(String id, String value)
	{
		double stat = 0;
		stat = FlatFile.getFileConfig("items/" + id).getDouble(id + ".stats." + value);
		return ValueUtils.round(stat, 1);
	}
}