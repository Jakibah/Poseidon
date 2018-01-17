package com.Jakibah.Poseidon.Engine.Shaders;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.Jakibah.Poseidon.Engine.Light;

public class StaticShader extends ShaderProgram {
	
	private static final String VERTEX_FILE = "src/main/resources/Shaders/vertexShader.glsl";
	private static final String FRAGMENT_FILE = "src/main/resources/Shaders/fragmentShader.glsl";
	
	private int location_transformationMatrix;
	
	private int location_projectionMatrix;
	private int location_sunLight;
	private int location_isLighted;
	private int location_light;
	
	

	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
		
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
		super.bindAttribute(2, "normals");

	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_sunLight = super.getUniformLocation("sunLight");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_isLighted = super.getUniformLocation("isLighted");
		location_light = super.getUniformLocation("light");
		
	}
	
	public void loadIsLighted(boolean value){
		super.loadBoolean(location_isLighted, value);
	}
	public void loadSun(Vector3f sun){
		super.loadVector(location_sunLight, sun);
	}
	
	public void loadTransformationMatrix(Matrix4f matrix){
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
	
	public void loadProjectionMatrix(Matrix4f matrix){
		super.loadMatrix(location_projectionMatrix, matrix);
	}
	
	public void loadLight(Vector3f value) {
		super.loadVector(location_light, value);
	}

}
