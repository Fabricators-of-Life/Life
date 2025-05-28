package org.mythic_goose_studios.life;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.mythic_goose_studios.life.component.FoodStatsComponent;
import org.mythic_goose_studios.life.gui.FoodStatsScreen;

public class LifeClient implements ClientModInitializer {
    // Keybinding for opening the GUI
    public static KeyBinding openFoodStatsKey;

    @Override
    public void onInitializeClient() {
        // Register client tick event for keybinding
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openFoodStatsKey.wasPressed()) {
                if (client.player != null) {
                    client.setScreen(new FoodStatsScreen());
                }
            }
        });

        // Register tick event for food stats decay
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null && client.world != null) {
                FoodStatsComponent component = FoodStatsComponent.get(client.player);
                component.tick();
            }
        });

        // Register keybinding (client-side only)
        openFoodStatsKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.life.open_gui",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G, // Default to 'G' key
                "category.life.general"
        ));

        Life.LOGGER.info("Life client initialized successfully!");
    }
    }
