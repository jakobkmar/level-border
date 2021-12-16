package net.axay.levelborder.sponge;

import net.axay.levelborder.common.LevelBorderHandler;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.ChangeEntityExperienceEvent;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;
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
        levelBorderHandler.initBorder(event.getTargetEntity());
    }

    @Listener
    public void onPlayerQuit(ClientConnectionEvent.Disconnect event) {
        levelBorderHandler.onLeave(event.getTargetEntity());
    }

    @Listener
    public void onChangeWorld(MoveEntityEvent.Teleport event) {
        if (event.getFromTransform().getExtent() != event.getToTransform().getExtent()) {
            if (event.getTargetEntity() instanceof Player player) {
                levelBorderHandler.initBorder(player);
            }
        }
    }

    @Listener
    public void onChangeLevel(ChangeEntityExperienceEvent event) {
        if (event.getOriginalData().level().get().intValue() != event.getFinalData().level().get().intValue()) {
            if (event.getTargetEntity() instanceof Player player) {
                levelBorderHandler.updateWorldBorder(player);
            }
        }
    }
}
