package net.axay.levelborder.common;

import net.minecraft.network.protocol.game.ClientboundInitializeBorderPacket;
import net.minecraft.network.protocol.game.ClientboundSetBorderLerpSizePacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.border.WorldBorder;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class LevelBorderHandler {
    public static LevelBorderHandler currentHandler;

    private final Map<UUID, WorldBorder> borders = new HashMap<>();

    private double calculateSize(Player player) {
        return Math.max(player.experienceLevel * 2.0D, 1.0D);
    }

    protected abstract WorldBorder createWorldBorder(ServerPlayer player);

    public void initBorder(ServerPlayer player) {
        final var border = createWorldBorder(player);
        border.setCenter(0.5d, 0.5d);
        border.setSize(calculateSize(player));
        borders.put(player.getUUID(), border);

        player.connection.send(new ClientboundInitializeBorderPacket(border));
    }

    public void updateWorldBorder(ServerPlayer player) {
        final var border = borders.get(player.getUUID());
        if (border != null) {
            border.lerpSizeBetween(border.getSize(), calculateSize(player), 2L * 1000L);
            player.connection.send(new ClientboundSetBorderLerpSizePacket(border));
        }
    }

    public static class DefaultImpl extends LevelBorderHandler {
        @Override
        protected WorldBorder createWorldBorder(ServerPlayer player) {
            return new WorldBorder();
        }
    }
}
