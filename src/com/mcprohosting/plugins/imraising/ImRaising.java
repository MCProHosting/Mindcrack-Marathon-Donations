package com.mcprohosting.plugins.imraising;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;

public class ImRaising extends JavaPlugin {
	private static Plugin plugin;
	private static Configuration configuration;

	public void onEnable() {
		plugin = this;
		configuration = new Configuration();

		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
			JSONObject json = JSONHandler.readJsonFromUrl(configuration.getPathToJSON() + "?api_key=" + configuration.getApi_key() +  "&api_secret=" + configuration.getApi_secret() + "&event_id=" + configuration.getEvent_id() + "&buffer=" + configuration.getBuffer());
			WhitelistHandler.whitelistFromJSON(json);
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