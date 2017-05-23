package io.notoh.dennls.command.cmds;

import io.notoh.dennls.Dennls;
import io.notoh.dennls.command.Command;
import io.notoh.dennls.mods.Module;

/**
 * Created by alexa on 5/23/2017.
 */
public class Toggle extends Command {

    public Toggle() {
        super("toggle","Toggles a module");
    }

    @Override
    public void execute(String command, String[] args) throws RuntimeException {
        boolean found = false;
        for(Module module : Dennls.getClient().getMods()) {
            if(args[0].equalsIgnoreCase(module.getName())) {
                module.toggle();
                found = true;
                Dennls.addChatMessage(module.getName() + " was toggled.");

            }
        }
        if(!found) {
            Dennls.addChatMessage("Module not found");
        }
    }

    @Override
    public String getSyntax() {
        return prefix + "toggle <mod>";
    }
}
