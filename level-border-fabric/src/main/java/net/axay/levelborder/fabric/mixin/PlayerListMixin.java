package net.axay.levelborder.fabric.mixin;

import net.axay.levelborder.common.LevelBorderHandler;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerList.class)
public class PlayerListMixin {
    @Inject(method = "sendLevelInfo", at = @At("RETURN"))
    private void onPlacePlayer(ServerPlayer player, ServerLevel world, CallbackInfo ci) {
        LevelBorderHandler.currentHandler.initBorder(player);
    }
}
