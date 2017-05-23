package io.notoh.dennls.util;

import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static net.minecraft.block.Block.getBlockById;
import static net.minecraft.block.Block.getBlockFromName;

/**
 * Created by alexa on 5/22/2017.
 */
public final class XrayUtils {

    public static final List<Block> XRAY_BLOCKS;

    static {
        Block[] xrayBlocks = {
                getBlockFromName("coal_ore"),
                getBlockFromName("iron_ore"),
                getBlockFromName("gold_ore"),
                getBlockFromName("redstone_ore"),
                getBlockById(74), //lit redstone
                getBlockFromName("lapis_ore"),
                getBlockFromName("diamond_ore"),
                getBlockFromName("emerald_ore"),
                getBlockFromName("quartz_ore"),
                getBlockFromName("glowstone"),
                getBlockById(8), //flowing water
                getBlockById(9), //normal water
                getBlockById(10), //flowing lava
                getBlockById(11), //normal lava
                getBlockFromName("crafting_table"),
                getBlockById(61), //furnace
                getBlockById(62), //lit furnace
                getBlockFromName("torch"),
                getBlockFromName("ladder"),
                getBlockFromName("tnt"),
                getBlockFromName("coal_block"),
                getBlockFromName("iron_block"),
                getBlockFromName("gold_block"),
                getBlockFromName("diamond_block"),
                getBlockFromName("emerald_block"),
                getBlockFromName("redstone_block"),
                getBlockFromName("lapis_block"),
                getBlockFromName("fire"),
                getBlockFromName("mossy_cobblestone"),
                getBlockFromName("mob_spawner"),
                getBlockFromName("end_portal_frame"),
                getBlockFromName("enchanting_table"),
                getBlockFromName("bookshelf"),
                getBlockFromName("command_block")
        };
        List<Block> tmp = new ArrayList<>();
        tmp.addAll(Arrays.asList(xrayBlocks));
        tmp.forEach(block -> {
            if(block == null) {
                tmp.remove(block);
            }
        });
        XRAY_BLOCKS = Collections.unmodifiableList(tmp);
    }

    public static boolean isXrayBlock(Block block) {
        if(XRAY_BLOCKS.contains(block)) {
            return true;
        }
        return false;
    }

}
