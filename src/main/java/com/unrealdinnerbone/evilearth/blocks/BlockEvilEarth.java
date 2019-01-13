package com.unrealdinnerbone.evilearth.blocks;

import com.unrealdinnerbone.evilearth.EvilBlocks;
import com.unrealdinnerbone.evilearth.EvilConfig;
import com.unrealdinnerbone.evilearth.EvilEarth;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class BlockEvilEarth extends Block {

    public static final String NAME = "evilearth";

    public static List<Class<? extends Entity>> MOBS = new ArrayList<>();

    public static void updateMobList() {
        MOBS.clear();
        for (String allowedSpawnMob : EvilConfig.allowedSpawnMobs) {
            for(ResourceLocation resourceLocation: ForgeRegistries.ENTITIES.getKeys()) {
                if(resourceLocation.toString().equalsIgnoreCase(allowedSpawnMob)) {
                    MOBS.add(ForgeRegistries.ENTITIES.getValue(resourceLocation).getEntityClass());
                }
            }
        }
    }

    public BlockEvilEarth() {
        super(Material.GOURD, MapColor.BLACK);
        setUnlocalizedName(EvilEarth.MOD_ID + "." + NAME);
        setRegistryName(new ResourceLocation(EvilEarth.MOD_ID, NAME));
        setTickRandomly(true);
    }

    @Override
    public void randomTick(World world, BlockPos blockPos, IBlockState state, Random rand) {
        spawnRandomMob(world, blockPos, rand);
        tryDirtSpreed(world, blockPos, rand);
    }

    public static void tryDirtSpreed(World world, BlockPos blockPos, Random rand) {
        for (int i = 0; i < 4; ++i) {
            BlockPos spreadPos = blockPos.add(rand.nextInt(3) - 1, 0, rand.nextInt(3) - 1);
            if (spreadPos.getY() >= 0 && spreadPos.getY() < 256 && world.isBlockLoaded(spreadPos)) {
                IBlockState aboveSpreedBlockState = world.getBlockState(spreadPos.up());
                IBlockState spreedBlockState = world.getBlockState(spreadPos);

                int lightLevel = world.getLight(blockPos.up(1), true);
                if ((spreedBlockState.getBlock() == Blocks.DIRT || spreedBlockState.getBlock() == Blocks.GRASS) && aboveSpreedBlockState.getBlock() == Blocks.AIR && lightLevel <= 7) {
                    world.setBlockState(spreadPos, EvilBlocks.BLOCK_EVIL_EARTH.getDefaultState());
                }
            }
        }
    }



    public static void spawnRandomMob(World world, BlockPos blockPos, Random random) {
        if(world.getLight(blockPos.up(1), true) <= 7) {
            AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos).grow(3, 3, 3);
            int mobCount = world.getEntitiesWithinAABB(EntityLiving.class, axisAlignedBB, Objects::nonNull).size();
            if(mobCount <= EvilConfig.maxMobCount) {
                Class<? extends Entity> randomMobClass = getRnaomdMobClass(random);
                try {
                    Entity randomEntity = randomMobClass.getDeclaredConstructor(World.class).newInstance(world);
                    if(randomEntity instanceof EntityLiving) {
                        EntityLiving entityLiving = (EntityLiving) randomEntity;
                        randomEntity.setLocationAndAngles(blockPos.getX() + 0.5, blockPos.getY() + 1.5, blockPos.getZ() + 0.5, 0, 0);
                        //Todo apply effects
                        boolean isCo = entityLiving.isNotColliding();
                        boolean isSpawn = entityLiving.getCanSpawnHere() || entityLiving instanceof EntityAnimal;
                        if(isCo && isSpawn) {
                            world.spawnEntity(randomEntity);
                        }else {
                            entityLiving.setDead();
                        }
                    }
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public static Class<? extends Entity> getRnaomdMobClass(Random random) {
        return MOBS.get(random.nextInt(MOBS.size()));
    }
}