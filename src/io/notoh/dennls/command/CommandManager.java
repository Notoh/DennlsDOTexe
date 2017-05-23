package io.notoh.dennls.command;

import io.notoh.dennls.Dennls;
import io.notoh.dennls.command.cmds.Bind;

import java.util.ArrayList;

/**
 * Created by alexa on 5/22/2017.
 */
public class CommandManager {

    public ArrayList<Command> commands = new ArrayList<>();

    public CommandManager() {
        commands.add(new Bind());
    }

    public ArrayList<Command> getCommands() {
        return this.commands;
    }

    public void callCommand(String input) {
        String[] split = input.split(" ");
        String command = split[0];
        String args = input.substring(command.length()).trim();
        for(Command c : this.commands) {
            if(c.getCommandName().equalsIgnoreCase(command)) {
                try {
                    c.execute(args, args.split(" "));
                } catch(RuntimeException e) {
                    Dennls.addChatMessage("Invalid usage! Usage is " + c.getSyntax());
                }
                return;
            }
        }
        Dennls.addChatMessage("Command not found!");
    }

}
