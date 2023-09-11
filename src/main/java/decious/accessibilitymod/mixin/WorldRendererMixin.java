package decious.accessibilitymod.mixin;

import decious.accessibilitymod.AccessibilityMod;
import decious.accessibilitymod.Constants.SettingsConstants;
import net.minecraft.client.render.WorldRenderer;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = WorldRenderer.class, remap = false)
public class WorldRendererMixin {

    @Redirect(method = "hurtCameraEffect", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V"))
    private void injected(float angle, float x, float y, float z) {
        float damageTiltPercentage = AccessibilityMod.CONFIG.getFloat(SettingsConstants.DAMAGE_TILT);
        GL11.glRotatef(angle*damageTiltPercentage, x*damageTiltPercentage, y*damageTiltPercentage, z*damageTiltPercentage);
    }

    @ModifyVariable(method = "hurtCameraEffect", at = @At("STORE"), name = "f3")
    private float injected(float f3) {
        Boolean allowDirectionalTilt = AccessibilityMod.CONFIG.getBoolean(SettingsConstants.ALLOW_DIRECTIONAL_TILT);
        return allowDirectionalTilt ? f3 : 0;
    }
}
