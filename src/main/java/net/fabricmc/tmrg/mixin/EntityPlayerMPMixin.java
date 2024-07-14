package net.fabricmc.tmrg.mixin;

import btw.AddonHandler;
import btw.block.tileentity.beacon.MagneticPoint;
import btw.community.tmrg.UpdateRunnerPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.EntityPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(EntityPlayerMP.class)
public class EntityPlayerMPMixin {

    //
    //@Inject(method = "updateMagneticInfluences", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/EntityPlayerMP;setHasValidMagneticPointForLocation(Z)V", ordinal = 0, ), locals = LocalCapture.CAPTURE_FAILHARD)
    /*
    @Inject(method = "updateMagneticInfluences", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/World;getMagneticPointList()Lbtw/block/tileentity/beacon/MagneticPointList;", *//*ordinal = 0,*//* shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    private void injectedPoint(CallbackInfo ci, MagneticPoint strongestPoint) {
        if (UpdateRunnerPos.getRunner() != null) {
            UpdateRunnerPos.updateTarget();
            if(UpdateRunnerPos.getTargetDimension() == ((EntityAccessor)this).getPlayerDimension()) {
                strongestPoint = new MagneticPoint( UpdateRunnerPos.getTargetX(), 0, UpdateRunnerPos.getTargetZ(), 8 );
            }
        }
    }

    @Inject(method = "updateMagneticInfluences", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/EntityPlayerMP;setHasValidMagneticPointForLocation(Z)V", ordinal = 0, shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    private void injectedPoint2(CallbackInfo ci, MagneticPoint strongestPoint) {
        if (MinecraftServer.getServer().worldServers[0].getTotalWorldTime() % 120L == 0L) {
            AddonHandler.logMessage("Strongest magnetic point at X:" + strongestPoint.posX + " Z:" + strongestPoint.posZ);
        }
    }
*/
    /*
    @Inject(method = "updateMagneticInfluences", at = @At("HEAD"), cancellable = true)
    void hasValidMagneticPointForLocation(CallbackInfo ci){
        if (UpdateRunnerPos.getRunner() != null) {
            UpdateRunnerPos.updateTarget();
            if(UpdateRunnerPos.getTargetDimension() == ((EntityAccessor)this).getPlayerDimension()) {
                this.setHasValidMagneticPointForLocation(true);
                this.setStrongestMagneticPointForLocationI(UpdateRunnerPos.getTargetX());
                this.setStrongestMagneticPointForLocationK(UpdateRunnerPos.getTargetZ());
                ci.cancel();
            }
        }
    }
    */

    @ModifyVariable(method = "updateMagneticInfluences", name = "strongestPoint", at = @At("STORE"), ordinal = -1)
    private MagneticPoint injected(MagneticPoint val) {
        if (UpdateRunnerPos.getRunner() != null) {
            UpdateRunnerPos.updateTarget();
            if(UpdateRunnerPos.getTargetDimension() == ((EntityAccessor)this).getPlayerDimension()) {
                MagneticPoint target = new MagneticPoint( UpdateRunnerPos.getTargetX(), 0, UpdateRunnerPos.getTargetZ(), 8 );
                return target;
            }
        }
        return val;
    }

}
