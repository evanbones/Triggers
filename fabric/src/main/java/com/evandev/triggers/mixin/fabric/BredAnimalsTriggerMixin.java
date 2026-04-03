package com.evandev.triggers.mixin.fabric;

import net.minecraft.advancements.critereon.BredAnimalsTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.animal.Animal;
import com.evandev.triggers.Triggers;
import com.evandev.triggers.event.events.TriggerEntityEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BredAnimalsTrigger.class)
public class BredAnimalsTriggerMixin {
    @Inject(method = "trigger", at = @At("HEAD"))
    private void onBredAnimalsTrigger(ServerPlayer serverPlayer, Animal parentA, Animal parentB, AgeableMob child, CallbackInfo ci) {
        Triggers.EVENTS.post(new TriggerEntityEvent.Breed(child, parentA, parentB, serverPlayer));
    }
}
