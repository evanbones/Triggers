package com.evandev.triggers;

import com.evandev.triggers.event.events.TriggerBlockEvent;
import com.evandev.triggers.event.events.TriggerEntityEvent;
import com.evandev.triggers.event.events.TriggerPlayerEvent;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.item.ItemTossEvent;
import net.neoforged.neoforge.event.entity.living.BabyEntitySpawnEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class TriggersNeoForgeEventForwarder {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Triggers.EVENTS.post(new TriggerBlockEvent.Break(event.getState(), event.getPos(), event.getPlayer()));
    }

    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
        if (event.getEntity() instanceof LivingEntity entity) {
            Triggers.EVENTS.post(new TriggerBlockEvent.Place(event.getState(), event.getPos(), entity));
        }
    }

    @SubscribeEvent
    public static void onBlockInteract(PlayerInteractEvent.RightClickBlock event) {
        if (!event.getLevel().isClientSide) {
            Triggers.EVENTS.post(new TriggerBlockEvent.Interact(event.getLevel().getBlockState(event.getPos()), event.getPos(), event.getEntity(), event.getItemStack()));
        }
    }

    @SubscribeEvent
    public static void onFarmlandTrample(BlockEvent.FarmlandTrampleEvent event) {
        if (event.getEntity() instanceof LivingEntity entity) {
            Triggers.EVENTS.post(new TriggerBlockEvent.FarmlandTrample(event.getState(), event.getPos(), entity));
        }
    }

    @SubscribeEvent
    public static void onEntityBreed(BabyEntitySpawnEvent event) {
        Triggers.EVENTS.post(new TriggerEntityEvent.Breed(event.getChild(), event.getParentA(), event.getParentB(), event.getCausedByPlayer()));
    }

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        Triggers.EVENTS.post(new TriggerEntityEvent.Death(event.getEntity(), event.getSource()));
    }

    @SubscribeEvent
    public static void onItemUse(LivingEntityUseItemEvent.Finish event) {
        Triggers.EVENTS.post(new TriggerEntityEvent.UseItem(event.getEntity(), event.getItem()));
    }

    @SubscribeEvent
    public static void onItemToss(ItemTossEvent event) {
        Triggers.EVENTS.post(new TriggerEntityEvent.TossItem(event.getPlayer(), event.getEntity().getItem()));
    }

    @SubscribeEvent
    public static void onItemPickup(ItemEntityPickupEvent.Pre event) {
        Triggers.EVENTS.post(new TriggerEntityEvent.PickupItem(event.getPlayer(), event.getItemEntity().getItem()));
    }

    @SubscribeEvent
    public static void onEffectAdded(MobEffectEvent.Added event) {
        Triggers.EVENTS.post(new TriggerEntityEvent.EffectAdded(event.getEntity(), event.getEffectInstance()));
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Triggers.EVENTS.post(new TriggerPlayerEvent.Tick(event.getEntity()));
    }

    @SubscribeEvent
    public static void onPlayerCraft(PlayerEvent.ItemCraftedEvent event) {
        Triggers.EVENTS.post(new TriggerPlayerEvent.Craft(event.getEntity(), event.getCrafting()));
    }

    @SubscribeEvent
    public static void onPlayerSmelt(PlayerEvent.ItemSmeltedEvent event) {
        Triggers.EVENTS.post(new TriggerPlayerEvent.Craft(event.getEntity(), event.getSmelting()));
    }
}
