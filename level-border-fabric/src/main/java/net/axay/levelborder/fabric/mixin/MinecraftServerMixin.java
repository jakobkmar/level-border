package net.axay.levelborder.fabric.mixin;

import net.axay.levelborder.fabric.LevelBorderMod;
import net.axay.levelborder.vanilla.ModLevelBorderHandler;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @Inject(method = "runServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;initServer()Z"))
    private void onInitServer(CallbackInfo ci) {
        LevelBorderMod.levelBorderHandler = new ModLevelBorderHandler((MinecraftServer) (Object) this);
    }
}
