package org.chaospvp.lavasponge;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class LavaSponge extends JavaPlugin implements Listener {
    public static int spongeRadius = 2;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        Block target = event.getBlock();
        World world = target.getWorld();

        if (target.getType() == Material.SPONGE) {
            int ox = target.getX();
            int oy = target.getY();
            int oz = target.getZ();

            SpongeUtil.clearSpongeLava(world, ox, oy, oz);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockFromTo(BlockFromToEvent event) {
        World world = event.getBlock().getWorld();
        Block blockFrom = event.getBlock();
        Block blockTo = event.getToBlock();

        boolean isLava = blockFrom.getType() == Material.LAVA || blockFrom.getType() == Material.STATIONARY_LAVA;

        if (isLava) {
            int ox = blockTo.getX();
            int oy = blockTo.getY();
            int oz = blockTo.getZ();

            for (int cx = -spongeRadius; cx <= spongeRadius; cx++) {
                for (int cy = -spongeRadius; cy <= spongeRadius; cy++) {
                    for (int cz = -spongeRadius; cz <= spongeRadius; cz++) {
                        Block sponge = world.getBlockAt(ox + cx, oy + cy, oz + cz);
                        if (sponge.getType() == Material.SPONGE) {
                            event.setCancelled(true);
                            return;
                        }
                    }
                }
            }
        }
    }
}
