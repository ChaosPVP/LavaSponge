package org.chaospvp.lavasponge;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public final class SpongeUtil {

    private SpongeUtil() {
    }

    public static void clearSpongeLava(World world, int ox, int oy, int oz) {
        for (int cx = -LavaSponge.spongeRadius; cx <= LavaSponge.spongeRadius; cx++) {
            for (int cy = -LavaSponge.spongeRadius; cy <= LavaSponge.spongeRadius; cy++) {
                for (int cz = -LavaSponge.spongeRadius; cz <= LavaSponge.spongeRadius; cz++) {
                    if (isBlockLava(world, ox + cx, oy + cy, oz + cz)) {
                        world.getBlockAt(ox + cx, oy + cy, oz + cz).setType(Material.AIR);
                    }
                }
            }
        }
    }

    public static boolean isBlockLava(World world, int ox, int oy, int oz) {
        Block block = world.getBlockAt(ox, oy, oz);
        return block.getType() == Material.LAVA || block.getType() == Material.STATIONARY_LAVA;
    }
}