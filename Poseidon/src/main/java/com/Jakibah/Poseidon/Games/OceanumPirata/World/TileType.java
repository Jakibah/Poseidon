package com.Jakibah.Poseidon.Games.OceanumPirata.World;

import com.Jakibah.Poseidon.Engine.Render.TexturedModel;
import com.Jakibah.Poseidon.Games.OceanumPirata.DataBank;

public enum TileType {
	
	TestTile(DataBank.TEST_TILE, false), Grass(DataBank.GRASS,true), Sand(DataBank.SAND, true), Water(DataBank.WATER, false);
	
	private TexturedModel model;
	private boolean solid;
	TileType(TexturedModel model, boolean solid){
		this.model = model;
		this.solid = solid;
	}

	public TexturedModel getModel(){
		return model;
	}
	public boolean isSolid() {
		return solid;
	}

}
