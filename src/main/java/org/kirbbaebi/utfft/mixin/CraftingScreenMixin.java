package org.kirbbaebi.utfft.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CraftingScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookProvider;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.CraftingScreenHandler;
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
@Mixin(CraftingScreen.class)
public abstract class CraftingScreenMixin extends HandledScreen<CraftingScreenHandler> implements RecipeBookProvider {

    public CraftingScreenMixin(CraftingScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Shadow
    private static final Identifier TEXTURE = Identifier.ofVanilla("textures/gui/container/crafting_table.png");

    @Unique
    GuiProps properties = GuiPropAPI.getProps("crafting_table");

    @Redirect(method = "drawBackground(Lnet/minecraft/client/gui/DrawContext;FII)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V"))
    private void injectBack(DrawContext context, Identifier texture, int x, int y, int u, int v, int width, int height) {
        int i = this.x;
        int j = (this.height - this.backgroundHeight) / 2;

        if (properties != null) {
            context.drawTexture(TEXTURE, i - properties.getXOffset(), j - properties.getYOffset(), 0, 0f, 0f, properties.getWidth(), properties.getHeight(), properties.getWidth(), properties.getHeight());
        } else {
            context.drawTexture(TEXTURE, i, j, 0, 0, 256, 256);
        }
    }
}