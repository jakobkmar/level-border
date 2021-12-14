package net.axay.levelborder.paper;

import net.axay.levelborder.common.LevelBorderHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class LevelBorderPlugin extends JavaPlugin {
    @Override
    public void onLoad() {
        LevelBorderHandler.currentHandler = new LevelBorderHandler();
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new ExperienceListener(), this);
    }
}
