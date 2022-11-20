package morph_hide.morph_hide;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Gtimer {
    public static int i = 600;
    private BossBar bar;
    private double b = 1.0;
    private int m;
    private int s;
    public void gcountdown(){
        createBar();
        Bukkit.getOnlinePlayers().forEach((player)->{
            bar.addPlayer(player);
        });
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                if(i == 0){
                    Bukkit.getOnlinePlayers().forEach((player)->{
                        bar.removePlayer(player);
                        player.getLocation().getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH,0.1F,1);
                        player.sendTitle(net.md_5.bungee.api.ChatColor.DARK_PURPLE + "FINISH","", 40, 250, 40);
                        if(player.getGameMode() == GameMode.ADVENTURE){
                            player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,10000,1,false,false));
                            player.getInventory().removeItem(new ItemStack(Material.ZOMBIE_HEAD,100));
                            player.getInventory().removeItem(new ItemStack(Material.GUNPOWDER,100));
                            player.getInventory().removeItem(new ItemStack(Material.BLAZE_ROD,100));
                            player.getInventory().removeItem(new ItemStack(Material.FEATHER,100));

                            player.setGameMode(GameMode.SURVIVAL);
                        }
                    });

                    Bukkit.broadcastMessage("お疲れさまでした。");
                    cancel();
                    return;
                } else if( i < 5){
                    Bukkit.getOnlinePlayers().forEach((player)->{
                        Location loc = player.getLocation();
                        loc.getWorld().playSound(loc, Sound.BLOCK_NOTE_BLOCK_HARP, 1, 10);
                    });
                } else if (i == 15){
                    EventListener.ss = false;
                } else if(i == 60){
                    Bukkit.getOnlinePlayers().forEach((player)->{
                        Location loc = player.getLocation();
                        loc.getWorld().playSound(loc, Sound.BLOCK_NOTE_BLOCK_HARP, 1, 10);
                        player.sendMessage("残り1分");
                    });
                } else if(i == 180){
                    Bukkit.getOnlinePlayers().forEach((player)->{
                        Location loc = player.getLocation();
                        loc.getWorld().playSound(loc, Sound.BLOCK_NOTE_BLOCK_HARP, 1, 10);
                        player.sendMessage("残り3分");
                    });
                } else if(i == 600){
                    Bukkit.getOnlinePlayers().forEach((player)->{
                        Location loc = player.getLocation();
                        loc.getWorld().playSound(loc, Sound.BLOCK_NOTE_BLOCK_HARP, 1, 10);
                        player.sendMessage("残り10分");
                    });
                }

                i--;
                b = i / 600d;
                bar.setProgress(b);
                m = i / 60;
                s = i - m * 60;
                bar.setTitle("残り時間：" + m + "分 " + s +"秒");
            }
        };
        task.runTaskTimer(Morph_hide.getPlugin(), 8L,20L);
    }

    public void addPlayer(Player player){
        bar.addPlayer(player);
    }

    public void removePlayer(Player player){
        bar.removePlayer(player);
    }

    public void createBar(){
        this.bar = Bukkit.createBossBar("残り時間" + i + "秒", BarColor.BLUE, BarStyle.SEGMENTED_6);
        bar.setVisible(true);
    }
}