package net.axay.levelborder.fabric.mixin;

import net.axay.levelborder.common.LevelBorderHandler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {
    @Inject(method = "giveExperienceLevels", at = @At("RETURN"))
    private void onAddLevel(int levels, CallbackInfo ci) {
        LevelBorderHandler.currentHandler.updateWorldBorder((ServerPlayer) (Object) this);
    }

    @Inject(method = "setExperiencePoints", at = @At("RETURN"))
    private void onSetLevel(int level, CallbackInfo ci) {
        LevelBorderHandler.currentHandler.updateWorldBorder((ServerPlayer) (Object) this);
    }

    @Inject(method = "onEnchantmentPerformed", at = @At("RETURN"))
    private void onApplyEnchantmentCost(ItemStack enchantedItem, int experienceLevels, CallbackInfo ci) {
        LevelBorderHandler.currentHandler.updateWorldBorder((ServerPlayer) (Object) this);
    }
}
