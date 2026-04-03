package com.evandev.triggers.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stat;
import com.evandev.triggers.Triggers;
import com.evandev.triggers.event.events.TriggerPlayerEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {
    @Inject(method = "awardStat", at = @At("HEAD"))
    private void onAwardStat(Stat<?> stat, int amount, CallbackInfo ci) {
        Triggers.EVENTS.post(new TriggerPlayerEvent.StatAward((ServerPlayer) (Object) this, stat, amount));
    }
}
