package com.mcprohosting.plugins.imraising;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ImRaising extends JavaPlugin {
	private static Plugin plugin;

	protected static String pathToJSON;
	protected static int refreshRate;
	protected static int buffer;
	protected static String cpEventID;
	protected static String cpUsername;
	protected static String cpPassword;

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
			getLogger().warning("You ust enter a valid Child's Play JSON URL!");
		}

		refreshRate = this.getConfig().getInt("refreshtime");
		buffer      = this.getConfig().getInt("buffer");
		cpUsername  = this.getConfig().getString("username");
		cpPassword  = this.getConfig().getString("password");
		cpEventID   = this.getConfig().getString("event_id");
	}

	public static Plugin getPlugin() {
		return plugin;
	}
}
