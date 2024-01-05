package fr.volcania.volcaniaplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandVolcania implements CommandExecutor {
    private static Main main;
    public CommandVolcania(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length == 0){
            if(!(sender instanceof Player)){
                sender.sendMessage("Vous devez être un joueur afin d'executer cette commande.");
            }else{
                Player p = (Player)sender;
                p.sendMessage("§clBienvenue sur Volcania " + "§e" + p.getDisplayName());
                p.sendMessage("§6Voici quelques informations afin de bien commencer votre aventure :");
                p.sendMessage("§e/shop afin de faire vos premiers achats !");
                p.sendMessage("§e/rules afin de lire les règles!");
                p.sendMessage("§ePour changer de serveur, utilisez la boussole !");
                p.sendMessage("§eDirection le discord, https://discord.volcania.fr");
                p.sendMessage("§c§lBon jeu !");

            }
        } else if (args.length == 1) {
            if(sender.hasPermission("volcaniaplugin.volcania.reload")){
                if(args[0].equalsIgnoreCase("reload")){
                    main.reloadConfig();
                    sender.sendMessage("§c[VolcaniaPlugin] Config reloaded !");
                }
            }else{
                sender.sendMessage("§c[VolcaniaPlugin] Permission nécessaire.");
            }

        }

        return false;
    }
}
