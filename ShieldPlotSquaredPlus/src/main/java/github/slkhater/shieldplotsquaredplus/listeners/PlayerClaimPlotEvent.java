package github.slkhater.shieldplotsquaredplus.listeners;

import com.intellectualcrafters.plot.api.PlotAPI;
import github.slkhater.shieldplotsquaredplus.Main;
import github.slkhater.shieldutils.enums.PermissionsEnum;
import github.slkhater.shieldutils.manager.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerClaimPlotEvent implements Listener{
    PlotAPI plotAPI = new PlotAPI();
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerClaimPlotEvent(com.plotsquared.bukkit.events.PlayerClaimPlotEvent e){
        final Player player = e.getPlayer();
        if(plotAPI.getPlayerPlotCount(Bukkit.getServer().getWorld("Terrenos"),player)>=3&&!player.hasPermission(PermissionsEnum.BYPASS_PLOT_CLAIM.toString())) {
            e.setCancelled(true);
            MessageManager.errorPlayer(player, "Ocorreu um erro ao adquirir o Terreno, você atingiu o limite de Terrenos em posse!");
        }
        Main.getInstance().getStorage().set("Terrenos."+e.getPlot().getId(),0);
        Main.getInstance().saveStorageFile();
    }
}