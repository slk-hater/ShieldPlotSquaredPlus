package github.slkhater.shieldplotsquaredplus.listeners;

import github.slkhater.shieldplotsquaredplus.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlotDeleteEvent implements Listener {
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlotDeleteEvent(com.plotsquared.bukkit.events.PlotDeleteEvent e){
        Main.getInstance().getStorage().set("Terrenos."+e.getPlot().getId(),null);
        Main.getInstance().saveStorageFile();
    }
}
