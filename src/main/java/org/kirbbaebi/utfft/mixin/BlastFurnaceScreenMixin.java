package org.kirbbaebi.utfft.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.BlastFurnaceScreen;
import org.kirbbaebi.utfft.util.ChildMixinPropsGetter;
import org.kirbbaebi.utfft.util.GuiPropAPI;
import org.kirbbaebi.utfft.util.GuiProps;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Environment(EnvType.CLIENT)
@Mixin(BlastFurnaceScreen.class)
public abstract class BlastFurnaceScreenMixin implements ChildMixinPropsGetter {

    @Unique
    GuiProps properties = GuiPropAPI.getProps("blast_furnace");

    @Override
    public GuiProps uTFFT$getProperties() {
        return this.properties;
    }
}
