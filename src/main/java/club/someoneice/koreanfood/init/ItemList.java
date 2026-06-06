package club.someoneice.koreanfood.init;

import club.someoneice.koreanfood.KoreanFoodMain;
import club.someoneice.koreanfood.common.ItemDrinkable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemList {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(KoreanFoodMain.MODID);

    // ==========================================
    // K-FOOD Custom Items (10 Dishes)
    // ==========================================
    
    // Kimchi (김치)
    public static final DeferredItem<Item> KIMCHI = ITEMS.register("kimchi", 
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(4).saturationModifier(0.4f)
                    .effect(() -> new net.minecraft.world.effect.MobEffectInstance(
                            net.minecraft.world.effect.MobEffects.MOVEMENT_SPEED, 100, 1), 1.0f)
                    .effect(() -> new net.minecraft.world.effect.MobEffectInstance(
                            net.minecraft.world.effect.MobEffects.FIRE_RESISTANCE, 240, 0), 1.0f)
                    .build())));

    // Bibimbap (비빔밥) - Returns a Bowl
    public static final DeferredItem<Item> BIBIMBAP = ITEMS.register("bibimbap", 
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(12).saturationModifier(0.8f)
                    .effect(getNourishmentEffect(4200), 1.0f).build())));

    // Jeyuk (제육볶음) - Returns a Bowl
    public static final DeferredItem<Item> JEYUK = ITEMS.register("jeyuk", 
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(8).saturationModifier(0.7f)
                    .effect(getNourishmentEffect(1800), 1.0f).build())));

    // Tteokbokki (떡볶이) - Returns a Bowl
    public static final DeferredItem<Item> TTEOKBOKKI = ITEMS.register("tteokbokki", 
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(6).saturationModifier(0.5f).build())));

    // Janchi-guksu (잔치국수) - Returns a Bowl
    public static final DeferredItem<Item> JANCHI_GUKSU = ITEMS.register("janchi_guksu", 
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(8).saturationModifier(0.6f)
                    .effect(getNourishmentEffect(2400), 1.0f).build())));

    // Buldak-bokkeum-myeon (불닭볶음면) - Returns a Bowl & fire resistance
    public static final DeferredItem<Item> BULDAK_BOKKEUM_MYEON = ITEMS.register("buldak_bokkeum_myeon", 
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(8).saturationModifier(0.5f)
                    .effect(() -> new net.minecraft.world.effect.MobEffectInstance(
                            net.minecraft.world.effect.MobEffects.FIRE_RESISTANCE, 2400, 0), 1.0f)
                    .build())));

    // Yangnyeom-chicken (양념치킨)
    public static final DeferredItem<Item> YANGNYEOM_CHICKEN = ITEMS.register("yangnyeom_chicken", 
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(9).saturationModifier(0.7f)
                    .effect(getNourishmentEffect(3600), 1.0f).build())));

    // Miyeok-guk (미역국) - Returns a Bowl
    public static final DeferredItem<Item> MIYEOK_GUK = ITEMS.register("miyeok_guk", 
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(6).saturationModifier(0.6f)
                    .effect(getNourishmentEffect(3000), 1.0f).build())));

    // Bulgogi (불고기)
    public static final DeferredItem<Item> BULGOGI = ITEMS.register("bulgogi", 
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(8).saturationModifier(0.7f)
                    .effect(getNourishmentEffect(2400), 1.0f).build())));

    // Sikhye (식혜) - Returns a Glass Bottle
    public static final DeferredItem<Item> SIKHYE = ITEMS.register("sikhye", 
            () -> new ItemDrinkable(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(3).saturationModifier(0.3f).build())));

    // Doenjang Jjigae (된장찌개) - Returns a Bowl
    public static final DeferredItem<Item> DOENJANG_JJIGAE = ITEMS.register("doenjang_jjigae", 
            () -> new BowlFoodItem(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(10).saturationModifier(0.8f).build())));


    // ==========================================
    // K-FOOD Custom Ingredients & Tools (13 Items)
    // ==========================================
    
    // Chili Pepper (고추)
    public static final DeferredItem<Item> CHILI_PEPPER = ITEMS.register("chili_pepper", 
            () -> new net.minecraft.world.item.ItemNameBlockItem(BlockList.CHILI_PEPPER_CROP.get(), 
                    new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(2).saturationModifier(0.1f).build())));

    // Chili Powder (고춧가루)
    public static final DeferredItem<Item> CHILI_POWDER = ITEMS.register("chili_powder", 
            () -> new Item(new Item.Properties()));

    // Garlic (마늘)
    public static final DeferredItem<Item> GARLIC = ITEMS.register("garlic", 
            () -> new net.minecraft.world.item.ItemNameBlockItem(BlockList.GARLIC_CROP.get(), 
                    new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(1).saturationModifier(0.1f).build())));

    // Salt (소금)
    public static final DeferredItem<Item> SALT = ITEMS.register("salt", 
            () -> new Item(new Item.Properties()));

    // Soy Sauce (간장)
    public static final DeferredItem<Item> SOY_SAUCE = ITEMS.register("soy_sauce", 
            () -> new Item(new Item.Properties()));

    // Sesame Oil (참기름)
    public static final DeferredItem<Item> SESAME_OIL = ITEMS.register("sesame_oil", 
            () -> new Item(new Item.Properties()));

    // Sesame Seeds (참깨)
    public static final DeferredItem<Item> SESAME_SEEDS = ITEMS.register("sesame_seeds", 
            () -> new net.minecraft.world.item.ItemNameBlockItem(BlockList.SESAME_CROP.get(), 
                    new Item.Properties()));

    // Presser (압착기) - Block Item
    public static final DeferredItem<Item> PRESSER = ITEMS.register("presser", 
            () -> new net.minecraft.world.item.BlockItem(BlockList.PRESSER.get(), new Item.Properties()));

    // Green Onion (대파)
    public static final DeferredItem<Item> GREEN_ONION = ITEMS.register("green_onion", 
            () -> new net.minecraft.world.item.ItemNameBlockItem(BlockList.GREEN_ONION_CROP.get(), 
                    new Item.Properties()));

    // Gochujang (고추장)
    public static final DeferredItem<Item> GOCHUJANG = ITEMS.register("gochujang", 
            () -> new Item(new Item.Properties().craftRemainder(net.minecraft.world.item.Items.GLASS_BOTTLE)));

    // Somen (소면)
    public static final DeferredItem<Item> SOMEN = ITEMS.register("somen", 
            () -> new Item(new Item.Properties()));

    // Deep-fried Noodles (유탕면)
    public static final DeferredItem<Item> DEEP_FRIED_NOODLES = ITEMS.register("deep_fried_noodles", 
            () -> new Item(new Item.Properties()));

    // Soybean (콩)
    public static final DeferredItem<Item> SOYBEAN = ITEMS.register("soybean", 
            () -> new net.minecraft.world.item.ItemNameBlockItem(BlockList.SOYBEAN_CROP.get(), 
                    new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(2).saturationModifier(0.2f).build())));

    // Tofu (두부)
    public static final DeferredItem<Item> TOFU = ITEMS.register("tofu", 
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(3).saturationModifier(0.3f).build())));

    // Rice Cake (떡)
    public static final DeferredItem<Item> RICE_CAKE = ITEMS.register("rice_cake", 
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(1).saturationModifier(0.1f).build())));

    // Fish Cake (어묵)
    public static final DeferredItem<Item> FISH_CAKE = ITEMS.register("fish_cake", 
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(2).saturationModifier(0.2f).build())));


    // ==========================================
    // Custom BowlFoodItem Class
    // ==========================================
    public static class BowlFoodItem extends Item {
        public BowlFoodItem(Item.Properties properties) {
            super(properties);
        }

        @Override
        public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
            ItemStack remainder = super.finishUsingItem(stack, level, entity);
            if (entity instanceof Player player) {
                if (!player.getAbilities().instabuild) {
                    if (remainder.isEmpty()) {
                        return new ItemStack(Items.BOWL);
                    }
                    player.getInventory().add(new ItemStack(Items.BOWL));
                }
            }
            return remainder;
        }
    }

    private static java.util.function.Supplier<net.minecraft.world.effect.MobEffectInstance> getNourishmentEffect(int duration) {
        return () -> {
            var holderOpt = net.minecraft.core.registries.BuiltInRegistries.MOB_EFFECT.getHolder(net.minecraft.resources.ResourceLocation.parse("farmersdelight:nourishment"));
            if (holderOpt.isPresent()) {
                return new net.minecraft.world.effect.MobEffectInstance(holderOpt.get(), duration, 0);
            } else {
                return new net.minecraft.world.effect.MobEffectInstance(net.minecraft.world.effect.MobEffects.SATURATION, duration, 0);
            }
        };
    }
}
