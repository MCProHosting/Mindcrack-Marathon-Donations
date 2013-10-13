package com.mcprohosting.plugins.imraising;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ImRaising extends JavaPlugin {
	private static Plugin plugin;
	private static Configuration configuration;

	public void onEnable() {
		plugin = this;
		configuration = new Configuration();

		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
			WhitelistHandler.whitelistFromJSON(JSONHandler.readJsonFromUrl(configuration.getPathToJSON()));
			}
		}, configuration.getRefreshRate()*20, configuration.getRefreshRate()*20);

		getLogger().info("Initialization Complete");
	}

	public void onDisable() {
		getLogger().info("Shutdown Complete");
	}

	public static Plugin getPlugin() {
		return plugin;
	}
}