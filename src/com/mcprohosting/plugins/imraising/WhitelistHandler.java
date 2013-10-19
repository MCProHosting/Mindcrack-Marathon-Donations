package com.mcprohosting.plugins.imraising;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class WhitelistHandler {
	public static void whitelistFromJSON(JSONObject jsonObject) {
		System.out.println("Running white-list loop");
		JSONArray donationJSON = (JSONArray) jsonObject.get("donations");

		for (Object o : donationJSON) {
			JSONObject donation = (JSONObject) o;
			ImRaising.logToFile(donation);

			String ign = donation.get("comment").toString().toLowerCase().trim();
			if (ign.contains(" ")) {
				break;
			}

			OfflinePlayer player = Bukkit.getOfflinePlayer(ign);

			if (!player.isWhitelisted()) {
				Number donationAmount;
				donationAmount = Double.parseDouble(donation.get("amount").toString());
				
				if (donationAmount.floatValue() >= 20f) {
					player.setWhitelisted(true);
					System.out.println("White-listed: " + player.getName());
					
					Bukkit.broadcastMessage(ChatColor.GREEN + "Thank you to " + ChatColor.YELLOW + ign + ChatColor.GREEN + " for donating " + ChatColor.YELLOW + "$" + donationAmount + ChatColor.GREEN + "!");
				}
			}
		}
	}
}
