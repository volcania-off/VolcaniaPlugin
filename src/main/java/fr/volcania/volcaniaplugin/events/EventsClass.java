package fr.volcania.volcaniaplugin.events;

import com.onarandombox.MultiverseCore.MVWorld;
import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.WorldProperties;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import com.onarandombox.MultiverseCore.listeners.MVEntityListener;
import com.onarandombox.MultiverseCore.utils.PlayerFinder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EventsClass implements Listener {

    @EventHandler
    public static void onJoin(PlayerJoinEvent e){
        if(e.getPlayer().getWorld().getName().equalsIgnoreCase("world")){
            if(!(e.getPlayer().getInventory().getItem(4) == Compass)){
                ItemStack Compass = new ItemStack(Material.COMPASS);
                ItemMeta meta = Compass.getItemMeta();
                meta.setDisplayName(ChatColor.BLUE + "Teleporteur");
                Compass.setItemMeta(meta);
                e.getPlayer().getInventory().addItem(Compass);
            }
        }
    }

    @EventHandler
    public static void onClick(PlayerInteractEvent e){
        Action a = e.getAction();
        Player p = e.getPlayer();
        ItemStack i = e.getItem();

        if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK){
            ItemStack Compass = new ItemStack(Material.COMPASS);
            ItemMeta meta = Compass.getItemMeta();
            meta.setDisplayName(ChatColor.BLUE + "Teleporteur");
            Compass.setItemMeta(meta);
            if (i == Compass){
                Inventory inv = Bukkit.createInventory(null, 27, ChatColor.RED + "Téléporteur");
                ItemStack one = new ItemStack(Material.DIAMOND_SWORD);
                ItemMeta oneM = one.getItemMeta();
                oneM.setDisplayName(ChatColor.RED + "test1");
                one.setItemMeta(oneM);
                inv.setItem(11, one);


                p.openInventory(inv);
            }
        }
    }

    @EventHandler
    public static void onInvClickEvent(InventoryClickEvent e){
        Inventory inv = e.getInventory();
    }
}
