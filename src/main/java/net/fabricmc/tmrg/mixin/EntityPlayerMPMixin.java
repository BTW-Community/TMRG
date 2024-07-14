package net.fabricmc.tmrg.mixin;

import btw.block.tileentity.beacon.MagneticPoint;
import btw.community.tmrg.UpdateRunnerPos;
import net.minecraft.src.EntityPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(EntityPlayerMP.class)
public class EntityPlayerMPMixin {

    //
    //@Inject(method = "updateMagneticInfluences", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/EntityPlayerMP;setHasValidMagneticPointForLocation(Z)V", ordinal = 0, ), locals = LocalCapture.CAPTURE_FAILHARD)
    //TODO: allow tracking in the nether
    @Inject(method = "updateMagneticInfluences", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/World;getMagneticPointList()Lbtw/block/tileentity/beacon/MagneticPointList;", /*ordinal = 0,*/ shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    private void injectedPoint(CallbackInfo ci, MagneticPoint strongestPoint) {
        if (UpdateRunnerPos.getRunner() != null) {
            UpdateRunnerPos.updateTarget();
            if(UpdateRunnerPos.getTargetDimension() == ((EntityAccessor)this).getPlayerDimension()) {
                strongestPoint = new MagneticPoint( UpdateRunnerPos.getTargetX(), 0, UpdateRunnerPos.getTargetZ(), 8 );
            }
        }
    }

/*
    @ModifyVariable(method = "updateMagneticInfluences", at = @At("STORE"), ordinal = 0)
    private MagneticPoint injected(MagneticPoint val) {
        if (UpdateRunnerPos.getRunner() != null) {
            if(UpdateRunnerPos.getTargetDimension() == ((EntityAccessor)this).getPlayerDimension()) {
                MagneticPoint target = new MagneticPoint( UpdateRunnerPos.getTargetX(), 0, UpdateRunnerPos.getTargetZ(), 8 );
                return target;
            }
        }
        return val;
    }
    */
}
