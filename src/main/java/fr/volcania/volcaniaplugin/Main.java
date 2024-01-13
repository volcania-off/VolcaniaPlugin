package fr.volcania.volcaniaplugin;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.volcania.volcaniaplugin.cmds.CommandCompass;
import fr.volcania.volcaniaplugin.cmds.CommandShop;
import fr.volcania.volcaniaplugin.events.EventsClass;
import fr.volcania.volcaniaplugin.events.ShopEventsClass;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

public class Main extends JavaPlugin{

    public static Plugin getPlugin() {
        return (Plugin)getPlugin(Main.class);
    }

    public static void sendPlayer(Player player, String serverInString) {
        ByteArrayDataOutput dataOutput = ByteStreams.newDataOutput();
        dataOutput.writeUTF("Connect");
        dataOutput.writeUTF(serverInString);

        player.sendPluginMessage(getPlugin(), "BungeeCord", dataOutput.toByteArray());
    }

    @Override
    public void onEnable() {
        System.out.println("[VolcaniaPliugin] Volcania plugin enabled");
        saveDefaultConfig();

        getServer().getMessenger().registerOutgoingPluginChannel((Plugin)this, "BungeeCord");

        getCommand("compass").setExecutor(new CommandCompass());
        getCommand("volcania").setExecutor(new CommandVolcania(this));
        getCommand("shop").setExecutor(new CommandShop(this));
        getServer().getPluginManager().registerEvents(new EventsClass(this), this);
        getServer().getPluginManager().registerEvents(new ShopEventsClass(this), this);
    }
}
