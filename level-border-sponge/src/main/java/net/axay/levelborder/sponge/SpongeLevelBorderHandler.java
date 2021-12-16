package net.axay.levelborder.sponge;

import net.axay.levelborder.common.BorderMode;
import net.axay.levelborder.common.LevelBorderHandler;
import net.axay.levelborder.common.Pos3i;
import org.spongepowered.api.Server;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.EventContext;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSources;
import org.spongepowered.api.world.WorldBorder;

import java.util.Collection;
import java.util.UUID;

public class SpongeLevelBorderHandler extends LevelBorderHandler<Player, WorldBorder, Server> {
    private BorderMode borderMode;

    @Override
    public void setMode(BorderMode mode) {
        this.borderMode = mode;
        super.setMode(mode);
    }

    @Override
    protected BorderMode getMode() {
        return borderMode;
    }

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
    protected Server getServer() {
        return Sponge.getServer();
    }

    @Override
    protected Collection<Player> getPlayers() {
        return getServer().getOnlinePlayers();
    }

    @Override
    protected double getDistance(Player player, WorldBorder worldBorder) {
        // TODO implement this for Sponge
        return 1;
    }

    @Override
    protected Pos3i sharedOverworldSpawn() {
        final var pos = getServer().getDefaultWorld().orElseThrow().getSpawnPosition();
        return new Pos3i(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    protected int getTotalExperience(Player player) {
        return player.get(Keys.TOTAL_EXPERIENCE).orElse(0);
    }

    @Override
    protected int getExperienceLevel(Player player) {
        return player.get(Keys.EXPERIENCE_LEVEL).orElse(0);
    }

    @Override
    protected void copyExperience(Player player, Player other) {
        player.offer(Keys.TOTAL_EXPERIENCE, other.get(Keys.TOTAL_EXPERIENCE).orElse(0));
    }

    @Override
    protected UUID getUUID(Player player) {
        return player.getUniqueId();
    }

    @Override
    protected void hurt(Player player, float damage) {
        player.damage(damage, DamageSources.GENERIC);
    }
}
