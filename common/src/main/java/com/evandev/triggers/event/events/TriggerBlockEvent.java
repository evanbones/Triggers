package com.evandev.triggers.event.events;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class TriggerBlockEvent extends TriggerEvent {
    public final BlockState state;
    public final BlockPos pos;
    @Nullable
    public final LivingEntity entity;

    public TriggerBlockEvent(BlockState state, BlockPos pos, @Nullable LivingEntity entity) {
        this.state = state;
        this.pos = pos;
        this.entity = entity;
    }

    public static class Place extends TriggerBlockEvent {
        public Place(BlockState state, BlockPos pos, @Nullable LivingEntity entity) {
            super(state, pos, entity);
        }
    }

    public static class Break extends TriggerBlockEvent {
        public Break(BlockState state, BlockPos pos, @Nullable LivingEntity entity) {
            super(state, pos, entity);
        }
    }

    public static class FarmlandTrample extends TriggerBlockEvent {
        public FarmlandTrample(BlockState state, BlockPos pos, @Nullable LivingEntity entity) {
            super(state, pos, entity);
        }
    }

    public static class Interact extends TriggerBlockEvent {
        public final ItemStack itemStack;

        public Interact(BlockState state, BlockPos pos, @Nullable LivingEntity entity, ItemStack itemStack) {
            super(state, pos, entity);
            this.itemStack = itemStack;
        }
    }
}
