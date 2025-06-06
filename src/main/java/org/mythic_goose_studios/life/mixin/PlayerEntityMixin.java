package org.mythic_goose_studios.life.mixin;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.mythic_goose_studios.life.component.FoodStatsComponent;
import org.mythic_goose_studios.life.init.LifeItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Inject(method = "eatFood", at = @At("TAIL"))
    private void onEatFood(World world, ItemStack stack, FoodComponent foodComponent, CallbackInfoReturnable<ItemStack> cir) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        FoodStatsComponent foodStats = FoodStatsComponent.get(player);

        if (foodStats != null) {
            Item item = stack.getItem();

            // Add food values based on the item consumed
            // You can expand this with more detailed food categorization

            // Grains
            if (item == Items.BREAD || item == Items.WHEAT || item == Items.COOKIE) {
                foodStats.addGrains(20.0f);
            }

            // Protein
            if (item == Items.COOKED_BEEF || item == Items.COOKED_PORKCHOP ||
                    item == Items.COOKED_CHICKEN || item == Items.COOKED_MUTTON ||
                    item == Items.COOKED_RABBIT || item == Items.COOKED_COD ||
                    item == Items.COOKED_SALMON || item == Items.EGG) {
                foodStats.addProtein(25.0f);
            }

            // Fruits
            if (item == Items.APPLE || item == Items.GOLDEN_APPLE ||
                    item == Items.ENCHANTED_GOLDEN_APPLE || item == Items.MELON_SLICE ||
                    item == Items.SWEET_BERRIES || item == Items.GLOW_BERRIES) {
                foodStats.addFruit(15.0f);
            }

            // Vegetables
            if (item == Items.CARROT || item == Items.GOLDEN_CARROT || item == Items.POTATO ||
                    item == Items.BAKED_POTATO || item == Items.BEETROOT) {
                foodStats.addVegetables(18.0f);
                foodStats.removeFruit(0.6f);
            }

            // Dairy (Placeholder)
            if (item == LifeItems.PLACEHOLDER_CHEESE) {
                foodStats.addDairy(5f);
            }

            // Mixed foods (add to multiple categories)
            if (item == Items.PUMPKIN_PIE) {
                foodStats.addGrains(10.0f); // Crust
                foodStats.addVegetables(15.0f); // Pumpkin
            }
        }
    }
}
