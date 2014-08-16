package org.miceda.examples;



import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import org.miceda.examples.textures.TextureShader;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.height = 360;
        config.width = 640;
        
        new LwjglApplication(new TextureShader(), config);
        
    }
}
