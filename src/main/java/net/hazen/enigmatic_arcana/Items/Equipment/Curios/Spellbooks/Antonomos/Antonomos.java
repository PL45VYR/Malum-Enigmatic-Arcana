package net.hazen.enigmatic_arcana.Items.Equipment.Curios.Spellbooks.Antonomos;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.sammy.malum.registry.common.MalumAttributes;
import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.SpellBook;
import io.redspace.ironsspellbooks.item.curios.CurioBaseItem;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.hazen.enigmatic_arcana.Items.Important.EADispatcher;
import net.hazen.enigmatic_arcana.Utils.EARarities;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import team.lodestar.lodestone.registry.common.LodestoneAttributes;
import top.theillusivec4.curios.api.SlotContext;

public class Antonomos extends SpellBook {
    public final EADispatcher dispatcher;
    public Antonomos() {
        super(10);
        this.withSpellbookAttributes(new AttributeContainer[]{
                new AttributeContainer(MalumAttributes.ARCANE_RESONANCE, 0.25, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(MalumAttributes.SOUL_WARD_INTEGRITY, 0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.SPELL_RESIST, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.MAX_MANA, 300, AttributeModifier.Operation.ADD_VALUE)
        });


        this.dispatcher = new EADispatcher();
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
