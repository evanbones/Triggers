package com.evandev.triggers;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.ItemStack;
import com.evandev.triggers.event.events.TriggerBlockEvent;
import com.evandev.triggers.event.events.TriggerEntityEvent;

public class TriggersFabricEventForwarder {
    public static void init() {
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, entity) -> Triggers.EVENTS.post(new TriggerBlockEvent.Break(state, pos, player)));

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (!player.isSpectator()) {
                Triggers.EVENTS.post(new TriggerBlockEvent.Place(world.getBlockState(hitResult.getBlockPos()), hitResult.getBlockPos(), player));
            }
            return InteractionResult.PASS;
        });

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (!player.isSpectator() && !world.isClientSide()) {
                Triggers.EVENTS.post(new TriggerBlockEvent.Interact(
                        world.getBlockState(hitResult.getBlockPos()),
                        hitResult.getBlockPos(),
                        player,
                        player.getItemInHand(hand)
                ));
            }
            return InteractionResult.PASS;
        });

        ServerLivingEntityEvents.AFTER_DEATH.register((entity, source) -> Triggers.EVENTS.post(new TriggerEntityEvent.Death(entity, source)));

        UseItemCallback.EVENT.register((player, world, hand) -> {
            if (!player.isSpectator()) {
                Triggers.EVENTS.post(new TriggerEntityEvent.UseItem(player, player.getItemInHand(hand)));
            }
            return InteractionResultHolder.pass(ItemStack.EMPTY);
        });
    }
}
