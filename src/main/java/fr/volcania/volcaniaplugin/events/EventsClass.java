package fr.volcania.volcaniaplugin.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EventsClass implements Listener {

    @EventHandler
    public static void onJoin(PlayerJoinEvent e){
        if(e.getPlayer().getWorld().getName().equalsIgnoreCase("world")){

            ItemStack Compass = new ItemStack(Material.COMPASS);
            ItemMeta meta = Compass.getItemMeta();
            meta.setDisplayName(ChatColor.BLUE + "Teleporteur");
            Compass.setItemMeta(meta);

            if(!(e.getPlayer().getInventory().contains(Compass))){
                e.getPlayer().getInventory().setItem(8, Compass);
            }
        }
    }

    @EventHandler
    public static void onClick(PlayerInteractEvent e){
        Action a = e.getAction();
        Player p = e.getPlayer();
        Material i = p.getInventory().getItemInMainHand().getType();

        if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK){
            if (i == Material.COMPASS){
                Inventory inv = Bukkit.createInventory(null, 27, "Téléporteur");
                ItemStack one = new ItemStack(Material.FIRE_CHARGE);
                ItemMeta oneM = one.getItemMeta();
                oneM.setDisplayName(ChatColor.RED + "Volcania");
                one.setItemMeta(oneM);
                inv.setItem(10, one);

                ItemStack two = new ItemStack(Material.SLIME_BLOCK);
                ItemMeta twoM = two.getItemMeta();
                twoM.setDisplayName(ChatColor.RED + "Jump");
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
        twoM.setDisplayName(ChatColor.RED + "Jump");
        two.setItemMeta(twoM);

        ItemStack thr = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta thrM = thr.getItemMeta();
        thrM.setDisplayName(ChatColor.RED + "Minage");
        thr.setItemMeta(thrM);

        ItemStack four = new ItemStack(Material.BLACK_BED);
        ItemMeta fourM = four.getItemMeta();
        fourM.setDisplayName(ChatColor.RED + "Spawn");
        four.setItemMeta(fourM);

        if(invv.contains(one) && invv.contains(two) && invv.contains(thr) && invv.contains(four)){
            e.setCancelled(true);
            if(it.equals(one)){
                Bukkit.dispatchCommand(console, "mv tp " + p.getDisplayName() + " volcania");
            }
            if(it.equals(two)){
                Bukkit.dispatchCommand(console, "mv tp " + p.getDisplayName() + " jump");
            }
            if(it.equals(thr)){
                Bukkit.dispatchCommand(console, "mv tp " + p.getDisplayName() + " minage");
            }
            if(it.equals(four)){
                Bukkit.dispatchCommand(console, "mv tp " + p.getDisplayName() + " world");
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


}
