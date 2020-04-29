package me.saltyfishhy.throwTNT;

import me.saltyfishhy.Commands.Commands;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        //registers the commands
        getCommand("tnttutorial").setExecutor(new Commands());
        getCommand("gettnt").setExecutor(new Commands());
        getCommand("tntreload").setExecutor(new Commands());
        //sends a message to the console saying the plugin has been enabled
        getLogger().info("Throwable TNT plugin Enabled.");
        //creates a variable named "pm" that is equal to the plugin manager
        PluginManager pm = this.getServer().getPluginManager();
        //registers the events from the Events Class
        pm.registerEvents(new Events(), this);
        //runs the loadConfig() method
        loadConfig();
    }

    @Override
    public void onDisable() {
        //sends a message to the console saying the command has been disabled
        getLogger().info("Throwable TNT plugin Disabled.");

    }

    public void loadConfig(){
        //tells the config file to copy the defaults that were created with the file initially
        getConfig().options().copyDefaults(true);
        //saves the config
        saveConfig();
    }


}
