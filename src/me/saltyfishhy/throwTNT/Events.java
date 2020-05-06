package me.saltyfishhy.throwTNT;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;


public class Events implements Listener {

    private Plugin plugin = Main.getPlugin(Main.class);
    String actionType = plugin.getConfig().getString("leftOrRightClick");
    String permission = plugin.getConfig().getString("commandPermission");

    @EventHandler
    //creates an event for when a player interacts
    public void onRightClick(PlayerInteractEvent e) {
        //if the event action was right click on air or right click on block
        if (actionType.equals("LEFT_CLICK")) {
            if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                //if the player from the event has permission
                if (e.getPlayer().hasPermission(permission)) {
                    ItemStack item = new ItemStack(Material.TNT);
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName("Throwable TNT");
                    List<String> stringList = new ArrayList<>();
                    if (actionType.equals("LEFT_CLICK")) {
                        stringList.add(ChatColor.DARK_GRAY + "Left click to throw!");
                    }
                    if (actionType.equals("RIGHT_CLICK")) {
                        stringList.add(ChatColor.DARK_GRAY + "Right click to throw!");
                    }
                    meta.setLore(stringList);
                    item.setItemMeta(meta);
                    if (ChatColor.stripColor(e.getPlayer().getItemInHand().getItemMeta().getDisplayName()).equals("Throwable TNT")) {
                        World playerWorld = e.getPlayer().getWorld();
                        //cancels the event, meaning their click doesn't register
                        e.setCancelled(true);
                        if (item.getAmount() == 1) {
                            e.getPlayer().getInventory().remove(item);
                        } else {
                            item.setAmount(item.getAmount() - 1);
                        }
                        TNTPrimed tnt = (TNTPrimed) playerWorld.spawn(e.getPlayer().getLocation(), TNTPrimed.class);
                        Vector playerDirection = e.getPlayer().getLocation().getDirection();
                        Vector velocity = playerDirection.multiply(plugin.getConfig().getDouble("velocityMultiplier"));
                        tnt.setVelocity(velocity);
                    }
                }

                else {
                    e.getPlayer().sendMessage(ChatColor.DARK_RED + "TNT > " + ChatColor.GRAY + "You do not have permission to throw tnt.");
                }
            }
        }
        else if (actionType.equals("RIGHT_CLICK")) {
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                //if the player from the event has permission
                ItemStack item = new ItemStack(Material.TNT);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("Throwable TNT");
                List<String> stringList = new ArrayList<>();
                if (actionType.equals("LEFT_CLICK")) {
                    stringList.add(ChatColor.DARK_GRAY + "Left click to throw!");
                }
                if (actionType.equals("RIGHT_CLICK")) {
                    stringList.add(ChatColor.DARK_GRAY + "Right click to throw!");
                }
                meta.setLore(stringList);
                item.setItemMeta(meta);
                if (ChatColor.stripColor(e.getPlayer().getItemInHand().getItemMeta().getDisplayName()).equals("Throwable TNT")) {
                    if (e.getPlayer().hasPermission(permission)) {

                        World playerWorld = e.getPlayer().getWorld();

                        //cancels the event, meaning their click doesn't register
                        e.setCancelled(true);
                        if (e.getPlayer().getItemInHand().getAmount() == 1) {
                            e.getPlayer().getInventory().remove(item);
                        } else {
                            e.getPlayer().getItemInHand().setAmount(e.getPlayer().getItemInHand().getAmount() - 1);
                        }
                        TNTPrimed tnt = (TNTPrimed) playerWorld.spawn(e.getPlayer().getLocation(), TNTPrimed.class);
                        Vector playerDirection = e.getPlayer().getLocation().getDirection();
                        Vector velocity = playerDirection.multiply(0.8D);
                        velocity.multiply(plugin.getConfig().getInt("velocityMultiplier"));
                        tnt.setVelocity(velocity);
                        tnt.setFuseTicks(plugin.getConfig().getInt("fuseTime") * 20);
                    } else {
                        e.getPlayer().sendMessage(ChatColor.DARK_RED + "TNT > " + ChatColor.GRAY + "You do not have permission to throw tnt.");
                    }
                }
            }
        }
        else {
            plugin.getLogger().warning("There is an error in your config.yml. Double check that the values are set properly.");
        }

    }

}
