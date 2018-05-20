package com.deep.spaceglad;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;

public class GameWorld {
    private static final float FOV = 67f;
    private ModelBatch modelBatch;
    private Environment environment;
    private PerspectiveCamera perspectiveCamera;

    public GameWorld(){
        initModelBatch();
        initEnvironemnt();
        initPerspectiveCamera();
    }

    private void initModelBatch(){
        modelBatch = new ModelBatch();
    }

    private void initEnvironemnt(){
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.3f, 0.3f, 0.3f, 1f));
    }

    private void initPerspectiveCamera(){
        perspectiveCamera = new PerspectiveCamera(FOV, Core.VIRTUAL_WIDTH, Core.VIRTUAL_HEIGHT);
        perspectiveCamera.position.set(30f,40f,30f);
        perspectiveCamera.lookAt(0f,0f,0f);
        perspectiveCamera.near = 1f;
        perspectiveCamera.far = 300f;
        perspectiveCamera.update();
    }

    public void dispose(){
        modelBatch.dispose();
    }

    public void resize(int width, int height){
        perspectiveCamera.viewportWidth = width;
        perspectiveCamera.viewportHeight = height;
    }

    public void render(float delta){
        modelBatch.begin(perspectiveCamera);
        modelBatch.end();
    }
}
