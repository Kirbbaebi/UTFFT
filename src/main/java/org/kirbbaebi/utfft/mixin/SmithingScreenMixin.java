package org.kirbbaebi.utfft.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.SmithingScreen;
import org.kirbbaebi.utfft.util.ChildMixinPropsGetter;
import org.kirbbaebi.utfft.util.GuiPropAPI;
import org.kirbbaebi.utfft.util.GuiProps;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Environment(EnvType.CLIENT)
@Mixin(SmithingScreen.class)
public abstract class SmithingScreenMixin implements ChildMixinPropsGetter {

    @Unique
    GuiProps properties = GuiPropAPI.getProps("smithing");

    @Override
    public GuiProps uTFFT$getProperties() {
        return this.properties;
    }
}
