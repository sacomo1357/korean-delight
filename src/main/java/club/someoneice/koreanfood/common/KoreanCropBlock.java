package club.someoneice.koreanfood.common;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import java.util.function.Supplier;

public class KoreanCropBlock extends CropBlock {
    private final Supplier<? extends ItemLike> seedSupplier;

    // Instance-bound unit codec definition for crop block serialization
    private final MapCodec<KoreanCropBlock> codec = MapCodec.unit(() -> this);

    public KoreanCropBlock(Supplier<? extends ItemLike> seedSupplier) {
        super(BlockBehaviour.Properties.of()
                .noCollission()
                .randomTicks()
                .instabreak()
                .sound(SoundType.CROP));
        this.seedSupplier = seedSupplier;
    }

    @Override
    public MapCodec<? extends CropBlock> codec() {
        return this.codec;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return this.seedSupplier.get();
    }
}
