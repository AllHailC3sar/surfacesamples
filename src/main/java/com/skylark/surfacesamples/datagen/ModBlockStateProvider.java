package com.skylark.surfacesamples.datagen;

import com.skylark.surfacesamples.SurfaceSamples;
import com.skylark.surfacesamples.block.ModBlocks;
import com.skylark.surfacesamples.block.custom.SampleBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, SurfaceSamples.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
//        ModelFile sampleA = models().withExistingParent("sample_a", this.modLoc("block/ore_sample_a"));
//        ModelFile sampleB = models().withExistingParent("sample_b", this.modLoc("block/ore_sample_b"));
//        ModelFile sampleC = models().withExistingParent("sample_c", this.modLoc("block/ore_sample_c"));
//        ModelFile sampleD = models().withExistingParent("sample_d", this.modLoc("block/ore_sample_d"));

        blockWithItem(ModBlocks.CRYSTALLISED_REDSTONE_BLOCK);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("tutorialmod:block/" + deferredBlock.getId().getPath()));
    }
}
