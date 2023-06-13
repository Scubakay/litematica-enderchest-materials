package com.scubakay.litematicaenderchestmaterials;

import fi.dy.masa.litematica.materials.MaterialListUtils;
import fi.dy.masa.malilib.util.InventoryUtils;
import fi.dy.masa.malilib.util.ItemType;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.InventoryS2CPacket;

import java.util.List;

public class EnderchestCache {
    private static Object2IntOpenHashMap<ItemType> enderChestItems;

    private static boolean enderChestOpened = false;

    public static void handleInventoryS2CPacket(InventoryS2CPacket packet) {
        if (enderChestOpened) {
            List<ItemStack> enderChestItemStacks = packet.getContents().subList(0, 26);
            enderChestItems = mapItemStacks(enderChestItemStacks);
            enderChestOpened = false;
        }
    }

    public static Object2IntOpenHashMap<ItemType> GetEnderChestItems() {
        return enderChestItems;
    }

    public static void openEnderChest() {
        enderChestOpened = true;
    }

    private static Object2IntOpenHashMap<ItemType> mapItemStacks(List<ItemStack> stacks) {
        Object2IntOpenHashMap<ItemType> map = new Object2IntOpenHashMap<>();
        stacks.forEach((stack) -> {
            map.addTo(new ItemType(stack, true, false), stack.getCount());
            if (stack.getItem() instanceof BlockItem && ((BlockItem)stack.getItem()).getBlock() instanceof ShulkerBoxBlock && InventoryUtils.shulkerBoxHasItems(stack)) {
                Object2IntOpenHashMap<ItemType> boxCounts = MaterialListUtils.getStoredItemCounts(stack);

                for (ItemType type : boxCounts.keySet()) {
                    map.addTo(type, boxCounts.getInt(type));
                }
            }
        });
        return map;
    }
}
