package org.miceda.examples.heightmap;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {

    
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.height = 400;
        config.width = 600;
        
        new LwjglApplication(new MeshExample(), config);
    }
  

}
