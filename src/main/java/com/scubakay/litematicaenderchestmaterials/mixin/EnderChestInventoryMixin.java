package com.scubakay.litematicaenderchestmaterials.mixin;

import com.scubakay.litematicaenderchestmaterials.EnderchestCache;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net/minecraft/inventory/EnderChestInventory")
public class EnderChestInventoryMixin {
    @Inject(method = "onOpen", at = @At(value = "RETURN"))
    public void litematicaEnderchestMaterials_onOpen(PlayerEntity player, CallbackInfo ci) {
        EnderchestCache.CacheEnderChestInventory((EnderChestInventory)(Object)this);
    }

    @Inject(method = "onClose", at = @At(value = "RETURN"))
    public void litematicaEnderchestMaterials_onClose(PlayerEntity player, CallbackInfo ci) {
        EnderchestCache.CacheEnderChestInventory((EnderChestInventory)(Object)this);
    }
}
