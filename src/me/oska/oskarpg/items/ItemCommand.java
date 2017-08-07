package me.oska.oskarpg.items;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.oska.oskarpg.MainClass;

public class ItemCommand implements CommandExecutor
{
	static MainClass mc = MainClass.getInstance();
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) 
	{
		if (arg0 instanceof Player)
		{
			Player p = (Player)arg0;
			if (!p.hasPermission("oskarpg.admin"))
			{
				p.sendMessage("§cNO PERMISSION TO USE THIS COMMANDS");
				return true;
			}
			if (arg2.equalsIgnoreCase("oskarpg"))
			{
				if (arg3[0].equalsIgnoreCase("item"))
				if (arg3.length == 3)
				{
					if (arg3[1].equalsIgnoreCase("get"))
					{
						String itemid = arg3[2];
						if (new File(mc.getDataFolder() + "/items/" + arg3[2] + ".yml").exists())
						{
							p.getInventory().addItem(ItemUtils.getItem(itemid));
						}
						else
						{
							p.sendMessage("§bItem Not Exists!");
						}
						return true;
					}
				}
			}
		}
		return false;
	}

}
