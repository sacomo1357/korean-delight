package club.someoneice.koreanfood.event;

import club.someoneice.koreanfood.init.BlockList;
import club.someoneice.koreanfood.init.ItemList;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;

import java.util.List;

@EventBusSubscriber(modid = "korean_food", bus = EventBusSubscriber.Bus.GAME)
public class VanillaEvent {

    @SubscribeEvent
    public static void onVillagerTrades(VillagerTradesEvent event) {
        if (event.getType() == net.minecraft.world.entity.npc.VillagerProfession.FARMER) {
            List<VillagerTrades.ItemListing> level1 = event.getTrades().get(1);
            level1.add((entity, random) -> new MerchantOffer(
                    new net.minecraft.world.item.trading.ItemCost(Items.EMERALD, 1),
                    new ItemStack(ItemList.CHILI_PEPPER.get(), 4),
                    12, 2, 0.05f
            ));
        }
    }

    @SubscribeEvent
    public static void wandererTradesSell(WandererTradesEvent event) {
        event.getGenericTrades().add((entity, random) -> new MerchantOffer(
                new net.minecraft.world.item.trading.ItemCost(Items.EMERALD, 3),
                new ItemStack(ItemList.GARLIC.get(), 4),
                8, 1, 0.05f
        ));
    }

    @SubscribeEvent
    public static void onItemUseFinish(net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof net.minecraft.world.entity.player.Player player) {
            if (!player.level().isClientSide()) {
                net.minecraft.world.item.Item item = event.getItem().getItem();
                if (item == ItemList.SIKHYE.get() || item == ItemList.TOFU.get() || item == ItemList.DOENJANG_JJIGAE.get()) {
                    player.level().playSound(
                            null, 
                            player.getX(), 
                            player.getY(), 
                            player.getZ(), 
                            net.minecraft.sounds.SoundEvents.PLAYER_LEVELUP, 
                            net.minecraft.sounds.SoundSource.PLAYERS, 
                            1.0F, 
                            1.0F
                    );
                }
            }
        }
    }
}
