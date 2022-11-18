package net.axay.levelborder.vanilla;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.axay.levelborder.common.BorderMode;
import net.axay.levelborder.common.LevelBorderHandler;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.function.Supplier;

public class VanillaLevelBorderCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher,
                                Supplier<LevelBorderHandler<ServerPlayer, ?, ?>> levelBorderHandlerSupplier) {
        LiteralArgumentBuilder<CommandSourceStack> command = Commands.literal("levelborder")
            .requires(source -> source.hasPermission(2))
            .executes(context -> {
                context.getSource().sendSuccess(
                    Component.literal("You executed the base command of level-border. If you want to change some settings, continue with the subcommands."),
                    true
                );
                return 1;
            })
            .then(Commands.literal("mode")
                .then(Commands.argument("mode", StringArgumentType.string())
                    .suggests((context, builder) -> {
                        for (BorderMode value : BorderMode.values()) {
                            builder.suggest(value.name());
                        }
                        return builder.buildFuture();
                    })
                    .executes(context -> {
                        levelBorderHandlerSupplier.get().setMode(
                            BorderMode.valueOf(context.getArgument("mode", String.class))
                        );
                        return 1;
                    })
                )
            );
        dispatcher.register(command);
    }
}
