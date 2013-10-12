package com.mcprohosting.plugins.imraising;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ImRaising extends JavaPlugin {
	private static Plugin plugin;

	public static String pathToJSON;
	public static int refreshRate;
	public static int buffer;

	public void onEnable() {
		loadConfiguration();

		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
			WhitelistHandler.whitelistFromJSON(JSONHandler.readJsonFromUrl(pathToJSON));
			}
		}, refreshRate*20, refreshRate*20);

		plugin = this;
		getLogger().info("Initialization Complete");
	}

	public void onDisable() {
		getLogger().info("Shutdown Complete");
	}

	private void loadConfiguration() {
		this.saveDefaultConfig();

		String jsonList = this.getConfig().getString("jsonlist");
		if (JSONHandler.verifyURL(jsonList)) {
			pathToJSON = jsonList;
		} else {
			throw new RuntimeException("JSON is not valid!");
		}

		refreshRate = this.getConfig().getInt("refreshtime");
		buffer = this.getConfig().getInt("buffer");
	}

	public static Plugin getPlugin() {
		return plugin;
	}
}
