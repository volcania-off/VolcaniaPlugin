package fr.volcania.volcaniaplugin.cmds;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class CommandCompass implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender,Command cmd,String label,String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("[VolcaniaPlugin] Vous devez être un joueur pour executyer cette commande");
        }else{
            Player p = (Player)sender;
            ItemStack Compass = new ItemStack(Material.COMPASS);
            ItemMeta meta = Compass.getItemMeta();
            meta.setDisplayName(ChatColor.BLUE + "Teleporteur");
            Compass.setItemMeta(meta);

            if(!(p.getInventory().contains(Compass))){

                p.getInventory().setItem(8, Compass);
            }else{
                p.sendMessage(ChatColor.RED + "[VolcaniaPlugin] Vous avez deja un téléporteur dans votre inventaire !");
            }

        }
        return false;
    }
}
