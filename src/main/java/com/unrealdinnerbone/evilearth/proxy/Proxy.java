package com.unrealdinnerbone.evilearth.proxy;

import com.unrealdinnerbone.evilearth.blocks.BlockEvilEarth;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class Proxy
{

    public void onPreInit(FMLPreInitializationEvent event) {
    }

    public void onInit(FMLInitializationEvent event) {

    }

    public void onPostInit(FMLPostInitializationEvent event) {
        BlockEvilEarth.updateMobList();
    }

    public void onServerStart(FMLServerStartingEvent event) {

    }



}
