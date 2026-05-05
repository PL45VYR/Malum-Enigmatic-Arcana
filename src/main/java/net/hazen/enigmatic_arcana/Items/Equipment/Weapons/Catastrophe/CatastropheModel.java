package net.hazen.enigmatic_arcana.Items.Equipment.Weapons.Catastrophe;

import net.hazen.enigmatic_arcana.EnigmaticArcana;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class CatastropheModel extends DefaultedItemGeoModel<Catastrophe> {
    public CatastropheModel() {
        super(ResourceLocation.fromNamespaceAndPath(EnigmaticArcana.MOD_ID, ""));
    }

    // Just replace where the path is with the file path of your texture, EZ PZ
    @Override
    public ResourceLocation getModelResource(Catastrophe animatable) {
        return ResourceLocation.fromNamespaceAndPath(EnigmaticArcana.MOD_ID, "geo/weapons/catastrophe.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Catastrophe animatable) {
        return ResourceLocation.fromNamespaceAndPath(EnigmaticArcana.MOD_ID, "textures/item/weapons/catastrophe.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Catastrophe animatable) {
        return ResourceLocation.fromNamespaceAndPath(EnigmaticArcana.MOD_ID, "animations/weapons/catastrophe.animation.json");
    }
}
