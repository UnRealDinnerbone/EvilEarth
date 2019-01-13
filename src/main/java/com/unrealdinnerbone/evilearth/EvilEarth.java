package com.unrealdinnerbone.evilearth;

import com.unrealdinnerbone.evilearth.proxy.Proxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.apache.logging.log4j.Logger;

@Mod(modid = EvilEarth.MOD_ID, name = EvilEarth.MOD_NAME, version = EvilEarth.VERSION)
public class EvilEarth
{
    public static final String MOD_ID = "evilearth";
    public static final String MOD_NAME = "Evil Earth";
    public static final String VERSION = "@VERSION@";

    private static final String PROXY_BASE = "com.unrealdinnerbone.evilearth.proxy.";
    private static final String CLIENT_PROXY = PROXY_BASE + "ClientProxy";
    private static final String SERVER_PROXY = PROXY_BASE + "Proxy";

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = SERVER_PROXY)
    public static Proxy proxy;

    private static Logger logger;
    private static SimpleNetworkWrapper networkWrapper;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);
        logger = event.getModLog();
        proxy.onPreInit(event);
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        proxy.onInit(event);
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
        proxy.onPostInit(event);
    }

    @Mod.EventHandler
    public void onServerStart(FMLServerStartingEvent event) {
        proxy.onServerStart(event);
    }

    public static Logger getLogger() {
        return logger;
    }

    public static SimpleNetworkWrapper getNetworkWrapper() {
        return networkWrapper;
    }

    public static Proxy getProxy() {
        return proxy;
    }
}
