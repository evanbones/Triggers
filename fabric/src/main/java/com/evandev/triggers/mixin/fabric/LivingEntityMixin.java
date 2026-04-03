package com.evandev.triggers.mixin.fabric;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import com.evandev.triggers.Triggers;
import com.evandev.triggers.event.events.TriggerEntityEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z", at = @At("RETURN"))
    private void onAddEffect(MobEffectInstance mobEffectInstance, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValueZ()) {
            Triggers.EVENTS.post(new TriggerEntityEvent.EffectAdded((LivingEntity) (Object) this, mobEffectInstance));
        }
    }
}
