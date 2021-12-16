package net.axay.levelborder.vanilla;

import net.minecraft.server.MinecraftServer;

public class ModLevelBorderHandler extends VanillaLevelBorderHandler {
    private final MinecraftServer server;

    public ModLevelBorderHandler(MinecraftServer server) {
        this.server = server;
    }

    @Override
    protected MinecraftServer getServer() {
        return server;
    }
}
