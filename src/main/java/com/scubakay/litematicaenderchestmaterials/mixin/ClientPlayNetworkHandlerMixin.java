package com.scubakay.litematicaenderchestmaterials.mixin;

import com.scubakay.litematicaenderchestmaterials.EnderChestCache;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import net.minecraft.network.packet.s2c.play.InventoryS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Inject(method = "onInventory", at = @At(value = "RETURN"))
    public void injectOnInventory(InventoryS2CPacket packet, CallbackInfo ci) {
        EnderChestCache.handleInventoryS2CPacket(packet);
    }

    @Inject(method = "onGameJoin", at = @At("HEAD"))
    public void injectOnGameJoin(GameJoinS2CPacket packet, CallbackInfo ci) {
        EnderChestCache.reset();
    }
}
