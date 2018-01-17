package com.Jakibah.Poseidon.Engine;

public abstract class Component {
	
	private Entity parent;
	private String name;
	/*
	 * A component can be given to every entity.
	 * Components have acces to all Entity class data trough
	 * the parent.
	 */
	
	public Component(Entity parent, String name){
		this.setParent(parent);
		parent.getComponents().add(this);
		this.setName(name);
	}
	
	public abstract void update();

	public Entity getParent() {
		return parent;
	}

	public void setParent(Entity parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
