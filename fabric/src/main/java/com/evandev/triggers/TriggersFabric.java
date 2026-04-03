package com.evandev.triggers;

import net.fabricmc.api.ModInitializer;

public class TriggersFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        CommonClass.init();
        TriggersFabricEventForwarder.init();
    }

}