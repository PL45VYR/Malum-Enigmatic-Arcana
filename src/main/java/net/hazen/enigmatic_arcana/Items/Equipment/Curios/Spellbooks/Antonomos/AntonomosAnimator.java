package net.hazen.enigmatic_arcana.Items.Equipment.Curios.Spellbooks.Antonomos;

import mod.azure.azurelib.common.animation.controller.AzAnimationController;
import mod.azure.azurelib.common.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.common.animation.easing.AzEasingTypeRegistry;
import mod.azure.azurelib.common.animation.impl.AzItemAnimator;
import net.hazen.enigmatic_arcana.EnigmaticArcana;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class AntonomosAnimator extends AzItemAnimator {
    private static final ResourceLocation ANIMATIONS = ResourceLocation.fromNamespaceAndPath(
            EnigmaticArcana.MOD_ID,
            "animations/curios/antonomos.animation.json"
    );

    @Override
    public void registerControllers(AzAnimationControllerContainer<ItemStack> animationControllerContainer) {
        animationControllerContainer.add(
                AzAnimationController.builder(this, "base_controller")
                        .setEasingType(AzEasingTypeRegistry.getOrNull("linear"))
                        .setTransitionLength(12)
                        .build()
        );
    }



    @Override
    public @NotNull ResourceLocation getAnimationLocation(ItemStack animatable) {
        return ANIMATIONS;
    }
}