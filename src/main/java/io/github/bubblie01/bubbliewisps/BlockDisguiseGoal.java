package io.github.bubblie01.bubbliewisps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDisguiseGoal extends Goal {

    private BlockPos blockPos;
    private BlockPos entityBlockPos;
    private FakeBlockEntity fakeBlockEntity;
    private Block block;
    private final float range;
    private World world;
    private BlockPos.Mutable mutableBlockPos = new BlockPos.Mutable();

    public BlockDisguiseGoal(FakeBlockEntity fakeBlockEntity, float range) {
        this.fakeBlockEntity = fakeBlockEntity;
        this.range = range;
        this.blockPos = BlockPos.ORIGIN;
        this.world = fakeBlockEntity.world;

    }

    @Override
    public boolean canStart() {
            this.entityBlockPos = this.fakeBlockEntity.getBlockPos();
            for (int x = blockPos.getX(); x < range; x++) {
                for (int y = blockPos.getY(); y < range; y++) {
                    for (int z = blockPos.getZ(); z < range; z++) {
                        mutableBlockPos.set(entityBlockPos.getX() + x, entityBlockPos.getY() + y, entityBlockPos.getZ() + z);
                        if (this.world.getBlockState(mutableBlockPos).getBlock() != Blocks.AIR) {
                            fakeBlockEntity.block = (this.world.getBlockState(mutableBlockPos));
                            System.out.println(fakeBlockEntity.block.getBlock().getName());

                            return true;
                        }
                    }
                }
            }return false;
        }


}




