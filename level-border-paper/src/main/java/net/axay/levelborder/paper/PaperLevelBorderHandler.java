package net.axay.levelborder.paper;

import net.axay.levelborder.vanilla.VanillaLevelBorderHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.border.WorldBorder;
import org.bukkit.Bukkit;

public class PaperLevelBorderHandler extends VanillaLevelBorderHandler {
    @Override
    protected WorldBorder createWorldBorder(ServerPlayer player) {
        var border = super.createWorldBorder(player);
        border.world = player.getLevel();
        return border;
    }

    @Override
    protected MinecraftServer getServer() {
        return ((org.bukkit.craftbukkit.v1_18_R1.CraftServer) Bukkit.getServer()).getServer();
    }
}
