package com.crystalhitbox.mixin;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.VertexRendering;
import net.minecraft.client.render.entity.EndCrystalEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndCrystalEntityRenderer.class)
public class EndCrystalRendererMixin {
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void crystalHitbox(EndCrystalEntity entity,float yaw,float tickDelta,
            MatrixStack matrices,VertexConsumerProvider consumers,int light,CallbackInfo ci){

        Box box = entity.getBoundingBox()
                .offset(-entity.getX(), -entity.getY(), -entity.getZ());

        VertexConsumer lines = consumers.getBuffer(RenderLayer.getLines());

        VertexRendering.drawBox(
                matrices,
                lines,
                box,
                90f/255f,
                216f/255f,
                214f/255f,
                1.0f
        );

        ci.cancel();
    }
}
