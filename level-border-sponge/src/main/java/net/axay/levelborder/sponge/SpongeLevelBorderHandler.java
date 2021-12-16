package net.axay.levelborder.sponge;

import net.axay.levelborder.common.LevelBorderHandler;
import net.axay.levelborder.common.Pos3i;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.EventContext;
import org.spongepowered.api.world.WorldBorder;

import java.util.Collection;
import java.util.UUID;

public class SpongeLevelBorderHandler extends LevelBorderHandler<Player, WorldBorder> {
    @Override
    protected WorldBorder createWorldBorder(Player player) {
        return WorldBorder.builder().build();
    }

    @Override
    protected void initBorder(Player player, WorldBorder border, double size) {
        border.setDiameter(size);
        player.setWorldBorder(border, Cause.builder().build(EventContext.builder().build()));
    }

    @Override
    protected void interpolateBorder(Player player, WorldBorder border, double size, long time) {
        border.setDiameter(border.getDiameter(), size, time);
        player.setWorldBorder(border, Cause.builder().build(EventContext.builder().build()));
    }

    @Override
    protected void setCenter(WorldBorder worldBorder, double centerX, double centerZ) {
        worldBorder.setCenter(centerX, centerZ);
    }

    @Override
    protected Collection<Player> getPlayers(Player player) {
        return Sponge.getServer().getOnlinePlayers();
    }

    @Override
    protected Pos3i sharedOverworldSpawn(Player player) {
        final var pos = Sponge.getServer().getDefaultWorld().orElseThrow().getSpawnPosition();
        return new Pos3i(pos.getX(), pos.getY(), pos.getZ());
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
