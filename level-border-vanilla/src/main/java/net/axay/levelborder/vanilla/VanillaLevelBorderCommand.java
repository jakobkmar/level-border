package net.axay.levelborder.vanilla;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.axay.levelborder.common.BorderMode;
import net.axay.levelborder.common.LevelBorderHandler;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;

import java.util.concurrent.CompletableFuture;

public class VanillaLevelBorderCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher,
                                LevelBorderHandler<ServerPlayer, ?> levelBorderHandler) {
        LiteralArgumentBuilder<CommandSourceStack> command = Commands.literal("levelborder")
            .requires(source -> source.hasPermission(2))
            .then(Commands.literal("mode")
                .then(Commands.argument("mode", new BorderModeArgumentType())
                    .executes(context -> {
                        levelBorderHandler.setMode(
                            context.getSource().getPlayerOrException(),
                            context.getArgument("mode", BorderMode.class)
                        );
                        return 1;
                    })
                )
            );
        dispatcher.register(command);
    }

    private static class BorderModeArgumentType implements ArgumentType<BorderMode> {
        @Override
        public BorderMode parse(StringReader reader) throws CommandSyntaxException {
            return BorderMode.valueOf(reader.readString());
        }

        @Override
        public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
            for (BorderMode value : BorderMode.values()) {
                builder.suggest(value.name());
            }
            return builder.buildFuture();
        }
    }
}
