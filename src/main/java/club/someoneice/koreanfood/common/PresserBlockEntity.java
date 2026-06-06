package club.someoneice.koreanfood.common;

import club.someoneice.koreanfood.init.BlockList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class PresserBlockEntity extends BlockEntity implements Container, MenuProvider {
    private final NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY); // 0-2 inputs, 3 output

    private int progress = 0;
    private int maxProgress = 100; // 5 seconds default

    protected final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            switch (index) {
                case 0: return PresserBlockEntity.this.progress;
                case 1: return PresserBlockEntity.this.maxProgress;
                default: return 0;
            }
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0: PresserBlockEntity.this.progress = value; break;
                case 1: PresserBlockEntity.this.maxProgress = value; break;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    public PresserBlockEntity(BlockPos pos, BlockState state) {
        super(BlockList.PRESSER_BLOCK_ENTITY.get(), pos, state);
        PresserRecipe.init(); // Make sure recipes are initialized
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, PresserBlockEntity blockEntity) {
        // Collect current inputs (slots 0, 1, 2)
        List<ItemStack> inputs = new ArrayList<>();
        inputs.add(blockEntity.items.get(0));
        inputs.add(blockEntity.items.get(1));
        inputs.add(blockEntity.items.get(2));

        PresserRecipe activeRecipe = null;
        for (PresserRecipe recipe : PresserRecipe.RECIPES) {
            if (recipe.matches(inputs)) {
                activeRecipe = recipe;
                break;
            }
        }

        if (activeRecipe != null) {
            blockEntity.maxProgress = activeRecipe.processTime;
            ItemStack recipeOutput = activeRecipe.output;

            // Check if we can output the product
            ItemStack currentOutput = blockEntity.items.get(3);
            boolean canOutput = false;
            if (currentOutput.isEmpty()) {
                canOutput = true;
            } else if (ItemStack.isSameItemSameComponents(currentOutput, recipeOutput)) {
                canOutput = currentOutput.getCount() + recipeOutput.getCount() <= currentOutput.getMaxStackSize();
            }

            if (canOutput) {
                blockEntity.progress++;
                if (blockEntity.progress >= blockEntity.maxProgress) {
                    // Consume inputs
                    blockEntity.consumeInputs(activeRecipe);
                    
                    // Put output
                    if (currentOutput.isEmpty()) {
                        blockEntity.items.set(3, recipeOutput.copy());
                    } else {
                        currentOutput.grow(recipeOutput.getCount());
                    }

                    blockEntity.progress = 0;
                    setChanged(level, pos, state);
                }
            } else {
                blockEntity.progress = Math.max(0, blockEntity.progress - 2);
            }
        } else {
            blockEntity.progress = 0;
        }
    }

    private void consumeInputs(PresserRecipe recipe) {
        // Safe shapeless ingredients consumption
        List<Ingredient> ingredientsToMatch = new ArrayList<>(recipe.ingredients);
        
        for (Ingredient ing : ingredientsToMatch) {
            for (int slot = 0; slot < 3; slot++) {
                ItemStack stack = this.items.get(slot);
                if (!stack.isEmpty() && ing.test(stack)) {
                    stack.shrink(1);
                    break;
                }
            }
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.items.clear();
        ContainerHelper.loadAllItems(tag, this.items, registries);
        this.progress = tag.getInt("Progress");
        this.maxProgress = tag.getInt("MaxProgress");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        ContainerHelper.saveAllItems(tag, this.items, registries);
        tag.putInt("Progress", this.progress);
        tag.putInt("MaxProgress", this.maxProgress);
    }

    // ==========================================
    // Container Interface Methods
    // ==========================================

    @Override
    public int getContainerSize() {
        return 4;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : this.items) {
            if (!stack.isEmpty()) return false;
        }
        return true;
    }

    @Override
    public ItemStack getItem(int slot) {
        return this.items.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        ItemStack result = ContainerHelper.removeItem(this.items, slot, amount);
        if (!result.isEmpty()) {
            setChanged();
        }
        return result;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(this.items, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        this.items.set(slot, stack);
        if (stack.getCount() > getMaxStackSize()) {
            stack.setCount(getMaxStackSize());
        }
        setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public void clearContent() {
        this.items.clear();
        setChanged();
    }

    // ==========================================
    // MenuProvider Interface Methods
    // ==========================================

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.korean_food.presser");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInv, Player player) {
        return new PresserMenu(id, playerInv, this, this.data);
    }
}
