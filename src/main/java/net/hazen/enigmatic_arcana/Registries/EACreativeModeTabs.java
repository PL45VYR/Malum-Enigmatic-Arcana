package net.hazen.enigmatic_arcana.Registries;

import net.hazen.enigmatic_arcana.EnigmaticArcana;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EACreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EnigmaticArcana.MOD_ID);

    public static final Supplier<CreativeModeTab> EA_EQUIPMENT = CREATIVE_MODE_TAB.register("ea_equipment",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(EAItemRegistry.AGROCONIC_ALLOY.get()))
                    //.withTabsBefore(ResourceLocation.fromNamespaceAndPath(EnigmaticArcana.MOD_ID, "ea_materials"))
                    .title(Component.translatable("creativetab.enigmatic_arcana.ea_equipment"))
                    .displayItems((itemDisplayParameters, output) -> {

                        /*
                        *** Materials
                         */

                        output.accept(EAItemRegistry.AGROCONIC_ALLOY.get());
                        output.accept(EAItemRegistry.UMBRAL_WEAVE.get());

                        output.accept(EAItemRegistry.NIHILITY.get());

                        output.accept(EAItemRegistry.AGROCONIC_BULWARK.get());
                        output.accept(EAItemRegistry.AGROCONIC_WINGS.get());
                        output.accept(EAItemRegistry.ANTONOMOS.get());

                        output.accept(EAItemRegistry.APOTHIC_CRUSADER_HELMET.get());
                        output.accept(EAItemRegistry.APOTHIC_CRUSADER_CHESTPLATE.get());
                        output.accept(EAItemRegistry.APOTHIC_CRUSADER_LEGGINGS.get());
                        output.accept(EAItemRegistry.APOTHIC_CRUSADER_BOOTS.get());

                    }).build());



    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}
