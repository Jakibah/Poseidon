package com.Jakibah.Poseidon.Games.OceanumPirata.World;

import org.joml.Vector3f;

import com.Jakibah.Poseidon.Engine.Entity;

public class Tile extends Entity {
	
	private TileType type;

	public Tile(Vector3f position, float rotX, float rotY, float rotZ,
			float scale, TileType type) {
		super(position, rotX, rotY, rotZ, scale, true, type.isSolid(), type.getModel(), true);
		this.setType(type);
		
	}

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
	}

}
