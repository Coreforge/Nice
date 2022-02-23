package coreforge.nice.mixin;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.Style;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.client.font.TextRenderer$Drawer")
public class DrawerMixin {
    int lastChar;

    @Shadow
    public boolean accept(int i, Style style, int j){ throw new RuntimeException();}
    /*@ModifyArg(method = "accept",at = @At(value = "INVOKE",target = "Lnet/minecraft/client/font/FontStorage;getGlyphRenderer(I)Lnet/minecraft/client/font/GlyphRenderer;"),index = 0)
    private int getGlyphStuff(int i){
        return 'Ö';
    } */

    @Inject(at = @At("TAIL"), method = "accept")
    public void theNiceMethod(int i, Style style, int j, CallbackInfoReturnable<Boolean> cir){

        if(lastChar == '6' && j == '9'){
            //System.out.println("Very Nice!");
            accept(i,style,'(');
            accept(i,style,'n');
            accept(i,style,'i');
            accept(i,style,'c');
            accept(i,style,'e');
            accept(i,style,')');
        }
        lastChar = j;
    }
}
