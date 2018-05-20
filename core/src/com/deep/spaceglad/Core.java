package com.deep.spaceglad;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

public class Core extends ApplicationAdapter {

	public PerspectiveCamera perspectiveCamera;

	public Model model;
	public ModelInstance modelInstance;

	public ModelBatch modelBatch;
	public Environment environment;

	@Override
	public void create () {
		perspectiveCamera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		perspectiveCamera.position.set(10f, 10f, 10f);
		perspectiveCamera.lookAt(0 , 0, 0);
		perspectiveCamera.near = 1f;
		perspectiveCamera.far = 300f;
		perspectiveCamera.update();

		ModelBuilder modelBuilder = new ModelBuilder();
		Material material = new Material(ColorAttribute.createDiffuse(Color.BLUE));
		model = modelBuilder.createBox(5,5,5, material,
				VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
		modelInstance = new ModelInstance(model);

		modelBatch = new ModelBatch();
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f,0.8f,0.8f,1f,-0.8f,0.2f));
	}

	Vector3 touch = new Vector3();
	@Override
	public void render () {
	    movement();
		rotate();

		if (Gdx.input.isTouched()) {
			touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			Gdx.app.log("Touch:", "x: "+touch.x+" y: "+touch.y);

			if (touch.x > Gdx.graphics.getWidth() * 0.5f) {
				perspectiveCamera.position.set(perspectiveCamera.position.x + Gdx.graphics.getDeltaTime()*2, 10f, 10f);
				perspectiveCamera.update();
			}
		}

		Gdx.gl.glViewport(0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		modelBatch.begin(perspectiveCamera);
		modelBatch.render(modelInstance, environment);
		modelBatch.end();
	}
	
	@Override
	public void dispose () {
		model.dispose();
	}

	Vector3 position = new Vector3();
	private void movement(){
	    modelInstance.transform.getTranslation(position);
	    position.x += Gdx.graphics.getDeltaTime();
	    modelInstance.transform.setTranslation(position);
    }

    private void rotate(){
		if (Gdx.input.isTouched()){
			modelInstance.transform.rotate(Vector3.X, Gdx.graphics.getDeltaTime() * 100);
		}
	}
}
