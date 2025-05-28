package org.mythic_goose_studios.life.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import org.mythic_goose_studios.life.Life;
import org.mythic_goose_studios.life.LifeClient;
import org.mythic_goose_studios.life.component.FoodStatsComponent;

public class FoodStatsScreen extends Screen {
    private static final Identifier BACKGROUND_TEXTURE = Identifier.of(Life.MOD_ID, "textures/gui/food_stats_background.png");
    private static final Identifier PROGRESS_BAR_TEXTURE = Identifier.of(Life.MOD_ID, "textures/gui/progress_bars.png");

    // Icon textures - you'll need to create these
    private static final Identifier GRAINS_ICON = Identifier.of(Life.MOD_ID, "textures/gui/icons/grains.png");
    private static final Identifier PROTEIN_ICON = Identifier.of(Life.MOD_ID, "textures/gui/icons/protein.png");
    private static final Identifier DAIRY_ICON = Identifier.of(Life.MOD_ID, "textures/gui/icons/dairy.png");
    private static final Identifier FRUIT_ICON = Identifier.of(Life.MOD_ID, "textures/gui/icons/fruit.png");
    private static final Identifier VEGETABLES_ICON = Identifier.of(Life.MOD_ID, "textures/gui/icons/vegetables.png");

    private static final int GUI_WIDTH = 200;
    private static final int GUI_HEIGHT = 150;
    private static final int PROGRESS_BAR_WIDTH = 120;
    private static final int PROGRESS_BAR_HEIGHT = 8;
    private static final int ICON_SIZE = 16;

    public FoodStatsScreen() {
        super(Text.translatable("gui.life.title"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {

        // Calculate GUI position (centered)
        int x = (this.width - GUI_WIDTH) / 2;
        int y = (this.height - GUI_HEIGHT) / 2;

        // Draw background panel
        context.fill(x, y, x + GUI_WIDTH, y + GUI_HEIGHT, 0xC0000000); // Semi-transparent black
        context.drawBorder(x, y, GUI_WIDTH, GUI_HEIGHT, 0xFFFFFF); // White border

        // Draw title
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, y + 10, 0xFFFFFF);

        // Get food stats
        FoodStatsComponent foodStats = null;
        if (MinecraftClient.getInstance().player != null) {
            foodStats = FoodStatsComponent.get(MinecraftClient.getInstance().player);
        }

        if (foodStats != null) {
            int startY = y + 30;
            int spacing = 22;

            // Draw each food stat with icon and progress bar
            drawFoodStat(context, x + 10, startY, GRAINS_ICON, "Grains",
                    foodStats.getGrains(), foodStats.getMaxFoodValue(), 0xFFD2691E); // Orange

            drawFoodStat(context, x + 10, startY + spacing, PROTEIN_ICON, "Protein",
                    foodStats.getProtein(), foodStats.getMaxFoodValue(), 0xFFDC143C); // Red

            drawFoodStat(context, x + 10, startY + spacing * 2, DAIRY_ICON, "Dairy",
                    foodStats.getDairy(), foodStats.getMaxFoodValue(), 0xFFFFFFE0); // Light yellow

            drawFoodStat(context, x + 10, startY + spacing * 3, FRUIT_ICON, "Fruit",
                    foodStats.getFruit(), foodStats.getMaxFoodValue(), 0xFFFF69B4); // Pink

            drawFoodStat(context, x + 10, startY + spacing * 4, VEGETABLES_ICON, "Vegetables",
                    foodStats.getVegetables(), foodStats.getMaxFoodValue(), 0xFF32CD32); // Green
        }

        super.render(context, mouseX, mouseY, delta);
    }

    private void drawFoodStat(DrawContext context, int x, int y, Identifier iconTexture,
                              String name, float current, float max, int color) {
        // Draw icon (with fallback if texture doesn't exist)
        try {
            context.drawTexture(iconTexture, x, y, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);
        } catch (Exception e) {
            // Fallback: draw colored square
            context.fill(x, y, x + ICON_SIZE, y + ICON_SIZE, color);
        }

        // Draw label
        context.drawTextWithShadow(this.textRenderer, name, x + ICON_SIZE + 5, y + 4, 0xFFFFFF);

        // Draw progress bar background
        int barX = x + ICON_SIZE + 5;
        int barY = y + 14;
        context.fill(barX, barY, barX + PROGRESS_BAR_WIDTH, barY + PROGRESS_BAR_HEIGHT, 0xFF333333);

        // Draw progress bar fill
        float progress = current / max;
        int fillWidth = (int) (PROGRESS_BAR_WIDTH * progress);
        context.fill(barX, barY, barX + fillWidth, barY + PROGRESS_BAR_HEIGHT, color);

        // Draw progress bar border
        context.drawBorder(barX, barY, PROGRESS_BAR_WIDTH, PROGRESS_BAR_HEIGHT, 0xC0000000);

        // Draw percentage text
        String percentage = String.format("%.0f%%", progress * 100);
        int textX = barX + PROGRESS_BAR_WIDTH + 5;
        context.drawTextWithShadow(this.textRenderer, percentage, textX, y + 4, 0xFFFFFF);
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        // Override to prevent any background rendering
    }

    @Override
    public boolean shouldPause() {
        return false; // Don't pause the game when this GUI is open
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        // Close GUI when escape is pressed or the keybind is pressed again
        if (keyCode == 256 || LifeClient.openFoodStatsKey.matchesKey(keyCode, scanCode)) { // 256 = ESC
            this.close();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}