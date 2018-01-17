package com.Jakibah.Poseidon.Engine;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector3f;

import com.Jakibah.Poseidon.Engine.Render.TexturedModel;
import com.Jakibah.Poseidon.Engine.Utils.Maths;
import com.Jakibah.Poseidon.Games.OceanumPirata.DataBank;

public class Entity {

	private Vector3f position;
	private float rotX, rotY, rotZ;
	private float scale;
	private TexturedModel model;
	private List<Component> components = new ArrayList<Component>();
	private boolean translateable, lighted;
	private boolean solid;

	public Entity(Vector3f position, float rotX, float rotY, float rotZ,
			float scale,boolean lighted, boolean solid, TexturedModel model,
			boolean translateable) {
		this.setModel(model);
		this.position = position;
		this.setTranslateable(translateable);
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
		this.setSolid(solid);
		this.setLighted(lighted);

	}
	
	public boolean hasComponent(String name) {
		
		for(Component c: components) {
			if(c.getName().equals(name)) {
				return true;
			}
		}
		
		return false;
	}

	public void update() {
		for(Component c : components) {
			c.update();
		}
		Window.masterRenderer.processEntity(this);
	}

	public void increaseRotation(float dx, float dy, float dz) {
		rotX += dx;
		rotY += dy;
		rotZ += dz;
	}

	public void increasePosition(float dx, float dy, float dz) {
		this.getPosition().x += dx;
		this.getPosition().y += dy;
		this.getPosition().z += dz;

	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getRotX() {
		return rotX;
	}

	public void setRotX(float rotX) {
		this.rotX = rotX;
	}

	public float getRotY() {
		return rotY;
	}

	public void setRotY(float rotY) {
		this.rotY = rotY;
	}

	public float getRotZ() {
		return rotZ;
	}

	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public TexturedModel getModel() {
		return model;
	}

	public void setModel(TexturedModel model) {
		this.model = model;
	}

	public List<Component> getComponents() {
		return components;
	}

	public void setComponents(List<Component> components) {
		this.components = components;
	}

	public boolean isTranslateable() {
		return translateable;
	}

	public void setTranslateable(boolean translateable) {
		this.translateable = translateable;
	}

	public boolean isLighted() {
		return lighted;
	}

	public void setLighted(boolean lighted) {
		this.lighted = lighted;
	}

	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

}
