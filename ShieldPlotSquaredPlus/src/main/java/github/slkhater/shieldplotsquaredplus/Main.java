package github.slkhater.shieldplotsquaredplus;

import github.slkhater.shieldplotsquaredplus.commands.*;
import github.slkhater.shieldplotsquaredplus.listeners.*;
import github.slkhater.shieldutils.api.ColorsAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public final class Main extends JavaPlugin {
    private static Main instance;
    public static Main getInstance() {
        return instance;
    }
    private void setInstance(Main instance) {
        Main.instance = instance;
    }
    public ArrayList<UUID> voarDamage = new ArrayList<UUID>();
    public void onEnable() {
        setInstance(this);
        createStorageFile();
        if(getStorage().getConfigurationSection("Terrenos")==null) getStorage().createSection("Terrenos");
        System.out.println(ColorsAPI.colorize("[ShieldPlotSquaredPlus] &dVersion: &7"+getDescription().getVersion()+" &dby &7"+getDescription().getAuthors()));
        System.out.println(ColorsAPI.colorize("[ShieldPlotSquaredPlus] &5Initializing module loading..."));
        registerEvents();
        System.out.println(ColorsAPI.colorize("[ShieldPlotSquaredPlus] &dModules loaded successfully!"));
        System.out.println(ColorsAPI.colorize("[ShieldPlotSquaredPlus] &5Initializing command loading..."));
        registerCommands();
        System.out.println(ColorsAPI.colorize("[ShieldPlotSquaredPlus] &dCommands loaded successfully!"));
        System.out.println(ColorsAPI.colorize("[ShieldPlotSquaredPlus] &5Searching for requested dependencies..."));
        HookTest("PlotSquared");
        HookTest("ShieldUtils");
        HookTest("Vault");
        System.out.println(ColorsAPI.colorize("[ShieldPlotSquaredPlus] &dHooked into all &5dependencies requested successfully!"));
    }
    public void onDisable(){
        try {
            getStorage().save(storageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void registerEvents(){
        getServer().getPluginManager().registerEvents(new EntityDamageEvent(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerClaimPlotEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerCommandPreprocessEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeavePlotEvent(), this);
        getServer().getPluginManager().registerEvents(new PlotClearEvent(), this);
        getServer().getPluginManager().registerEvents(new PlotDeleteEvent(), this);
    }
    public void registerCommands(){
        getCommand("terrenos").setExecutor(new TerrenosCommand());
        getCommand("voar").setExecutor(new VoarCommand());
    }
    private File storageFile;
    private FileConfiguration storage;
    public FileConfiguration getStorage() { return this.storage; }
    private void createStorageFile() {
        storageFile = new File(getDataFolder(), "storage.yml");
        if (!storageFile.exists()) {
            storageFile.getParentFile().mkdirs();
            saveResource("storage.yml", false);
        }
        storage= new YamlConfiguration();
        try {
            storage.load(storageFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
    public void saveStorageFile(){
        try {
            getStorage().save(storageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void HookTest(String s){
        if(getServer().getPluginManager().getPlugin(s) != null) System.out.println(ColorsAPI.colorize("[ShieldPlotSquaredPlus] &5'" + s + "' &dhas been found, successfully hooked into."));
        else{
            System.out.println(ColorsAPI.colorize("[ShieldPlotSquaredPlus] &cCouldn't found &4'" + s + "'&c, hook has failed and the Plugin has been disabled!"));
            getServer().getPluginManager().disablePlugin(this);
        }
    }
}
