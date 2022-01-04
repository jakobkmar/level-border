package net.axay.levelborder.sponge;

import net.axay.levelborder.common.LevelBorderHandler;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.ChangeEntityExperienceEvent;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.event.entity.living.humanoid.player.RespawnPlayerEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.world.DimensionTypes;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.WorldBorder;

@Plugin(id = "level-border")
public class LevelBorderPlugin {
    private LevelBorderHandler<Player, WorldBorder, ?> levelBorderHandler;

    @Listener
    public void onServerStarting(GameStartingServerEvent event) {
        levelBorderHandler = new SpongeLevelBorderHandler();
        SpongeLevelBorderCommand.register(this, () -> levelBorderHandler);
    }

    @Listener
    public void onPlayerJoin(ClientConnectionEvent.Join event) {
        levelBorderHandler.initBorder(
            event.getTargetEntity(),
            event.getTargetEntity().getWorld().getDimension().getType() == DimensionTypes.NETHER
        );
    }

    @Listener
    public void onPlayerQuit(ClientConnectionEvent.Disconnect event) {
        levelBorderHandler.onLeave(event.getTargetEntity());
    }

    @Listener
    public void onChangeWorld(MoveEntityEvent.Teleport event) {
        if (event.getFromTransform().getExtent() != event.getToTransform().getExtent()) {
            if (event.getTargetEntity() instanceof Player player) {
                levelBorderHandler.initBorder(
                    player,
                    event.getToTransform().getExtent().getDimension().getType() == DimensionTypes.NETHER
                );
            }
        }
    }

    @Listener
    public void onChangePoints(ChangeEntityExperienceEvent event) {
        if (event.getTargetEntity() instanceof Player player) {
            if (event.getOriginalData().level().get().intValue() != event.getFinalData().level().get().intValue()) {
                levelBorderHandler.onChangeLevel(player);
            } else {
                levelBorderHandler.onChangeExperience();
            }
        }
    }

    @Listener
    public void onPlayerRespawn(RespawnPlayerEvent event) {
        final var pos = levelBorderHandler.getRespawnPos();
        final var loc = new Location<>(
            Sponge.getServer().getWorld("world").orElseThrow(),
            pos.x(), pos.y(), pos.z()
        );
        event.setToTransform(event.getToTransform().setLocation(loc));
    }
}
