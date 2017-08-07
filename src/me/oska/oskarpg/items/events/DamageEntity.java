package me.oska.oskarpg.items.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.oska.oskarpg.MainClass;
import me.oska.oskarpg.api.items.ORPGEntityDamageEntity;
import me.oska.oskarpg.utility.PlayerManager;

public class DamageEntity implements Listener
{
	static MainClass mc = MainClass.getInstance();
	@EventHandler
	public void EDE(EntityDamageByEntityEvent e)
	{
		double damage = 0;
		double armor = 0;
		if (e.getDamager() instanceof Player)
		{
			Player attacker = (Player)e.getDamager();
			damage += PlayerManager.Damage.get(attacker);
			damage += PlayerManager.statsConversion(attacker, "damage");
			if (e.getEntity() instanceof Player)
			{
				// Damage ( Player -> Player )
				Player victim = (Player)e.getEntity();
				armor += PlayerManager.Armor.get(victim);
				armor += PlayerManager.statsConversion(victim, "armor");
				ORPGEntityDamageEntity event = new ORPGEntityDamageEntity(attacker, victim, damage, armor, false);
				Bukkit.getPluginManager().callEvent(event);
				if (event.isCancelled())
				{
					return;
				}
				if (event.isSetting())
				{
					damage = event.getDamage();
					armor = event.getArmor();
				}
			}
			else
			{
				// Damage ( Player -> Entity )
				Entity victim = e.getEntity();
				ORPGEntityDamageEntity event = new ORPGEntityDamageEntity(attacker, victim, damage, armor, false);
				Bukkit.getPluginManager().callEvent(event);
				if (event.isCancelled())
				{
					return;
				}
				if (event.isSetting())
				{
					damage = event.getDamage();
					armor = event.getArmor();
				}
			}
		}
		if (e.getDamager() instanceof Projectile)
		{
			if (((Projectile)e.getDamager()).getShooter() instanceof Entity)
			{
				// Range Damage ( Entity -> Player 
				Entity attacker = (Entity)((Projectile)e.getDamager()).getShooter();
				damage += e.getDamage();
				if (e.getEntity() instanceof Player)
				{
					Player victim = (Player)e.getEntity();
					armor += PlayerManager.Armor.get(victim);
					armor += PlayerManager.statsConversion(victim, "armor");
					ORPGEntityDamageEntity event = new ORPGEntityDamageEntity(attacker, victim, damage, armor, true);
					Bukkit.getPluginManager().callEvent(event);
					if (event.isCancelled())
					{
						return;
					}
					if (event.isSetting())
					{
						damage = event.getDamage();
						armor = event.getArmor();
					}
				}
			}
			if (((Projectile)e.getDamager()).getShooter() instanceof Player)
			{
				Player attacker = (Player)((Projectile)e.getDamager()).getShooter();
				damage += PlayerManager.RangeDamage.get(attacker);
				damage += PlayerManager.statsConversion(attacker, "range-damage");
				if (e.getEntity() instanceof Player)
				{
					// Range Damage ( Player -> Player )
					Player victim = (Player)e.getEntity();
					armor += PlayerManager.Armor.get(victim);
					armor += PlayerManager.statsConversion(victim, "armor");
					ORPGEntityDamageEntity event = new ORPGEntityDamageEntity(attacker, victim, damage, armor, true);
					Bukkit.getPluginManager().callEvent(event);
					if (event.isCancelled())
					{
						return;
					}
					if (event.isSetting())
					{
						damage = event.getDamage();
						armor = event.getArmor();
					}
				}
				else
				{
					// Range Damage ( Player -> Entity )
					Entity victim = e.getEntity();
					ORPGEntityDamageEntity event = new ORPGEntityDamageEntity(attacker, victim, damage, armor, true);
					Bukkit.getPluginManager().callEvent(event);
					if (event.isCancelled())
					{
						return;
					}
					if (event.isSetting())
					{
						damage = event.getDamage();
						armor = event.getArmor();
					}
				}
			}
		}
		if (e.getDamager() instanceof Entity)
		{
			Entity attacker = e.getDamager();
			damage += e.getDamage();
			if (e.getEntity() instanceof Player)
			{
				// Entity -> Player
				Player victim = (Player)e.getEntity();
				armor += PlayerManager.Armor.get(victim);
				armor += PlayerManager.statsConversion(victim, "armor");
				ORPGEntityDamageEntity event = new ORPGEntityDamageEntity(attacker, victim, damage, armor, false);
				Bukkit.getPluginManager().callEvent(event);
				if (event.isCancelled())
				{
					return;
				}
				if (event.isSetting())
				{
					damage = event.getDamage();
					armor = event.getArmor();
				}
			}
		}
		e.setDamage(damage - armor);
	}
}
