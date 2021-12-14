package net.axay.levelborder.mixin;

import net.axay.levelborder.LevelBorderMod;
import net.minecraft.network.Connection;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerList.class)
public class PlayerListMixin {
    @Inject(method = "placeNewPlayer", at = @At("RETURN"))
    private void onPlacePlayer(Connection connection, ServerPlayer player, CallbackInfo ci) {
        LevelBorderMod.handler.initBorder(player);
    }
}
