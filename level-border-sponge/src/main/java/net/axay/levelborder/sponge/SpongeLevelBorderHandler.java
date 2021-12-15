package net.axay.levelborder.sponge;

import net.axay.levelborder.common.LevelBorderHandler;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.EventContext;
import org.spongepowered.api.world.WorldBorder;

import java.util.UUID;

public class SpongeLevelBorderHandler extends LevelBorderHandler<Player, WorldBorder> {
    @Override
    protected WorldBorder createWorldBorder(Player player) {
        return WorldBorder.builder().build();
    }

    @Override
    protected void initBorder(Player player, WorldBorder worldBorder, double posX, double posZ, double size) {
        worldBorder.setCenter(posX, posZ);
        worldBorder.setDiameter(size);
        player.setWorldBorder(worldBorder, Cause.builder().build(EventContext.builder().build()));
    }

    @Override
    protected void interpolateBorder(Player player, WorldBorder worldBorder, double size, long time) {
        worldBorder.setDiameter(worldBorder.getDiameter(), size, time);
        player.setWorldBorder(worldBorder, Cause.builder().build(EventContext.builder().build()));
    }

    @Override
    protected int getExperienceLevels(Player player) {
        return player.get(Keys.EXPERIENCE_LEVEL).orElse(0);
    }

    @Override
    protected UUID getUUID(Player player) {
        return player.getUniqueId();
    }
}
