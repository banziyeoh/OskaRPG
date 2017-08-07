package me.oska.oskarpg.items.skills;

import me.oska.oskarpg.api.items.ORPGEntityDamageEntity;

public abstract class Skill
{
	private int level;
	private int chance;
	private String skillname;
	public Skill(String name, int level, int chance)
	{
		this.skillname = name;
		this.level = level;
		this.chance = chance;
	}
	
	public abstract void trigger(ORPGEntityDamageEntity e, Skill s);
	
	public String getSkill()
	{
		return this.skillname;
	}
	public int getLevel()
	{
		return this.level;
	}
	public int getChance() 
	{
		return this.chance;
	}
}
