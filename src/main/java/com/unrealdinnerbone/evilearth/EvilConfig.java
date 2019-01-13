package com.unrealdinnerbone.evilearth;

import com.unrealdinnerbone.evilearth.blocks.BlockEvilEarth;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Config(modid = EvilEarth.MOD_ID)
@Mod.EventBusSubscriber(modid = EvilEarth.MOD_ID)
public class EvilConfig
{
    @Config.Comment("Names a mobs")
    public static String[] allowedSpawnMobs = {"minecraft:zombie", "minecraft:creeper", "minecraft:enderman"};

    @Config.Comment("The max amount of mobs that that can be around the evil dirt before it stop spawning mobs")
    public static int maxMobCount = 5;

    @SubscribeEvent
    public static void onConfigChaneEvent(ConfigChangedEvent configChangedEvent) {
        if(configChangedEvent.getModID().equalsIgnoreCase(EvilEarth.MOD_ID)) {
            ConfigManager.sync(EvilEarth.MOD_ID, Config.Type.INSTANCE);
            BlockEvilEarth.updateMobList();
        }
    }
}
