package net.axay.levelborder.paper;

import net.axay.levelborder.vanilla.VanillaLevelBorderHandler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.border.WorldBorder;

public class PaperLevelBorderHandler extends VanillaLevelBorderHandler {
    @Override
    protected WorldBorder createWorldBorder(ServerPlayer player) {
        var border = super.createWorldBorder(player);
        border.world = player.getLevel();
        return border;
    }
}
