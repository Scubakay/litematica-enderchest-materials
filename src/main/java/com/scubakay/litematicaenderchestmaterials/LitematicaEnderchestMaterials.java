package com.scubakay.litematicaenderchestmaterials;

import com.scubakay.litematicaenderchestmaterials.event.DisconnectEvent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

public class LitematicaEnderchestMaterials implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        ClientPlayConnectionEvents.DISCONNECT.register(new DisconnectEvent());
    }
}
