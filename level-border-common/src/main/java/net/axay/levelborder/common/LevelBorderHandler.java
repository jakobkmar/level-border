package net.axay.levelborder.common;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class LevelBorderHandler<Player, WorldBorder> {
    private final Map<UUID, WorldBorder> borders = new HashMap<>();

    private double calculateSize(Player player) {
        return Math.max(getExperienceLevels(player) * 2.0D, 1.0D);
    }

    final public void initBorder(Player player) {
        final var border = createWorldBorder(player);
        initBorder(player, border, 0.5d, 0.5d, calculateSize(player));
        borders.put(getUUID(player), border);
    }

    public void updateWorldBorder(Player player) {
        final var border = borders.get(getUUID(player));
        if (border != null) {
            interpolateBorder(player, border, calculateSize(player), 2L * 1000L);
        }
    }

    protected abstract WorldBorder createWorldBorder(Player player);

    abstract protected void initBorder(Player player, WorldBorder border, double posX, double posZ, double size);
    abstract protected void interpolateBorder(Player player, WorldBorder border, double size, long time);

    abstract protected int getExperienceLevels(Player player);
    abstract protected UUID getUUID(Player player);
}
