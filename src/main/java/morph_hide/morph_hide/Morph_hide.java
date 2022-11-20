package morph_hide.morph_hide;

import org.bukkit.plugin.java.JavaPlugin;

public final class Morph_hide extends JavaPlugin {
    public static Morph_hide plugin;
    public static Morph_hide instance;
    public Morph_hide(){instance = this;}
    public static Morph_hide getInstance(){return instance;}
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        getServer().getPluginManager().registerEvents(new EventListener(),this);
        getCommand("azgame").setExecutor(new CommandAz());
        itemManager.init();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static Morph_hide getPlugin(){
        return plugin;
    }
}
