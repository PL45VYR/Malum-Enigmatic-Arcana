package net.hazen.enigmatic_arcana.Registries;

import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.hazen.enigmatic_arcana.EnigmaticArcana;
import net.hazen.enigmatic_arcana.Items.Equipment.Armor.AgroconicSets.ApothicCrusader.ApothicCrusaderArmorItem;
import net.hazen.enigmatic_arcana.Items.Equipment.Curios.CustomCurios.AgroconicBulwark.AgroconicBulwark;
import net.hazen.enigmatic_arcana.Items.Equipment.Curios.CustomCurios.AgroconicWings.AgroconicWings;
import net.hazen.enigmatic_arcana.Items.Equipment.Curios.Spellbooks.Antonomos.Antonomos;
import net.hazen.enigmatic_arcana.Items.Equipment.Weapons.Catastrophe.Catastrophe;
import net.hazen.enigmatic_arcana.Items.Equipment.Weapons.Nihility;
import net.hazen.enigmatic_arcana.Utils.EARarities;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Unbreakable;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collection;

public class EAItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EnigmaticArcana.MOD_ID);


    /*
    *** Materials
     */

    public static final DeferredItem<Item> AGROCONIC_ALLOY = ITEMS.register("agroconic_alloy",
            () -> new Item(new Item.Properties()
                    .rarity(EARarities.AGROCONIC_RARITY.getValue())

            ));

    public static final DeferredItem<Item> UMBRAL_WEAVE = ITEMS.register("umbral_weave",
            () -> new Item(new Item.Properties()
                    .rarity(EARarities.UMBRAL_RARITY.getValue())

            ));


    /*
    *** Curios
     */

    // Agroconic Bulwark
    public static final DeferredItem<AgroconicBulwark> AGROCONIC_BULWARK = ITEMS.register("agroconic_bulwark", AgroconicBulwark::new);

    // Agroconic Wings
    public static final DeferredItem<AgroconicWings> AGROCONIC_WINGS = ITEMS.register("agroconic_wings", AgroconicWings::new);


    /*
    *** Spellbooks
     */

    // Antonomos
    public static final DeferredItem <Antonomos> ANTONOMOS = ITEMS.register("antonomos", Antonomos::new);




    /*
    *** Weapons
     */


    //Nihility
    public static final DeferredHolder<Item, Item> NIHILITY = ITEMS.register
            ("nihility", Nihility::new);

    public static final DeferredItem<Catastrophe> CATASTROPHE =
            ITEMS.register("catastrophe", () -> new Catastrophe(ItemPropertiesHelper
                            .equipment(1)
                            .fireResistant()
                            .rarity(EARarities.SOUL_STAINED_RARITY.getValue())
                            .durability(1561)
                            .component(DataComponents.UNBREAKABLE, new Unbreakable(false))
            ));


    /*
    *** Armor
     */

    public static final DeferredHolder<Item, Item> APOTHIC_CRUSADER_HELMET = ITEMS.register("apothic_crusader_helmet",
            () -> new ApothicCrusaderArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
                    .equipment(1)
                    .rarity(EARarities.AGROCONIC_RARITY.getValue())
                    .fireResistant()
                    .durability(ArmorItem.Type.HELMET.getDurability(64))
            ));
    public static final DeferredHolder<Item, Item> APOTHIC_CRUSADER_CHESTPLATE = ITEMS.register("apothic_crusader_chestplate",
            () -> new ApothicCrusaderArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
                    .equipment(1)
                    .rarity(EARarities.AGROCONIC_RARITY.getValue())
                    .fireResistant()
                    .durability(ArmorItem.Type.CHESTPLATE.getDurability(64))
            ));
    public static final DeferredHolder<Item, Item> APOTHIC_CRUSADER_LEGGINGS = ITEMS.register("apothic_crusader_leggings",
            () -> new ApothicCrusaderArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
                    .equipment(1)
                    .rarity(EARarities.AGROCONIC_RARITY.getValue())
                    .fireResistant()
                    .durability(ArmorItem.Type.LEGGINGS.getDurability(64))
            ));
    public static final DeferredHolder<Item, Item> APOTHIC_CRUSADER_BOOTS = ITEMS.register("apothic_crusader_boots",
            () -> new ApothicCrusaderArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
                    .equipment(1)
                    .rarity(EARarities.AGROCONIC_RARITY.getValue())
                    .fireResistant()
                    .durability(ArmorItem.Type.BOOTS.getDurability(64))
            ));



    public static Collection<DeferredHolder<Item, ? extends Item>> getEAItems()
    {
        return ITEMS.getEntries();
    }

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}