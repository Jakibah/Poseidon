package com.Jakibah.Poseidon.Games.OceanumPirata.World;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;

import com.Jakibah.Poseidon.Engine.Window;
import com.Jakibah.Poseidon.Engine.Utils.SimplexNoise;
import com.Jakibah.Poseidon.Games.OceanumPirata.Player;
import com.Jakibah.Poseidon.Games.OceanumPirata.Utils.TerrainGenerator;

public class World {
	
	private static int seed_, startXId, startYId;
	
	public static List<Chunk> activeChunks = new ArrayList<Chunk>();
	public static List<Chunk> activeChunksToAdd = new ArrayList<Chunk>();
	public static List<Chunk> activeChunksToRemove = new ArrayList<Chunk>();
	public static Player player;

	
	
	public World(int seed, Vector2f pCoords, Player p) {
		World.player = p;
		seed_ = seed;
		startXId =  (int) Math.floor(pCoords.x / (Chunk.SIZE * 32));
		startYId =  (int) Math.floor(pCoords.y / (Chunk.SIZE * 32));
		new SimplexNoise(seed_);
		activeChunks.add(new Chunk(startXId, startYId, new TerrainGenerator(), this));
	}
	
	
	
	public static void update() {
		activeChunks.removeAll(activeChunksToRemove);
		activeChunksToRemove.clear();
		activeChunks.addAll(activeChunksToAdd);
		activeChunksToAdd.clear();
		for(Chunk c : activeChunks) {
			c.update();
		}
	}
	public static Chunk getChunkAtCoords(float x, float y) {
		int xId =  (int) Math.floor(x / (Chunk.SIZE * 32));
		int yId =  (int) Math.floor(y / (Chunk.SIZE * 32));
		for (Chunk chunk : activeChunks) {
			if(xId == chunk.getxId() && yId == chunk.getyId()) {
				return chunk;
			}
		}
		return null;
	}
	
	public static Chunk getChunkAtId(float x, float y) {
		for (Chunk chunk : activeChunks) {
			if(x == chunk.getxId() && y == chunk.getyId()) {
				return chunk;
			}
		}
		return null;
	}

	public static int getSeed() {
		return seed_;
	}

	public static void setSeed(int seed) {
		World.seed_ = seed;
	}



	public static Vector2f mouseToWorldCoords(Player player) {
		Vector2f toReturn = new Vector2f();
		toReturn.x = Window.Cursor.x + player.getPosition().x - (Window.width/2);
		toReturn.y = Window.Cursor.y + player.getPosition().y - (Window.height/2);
		System.out.println(toReturn.x + ", " + toReturn.y);
		return toReturn;
	}

}
