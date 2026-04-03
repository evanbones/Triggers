package com.evandev.triggers;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.evandev.triggers.event.events.TriggerBlockEvent;
import com.evandev.triggers.event.events.TriggerEntityEvent;
import com.evandev.triggers.event.events.TriggerPlayerEvent;

public class TriggersForgeEventForwarder {

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
    public static void onItemPickup(EntityItemPickupEvent event) {
        Triggers.EVENTS.post(new TriggerEntityEvent.PickupItem(event.getEntity(), event.getItem().getItem()));
    }

    @SubscribeEvent
    public static void onEffectAdded(MobEffectEvent.Added event) {
        Triggers.EVENTS.post(new TriggerEntityEvent.EffectAdded(event.getEntity(), event.getEffectInstance()));
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Triggers.EVENTS.post(new TriggerPlayerEvent.Tick(event.player));
        }
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
