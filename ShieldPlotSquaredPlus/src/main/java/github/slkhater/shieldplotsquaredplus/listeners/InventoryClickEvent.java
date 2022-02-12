package github.slkhater.shieldplotsquaredplus.listeners;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotPlayer;
import github.slkhater.shieldplotsquaredplus.Main;
import github.slkhater.shieldplotsquaredplus.PlotSquaredAPI;
import github.slkhater.shieldutils.api.ColorsAPI;
import github.slkhater.shieldutils.api.InventoriesAPI;
import github.slkhater.shieldutils.manager.MessageManager;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import github.slkhater.shieldplotsquaredplus.inventories.TerrenosInventories;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InventoryClickEvent implements Listener{
	PlotAPI plotAPI = new PlotAPI();
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onInventoryClickEvent(org.bukkit.event.inventory.InventoryClickEvent e) throws InterruptedException {
		Player player = (Player) e.getWhoClicked();
		Inventory inventory = e.getInventory();
		String inventoryTitle = inventory.getTitle();
		if (e.getCurrentItem() == null) return;
		ItemStack clickedItem = e.getCurrentItem();
		Material clickedItemMaterial = clickedItem.getType();
		ItemMeta clickedItemMeta = clickedItem.getItemMeta();
		if(inventory.getType() != InventoryType.CHEST || inventoryTitle.equals(InventoryType.CHEST.getDefaultTitle())) return;
		if(clickedItemMaterial == Material.BARRIER && clickedItemMeta.hasDisplayName() && clickedItemMeta.getDisplayName().equalsIgnoreCase(ColorsAPI.colorize("&cFechar"))){
			player.playSound(player.getLocation(), Sound.CHEST_CLOSE, 0.7F, 1.0F);
			player.closeInventory();
			return;
		}
		if(inventoryTitle.equals(TerrenosInventories.semTerrenosTitle)) {
            e.setCancelled(true);
			if (clickedItemMaterial == Material.GRASS && clickedItemMeta.hasDisplayName() && clickedItemMeta.getDisplayName().equalsIgnoreCase(ColorsAPI.colorize("&eTerreno Randômico"))) {
				player.performCommand("plotsquared auto");
				player.playSound(player.getLocation(), Sound.NOTE_BASS_GUITAR, 0.7F, 1.0F);
				MessageManager.goodPlayer(player, "Este Terreno agora é de sua propriedade, parabéns pela sua aquisição, um custo de &6'175,000 purse' &ffoi-lhe cobrado.");
			}
			if (clickedItemMaterial == Material.DIRT && clickedItemMeta.hasDisplayName() && clickedItemMeta.getDisplayName().equalsIgnoreCase(ColorsAPI.colorize("&eTerreno Específico"))) {
				Plot standing = plotAPI.getPlot(player);
				PlotPlayer plotPlayer = PlotPlayer.wrap(player);
				if(standing != null) {
					if(!standing.hasOwner()) {
						if(standing.canClaim(plotPlayer) && standing.claim(plotPlayer, false, null)) {
							player.playSound(player.getLocation(), Sound.NOTE_BASS_GUITAR, 0.7F, 1.0F);
							MessageManager.goodPlayer(player, "Este Terreno agora é de sua propriedade, parabéns pela sua aquisição, um custo de &6'150,000 purse' &ffoi-lhe cobrado.");
						} else MessageManager.errorPlayer(player, "&fAlgum motivo está impossibilitando você de adquirir este Terreno!");
					} else MessageManager.infoPlayer(player, "&fEste Terreno já possui um proprietário!");
				} else MessageManager.infoPlayer(player, "&fVocê deve estar sobre um Terreno para o adquirir!");
			}
			if(clickedItemMaterial == Material.ARROW && clickedItemMeta.hasDisplayName() && clickedItemMeta.getDisplayName().equalsIgnoreCase(ColorsAPI.colorize("&cVoltar"))){
				player.playSound(player.getLocation(), Sound.ARROW_HIT, 0.7F, 1.0F);
				if(plotAPI.getPlayerPlotCount(Bukkit.getServer().getWorld("Terrenos"), player) > 0) InventoriesAPI.closeInventoryAndOpenNew(player, TerrenosInventories.comTerrenosInventory);
				else InventoriesAPI.closeInventoryAndOpenNew(player, TerrenosInventories.semTerrenosInventory);
			}
		}
		else if(inventoryTitle.equals(TerrenosInventories.comTerrenosTitle)){
            e.setCancelled(true);
			if(clickedItemMaterial == Material.GRASS && clickedItemMeta.hasDisplayName() && clickedItemMeta.getDisplayName().equalsIgnoreCase(ColorsAPI.colorize("&eAdquirir Terreno"))){
				InventoriesAPI.closeInventoryAndOpenNew(player, TerrenosInventories.semTerrenosInventory, Sound.NOTE_BASS, 0.7F);
			}
			if(clickedItemMaterial == Material.MAP && clickedItemMeta.hasDisplayName() && clickedItemMeta.getDisplayName().equalsIgnoreCase(ColorsAPI.colorize("&eGerir Terrenos"))){
				InventoriesAPI.closeInventoryAndOpenNew(player, TerrenosInventories.manageTerrenosInventory, Sound.NOTE_BASS, 0.7F);
				int i = 10;
				for(Plot plot : plotAPI.getPlayerPlots(Bukkit.getWorld("Terrenos"), player)){
					List<String> members = plot.getMembers().stream().map(Bukkit::getOfflinePlayer).map(OfflinePlayer::getName).collect(Collectors.toList());
					List<String> helpers = plot.getTrusted().stream().map(Bukkit::getOfflinePlayer).map(OfflinePlayer::getName).collect(Collectors.toList());
					InventoriesAPI.createItem(player.getOpenInventory().getTopInventory(), Material.GRASS, i, 1, "&eTerreno - &6'"+plot.getId().toString()+"&6'", "&7Apelido: &f"+plot.getAlias()+
							"\n&7Bioma: &f"+plot.getBiome()+
							"\n&7Ajudantes: &f'"+helpers+
							"'\n&7Membros: &f'"+members+
							"'\n&7Jogadores dentro: &f"+plot.getPlayersInPlot().size()+
							"\n&7Pontuação: &f✴"+PlotSquaredAPI.getPontuacaoPlot(plot)+
							"\n&8Clique para gerir o Terreno", 0);
					if(i < 16) i++;
				}
			}
		}
		else if(inventoryTitle.equals(TerrenosInventories.manageTerrenosTitle)){
            e.setCancelled(true);
			if(clickedItemMaterial == Material.GRASS && clickedItemMeta.hasDisplayName() && clickedItemMeta.getDisplayName().contains(ColorsAPI.colorize("&eTerreno - &6'"))){
				for(Plot plot : plotAPI.getPlayerPlots(player)){
					String plotID = ChatColor.stripColor(clickedItemMeta.getDisplayName().replace("Terreno - ", "").replaceAll("'", ""));
					if(plot.getId().toString().equals(plotID)){
						Inventory managingInventory = Bukkit.createInventory(null, 36, TerrenosInventories.managingTerrenosTitle + plotID);
						InventoriesAPI.createItem(managingInventory, Material.REDSTONE_COMPARATOR, 11, 1, ColorsAPI.colorize("&eConfigurações"), ColorsAPI.colorize("&8Clique para configurar o Terreno"), (short) 0);
						ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
						SkullMeta playerSkullMeta = (SkullMeta) playerSkull.getItemMeta();
						playerSkullMeta.setOwner(player.getName());
						playerSkullMeta.setDisplayName(ColorsAPI.colorize("&eGerir Membros"));
						List<String> lore = new ArrayList<>();
						lore.add(ColorsAPI.colorize("&8Clique para gerir os membros"));
						playerSkullMeta.setLore(lore);
						playerSkull.setItemMeta(playerSkullMeta);
						managingInventory.setItem(13, playerSkull);
						InventoriesAPI.createItem(managingInventory, Material.ENDER_PEARL, 15, 1, ColorsAPI.colorize("&eTeletransportar"), ColorsAPI.colorize("&8Clique para teletransportar até o Terreno"), (short) 0);
						InventoriesAPI.createGlowItem(managingInventory, Material.ARROW, 27, 1, ColorsAPI.colorize("&cVoltar"), "", (short) 0);
						InventoriesAPI.createGlowItem(managingInventory, Material.BARRIER, 31, 1, ColorsAPI.colorize("&cFechar"), "", (short) 0);
						InventoriesAPI.fillEmptySlots(managingInventory, Material.STAINED_GLASS_PANE, " ", "", (short) 8);
						InventoriesAPI.closeInventoryAndOpenNew(player, managingInventory, Sound.NOTE_BASS, 0.7F);
					}
				}
			}
			if(clickedItemMaterial == Material.ARROW && clickedItemMeta.hasDisplayName() && clickedItemMeta.getDisplayName().equalsIgnoreCase(ColorsAPI.colorize("&cVoltar"))){
				player.playSound(player.getLocation(), Sound.ARROW_HIT, 0.7F, 1.0F);
				InventoriesAPI.closeInventoryAndOpenNew(player, TerrenosInventories.comTerrenosInventory);
			}
		}
		else if(inventoryTitle.contains(TerrenosInventories.managingTerrenosTitle)){
            e.setCancelled(true);
			String plotID = ChatColor.stripColor(inventory.getTitle().replace(TerrenosInventories.managingTerrenosTitle, ""));
			if(clickedItemMaterial == Material.ENDER_PEARL && clickedItemMeta.hasDisplayName() && clickedItemMeta.getDisplayName().equalsIgnoreCase(ColorsAPI.colorize("&eTeletransportar"))){
				for(Plot plot : plotAPI.getPlayerPlots(player)){
					if(plot.getId().toString().equals(plotID)){
						player.playSound(player.getLocation(), Sound.NOTE_BASS, 0.7F, 1.0F);
						player.closeInventory();
						MessageManager.infoPlayer(player, "Teletransportando para o Terreno com &6ID(&l" + plotID + "&6) &fem &6&l5 &6segundos&f.");
						Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
							plot.teleportPlayer(PlotPlayer.wrap(player));
							player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 0.7F, 1.0F);
							MessageManager.goodPlayer(player, "Teletransportado para o Terreno com &6ID(&l" + plotID + "&6) &fcom sucesso.");
						}, 5 * 20L);
					}
				}
			}
			if(clickedItemMaterial == Material.REDSTONE_COMPARATOR && clickedItemMeta.hasDisplayName() && clickedItemMeta.getDisplayName().equalsIgnoreCase(ColorsAPI.colorize("&eConfigurações"))){
				Inventory settings2ndInventory = Bukkit.createInventory(null, 36, TerrenosInventories.settingsTerrenosTitle+plotID);
				settings2ndInventory.setContents(TerrenosInventories.settingsTerrenosInventory.getContents());
				InventoriesAPI.closeInventoryAndOpenNew(player, settings2ndInventory, Sound.NOTE_BASS, 0.7F);
			}
			if(clickedItemMaterial == Material.ARROW && clickedItemMeta.hasDisplayName() && clickedItemMeta.getDisplayName().equalsIgnoreCase(ColorsAPI.colorize("&cVoltar"))){
				player.playSound(player.getLocation(), Sound.ARROW_HIT, 0.7F, 1.0F);
				InventoriesAPI.closeInventoryAndOpenNew(player, TerrenosInventories.manageTerrenosInventory);
			}
		}
        else if(inventoryTitle.contains(TerrenosInventories.settingsTerrenosTitle)){
            e.setCancelled(true);
			String plotID = ChatColor.stripColor(inventory.getTitle().replace(TerrenosInventories.settingsTerrenosTitle, ""));
			if(clickedItemMaterial == Material.GOLD_INGOT && clickedItemMeta.hasDisplayName() && clickedItemMeta.getDisplayName().equalsIgnoreCase(ColorsAPI.colorize("&ePontuação"))){
				player.openInventory(TerrenosInventories.pointingTerrenosInventory);
				HashMap<String, Integer> pontuacoesTerrenos = new HashMap<>();
				HashMap<String, Integer> pontuacoesTerrenosSum = new HashMap<>();
				FileConfiguration storageFile = Main.getInstance().getStorage();
				int pontuacaoTotal = 0;
				for(String key : storageFile.getConfigurationSection("Terrenos").getKeys(false)){
					pontuacoesTerrenos.put(key, PlotSquaredAPI.getPontuacaoPlot(key));
					for(Plot plot : plotAPI.getPlayerPlots(player)) if(key.equals(plot.getId().toString())) pontuacaoTotal += PlotSquaredAPI.getPontuacaoPlot(key);
				}
				InventoriesAPI.createItem(player.getOpenInventory().getTopInventory(), Material.GOLD_NUGGET, 13, 1, "&ePontuação", "&7Pontuação deste Terreno: &f✴" + PlotSquaredAPI.getPontuacaoPlot(plotID) + "\n&7Pontuação dos seus Terrenos: &f✴"+pontuacaoTotal, 0);
				InventoriesAPI.createItem(player.getOpenInventory().getTopInventory(), Material.ARMOR_STAND, 15, 1, "&eTOP10 - Pontuação", "&b♚1º null: &f✴loading...\n&b 2º null: &f✴loading...\n&b 3º null: &f✴loading...\n&b 4º null: &f✴loading...\n&b 5º null: &f✴loading...\n&b 6º null: &f✴loading...\n&b 7º null: &f✴loading...\n&b 8º null: &f✴loading...\n&b 9º null: &f✴loading...\n&b 10º null: &f✴loading...", 0);
				for(String s : pontuacoesTerrenos.keySet()){
					String p = PlotSquaredAPI.getPlotOwner(s).getName();
					int i = PlotSquaredAPI.getPontuacaoPlot(s);
					pontuacoesTerrenosSum.merge(p, i, Integer::sum);
				}
				AtomicInteger i = new AtomicInteger();
				i.getAndIncrement();
				ItemStack amstandIS = player.getOpenInventory().getTopInventory().getItem(15);
				ItemMeta amstandMeta = amstandIS.getItemMeta();
				List<String> amlore = amstandMeta.getLore();
				amlore.clear();
				pontuacoesTerrenosSum.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(10).forEach(e1 -> {
					if(i.get() == 1 && e1.getValue()>0) amlore.add(ColorsAPI.colorize("&b♚" + i.get() + "º " + e1.getKey() + ": &f✴" + e1.getValue()));
					else if(e1.getValue()>0) amlore.add(ColorsAPI.colorize(" &b" + i.get() + "º " + e1.getKey() + ": &f✴" + e1.getValue()));
					i.getAndIncrement();
				});
				amstandMeta.setLore(amlore);
				amstandIS.setItemMeta(amstandMeta);
			}
            if(clickedItemMaterial == Material.ARROW && clickedItemMeta.hasDisplayName() && clickedItemMeta.getDisplayName().equalsIgnoreCase(ColorsAPI.colorize("&cVoltar"))) {
				player.playSound(player.getLocation(), Sound.ARROW_HIT, 0.7F, 1.0F);
				InventoriesAPI.closeInventoryAndOpenNew(player, TerrenosInventories.manageTerrenosInventory);
			}
        }
        else if(inventoryTitle.contains(TerrenosInventories.pointingTerrenosTitle)){
        	e.setCancelled(true);
			if(clickedItemMaterial == Material.ARROW && clickedItemMeta.hasDisplayName() && clickedItemMeta.getDisplayName().equalsIgnoreCase(ColorsAPI.colorize("&cVoltar"))){
				player.playSound(player.getLocation(), Sound.ARROW_HIT, 0.7F, 1.0F);
				InventoriesAPI.closeInventoryAndOpenNew(player, TerrenosInventories.manageTerrenosInventory);
			}
		}
	}

}