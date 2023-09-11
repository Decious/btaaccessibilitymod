package decious.accessibilitymod;

import decious.accessibilitymod.Constants.ModConstants;
import decious.accessibilitymod.settings.ModConfiguration;
import decious.accessibilitymod.settings.properties.AccessibilitySettingsProperties;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.option.GameSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.transformer.Config;

public class AccessibilityMod implements ModInitializer {
    public static final ModConfiguration CONFIG = new ModConfiguration(ModConstants.MOD_ID, new AccessibilitySettingsProperties());
    public static final Logger logger = LoggerFactory.getLogger(ModConstants.MOD_ID);

    @Override
    public void onInitialize()
    {
        logger.info("Initialization complete.");
    }
}
