package io.notoh.dennls.command.cmds;

import io.notoh.dennls.Dennls;
import io.notoh.dennls.command.Command;
import io.notoh.dennls.mods.Module;
import org.lwjgl.input.Keyboard;

/**
 * Created by alexa on 5/22/2017.
 */
public class Bind extends Command {

    public Bind() {
        super("bind","Changes keybind of hack.");
    }

    @Override
    public void execute(String command, String[] args) throws RuntimeException {
        if(args[0].equalsIgnoreCase("set")) {
            args[2] = args[2].toUpperCase();

            int key = Keyboard.getKeyIndex(args[2]);
            for(Module module : Dennls.getClient().getMods()) {
                if(args[1].equalsIgnoreCase(module.getName())) {
                    module.setKeyCode(Keyboard.getKeyIndex(Keyboard.getKeyName(key)));
                    Dennls.addChatMessage(args[1] + " has been bound to " + Keyboard.getKeyName(module.getKeyCode()));
                    return;
                }
            }
        } else if(args[0].equalsIgnoreCase("get")) {
            String mod = args[1];
            for(Module module : Dennls.getClient().getMods()) {
                if(mod.equalsIgnoreCase(module.getName())) {
                    Dennls.addChatMessage("The key code for " + mod + " is " + Keyboard.getKeyName(module.getKeyCode
                            ()) + ".");
                    return;
                }
            }
            Dennls.addChatMessage("That module doesn't exist!");
        } else {
            throw new RuntimeException("Invalid Syntax");
        }
    }

    @Override
    public String getSyntax() {
        return prefix + "bind set <Module> <Key>";
    }
}
