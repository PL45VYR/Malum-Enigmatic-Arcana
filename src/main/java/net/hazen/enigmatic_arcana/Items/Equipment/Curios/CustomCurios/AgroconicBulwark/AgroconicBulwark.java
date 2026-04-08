package net.hazen.enigmatic_arcana.Items.Equipment.Curios.CustomCurios.AgroconicBulwark;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.sammy.malum.registry.common.MalumAttributes;
import io.redspace.ironsspellbooks.item.curios.CurioBaseItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.hazen.enigmatic_arcana.Items.Important.EADispatcher;
import net.hazen.enigmatic_arcana.Utils.EARarities;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

public class AgroconicBulwark extends CurioBaseItem {
    public final EADispatcher dispatcher;

    public AgroconicBulwark() {
        super(ItemPropertiesHelper
                .equipment()
                .stacksTo(1)
                .fireResistant()
                .rarity(EARarities.AGROCONIC_RARITY.getValue())
        );

        this.dispatcher = new EADispatcher();
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> attr = LinkedHashMultimap.create();
        attr.put(Attributes.ARMOR, new AttributeModifier(id, 4f, AttributeModifier.Operation.ADD_VALUE));
        attr.put(MalumAttributes.SOUL_WARD_CAPACITY, new AttributeModifier(id, 8f, AttributeModifier.Operation.ADD_VALUE));
        attr.put(MalumAttributes.SOUL_WARD_RECOVERY_RATE, new AttributeModifier(id, 0.5f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        attr.put(MalumAttributes.SOUL_WARD_INTEGRITY, new AttributeModifier(id, 0.3f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        return attr;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (!level.isClientSide && entity instanceof Player player )
        {
            dispatcher.idle(player, stack);
        }
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }
}
