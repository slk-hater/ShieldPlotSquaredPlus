package github.slkhater.shieldplotsquaredplus.inventories;

import github.slkhater.shieldutils.api.ColorsAPI;
import github.slkhater.shieldutils.api.InventoriesAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

public class TerrenosInventories {
    public static String semTerrenosTitle = ColorsAPI.colorize("Terrenos - Adquirir terreno");
    public static Inventory semTerrenosInventory = Bukkit.createInventory(null, 9*4, semTerrenosTitle);
    static {
        InventoriesAPI.createItem(semTerrenosInventory, Material.GRASS, 12, 1, "&eTerreno Randômico",
                " &7Adquire um terreno randômico\n" +
                        "&7e estilize ele ao seu gosto.\n " +
                        "&7Custo: &6175,000 purse\n" +
                        "&8Clique para comprar um Terreno", 0);
        InventoriesAPI.createItem(semTerrenosInventory, Material.DIRT, 14, 1, "&eTerreno Específico",
                " &7Adquire o terreno que você está\n" +
                        "&7sobre e estilize ele ao seu gosto.\n \n" +
                        "&7Custo: &6150,000 purse\n" +
                        "&8Clique para comprar um Terreno",  0);
        InventoriesAPI.createGlowItem(semTerrenosInventory, Material.ARROW, 27, 1, "&cVoltar", "&8Clique para voltar atrás", 0);
        InventoriesAPI.createGlowItem(semTerrenosInventory, Material.BARRIER, 31, 1, "&cFechar", "&8Clique para fechar o menu", 0);
        InventoriesAPI.fillEmptySlots(semTerrenosInventory, Material.STAINED_GLASS_PANE, " ", "", 8);
    }
    public static String comTerrenosTitle = ColorsAPI.colorize("Terrenos - Principal");
    public static Inventory comTerrenosInventory = Bukkit.createInventory(null, 9*4, comTerrenosTitle);
    static {
        InventoriesAPI.createItem(comTerrenosInventory, Material.GRASS, 12, 1, "&eAdquirir Terreno",
                " &7Adquire um terreno\n" +
                        "&7e estilize ele ao seu gosto.\n" +
                        "&8Clique para comprar um Terreno", 0);
        InventoriesAPI.createItem(comTerrenosInventory, Material.MAP, 14, 1, "&eGerir Terrenos", "&8Clique para gerir seus Terrenos", 0);
        InventoriesAPI.createGlowItem(comTerrenosInventory, Material.BARRIER, 31, 1, "&cFechar", "&8Clique para fechar o menu", 0);
        InventoriesAPI.fillEmptySlots(comTerrenosInventory, Material.STAINED_GLASS_PANE, " ", "", 8);
    }
    public static String manageTerrenosTitle = ColorsAPI.colorize("Terrenos - Gerir terrenos");
    public static Inventory manageTerrenosInventory = Bukkit.createInventory(null, 9*3, manageTerrenosTitle);
    static {
        InventoriesAPI.fillBorderSlots(manageTerrenosInventory, Material.STAINED_GLASS_PANE, " ", "", 8);
        InventoriesAPI.createGlowItem(manageTerrenosInventory, Material.ARROW, 18, 1, "&cVoltar", "&8Clique para voltar atrás", 0);
        InventoriesAPI.createGlowItem(manageTerrenosInventory, Material.BARRIER, 22, 1, "&cFechar", "&8Clique para fechar o menu", 0);
    }
    public static String managingTerrenosTitle = ColorsAPI.colorize("Terrenos - Gerir T. ");
    public static Inventory managingTerrenosInventory = Bukkit.createInventory(null, 9*4, managingTerrenosTitle);
    static {
        InventoriesAPI.createGlowItem(managingTerrenosInventory, Material.ARROW, 27, 1, "&cVoltar", "&8Clique para voltar atrás", 0);
        InventoriesAPI.createGlowItem(managingTerrenosInventory, Material.BARRIER, 31, 1, "&cFechar", "&8Clique para fechar o menu", 0);
        InventoriesAPI.fillEmptySlots(managingTerrenosInventory, Material.STAINED_GLASS_PANE, " ", "", 8);
    }
    public static String settingsTerrenosTitle = ColorsAPI.colorize("Terrenos - Configurar T. ");
    public static Inventory settingsTerrenosInventory = Bukkit.createInventory(null, 9*4, settingsTerrenosTitle);
    static {
        InventoriesAPI.createItem(settingsTerrenosInventory, Material.SAPLING, 11, 1, "&eAlterar bioma",
                "&8Clique para alterar o bioma do Terreno", 0);
        InventoriesAPI.createItem(settingsTerrenosInventory, Material.GOLD_INGOT, 13, 1, "&ePontuação",
                "&8Clique para ver a sua pontuação total", 0);
        InventoriesAPI.createItem(settingsTerrenosInventory, Material.GOLD_SPADE, 15, 1, "&eAlterar permissões",
                "&8Clique para alterar as permissões do Terreno", 0);
        InventoriesAPI.fillEmptySlots(settingsTerrenosInventory, Material.STAINED_GLASS_PANE, " ", "", 8);
        InventoriesAPI.createGlowItem(settingsTerrenosInventory, Material.ARROW, 27, 1, "&cVoltar", "&8Clique para voltar atrás", 0);
        InventoriesAPI.createGlowItem(settingsTerrenosInventory, Material.BARRIER, 31, 1, "&cFechar", "&8Clique para fechar o menu", 0);
    }
    public static String pointingTerrenosTitle = ColorsAPI.colorize("Terrenos - Pontuação dos seus T.");
    public static Inventory pointingTerrenosInventory = Bukkit.createInventory(null, 9*4, pointingTerrenosTitle);
    static  {
        InventoriesAPI.createItem(pointingTerrenosInventory, Material.BARRIER, 11, 1, "&e-*-", "", 0);
        InventoriesAPI.createItem(pointingTerrenosInventory, Material.GOLD_NUGGET, 13, 1, "&ePontuação",
                "&7Pontuação deste Terreno: &8loading...\n" +
                        "&7Pontuação dos seus Terrenos: &8loading...&f", 0);
        InventoriesAPI.createItem(pointingTerrenosInventory, Material.ARMOR_STAND, 15, 1, "&eTOP10 - Pontuação","&b1º null: &floading...\n&b2º null: &floading...\n&b3º null: &floading...\n&b4º null: &floading...\n&b5º null: &floading...\n&b6º null: &floading...\n&b7º null: &floading...\n&b8º null: &floading...\n&b9º null: &floading...\n&b10º null: &floading...", 0);
        InventoriesAPI.fillEmptySlots(pointingTerrenosInventory, Material.STAINED_GLASS_PANE, " ", "", 8);
        InventoriesAPI.createGlowItem(pointingTerrenosInventory, Material.ARROW, 27, 1, "&cVoltar", "&8Clique para voltar atrás", 0);
        InventoriesAPI.createGlowItem(pointingTerrenosInventory, Material.BARRIER, 31, 1, "&cFechar", "&8Clique para fechar o menu", 0);
    }
    public static String manageMembrosTitle = ColorsAPI.colorize("Terrenos - Gerir M. ");
    public static Inventory manageMembrosInventory = Bukkit.createInventory(null, 9*6, manageMembrosTitle);
    static {
        InventoriesAPI.fillBorderSlots(manageMembrosInventory, Material.STAINED_GLASS_PANE, " ", "", 8);
        InventoriesAPI.createGlowItem(manageMembrosInventory, Material.ARROW, 46, 1, "&cVoltar", "&8Clique para voltar atrás", 0);
        InventoriesAPI.createGlowItem(manageMembrosInventory, Material.BARRIER, 50, 1, "&cFechar", "&8Clique para fechar o menu", 0);
    }
}
