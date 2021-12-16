package net.axay.levelborder.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class LevelBorderHandler<Player, WorldBorder> {
    private final Map<UUID, WorldBorder> borders = new HashMap<>();

    private BorderMode mode = BorderMode.OWN;

    private double calculateSize(Player player) {
        final int experience;
        if (mode == BorderMode.OWN) {
            experience = getExperienceLevels(player);
        } else {
            experience = getPlayers(player).stream()
                .map(this::getExperienceLevels).reduce(0, Integer::sum);
        }
        return Math.max(experience * 2.0D, 1.0D);
    }

    final public void initBorder(Player player) {
        final var spawn = sharedOverworldSpawn(player);

        final var border = createWorldBorder(player, spawn.x() + 0.5d, spawn.z() + 0.5d);
        initBorder(player, border, calculateSize(player));
        borders.put(getUUID(player), border);
    }

    final public void updateWorldBorder(Player player) {
        final var border = borders.get(getUUID(player));
        if (border != null) {
            interpolateBorder(player, border, calculateSize(player), 2L * 1000L);
        }
    }

    public void setMode(Player initiator, BorderMode mode) {
        this.mode = mode;
        getPlayers(initiator).forEach(this::updateWorldBorder);
    }

    protected abstract WorldBorder createWorldBorder(Player player, double centerX, double centerZ);

    abstract protected void initBorder(Player player, WorldBorder border, double size);
    abstract protected void interpolateBorder(Player player, WorldBorder border, double size, long time);

    abstract protected Collection<Player> getPlayers(Player player);

    abstract protected Pos3i sharedOverworldSpawn(Player player);
    abstract protected int getExperienceLevels(Player player);
    abstract protected UUID getUUID(Player player);
}
