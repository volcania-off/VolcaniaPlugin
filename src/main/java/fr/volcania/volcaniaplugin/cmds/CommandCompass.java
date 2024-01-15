package fr.volcania.volcaniaplugin.cmds;

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
    @Override
    public boolean onCommand(CommandSender sender,Command cmd,String label,String[] args) {
        if(args.length == 0){
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
        }else if (args.length == 1){
            if(args[0].equalsIgnoreCase("open")){
                Player p = (Player)sender;
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


        return false;
    }
}
