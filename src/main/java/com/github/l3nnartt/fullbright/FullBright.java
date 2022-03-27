package com.github.l3nnartt.fullbright;

import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Material;
import net.minecraft.client.Minecraft;

import java.util.List;

public class FullBright extends LabyModAddon {

    private boolean enabled;

    @Override
    public void onEnable() {
        getLogger("Addon started");
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

    public static void getLogger(String log) {
        System.out.println("[FullBright-1.16] " + log);
    }
}