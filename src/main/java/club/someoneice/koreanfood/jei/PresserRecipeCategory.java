package club.someoneice.koreanfood.jei;

import club.someoneice.koreanfood.KoreanFoodMain;
import club.someoneice.koreanfood.common.PresserRecipe;
import club.someoneice.koreanfood.init.BlockList;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public class PresserRecipeCategory implements IRecipeCategory<PresserRecipe> {
    public static final RecipeType<PresserRecipe> RECIPE_TYPE = 
            RecipeType.create(KoreanFoodMain.MODID, "presser", PresserRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;
    private final Component localizedName;
    private final IDrawable arrow;

    public PresserRecipeCategory(IGuiHelper guiHelper) {
        ResourceLocation texture = ResourceLocation.parse(KoreanFoodMain.MODID + ":textures/gui/container/presser.png");
        
        // We crop from x=24, y=20 to x=144, y=72 (width=120, height=52)
        // Relative coordinates:
        // Slot 0 (30, 25) -> relative: (6, 5)
        // Slot 1 (48, 25) -> relative: (24, 5)
        // Slot 2 (66, 25) -> relative: (42, 5)
        // Output Slot 3 (122, 51) -> relative: (98, 31)
        // Arrow (122, 23) -> relative: (98, 3), width 15, height 22
        this.background = guiHelper.createDrawable(texture, 24, 20, 120, 52);
        this.icon = guiHelper.createDrawableItemStack(new ItemStack(BlockList.PRESSER.get()));
        this.localizedName = Component.translatable("container.korean_food.presser");
        
        // Animating the arrow pointing downwards
        this.arrow = guiHelper.createAnimatedDrawable(
                guiHelper.createDrawable(texture, 176, 4, 15, 22), 
                100, // process time / scale speed
                IDrawableAnimated.StartDirection.TOP, 
                false
        );
    }

    @Override
    public RecipeType<PresserRecipe> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return this.localizedName;
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, PresserRecipe recipe, IFocusGroup focuses) {
        List<Ingredient> ingredients = recipe.ingredients;
        
        // Add 3 input slots (0, 1, 2)
        if (ingredients.size() > 0) {
            builder.addSlot(RecipeIngredientRole.INPUT, 6, 5).addIngredients(ingredients.get(0));
        }
        if (ingredients.size() > 1) {
            builder.addSlot(RecipeIngredientRole.INPUT, 24, 5).addIngredients(ingredients.get(1));
        }
        if (ingredients.size() > 2) {
            builder.addSlot(RecipeIngredientRole.INPUT, 42, 5).addIngredients(ingredients.get(2));
        }

        // Add 1 output slot
        builder.addSlot(RecipeIngredientRole.OUTPUT, 98, 31).addItemStack(recipe.output);
    }

    @Override
    public void draw(PresserRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        // Draw progress arrow pointing downwards
        this.arrow.draw(guiGraphics, 98, 3);
    }
}
