package net.axay.levelborder.fabric.mixin;

import net.axay.levelborder.fabric.LevelBorderMod;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerList.class)
public class PlayerListMixin {

    @Inject(method = "sendLevelInfo", at = @At("RETURN"))
    private void onPlacePlayer(ServerPlayer player, ServerLevel world, CallbackInfo ci) {
        LevelBorderMod.levelBorderHandler.initBorder(player, world.dimension() == Level.NETHER);
    }

    @Inject(method = "respawn", at = @At("RETURN"))
    private void onRespawn(ServerPlayer player, boolean alive, CallbackInfoReturnable<ServerPlayer> cir) {
        final var pos = LevelBorderMod.levelBorderHandler.getRespawnPos();
        player.teleportTo(player.server.overworld(), pos.x(), pos.y(), pos.z(), 0f, 0f);
    }
}
