package com.example.horsemon;

import net.minecraft.core.UUIDUtil;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import java.util.UUID;
import java.util.Optional;
import java.util.function.Supplier;

public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, "horsemon");
    public static final Supplier<AttachmentType<Optional<UUID>>> MY_HORSE = ATTACHMENT_TYPES.register("my_horse", 
        () -> AttachmentType.builder(() -> Optional.<UUID>empty()).serialize(UUIDUtil.CODEC.optionalFieldOf("uuid").xmap(Optional::of, Optional::get)).build());
}