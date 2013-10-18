package com.mcprohosting.plugins.imraising;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;

import java.io.*;

public class ImRaising extends JavaPlugin {
	private static Plugin plugin;
	private static Configuration configuration;
	private static JSONObject lastJson;

	public void onEnable() {
		plugin = this;
		configuration = new Configuration();

		getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
			public void run() {
			lastJson = JSONHandler.readJsonFromUrl(configuration.getPathToJSON() + "?api_key=" + configuration.getApi_key() +  "&api_secret=" + configuration.getApi_secret() + "&event_id=" + configuration.getEvent_id() + "&buffer=" + configuration.getBuffer());
			}
		}, configuration.getRefreshRate()*20, configuration.getRefreshRate()*20);

		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				WhitelistHandler.whitelistFromJSON(lastJson);
			}
		}, configuration.getRefreshRate()+2*20, configuration.getRefreshRate()+2*20);

		getLogger().info("Initialization Complete");
	}

	public void onDisable() {
		getLogger().info("Shutdown Complete");
	}

	public static Plugin getPlugin() {
		return plugin;
	}

	public static void logToFile(JSONObject json) {
		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("json.log"), "utf-8"));
			writer.write("Status: " + json.get("status").toString().toLowerCase() + " Comment: " + json.get("comment".toString().trim()));
		} catch (IOException ex) {} finally {
			try {
				writer.close();
			} catch (Exception e) {}
		}
	}
}