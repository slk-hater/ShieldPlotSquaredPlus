package github.slkhater.shieldplotsquaredplus.listeners;

import github.slkhater.shieldutils.api.MetadataAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import github.slkhater.shieldplotsquaredplus.Main;

public class PlayerJoinEvent implements Listener{
	@EventHandler
	public void onPlayerJoinEvent(org.bukkit.event.player.PlayerJoinEvent e) {
		MetadataAPI.setEntityMetadata(e.getPlayer(), "voar", false, Main.getInstance());
		MetadataAPI.setEntityMetadata(e.getPlayer(), "voarterrenos", false, Main.getInstance());
	}
}