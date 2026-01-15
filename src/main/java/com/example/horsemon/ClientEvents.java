package com.example.horsemon;

import net.minecraft.client.KeyMapping;
import com.mojang.blaze3d.platform.InputConstants;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

public class ClientEvents {
    public static final KeyMapping KEY_P = new KeyMapping("Привязать", GLFW.GLFW_KEY_P, "Horse Mod");
    public static final KeyMapping KEY_V = new KeyMapping("Позвать", GLFW.GLFW_KEY_V, "Horse Mod");

    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(KEY_P);
        event.register(KEY_V);
    }

    public static void onKeyInput(InputEvent.Key event) {
        if (KEY_P.consumeClick()) {
            PacketDistributor.sendToServer(new HorsePacket(1));
        }
        if (KEY_V.consumeClick()) {
            PacketDistributor.sendToServer(new HorsePacket(2));
        }
    }
}
