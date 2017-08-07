package me.oska.oskarpg.api.items;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ORPGEntityDamageEntity extends Event
{
	private static final HandlerList HANDLERS = new HandlerList();
	public HandlerList getHandlers()
	{
		return HANDLERS;
	}
	public static HandlerList getHandlerList()
	{
		return HANDLERS;
	}
	private Entity attacker;
	private Entity victim;
	private double damage;
	private double armor;
	private boolean range;
	
	private boolean isCancelled;
	public boolean isCancelled()
	{
		return this.isCancelled;
	}
	public void setCancelled(boolean isCancelled)
	{
		this.isCancelled = isCancelled;
	}
	
	private boolean isSetting;
	public boolean isSetting()
	{
		return this.isSetting;
	}
	public void setSetting(boolean isSetting)
	{
		this.isSetting = isSetting;
	}
	
	public ORPGEntityDamageEntity(Entity attacker, Entity victim, double damage, double armor,  boolean range)
	{
		this.attacker = attacker;
		this.victim = victim;
		this.damage = damage;
		this.armor = armor;
		this.range = range;
		this.isCancelled = false;
		this.isSetting = false;
	}
	
	public Entity getAttacker()
	{
		return this.attacker;
	}
	public Entity getVictim()
	{
		return this.victim;
	}
	public double getDamage()
	{
		return this.damage;
	}
	public double getArmor()
	{
		return this.armor;
	}
	public boolean getRange()
	{
		return this.range;
	}
	public void setDamage(double damage)
	{
		this.damage = damage;
		this.isSetting = true;
	}
	public void setArmor(double armor)
	{
		this.armor = armor;
		this.isSetting = true;
	}
}
