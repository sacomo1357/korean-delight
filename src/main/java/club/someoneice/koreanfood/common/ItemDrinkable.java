package club.someoneice.koreanfood.common;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class ItemDrinkable extends Item {

    public ItemDrinkable(Item.Properties properties) {
        super(properties);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack remainder = super.finishUsingItem(stack, level, entity);
        if (entity instanceof net.minecraft.world.entity.player.Player player) {
            if (!player.getAbilities().instabuild) {
                if (remainder.isEmpty()) {
                    return new ItemStack(Items.GLASS_BOTTLE);
                }
                player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
            }
        }
        return remainder;
    }
}
