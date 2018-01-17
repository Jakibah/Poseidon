package com.Jakibah.Poseidon.Engine.Render;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;

import java.util.List;
import java.util.Map;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import com.Jakibah.Poseidon.Engine.Entity;
import com.Jakibah.Poseidon.Engine.Light;
import com.Jakibah.Poseidon.Engine.Window;
import com.Jakibah.Poseidon.Engine.Shaders.StaticShader;
import com.Jakibah.Poseidon.Engine.Utils.Maths;

public class Renderer {

	private float FOV, Z_NEAR, Z_FAR;
	private StaticShader shader = Window.shader;

	public Renderer(float FOV, float Z_NEAR, float Z_FAR) {
		this.FOV = FOV;
		this.Z_NEAR = Z_NEAR;
		this.Z_FAR = Z_FAR;
		projectionMatrix = Maths.createProjectionMatrix(FOV, Z_NEAR, Z_FAR);
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}

	private Matrix4f projectionMatrix;

	public void prepare() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the
																	// framebuffer
		GL11.glClearColor(0, 0, 0, 1);

	}

	

	public void render(Map<TexturedModel, List<Entity>> entities, List<Light> lights) {
		shader.start();
		for (TexturedModel model : entities.keySet()) {
			prepareTexturedModel(model);
			List<Entity> batch = entities.get(model);
			for (Entity e : batch) {
				prepareInstance(e, lights);
				
			}
			unbindTexturedModel();
		}
		shader.stop();
	}

	private void prepareTexturedModel(TexturedModel model) {
		RawModel rawModel = model.getRawModel();
		GL30.glBindVertexArray(rawModel.getVaoId());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		Texture tex = model.getTexture();
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture()
				.getTextureId());

	}

	private void unbindTexturedModel() {
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}
//x - Main.GAME.p.getX() + Display.getWidth() / 2 - 16
	private void prepareInstance(Entity entity, List<Light> lights) {
		Vector3f finalPosition;
		Window.shader.loadIsLighted(entity.isLighted());
		if(entity.isTranslateable()){
			finalPosition = new Vector3f(((entity.getPosition().x * Window.factorX) - (Window.transPoint.x * Window.factorX))*2, (-((entity.getPosition().y * Window.factorY) - (Window.transPoint.y * Window.factorY))) * 2,entity.getPosition().z-1);
			if(finalPosition.x < -1.1f || finalPosition.x > 1.1f || finalPosition.y > 1.1f || finalPosition.y < -1.1f){
				return;
			}
		}else{
			finalPosition = new Vector3f((entity.getPosition().x * Window.factorX), -(entity.getPosition().y * Window.factorY),entity.getPosition().z);
		}
		
		Matrix4f transofrmationMatrix = Maths.createTransformationMatrix(
				finalPosition, entity.getRotX(), entity.getRotY(),
				entity.getRotZ(), entity.getScale());
		Window.shader.loadTransformationMatrix(transofrmationMatrix);
		Window.shader.loadLight(Maths.calcLight(entity, lights));
		GL11.glDrawElements(GL11.GL_TRIANGLES, entity.getModel().getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
	}

	public float getFOV() {
		return FOV;
	}

	public void setFOV(float fOV) {
		FOV = fOV;
	}

	public float getZ_NEAR() {
		return Z_NEAR;
	}

	public void setZ_NEAR(float z_NEAR) {
		Z_NEAR = z_NEAR;
	}

	public float getZ_FAR() {
		return Z_FAR;
	}

	public void setZ_FAR(float z_FAR) {
		Z_FAR = z_FAR;
	}

}
