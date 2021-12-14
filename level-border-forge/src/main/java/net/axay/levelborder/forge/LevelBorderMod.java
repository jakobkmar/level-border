package net.axay.levelborder.forge;

import net.axay.levelborder.common.LevelBorderHandler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("level-border")
public class LevelBorderMod {
    public LevelBorderMod() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LevelBorderHandler.currentHandler = new LevelBorderHandler.DefaultImpl();
    }

    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getPlayer() instanceof ServerPlayer player) {
            LevelBorderHandler.currentHandler.initBorder(player);
        }
    }

    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.getPlayer() instanceof ServerPlayer player) {
            LevelBorderHandler.currentHandler.initBorder(player);
        }
    }

    @SubscribeEvent
    public void onChangeLevel(PlayerXpEvent.LevelChange event) {
        if (event.getPlayer() instanceof ServerPlayer player) {
            LevelBorderHandler.currentHandler.updateWorldBorder(player);
        }
    }
}
