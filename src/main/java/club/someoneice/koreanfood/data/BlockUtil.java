package club.someoneice.koreanfood.data;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class BlockUtil {

    public static Block pie() {
        return new Block(BlockBehaviour.Properties.of()
                .strength(0.5f)
                .sound(SoundType.WOOL));
    }

    public static Block pineappleHuge() {
        return new Block(BlockBehaviour.Properties.of()
                .strength(2.0f)
                .sound(SoundType.WOOD));
    }

    public static Block pineappleCrate() {
        return new Block(BlockBehaviour.Properties.of()
                .strength(2.0f)
                .sound(SoundType.WOOD));
    }

    public static class WildCropBlock extends BushBlock {
        public static final com.mojang.serialization.MapCodec<WildCropBlock> CODEC = simpleCodec(WildCropBlock::new);

        public WildCropBlock(Properties properties) {
            super(properties);
        }

        @Override
        public com.mojang.serialization.MapCodec<? extends BushBlock> codec() {
            return CODEC;
        }
    }

    public static Block wildBlock() {
        return new WildCropBlock(BlockBehaviour.Properties.of()
                .noCollission()
                .instabreak()
                .sound(SoundType.GRASS));
    }
}
