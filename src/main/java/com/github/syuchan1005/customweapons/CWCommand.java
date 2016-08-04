package com.github.syuchan1005.customweapons;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by syuchan on 2016/08/02.
 */
public class CWCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = sender instanceof Player ? ((Player) sender) : null;
		if(args[0].toUpperCase().equals("ALL")) {
			for (WorkBenchType workBenchType : WorkBenchType.values()) {
				player.getInventory().addItem(WorkBenchItemStacks.getWorkBench(workBenchType, Integer.parseInt(args[1])));
			}
		} else {
			player.getInventory().addItem(WorkBenchItemStacks.getWorkBench(WorkBenchType.valueOf(args[0].toUpperCase()), Integer.parseInt(args[1])));
		}
		return true;
	}
}
