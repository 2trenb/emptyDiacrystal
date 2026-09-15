package com.crystalhitbox.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.CameraRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EndCrystalRenderer;
import net.minecraft.client.renderer.entity.state.EndCrystalRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.Shapes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndCrystalRenderer.class)
public class EndCrystalRendererMixin {

    private static final int COLOR = 0xFF5AD8D6;
    private static final float LINE_WIDTH = 1.0F;

    @Inject(method = "submit", at = @At("HEAD"), cancellable = true)
    private void crystalHitbox(
            EndCrystalRenderState state,
            PoseStack poseStack,
            SubmitNodeCollector collector,
            CameraRenderState camera,
            CallbackInfo ci
    ) {
        AABB hitbox = new AABB(
                -1.0, 0.0, -1.0,
                 1.0, 2.0,  1.0
        );

        collector.submitShapeOutline(
                poseStack,
                Shapes.create(hitbox),
                RenderTypes.lines(),
                COLOR,
                LINE_WIDTH,
                false
        );

        ci.cancel();
    }
}
