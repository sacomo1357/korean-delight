package club.someoneice.koreanfood.common;

import club.someoneice.koreanfood.KoreanFoodMain;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class PresserScreen extends AbstractContainerScreen<PresserMenu> {
    private static final ResourceLocation TEXTURE = ResourceLocation.parse(KoreanFoodMain.MODID + ":textures/gui/container/presser.png");

    public PresserScreen(PresserMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
        this.inventoryLabelY = this.imageHeight - 94; // Standard inventory label position
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        // Render main background sheet
        guiGraphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);

        // Render recipe book button (green book)
        int rx = x + 48;
        int ry = y + 48;
        int u = 0;
        int v = 168;
        if (mouseX >= rx && mouseX < rx + 20 && mouseY >= ry && mouseY < ry + 18) {
            v = 186; // Hovered state
        }
        guiGraphics.blit(TEXTURE, rx, ry, u, v, 20, 18);

        // Render animated progress arrow (pointing down)
        if (this.menu.isCrafting()) {
            int progress = this.menu.getPressProgressScaled(); // returns 0 to 22
            // Blit the progress overlay arrow from the texture sheet (u=176, v=4)
            // Empty arrow is visually at x + 122, y + 23.
            guiGraphics.blit(TEXTURE, x + 122, y + 23, 176, 4, 15, progress);
        }
    }
}
