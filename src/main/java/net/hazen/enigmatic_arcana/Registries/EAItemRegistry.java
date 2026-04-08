package net.hazen.enigmatic_arcana.Registries;

import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.hazen.enigmatic_arcana.EnigmaticArcana;
import net.hazen.enigmatic_arcana.Items.Equipment.Armor.AgroconicSets.ApothicCrusader.ApothicCrusaderArmorItem;
import net.hazen.enigmatic_arcana.Items.Equipment.Curios.CustomCurios.AgroconicBulwark.AgroconicBulwark;
import net.hazen.enigmatic_arcana.Items.Equipment.Curios.CustomCurios.AgroconicWings.AgroconicWings;
import net.hazen.enigmatic_arcana.Items.Equipment.Curios.Spellbooks.Antonomos.Antonomos;
import net.hazen.enigmatic_arcana.Items.Equipment.Weapons.Nihility;
import net.hazen.enigmatic_arcana.Utils.EARarities;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
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


    /*
    *** Armor
     */

    public static final DeferredHolder<Item, Item> APOTHIC_CRUSADER_HELMET = ITEMS.register("apothic_crusader_helmet",
            () -> new ApothicCrusaderArmorItem(ArmorItem.Type.HELMET, ItemPropertiesHelper
                    .equipment(1)
                    .rarity(EARarities.AGROCONIC_RARITY.getValue())
                    .fireResistant()
            ));
    public static final DeferredHolder<Item, Item> APOTHIC_CRUSADER_CHESTPLATE = ITEMS.register("apothic_crusader_chestplate",
            () -> new ApothicCrusaderArmorItem(ArmorItem.Type.CHESTPLATE, ItemPropertiesHelper
                    .equipment(1)
                    .rarity(EARarities.AGROCONIC_RARITY.getValue())
                    .fireResistant()
            ));
    public static final DeferredHolder<Item, Item> APOTHIC_CRUSADER_LEGGINGS = ITEMS.register("apothic_crusader_leggings",
            () -> new ApothicCrusaderArmorItem(ArmorItem.Type.LEGGINGS, ItemPropertiesHelper
                    .equipment(1)
                    .rarity(EARarities.AGROCONIC_RARITY.getValue())
                    .fireResistant()
            ));
    public static final DeferredHolder<Item, Item> APOTHIC_CRUSADER_BOOTS = ITEMS.register("apothic_crusader_boots",
            () -> new ApothicCrusaderArmorItem(ArmorItem.Type.BOOTS, ItemPropertiesHelper
                    .equipment(1)
                    .rarity(EARarities.AGROCONIC_RARITY.getValue())
                    .fireResistant()
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