package net.hazen.enigmatic_arcana.Items.Equipment.Weapons.Catastrophe;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.util.GeckoLibUtil;
import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.entity.projectile.AbstractArrow;

public class Catastrophe extends BowItem implements GeoItem {
    public Catastrophe(Properties properties) {
        super(properties);
        GeoItem.registerSyncedAnimatable(this);
    }

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public static final int MAX_DRAW_DURATION = 60;
    public static final int DEFAULT_RANGE = 15;
    private static final String CONTROLLER = "controller";
    private static final RawAnimation CHARGING = RawAnimation.begin().thenLoop("charging_normal");
    private static final RawAnimation IDLE = RawAnimation.begin().thenLoop("idle");
    private static final RawAnimation RELEASE = RawAnimation.begin().thenPlay("release");


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entityLiving, int timeLeft) {
        if (!(entityLiving instanceof Player player)) return;

        ItemStack ammoStack = player.getProjectile(stack);
        if (ammoStack.isEmpty()) return;

        int charge = this.getUseDuration(stack, entityLiving) - timeLeft;
        charge = EventHooks.onArrowLoose(stack, level, player, charge, true);
        if (charge < 0) return;

        float power = getPowerForTime(charge);
        if (power < 0.1F) return;

        List<ItemStack> ammoList = draw(stack, ammoStack, player);
        if (ammoList.isEmpty()) return;

        boolean infiniteAmmo =
                player.getAbilities().instabuild
                        || (ammoStack.getItem() instanceof ArrowItem arrow
                        && arrow.isInfinite(ammoStack, stack, player));

        if (level instanceof ServerLevel serverLevel) {

            if (isAllNormalArrows(ammoList)) {shootThreeArc(serverLevel,player, stack, ammoList, power * 3.0F, 1.0F, infiniteAmmo);
            } else {
                this.shoot(serverLevel, player, player.getUsedItemHand(), stack, ammoList, power * 3.0F, 1.0F, power == 1.0F, null
                );
            }

            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + power * 0.5F);

            player.awardStat(Stats.ITEM_USED.get(this));
        }

        if (entityLiving instanceof ServerPlayer serverPlayer) {
            int id = Math.toIntExact(GeoItem.getOrAssignId(stack, serverPlayer.serverLevel()));
            triggerAnim(serverPlayer, id, CONTROLLER, "release");
        }
    }

    @Override
    protected void shootProjectile(LivingEntity shooter, Projectile projectile, int index, float velocity, float inaccuracy, float angle, @Nullable LivingEntity target) {
        projectile.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot() + angle, 0.0F, velocity, inaccuracy);
    }

    // Helper: checks whether the provided ammo stacks are vanilla arrows
    private boolean isAllNormalArrows(List<ItemStack> ammoStacks) {
        for (ItemStack s : ammoStacks) {
            if (s == null || s.isEmpty()) return false;
            if (s.getItem() != Items.ARROW) return false;
        }
        return true;
    }

    private void shootThreeArc(ServerLevel level, Player player, ItemStack bowStack, List<ItemStack> ammoStacks,
                               float velocity, float inaccuracy, boolean infinite) {

        if (ammoStacks.isEmpty()) return;

        ItemStack ammo = ammoStacks.get(0);

        float basePitch = player.getXRot();
        float yaw = player.getYRot();
        float[] pitchOffsets = {0.0f, -15.0f, 15.0f};

        boolean hasAmmo = !ammo.isEmpty();

        for (int i = 0; i < 3; i++) {
            float pitch = basePitch + pitchOffsets[i];

            ItemStack usedAmmo = hasAmmo ? ammo.copy() : new ItemStack(Items.ARROW);

            ArrowItem arrowItem = (ArrowItem) usedAmmo.getItem();
            AbstractArrow arrowEntity = arrowItem.createArrow(level, usedAmmo, player, bowStack);

            arrowEntity.setPos(player.getX(), player.getEyeY() - 0.1D, player.getZ());
            arrowEntity.shootFromRotation(player, pitch, yaw, 0.0F, velocity, inaccuracy);

            arrowEntity.pickup = infinite
                    ? AbstractArrow.Pickup.CREATIVE_ONLY
                    : (i == 0 ? AbstractArrow.Pickup.ALLOWED : AbstractArrow.Pickup.DISALLOWED);

            level.addFreshEntity(arrowEntity);
        }

        if (hasAmmo && !infinite) {
            ammo.shrink(1);
        }
    }

    private boolean removeOneAmmoFromPlayer(Player player, ItemStack prototype) {
        for (int i = 0; i < player.getInventory().items.size(); i++) {
            ItemStack itemStack = player.getInventory().items.get(i);
            if (!itemStack.isEmpty() && ItemStack.isSameItemSameComponents(itemStack, prototype)) {
                if (itemStack.isEmpty()) player.getInventory().items.set(i, ItemStack.EMPTY);
                return true;
            }
        }
        return false;
    }

    public static float getPowerForTime(int charge) {
        float f = (float) charge / 60.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        boolean flag = !player.getProjectile(itemstack).isEmpty();
        InteractionResultHolder<ItemStack> ret = EventHooks.onArrowNock(itemstack, level, player, hand, flag);
        if (ret != null) {
            return ret;
        } else if (!player.hasInfiniteMaterials() && !flag) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(itemstack);
        }
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ARROW_ONLY;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 15;
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private CatastropheRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null)
                    this.renderer = new CatastropheRenderer();

                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, CONTROLLER, 0, state -> {

            var stack = state.getData(DataTickets.ITEMSTACK);
            var perspective = state.getData(DataTickets.ITEM_RENDER_PERSPECTIVE);

            boolean play = false;

            // Fallback: if the synced entity is using the item, play the charging animation too
            if (!play && state.getData(DataTickets.ENTITY) instanceof LivingEntity entity) {
                if (entity.isUsingItem() && entity.getUseItem() == stack) {
                    play = true;
                }
            }

            if (play) {
                state.setAnimation(CHARGING);
            } else {
                state.setAnimation(IDLE);
            }

            return PlayState.CONTINUE;
        }).triggerableAnim("release", RELEASE));
    }

}
