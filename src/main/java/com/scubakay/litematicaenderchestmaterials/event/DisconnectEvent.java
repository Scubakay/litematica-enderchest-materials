package com.scubakay.litematicaenderchestmaterials.event;

import com.scubakay.litematicaenderchestmaterials.EnderChestCache;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;

public class DisconnectEvent implements ClientPlayConnectionEvents.Disconnect {
    @Override
    public void onPlayDisconnect(ClientPlayNetworkHandler handler, MinecraftClient client) {
        EnderChestCache.reset();
    }
}
