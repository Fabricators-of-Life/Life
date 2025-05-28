package org.mythic_goose_studios.life.component;

import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;
import org.mythic_goose_studios.life.Life;

public class FoodStatComponentInit implements EntityComponentInitializer {

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        Life.LOGGER.info("=== STARTING COMPONENT REGISTRATION ===");

        try {
            registry.registerForPlayers(
                    FoodStatsComponent.KEY,
                    FoodStatsComponent::new,
                    RespawnCopyStrategy.ALWAYS_COPY
            );

            Life.LOGGER.info("=== COMPONENT REGISTRATION SUCCESSFUL ===");
        } catch (Exception e) {
            Life.LOGGER.error("=== COMPONENT REGISTRATION FAILED ===", e);
            throw e;
        }
    }
}