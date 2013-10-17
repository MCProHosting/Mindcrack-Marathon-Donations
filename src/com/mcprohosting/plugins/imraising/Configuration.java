package com.mcprohosting.plugins.imraising;

public class Configuration {
	private String pathToJSON;
	private int refreshRate;
	private int buffer;
	private String api_key;
	private String api_secret;
	private String event_id;

	public Configuration() {
		ImRaising.getPlugin().saveDefaultConfig();

		String jsonList = ImRaising.getPlugin().getConfig().getString("jsonlist");
		if (JSONHandler.verifyURL(jsonList)) {
			pathToJSON = jsonList;
		} else {
			throw new RuntimeException("You ust enter a valid Child's Play JSON URL!");
		}

		refreshRate = ImRaising.getPlugin().getConfig().getInt("refreshtime");
		buffer      = ImRaising.getPlugin().getConfig().getInt("buffer");
		api_key     = ImRaising.getPlugin().getConfig().getString("api_key");
		api_secret  = ImRaising.getPlugin().getConfig().getString("api_secret");
		event_id    = ImRaising.getPlugin().getConfig().getString("event_id");
	}

	public String getPathToJSON() {
		return pathToJSON;
	}

	public int getRefreshRate() {
		return refreshRate;
	}

	public int getBuffer() {
		return buffer;
	}

	public String getApi_key() {
		return api_key;
	}

	public String getApi_secret() {
		return api_secret;
	}

	public String getEvent_id() {
		return event_id;
	}
}
