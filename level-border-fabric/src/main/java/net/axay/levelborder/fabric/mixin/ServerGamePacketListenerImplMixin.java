package net.axay.levelborder.fabric.mixin;

import net.axay.levelborder.fabric.LevelBorderMod;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerGamePacketListenerImpl.class)
public class ServerGamePacketListenerImplMixin {

    @Shadow public ServerPlayer player;

    @Inject(
        method = "onDisconnect",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/players/PlayerList;remove(Lnet/minecraft/server/level/ServerPlayer;)V",
            shift = At.Shift.AFTER
        )
    )
    private void onRemovePlayer(CallbackInfo ci) {
        LevelBorderMod.levelBorderHandler.onLeave(player);
    }
}
