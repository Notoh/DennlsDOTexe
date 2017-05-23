package io.notoh.dennls.command;

import net.minecraft.client.Minecraft;

/**
 * Represents a command.
 * @author Notoh
 */
public abstract class Command {

    protected Minecraft mc = Minecraft.getMinecraft();

    private String command;
    private String description;
    protected char prefix = '.';

    public Command(String cmd, String description) {
        this.command = cmd;
        this.description = description;
    }

    public String getCommandName() {
        return command;
    }

    public String getDescription() {
        return this.description;
    }

    public abstract void execute(String command, String[] args) throws RuntimeException;

    public abstract String getSyntax();


}
