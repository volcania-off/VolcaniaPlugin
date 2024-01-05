package fr.volcania.volcaniaplugin;

import fr.volcania.volcaniaplugin.cmds.CommandCompass;
import fr.volcania.volcaniaplugin.cmds.CommandShop;
import fr.volcania.volcaniaplugin.events.EventsClass;
import fr.volcania.volcaniaplugin.events.ShopEventsClass;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

public class Main extends JavaPlugin{

    public ArrayList<UUID> spawnJump = new ArrayList<UUID>();
    @Override
    public void onEnable() {
        System.out.println("[VolcaniaPliugin] Volcania plugin enabled");
        saveDefaultConfig();

        getCommand("compass").setExecutor(new CommandCompass());
        getCommand("volcania").setExecutor(new CommandVolcania(this));
        getCommand("shop").setExecutor(new CommandShop(this));
        getServer().getPluginManager().registerEvents(new EventsClass(this), this);
        getServer().getPluginManager().registerEvents(new ShopEventsClass(this), this);
    }
}
