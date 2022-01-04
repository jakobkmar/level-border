package net.axay.levelborder.paper;

import com.destroystokyo.paper.event.server.ServerTickStartEvent;
import net.axay.levelborder.common.LevelBorderHandler;
import net.axay.levelborder.vanilla.VanillaLevelBorderCommand;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.border.WorldBorder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_18_R1.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;

public class LevelBorderPlugin extends JavaPlugin implements Listener {
    private LevelBorderHandler<ServerPlayer, WorldBorder, ?> levelBorderHandler;

    @Override
    public void onLoad() {
        levelBorderHandler = new PaperLevelBorderHandler();
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        VanillaLevelBorderCommand.register(
            ((CraftServer) Bukkit.getServer())
                .getServer().vanillaCommandDispatcher.getDispatcher(),
            () -> levelBorderHandler
        );
    }

    private ServerPlayer toVanillaPlayer(Player player) {
        return ((org.bukkit.craftbukkit.v1_18_R1.entity.CraftPlayer) player).getHandle();
    }

    private boolean isInNether(Player player) {
        return player.getWorld().getEnvironment() == World.Environment.NETHER;
    }

    @EventHandler
    public void onPlayerTick(ServerTickStartEvent event) {
        for (ServerPlayer player : ((CraftServer) Bukkit.getServer()).getServer().getPlayerList().getPlayers()) {
            levelBorderHandler.checkOutsideBorder(player);
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        final var player = toVanillaPlayer(event.getPlayer());

        final var pos = levelBorderHandler.getRespawnPos();
        event.setRespawnLocation(new Location(Bukkit.getWorld("world"), pos.x(), pos.y(), pos.z()));

        Bukkit.getScheduler().runTaskLater(this, () -> {
            levelBorderHandler.initBorder(player);
        }, 20L);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskLater(this, () -> {
            levelBorderHandler.initBorder(toVanillaPlayer(event.getPlayer()), isInNether(event.getPlayer()));
        }, 20L);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Bukkit.getScheduler().runTaskLater(this, () -> {
            levelBorderHandler.onLeave(toVanillaPlayer(event.getPlayer()));
        }, 20L);
    }

    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent event) {
        levelBorderHandler.initBorder(toVanillaPlayer(event.getPlayer()), isInNether(event.getPlayer()));
    }

    @EventHandler
    public void onChangeLevel(PlayerLevelChangeEvent event) {
        levelBorderHandler.onChangeLevel(toVanillaPlayer(event.getPlayer()));
    }

    @EventHandler
    public void onChangePoints(PlayerExpChangeEvent event) {
        levelBorderHandler.onChangeExperience();
    }
}
