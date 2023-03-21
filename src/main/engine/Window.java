package main.engine;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_MAXIMIZED;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

/* What is OpenGL?
 * OpenGL is an API, its an interface that tells the GPU what to do.
 * It allows to create game window, render models, textures, sprites and everything else.
 * 
 * What is GLFW?
 * GLFW is a separate library that handles things like creating windows and receiving
 * inputs in a cross platform way.
 */

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
        // Setup error callback: this is where GLFW will print to if there are any
        // errors
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
        if (glfwWindow == NULL) {
            throw new IllegalStateException("Failed to create the GLFW window");
        }

        /*
         * What is a context?
         * A context is a container that holds all of OpenGl. All the states and objects
         * that are created are part of this context. It also includes the window which
         * is rendered to.
         */

        // Make the OpenGL context current
        glfwMakeContextCurrent(glfwWindow);

        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(glfwWindow);

        /*
         * This line is critical for LWJGL's interoperation with GLFW's
         * OpenGL context, or any context that is managed externally.
         * LWJGL detects the context that is current in the current thread,
         * creates the GLCapabilities instance and make the OpenGl
         * bindings available for use.
         */
        GL.createCapabilities();

    }

    private void loop() {
        while (!glfwWindowShouldClose(glfwWindow)) {
            // Poll events (key events, mouse events...)
            glfwPollEvents();

            glClearColor(1, 1, 1, 1);

            // clear the framebuffer
            glClear(GL_COLOR_BUFFER_BIT);

            glfwSwapBuffers(glfwWindow);
        }
    }

}
