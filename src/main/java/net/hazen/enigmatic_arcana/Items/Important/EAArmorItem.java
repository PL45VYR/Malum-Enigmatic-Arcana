package net.hazen.enigmatic_arcana.Items.Important;

import com.google.common.base.Suppliers;
import com.sammy.malum.registry.common.MalumAttributes;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.hazen.enigmatic_arcana.Datagen.EATags;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

public class EAArmorItem extends ArmorItem {
    private final Supplier<ItemAttributeModifiers> defaultModifiers;
    public final EADispatcher dispatcher;

    public EAArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties, AttributeContainer... attributeContainers) {
        super(material, type, properties);
        this.dispatcher = new EADispatcher();
        this.defaultModifiers = Suppliers.memoize(() ->
        {
            // Looking at how ISS does this because it is 1 AM and I am tired
            int i = material.value().getDefense(type);
            float f = material.value().toughness();
            ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
            EquipmentSlotGroup equipmentSlotGroup = EquipmentSlotGroup.bySlot(type.getSlot());
            ResourceLocation resourceLocation = ResourceLocation.withDefaultNamespace("armor." + type.getName());
            builder.add(Attributes.ARMOR, new AttributeModifier(resourceLocation, i, AttributeModifier.Operation.ADD_VALUE), equipmentSlotGroup);
            builder.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(resourceLocation, f, AttributeModifier.Operation.ADD_VALUE), equipmentSlotGroup);

            float resistance = material.value().knockbackResistance();
            if (resistance > 0.0F)
            {
                builder.add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(resourceLocation, resistance, AttributeModifier.Operation.ADD_VALUE), equipmentSlotGroup);
            }
            for (AttributeContainer holder : attributeContainers)
            {
                builder.add(holder.attribute(), holder.createModifier(type.getSlot().getName()), equipmentSlotGroup);
            }

            return builder.build();
        });
    }

    public static AttributeContainer[] pureTierMalum(int mana, float spellPower, float resistSpellPower, float soulWardCapacity, float soulWardRecovery)
    {
        return new AttributeContainer[]{
                new AttributeContainer(AttributeRegistry.MAX_MANA, mana, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(AttributeRegistry.SPELL_RESIST, resistSpellPower, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.SPELL_POWER, spellPower, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(MalumAttributes.SOUL_WARD_CAPACITY, soulWardCapacity, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(MalumAttributes.SOUL_WARD_RECOVERY_RATE, soulWardRecovery, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
        };
    }

    public static AttributeContainer[] paragonTierMalum(
            int mana,
            float spellPower,
            float resistSpellPower,
            float scytheProficiency,
            float arcaneResonance
    )
    {
        return new AttributeContainer[]{
                new AttributeContainer(AttributeRegistry.MAX_MANA, mana, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(AttributeRegistry.SPELL_RESIST, resistSpellPower, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.SPELL_POWER, spellPower, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(MalumAttributes.SCYTHE_PROFICIENCY, scytheProficiency, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(MalumAttributes.ARCANE_RESONANCE, arcaneResonance, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
        };
    }


    @Override
    public @NotNull ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
        ItemAttributeModifiers modifiers = super.getDefaultAttributeModifiers(stack);
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();

        List<ItemAttributeModifiers.Entry> entries = modifiers.modifiers();
        for (ItemAttributeModifiers.Entry entry : entries) {
            builder.add(entry.attribute(), entry.modifier(), entry.slot());
        }

        List<ItemAttributeModifiers.Entry> extraEntries = createExtraAttributes();
        for (ItemAttributeModifiers.Entry entry : extraEntries) {
            builder.add(entry.attribute(), entry.modifier(), entry.slot());
        }

        return builder.build();
    }

    @Override
    public @NotNull ItemAttributeModifiers getDefaultAttributeModifiers() {
        return this.defaultModifiers.get();
    }

    public List<ItemAttributeModifiers.Entry> createExtraAttributes() {
        return List.of(); // or Collections.emptyList();
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (!level.isClientSide && entity instanceof Player player ) {
            player.getArmorSlots().forEach(wornArmor -> {
                if (wornArmor != null && wornArmor.is(EATags.IDLE_ARMOR)) {
                    dispatcher.idle(player, wornArmor);
                }
            });
        }
    }
}