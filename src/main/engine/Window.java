package main.engine;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {
    private int width, height;
    private String title;
    private long glfwWindow;

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

        init();
        loop();
    }

    private void init() {
        // Setup rror callback: this is where GLFW will print to if there are any errors
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        // Create the window
        glfwWindow = glfwCreateWindow(width, height, title, NULL, NULL);
        

    }

    private void loop() {
    }

}
