package net.fabricmc.tmrg.mixin;

import btw.AddonHandler;
import btw.community.tmrg.UpdateRunnerPos;
import net.minecraft.src.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin {

    //this is the working single player code for reference:
    @Inject(method = "hasValidMagneticPointForLocation", at = @At("HEAD"), cancellable = true)
    void hasValidMagneticPointForLocation(CallbackInfoReturnable<Boolean> cir){
        if (UpdateRunnerPos.getRunner() != null) {
            //UpdateRunnerPos.updateTarget();
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "getStongestMagneticPointForLocationI", at = @At("HEAD"), cancellable = true)
    void getStongestMagneticPointForLocationI(CallbackInfoReturnable<Integer> cir){
        if(UpdateRunnerPos.getTargetDimension() == ((EntityAccessor)this).getPlayerDimension()) {
            //AddonHandler.logMessage("overridden magnetic point I:" + UpdateRunnerPos.getTargetX());
            cir.setReturnValue(UpdateRunnerPos.getTargetX());
        }
    }

    @Inject(method = "getStongestMagneticPointForLocationK", at = @At("HEAD"), cancellable = true)
    void getStongestMagneticPointForLocationK(CallbackInfoReturnable<Integer> cir){
        if(UpdateRunnerPos.getTargetDimension() == ((EntityAccessor)this).getPlayerDimension()) {
            //AddonHandler.logMessage("overridden magnetic point K:" + UpdateRunnerPos.getTargetZ());
            cir.setReturnValue(UpdateRunnerPos.getTargetZ());
        }
    }
}
