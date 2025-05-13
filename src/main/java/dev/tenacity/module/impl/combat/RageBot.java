package dev.tenacity.module.impl.combat;

import dev.tenacity.module.Category;
import dev.tenacity.module.Module;
import dev.tenacity.module.settings.impl.BooleanSetting;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;

import java.awt.*;
import java.util.*;
import java.util.List;

public class RageBot extends Module {
    private final BooleanSetting slientAim = new BooleanSetting("Slient Aim", false);
    public RageBot() {
        super("RageBot", Category.COMBAT, "Automatically aims your enemies and shoot");
    }
    List<Entity> targets = new ArrayList<>();
    int targetColor = new Color(255, 0, 0).getRGB(), hitcolor = new Color(0, 255, 0).getRGB();
    int predictionTicks = 1;
    float serverYaw, serverPitch;
    Entity target = null;
    Vec3 aimPoint = null;
    boolean sentAlready = false;
    HashSet ignoreBlocks = new HashSet<>(Arrays.asList(
            "wall_sign",
            "torch",
            "air",
            "ladder",
            "wheat",
            "planks",
            "coal_ore",
            "emerald_ore",
            "wooden_slab",
            "iron_ore",
            "stained_glass_pane",
            "diamond_ore",
            "iron_bars",
            "spruce_stairs",
            "fire"
    ));
    Map<String, List<Double[]>> recoilValues = new HashMap<>();
    int recoil = 0, aim = 0, lastStackSize = 1, lastDurability = 100;

    String teamColor = "";

    double hitboxScale = 1.0;
    double width = 0.6 * hitboxScale;
    double height = 1.8 * hitboxScale;
    double halfWidth = width / 2d;
    double halfHeight = height / 2d;

    @Override
    public void onEnable() {
        serverYaw = mc.thePlayer.getRotationYawHead();
        serverPitch = mc.thePlayer.getRotationPitchHead();
    }
}
