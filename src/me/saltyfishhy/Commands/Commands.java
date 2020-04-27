package me.saltyfishhy.Commands;


import me.saltyfishhy.throwTNT.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Commands implements CommandExecutor {

    private Plugin plugin = Main.getPlugin(Main.class);
    String permission = plugin.getConfig().getString("commandPermission");
    String action = plugin.getConfig().getString("leftOrRightClick");

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("tnttutorial")) {
            Player player = (Player) sender;
            player.sendMessage(ChatColor.DARK_RED + "TNT > " + ChatColor.GRAY + "To throw tnt, right click while holding throwable tnt at the air.");
            player.sendMessage(ChatColor.DARK_RED + "TNT > " + ChatColor.GRAY + "To get tnt, type the command /gettnt");
            return true;
        } else if (label.equalsIgnoreCase("gettnt")) {
            if (!(sender.hasPermission(permission))) {
                sender.sendMessage(ChatColor.RED + "TNT > " + ChatColor.GRAY + "You do not have permission to get throwable tnt.");
                return true;
            }
            Player player = (Player) sender;
            ItemStack item = new ItemStack(Material.TNT);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("Throwable TNT");
            List<String> stringList = new ArrayList<>();
            if (action.equals("LEFT_CLICK")) {
                stringList.add(ChatColor.DARK_GRAY + "Left click to throw!");
            }
            if (action.equals("RIGHT_CLICK")) {
                stringList.add(ChatColor.DARK_GRAY + "Right click to throw!");
            }
            meta.setLore(stringList);
            item.setItemMeta(meta);
            player.getInventory().addItem(item);
            return true;
        }
        return false;
    }
}
