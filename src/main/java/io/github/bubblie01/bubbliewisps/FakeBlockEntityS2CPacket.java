package io.github.bubblie01.bubbliewisps;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.MobSpawnS2CPacket;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;

import java.util.UUID;

public class FakeBlockEntityS2CPacket extends MobSpawnS2CPacket implements Packet<ClientPlayPacketListener> {

    /*
    private final int velocityX;
    private final int velocityY;
    private final int velocityZ;
    private final int id;
    private final UUID uuid;
    private final int entityTypeId;
    private final double x;
    private final double y;
    private final double z;
    private final byte yaw;
    private final byte pitch;
    private final byte headYaw;

     */

    private final int entityData;

    public FakeBlockEntityS2CPacket(LivingEntity entity, int entityData) {
        super(entity);
        this.entityData = entityData;
    }

    public FakeBlockEntityS2CPacket(PacketByteBuf buf) {
        super(buf);
        this.entityData = buf.readInt();
    }

    @Override
    public void write(PacketByteBuf buf) {
        super.write(buf);
        buf.writeInt(this.entityData);
    }

}
