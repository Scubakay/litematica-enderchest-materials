package com.scubakay.litematicaenderchestmaterials.mixin;

import com.scubakay.litematicaenderchestmaterials.EnderChestCache;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class ScreenMixin {
    @Inject(method = "close", at = @At(value = "RETURN"))
    public void injectClose(CallbackInfo ci) {
        EnderChestCache.closeEnderChest();
    }
}
