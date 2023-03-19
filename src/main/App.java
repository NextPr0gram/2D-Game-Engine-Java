package main;

import main.engine.Window;

public class App {
    public static void main(String[] args) throws Exception {
        Window window = Window.get();
        window.run();
    }
}
