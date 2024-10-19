package org.kirbbaebi.utfft.util;

import net.minecraft.util.Identifier;

public class GuiPropAPI {
    private static reloadlisten loader;

    public static void init(reloadlisten reloadlisten) {
        loader = reloadlisten;
    }

    public static GuiProps getProps(String id) {
        return loader.getGuiProps(id);
    }
}
