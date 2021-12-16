package net.axay.levelborder.sponge;

import net.axay.levelborder.common.BorderMode;
import net.axay.levelborder.common.LevelBorderHandler;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import java.util.function.Supplier;

public class SpongeLevelBorderCommand {
    public static void register(Object plugin,
                                Supplier<LevelBorderHandler<Player, ?>> levelBorderHandlerSupplier) {
        final var modeCommand = CommandSpec.builder()
            .arguments(
                GenericArguments.onlyOne(
                    GenericArguments.enumValue(Text.of("mode"), BorderMode.class)
            ))
            .executor((src, args) -> {
                if (src instanceof Player player) {
                    levelBorderHandlerSupplier.get().setMode(
                        player,
                        args.<BorderMode>getOne("mode").orElseThrow()
                    );
                }

                return CommandResult.success();
            })
            .build();

        final var levelBorderCommand = CommandSpec.builder()
            .description(Text.of("Level Border configuration command"))
            .permission("levelborder.command.levelborder")
            .child(modeCommand)
            .build();

        Sponge.getCommandManager().register(plugin, levelBorderCommand);
    }
}
