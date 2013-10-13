package com.mcprohosting.plugins.imraising;

public class Configuration {
	private String pathToJSON;
	private int refreshRate;
	private int buffer;
	private String cpEventID;
	private String cpUsername;
	private String cpPassword;

	public Configuration() {
		ImRaising.getPlugin().saveDefaultConfig();

		String jsonList = ImRaising.getPlugin().getConfig().getString("jsonlist");
		if (JSONHandler.verifyURL(jsonList)) {
			pathToJSON = jsonList;
		} else {
			throw new RuntimeException("You ust enter a valid Child's Play JSON URL!");
		}

		refreshRate = ImRaising.getPlugin().getConfig().getInt("refreshtime");
		buffer = ImRaising.getPlugin().getConfig().getInt("buffer");
		cpUsername = ImRaising.getPlugin().getConfig().getString("username");
		cpPassword = ImRaising.getPlugin().getConfig().getString("password");
		cpEventID = ImRaising.getPlugin().getConfig().getString("event_id");
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

	public String getCpEventID() {
		return cpEventID;
	}

	public String getCpUsername() {
		return cpUsername;
	}

	public String getCpPassword() {
		return cpPassword;
	}
}
