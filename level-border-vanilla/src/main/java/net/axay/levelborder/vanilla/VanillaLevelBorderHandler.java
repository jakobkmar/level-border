package net.axay.levelborder.vanilla;

import net.axay.levelborder.common.BorderMode;
import net.axay.levelborder.common.LevelBorderHandler;
import net.axay.levelborder.common.Pos3i;
import net.minecraft.network.protocol.game.ClientboundInitializeBorderPacket;
import net.minecraft.network.protocol.game.ClientboundSetBorderLerpSizePacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.border.WorldBorder;

import java.util.Collection;
import java.util.UUID;

public abstract class VanillaLevelBorderHandler extends LevelBorderHandler<ServerPlayer, WorldBorder, MinecraftServer> {
    @Override
    public void setMode(BorderMode mode) {
       var borderModeSavedData = getServer().overworld().getDataStorage()
            .computeIfAbsent(BorderModeSavedData::load, BorderModeSavedData::new, "levelBorder");
       borderModeSavedData.borderMode = mode;
       borderModeSavedData.setDirty();

       super.setMode(mode);
    }

    @Override
    protected BorderMode getMode() {
        return getServer().overworld().getDataStorage()
            .computeIfAbsent(BorderModeSavedData::load, BorderModeSavedData::new, "levelBorder")
            .borderMode;
    }

    @Override
    protected WorldBorder createWorldBorder(ServerPlayer player) {
        return new WorldBorder();
    }

    @Override
    protected void initBorder(ServerPlayer player, WorldBorder border, double size) {
        border.setSize(size);
        player.connection.send(new ClientboundInitializeBorderPacket(border));
    }

    @Override
    protected void interpolateBorder(ServerPlayer player, WorldBorder border, double size, long time) {
        border.lerpSizeBetween(border.getSize(), size, time);
        player.connection.send(new ClientboundSetBorderLerpSizePacket(border));
    }

    @Override
    protected void setCenter(WorldBorder border, double centerX, double centerZ) {
        border.setCenter(centerX, centerZ);
    }

    @Override
    protected Collection<ServerPlayer> getPlayers() {
        return getServer().getPlayerList().getPlayers();
    }

    @Override
    protected double getDistance(ServerPlayer player, WorldBorder border) {
        return border.getDistanceToBorder(player);
    }

    @Override
    protected Pos3i sharedOverworldSpawn() {
        final var pos = getServer().overworld().getSharedSpawnPos();
        return new Pos3i(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    protected int getTotalExperience(ServerPlayer player) {
        return player.totalExperience;
    }

    @Override
    protected int getExperienceLevel(ServerPlayer player) {
        return player.experienceLevel;
    }

    @Override
    protected void copyExperience(ServerPlayer player, ServerPlayer other) {
        player.totalExperience = other.totalExperience;
        player.experienceProgress = other.experienceProgress;
        player.experienceLevel = other.experienceLevel;
    }

    @Override
    protected UUID getUUID(ServerPlayer player) {
        return player.getUUID();
    }

    @Override
    protected void hurt(ServerPlayer player, float damage) {
        player.hurt(DamageSource.IN_WALL, damage);
    }
}
