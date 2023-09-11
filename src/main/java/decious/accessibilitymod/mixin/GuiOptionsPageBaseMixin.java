package decious.accessibilitymod.mixin;

import decious.accessibilitymod.helpers.ArrayHelper;
import decious.accessibilitymod.settings.AccessibilityOptionsPage;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.options.GuiOptionsPageBase;
import net.minecraft.client.option.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(value= GuiOptionsPageBase.class, remap = false)
public abstract class GuiOptionsPageBaseMixin{

    @Shadow static Class<? extends GuiOptionsPageBase>[] pages;

    @Shadow static String[] pageLanguageKeys;

    @Inject(method="<init>", at = @At("TAIL"))
    void GuiOptionsPageBase(GuiScreen parent, GameSettings settings, CallbackInfo ci) {
        if (Arrays.stream(pages).noneMatch(x -> x == AccessibilityOptionsPage.class)) {
            pages = ArrayHelper.append(pages, AccessibilityOptionsPage.class);
            pageLanguageKeys = ArrayHelper.append(pageLanguageKeys, "options.tab.accessibility");
        }
    }
}
