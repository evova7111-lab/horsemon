package com.example.horsemon;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.network.chat.Component;
import java.util.Optional;

public record HorsePacket(int packetType) implements CustomPacketPayload {
    public static final Type<HorsePacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("horsemon", "horse_packet"));
    public static final StreamCodec<FriendlyByteBuf, HorsePacket> STREAM_CODEC = StreamCodec.unit(new HorsePacket(0));

    @Override public Type<? extends CustomPacketPayload> type() { return TYPE; }

    public static void handle(HorsePacket payload, net.neoforged.neoforge.network.handling.IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            if (payload.packetType == 1) {
                if (player.getVehicle() instanceof AbstractHorse horse) {
                    player.setData(ModAttachments.MY_HORSE, Optional.of(horse.getUUID()));
                    player.displayClientMessage(Component.literal("§aЛошадь привязана!"), true);
                }
            } else {
                player.getData(ModAttachments.MY_HORSE).ifPresent(uuid -> {
                    var horse = player.serverLevel().getEntity(uuid);
                    if (horse instanceof AbstractHorse h) {
                        h.teleportTo(player.getX() + 2, player.getY(), player.getZ() + 2);
                        h.getNavigation().moveTo(player, 1.2D);
                    }
                });
            }
        });
    }
}