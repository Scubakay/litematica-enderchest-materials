package com.scubakay.litematicaenderchestmaterials;

import fi.dy.masa.litematica.materials.MaterialListUtils;
import fi.dy.masa.malilib.util.InventoryUtils;
import fi.dy.masa.malilib.util.ItemType;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.InventoryS2CPacket;
import net.minecraft.screen.ScreenHandler;

import java.util.List;

public class EnderChestCache {
    private static Object2IntOpenHashMap<ItemType> enderChestItems;

    private static boolean enderChestOpen = false;
    private static boolean enderChestOpened = false;

    /**
     * Update the material list the first time the ender chest is opened
     */
    public static void handleInventoryS2CPacket(InventoryS2CPacket packet) {
        if (enderChestOpen && !enderChestOpened) {
            List<ItemStack> enderChestItemStacks = packet.contents().subList(0, 27);
            enderChestItems = mapItemStacks(enderChestItemStacks);
            enderChestOpened = true;
        }
    }

    /**
     * Update the material list every time a stack is moved within the ender chest
     */
    public static void updateSlots(PlayerEntity player) {
        if (enderChestOpen) {
            ScreenHandler screenHandler = player.currentScreenHandler;
            List<ItemStack> enderChestItemStacks = screenHandler.getStacks().subList(0, 27);
            enderChestItems = mapItemStacks(enderChestItemStacks);
        }
    }

    public static Object2IntOpenHashMap<ItemType> getEnderChestItems() {
        return enderChestItems;
    }

    public static void openEnderChest() {
        enderChestOpen = true;
    }

    public static void closeEnderChest() {
        if (enderChestOpen) {
            enderChestOpen = false;
        }
    }

    public static void reset() {
        enderChestOpen = false;
        enderChestOpened = false;
        enderChestItems = null;
    }

    /**
     * Map the ender chest stacks to something useful for Litematica
     */
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
