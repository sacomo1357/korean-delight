package club.someoneice.koreanfood.data;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class ItemUtil {

    public static Item food(int hunger, float saturation, boolean alwaysEat, boolean fast, boolean isDrink, boolean fireResistant) {
        FoodProperties.Builder builder = new FoodProperties.Builder()
                .nutrition(hunger)
                .saturationModifier(saturation);
        if (alwaysEat) {
            builder.alwaysEdible();
        }
        
        Item.Properties properties = new Item.Properties().food(builder.build());
        return new Item(properties);
    }

    public static Item foodItems(int hunger, float saturation, boolean alwaysEat, boolean fast, boolean fireResistant) {
        return food(hunger, saturation, alwaysEat, fast, false, fireResistant);
    }

    public static Item foodItems(int hunger, float saturation, boolean alwaysEat, boolean fast) {
        return food(hunger, saturation, alwaysEat, fast, false, false);
    }

    public static Item foodDrinkItems(int hunger, float saturation, boolean alwaysEat, boolean fast) {
        return food(hunger, saturation, alwaysEat, fast, true, false);
    }
}
