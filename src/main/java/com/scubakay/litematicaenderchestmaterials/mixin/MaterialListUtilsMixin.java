package com.scubakay.litematicaenderchestmaterials.mixin;

import com.scubakay.litematicaenderchestmaterials.EnderChestCache;
import fi.dy.masa.litematica.materials.MaterialCache;
import fi.dy.masa.litematica.materials.MaterialListEntry;
import fi.dy.masa.litematica.materials.MaterialListUtils;
import fi.dy.masa.malilib.util.ItemType;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(MaterialListUtils.class)
public class MaterialListUtilsMixin {
    @Inject(method = "getMaterialList", at = @At(value = "INVOKE", target = "Lit/unimi/dsi/fastutil/objects/ObjectSet;iterator()Lit/unimi/dsi/fastutil/objects/ObjectIterator;", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void addEnderchestItemCount(
            Object2IntOpenHashMap<BlockState> countsTotal,
            Object2IntOpenHashMap<BlockState> countsMissing,
            Object2IntOpenHashMap<BlockState> countsMismatch,
            PlayerEntity player,
            CallbackInfoReturnable<List<MaterialListEntry>> cir,
            List<MaterialListEntry> list,
            MaterialCache cache,
            Object2IntOpenHashMap<ItemType> itemTypesTotal,
            Object2IntOpenHashMap<ItemType> itemTypesMissing,
            Object2IntOpenHashMap<ItemType> itemTypesMismatch,
            Object2IntOpenHashMap<ItemType> playerInvItems
    ) {
        Object2IntOpenHashMap<ItemType> enderChestItems = EnderChestCache.getEnderChestItems();
        if (enderChestItems != null) {
            enderChestItems.forEach((key, value) ->
                playerInvItems.merge(key, value, Integer::sum
            ));
        }
    }

    @Inject(method = "updateAvailableCounts", at = @At(value = "INVOKE", target = "Ljava/util/List;iterator()Ljava/util/Iterator;", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void updateAvailableCounts(
            List<MaterialListEntry> list,
            PlayerEntity player,
            CallbackInfo ci,
            Object2IntOpenHashMap<ItemType> playerInvItems
    ) {
        Object2IntOpenHashMap<ItemType> enderChestItems = EnderChestCache.getEnderChestItems();
        if (enderChestItems != null) {
            enderChestItems.forEach((key, value) ->
                playerInvItems.merge(key, value, Integer::sum
            ));
        }
    }
}
