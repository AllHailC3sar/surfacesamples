package com.skylark.surfacesamples.block.custom;

import com.skylark.surfacesamples.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;


public class SampleBlock extends Block implements SimpleWaterloggedBlock {

        public enum SampleFlavour implements StringRepresentable {
            UNKNOWN("unknown"),
            STONY("stony"),
            SANDY("sandy"),
            RED_SANDY("red_sandy"),
            SNOWY("snowy");

            private final String name;

            private SampleFlavour(String p_156018_) {
                this.name = p_156018_;
            }

            public String toString() {
                return this.name;
            }

            public String getSerializedName() {
                return this.name;
            }
        }

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<SampleFlavour> SAMPLEFLAVOUR = EnumProperty.create("flavour", SampleFlavour.class);
    protected static final VoxelShape AABB = Shapes.or(Block.box(1.0, 0.0, 1.0, 13.0, 3.0, 13.0));

    public SampleBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.FALSE).setValue(SAMPLEFLAVOUR, SampleFlavour.STONY));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!player.isCrouching()) {
            level.destroyBlock(pos, true);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, SAMPLEFLAVOUR);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return AABB;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = this.defaultBlockState();
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        boolean shouldBeWaterlogged = Boolean.valueOf(Boolean.FALSE); // this is only here because I do not want to make a waterlogged model variant for all 90+ variations
        if (context.getLevel().getBlockState(context.getClickedPos()).getBlock() == Blocks.WATER) {
            shouldBeWaterlogged = Boolean.valueOf(fluidstate.getType() == Fluids.WATER);
        }

        if (context.getLevel().getBlockState(context.getClickedPos().below()).getBlock() == Blocks.SAND || context.getLevel().getBlockState(context.getClickedPos().below()).getBlock() == Blocks.SANDSTONE || context.getLevel().getBlockState(context.getClickedPos().below()).getBlock() == Blocks.SUSPICIOUS_SAND) {
            return blockstate.setValue(SAMPLEFLAVOUR, SampleFlavour.SANDY).setValue(WATERLOGGED, Boolean.valueOf(shouldBeWaterlogged));
        } else if (context.getLevel().getBlockState(context.getClickedPos().below()).getBlock() == Blocks.RED_SAND || context.getLevel().getBlockState(context.getClickedPos().below()).getBlock() == Blocks.RED_SANDSTONE || context.getLevel().getBlockState(context.getClickedPos().below()).is(BlockTags.BADLANDS_TERRACOTTA)) {
            return blockstate.setValue(SAMPLEFLAVOUR, SampleFlavour.RED_SANDY).setValue(WATERLOGGED, Boolean.valueOf(shouldBeWaterlogged));
        }
        return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(shouldBeWaterlogged));
    }


    private static boolean isSnowySetting(BlockState state) {
        return state.is(BlockTags.SNOW);
    }

    private BlockState flavourGetter (BlockState belowState, BlockState state) {
        if (belowState.is(Blocks.SAND) || belowState.is(Blocks.SANDSTONE) || belowState.is(Blocks.SUSPICIOUS_SAND)) {
            return state.setValue(SAMPLEFLAVOUR, SampleFlavour.SANDY);
        } else if (belowState.is(Blocks.RED_SAND) || belowState.is(Blocks.RED_SANDSTONE) || belowState.is(BlockTags.BADLANDS_TERRACOTTA)) {
            return state.setValue(SAMPLEFLAVOUR, SampleFlavour.RED_SANDY);
        }
        return this.defaultBlockState();
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState facingState, LevelAccessor level, BlockPos pos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        if (!canSurvive(state, level, pos)){
            return Blocks.AIR.defaultBlockState();
        }

        return direction == Direction.DOWN
                ? flavourGetter(facingState, state) // technically this feels like poor form but I couldn't figure out how to do it otherwise
                : super.updateShape(state, direction, facingState, level, pos, facingPos);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(SAMPLEFLAVOUR) == SampleFlavour.UNKNOWN) {
            if (level.getBlockState(pos.below()).getBlock() == Blocks.RED_SAND || level.getBlockState(pos.below()).getBlock() == Blocks.RED_SANDSTONE || level.getBlockState(pos.below()).getTags().toList().contains(BlockTags.BADLANDS_TERRACOTTA)) {
                level.setBlock(pos, state.setValue(SAMPLEFLAVOUR, SampleFlavour.RED_SANDY), 2);
            } else if (level.getBlockState(pos.below()).getBlock() == Blocks.SAND || level.getBlockState(pos.below()).getBlock() == Blocks.SANDSTONE || level.getBlockState(pos.below()).getBlock() == Blocks.SUSPICIOUS_SAND) {
                level.setBlock(pos, state.setValue(SAMPLEFLAVOUR, SampleFlavour.SANDY), 2);
            } else {
                level.setBlock(pos, state.setValue(SAMPLEFLAVOUR, SampleFlavour.STONY), 2);
                super.tick(state, level, pos, random);
            }
        }
    }



    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        level.scheduleTick(pos, this, 0);
        super.onPlace(state, level, pos, oldState, movedByPiston);
    }

    @Override
    protected FluidState getFluidState(BlockState state){
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return !level.isEmptyBlock(pos.below());
    }
}


