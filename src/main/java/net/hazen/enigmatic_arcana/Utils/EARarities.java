package net.hazen.enigmatic_arcana.Utils;

import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Rarity;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;

import java.util.function.UnaryOperator;

public class EARarities {

    public static final EnumProxy<Rarity> AGROCONIC_RARITY = new EnumProxy<>(Rarity.class,
            -1,
            "enigmatic_arcana:agroconic",
            (UnaryOperator<Style>) ((style) -> style.withColor(0x841236))
            );

    public static final EnumProxy<Rarity> UMBRAL_RARITY = new EnumProxy<>(Rarity.class,
            -1,
            "enigmatic_arcana:umbral",
            (UnaryOperator<Style>) ((style) -> style.withColor(0xe77e0b))
    );
}