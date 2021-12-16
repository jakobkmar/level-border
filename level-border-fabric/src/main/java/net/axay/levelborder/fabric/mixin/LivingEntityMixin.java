package net.axay.levelborder.fabric.mixin;

import net.axay.levelborder.fabric.LevelBorderMod;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(
        method = "baseTick",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/border/WorldBorder;isWithinBounds(Lnet/minecraft/world/phys/AABB;)Z"
        )
    )
    private void tickBorderCheck(CallbackInfo ci) {
        if ((LivingEntity) (Object) this instanceof ServerPlayer player) {
            LevelBorderMod.levelBorderHandler.checkOutsideBorder(player);
        }
    }
}
