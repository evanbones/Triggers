package com.evandev.triggers.mixin;

import net.minecraft.advancements.critereon.TameAnimalTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.animal.Animal;
import com.evandev.triggers.Triggers;
import com.evandev.triggers.event.events.TriggerEntityEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TameAnimalTrigger.class)
public class TameAnimalTriggerMixin {
    @Inject(method = "trigger(Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/world/entity/animal/Animal;)V", at = @At("HEAD"))
    public void onTamed(ServerPlayer player, Animal animal, CallbackInfo ci) {
        Triggers.EVENTS.post(new TriggerEntityEvent.TameAnimal(player, animal));
    }
}
