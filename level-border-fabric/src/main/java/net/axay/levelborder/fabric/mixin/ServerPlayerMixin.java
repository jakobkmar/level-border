package net.axay.levelborder.fabric.mixin;

import net.axay.levelborder.fabric.LevelBorderMod;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {
    @Inject(method = "giveExperiencePoints", at = @At("RETURN"))
    private void onGivePoints(CallbackInfo ci) {
        LevelBorderMod.levelBorderHandler.onChangeExperience();
    }

    @Inject(method = "setExperiencePoints", at = @At("RETURN"))
    private void onSetPoints(CallbackInfo ci) {
        LevelBorderMod.levelBorderHandler.onChangeExperience();
    }

    @Inject(method = "giveExperienceLevels", at = @At("RETURN"))
    private void onAddLevel(CallbackInfo ci) {
        LevelBorderMod.levelBorderHandler.onChangeLevel((ServerPlayer) (Object) this);
    }

    @Inject(method = "setExperienceLevels", at = @At("RETURN"))
    private void onSetLevel(CallbackInfo ci) {
        LevelBorderMod.levelBorderHandler.onChangeLevel((ServerPlayer) (Object) this);
    }

    @Inject(method = "onEnchantmentPerformed", at = @At("RETURN"))
    private void onApplyEnchantmentCost(CallbackInfo ci) {
        LevelBorderMod.levelBorderHandler.onChangeLevel((ServerPlayer) (Object) this);
    }
}
