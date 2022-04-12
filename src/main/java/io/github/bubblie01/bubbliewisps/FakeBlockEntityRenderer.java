package io.github.bubblie01.bubbliewisps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.FallingBlockEntityRenderer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

import java.util.Random;

import static io.github.bubblie01.bubbliewisps.FakeBlockEntity.FAKE_BLOCK_RAW_ID;

public class FakeBlockEntityRenderer extends EntityRenderer<FakeBlockEntity> {
    public FakeBlockEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(FakeBlockEntity fakeBlockEntity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        BlockState blockState = fakeBlockEntity.block;
        World world = fakeBlockEntity.getWorld();
        matrices.push();
        BlockPos blockPos = new BlockPos(fakeBlockEntity.getX(), fakeBlockEntity.getBoundingBox().maxY, fakeBlockEntity.getZ());
        matrices.translate(-0.5,0.0,-0.5);

        BlockRenderManager blockRenderManager = MinecraftClient.getInstance().getBlockRenderManager();
        blockRenderManager.getModelRenderer().render(world, blockRenderManager.getModel(blockState), blockState, blockPos, matrices, vertexConsumers.getBuffer(RenderLayers.getMovingBlockLayer(blockState)), false, new Random(), blockState.getRenderingSeed(fakeBlockEntity.getBlockPos()), OverlayTexture.DEFAULT_UV);
        matrices.pop();

    }

    @Override
    public Identifier getTexture(FakeBlockEntity entity) {
        return SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE;
    }
}
