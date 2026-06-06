package club.someoneice.koreanfood.init;

import club.someoneice.koreanfood.KoreanFoodMain;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TabInit {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KoreanFoodMain.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> KOREAN_FOOD_TAB = TABS.register("korean_food_tab", 
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.korean_food"))
                    .icon(() -> new ItemStack(ItemList.KIMCHI.get()))
                    .displayItems((params, output) -> {
                        // K-FOOD Dishes (10 items)
                        output.accept(ItemList.KIMCHI.get());
                        output.accept(ItemList.BIBIMBAP.get());
                        output.accept(ItemList.JEYUK.get());
                        output.accept(ItemList.TTEOKBOKKI.get());
                        output.accept(ItemList.JANCHI_GUKSU.get());
                        output.accept(ItemList.BULDAK_BOKKEUM_MYEON.get());
                        output.accept(ItemList.YANGNYEOM_CHICKEN.get());
                        output.accept(ItemList.MIYEOK_GUK.get());
                        output.accept(ItemList.BULGOGI.get());
                        output.accept(ItemList.SIKHYE.get());
                        output.accept(ItemList.DOENJANG_JJIGAE.get());

                        // K-FOOD Ingredients & Tools (12 items)
                        output.accept(ItemList.CHILI_PEPPER.get());
                        output.accept(ItemList.CHILI_POWDER.get());
                        output.accept(ItemList.GARLIC.get());
                        output.accept(ItemList.SALT.get());
                        output.accept(ItemList.SOY_SAUCE.get());
                        output.accept(ItemList.SESAME_OIL.get());
                        output.accept(ItemList.SESAME_SEEDS.get());
                        output.accept(ItemList.PRESSER.get());
                        output.accept(ItemList.GREEN_ONION.get());
                        output.accept(ItemList.GOCHUJANG.get());
                        output.accept(ItemList.SOMEN.get());
                        output.accept(ItemList.DEEP_FRIED_NOODLES.get());
                        output.accept(ItemList.SOYBEAN.get());
                        output.accept(ItemList.TOFU.get());
                        output.accept(ItemList.RICE_CAKE.get());
                        output.accept(ItemList.FISH_CAKE.get());
                        output.accept(BlockList.MEJU_ITEM.get());
                        output.accept(BlockList.SALT_ORE_ITEM.get());



                        // K-FOOD Wild Crops (5 items)
                        output.accept(BlockList.WILD_CHILI_PEPPER_ITEM.get());
                        output.accept(BlockList.WILD_GARLIC_ITEM.get());
                        output.accept(BlockList.WILD_SESAME_ITEM.get());
                        output.accept(BlockList.WILD_GREEN_ONION_ITEM.get());
                        output.accept(BlockList.WILD_SOYBEAN_ITEM.get());
                    })
                    .build());
}
