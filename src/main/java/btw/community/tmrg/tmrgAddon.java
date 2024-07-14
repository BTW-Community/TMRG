package btw.community.tmrg;

import btw.AddonHandler;
import btw.BTWAddon;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class tmrgAddon extends BTWAddon {
    private static tmrgAddon instance;

    public tmrgAddon() {
        super();
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");

        TrackCommand track = new TrackCommand();
        this.registerAddonCommand(track);
        TrackCoolDownCommand trackCoolDown = new TrackCoolDownCommand();
        this.registerAddonCommand(trackCoolDown);

        //new UpdateRunnerPos();
    }

/*
    @Override
    public void preInitialize() {
        this.registerProperty("ForceWhiteGUIText", "False", "Force all text to be white. WARNING: will break all colored text rendering");
        this.registerProperty("NoPotionOffset", "True", "Disable the offset to the GUI when you have potion effects");
        this.registerProperty("RenderTranslucentGUI", "True", "Allow the game to render translucent GUI textures. Disabling this may have a minor performance improvement");
    }

    @Override
    public void handleConfigProperties(Map<String, String> propertyValues) {
        shouldForceWhiteGUIText = Boolean.parseBoolean(propertyValues.get("ForceWhiteGUIText"));
        shouldNotPotionOffset = Boolean.parseBoolean(propertyValues.get("NoPotionOffset"));
        shouldRenderTranslucency = Boolean.parseBoolean(propertyValues.get("RenderTranslucentGUI"));
    }
    */
}