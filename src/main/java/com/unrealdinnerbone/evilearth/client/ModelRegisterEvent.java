package com.unrealdinnerbone.evilearth.client;

import com.unrealdinnerbone.evilearth.EvilBlocks;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ModelRegisterEvent
{
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event){
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(EvilBlocks.BLOCK_EVIL_EARTH), 0, new ModelResourceLocation(EvilBlocks.BLOCK_EVIL_EARTH.getRegistryName(), "inventory"));
    }
}
