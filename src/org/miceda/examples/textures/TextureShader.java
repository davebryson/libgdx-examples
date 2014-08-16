package org.miceda.examples.textures;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;

public class TextureShader implements ApplicationListener {
    private static final String TAG = "TextureShader";

    private ShaderProgram shader;
    private Texture texture;
    private Matrix4 mat = new Matrix4();
    private Mesh mesh;

    @Override
    public void create() {
        String vert = Gdx.files.internal("assets/data/shaders/Marker.vert").readString();
        String frag = Gdx.files.internal("assets/data/shaders/Marker.frag").readString();
        shader = new ShaderProgram(vert, frag);
        if (!shader.isCompiled()) {
            Gdx.app.log(TAG, shader.getLog());
            Gdx.app.exit();
        }

        mesh = new Mesh(true, 4, 6, VertexAttribute.Position(),
                VertexAttribute.ColorUnpacked(), VertexAttribute.TexCoords(0));
        // Use their coordinates
        mesh.setVertices(new float[] {
                -1f, -1f, 0, 1, 1, 1, 1, 0, 1, // Coord-Color-UV LL
                1f, -1f, 0, 1, 1, 1, 1, 1, 1, // LR
                1f, 1f, 0, 1, 1, 1, 1, 1, 0, // UR
                -1f, 1f, 0, 1, 1, 1, 1, 0, 0 // UL
        });
        mesh.setIndices(new short[] { 0, 1, 2, 2, 3, 0 });
        texture = new Texture(Gdx.files.internal("assets/data/badlogic.jpg"));
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 0f);

        texture.bind();

        // Bind the SurfaceImage here myself

        shader.begin();
        shader.setUniformMatrix("u_matrix", mat);
        shader.setUniformi("u_texture", 0);
        mesh.render(shader, GL20.GL_TRIANGLES);
        shader.end();
    }

    @Override
    public void dispose() {
        mesh.dispose();
        texture.dispose();
        shader.dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resize(int arg0, int arg1) {
        Gdx.gl20.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void resume() {
    }

}
