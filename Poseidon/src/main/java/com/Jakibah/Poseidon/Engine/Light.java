package com.Jakibah.Poseidon.Engine;

import org.joml.Vector3f;

public class Light {
	
	private Vector3f position;
	private float brightness, radius;

	
	
	
	
	public Light(Vector3f position, float brightness, float radius) {
		this.position = position;
		this.setBrightness(brightness);
		this.setRadius(radius*32);
	}
	
	
	
	public Vector3f getPosition() {
		return position;
	}
	public void setPosition(Vector3f position) {
		this.position = position;
	}



	public float getBrightness() {
		return brightness;
	}



	public void setBrightness(float brightness) {
		this.brightness = brightness;
	}



	public float getRadius() {
		return radius;
	}



	public void setRadius(float radius) {
		this.radius = radius*32;
	}



	
	

}
