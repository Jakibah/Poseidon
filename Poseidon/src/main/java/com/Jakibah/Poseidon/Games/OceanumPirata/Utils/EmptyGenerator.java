package com.Jakibah.Poseidon.Games.OceanumPirata.Utils;

import org.joml.Vector3f;

import com.Jakibah.Poseidon.Games.OceanumPirata.World.Chunk;
import com.Jakibah.Poseidon.Games.OceanumPirata.World.Tile;
import com.Jakibah.Poseidon.Games.OceanumPirata.World.TileType;

public class EmptyGenerator extends ChunkGenerator {

	@Override
	public Tile[][] generate(int xId, int yId) {
		Tile[][] tiles = new Tile[Chunk.SIZE][Chunk.SIZE];
		for(int i = 0; i < tiles.length; i++){
			for(int j = 0; j < tiles[i].length; j++){
				tiles[i][j] = new Tile(new Vector3f(i*32 + (xId * 3200),j*32 + (yId*3200),0),0,0,0,1, TileType.Grass);
			}
		}
		return tiles;
	}

}
