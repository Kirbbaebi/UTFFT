package org.kirbbaebi.utfft.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.SmokerScreen;
import org.kirbbaebi.utfft.util.ChildMixinPropsGetter;
import org.kirbbaebi.utfft.util.GuiPropAPI;
import org.kirbbaebi.utfft.util.GuiProps;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Environment(EnvType.CLIENT)
@Mixin(SmokerScreen.class)
public abstract class SmokerScreenMixin implements ChildMixinPropsGetter {

    @Unique
    GuiProps properties = GuiPropAPI.getProps("furnace");

    @Override
    public GuiProps uTFFT$getProperties() {
        return this.properties;
    }
}
