package fr.volcania.volcaniaplugin;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.volcania.volcaniaplugin.cmds.CommandCompass;
import fr.volcania.volcaniaplugin.cmds.CommandShop;
import fr.volcania.volcaniaplugin.cmds.CommandVolcania;
import fr.volcania.volcaniaplugin.events.EventsClass;
import fr.volcania.volcaniaplugin.events.ShopEventsClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin{

    public static Plugin getPlugin() {
        return (Plugin)getPlugin(Main.class);
    }

    public void openPlayerCompass(Player p){
        Inventory inv = Bukkit.createInventory(null, 27, getConfig().getString("compass.title").replace("&", "§"));
        ItemStack one = new ItemStack(Material.valueOf(getConfig().getString("compass.volcania.item_icon").replace
                ("Material.", "")));
        ItemMeta oneM = one.getItemMeta();
        oneM.setDisplayName(getConfig().getString("compass.volcania.item_name").replace("&", "§"));
        one.setItemMeta(oneM);
        inv.setItem(10, one);

        ItemStack two = new ItemStack(Material.valueOf(getConfig().getString("compass.minijeux.item_icon").replace
                ("Material.", "")));
        ItemMeta twoM = two.getItemMeta();
        twoM.setDisplayName(getConfig().getString("compass.minijeux.item_name").replace("&", "§"));
        two.setItemMeta(twoM);
        inv.setItem(12, two);

        ItemStack thr = new ItemStack(Material.valueOf(getConfig().getString("compass.minage.item_icon").replace
                ("Material.", "")));               ItemMeta thrM = thr.getItemMeta();
        thrM.setDisplayName(getConfig().getString("compass.minage.item_name").replace("&", "§"));
        thr.setItemMeta(thrM);
        inv.setItem(14, thr);

        ItemStack four = new ItemStack(Material.valueOf(getConfig().getString("compass.lobby.item_icon").replace
                ("Material.", "")));
        ItemMeta fourM = four.getItemMeta();
        fourM.setDisplayName(getConfig().getString("compass.lobby.item_name").replace("&", "§"));
        four.setItemMeta(fourM);
        inv.setItem(16, four);

        p.openInventory(inv);
    }

    public void givePlayerCompass(Player p){
        ItemStack Compass = new ItemStack(Material.COMPASS);
        ItemMeta meta = Compass.getItemMeta();
        meta.setDisplayName(getConfig().getString("compass.title").replace("&", "§"));
        Compass.setItemMeta(meta);

        if(!(p.getInventory().contains(Compass))){

            p.getInventory().setItem(8, Compass);
        }else{
            p.sendMessage(getConfig().getString("compass.already_have_error").replace("&", "§"));
        }
    }

    /*test*/

    public void openShop(Player p){
        Inventory inv = Bukkit.createInventory(null, 27, getConfig().getString("shop.inv_name")
                .replace("%player%", p.getDisplayName()).replace("&", "§"));

        for(int i = 1; i <= getConfig().getInt("shop.item_amount"); i++) {
            ItemStack item = new ItemStack(Material.valueOf(getConfig().getString("shop.item" + i)
                    .replace("Material.", "")));
            ItemMeta itemMeta = item.getItemMeta();
            ArrayList<String> lore = new ArrayList<String>();
            lore.add(getConfig().getString("shop.item" + i + "_price") + "$");
            itemMeta.setLore(lore);
            itemMeta.setDisplayName(getConfig().getString("shop.item" + i + "_name")
                    .replace("&", "§"));
            item.setItemMeta(itemMeta);

            inv.setItem(getConfig().getInt("shop.item" + i + "_slot"), item);
        }

        p.openInventory(inv);
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

        getCommand("compass").setExecutor(new CommandCompass(this));
        getCommand("volcania").setExecutor(new CommandVolcania(this));
        getCommand("shop").setExecutor(new CommandShop(this));
        getServer().getPluginManager().registerEvents(new EventsClass(this), this);
        getServer().getPluginManager().registerEvents(new ShopEventsClass(this), this);
    }
}
