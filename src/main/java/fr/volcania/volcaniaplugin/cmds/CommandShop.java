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
        main.openShop(p);
        return false;
    }

}
