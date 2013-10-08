package com.mcprohosting.plugins.imraising;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Logger;

public class ImRaising extends JavaPlugin {
	private Logger log;

	public void onEnable() {
		log = getLogger();

		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				try {
					WhitelistHandler.whitelistFromJSON(JSONHandler.readJsonFromUrl("http://imraising.com/minecraftmarathon/json/livedata.jsonp"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, 100, 1000);

		log.info("Initialization Complete");
	}

	public void onDisable() {
		log.info("Shutdown Complete");
	}

	public static void main(String[] args) {
		try {
			WhitelistHandler.whitelistFromJSON(JSONHandler.readJsonFromUrl("http://imraising.com/minecraftmarathon/json/livedata.jsonp"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
