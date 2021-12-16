package net.axay.levelborder.fabric.mixin;

import com.mojang.brigadier.CommandDispatcher;
import net.axay.levelborder.fabric.LevelBorderMod;
import net.axay.levelborder.vanilla.VanillaLevelBorderCommand;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Commands.class)
public class CommandsMixin {
    @Shadow @Final private CommandDispatcher<CommandSourceStack> dispatcher;

    @Inject(
        method = "<init>",
        at = @At(
            value = "INVOKE",
            target = "Lcom/mojang/brigadier/CommandDispatcher;findAmbiguities(Lcom/mojang/brigadier/AmbiguityConsumer;)V",
            shift = At.Shift.BEFORE
        )
    )
    public void onRegister(CallbackInfo ci) {
        VanillaLevelBorderCommand.register(dispatcher, LevelBorderMod.levelBorderHandler);
    }
}
