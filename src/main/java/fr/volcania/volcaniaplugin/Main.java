package fr.volcania.volcaniaplugin;

import fr.volcania.volcaniaplugin.cmds.CommandCompass;
import fr.volcania.volcaniaplugin.events.EventsClass;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
    @Override
    public void onEnable() {
        System.out.println("[VolcaniaPliugin] Volcania plugin enabled");
        getCommand("compass").setExecutor(new CommandCompass());
        getServer().getPluginManager().registerEvents(new EventsClass(), this);
    }
}
