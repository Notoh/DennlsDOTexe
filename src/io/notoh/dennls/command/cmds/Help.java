package io.notoh.dennls.command.cmds;

import io.notoh.dennls.Dennls;
import io.notoh.dennls.command.Command;

/**
 * Created by alexa on 5/23/2017.
 */
public class Help extends Command {

    public Help() {
        super("help","Gets a helpful command list");
    }

    @Override
    public void execute(String command, String[] args) throws RuntimeException {
        Dennls.addChatMessage("Available Commands:");
        for(Command cmd : Dennls.getClient().commandManager.getCommands()) {
            Dennls.addChatMessage(cmd.getSyntax() + " - " + cmd.getDescription());
        }
    }

    @Override
    public String getSyntax() {
        return prefix + "help";
    }
}
