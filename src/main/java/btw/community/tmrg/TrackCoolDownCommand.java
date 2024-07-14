package btw.community.tmrg;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;

import java.util.List;

public class TrackCoolDownCommand extends CommandBase {
    public static int coolDown = 300;

    public String getCommandName() {
        return "trackCoolDown";
    }

    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "set the refresh rate of the tracked player position in seconds the default is 300 (5 minutes)";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        // tell the currently tracked player if no argument is given
        if (args.length == 0) {
            sender.sendChatToPlayer(ChatMessageComponent.createFromText("\247bThe current position with update every " + coolDown + " seconds"));
        } else if (args.length == 1) {
            try {
                coolDown = Integer.parseInt(args[0]);
                sender.sendChatToPlayer(ChatMessageComponent.createFromText("\247bTracking cool down set to " + coolDown + " seconds"));
            } catch (NumberFormatException e) {
                // Send an error message if the concatenated string is not a valid int
                sender.sendChatToPlayer(ChatMessageComponent.createFromText("\247cError: What the fuck is " + args[0]));
            }

        } else {
            sender.sendChatToPlayer(ChatMessageComponent.createFromText("\247cError: too many arguments"));
        }
    }

    public static int getCoolDown(){
        return coolDown;
    }
}
