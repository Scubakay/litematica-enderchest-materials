package com.scubakay.litematicaenderchestmaterials.mixin;

import com.scubakay.litematicaenderchestmaterials.EnderChestCache;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.InventoryS2CPacket;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Inject(method = "onInventory", at = @At(value = "RETURN"))
    public void onInventory(InventoryS2CPacket packet, CallbackInfo ci) {
        EnderChestCache.handleInventoryS2CPacket(packet);
    }

    @Inject(method = "onDisconnected", at = @At("HEAD"))
    private void onDisconnected(Text reason, CallbackInfo ci) {
        EnderChestCache.reset();
    }
}
