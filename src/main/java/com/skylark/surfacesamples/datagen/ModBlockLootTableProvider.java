package com.skylark.surfacesamples.datagen;

import com.skylark.surfacesamples.block.ModBlocks;
import com.skylark.surfacesamples.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.common.data.internal.NeoForgeItemTagsProvider;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        add(ModBlocks.GLOWQUARTZ_BLOCK.get(),
                block -> createMultipleOreDrops(ModBlocks.GLOWQUARTZ_BLOCK.get(), ModItems.GLOWQUARTZ_SHARD.get(), 4, 9));
        dropSelf(ModBlocks.GLOWQUARTZ_LAMP.get());
        dropSelf(ModBlocks.POLISHED_GLOWQUARTZ_BLOCK.get());

        add(ModBlocks.CRYSTALLISED_REDSTONE_BLOCK.get(),
                block -> createMultipleOreDrops(ModBlocks.CRYSTALLISED_REDSTONE_BLOCK.get(), ModItems.REDSTONE_SHARD.get(), 7, 9));

//        add(ModBlocks.TEST_SAMPLE.get(),
//                block -> createMultipleOreDrops(ModBlocks.TEST_SAMPLE.get(), Items.IRON_NUGGET, 0, 3));
        add(ModBlocks.COAL_SAMPLE.get(),
                block -> createMultipleOreDrops(ModBlocks.COAL_SAMPLE.get(), Items.COAL, 1, 1));
        add(ModBlocks.DIAMOND_SAMPLE.get(),
                block -> createMultipleOreDrops(ModBlocks.DIAMOND_SAMPLE.get(), Items.DIAMOND, 0, 1));
        add(ModBlocks.EMERALD_SAMPLE.get(),
                block -> createMultipleOreDrops(ModBlocks.EMERALD_SAMPLE.get(), Items.EMERALD, 0, 1));
        add(ModBlocks.GOLD_SAMPLE.get(),
                block -> createMultipleOreDrops(ModBlocks.GOLD_SAMPLE.get(), Items.GOLD_NUGGET, 0, 3));
        add(ModBlocks.IRON_SAMPLE.get(),
                block -> createMultipleOreDrops(ModBlocks.IRON_SAMPLE.get(), Items.IRON_NUGGET, 0, 4));
        add(ModBlocks.LAPIS_SAMPLE.get(),
                block -> createMultipleOreDrops(ModBlocks.LAPIS_SAMPLE.get(), Items.LAPIS_LAZULI, 0, 2));
        add(ModBlocks.REDSTONE_SAMPLE.get(),
                block -> createMultipleOreDrops(ModBlocks.REDSTONE_SAMPLE.get(), Items.REDSTONE, 0, 5));
        add(ModBlocks.ANCIENT_DEBRIS_SAMPLE.get(),
                block -> createMultipleOreDrops(ModBlocks.ANCIENT_DEBRIS_SAMPLE.get(), Items.NETHERITE_SCRAP, 0, 1));
        add(ModBlocks.NETHER_GOLD_SAMPLE.get(),
                block -> createMultipleOreDrops(ModBlocks.NETHER_GOLD_SAMPLE.get(), Items.GOLD_NUGGET, 0, 7));
        add(ModBlocks.ANDESITE_SAMPLE.get(),
                block -> createMultipleOreDrops(ModBlocks.ANDESITE_SAMPLE.get(), ModItems.ANDESITE_CHUNK.get(), 1, 3));
        add(ModBlocks.FLINT_SAMPLE.get(),
                block -> createMultipleOreDrops(ModBlocks.FLINT_SAMPLE.get(), Items.FLINT, 1, 1));
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
