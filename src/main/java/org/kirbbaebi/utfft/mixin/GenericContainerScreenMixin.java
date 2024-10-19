package org.kirbbaebi.utfft.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.GenericContainerScreenHandler;
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
@Mixin(GenericContainerScreen.class)
public abstract class GenericContainerScreenMixin extends HandledScreen<GenericContainerScreenHandler> implements ScreenHandlerProvider<GenericContainerScreenHandler> {

    @Unique
    GuiProps properties = GuiPropAPI.getProps("generic_54");

    public GenericContainerScreenMixin(GenericContainerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Shadow
    static Identifier TEXTURE;

    @Shadow
    int rows;

    @Redirect(method = "drawBackground(Lnet/minecraft/client/gui/DrawContext;FII)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V"))
    private void inject(DrawContext context, Identifier texture, int x, int y, int u, int v, int width, int height) {
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        if (properties != null) {
            context.drawTexture(TEXTURE, i - properties.getXOffset(), j - properties.getYOffset(), 0, 0f, 0f, properties.getWidth(), (this.rows * 18 + 35), properties.getWidth(), properties.getHeight());
            context.drawTexture(TEXTURE, i - properties.getXOffset(), j - (properties.getYOffset() - 18) + this.rows * 18 + 17, 0, 0f, 144f, properties.getWidth(), properties.getHeight() - 144, properties.getWidth(), properties.getHeight());
        } else {
            context.drawTexture(TEXTURE, i, j, 0, 0, 256, this.rows * 18 + 17);
            context.drawTexture(TEXTURE, i, j + this.rows * 18 + 17, 0, 126, 256, 96);
        }
    }
}