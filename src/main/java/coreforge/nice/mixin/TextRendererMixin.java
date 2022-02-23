package coreforge.nice.mixin;


import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.OrderedText;
import net.minecraft.util.math.Matrix4f;
import org.checkerframework.common.reflection.qual.Invoke;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TextRenderer.class)
public class TextRendererMixin {

    //@Inject(at = @At("INVOKE"),method = "draw(Ljava/lang/String;FFILnet/minecraft/util/math/Matrix4f;ZZ)I")
    public void draw(String text, float x, float y, int color, Matrix4f matrix, boolean shadow, boolean mirror, CallbackInfoReturnable<Integer> cir){
        text = "nice";
        //System.out.println("Text!");
    }

    @ModifyArg(method = "draw(Ljava/lang/String;FFILnet/minecraft/util/math/Matrix4f;ZZ)I", at = @At(value = "INVOKE",target = "Lnet/minecraft/client/font/TextRenderer;draw(Ljava/lang/String;FFIZLnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider;ZIIZ)I"),index = 0)
    private String injected(String text){
        return "nice";
    }

    //@ModifyArg(method = "drawInternal(Ljava/lang/String;FFIZLnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider;ZIIZ)I",at = @At(value = "INVOKE",target = "Lnet/minecraft/client/font/TextRenderer;drawLayer(Ljava/lang/String;FFIZLnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider;ZII)F"),index = 0)
    private String injected2(String text){
        return "nice";
    }

    @ModifyArg(method = "draw(Lnet/minecraft/text/OrderedText;FFILnet/minecraft/util/math/Matrix4f;Z)I",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/text/OrderedText;FFIZLnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider;ZII)I"),index = 3)
    private int injected3(int text){
        return 32;
    }

    /*@ModifyArg(method = "drawLayer(Ljava/lang/String;FFIZLnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider;ZII)F",at = @At("INVOKE"),index = 0)
    private String injected4(String text){
        return "nice";
    }*/
}
