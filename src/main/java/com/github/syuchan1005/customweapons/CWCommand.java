package com.github.syuchan1005.customweapons;

import com.github.syuchan1005.customweapons.workbench.IWorkBench;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

/**
 * Created by syuchan on 2016/08/02.
 */
public class CWCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player;
		if (sender instanceof Player) player = ((Player) sender);
		else return false;
		PlayerInventory inventory = player.getInventory();
		if(args[0].toUpperCase().equals("ALL")) {
			for (IWorkBench iWorkBench : WorkBenchManager.getWorkBenchList()) {
				inventory.addItem(WorkBenchManager.getWorkBenchStack(iWorkBench, Integer.parseInt(args[1])));
			}
		} else {
			inventory.addItem(WorkBenchManager.getWorkBenchStack(WorkBenchManager.getWorkBench(args[0]), Integer.parseInt(args[1])));
		}
		return true;
	}
}
