package io.github.bubblie01.bubbliewisps.items;

import io.github.bubblie01.bubbliewisps.Sounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class FluteItem extends Item {
    public FluteItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient)
        world.playSound(null,user.getBlockPos(), Sounds.FLUTE_NOTE_1_EVENT, SoundCategory.MASTER, 1f, 1f);
        return super.use(world, user, hand);
    }
}
