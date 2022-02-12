package github.slkhater.shieldplotsquaredplus;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.stream.Collectors;

public class PlotSquaredAPI {
    static PlotAPI plotAPI = new PlotAPI();
    static FileConfiguration storageFile = Main.getInstance().getStorage();
    public static Plot getPlot(String plotId){
        for(Plot plot : plotAPI.getAllPlots()) if(plot.getId().toString().equals(plotId)) return plot;
        return null;
    }
    public static OfflinePlayer getPlotOwner(String plotID){
        Plot plot = getPlot(plotID);
        assert plot != null;
        List<String> owners = plot.getOwners().stream().map(Bukkit::getOfflinePlayer).map(OfflinePlayer::getName).collect(Collectors.toList());
        return Bukkit.getOfflinePlayer(owners.get(0));
    }
    public static int getPontuacaoPlot(Plot plot){ return storageFile.getInt("Terrenos."+plot.getId()); }
    public static int getPontuacaoPlot(String plotId){ return storageFile.getInt("Terrenos."+plotId); }
}
