package org.mythic_goose_studios.life.api.v1.registers;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class SimpleRegistrar {
    protected static Item createItem(String name, Item item, String id) {
        return Registry.register(Registries.ITEM, Identifier.of(id, name), item);
    }

    protected static Block createBlock(String name, Block block, String id) {
        registerBlockItem(name, block, id);
        return Registry.register(Registries.BLOCK, Identifier.of(id, name), block);
    }

    private static void registerBlockItem(String name, Block block, String id) {
        Registry.register(Registries.ITEM, Identifier.of(id, name),
                new BlockItem(block, new Item.Settings()));
    }
}
