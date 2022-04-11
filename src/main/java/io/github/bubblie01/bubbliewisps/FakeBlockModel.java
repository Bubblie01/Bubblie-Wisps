package io.github.bubblie01.bubbliewisps;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.util.math.MatrixStack;

public class FakeBlockModel extends EntityModel<FakeBlockEntity> {

    private ModelPart blockPart;

    public FakeBlockModel(ModelPart root) {
        this.blockPart = root.getChild(EntityModelPartNames.CUBE);
    }

    @Override
    public void setAngles(FakeBlockEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {

    }




}
