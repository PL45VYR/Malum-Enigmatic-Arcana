package net.hazen.enigmatic_arcana.Items.Equipment.Armor.AgroconicSets.ApothicCrusader;

import io.redspace.ironsspellbooks.item.armor.IDisableHat;
import io.redspace.ironsspellbooks.item.armor.IDisableJacket;
import net.hazen.enigmatic_arcana.Items.Important.EAArmorMaterials;
import net.hazen.enigmatic_arcana.Items.Important.EADispatcher;
import net.hazen.enigmatic_arcana.Items.Important.EAImbuableArmorItem;

public class ApothicCrusaderArmorItem extends EAImbuableArmorItem implements IDisableJacket, IDisableHat {
    public final EADispatcher dispatcher;

    public ApothicCrusaderArmorItem(Type type, Properties properties) {
        super(EAArmorMaterials.AGROCONIC_MATERIAL,
                type,
                properties,
                pureTierMalum(
                250,
                0.15f,
                0.5f,
                4,
                0.2F
        ));
        this.dispatcher = new EADispatcher();
    }
}