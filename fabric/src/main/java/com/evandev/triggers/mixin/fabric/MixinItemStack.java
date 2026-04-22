package com.evandev.triggers.mixin.fabric;

import com.evandev.triggers.Triggers;
import com.evandev.triggers.event.events.TriggerPlayerEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public class MixinItemStack {
    @Inject(method = "onCraftedBy", at = @At("TAIL"))
    private void onCraftedBy(Level level, Player player, int i, CallbackInfo ci) {
        Triggers.EVENTS.post(new TriggerPlayerEvent.Craft(player, (ItemStack) (Object) this));
    }
}
