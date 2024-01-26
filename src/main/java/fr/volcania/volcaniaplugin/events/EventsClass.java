package fr.volcania.volcaniaplugin.events;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import fr.volcania.volcaniaplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EventsClass implements Listener {

    private static Main main;

    public EventsClass(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

        p.sendTitle(main.getConfig().getString("join.jointitle").replace("&", "§").
                        replace("%player%", p.getDisplayName()), main.getConfig().getString("join.joinsubtitle")
                .replace("&", "§").replace("%player%", p.getDisplayName()),
                main.getConfig().getInt("join.join_enter_fade"), main.getConfig().getInt("join.join_time"),
                main.getConfig().getInt("join.join_quit_fade"));

        if(!p.getInventory().getItem(8).getItemMeta().getDisplayName().equalsIgnoreCase(main.getConfig().
                getString("compass.title").replace("&", "§"))){
            main.givePlayerCompass(p);
        }


        if(p.hasPermission("group.dev") || p.hasPermission("group.fonda") ||
                p.hasPermission("group.cofonda") || p.hasPermission("group.admin")){
            e.setJoinMessage(main.getConfig().getString("join.staffjoinmessage").
                    replace("%player%", p.getDisplayName()) .replace("&", "§"));

        }else{
            e.setJoinMessage(null);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if(p.hasPermission("group.dev") || p.hasPermission("group.fonda") ||
                p.hasPermission("group.cofonda") || p.hasPermission("group.admin")){
            e.setQuitMessage(main.getConfig().getString("join.staffquitmessage").
                    replace("%player%", p.getDisplayName()).replace("&", "§"));

        }else{
            e.setQuitMessage(null);
        }
    }

    @EventHandler
    public static void onClick(PlayerInteractEvent e){
        Action a = e.getAction();
        Player p = e.getPlayer();
        ItemStack i = e.getItem();

        ItemStack Compass = new ItemStack(Material.COMPASS);
        ItemMeta meta = Compass.getItemMeta();
        meta.setDisplayName(main.getConfig().getString("compass.title").replace("&", "§"));
        Compass.setItemMeta(meta);

        if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK){
            if(i == null) return;
            if (i.equals(Compass)){
                main.openPlayerCompass(p);
            }
        }
    }
    @EventHandler
    public static void onInvClickEvent(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        Inventory inv = e.getClickedInventory();
        ItemStack it = e.getCurrentItem();

        ItemStack Compass = new ItemStack(Material.COMPASS);
        ItemMeta meta = Compass.getItemMeta();
        meta.setDisplayName(main.getConfig().getString("compass.title").replace("&", "§"));
        Compass.setItemMeta(meta);

        ItemStack one = new ItemStack(Material.valueOf(main.getConfig().getString("compass.volcania.item_icon").replace
                ("Material.", "")));
        ItemMeta oneM = one.getItemMeta();
        oneM.setDisplayName(main.getConfig().getString("compass.volcania.item_name").replace("&", "§"));
        one.setItemMeta(oneM);
        inv.setItem(10, one);

        ItemStack two = new ItemStack(Material.valueOf(main.getConfig().getString("compass.minijeux.item_icon").replace
                ("Material.", "")));
        ItemMeta twoM = two.getItemMeta();
        twoM.setDisplayName(main.getConfig().getString("compass.minijeux.item_name").replace("&", "§"));
        two.setItemMeta(twoM);
        inv.setItem(12, two);

        ItemStack thr = new ItemStack(Material.valueOf(main.getConfig().getString("compass.minage.item_icon").replace
                ("Material.", "")));
        ItemMeta thrM = thr.getItemMeta();
        thrM.setDisplayName(main.getConfig().getString("compass.minage.item_name").replace("&", "§"));
        thr.setItemMeta(thrM);
        inv.setItem(14, thr);

        ItemStack four = new ItemStack(Material.valueOf(main.getConfig().getString("compass.lobby.item_icon").replace
                ("Material.", "")));
        ItemMeta fourM = four.getItemMeta();
        fourM.setDisplayName(main.getConfig().getString("compass.lobby.item_name").replace("&", "§"));
        four.setItemMeta(fourM);
        inv.setItem(16, four);

        if(inv.contains(one)){
            e.setCancelled(true);
            if(!(it == null)){
                if(it.equals(one)){
                    Main.sendPlayer(p, main.getConfig().getString("compass.volcania.server_name"));
                }
                if(it.equals(two)){
                    Main.sendPlayer(p, main.getConfig().getString("compass.minijeux.server_name"));
                }
                if(it.equals(thr)){
                    Main.sendPlayer(p, main.getConfig().getString("compass.minage.server_name"));
                }
                if(it.equals(four)){
                    Main.sendPlayer(p, main.getConfig().getString("compass.lobby.server_name"));
                }
            }

        }
        if(inv.contains(Compass)) {
            if (it.equals(Compass)) {
                e.setCancelled(true);
            } else {
                e.setCancelled(false);
            }
        }
    }

    @EventHandler
    public static void onDrop(PlayerDropItemEvent e){
        ItemStack Compass = new ItemStack(Material.COMPASS);
        ItemMeta meta = Compass.getItemMeta();
        meta.setDisplayName(main.getConfig().getString("compass.title").replace("&", "§"));
        Compass.setItemMeta(meta);

        ItemStack item = e.getItemDrop().getItemStack();
        Player p = e.getPlayer();

        if(item.equals(Compass)){
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        ItemStack Compass = new ItemStack(Material.COMPASS);
        ItemMeta meta = Compass.getItemMeta();
        meta.setDisplayName(main.getConfig().getString("compass.title").replace("&", "§"));
        Compass.setItemMeta(meta);

        e.getDrops().remove(Compass);

    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        Player p = (Player)e.getPlayer();
        main.givePlayerCompass(p);
    }

    @EventHandler
    public static void onChat(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        String chat = e.getMessage();
        if(chat.equalsIgnoreCase("/shop")){
            if(p.getWorld().getName().equalsIgnoreCase("jump") ||
                    p.getWorld().getName().equalsIgnoreCase("jumplobby") ||
                    p.getWorld().getName().equalsIgnoreCase("lobby")){
                e.setCancelled(true);
                p.sendMessage("§c[VolcaShop] Shop accessible que sur le Volcania ou le Minage.");
            }else{
                e.setCancelled(false);
            }
        }
    }
}
