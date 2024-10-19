package org.kirbbaebi.utfft.util;

import java.util.Properties;

public class GuiProps {
    private final Properties properties;

    public GuiProps(Properties properties) {
        this.properties = properties;
    }

    public int getWidth() { return Integer.parseInt(String.valueOf(properties.getOrDefault("width", 256))); }

    public int getHeight() { return Integer.parseInt(String.valueOf(properties.getOrDefault("height", 256))); }

    public int getXOffset() { return Integer.parseInt(String.valueOf(properties.getOrDefault("x_offset", 0))); }

    public int getYOffset() { return Integer.parseInt(String.valueOf(properties.getOrDefault("y_offset", 0))); }
}