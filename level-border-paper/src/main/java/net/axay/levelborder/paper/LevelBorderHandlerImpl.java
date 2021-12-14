package net.axay.levelborder.paper;

import net.axay.levelborder.common.LevelBorderHandler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.border.WorldBorder;

public class LevelBorderHandlerImpl extends LevelBorderHandler {
    @Override
    protected WorldBorder createWorldBorder(ServerPlayer player) {
        var border = new WorldBorder();
        border.world = player.getLevel();
        return border;
    }
}
