package org.kirbbaebi.utfft.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourceType;
import org.kirbbaebi.utfft.util.GuiPropAPI;
import org.kirbbaebi.utfft.util.reloadlisten;

public class UTFFTClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */

    private static final reloadlisten LOADER = new reloadlisten();

    @Override
    public void onInitializeClient() {
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(LOADER);
        GuiPropAPI.init(LOADER);
    }
}
