package github.slkhater.shieldplotsquaredplus.listeners;

import com.intellectualcrafters.plot.api.PlotAPI;
import github.slkhater.shieldutils.api.InventoriesAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import github.slkhater.shieldplotsquaredplus.inventories.TerrenosInventories;

public class PlayerCommandPreprocessEvent implements Listener{
	PlotAPI plotAPI = new PlotAPI();
	@EventHandler
	public void onPlayerCommandPreprocessEvent(org.bukkit.event.player.PlayerCommandPreprocessEvent e) {
		Player player = e.getPlayer();
		String message = e.getMessage().replaceAll(" ", "");
		if(message.equalsIgnoreCase("/p") || message.equalsIgnoreCase("/plot") || message.equalsIgnoreCase("/plots")) {
			if(plotAPI.getPlayerPlotCount(Bukkit.getServer().getWorld("Terrenos"), player) > 0){
				e.setCancelled(true);
				InventoriesAPI.closeInventoryAndOpenNew(player, TerrenosInventories.comTerrenosInventory, Sound.NOTE_BASS, 0.7F);
			} else {
				e.setCancelled(true);
				InventoriesAPI.closeInventoryAndOpenNew(player, TerrenosInventories.semTerrenosInventory, Sound.NOTE_BASS, 0.7F);
			}
		}
	}
}