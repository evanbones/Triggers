package com.evandev.triggers;

import com.evandev.triggers.client.ClientConfigSetup;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;

@Mod(Constants.MOD_ID)
public class TriggersNeoForge {
    public TriggersNeoForge(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(TriggersNeoForgeEventForwarder.class);
        if (FMLEnvironment.dist.isClient()) {
            ClientConfigSetup.register(modContainer);
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(CommonClass::init);
    }
}