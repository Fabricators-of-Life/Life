package org.mythic_goose_studios.life;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.server.network.ServerPlayerEntity;
import org.lwjgl.glfw.GLFW;
import org.mythic_goose_studios.life.component.FoodStatsComponent;
import org.mythic_goose_studios.life.config.ConfigRegistery;
import org.mythic_goose_studios.life.init.LifeItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Life implements ModInitializer {
	public static final String MOD_ID = "life";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);



	@Override
	public void onInitialize() {
		ConfigRegistery.register();
		LifeItems.init();

		ServerTickEvents.END_SERVER_TICK.register(server -> {
			for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
				FoodStatsComponent.get(player).tick();
			}
		});
	}
}
