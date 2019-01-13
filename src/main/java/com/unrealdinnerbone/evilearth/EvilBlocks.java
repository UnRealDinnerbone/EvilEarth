package com.unrealdinnerbone.evilearth;

import com.unrealdinnerbone.evilearth.blocks.BlockEvilEarth;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
@GameRegistry.ObjectHolder(EvilEarth.MOD_ID)
public class EvilBlocks
{
    @GameRegistry.ObjectHolder(BlockEvilEarth.NAME)
    public static BlockEvilEarth BLOCK_EVIL_EARTH;

    @SubscribeEvent
    public static void onBlockRegisterEvent(RegistryEvent.Register<Block> blockRegisterEvent) {
        blockRegisterEvent.getRegistry().register(new BlockEvilEarth());
    }

    @SubscribeEvent
    public static void onItemRegisterEvent(RegistryEvent.Register<Item> blockRegisterEvent) {
        blockRegisterEvent.getRegistry().register(new ItemBlock(BLOCK_EVIL_EARTH).setRegistryName(BLOCK_EVIL_EARTH.getRegistryName()));
    }
}
