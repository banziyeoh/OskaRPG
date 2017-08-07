package me.oska.oskarpg;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.oska.oskarpg.items.ItemCommand;
import me.oska.oskarpg.items.events.DamageEntity;
import me.oska.oskarpg.items.events.ItemEquip;
import me.oska.oskarpg.items.skills.SkillEvent;
import me.oska.oskarpg.saves.FlatFile;
import me.oska.oskarpg.utility.PlayerManager;

public class MainClass extends JavaPlugin implements Listener
{
	// Instance
	private static MainClass mc;
	public MainClass() 
	{
		mc = this;
	}
	public static MainClass getInstance()
	{
		return mc;
	}
	// Listener
	public void registerListener()
	{
		if (FlatFile.getFileConfig("config").getBoolean("config.items.enabled"))
		{
			Bukkit.getServer().getPluginCommand("oskarpg").setExecutor(new ItemCommand());
			Bukkit.getServer().getPluginManager().registerEvents(new DamageEntity(), this);
			Bukkit.getServer().getPluginManager().registerEvents(new ItemEquip(), this);
			Bukkit.getServer().getPluginManager().registerEvents(new SkillEvent(), this);
			PlayerManager.Load();
			getLogger().info("Items Enabled!");
		}
	}
	// Enable / Disable
	@Override
	public void onEnable()
	{
		FlatFile.checkFileExists("config");
		FlatFile.checkFileExists("language");
		FlatFile.checkFileExists("message");
		FlatFile.checkFileExists("items/example-item");
		
		registerListener();
	}
	
	@Override
	public void onDisable()
	{
		FlatFile.reloadFile("config");
		FlatFile.reloadFile("language");
		FlatFile.reloadFile("message");
	}
}
