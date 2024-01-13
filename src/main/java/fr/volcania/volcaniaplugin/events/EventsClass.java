package fr.volcania.volcaniaplugin.events;

import fr.volcania.volcaniaplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
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

    private Main main;

    public EventsClass(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Bukkit.dispatchCommand(console, "mv tp " + p.getName() + " world");


        p.sendTitle(main.getConfig().getString("join.jointitle").replace("&", "§").
                        replace("%player%", p.getDisplayName()), main.getConfig().getString("join.joinsubtitle")
                .replace("&", "§").replace("%player%", p.getDisplayName()),
                main.getConfig().getInt("join.join_enter_fade"), main.getConfig().getInt("join.join_time"),
                main.getConfig().getInt("join.join_quit_fade"));
        if(e.getPlayer().getWorld().getName().equalsIgnoreCase("world")){

            ItemStack Compass = new ItemStack(Material.COMPASS);
            ItemMeta meta = Compass.getItemMeta();
            meta.setDisplayName(ChatColor.BLUE + "Teleporteur");
            Compass.setItemMeta(meta);

            if(!(e.getPlayer().getInventory().contains(Compass))){
                e.getPlayer().getInventory().setItem(8, Compass);
            }
        }
        if(p.hasPermission("group.developpeur") || p.hasPermission("group.fondateur") ||
                p.hasPermission("group.cofondateur") || p.hasPermission("group.admin")){
            e.setJoinMessage(main.getConfig().getString("join.staffjoinmessage").
                    replace("%player%", p.getDisplayName()) .replace("&", "§"));

        }else{
            e.setJoinMessage(null);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if(p.hasPermission("group.developpeur") || p.hasPermission("group.fondateur") ||
                p.hasPermission("group.cofondateur") || p.hasPermission("group.admin")){
            e.setQuitMessage(main.getConfig().getString("join.staffquitmessage").
                    replace("%player%", p.getDisplayName()) .replace("&", "§"));

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
        meta.setDisplayName(ChatColor.BLUE + "Teleporteur");
        Compass.setItemMeta(meta);

        if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK){
            if(i == null) return;
            if (i.equals(Compass)){
                Inventory inv = Bukkit.createInventory(null, 27, "Téléporteur");
                ItemStack one = new ItemStack(Material.FIRE_CHARGE);
                ItemMeta oneM = one.getItemMeta();
                oneM.setDisplayName(ChatColor.RED + "Volcania");
                one.setItemMeta(oneM);
                inv.setItem(10, one);

                ItemStack two = new ItemStack(Material.SLIME_BLOCK);
                ItemMeta twoM = two.getItemMeta();
                twoM.setDisplayName(ChatColor.RED + "Mini Jeux");
                two.setItemMeta(twoM);
                inv.setItem(12, two);

                ItemStack thr = new ItemStack(Material.DIAMOND_PICKAXE);
                ItemMeta thrM = thr.getItemMeta();
                thrM.setDisplayName(ChatColor.RED + "Minage");
                thr.setItemMeta(thrM);
                inv.setItem(14, thr);

                ItemStack four = new ItemStack(Material.BLACK_BED);
                ItemMeta fourM = four.getItemMeta();
                fourM.setDisplayName(ChatColor.RED + "Spawn");
                four.setItemMeta(fourM);
                inv.setItem(16, four);

                p.openInventory(inv);
            }
        }
    }
    @EventHandler
    public static void onInvClickEvent(InventoryClickEvent e){
        ItemStack Compass = new ItemStack(Material.COMPASS);
        ItemMeta meta = Compass.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + "Teleporteur");
        Compass.setItemMeta(meta);

        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

        Player p = (Player) e.getWhoClicked();
        ItemStack it = e.getCurrentItem();
        InventoryAction a = e.getAction();
        Inventory invv = e.getClickedInventory();

        ItemStack one = new ItemStack(Material.FIRE_CHARGE);
        ItemMeta oneM = one.getItemMeta();
        oneM.setDisplayName(ChatColor.RED + "Volcania");
        one.setItemMeta(oneM);

        ItemStack two = new ItemStack(Material.SLIME_BLOCK);
        ItemMeta twoM = two.getItemMeta();
        twoM.setDisplayName(ChatColor.RED + "Mini Jeux");
        two.setItemMeta(twoM);

        ItemStack thr = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta thrM = thr.getItemMeta();
        thrM.setDisplayName(ChatColor.RED + "Minage");
        thr.setItemMeta(thrM);

        ItemStack four = new ItemStack(Material.BLACK_BED);
        ItemMeta fourM = four.getItemMeta();
        fourM.setDisplayName(ChatColor.RED + "Spawn");
        four.setItemMeta(fourM);

        if(invv.contains(one)){
            e.setCancelled(true);
            if(!(it == null)){
                if(it.equals(one)){
                    Main.sendPlayer(p, "volcania");
                }
                if(it.equals(two)){
                    Main.sendPlayer(p, "jump");
                }
                if(it.equals(thr)){
                    Main.sendPlayer(p, "minage");
                }
                if(it.equals(four)){
                    Main.sendPlayer(p, "lobby");
                }
            }

        }
        if(invv.contains(Compass)){
            if(it.equals(Compass)){
                e.setCancelled(true);
            }else{
                e.setCancelled(false);
            }
        }


    }

    @EventHandler
    public static void onDrop(PlayerDropItemEvent e){
        ItemStack Compass = new ItemStack(Material.COMPASS);
        ItemMeta meta = Compass.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + "Teleporteur");
        Compass.setItemMeta(meta);

        ItemStack item = e.getItemDrop().getItemStack();
        Player p = e.getPlayer();

        if(item.equals(Compass)){
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Bukkit.dispatchCommand(console, "mv tp " + e.getEntity().getName() + " volcania");
        ItemStack Compass = new ItemStack(Material.COMPASS);
        ItemMeta meta = Compass.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + "Teleporteur");
        Compass.setItemMeta(meta);

        e.getDrops().remove(Compass);

    }

    @EventHandler
    public static void onChat(PlayerCommandPreprocessEvent e){
        Player p = e.getPlayer();
        String chat = e.getMessage();

        if(chat.equalsIgnoreCase("/rtp") && p.getWorld().getName().equalsIgnoreCase("volcania")){
            e.setCancelled(true);
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            Bukkit.dispatchCommand(console, "mv tp " + p.getName() + " volcaniaplay");
            Bukkit.dispatchCommand(console, "rtp player " + p.getName());

        }
        if(chat.equalsIgnoreCase("/spawn") && p.getWorld().getName().equalsIgnoreCase("volcaniaplay")){
            e.setCancelled(true);
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            Bukkit.dispatchCommand(console, "mv tp " + p.getName() + " volcania");
        }
    }

}
