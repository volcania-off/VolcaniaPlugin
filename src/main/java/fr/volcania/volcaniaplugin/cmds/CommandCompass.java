package fr.volcania.volcaniaplugin.cmds;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class CommandCompass implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("[VolcaniaPlugin] Vous devez être un joueur pour executyer cette commande");
        }else{
            Player p = (Player)sender;
            ItemStack Compass = new ItemStack(Material.COMPASS);
            ItemMeta meta = Compass.getItemMeta();
            meta.setDisplayName(ChatColor.BLUE + "Teleporteur");
            Compass.setItemMeta(meta);

            p.getInventory().addItem(Compass);
        }
        return false;
    }
}
