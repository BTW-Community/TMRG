package btw.community.tmrg;

import btw.AddonHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.EntityPlayerMP;

public class UpdateRunnerPos {
    public static int targetX, targetZ, targetDimension;
    public static String runner;
    private static long timeAtLastUpdate = 0;
    private static final int TICKS_PER_SEC = 20; // 20 ticks per second * 60 seconds

    public static void updateTarget() {
        long curTime = MinecraftServer.getServer().worldServers[0].getTotalWorldTime();
        //AddonHandler.logMessage("time at last update: " + timeAtLastUpdate + " and current time is :" + curTime + " so it should check in " + ((timeAtLastUpdate+UPDATE_INTERVAL_TICKS) - curTime) + " ticks");
        if (curTime >= timeAtLastUpdate + ((long) TrackCoolDownCommand.getCoolDown() * TICKS_PER_SEC)) {
            timeAtLastUpdate = curTime;
            updateRunnerPos();
        }
    }
    private static void updateRunnerPos() {
        EntityPlayerMP target = MinecraftServer.getServer().getConfigurationManager().getPlayerEntity(runner);
        if (target != null) {
            AddonHandler.logMessage("New target location for " + runner + " at X:" + target.posX + " Z:" + target.posZ + " Dimension:" + target.dimension);
            targetX = (int) Math.floor(target.posX);
            targetZ = (int) Math.floor(target.posZ);
            targetDimension = target.dimension;
        }
    }

    public static void setRunner(String name){
        runner = name;
    }
    public static String getRunner(){
        return runner;
    }
    public static int getTargetX(){
        return targetX;
    }
    public static int getTargetZ(){
        return targetZ;
    }
    public static int getTargetDimension(){
        if (runner != null)
            return targetDimension;

        return 100;
    }
}
