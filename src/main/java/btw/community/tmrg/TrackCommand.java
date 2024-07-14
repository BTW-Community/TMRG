package btw.community.tmrg;

import net.fabricmc.tmrg.mixin.EntityAccessor;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;

import java.util.Collections;
import java.util.List;

public class TrackCommand extends CommandBase {
    private String trackedPlayer;

    public String getCommandName() {
        return "track";
    }

    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "set who you want to track with /track <player> and to get the currently tracked player don't specify a name";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        // tell the currently tracked player if no argument is given
        if (args.length == 0) {
            if (trackedPlayer != null) {
                sender.sendChatToPlayer(ChatMessageComponent.createFromText("\247bYou are currently tracking " + trackedPlayer));
            } else {
                sender.sendChatToPlayer(ChatMessageComponent.createFromText("\247eYou are not tracking anyone /track <player> to track someone"));
            }
        } else if (args.length == 1) {
            EntityPlayerMP player = getPlayer(sender, args[0]);
            if (player == null) {
                throw new PlayerNotFoundException();
            }
            trackedPlayer = player.getEntityName();
            UpdateRunnerPos.setRunner(trackedPlayer);
            //player.getUniqueID()

            sender.sendChatToPlayer(ChatMessageComponent.createFromText("\247bYou are now tracking " + trackedPlayer));

        } else {
            sender.sendChatToPlayer(ChatMessageComponent.createFromText("\247cError: too many arguments"));
        }
    }

    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        // Provide tab completion options
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, this.getAllUsernames()) : null;
    }

    protected String[] getAllUsernames()
    {
        return MinecraftServer.getServer().getAllUsernames();
    }
    
    public boolean isUsernameIndex(String[] args, int index) {
        // Define if any argument is a username
        return index == 0;
    }
}
