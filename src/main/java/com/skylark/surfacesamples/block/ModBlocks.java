package com.skylark.surfacesamples.block;

import com.skylark.surfacesamples.SurfaceSamples;
import com.skylark.surfacesamples.block.custom.ConcentratedRedstoneOre;
import com.skylark.surfacesamples.block.custom.SampleBlock;
import com.skylark.surfacesamples.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class ModBlocks {
    // function taken from the minecraft Blocks class to allow for lit/unlit blocks
    private static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
        return p_50763_ -> p_50763_.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }

    private static ToIntFunction<BlockState> poweredBlockEmission(int lightValue) {
        return p_50763_ -> p_50763_.getValue(BlockStateProperties.POWERED) ? lightValue : 0;
    }


    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(SurfaceSamples.MOD_ID);

    public static final DeferredBlock<Block> CRYSTALLISED_REDSTONE_BLOCK = registerBlock("crystallised_redstone_block",
            () -> new ConcentratedRedstoneOre(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.FIRE)
                    .lightLevel(poweredBlockEmission(9))
                    .strength(3.0F, 6.0F)
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> GLOWQUARTZ_BLOCK = registerBlock("glowquartz_block",
            () -> new RedstoneLampBlock(BlockBehaviour.Properties.of()
                    .lightLevel(litBlockEmission(9))
                    .strength(2.0F)
                    .sound(SoundType.AMETHYST)
                    .isValidSpawn(Blocks::always)
            ));

    public static final DeferredBlock<Block> POLISHED_GLOWQUARTZ_BLOCK = registerBlock("polished_glowquartz_block",
            () -> new RedstoneLampBlock(BlockBehaviour.Properties.of()
                    .lightLevel(litBlockEmission(15))
                    .strength(2.0F)
                    .sound(SoundType.AMETHYST)
                    .isValidSpawn(Blocks::always)
            ));

    public static final DeferredBlock<Block> GLOWQUARTZ_LAMP = registerBlock("glowquartz_lamp",
            () -> new RedstoneLampBlock(BlockBehaviour.Properties.of()
                    .lightLevel(litBlockEmission(15))
                    .strength(2.0F)
                    .sound(SoundType.GLASS)
                    .isValidSpawn(Blocks::always)
            ));

    public static final DeferredBlock<Block> TEST_SAMPLE = registerBlock("test_sample",
            () -> new SampleBlock(BlockBehaviour.Properties.of()
                    .strength(0.1f)
                    .sound(SoundType.TUFF)
                    .noOcclusion()
            ));
    public static final DeferredBlock<Block> COAL_SAMPLE = registerBlock("coal_ore_sample",
            () -> new SampleBlock(BlockBehaviour.Properties.of()
                    .strength(0.1f)
                    .sound(SoundType.TUFF)
                    .noOcclusion()
            ));
    public static final DeferredBlock<Block> COPPER_SAMPLE = registerBlock("copper_ore_sample",
            () -> new SampleBlock(BlockBehaviour.Properties.of()
                    .strength(0.1f)
                    .sound(SoundType.TUFF)
                    .noOcclusion()
//                    .noLootTable()
            ));
    public static final DeferredBlock<Block> DIAMOND_SAMPLE = registerBlock("diamond_ore_sample",
            () -> new SampleBlock(BlockBehaviour.Properties.of()
                    .strength(0.1f)
                    .sound(SoundType.TUFF)
                    .noOcclusion()
            ));
    public static final DeferredBlock<Block> EMERALD_SAMPLE = registerBlock("emerald_ore_sample",
            () -> new SampleBlock(BlockBehaviour.Properties.of()
                    .strength(0.1f)
                    .sound(SoundType.TUFF)
                    .noOcclusion()
            ));
    public static final DeferredBlock<Block> GOLD_SAMPLE = registerBlock("gold_ore_sample",
            () -> new SampleBlock(BlockBehaviour.Properties.of()
                    .strength(0.1f)
                    .sound(SoundType.TUFF)
                    .noOcclusion()
            ));
    public static final DeferredBlock<Block> IRON_SAMPLE = registerBlock("iron_ore_sample",
            () -> new SampleBlock(BlockBehaviour.Properties.of()
                    .strength(0.1f)
                    .sound(SoundType.TUFF)
                    .noOcclusion()
            ));
    public static final DeferredBlock<Block> LAPIS_SAMPLE = registerBlock("lapis_ore_sample",
            () -> new SampleBlock(BlockBehaviour.Properties.of()
                    .strength(0.1f)
                    .sound(SoundType.TUFF)
                    .noOcclusion()
            ));
    public static final DeferredBlock<Block> REDSTONE_SAMPLE = registerBlock("redstone_ore_sample",
            () -> new SampleBlock(BlockBehaviour.Properties.of()
                    .strength(0.1f)
                    .sound(SoundType.TUFF)
                    .noOcclusion()
            ));
    public static final DeferredBlock<Block> ANCIENT_DEBRIS_SAMPLE = registerBlock("ancient_debris_sample",
            () -> new SampleBlock(BlockBehaviour.Properties.of()
                    .strength(0.1f)
                    .sound(SoundType.NETHERRACK)
                    .noOcclusion()
            ));
    public static final DeferredBlock<Block> NETHER_GOLD_SAMPLE = registerBlock("nether_gold_ore_sample",
            () -> new SampleBlock(BlockBehaviour.Properties.of()
                    .strength(0.1f)
                    .sound(SoundType.NETHERRACK)
                    .noOcclusion()
            ));
    public static final DeferredBlock<Block> NETHER_QUARTZ_SAMPLE = registerBlock("nether_quartz_ore_sample",
            () -> new SampleBlock(BlockBehaviour.Properties.of()
                    .strength(0.1f)
                    .sound(SoundType.NETHERRACK)
                    .noOcclusion()
//                    .noLootTable()
            ));
    public static final DeferredBlock<Block> QUARTZ_SAMPLE = registerBlock("quartz_ore_sample",
            () -> new SampleBlock(BlockBehaviour.Properties.of()
                    .strength(0.1f)
                    .sound(SoundType.TUFF)
                    .noOcclusion()
//                    .noLootTable()
            ));
    public static final DeferredBlock<Block> ZINC_SAMPLE = registerBlock("zinc_ore_sample",
            () -> new SampleBlock(BlockBehaviour.Properties.of()
                    .strength(0.1f)
                    .sound(SoundType.TUFF)
                    .noOcclusion()
//                    .noLootTable()
            ));
    public static final DeferredBlock<Block> ANDESITE_SAMPLE = registerBlock("andesite_sample",
            () -> new SampleBlock(BlockBehaviour.Properties.of()
                    .strength(0.1f)
                    .sound(SoundType.TUFF)
                    .noOcclusion()
            ));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
