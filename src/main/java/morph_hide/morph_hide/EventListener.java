package morph_hide.morph_hide;

import org.bukkit.*;
import org.bukkit.boss.BossBar;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class EventListener implements Listener {
    private int Gint = 0;
    public static boolean ss = false;
    @EventHandler
    public void onClickEvent(PlayerInteractEvent e){
        Player p = e.getPlayer();
        p.setFoodLevel(20);
        if(p.getInventory().getItemInMainHand().getType() == Material.STICK){
            p.chat("/morph");
            return;
        }

        if(p.getInventory().getItemInMainHand().getType() == Material.PAPER){
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String scommand = "clear " + p.getName();
            Bukkit.dispatchCommand(console, scommand);
            p.teleport(new Location(p.getWorld(),345,112,1893));
            p.setGameMode(GameMode.ADVENTURE);
            BukkitRunnable task = new BukkitRunnable() {
                public void run(){
                    p.getInventory().addItem(new ItemStack(Material.STICK, 1));
                    cancel();
                }
            };
            task.runTaskTimer(Morph_hide.getPlugin(),20L,0);

            return;
        }
        if (!ss)return;
        if(p.getInventory().getItemInMainHand().getType() == Material.GUNPOWDER){

            p.sendMessage("花火を打ちあげます");
            p.getInventory().removeItem(new ItemStack(Material.GUNPOWDER, 1));
            p.getInventory().removeItem(new ItemStack(Material.ZOMBIE_HEAD, 1));
            p.getInventory().removeItem(new ItemStack(Material.FEATHER, 1));
            p.getInventory().removeItem(new ItemStack(Material.BLAZE_ROD, 1));
            BukkitRunnable task = new BukkitRunnable() {
                public void run(){
                    Firework f = e.getPlayer().getWorld().spawn(e.getPlayer().getLocation(),Firework.class);
                    FireworkMeta data = (FireworkMeta) f.getFireworkMeta();
                    data.addEffects(FireworkEffect.builder().withColor(Color.PURPLE).withColor(Color.GREEN).with(FireworkEffect.Type.BALL_LARGE).withFlicker().build());
                    data.setPower(1);
                    f.setFireworkMeta(data);
                    p.getInventory().addItem(new ItemStack(Material.GUNPOWDER, 1));
                    p.getInventory().addItem(new ItemStack(Material.ZOMBIE_HEAD, 1));
                    p.getInventory().addItem(new ItemStack(Material.FEATHER, 1));
                    p.getInventory().addItem(new ItemStack(Material.BLAZE_ROD, 1));
                    p.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
                    cancel();
                }
            };
            task.runTaskTimer(Morph_hide.getPlugin(),200L,0);
        } else if(p.getInventory().getItemInMainHand().getType() == Material.ZOMBIE_HEAD){

            p.sendMessage("ジャイアントを召喚します");

            if(Gint >= 3){
                p.getWorld().setDifficulty(Difficulty.PEACEFUL);
                Gint = 0;
                BukkitRunnable task = new BukkitRunnable() {
                    public void run(){
                        p.getWorld().setDifficulty(Difficulty.EASY);

                        cancel();
                    }
                };
                task.runTaskTimer(Morph_hide.getPlugin(),20L,0);
            }
            Gint += 1;
            p.getInventory().removeItem(new ItemStack(Material.GUNPOWDER, 1));
            p.getInventory().removeItem(new ItemStack(Material.ZOMBIE_HEAD, 1));
            p.getInventory().removeItem(new ItemStack(Material.FEATHER, 1));
            p.getInventory().removeItem(new ItemStack(Material.BLAZE_ROD, 1));
            BukkitRunnable task = new BukkitRunnable() {
                public void run(){
                    Location loc = p.getLocation();
                    World w = p.getWorld();
                    w.spawnEntity(loc.subtract(5,0,0), EntityType.GIANT);
                    p.getInventory().addItem(new ItemStack(Material.GUNPOWDER, 1));
                    p.getInventory().addItem(new ItemStack(Material.ZOMBIE_HEAD, 1));
                    p.getInventory().addItem(new ItemStack(Material.FEATHER, 1));
                    p.getInventory().addItem(new ItemStack(Material.BLAZE_ROD, 1));
                    p.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
                    cancel();
                }
            };
            task.runTaskTimer(Morph_hide.getPlugin(),200L,0);
        } else if(p.getInventory().getItemInMainHand().getType() == Material.FEATHER){

            p.sendMessage("空を飛びます");
            p.getInventory().removeItem(new ItemStack(Material.GUNPOWDER, 1));
            p.getInventory().removeItem(new ItemStack(Material.ZOMBIE_HEAD, 1));
            p.getInventory().removeItem(new ItemStack(Material.FEATHER, 1));
            p.getInventory().removeItem(new ItemStack(Material.BLAZE_ROD, 1));
            BukkitRunnable task = new BukkitRunnable() {
                public void run(){
                    p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,80,1,false,false));
                    p.getInventory().addItem(new ItemStack(Material.GUNPOWDER, 1));
                    p.getInventory().addItem(new ItemStack(Material.ZOMBIE_HEAD, 1));
                    p.getInventory().addItem(new ItemStack(Material.FEATHER, 1));
                    p.getInventory().addItem(new ItemStack(Material.BLAZE_ROD, 1));
                    p.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
                    /*
                    for(PotionEffect effect:p.getActivePotionEffects()){
                        p.removePotionEffect(effect.getType());
                    }

                     */
                    cancel();
                }
            };
            task.runTaskTimer(Morph_hide.getPlugin(),100L,0);
        } else if(p.getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD){

            p.sendMessage("雷を召喚します");
            p.getInventory().removeItem(new ItemStack(Material.GUNPOWDER, 1));
            p.getInventory().removeItem(new ItemStack(Material.ZOMBIE_HEAD, 1));
            p.getInventory().removeItem(new ItemStack(Material.FEATHER, 1));
            p.getInventory().removeItem(new ItemStack(Material.BLAZE_ROD, 1));
            BukkitRunnable task = new BukkitRunnable() {
                public void run(){
                    p.getWorld().strikeLightningEffect(p.getLocation());
                    p.getInventory().addItem(new ItemStack(Material.GUNPOWDER, 1));
                    p.getInventory().addItem(new ItemStack(Material.ZOMBIE_HEAD, 1));
                    p.getInventory().addItem(new ItemStack(Material.FEATHER, 1));
                    p.getInventory().addItem(new ItemStack(Material.BLAZE_ROD, 1));
                    p.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
                    cancel();
                }
            };
            task.runTaskTimer(Morph_hide.getPlugin(),200L,0);
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e){
        LivingEntity entity = e.getEntity();
        Player p = e.getEntity().getKiller();
        if(entity.getType() != EntityType.PLAYER){
            if(p == null)return;
            entity.getKiller().sendMessage(ChatColor.RED + "モブでした");
            entity.getKiller().setHealth(0.0D);
        }
    }

    @EventHandler
    public void onDeathEvent(PlayerDeathEvent e){
        Player p = e.getPlayer();
        if(p.getGameMode() == GameMode.ADVENTURE){
            p.setGameMode(GameMode.SPECTATOR);
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String scommand = "clear " + p.getName();
            Bukkit.dispatchCommand(console, scommand);
            p.sendMessage(ChatColor.AQUA + "観戦モードに切り替わります。");
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if(event.getEntity() instanceof Player){
            Player p = (Player) event.getEntity();

            if(event.getCause() == EntityDamageEvent.DamageCause.FALL){
                event.setCancelled(true);
            }
        }
    }

    /*
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(!ss)return;
        p.setGameMode(GameMode.ADVENTURE);
    }

     */
}
