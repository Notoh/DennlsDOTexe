package io.notoh.dennls;

import io.notoh.dennls.gui.ClientGui;
import io.notoh.dennls.mods.ChangeMode;
import io.notoh.dennls.mods.Mode;
import io.notoh.dennls.mods.Module;
import io.notoh.dennls.mods.blatant.Fly;
import io.notoh.dennls.mods.blatant.KillAura;
import io.notoh.dennls.mods.blatant.NoFall;
import io.notoh.dennls.mods.blatant.Nuker;
import io.notoh.dennls.mods.ghost.Sprint;
import io.notoh.dennls.mods.ghost.Triggerbot;
import io.notoh.dennls.mods.render.ESP;
import io.notoh.dennls.mods.render.Fullbright;
import io.notoh.dennls.mods.render.NukerESP;
import io.notoh.dennls.mods.render.Tracers;
import org.lwjgl.opengl.Display;

import java.util.ArrayList;
import java.util.List;

/**
 * Main client class
 * @author Notoh
 */
public class ClientEntry {

    private ArrayList<Module> mods;
    private static ClientEntry instance;
    private static ClientGui gui;
    public static Nuker nuker;
    public static Fly fly;
    public static NoFall noFall;
    public static KillAura killAura;
    public static Triggerbot triggerbot;
    public static NukerESP nukerESP;
    public static Fullbright fullbright;
    public static Tracers tracers;
    public static ESP esp;
    public static Sprint sprint;
    private static Mode active = Mode.BYPASS;


    public static ClientEntry getInstance() {
        return instance;
    }

    public ClientEntry() {
        mods = new ArrayList<>();
        instance = this;
        registerMod(new ChangeMode());
        //blatant mods
        registerMod(fly = new Fly());
        registerMod(nuker = new Nuker());
        registerMod(noFall = new NoFall());
        registerMod(killAura = new KillAura());
        //ghost mods
        registerMod(triggerbot = new Triggerbot());
        registerMod(sprint = new Sprint());
        //render mods
        registerMod(nukerESP = new NukerESP());
        registerMod(fullbright = new Fullbright());
        registerMod(tracers = new Tracers());
        registerMod(esp = new ESP());

        gui = new ClientGui();
        Display.setTitle("DennlsDOTExe (test build 1.0.1)");
    }

    public List<Module> getMods() {
        return this.mods;
    }

    public void registerMod(Module module) {
        this.mods.add(module);
    }

    public static ClientEntry getClient() {
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
}
