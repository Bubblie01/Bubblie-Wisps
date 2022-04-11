package io.github.bubblie01.bubbliewisps;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class ClientInitializer implements ClientModInitializer
{

    @Override
    public void onInitializeClient()
    {
        EntityRendererRegistry.register(FakeBlockEntity.FAKE_BLOCK_ENTITY_TYPE, ((context) -> {
            return new FakeBlockEntityRenderer(context);
        }));
    }
}
