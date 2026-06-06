package club.someoneice.koreanfood.jei;

import club.someoneice.koreanfood.KoreanFoodMain;
import club.someoneice.koreanfood.common.PresserMenu;
import club.someoneice.koreanfood.common.PresserRecipe;
import club.someoneice.koreanfood.common.PresserScreen;
import club.someoneice.koreanfood.init.BlockList;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    public static final ResourceLocation UID = ResourceLocation.parse(KoreanFoodMain.MODID + ":jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new PresserRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        PresserRecipe.init();
        registration.addRecipes(PresserRecipeCategory.RECIPE_TYPE, PresserRecipe.RECIPES);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(BlockList.PRESSER.get()), PresserRecipeCategory.RECIPE_TYPE);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        // Arrow in the middle (x=122, y=23, width=15, height=22)
        registration.addRecipeClickArea(PresserScreen.class, 122, 23, 15, 22, PresserRecipeCategory.RECIPE_TYPE);
        // Green book button (x=48, y=48, width=20, height=18)
        registration.addRecipeClickArea(PresserScreen.class, 48, 48, 20, 18, PresserRecipeCategory.RECIPE_TYPE);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        // PresserMenu slots:
        // Input slots: 0, 1, 2 (3 slots)
        // Output slot: 3 (1 slot)
        // Player inventory: starts at index 4, size is 36
        registration.addRecipeTransferHandler(
                PresserMenu.class,
                BlockList.PRESSER_MENU.get(),
                PresserRecipeCategory.RECIPE_TYPE,
                0, // recipeSlotStart
                3, // recipeSlotCount
                4, // inventorySlotStart
                36 // inventorySlotCount
        );
    }
}
