package org.mythic_goose_studios.life.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "life")
@Config.Gui.Background("minecraft:textures/block/stone.png")
public class LifeConfig implements ConfigData {

    @ConfigEntry.Category("food_decay")
    @ConfigEntry.Gui.Tooltip(count = 3)
    public static float grainsDecay = 0.001f;

    @ConfigEntry.Category("food_decay")
    @ConfigEntry.Gui.Tooltip(count = 3)
    public static float proteinDecay = 0.0012f;

    @ConfigEntry.Category("food_decay")
    @ConfigEntry.Gui.Tooltip(count = 3)
    public static float dairyDecay = 0.0008f;

    @ConfigEntry.Category("food_decay")
    @ConfigEntry.Gui.Tooltip(count = 3)
    public static float fruitDecay = 0.0015f;

    @ConfigEntry.Category("food_decay")
    @ConfigEntry.Gui.Tooltip(count = 3)
    public static float vegetableDecay = 0.001f;

}
