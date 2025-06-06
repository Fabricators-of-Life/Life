package org.mythic_goose_studios.life.init;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class LifeItems {
    public static Item PLACEHOLDER_CHEESE;

    public static void init() {
        PLACEHOLDER_CHEESE = createItem("cheese", new Item(new Item.Settings().food(FoodComponents.POTATO)), "life");
    }

    private static Item createItem(String name, Item item, String id) {
        return Registry.register(Registries.ITEM, Identifier.of(id, name), item);
    }
}
