package net.axay.levelborder.common;

import java.util.*;

public abstract class LevelBorderHandler<Player, WorldBorder, Server> {
    private final Map<UUID, WorldBorder> borders = new HashMap<>();

    private double calculateSize(Player player) {
        final int experience = switch (getMode()) {
            case OWN, SHARED -> getExperienceLevel(player);
            case SUM -> getPlayers().stream()
                .map(this::getExperienceLevel).reduce(0, Integer::sum);
        };
        return Math.max(experience * 2.0D, 1.0D);
    }

    final public void initBorder(Player player) {
        if (getMode() == BorderMode.SHARED) {
            shareExperience();
        }

        final var spawn = sharedOverworldSpawn();

        final var border = createWorldBorder(player);
        setCenter(border, spawn.x() + 0.5d, spawn.z() + 0.5d);
        initBorder(player, border, calculateSize(player));
        borders.put(getUUID(player), border);

        for (Player onlinePlayer : getPlayers()) {
            if (onlinePlayer != player) {
                updateWorldBorder(onlinePlayer);
            }
        }
    }

    final public void onChangeLevel(Player player) {
        final var mode = getMode();
        if (mode != BorderMode.OWN) {
            if (mode == BorderMode.SHARED) {
                shareExperience();
            }
            updateForAll();
        } else {
            updateWorldBorder(player);
        }
    }

    final public void updateWorldBorder(Player player) {
        final var border = borders.get(getUUID(player));
        if (border != null) {
            interpolateBorder(player, border, calculateSize(player), 2L * 1000L);
        }
    }

    final public void checkOutsideBorder(Player player) {
        final var border = borders.get(getUUID(player));
        if (border != null) {
            if (getDistance(player, border) + 5.0d < 0) {
                hurt(player, 1f);
            }
        }
    }

    final public void onLeave(Player player) {
        borders.remove(getUUID(player));
        updateForAll();
    }

    final public Pos3i getRespawnPos() {
        return sharedOverworldSpawn();
    }

    private void shareExperience() {
        final var maxPlayer = getPlayers().stream()
            .max(Comparator.comparingInt(this::getTotalExperience))
            .orElseThrow();
        for (Player player : getPlayers()) {
            if (player != maxPlayer) {
                copyExperience(player, maxPlayer);
            }
        }
    }

    private void updateForAll() {
        getPlayers().forEach(this::updateWorldBorder);
    }

    public void setMode(BorderMode mode) {
        if (mode == BorderMode.SHARED) {
            shareExperience();
        }
        updateForAll();
    }

    abstract protected BorderMode getMode();

    abstract protected WorldBorder createWorldBorder(Player player);
    abstract protected void setCenter(WorldBorder border, double centerX, double centerZ);

    abstract protected void initBorder(Player player, WorldBorder border, double size);
    abstract protected void interpolateBorder(Player player, WorldBorder border, double size, long time);

    abstract protected Server getServer();
    abstract protected Collection<Player> getPlayers();

    abstract protected double getDistance(Player player, WorldBorder border);

    abstract protected Pos3i sharedOverworldSpawn();

    abstract protected int getTotalExperience(Player player);
    abstract protected int getExperienceLevel(Player player);

    abstract protected void copyExperience(Player player, Player other);

    abstract protected UUID getUUID(Player player);
    abstract protected void hurt(Player player, float damage);
}
