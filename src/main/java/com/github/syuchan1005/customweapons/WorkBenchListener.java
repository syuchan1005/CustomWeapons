package com.github.syuchan1005.customweapons;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

/**
 * Created by syuchan on 2016/08/02.
 */
public class WorkBenchListener implements Listener {

	@EventHandler
	public void onPlace(final BlockPlaceEvent event) throws ReflectiveOperationException {
		ItemStack itemInHand = event.getItemInHand();
		if (!WorkBenchManager.isWorkBench(itemInHand)) return;
		final Furnace furnace = (Furnace) event.getBlockPlaced().getState();
		final List<String> lore = itemInHand.getItemMeta().getLore();
		new BukkitRunnable() {
			@Override
			public void run() {
				try {
					FurnaceUtil.setInventoryName(event.getBlockPlaced().getLocation(), furnace, "Custom Weapons" + ":" +
							lore.get(1).substring(6) + ":" + lore.get(2).substring(6));
				} catch (ReflectiveOperationException e) {
					e.printStackTrace();
				}
			}
		}.runTaskLater(CustomWeapons.getInstance(), 2L);
	}

	@EventHandler
	public void onBreak(BlockBreakEvent event) throws ReflectiveOperationException {
		Block block = event.getBlock();
		if (block.getType() != Material.FURNACE) return;
		Furnace furnace = (Furnace) block.getState();
		String inventoryName = FurnaceUtil.getInventoryName(furnace);
		String[] split = inventoryName.split(":");
		if (split.length != 3 || !split[0].equals("Custom Weapons")) return;
		block.setType(Material.AIR);
		Location location = block.getLocation();
		if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
			location.getWorld().dropItem(location, WorkBenchManager.getWorkBenchStack(WorkBenchManager.getWorkBench(split[1]), Integer.parseInt(split[2])));
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) throws ReflectiveOperationException {
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		if (event.getPlayer().isSneaking()) return;
		Block clickedBlock = event.getClickedBlock();
		if (clickedBlock.getType() != Material.FURNACE) return;
		WorkBenchHolder workBenchHolder = getWorkBenchHolder(FurnaceUtil.getInventoryName(((Furnace) clickedBlock.getState())));
		if (workBenchHolder != null) {
			event.setCancelled(true);
			event.getPlayer().setMetadata("WBLocation", new FixedMetadataValue(CustomWeapons.getInstance(), clickedBlock.getLocation()));
			event.getPlayer().openInventory(workBenchHolder.getInventory());
		}
	}

	private static WorkBenchHolder getWorkBenchHolder(String title) {
		String[] split = title.split(":");
		if (split.length != 3 || !split[0].equals("Custom Weapons")) return null;
		return new WorkBenchHolder(WorkBenchManager.getWorkBench(split[1]), Integer.parseInt(split[2]));
	}
}
