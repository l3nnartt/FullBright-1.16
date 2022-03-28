package com.github.l3nnartt.fullbright;

import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Material;
import net.minecraft.client.Minecraft;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FullBright extends LabyModAddon {

    private static final Logger LOGGER = Logger.getLogger("FullBright");
    private final String PREFIX = "[FullBright-1.16] ";
    private boolean enabled;

    @Override
    public void onEnable() {
        LOGGER.getParent().getHandlers()[0].setLevel(Level.ALL);
        LOGGER.log(Level.INFO, PREFIX + "Addon successful activated");
    }

    @Override
    public void loadConfig() {
        this.enabled = !getConfig().has("enabled") || getConfig().get("enabled").getAsBoolean();
        if (enabled)
            changeGama(10.0F);
    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {
        BooleanElement enableFullBrightElement = new BooleanElement("Enable Full Bright", this, new ControlElement.IconData(Material.TORCH), "enableFullBright", this.enabled);
        enableFullBrightElement.addCallback(callback -> {
            if (callback) {
                changeGama(10.0F);
            } else {
                changeGama(1.0F);
            }
        });
        list.add(enableFullBrightElement);
    }

    public void changeGama(double gamma) {
        Minecraft.getInstance().gameSettings.gamma = gamma;
    }
}