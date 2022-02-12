package github.slkhater.shieldplotsquaredplus.listeners;

import github.slkhater.shieldutils.api.MetadataAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import github.slkhater.shieldplotsquaredplus.Main;
import github.slkhater.shieldutils.manager.MessageManager;

public class PlayerLeavePlotEvent implements Listener{
	@EventHandler
	public void onPlayerLeavePlotEvent(com.plotsquared.bukkit.events.PlayerLeavePlotEvent e) {
		final Player player = e.getPlayer();
		if(!player.getMetadata("voarterrenos").isEmpty()) {
			if(player.getMetadata("voarterrenos").get(0).asBoolean()) {
				MetadataAPI.setEntityMetadata(player, "voarterrenos", false, Main.getInstance());
				player.setAllowFlight(false);
				player.setFlying(false);
				MessageManager.infoPlayer(player, "&fSeu modo Voar foi desligado devido a você sair do Terreno!");
				Main.getInstance().voarDamage.add(player.getUniqueId());
			}
		} else {
			MetadataAPI.setEntityMetadata(player, "voarterrenos", false, Main.getInstance());
			player.setAllowFlight(false);
			player.setFlying(false);
			MessageManager.infoPlayer(player, "&fSeu modo Voar foi desligado devido a você sair do Terreno!");
			Main.getInstance().voarDamage.add(player.getUniqueId());
		}
	}
}