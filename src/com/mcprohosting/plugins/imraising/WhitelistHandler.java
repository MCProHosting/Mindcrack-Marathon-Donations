package com.mcprohosting.plugins.imraising;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class WhitelistHandler {
	public static void whitelistFromJSON(JSONObject jsonObject) {
		JSONArray donationJSON = (JSONArray) jsonObject.get("donations");
		
		for (Object o : donationJSON) {
			JSONObject donation = (JSONObject) o;
			
			String ign = donation.get("comments").toString().toLowerCase().trim();

			OfflinePlayer player = Bukkit.getOfflinePlayer(ign);
			
			if (!player.isWhitelisted()) {
				Number donationAmount;
				donationAmount =  Integer.parseInt(donation.get("amount").toString());
				
				if (donationAmount.floatValue() >= 20f) {
					player.setWhitelisted(true);
					
					Bukkit.broadcastMessage(ChatColor.GREEN + "Thank you to " + ChatColor.YELLOW + ign + ChatColor.GREEN + " for donating " + ChatColor.YELLOW + "$" + donationAmount + ChatColor.GREEN + "!");
				}
			}
		}
	}
}
