package fr.volcania.volcaniaplugin.events;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import fr.volcania.volcaniaplugin.Main;
import net.ess3.api.MaxMoneyException;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ShopEventsClass implements Listener {

    public static Main main;
    public ShopEventsClass(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onInvClickEvent(InventoryClickEvent e) throws UserDoesNotExistException, MaxMoneyException, NoLoanPermittedException {

        Player p = (Player) e.getWhoClicked();
        ItemStack it = e.getCurrentItem();
        InventoryAction a = e.getAction();
        Inventory invv = e.getClickedInventory();

        if(p.getOpenInventory().getTitle().equalsIgnoreCase(main.getConfig().getString("shop.inv_name").replace
                ("%player%", p.getDisplayName()).replace("&", "§"))){
            e.setCancelled(true);

            for(int i = 1; i <= main.getConfig().getInt("shop.item_amount"); i++) {
                ItemStack item = new ItemStack(Material.valueOf(main.getConfig().getString("shop.item" + i).replace("Material.", "")));
                ItemMeta itemMeta = item.getItemMeta();
                ArrayList<String> lore = new ArrayList<String>();
                lore.add(main.getConfig().getString("shop.item" + i + "_price").replace("&", "§") + "$");
                itemMeta.setLore(lore);
                itemMeta.setDisplayName(main.getConfig().getString("shop.item" + i + "_name").replace("&", "§"));

                item.setItemMeta(itemMeta);


                if(it.equals(item)){
                    if(Economy.getMoney(p.getName()) >= main.getConfig().getInt("shop.item"+ i + "_price")){
                        ItemStack item2 = new ItemStack(Material.valueOf(main.getConfig().getString("shop.item" + i).replace("Material.", "")));
                        Economy.setMoney(p.getName(), Economy.getMoney(p.getName()) - main.getConfig().getInt("shop.item"+ i + "_price"));
                        p.getInventory().addItem(item2);
                    }else{
                        p.closeInventory();
                        p.sendMessage(main.getConfig().getString("shop.not_enouth_money_message").replace("&", "§").replace("%player%", p.getDisplayName()));
                    }
                }
            }

        }

    }
}
