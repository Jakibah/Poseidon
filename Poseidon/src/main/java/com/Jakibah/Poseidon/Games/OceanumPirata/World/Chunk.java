package com.Jakibah.Poseidon.Games.OceanumPirata.World;

import java.awt.Rectangle;

import org.joml.Vector2f;

import com.Jakibah.Poseidon.Engine.Window;
import com.Jakibah.Poseidon.Engine.Utils.Maths;
import com.Jakibah.Poseidon.Games.OceanumPirata.Utils.ChunkGenerator;
import com.Jakibah.Poseidon.Games.OceanumPirata.Utils.TerrainGenerator;

public class Chunk {

	private int xId, yId;
	private Tile[][] tiles;
	private Rectangle north, east, south, west, body;
	private World world;

	public static final int SIZE = 100;
	
	public static boolean anyCollision(Rectangle r, Tile[][] tiles) {
		
		
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				if(Maths.isColliding(r, tiles[i][j].getCollider()) && tiles[i][j].isSolid()) {
					return true;
				}
			}
		}
		
		return false;
	}
	
public  Tile anyCollision(Vector2f mouse, Tile[][] tiles) {
		
		Rectangle r = new Rectangle();
		r.setBounds((int)mouse.x, (int)mouse.y, 1, 1);
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				if(Maths.isColliding(r, tiles[i][j].getCollider())) {
					return tiles[i][j];
				}
			}
		}
		return null;
	}
	

	public Chunk(int xId, int yId, ChunkGenerator cg, World parent) {
		super();
		this.setWorld(parent);
		this.xId = xId;
		this.yId = yId;
		this.tiles = cg.generate(xId, yId);
		north = new Rectangle();
		north.setBounds((xId * Chunk.SIZE * 32), (yId * Chunk.SIZE * 32), (Chunk.SIZE * 32), Window.height / 2);
		east = new Rectangle();
		east.setBounds((xId * SIZE * 32) + (SIZE * 32) - (Window.width / 2), yId * (32 * SIZE), Window.width / 2,
				(SIZE * 32));
		south = new Rectangle();
		south.setBounds(xId * (32 * SIZE), (yId * (32 * SIZE)) + (SIZE * 32) - (Window.height / 2), (32 * SIZE),
				Window.height / 2);
		west = new Rectangle();
		west.setBounds(xId * (SIZE*32), yId * (SIZE*32), Window.width / 2, (32*SIZE));
		body = new Rectangle();
		body.setBounds((xId * (SIZE*32)) - (Window.width / 2), (yId * (SIZE*32)) - (Window.height / 2),
				(SIZE*32) + (Window.width), (SIZE*32) + (Window.height));
	}

	public void loadAdjecentChunks() {
		if(!Maths.isColliding(body, World.player.getCollider())) {
			World.activeChunksToRemove.add(this);
		}
		if (Maths.isColliding(north, World.player.getCollider())) {
			if (World.getChunkAtId(xId, yId - 1) == null) {
				World.activeChunksToAdd.add(new Chunk(xId, yId - 1, new TerrainGenerator(), this.getWorld()));
			}
		}
		if (Maths.isColliding(east, World.player.getCollider())) {
			if (World.getChunkAtId(xId + 1, yId) == null) {
				World.activeChunksToAdd.add(new Chunk(xId + 1, yId, new TerrainGenerator(), this.getWorld()));
			}
		}
		if(Maths.isColliding(south, World.player.getCollider())) {
			if(World.getChunkAtId(xId, yId+1)==null) {
				World.activeChunksToAdd.add(new Chunk(xId, yId+1, new TerrainGenerator(), this.getWorld()));
			}
		}
		if (Maths.isColliding(west, World.player.getCollider())) {
			if (World.getChunkAtId(xId - 1, yId) == null) {
				World.activeChunksToAdd.add(new Chunk(xId - 1, yId, new TerrainGenerator(), this.getWorld()));
			}
		}
	}

	public Tile getTileAt(float x, float y) {

		x -= xId * SIZE * 32;
		y -= yId * SIZE * 32;
		
		x = x/32;
		y = y/32;
		
		x = (float) Math.floor(x);
		y = (float) Math.floor(y);
		
		return tiles[(int) x][(int) y];
		
	}

	public void update() {
		loadAdjecentChunks();
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tiles[i][j].update();
			}
		}
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}

	public int getxId() {
		return xId;
	}

	public int getyId() {
		return yId;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

}
