package fr.volcania.volcaniaplugin.cmds;

import fr.volcania.volcaniaplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class CommandCompass implements CommandExecutor {

    public Main main;
    public CommandCompass(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender,Command cmd,String label,String[] args) {
        if(args.length == 0){
            if(!(sender instanceof Player)) {
                sender.sendMessage("[VolcaniaPlugin] Vous devez être un joueur pour executer cette commande");
            }else{
                main.givePlayerCompass((Player) sender);

            }
        }else if (args.length == 1){
            if(args[0].equalsIgnoreCase("open")){
                main.openPlayerCompass((Player) sender);
            }
        }


        return false;
    }
}
