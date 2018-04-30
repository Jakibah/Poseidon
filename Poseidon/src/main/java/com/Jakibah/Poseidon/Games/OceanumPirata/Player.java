package com.Jakibah.Poseidon.Games.OceanumPirata;

import java.awt.Rectangle;

import org.joml.Vector3f;

import com.Jakibah.Poseidon.Engine.Entity;
import com.Jakibah.Poseidon.Games.OceanumPirata.Components.PlayerController;



public class Player extends Entity {
	

	PlayerController controller;
	private Rectangle collider = new Rectangle();
	
	public Player(Vector3f position, float rotX, float rotY, float rotZ,
			float scale) {
		super(position, rotX, rotY, rotZ, scale, false, false, DataBank.PLAYER, true);
		this.setModel(DataBank.PLAYER);
		this.controller = new PlayerController(this);
		collider.setBounds((int)this.getPosition().x, (int)this.getPosition().y, 32, 32);
		
	}
	@Override
	public void update(){
		super.update();
		
		
	}
	public Rectangle getCollider() {
		collider.setBounds((int) this.getPosition().x, (int) this.getPosition().y, 32,32);
		return collider;
	}
	public void setCollider(Rectangle collider) {
		this.collider = collider;
	}

	

}
