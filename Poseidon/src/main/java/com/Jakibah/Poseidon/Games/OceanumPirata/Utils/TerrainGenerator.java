package com.Jakibah.Poseidon.Games.OceanumPirata.Utils;

import org.joml.Vector3f;

import com.Jakibah.Poseidon.Engine.Utils.NoiseGenerator;
import com.Jakibah.Poseidon.Games.OceanumPirata.World.Chunk;
import com.Jakibah.Poseidon.Games.OceanumPirata.World.Tile;
import com.Jakibah.Poseidon.Games.OceanumPirata.World.TileType;

public class TerrainGenerator extends ChunkGenerator {

	@Override
	public Tile[][] generate(int xId, int yId) {
		System.out.println(xId + ", " + yId);
		float[][] noise = NoiseGenerator.generateOctavedSimplexNoise((float) xId * Chunk.SIZE, (float) yId * Chunk.SIZE,
				Chunk.SIZE, Chunk.SIZE, 8, 0.3f, 0.005f);
		Tile[][] tiles = new Tile[Chunk.SIZE][Chunk.SIZE];
		for (int i = 0; i < noise.length; i++) {
			for (int j = 0; j < noise[i].length; j++) {
				if (noise[i][j] < 0.85f) {
					tiles[i][j] = new Tile(new Vector3f(i * 32 + (xId * Chunk.SIZE * 32), j * 32 + (yId * Chunk.SIZE * 32), 0), 0, 0, 0, 1, TileType.Water);
				} else if (noise[i][j] < 0.9f) {
					tiles[i][j] = new Tile(new Vector3f(i * 32 + (xId * Chunk.SIZE * 32), j * 32 + (yId * Chunk.SIZE * 32), 0), 0, 0, 0, 1, TileType.Sand);
				} else {
					tiles[i][j] = new Tile(new Vector3f(i * 32 + (xId * Chunk.SIZE * 32), j * 32 + (yId * Chunk.SIZE * 32), 0), 0, 0, 0, 1, TileType.Grass);
				}
			}
		}
		return tiles;
	}

}
