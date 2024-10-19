package org.kirbbaebi.utfft.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookProvider;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PlayerScreenHandler;
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
@Mixin(InventoryScreen.class)
public abstract class InventoryScreenMixin extends AbstractInventoryScreen<PlayerScreenHandler> implements RecipeBookProvider {

    public InventoryScreenMixin(PlayerScreenHandler screenHandler, PlayerInventory playerInventory, Text text) {
        super(screenHandler, playerInventory, text);
    }

    @Unique
    GuiProps properties = GuiPropAPI.getProps("inventory");

    @Redirect(method = "drawBackground(Lnet/minecraft/client/gui/DrawContext;FII)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V"))
    private void inject(DrawContext context, Identifier texture, int x, int y, int u, int v, int width, int height) {
        if (properties != null) {
            context.drawTexture(BACKGROUND_TEXTURE, x - properties.getXOffset(), y - properties.getYOffset(), 0, 0f, 0f, properties.getWidth(), properties.getHeight(), properties.getWidth(), properties.getHeight());
        } else {
            context.drawTexture(BACKGROUND_TEXTURE, x, y, 0, 0, 256, 256);
        }
    }
}
