package club.someoneice.koreanfood.common;

import club.someoneice.koreanfood.init.BlockList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;

public class PresserMenu extends AbstractContainerMenu {
    private final Container container;
    private final ContainerData data;

    // Client-side constructor
    public PresserMenu(int containerId, Inventory playerInv, FriendlyByteBuf extraData) {
        this(containerId, playerInv, new SimpleContainer(4), new SimpleContainerData(2));
    }

    // Server-side and clean constructor
    public PresserMenu(int containerId, Inventory playerInv, Container container, ContainerData data) {
        super(BlockList.PRESSER_MENU.get(), containerId);
        checkContainerSize(container, 4);
        this.container = container;
        this.data = data;

        container.startOpen(playerInv.player);

        // Add 3 input slots (0, 1, 2)
        this.addSlot(new Slot(container, 0, 30, 25));
        this.addSlot(new Slot(container, 1, 48, 25));
        this.addSlot(new Slot(container, 2, 66, 25));

        // Add 1 output slot (3)
        this.addSlot(new Slot(container, 3, 122, 51) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });

        // Add standard player inventory slots (slots 4-30)
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        // Add standard player hotbar slots (slots 31-39)
        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInv, k, 8 + k * 18, 142));
        }

        // Track variables (progress, maxProgress)
        this.addDataSlots(data);
    }

    public boolean isCrafting() {
        return this.data.get(0) > 0;
    }

    public int getPressProgressScaled() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        return maxProgress != 0 && progress != 0 ? progress * 22 / maxProgress : 0; // exact 22 pixels arrow height
    }

    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index == 3) { // Output slot
                if (!this.moveItemStackTo(itemstack1, 4, 40, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(itemstack1, itemstack);
            } else if (index >= 0 && index <= 2) { // Input slots
                if (!this.moveItemStackTo(itemstack1, 4, 40, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 4 && index < 40) { // Player inventory
                // Try putting into inputs (slots 0-2). Slot 3 will reject it automatically via mayPlace()
                if (!this.moveItemStackTo(itemstack1, 0, 3, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }
        return itemstack;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.container.stopOpen(player);
    }
}
