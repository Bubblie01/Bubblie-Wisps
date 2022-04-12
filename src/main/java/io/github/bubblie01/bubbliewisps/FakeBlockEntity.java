package io.github.bubblie01.bubbliewisps;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.network.packet.s2c.play.MobSpawnS2CPacket;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class FakeBlockEntity extends PathAwareEntity {

    public volatile BlockState block;
    public VoxelShape shape;
    public static final EntityType<FakeBlockEntity> FAKE_BLOCK_ENTITY_TYPE = Registry.register(Registry.ENTITY_TYPE, new Identifier("bubbliewisp", "fake_block_entity"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, FakeBlockEntity::new).dimensions(EntityDimensions.changing(0.98f,0.98f)).build());
    public static final TrackedData<Integer> FAKE_BLOCK_RAW_ID = DataTracker.registerData(FakeBlockEntity.class,TrackedDataHandlerRegistry.INTEGER);

    public FakeBlockEntity(EntityType<? extends FakeBlockEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(FAKE_BLOCK_RAW_ID,0);
        super.initDataTracker();


    }



    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new WanderAroundFarGoal(this, 0.5D));
        this.goalSelector.add(1, new TemptGoal(this, 0.5D, Ingredient.ofItems(Items.APPLE), false));
        this.goalSelector.add(2, new LookAtEntityGoal(this, PlayerEntity.class, 6.0f));
        this.goalSelector.add(3, new BlockDisguiseGoal(this, 5.0f));
        this.goalSelector.add(1, new LookAroundGoal(this));
        super.initGoals();
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
            var stackInHand = player.getStackInHand(hand).getItem();
            if(stackInHand instanceof BlockItem) {
                block = ((BlockItem) stackInHand).getBlock().getDefaultState();
                shape = block.getOutlineShape(this.world,this.getBlockPos());
            }
            System.out.println(block.getBlock().getName());

            System.out.println("X: " + this.getX() + " Y: " + this.getY() + " Z: " + this.getZ());
            System.out.println("BlockX: " + this.getBlockX() + " BlockY: " + this.getBlockY() + " BlockZ: " + this.getBlockZ());
        return super.interactMob(player, hand);
    }





    @Override
    public boolean isCollidable() {
        return true;
    }


    @Override
    public boolean canSpawn(WorldView world) {
        if(block == null) {
            block = Blocks.AIR.getDefaultState();
        }
        this.dataTracker.set(FAKE_BLOCK_RAW_ID, Block.getRawIdFromState(block));
        return super.canSpawn(world);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.BLOCK_AMETHYST_BLOCK_STEP;
    }

    public static void registerFakeBlockEntityAttributes() {
        FabricDefaultAttributeRegistry.register(FAKE_BLOCK_ENTITY_TYPE, FakeBlockEntity.createMobAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 10.0D));
    }

    @Override
    public void readFromPacket(MobSpawnS2CPacket packet) {
        this.block = Block.getStateFromRawId(this.dataTracker.get(FAKE_BLOCK_RAW_ID));
        super.readFromPacket(packet);
    }

    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
    }

    @Override
    protected Box calculateBoundingBox() {
        return super.calculateBoundingBox();
    }
}
