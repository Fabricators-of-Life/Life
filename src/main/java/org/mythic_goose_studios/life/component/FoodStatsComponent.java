package org.mythic_goose_studios.life.component;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.mythic_goose_studios.life.Life;

public class FoodStatsComponent implements AutoSyncedComponent {
    public static final ComponentKey<FoodStatsComponent> KEY =
            ComponentRegistry.getOrCreate(
                    Identifier.of(Life.MOD_ID, "food_stats"),
                    FoodStatsComponent.class
            );

    // Maximum values for each food group
    private static final int MAX_FOOD_VALUE = 100;

    // Decay rates (per tick) - adjust these values to control decay speed
    private static final float GRAINS_DECAY = 0.001f;
    private static final float PROTEIN_DECAY = 0.0012f;
    private static final float DAIRY_DECAY = 0.0008f;
    private static final float FRUIT_DECAY = 0.0015f;
    private static final float VEGETABLES_DECAY = 0.001f;

    // Current food values
    private float grains = MAX_FOOD_VALUE;
    private float protein = MAX_FOOD_VALUE;
    private float dairy = MAX_FOOD_VALUE;
    private float fruit = MAX_FOOD_VALUE;
    private float vegetables = MAX_FOOD_VALUE;

    private final PlayerEntity player;

    public FoodStatsComponent(PlayerEntity player) {
        this.player = player;
    }

    public static FoodStatsComponent get(PlayerEntity player) {
        return KEY.get(player);
    }

    public void tick() {
        // Decay food values over time
        grains = Math.max(0, grains - GRAINS_DECAY);
        protein = Math.max(0, protein - PROTEIN_DECAY);
        dairy = Math.max(0, dairy - DAIRY_DECAY);
        fruit = Math.max(0, fruit - FRUIT_DECAY);
        vegetables = Math.max(0, vegetables - VEGETABLES_DECAY);

        // Sync with client if on server
        KEY.sync(player);
    }

    // Getters
    public float getGrains() { return grains; }
    public float getProtein() { return protein; }
    public float getDairy() { return dairy; }
    public float getFruit() { return fruit; }
    public float getVegetables() { return vegetables; }

    public int getMaxFoodValue() { return MAX_FOOD_VALUE; }

    // Methods to add food values (call when player eats certain foods)
    public void addGrains(float amount) {
        grains = Math.min(MAX_FOOD_VALUE, grains + amount);
        KEY.sync(player);
    }

    public void addProtein(float amount) {
        protein = Math.min(MAX_FOOD_VALUE, protein + amount);
        KEY.sync(player);
    }

    public void addDairy(float amount) {
        dairy = Math.min(MAX_FOOD_VALUE, dairy + amount);
        KEY.sync(player);
    }

    public void addFruit(float amount) {
        fruit = Math.min(MAX_FOOD_VALUE, fruit + amount);
        KEY.sync(player);
    }

    public void addVegetables(float amount) {
        vegetables = Math.min(MAX_FOOD_VALUE, vegetables + amount);
        KEY.sync(player);
    }

    public void removeGrains(float amount) {
        grains = Math.max(0, grains - amount);
        KEY.sync(player);
    }

    public void removeProtein(float amount) {
        protein = Math.max(0, protein - amount);
        KEY.sync(player);
    }

    public void removeDairy(float amount) {
        dairy = Math.max(0, dairy - amount);
        KEY.sync(player);
    }

    public void removeFruit(float amount) {
        fruit = Math.max(0, fruit - amount);
        KEY.sync(player);
    }

    public void removeVegetables(float amount) {
        vegetables = Math.max(0, vegetables - amount);
        KEY.sync(player);
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup wrapperLookup) {
        grains = tag.getFloat("grains");
        protein = tag.getFloat("protein");
        dairy = tag.getFloat("dairy");
        fruit = tag.getFloat("fruit");
        vegetables = tag.getFloat("vegetables");
        Life.LOGGER.info("Loading Data");
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup wrapperLookup) {
        tag.putFloat("grains", grains);
        tag.putFloat("protein", protein);
        tag.putFloat("dairy", dairy);
        tag.putFloat("fruit", fruit);
        tag.putFloat("vegetables", vegetables);
        Life.LOGGER.info("Saving Data");
    }
}