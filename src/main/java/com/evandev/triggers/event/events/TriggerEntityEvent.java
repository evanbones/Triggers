package com.evandev.triggers.event.events;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public class TriggerEntityEvent extends TriggerEvent {
    public final LivingEntity entity;

    public TriggerEntityEvent(LivingEntity entity) {
        this.entity = entity;
    }

    public static class Breed extends TriggerEntityEvent {
        public final LivingEntity parentA;
        public final LivingEntity parentB;
        public final Player causedByPlayer;

        public Breed(LivingEntity entity, LivingEntity parentA, LivingEntity parentB, Player causedByPlayer) {
            super(entity);

            this.parentA = parentA;
            this.parentB = parentB;
            this.causedByPlayer = causedByPlayer;
        }
    }

    public static class Death extends TriggerEntityEvent {
        public final DamageSource damageSource;

        public Death(LivingEntity entity, DamageSource source) {
            super(entity);

            this.damageSource = source;
        }
    }

    public static class UseItem extends TriggerEntityEvent {
        public final ItemStack item;

        public UseItem(LivingEntity entity, ItemStack item) {
            super(entity);

            this.item = item;
        }
    }

    public static class TossItem extends TriggerEntityEvent {
        public final ItemStack item;

        public TossItem(LivingEntity entity, ItemStack item) {
            super(entity);

            this.item = item;
        }
    }

    public static class PickupItem extends TriggerEntityEvent {
        public final ItemStack item;

        public PickupItem(LivingEntity entity, ItemStack item) {
            super(entity);

            this.item = item;
        }
    }

    public static class EffectAdded extends TriggerEntityEvent {
        public final MobEffectInstance effect;

        public EffectAdded(LivingEntity entity, MobEffectInstance effect) {
            super(entity);

            this.effect = effect;
        }
    }

    public static class TameAnimal extends TriggerEntityEvent {
        @Nullable
        public final Player causedByPlayer;

        public TameAnimal(@Nullable Player player, Animal animal) {
            super(animal);
            this.causedByPlayer = player;
        }
    }
}
