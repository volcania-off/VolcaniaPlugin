package fr.volcania.volcaniaplugin.cmds;

import fr.volcania.volcaniaplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CommandShop implements CommandExecutor {
    private Main main;
    public CommandShop(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("§c[Volcania] Vous devez être un joueur !");
        }
        Player p = (Player)sender;
        if(!(p.getWorld().getName().equalsIgnoreCase("world") || p.getWorld().getName().equalsIgnoreCase("jump"))){
            Inventory inv = Bukkit.createInventory(null, 27, main.getConfig().getString("shop.inv_name").replace("%player%", p.getDisplayName()).replace("&", "§"));
            for(int i = 1; i <= main.getConfig().getInt("shop.item_amount"); i++) {
                ItemStack item = new ItemStack(Material.valueOf(main.getConfig().getString("shop.item" + i).replace("Material.", "")));
                ItemMeta itemMeta = item.getItemMeta();
                ArrayList<String> lore = new ArrayList<String>();
                lore.add(main.getConfig().getString("shop.item" + i + "_price") + "$");
                itemMeta.setLore(lore);
                itemMeta.setDisplayName(main.getConfig().getString("shop.item" + i + "_name").replace("&", "§"));
                item.setItemMeta(itemMeta);

                inv.setItem(main.getConfig().getInt("shop.item" + i + "_slot"), item);
            }
            p.openInventory(inv);
        }else{
            p.sendMessage("§c[Volcania] Vous devez être dans le Volcania ou dans le Minage afin d'utiliser le shop.");
        }
        return false;
    }

}
