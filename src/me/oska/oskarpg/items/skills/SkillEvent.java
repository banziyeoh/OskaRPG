package me.oska.oskarpg.items.skills;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.oska.oskarpg.api.items.ORPGEntityDamageEntity;
import me.oska.oskarpg.utility.PlayerManager;
import me.oska.oskarpg.utility.ValueUtils;

public class SkillEvent implements Listener 
{
	@EventHandler
	public void onDamage(ORPGEntityDamageEntity e)
	{
		if (e.getAttacker() instanceof Player)
		{
			Player attacker = (Player)e.getAttacker();
			for (Skill s :PlayerManager.Skills.get(attacker))
			{
				if (ValueUtils.chance(s.getChance()))
				{
					s.trigger(e, s);
				}
			}
		}
	}
}
