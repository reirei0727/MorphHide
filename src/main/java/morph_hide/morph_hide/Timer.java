package morph_hide.morph_hide;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer {
    public static int count = 5;
    private Gtimer gtimer;
    public void countdown(){
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                if(count == 0){
                    Bukkit.getOnlinePlayers().forEach((player)->{
                        player.getLocation().getWorld().playSound(player.getLocation(), Sound.ENTITY_WITHER_SPAWN,0.5F,1);
                        player.sendTitle(ChatColor.AQUA + "GAME START","かくれんぼ", 40, 150, 40);

                        if(player.getGameMode() == GameMode.ADVENTURE) {
                            player.getInventory().addItem(new ItemStack(Material.BLAZE_ROD, 1));
                            player.getInventory().addItem(new ItemStack(Material.FEATHER, 1));
                            player.getInventory().addItem(new ItemStack(Material.ZOMBIE_HEAD, 1));
                            player.getInventory().addItem(new ItemStack(Material.GUNPOWDER, 1));
                        } else if(player.getGameMode() == GameMode.SURVIVAL){
                            //player.getInventory().addItem(new ItemStack(Material.NETHERITE_SWORD, 1));
                            ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);
                            sword.addEnchantment(Enchantment.DAMAGE_ALL,5);
                            sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL,400);
                            player.getInventory().addItem(sword);
                            player.getInventory().addItem(new ItemStack(Material.WHEAT_SEEDS, 1));
                            player.getInventory().addItem(new ItemStack(Material.WHEAT, 1));
                            player.getInventory().addItem(new ItemStack(Material.CARROT, 1));
                            player.getInventory().addItem(new ItemStack(Material.SALMON, 1));
                            player.getInventory().addItem(new ItemStack(Material.BAMBOO, 1));
                        }
                    });
                    gtimer = new Gtimer();
                    gtimer.gcountdown();
                    EventListener.ss = true;

                    cancel();
                    return;
                } else if( count < 5){
                    Bukkit.getOnlinePlayers().forEach((player)->{
                        Location loc = player.getLocation();
                        loc.getWorld().playSound(loc, Sound.BLOCK_NOTE_BLOCK_HARP, 1, 10);
                    });
                }
                Bukkit.getOnlinePlayers().forEach((player)->{
                    player.setLevel(count);
                });

                count--;
            }
        };
        task.runTaskTimer(Morph_hide.getPlugin(), 8L,20L);
    }
}
