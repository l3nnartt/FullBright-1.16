package com.github.l3nnartt.fullbright;

import java.util.List;
import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Material;
import net.minecraft.client.Minecraft;

public class FullBright extends LabyModAddon {

  private boolean enabled;

  @Override
  public void onEnable() {
  }

  @Override
  public void loadConfig() {
    this.enabled = !getConfig().has("enabled") || getConfig().get("enabled").getAsBoolean();
  }

  @Override
  protected void fillSettings(List<SettingsElement> list) {
    BooleanElement enableFullBrightElement = new BooleanElement("Enable Full Bright", this, new ControlElement.IconData(Material.TORCH), "enableFullBright", this.enabled);
    enableFullBrightElement.addCallback(callback -> {
      if (callback) {
        Minecraft.getInstance().gameSettings.gamma = 10.0F;
      } else {
        Minecraft.getInstance().gameSettings.gamma = 1.0F;
      }
    });
    list.add(enableFullBrightElement);
  }
}
