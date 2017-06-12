package io.notoh.dennls;

import io.notoh.dennls.command.CommandManager;
import io.notoh.dennls.gui.ClientGui;
import io.notoh.dennls.gui.GuiManager;
import io.notoh.dennls.mods.*;
import io.notoh.dennls.mods.blatant.*;
import io.notoh.dennls.mods.ghost.*;
import io.notoh.dennls.mods.render.*;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.ChatComponentText;
import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;
import org.lwjgl.opengl.Display;

import java.util.ArrayList;
import java.util.List;

/**
 * Main client class
 * @author Notoh
 */
public class Dennls {

    private ArrayList<Module> mods;
    private static Dennls instance;
    private static ClientGui gui;
    public static Nuker nuker;
    public static Fly fly;
    public static NoFall noFall;
    public static KillAura killAura;
    public static Aimbot aimbot;
    public static Triggerbot triggerbot;
    public static NukerESP nukerESP;
    public static ChestStealer chestStealer;
    public static Fullbright fullbright;
    public static Tracers tracers;
    public static ChestESP chestESP;
    public static HighJump highJump;
    public static ESP esp;
    public static Sprint sprint;
    public static Step step;
    public static Criticals criticals;
    public static Glide glide;
    public static BypassSpeed bypassSpeed;
    public static SmoothAim smoothAim;
    public static Speed speed;
    public static FastPlace fastPlace;
    public static Waterwalk waterwalk;
    public static Xray xray;
    private static Mode active = Mode.SAFE;
    public CommandManager commandManager;
    public GuiManager guiManager;

    public static Dennls getInstance() {
        return instance;
    }

    public Dennls() {
        mods = new ArrayList<>();
        instance = this;
        registerMod(new ChangeMode());
        //blatant mods
        registerMod(fly = new Fly());
        registerMod(nuker = new Nuker());
        registerMod(noFall = new NoFall());
        registerMod(killAura = new KillAura());
        registerMod(glide = new Glide());
        registerMod(aimbot = new Aimbot());
        registerMod(chestStealer = new ChestStealer());
        registerMod(waterwalk = new Waterwalk());
        registerMod(bypassSpeed = new BypassSpeed());
        registerMod(speed = new Speed());
        registerMod(highJump = new HighJump());
        registerMod(fastPlace = new FastPlace());
        //ghost mods
        registerMod(triggerbot = new Triggerbot());
        registerMod(sprint = new Sprint());
        registerMod(step = new Step());
        registerMod(criticals = new Criticals());
        registerMod(smoothAim = new SmoothAim());
        //render mods
        registerMod(nukerESP = new NukerESP());
        registerMod(fullbright = new Fullbright());
        registerMod(tracers = new Tracers());
        registerMod(esp = new ESP());
        registerMod(chestESP = new ChestESP());
        registerMod(xray = new Xray());
        registerMod(new ClickGui());

        guiManager = new GuiManager();
        guiManager.setTheme(new SimpleTheme());
        guiManager.setup();
        gui = new ClientGui();
        commandManager = new CommandManager();
        Display.setTitle("DennlsDOTExe (test build 2.0.5)");
    }

    public List<Module> getMods() {
        return this.mods;
    }

    public void registerMod(Module module) {
        this.mods.add(module);
    }

    public static Dennls getClient() {
        return instance;
    }

    public void onUpdate() {
        for(Module module : this.mods) {
            module.onUpdate();
        }
    }

    public void onRender() {
        for(Module module : this.mods) {
            module.onRender();
        }
    }

    public void onKeyPress(int keyCode) {
        for(Module module : this.mods) {
            if(module.getKeyCode() == keyCode) {
                module.toggle();
            }
        }
    }

    public static ClientGui getClientGui() {
        return gui;
    }

    public static Mode getActive() {
        return active;
    }

    public static void setActive(Mode change) {
        active = change;
    }

    public static void addChatMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("DennlsDOTExe: " + message));
    }

    public static boolean onSendChatMessage(String message) {
        if (message.startsWith(".")) {
            instance.commandManager.callCommand(message.substring(1));
            return false;
        }

        for (Module module : getClient().getMods()) {
            if (module instanceof ChangeMode) {
                continue;
            }
            if (module.getToggle()) {
                return true;
            }
        }
        return true;
    }

    public static boolean onReceiveChatMessage(S02PacketChat packet) {
        for(Module module : getClient().getMods()) {
            if(module.getToggle()) {
                return true;
            }
        }
        return true;
    }
}
