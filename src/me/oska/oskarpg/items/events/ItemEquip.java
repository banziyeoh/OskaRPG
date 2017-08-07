package me.oska.oskarpg.items.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import me.oska.oskarpg.utility.PlayerManager;

public class ItemEquip implements Listener
{
	@EventHandler
	public void CI(InventoryCloseEvent e)
	{
		Player p = (Player)e.getPlayer();
		PlayerManager.calculateStats(p, p.getInventory().getArmorContents());
	}
	@EventHandler
	public void PDE(PlayerDeathEvent e)
	{
		Player p = (Player)e.getEntity();
		PlayerManager.calculateStats(p, p.getInventory().getArmorContents());
	}
	@EventHandler
	public void PJ(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		PlayerManager.calculateStats(p, p.getInventory().getArmorContents());
	}
}
