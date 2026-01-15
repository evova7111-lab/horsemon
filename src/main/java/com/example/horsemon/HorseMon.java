package com.example.horsemon;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@Mod("horsemon")
public class HorseMon {
    public HorseMon(IEventBus modEventBus) {
        ModAttachments.ATTACHMENT_TYPES.register(modEventBus);
        modEventBus.addListener(this::registerNetworking);
        modEventBus.addListener(ClientEvents::onKeyRegister);
        NeoForge.EVENT_BUS.addListener(ClientEvents::onKeyInput);
    }

    private void registerNetworking(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToServer(HorsePacket.TYPE, HorsePacket.STREAM_CODEC, HorsePacket::handle);
    }
}