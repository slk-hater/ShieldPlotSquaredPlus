package github.slkhater.shieldplotsquaredplus.commands;

import com.intellectualcrafters.plot.api.PlotAPI;
import github.slkhater.shieldplotsquaredplus.inventories.TerrenosInventories;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TerrenosCommand implements CommandExecutor {
    PlotAPI plotAPI = new PlotAPI();
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("terrenos")) {
                player.playSound(player.getLocation(), Sound.CHEST_OPEN, 0.7F, 1.0F);
                if(plotAPI.getPlayerPlotCount(Bukkit.getServer().getWorld("Terrenos"), player) > 0)player.openInventory(TerrenosInventories.comTerrenosInventory);
                else player.openInventory(TerrenosInventories.semTerrenosInventory);
            }
        }return true;
    }
}