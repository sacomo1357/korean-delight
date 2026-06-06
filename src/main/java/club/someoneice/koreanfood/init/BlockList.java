package club.someoneice.koreanfood.init;

import club.someoneice.koreanfood.KoreanFoodMain;
import club.someoneice.koreanfood.common.KoreanCropBlock;
import club.someoneice.koreanfood.data.BlockUtil;
import club.someoneice.koreanfood.common.PresserBlock;
import club.someoneice.koreanfood.common.PresserBlockEntity;
import club.someoneice.koreanfood.common.PresserMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockList {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(KoreanFoodMain.MODID);
    public static final DeferredRegister.Items BLOCK_ITEMS = DeferredRegister.createItems(KoreanFoodMain.MODID);

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = 
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, KoreanFoodMain.MODID);

    public static final DeferredRegister<MenuType<?>> MENUS = 
            DeferredRegister.create(Registries.MENU, KoreanFoodMain.MODID);

    // ==========================================
    // K-FOOD Presser Block & Entity / Menu
    // ==========================================
    public static final DeferredBlock<Block> PRESSER = BLOCKS.register("presser", 
            () -> new PresserBlock(BlockBehaviour.Properties.of()
                    .destroyTime(3.0F)
                    .explosionResistance(3.0F)
                    .sound(SoundType.METAL)
                    .requiresCorrectToolForDrops()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PresserBlockEntity>> PRESSER_BLOCK_ENTITY = 
            BLOCK_ENTITIES.register("presser", 
                    () -> BlockEntityType.Builder.of(PresserBlockEntity::new, PRESSER.get()).build(null));

    public static final DeferredHolder<MenuType<?>, MenuType<PresserMenu>> PRESSER_MENU = 
            MENUS.register("presser", 
                    () -> net.neoforged.neoforge.common.extensions.IMenuTypeExtension.create(PresserMenu::new));

    // ==========================================
    // K-FOOD Crop Blocks
    // ==========================================
    public static final DeferredBlock<Block> CHILI_PEPPER_CROP = BLOCKS.register("chili_pepper_crop", 
            () -> new KoreanCropBlock(BlockList::getChiliCropItem));

    public static final DeferredBlock<Block> GARLIC_CROP = BLOCKS.register("garlic_crop", 
            () -> new KoreanCropBlock(BlockList::getGarlicCropItem));

    public static final DeferredBlock<Block> SESAME_CROP = BLOCKS.register("sesame_crop", 
            () -> new KoreanCropBlock(BlockList::getSesameCropItem));

    public static final DeferredBlock<Block> GREEN_ONION_CROP = BLOCKS.register("green_onion_crop", 
            () -> new KoreanCropBlock(BlockList::getGreenOnionCropItem));

    public static final DeferredBlock<Block> SOYBEAN_CROP = BLOCKS.register("soybean_crop", 
            () -> new KoreanCropBlock(BlockList::getSoybeanCropItem));


    // Helper methods to resolve crop block items lazily to avoid circular dependencies
    private static Item getChiliCropItem() { return ItemList.CHILI_PEPPER.get(); }
    private static Item getGarlicCropItem() { return ItemList.GARLIC.get(); }
    private static Item getSesameCropItem() { return ItemList.SESAME_SEEDS.get(); }
    private static Item getGreenOnionCropItem() { return ItemList.GREEN_ONION.get(); }
    private static Item getSoybeanCropItem() { return ItemList.SOYBEAN.get(); }


    // ==========================================
    // K-FOOD Wild Crop Blocks & Block Items
    // ==========================================
    public static final DeferredBlock<Block> WILD_CHILI_PEPPER = BLOCKS.register("wild_chili_pepper", 
            BlockUtil::wildBlock);
    public static final DeferredItem<Item> WILD_CHILI_PEPPER_ITEM = BLOCK_ITEMS.register("wild_chili_pepper", 
            () -> new BlockItem(WILD_CHILI_PEPPER.get(), new Item.Properties()));

    public static final DeferredBlock<Block> WILD_GARLIC = BLOCKS.register("wild_garlic", 
            BlockUtil::wildBlock);
    public static final DeferredItem<Item> WILD_GARLIC_ITEM = BLOCK_ITEMS.register("wild_garlic", 
            () -> new BlockItem(WILD_GARLIC.get(), new Item.Properties()));

    public static final DeferredBlock<Block> WILD_SESAME = BLOCKS.register("wild_sesame", 
            BlockUtil::wildBlock);
    public static final DeferredItem<Item> WILD_SESAME_ITEM = BLOCK_ITEMS.register("wild_sesame", 
            () -> new BlockItem(WILD_SESAME.get(), new Item.Properties()));

    public static final DeferredBlock<Block> WILD_GREEN_ONION = BLOCKS.register("wild_green_onion", 
            BlockUtil::wildBlock);
    public static final DeferredItem<Item> WILD_GREEN_ONION_ITEM = BLOCK_ITEMS.register("wild_green_onion", 
            () -> new BlockItem(WILD_GREEN_ONION.get(), new Item.Properties()));

    public static final DeferredBlock<Block> WILD_SOYBEAN = BLOCKS.register("wild_soybean", 
            BlockUtil::wildBlock);
    public static final DeferredItem<Item> WILD_SOYBEAN_ITEM = BLOCK_ITEMS.register("wild_soybean", 
            () -> new BlockItem(WILD_SOYBEAN.get(), new Item.Properties()));

    // ==========================================
    // K-FOOD Meju Block & Block Item
    // ==========================================
    public static final DeferredBlock<Block> MEJU = BLOCKS.register("meju", 
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.5F)
                    .sound(SoundType.WOOD)));

    public static final DeferredItem<Item> MEJU_ITEM = BLOCK_ITEMS.register("meju", 
            () -> new BlockItem(MEJU.get(), new Item.Properties()));

    // ==========================================
    // K-FOOD Salt Ore Block & Block Item
    // ==========================================
    public static final DeferredBlock<Block> SALT_ORE = BLOCKS.register("salt_ore", 
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3.0F)
                    .sound(net.minecraft.world.level.block.SoundType.STONE)
                    .requiresCorrectToolForDrops()));

    public static final DeferredItem<Item> SALT_ORE_ITEM = BLOCK_ITEMS.register("salt_ore", 
            () -> new BlockItem(SALT_ORE.get(), new Item.Properties()));
}
