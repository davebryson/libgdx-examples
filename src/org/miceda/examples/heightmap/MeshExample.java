package org.miceda.examples.heightmap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class MeshExample extends ApplicationAdapter {
    ShaderProgram shader;
    Mesh mesh;
    Matrix4 matrix = new Matrix4();
    PerspectiveCamera camera;

    @Override
    public void create() {
        String vertexShader = "attribute vec4 a_position;    \n"
                + "attribute vec4 a_color;\n"
                + "attribute vec2 a_texCoord0;\n"
                + "uniform mat4 u_worldView;\n"
                + "varying vec4 v_color;"
                + "varying vec2 v_texCoords;"
                + "void main()                  \n" + "{                            \n"
                + "   v_color = vec4(0.5,0.5,0.5,1); \n"
                + "   v_texCoords = a_texCoord0; \n"
                + "   gl_Position =  u_worldView * a_position;  \n"
                + "}                            \n";
        String fragmentShader = "#ifdef GL_ES\n"
                + "precision mediump float;\n"
                + "#endif\n"
                + "varying vec4 v_color;\n"
                + "varying vec2 v_texCoords;\n"
                + "uniform sampler2D u_texture;\n"
                + "void main()                                  \n"
                + "{                                            \n"
                + "  gl_FragColor = v_color;\n"
                + "}";

        shader = new ShaderProgram(vertexShader, fragmentShader);
        if (!shader.isCompiled()) {
            Gdx.app.log("ShaderTest", shader.getLog());
            Gdx.app.exit();
        }

        camera = new PerspectiveCamera(50f, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        camera.far = 1000;
        camera.position.set(0, 0f, 100);
        camera.up.set(0, 1, 0);
        camera.direction.set(0, 0, -1);
        camera.rotateAround(new Vector3(), Vector3.X, 60);
        camera.update();

        CameraInputController camController = new CameraInputController(camera);
        Gdx.input.setInputProcessor(camController);

        mesh = GeometryBuilder.buildPlane(300, 300, 50, true);
    }


    @Override
    public void render() {
        camera.update();

        Gdx.gl20.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl20.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glEnable(GL20.GL_BLEND);
        Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        shader.begin();
        shader.setUniformMatrix("u_worldView", camera.combined);
        mesh.render(shader, GL20.GL_LINES);
        shader.end();
    }

    @Override
    public void dispose() {
        mesh.dispose();
        shader.dispose();
    }

}
