package github.slkhater.shieldplotsquaredplus.commands;

import github.slkhater.shieldutils.api.MetadataAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.intellectualcrafters.plot.api.PlotAPI;

import github.slkhater.shieldplotsquaredplus.Main;
import github.slkhater.shieldutils.manager.MessageManager;
import github.slkhater.shieldutils.enums.PermissionsEnum;
import sun.plugin2.message.Message;

public class VoarCommand implements CommandExecutor{
    PlotAPI plotAPI = new PlotAPI();
    public void changeFlight(Player p, String s, boolean b){
        if(s.equals("voar")){
            MetadataAPI.setEntityMetadata(p, "voar", b, Main.getInstance());
            p.setAllowFlight(b);
            p.setFlying(b);
            if(b) MessageManager.goodPlayer(p, "&fSeu modo Voar foi ligado.");
            else MessageManager.goodPlayer(p, "&fSeu modo Voar foi desligado.");
        } else if(s.equals("voarterrenos")){
            MetadataAPI.setEntityMetadata(p, "voarterrenos", b, Main.getInstance());
            p.setAllowFlight(b);
            p.setFlying(b);
            if(b) MessageManager.goodPlayer(p, "&fSeu modo Voar em Terrenos foi ligado.");
            else MessageManager.goodPlayer(p, "&fSeu modo Voar em Terrenos foi desligado.");
            if(!b) Main.getInstance().voarDamage.add(p.getUniqueId());
            else Main.getInstance().voarDamage.remove(p.getUniqueId());
        }
    }
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("voar")) {
                if(player.hasPermission(PermissionsEnum.BYPASS_VOAR.toString())) {
                    if(!player.getMetadata("voar").isEmpty()) changeFlight(player, "voar", player.getMetadata("voar").get(0).asBoolean());
                    else changeFlight(player, "voar", true);
                } else {
                    if(plotAPI.isInPlot(player)) {
                        if(plotAPI.getPlot(player).isOwner(player.getUniqueId()) || plotAPI.getPlot(player).isAdded(player.getUniqueId())) {
                            if(!player.getMetadata("voarterrenos").isEmpty()) changeFlight(player, "voarterrenos", player.getMetadata("voarterrenos").get(0).asBoolean());
                            else changeFlight(player, "voarterrenos", true);
                        }else MessageManager.errorPlayer(player, "&fVocê deve ser o proprietário ou um membro do Terreno para ligar o modo Voar!");
                    }else MessageManager.infoPlayer(player, "&fVocê deve estar sobre um Terreno para ligar o modo Voar!");
                }
            }
        }return true;
    }
}