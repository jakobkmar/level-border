package net.axay.levelborder.paper;

import net.axay.levelborder.common.LevelBorderHandler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.border.WorldBorder;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class LevelBorderPlugin extends JavaPlugin implements Listener {
    private LevelBorderHandler<ServerPlayer, WorldBorder> levelBorderHandler;

    @Override
    public void onLoad() {
        levelBorderHandler = new PaperLevelBorderHandler();
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    private ServerPlayer toVanillaPlayer(org.bukkit.entity.Player player) {
        return ((org.bukkit.craftbukkit.v1_18_R1.entity.CraftPlayer) player).getHandle();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        levelBorderHandler.initBorder(toVanillaPlayer(event.getPlayer()));
    }

    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent event) {
        levelBorderHandler.initBorder(toVanillaPlayer(event.getPlayer()));
    }

    @EventHandler
    public void onChangeLevel(PlayerLevelChangeEvent event) {
        levelBorderHandler.updateWorldBorder(toVanillaPlayer(event.getPlayer()));
    }
}
