package me.oska.oskarpg.saves;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.oska.oskarpg.MainClass;

public class FlatFile 
{
	static MainClass mc = MainClass.getInstance();
	static private File cf;
	static private FileConfiguration config;
	public static FileConfiguration getFileConfig(String filename)
	{
		cf = new File(mc.getDataFolder(), filename + ".yml");
		config = YamlConfiguration.loadConfiguration(cf);
		return config;
	}
	public static void reloadFile(String filename)
	{
		cf = new File(mc.getDataFolder(), filename + ".yml");
		config = YamlConfiguration.loadConfiguration(cf);
	}
	public static void checkFileExists(String filename)
	{
		cf = new File(mc.getDataFolder(), filename + ".yml");
		if (!cf.exists())
		{
			cf.getParentFile().mkdirs();
			mc.saveResource(filename + ".yml", false);
		}
		config = new YamlConfiguration();
		try {
			config.load(cf);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
}
