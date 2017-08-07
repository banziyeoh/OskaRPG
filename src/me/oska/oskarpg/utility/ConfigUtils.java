package me.oska.oskarpg.utility;

import me.oska.oskarpg.saves.FlatFile;

public class ConfigUtils 
{
	public static String getString(String node)
	{
		return FlatFile.getFileConfig("language").getString(node);
	}
	public static String getMessage(String node)
	{
		return FlatFile.getFileConfig("message").getString(node).replaceAll("&", "§");
	}
	public static Object getConfig(String node)
	{
		return FlatFile.getFileConfig("config").get(node);
	}
}
