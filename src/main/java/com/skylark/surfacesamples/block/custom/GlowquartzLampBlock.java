package com.skylark.surfacesamples.block.custom;
import com.google.common.collect.Sets;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import java.util.Set;

public class GlowquartzLampBlock extends RedstoneLampBlock {
    private boolean shouldSignal = true;
    public static final IntegerProperty POWER = BlockStateProperties.POWER;


    public GlowquartzLampBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(LIT, false)
                .setValue(POWER, 0));
    }

    private void updatePowerStrength(Level level, BlockPos pos, BlockState state) {
        int i = this.calculateTargetStrength(level, pos);
        if (state.getValue(POWER) != i) {
            if (level.getBlockState(pos) == state) {
                level.setBlock(pos, state.setValue(POWER, Integer.valueOf(i)), 2);
            }

            Set<BlockPos> set = Sets.newHashSet();
            set.add(pos);

            for (Direction direction : Direction.values()) {
                set.add(pos.relative(direction));
            }

            for (BlockPos blockpos : set) {
                level.updateNeighborsAt(blockpos, this);
            }
        }
    }

    private int calculateTargetStrength(Level level, BlockPos pos) {
        this.shouldSignal = false;
        int i = level.getBestNeighborSignal(pos);
        this.shouldSignal = true;
        int j = 0;
        if (i < 15) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                BlockPos blockpos = pos.relative(direction);
                BlockState blockstate = level.getBlockState(blockpos);
                j = Math.max(j, this.getWireSignal(blockstate));
                BlockPos blockpos1 = pos.above();
                if (blockstate.isRedstoneConductor(level, blockpos) && !level.getBlockState(blockpos1).isRedstoneConductor(level, blockpos1)) {
                    j = Math.max(j, this.getWireSignal(level.getBlockState(blockpos.above())));
                } else if (!blockstate.isRedstoneConductor(level, blockpos)) {
                    j = Math.max(j, this.getWireSignal(level.getBlockState(blockpos.below())));
                }
            }
        }

        return Math.max(i, j - 1);
    }

    @Override
    protected int getDirectSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return !this.shouldSignal ? 0 : blockState.getSignal(blockAccess, pos, side);
    }

    @Override
    protected int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        if (this.shouldSignal && side != Direction.DOWN) {
            int i = blockState.getValue(POWER);
            if (i == 0) {
                return 0;
            } else {
                return i;
            }
        } else {
            return 0;
        }
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!oldState.is(state.getBlock()) && !level.isClientSide) {
            this.updatePowerStrength(level, pos, state);

            for (Direction direction : Direction.Plane.VERTICAL) {
                level.updateNeighborsAt(pos.relative(direction), this);
            }

            this.updateNeighborsOfNeighboringWires(level, pos);
        }
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!isMoving && !state.is(newState.getBlock())) {
            super.onRemove(state, level, pos, newState, isMoving);
            if (!level.isClientSide) {
                for (Direction direction : Direction.values()) {
                    level.updateNeighborsAt(pos.relative(direction), this);
                }

                this.updatePowerStrength(level, pos, state);
                this.updateNeighborsOfNeighboringWires(level, pos);
            }
        }
    }

    private void updateNeighborsOfNeighboringWires(Level level, BlockPos pos) {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            this.checkCornerChangeAt(level, pos.relative(direction));
        }

        for (Direction direction1 : Direction.Plane.HORIZONTAL) {
            BlockPos blockpos = pos.relative(direction1);
            if (level.getBlockState(blockpos).isRedstoneConductor(level, blockpos)) {
                this.checkCornerChangeAt(level, blockpos.above());
            } else {
                this.checkCornerChangeAt(level, blockpos.below());
            }
        }
    }

    private void checkCornerChangeAt(Level level, BlockPos pos) {
        if (level.getBlockState(pos).is(this)) {
            level.updateNeighborsAt(pos, this);

            for (Direction direction : Direction.values()) {
                level.updateNeighborsAt(pos.relative(direction), this);
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT, POWER);
    }

    private int getWireSignal(BlockState state) {
        return state.is(this) ? state.getValue(POWER) : 0;
    }
}
