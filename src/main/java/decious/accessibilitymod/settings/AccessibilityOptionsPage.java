package decious.accessibilitymod.settings;

import decious.accessibilitymod.AccessibilityMod;
import decious.accessibilitymod.Constants.SettingsConstants;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.options.GuiOptionsPageOptionBase;
import net.minecraft.client.option.FloatOption;
import net.minecraft.client.option.GameSettings;

public class AccessibilityOptionsPage extends GuiOptionsPageOptionBase {
    private FloatOption damageTilt;
    public AccessibilityOptionsPage(GuiScreen parent, GameSettings settings) {
        super(parent, settings);
        float damageTiltValue = AccessibilityMod.CONFIG.getFloat(SettingsConstants.DAMAGE_TILT);
        damageTilt = new FloatOption(settings, "damage.tilt", damageTiltValue);
        this.addOptionsCategory("options.category.accessibility", damageTilt);
    }

    @Override
    protected void handleButtonClick(GuiButton guibutton, int categoryIndex, int optionIndex) {
        OnSettingChanged(categoryIndex, optionIndex);
    }

    @Override
    protected void handleButtonRelease(GuiButton guibutton, int categoryIndex, int optionIndex) {
        OnSettingChanged(categoryIndex, optionIndex);
    }

    private void OnSettingChanged(int categoryIndex, int optionIndex){
        if(categoryIndex == CATEGORY_INDEX){
            if (optionIndex == DAMAGE_TILT_OPTION_INDEX){
                damageTilt.onUpdate();
                AccessibilityMod.CONFIG.setProperty(SettingsConstants.DAMAGE_TILT, String.valueOf(damageTilt.value));
            }
        }
        AccessibilityMod.CONFIG.updateConfig();
    }

    private static int CATEGORY_INDEX = 0;

    private static int DAMAGE_TILT_OPTION_INDEX = 0;
}
