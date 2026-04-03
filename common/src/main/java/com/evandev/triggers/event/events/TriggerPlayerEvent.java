package com.evandev.triggers.event.events;

import net.minecraft.stats.Stat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class TriggerPlayerEvent extends TriggerEvent {
    public final Player player;

    public TriggerPlayerEvent(Player player) {
        this.player = player;
    }

    public static class Enchant extends TriggerPlayerEvent {
        public final ItemStack item;
        public final int slot;

        public Enchant(Player player, ItemStack item, int slot) {
            super(player);

            this.item = item;
            this.slot = slot;
        }
    }

    public static class Tick extends TriggerPlayerEvent {
        public Tick(Player player) {
            super(player);
        }
    }

    public static class StatAward extends TriggerPlayerEvent {
        public final Stat<?> stat;
        public final int amount;

        public StatAward(Player player, Stat<?> stat, int amount) {
            super(player);
            this.stat = stat;
            this.amount = amount;
        }
    }

    public static class Craft extends TriggerPlayerEvent {
        public final ItemStack outputItem;

        public Craft(Player player, ItemStack outputItem) {
            super(player);
            this.outputItem = outputItem;
        }
    }
}
