package net.axay.levelborder.paper;

import net.axay.levelborder.common.LevelBorderHandler;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class ExperienceListener implements Listener {
    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        LevelBorderHandler.currentHandler.initBorder(((CraftPlayer) event.getPlayer()).getHandle());
    }

    @EventHandler
    private void onChangeLevel(PlayerLevelChangeEvent event) {
        LevelBorderHandler.currentHandler.updateWorldBorder(((CraftPlayer) event.getPlayer()).getHandle());
    }
}
