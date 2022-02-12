package github.slkhater.shieldplotsquaredplus.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import github.slkhater.shieldplotsquaredplus.Main;

public class EntityDamageEvent implements Listener{
	@EventHandler
	public void onEntityDamageEvent(org.bukkit.event.entity.EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			if(e.getCause() == DamageCause.FALL  && Main.getInstance().voarDamage.contains(player.getUniqueId())) {
				e.setCancelled(true);
				Main.getInstance().voarDamage.remove(player.getUniqueId());
			}
		}
	}
}