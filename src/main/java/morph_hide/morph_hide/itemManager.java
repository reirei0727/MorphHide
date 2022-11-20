package morph_hide.morph_hide;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class itemManager {
    public static ItemStack wand;
    public static void init(){createWand();}

    private static void createWand(){
        ItemStack item = new ItemStack(Material.CARVED_PUMPKIN,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "ハッピーハロウィン！");
        List<String> lore = new ArrayList<>();
        //lore.add("個数計算");
        meta.setLore(lore);
        item.setItemMeta(meta);
        wand = item;

        ShapelessRecipe sir = new ShapelessRecipe(NamespacedKey.minecraft("wand_shapeless"),item);
        sir.addIngredient(1,Material.DIAMOND);
        Bukkit.getServer().addRecipe(sir);
    }
}
