package club.someoneice.koreanfood.common;

import club.someoneice.koreanfood.init.BlockList;
import club.someoneice.koreanfood.init.ItemList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PresserRecipe {
    public final List<Ingredient> ingredients;
    public final ItemStack output;
    public final int processTime;

    public PresserRecipe(List<Ingredient> ingredients, ItemStack output, int processTime) {
        this.ingredients = ingredients;
        this.output = output;
        this.processTime = processTime;
    }

    public boolean matches(List<ItemStack> inputs) {
        List<ItemStack> checkList = new ArrayList<>();
        for (ItemStack stack : inputs) {
            if (!stack.isEmpty()) {
                checkList.add(stack.copy());
            }
        }

        if (checkList.size() != ingredients.size()) {
            return false;
        }

        for (Ingredient ing : ingredients) {
            boolean matched = false;
            for (int i = 0; i < checkList.size(); i++) {
                if (ing.test(checkList.get(i))) {
                    matched = true;
                    checkList.remove(i);
                    break;
                }
            }
            if (!matched) return false;
        }

        return true;
    }

    // Static list of all presser recipes
    public static final List<PresserRecipe> RECIPES = new ArrayList<>();

    public static void init() {
        RECIPES.clear();

        // 1. Tofu Recipe: 2 Soybean + 1 Salt -> 1 Tofu
        RECIPES.add(new PresserRecipe(
                Arrays.asList(
                        Ingredient.of(ItemList.SOYBEAN.get()),
                        Ingredient.of(ItemList.SOYBEAN.get()),
                        Ingredient.of(ItemList.SALT.get())
                ),
                new ItemStack(ItemList.TOFU.get(), 1),
                100 // 5 seconds
        ));

        // 2. Sesame Oil Recipe: 1 Sesame Seeds -> 1 Sesame Oil
        RECIPES.add(new PresserRecipe(
                Arrays.asList(
                        Ingredient.of(ItemList.SESAME_SEEDS.get())
                ),
                new ItemStack(ItemList.SESAME_OIL.get(), 1),
                100
        ));

        // 3. Rice Cake Recipe: 2 Farmers Delight Rice -> 2 Rice Cake
        Item riceItem = BuiltInRegistries.ITEM.get(ResourceLocation.parse("farmersdelight:rice"));
        if (riceItem == Items.AIR) {
            // Fallback to wheat if Farmers Delight is somehow missing in development
            riceItem = Items.WHEAT;
        }
        RECIPES.add(new PresserRecipe(
                Arrays.asList(
                        Ingredient.of(riceItem),
                        Ingredient.of(riceItem)
                ),
                new ItemStack(ItemList.RICE_CAKE.get(), 2),
                100
        ));

        // 4. Fish Cake Recipe: 1 Fish + 1 Wheat + 1 Salt -> 2 Fish Cake
        RECIPES.add(new PresserRecipe(
                Arrays.asList(
                        Ingredient.of(Items.COD, Items.SALMON, Items.PUFFERFISH, Items.TROPICAL_FISH),
                        Ingredient.of(Items.WHEAT),
                        Ingredient.of(ItemList.SALT.get())
                ),
                new ItemStack(ItemList.FISH_CAKE.get(), 2),
                100
        ));

        // 5. Meju Recipe: 3 Soybean -> 1 Meju Block
        RECIPES.add(new PresserRecipe(
                Arrays.asList(
                        Ingredient.of(ItemList.SOYBEAN.get()),
                        Ingredient.of(ItemList.SOYBEAN.get()),
                        Ingredient.of(ItemList.SOYBEAN.get())
                ),
                new ItemStack(BlockList.MEJU_ITEM.get(), 1),
                100
        ));
    }
}
