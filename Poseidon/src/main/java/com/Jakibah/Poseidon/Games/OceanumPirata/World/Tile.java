package com.Jakibah.Poseidon.Games.OceanumPirata.World;

import java.awt.Rectangle;

import org.joml.Vector3f;

import com.Jakibah.Poseidon.Engine.Entity;

public class Tile extends Entity {
	
	private TileType type;
	private Rectangle collider = new Rectangle();

	public Tile(Vector3f position, float rotX, float rotY, float rotZ,
			float scale, TileType type) {
		super(position, rotX, rotY, rotZ, scale, true, type.isSolid(), type.getModel(), true);
		this.setType(type);
		collider.setBounds((int)this.getPosition().x, (int)this.getPosition().y, 32, 32);
		
	}

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
	}

	public Rectangle getCollider() {
		return collider;
	}

	public void setCollider(Rectangle collider) {
		this.collider = collider;
	}

}
