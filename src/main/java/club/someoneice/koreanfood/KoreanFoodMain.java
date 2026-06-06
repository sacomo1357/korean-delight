package club.someoneice.koreanfood;

import club.someoneice.koreanfood.init.BlockList;
import club.someoneice.koreanfood.init.ItemList;
import club.someoneice.koreanfood.init.TabInit;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(KoreanFoodMain.MODID)
public class KoreanFoodMain {
    public static final String MODID = "korean_food";
    private static final Logger LOGGER = LoggerFactory.getLogger("Korean Food");

    public KoreanFoodMain(IEventBus modEventBus) {
        LOGGER.info("Initializing Korean Food Mod...");

        // Register registries
        BlockList.BLOCKS.register(modEventBus);
        BlockList.BLOCK_ITEMS.register(modEventBus);
        BlockList.BLOCK_ENTITIES.register(modEventBus);
        BlockList.MENUS.register(modEventBus);
        ItemList.ITEMS.register(modEventBus);
        TabInit.TABS.register(modEventBus);

        // Register lifecycle events
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::registerScreens);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            net.minecraft.world.level.block.ComposterBlock.COMPOSTABLES.put(ItemList.CHILI_PEPPER.get(), 0.3F);
            net.minecraft.world.level.block.ComposterBlock.COMPOSTABLES.put(ItemList.GREEN_ONION.get(), 0.3F);
            net.minecraft.world.level.block.ComposterBlock.COMPOSTABLES.put(ItemList.GARLIC.get(), 0.3F);
            net.minecraft.world.level.block.ComposterBlock.COMPOSTABLES.put(ItemList.SESAME_SEEDS.get(), 0.3F);
            net.minecraft.world.level.block.ComposterBlock.COMPOSTABLES.put(ItemList.SOYBEAN.get(), 0.3F);
            net.minecraft.world.level.block.ComposterBlock.COMPOSTABLES.put(ItemList.TOFU.get(), 0.3F);
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {
    }

    private void registerScreens(final net.neoforged.neoforge.client.event.RegisterMenuScreensEvent event) {
        event.register(BlockList.PRESSER_MENU.get(), club.someoneice.koreanfood.common.PresserScreen::new);
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
