package coreforge.nice.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.*;
import net.minecraft.util.Arm;
import net.minecraft.util.math.Vec3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import coreforge.nice.client.DragonHeadFeatureRenderer;

import java.awt.*;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    HeldItemRenderer rightHeadRenderer = new HeldItemRenderer(MinecraftClient.getInstance());
    ItemStack rightHeadStack = new ItemStack(Items.DRAGON_HEAD,1);

    public PlayerEntityRendererMixin(EntityRendererFactory.Context ctx, PlayerEntityModel<AbstractClientPlayerEntity> model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    /*@Shadow
    protected final boolean addFeature(net.minecraft.client.render.entity.feature.FeatureRenderer<T, M> feature) {
        return false;
    }*/

    @Inject(method = "<init>",at = @At("TAIL"))
    public void init(EntityRendererFactory.Context ctx, boolean slim, CallbackInfo ci){
        this.addFeature(new DragonHeadFeatureRenderer<>(this));
    }

    /*@Inject(method = "renderArm",at = @At("TAIL"))
    public void renderArmInject(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity player, ModelPart arm, ModelPart sleeve, CallbackInfo ci){
        rightHeadRenderer.renderItem(player,rightHeadStack, ModelTransformation.Mode.FIRST_PERSON_RIGHT_HAND,false,matrices,vertexConsumers,255);

        arm.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEndPortal()), light, OverlayTexture.DEFAULT_UV);
        matrices.push();
        //((ModelWithArms)this.getContextModel()).setArmAngle(arm, matrices);
        ((PlayerEntityRenderer)((Object)this)).getModel().setArmAngle(player.getMainArm(),matrices);
        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(-90.0F));
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0F));
        boolean bl = false;
        matrices.translate((double)((float)(bl ? -1 : 1) / 16.0F), 0.125D, -0.625D);
        MinecraftClient.getInstance().getHeldItemRenderer().renderItem(player,rightHeadStack, ModelTransformation.Mode.THIRD_PERSON_RIGHT_HAND,true,matrices,vertexConsumers,light);
        matrices.pop();
    }*/
}
