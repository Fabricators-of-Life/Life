package org.mythic_goose_studios.life.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

public class ConfigRegistery {
    public static LifeConfig CONFIG = new LifeConfig();

    public static void register() {
        AutoConfig.register(LifeConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(LifeConfig.class).getConfig();
    }
}
