package org.kirbbaebi.utfft.util;

import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.kirbbaebi.utfft.UTFFT;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class reloadlisten implements SimpleSynchronousResourceReloadListener {

    private final Map<String, GuiProps> guiPropsMap = new HashMap<>();

    @Override
    public Identifier getFabricId() {
        return Identifier.of(UTFFT.modID, "resource_reload");
    }

    @Override
    public void reload(ResourceManager manager) {
        guiPropsMap.clear();

        for (ResourcePack pack : MinecraftClient.getInstance().getResourceManager().streamResourcePacks().toList()) {
            pack.findResources(ResourceType.CLIENT_RESOURCES, "utfft", "gui", (identifier, inputStreamInputSupplier) -> {
                try (InputStream inputStream = inputStreamInputSupplier.get()) {
                    Properties properties = new Properties();
                    properties.load(inputStream);
                    String noGUI = identifier.getPath().substring(4);
                    String Final = noGUI.substring(0, noGUI.length() - 11);
                    guiPropsMap.put(Final, new GuiProps(properties));
                    System.out.println();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public GuiProps getGuiProps(String id) {
        return guiPropsMap.get(id);
    }
}
