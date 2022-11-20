package morph_hide.morph_hide;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandAz implements CommandExecutor {
    private Timer timer;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.isOp()){
            if(args[0].equalsIgnoreCase("start")){
                timer = new Timer();
                timer.countdown();
                Timer.count = 5;
                Gtimer.i = 600;
            } else if(args[0].equalsIgnoreCase("0")){
                Timer.count =1;
                Gtimer.i = 1;
            }
        } else {
            sender.sendMessage("権限がありません");
        }

        return true;
    }
}
