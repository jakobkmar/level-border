package net.axay.levelborder.sponge;

import net.axay.levelborder.common.LevelBorderHandler;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.ChangeEntityExperienceEvent;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;

@Plugin(id = "level-border")
public class LevelBorderPlugin {
    @Listener
    public void onPlayerJoin(ClientConnectionEvent.Join event) {
        if (event.getTargetEntity() instanceof ServerPlayer player) {
            LevelBorderHandler.currentHandler.initBorder(player);
        }
    }

    @Listener
    public void onChangeWorld(MoveEntityEvent.Teleport event) {
        if (event.getFromTransform().getExtent() != event.getToTransform().getExtent()) {
            if (event.getTargetEntity() instanceof ServerPlayer player) {
                LevelBorderHandler.currentHandler.initBorder(player);
            }
        }
    }

    @Listener
    public void onChangeLevel(ChangeEntityExperienceEvent event) {
        if (event.getOriginalData().level().get().intValue() != event.getFinalData().level().get().intValue()) {
            if (event.getTargetEntity() instanceof ServerPlayer player) {
                LevelBorderHandler.currentHandler.updateWorldBorder(player);
            }
        }
    }
}
