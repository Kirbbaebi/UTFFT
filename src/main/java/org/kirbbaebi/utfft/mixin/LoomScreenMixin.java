package org.kirbbaebi.utfft.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.LoomScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.LoomScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.kirbbaebi.utfft.util.GuiPropAPI;
import org.kirbbaebi.utfft.util.GuiProps;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.CLIENT)
@Mixin(LoomScreen.class)
public abstract class LoomScreenMixin extends HandledScreen<LoomScreenHandler> {

    @Shadow
    static Identifier TEXTURE;

    @Unique
    GuiProps properties = GuiPropAPI.getProps("loom");


    public LoomScreenMixin(LoomScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Redirect(method = "drawBackground(Lnet/minecraft/client/gui/DrawContext;FII)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V"))
    private void inject(DrawContext context, Identifier texture, int x, int y, int u, int v, int width, int height) {
        if (properties != null) {
            context.drawTexture(TEXTURE, x - properties.getXOffset(), y - properties.getYOffset(), 0, 0f, 0f, properties.getWidth(), properties.getHeight(), properties.getWidth(), properties.getHeight());
        } else {
            context.drawTexture(TEXTURE, x, y, 0, 0, 256, 256);
        }
    }
}
