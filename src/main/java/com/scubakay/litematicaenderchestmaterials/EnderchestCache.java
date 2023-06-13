package com.scubakay.litematicaenderchestmaterials;

import net.minecraft.inventory.Inventory;

public class EnderchestCache {
    private static Inventory enderChestInventory;

    public static void CacheEnderChestInventory(Inventory inventory) {
        enderChestInventory = inventory;
    }

    public static Inventory GetEnderChestInventoryCache() {
        return enderChestInventory;
    }
}
