package net.hazen.enigmatic_arcana.Items.Equipment.Weapons;

import io.redspace.ironsspellbooks.api.events.ModifySpellLevelEvent;
import io.redspace.ironsspellbooks.api.item.curios.AffinityData;
import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.registries.ComponentRegistry;
import io.redspace.ironsspellbooks.util.TooltipsUtils;
import net.hazen.enigmatic_arcana.Items.Important.EAExtendedWeaponsTiers;
import net.hazen.enigmatic_arcana.Utils.ArcaneScytheItem;
import net.hazen.enigmatic_arcana.Utils.EARarities;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import org.jetbrains.annotations.NotNull;
import team.lodestar.lodestone.systems.item.LodestoneItemProperties;

import java.util.List;
import java.util.Map;

public class Nihility extends ArcaneScytheItem {

    public Nihility() {
        super(
                EAExtendedWeaponsTiers.NIHILITY,
                2f,
                0.6f,
                6.0f,
                new LodestoneItemProperties()
                        .fireResistant()
                        .rarity(EARarities.AGROCONIC_RARITY.getValue())
                        .attributes(ExtendedSwordItem.createAttributes(EAExtendedWeaponsTiers.NIHILITY)
                        )
        );
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @NotNull TooltipContext context, @NotNull List<Component> lines, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemStack, context, lines, flag);
        var affinityData = AffinityData.getAffinityData(itemStack);
        if (!affinityData.affinityData().isEmpty()) {
            int i = TooltipsUtils.indexOfComponent(lines, "tooltip.hazennstuff.spellbook_spell_count");
            lines.addAll(i < 0 ? lines.size() : i + 1, affinityData.getDescriptionComponent());
        }
    }

    @Override
    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack == null) {
            return;
        }

        super.initializeSpellContainer(itemStack);
        itemStack.set(ComponentRegistry.AFFINITY_COMPONENT, new AffinityData(Map.of(
                SpellRegistry.ECHOING_STRIKES_SPELL.get().getSpellResource(), 1
        )));
    }

    @EventBusSubscriber(value = Dist.CLIENT)
    public static class SpellEvents {

        @SubscribeEvent
        public static void onModifySpellLevel(ModifySpellLevelEvent event) {
            LivingEntity caster = event.getEntity();
            if (caster == null) return;

            if (event.getSpell() != SpellRegistry.ECHOING_STRIKES_SPELL.get()) {
                return;
            }

            ItemStack mainHand = caster.getMainHandItem();
            ItemStack offHand = caster.getOffhandItem();

            boolean nihility = mainHand.getItem() instanceof Nihility ||
                    offHand.getItem() instanceof Nihility;

            if (nihility) {
                event.addLevels(1);
            }
        }
    }
}
