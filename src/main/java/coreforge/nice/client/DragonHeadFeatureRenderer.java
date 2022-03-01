package coreforge.nice.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Arm;
import net.minecraft.util.math.Vec3f;

public class DragonHeadFeatureRenderer<T extends LivingEntity, M extends EntityModel<T> & ModelWithArms> extends FeatureRenderer<T, M> {
    public DragonHeadFeatureRenderer(FeatureRendererContext<T, M> featureRendererContext) {
        super(featureRendererContext);
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l) {
        boolean bl = livingEntity.getMainArm() == Arm.RIGHT;
        ItemStack itemStack = new ItemStack(Items.DRAGON_HEAD);//bl ? livingEntity.getOffHandStack() : livingEntity.getMainHandStack();
        ItemStack itemStack2 = new ItemStack(Items.DRAGON_HEAD);//bl ? livingEntity.getMainHandStack() : livingEntity.getOffHandStack();
        if (!itemStack.isEmpty() || !itemStack2.isEmpty()) {
            matrixStack.push();
            if (this.getContextModel().child) {
                float m = 0.5F;
                matrixStack.translate(0.0D, 0.75D, 0.0D);
                matrixStack.scale(0.5F, 0.5F, 0.5F);
            }

            this.renderItem(livingEntity, itemStack2, ModelTransformation.Mode.THIRD_PERSON_RIGHT_HAND, Arm.RIGHT, matrixStack, vertexConsumerProvider, i);
            this.renderItem(livingEntity, itemStack, ModelTransformation.Mode.THIRD_PERSON_LEFT_HAND, Arm.LEFT, matrixStack, vertexConsumerProvider, i);
            this.renderBoot(livingEntity,itemStack,ModelTransformation.Mode.THIRD_PERSON_LEFT_HAND,matrixStack,vertexConsumerProvider,i,true);
            this.renderBoot(livingEntity,itemStack2,ModelTransformation.Mode.THIRD_PERSON_RIGHT_HAND,matrixStack,vertexConsumerProvider,i,false);
            matrixStack.pop();
        }
    }

    protected void renderItem(LivingEntity entity, ItemStack stack, ModelTransformation.Mode transformationMode, Arm arm, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (!stack.isEmpty()) {
            matrices.push();
            ((ModelWithArms)this.getContextModel()).setArmAngle(arm, matrices);
            //((BipedEntityModel)this.getContextModel()).leftLeg.rotate(matrices);
            matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(-90.0F));
            matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0F));
            boolean bl = arm == Arm.LEFT;
            matrices.translate((double)((float)(bl ? -1 : 1) / 16.0F), 0.125D, -0.625D);
            MinecraftClient.getInstance().getHeldItemRenderer().renderItem(entity, stack, transformationMode, bl, matrices, vertexConsumers, light);
            matrices.pop();
        }
    }

    private void renderBoot(LivingEntity entity, ItemStack stack, ModelTransformation.Mode transformationMode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, boolean left){
        matrices.push();
        //((ModelWithArms)this.getContextModel()).setArmAngle(arm, matrices);
        if(left) {
            ((BipedEntityModel) this.getContextModel()).leftLeg.rotate(matrices);
        } else {
            ((BipedEntityModel) this.getContextModel()).rightLeg.rotate(matrices);
        }
        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(-90.0F));
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0F));
        matrices.translate((double)((float)(left ? -1 : 1) / 16.0F), 0.125D, -0.645D);
        MinecraftClient.getInstance().getHeldItemRenderer().renderItem(entity, stack, transformationMode, left, matrices, vertexConsumers, light);
        matrices.pop();
    }
}