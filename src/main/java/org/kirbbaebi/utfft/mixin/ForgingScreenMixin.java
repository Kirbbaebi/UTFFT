package org.kirbbaebi.utfft.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.ForgingScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.kirbbaebi.utfft.util.ChildMixinPropsGetter;
import org.kirbbaebi.utfft.util.GuiProps;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.CLIENT)
@Mixin(ForgingScreen.class)
public abstract class ForgingScreenMixin <T extends ForgingScreenHandler> extends HandledScreen<T> implements ScreenHandlerListener {

    @Shadow
    private final Identifier texture;

    public ForgingScreenMixin(T handler, PlayerInventory inventory, Text title, Identifier texture) {
        super(handler, inventory, title);
        this.texture = texture;
    }

    @Redirect(method = "drawBackground(Lnet/minecraft/client/gui/DrawContext;FII)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V"))
    private void inject(DrawContext context, Identifier texture, int x, int y, int u, int v, int width, int height) {
        GuiProps properties = null;
        if (this instanceof ChildMixinPropsGetter) {
            properties = ((ChildMixinPropsGetter) this).uTFFT$getProperties();
        }

        if (properties != null) {
            context.drawTexture(this.texture, this.x - properties.getXOffset(), this.y - properties.getYOffset(), 0, 0f, 0f, properties.getWidth(), properties.getHeight(), properties.getWidth(), properties.getHeight());
        } else {
            context.drawTexture(this.texture, this.x, this.y, 0, 0, 256, 256);
        }
    }
}
