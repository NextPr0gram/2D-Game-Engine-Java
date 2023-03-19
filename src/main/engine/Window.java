package main.engine;

import org.lwjgl.Version;

public class Window {
    private int width, height;
    private String title;

    private static Window window = null;

    private Window() {
        this.width = 1920;
        this.height = 1080;
        this.title = "game-engine";
    }

    public static Window get() {
        if (Window.window == null) {
            Window.window = new Window();
        }
        return window;
    }

    public void run() {
        System.out.println("LWJGL " + Version.getVersion());
    }
}
