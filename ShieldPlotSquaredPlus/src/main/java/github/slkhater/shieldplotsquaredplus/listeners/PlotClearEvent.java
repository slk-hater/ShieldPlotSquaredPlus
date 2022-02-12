package github.slkhater.shieldplotsquaredplus.listeners;

import github.slkhater.shieldplotsquaredplus.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlotClearEvent implements Listener {
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlotClearEvent(com.plotsquared.bukkit.events.PlotClearEvent e){
        Main.getInstance().getStorage().set("Terrenos."+e.getPlot().getId(),0);
        Main.getInstance().saveStorageFile();
    }
}
