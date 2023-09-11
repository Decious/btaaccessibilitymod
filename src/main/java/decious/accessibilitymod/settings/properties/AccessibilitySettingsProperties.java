package decious.accessibilitymod.settings.properties;

import decious.accessibilitymod.Constants.SettingsConstants;

import java.util.Properties;

public class AccessibilitySettingsProperties extends Properties {
    public AccessibilitySettingsProperties()
    {
            super();
        setProperty(SettingsConstants.DAMAGE_TILT, "1");
        setProperty(SettingsConstants.ALLOW_DIRECTIONAL_TILT, "true");
    }
}
