package net.hazen.enigmatic_arcana.Datagen.ItemGenerator;

import com.sammy.malum.registry.common.MalumTags;
import io.redspace.ironsspellbooks.util.ModTags;
import net.hazen.enigmatic_arcana.EnigmaticArcana;
import net.hazen.enigmatic_arcana.Registries.EAItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EAItemTagProvider extends ItemTagsProvider {
    public EAItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, EnigmaticArcana.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        //Ores and Ingots
        tag(Tags.Items.INGOTS)
                .add(EAItemRegistry.AGROCONIC_ALLOY.get())
        ;

        tag(Tags.Items.TOOLS_BOW)
                .add(EAItemRegistry.CATASTROPHE.get())
        ;


        /*
        *** Enchantable Tags
         */

        tag(Tags.Items.ENCHANTABLES)
                .add(EAItemRegistry.APOTHIC_CRUSADER_HELMET.get())
                .add(EAItemRegistry.APOTHIC_CRUSADER_CHESTPLATE.get())
                .add(EAItemRegistry.APOTHIC_CRUSADER_LEGGINGS.get())
                .add(EAItemRegistry.APOTHIC_CRUSADER_BOOTS.get())

                .add(EAItemRegistry.NIHILITY.get())
                .add(EAItemRegistry.CATASTROPHE.get())
        ;

        tag(ItemTags.EQUIPPABLE_ENCHANTABLE)
                .add(EAItemRegistry.APOTHIC_CRUSADER_HELMET.get())
                .add(EAItemRegistry.APOTHIC_CRUSADER_CHESTPLATE.get())
                .add(EAItemRegistry.APOTHIC_CRUSADER_LEGGINGS.get())
                .add(EAItemRegistry.APOTHIC_CRUSADER_BOOTS.get())
        ;

        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .add(EAItemRegistry.NIHILITY.get())
        ;



        tag(ItemTags.ARMOR_ENCHANTABLE)
                .add(EAItemRegistry.APOTHIC_CRUSADER_HELMET.get())
                .add(EAItemRegistry.APOTHIC_CRUSADER_CHESTPLATE.get())
                .add(EAItemRegistry.APOTHIC_CRUSADER_LEGGINGS.get())
                .add(EAItemRegistry.APOTHIC_CRUSADER_BOOTS.get())
        ;

        tag(ItemTags.HEAD_ARMOR_ENCHANTABLE)
                .add(EAItemRegistry.APOTHIC_CRUSADER_HELMET.get())
        ;

        tag(ItemTags.CHEST_ARMOR_ENCHANTABLE)
                .add(EAItemRegistry.APOTHIC_CRUSADER_CHESTPLATE.get())
        ;

        tag(ItemTags.LEG_ARMOR_ENCHANTABLE)
                .add(EAItemRegistry.APOTHIC_CRUSADER_LEGGINGS.get())
        ;

        tag(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                .add(EAItemRegistry.APOTHIC_CRUSADER_BOOTS.get())
        ;

        tag(ItemTags.SHARP_WEAPON_ENCHANTABLE)
                .add(EAItemRegistry.NIHILITY.get())
        ;

        tag(ItemTags.BOW_ENCHANTABLE)
                .add(EAItemRegistry.CATASTROPHE.get())
        ;

        /*
        *** Armor Tags
         */

        tag(Tags.Items.ARMORS)
                .add(EAItemRegistry.APOTHIC_CRUSADER_HELMET.get())
                .add(EAItemRegistry.APOTHIC_CRUSADER_CHESTPLATE.get())
                .add(EAItemRegistry.APOTHIC_CRUSADER_LEGGINGS.get())
                .add(EAItemRegistry.APOTHIC_CRUSADER_BOOTS.get())
        ;

        tag(ItemTags.HEAD_ARMOR)
                .add(EAItemRegistry.APOTHIC_CRUSADER_HELMET.get())
        ;

        tag(ItemTags.CHEST_ARMOR)
                .add(EAItemRegistry.APOTHIC_CRUSADER_CHESTPLATE.get())
        ;

        tag(ItemTags.LEG_ARMOR)
                .add(EAItemRegistry.APOTHIC_CRUSADER_LEGGINGS.get())
        ;

        tag(ItemTags.FOOT_ARMOR)
                .add(EAItemRegistry.APOTHIC_CRUSADER_BOOTS.get())
        ;


        /*
        *** Tools and Weapons Tags
         */

//        tag(ItemTags.AXES)
//                .add(EAItemRegistry.BEONGAE.get())
//        ;

        tag(ItemTags.SWORDS)
                .add(EAItemRegistry.NIHILITY.get())
        ;

        tag(ModTags.CAN_BE_IMBUED)

                .add(EAItemRegistry.APOTHIC_CRUSADER_HELMET.get())
                .add(EAItemRegistry.APOTHIC_CRUSADER_CHESTPLATE.get())
                .add(EAItemRegistry.APOTHIC_CRUSADER_LEGGINGS.get())
                .add(EAItemRegistry.APOTHIC_CRUSADER_BOOTS.get())

                .add(EAItemRegistry.NIHILITY.get())
        ;

        tag(ModTags.CAN_BE_UPGRADED)

                .add(EAItemRegistry.APOTHIC_CRUSADER_HELMET.get())
                .add(EAItemRegistry.APOTHIC_CRUSADER_CHESTPLATE.get())
                .add(EAItemRegistry.APOTHIC_CRUSADER_LEGGINGS.get())
                .add(EAItemRegistry.APOTHIC_CRUSADER_BOOTS.get())

                .add(EAItemRegistry.NIHILITY.get())
        ;

        tag(MalumTags.ItemTags.SCYTHES)
                .add(EAItemRegistry.NIHILITY.get())
        ;

        tag(MalumTags.ItemTags.MAGIC_CAPABLE_WEAPON)
                .add(EAItemRegistry.NIHILITY.get())
        ;
    }
}