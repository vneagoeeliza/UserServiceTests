package utils;

import configuration.Toolkit;


public class PropertiesLoader {
    public Toolkit toolkit;
    public PropertiesLoader() {
        initialize();
    }

    private void initialize() {
        toolkit = new Toolkit();
    }
}

