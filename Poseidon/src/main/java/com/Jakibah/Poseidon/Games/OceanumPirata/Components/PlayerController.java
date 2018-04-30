package com.Jakibah.Poseidon.Games.OceanumPirata.Components;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;
import com.Jakibah.Poseidon.Engine.Component;
import com.Jakibah.Poseidon.Engine.Entity;
import com.Jakibah.Poseidon.Engine.Window;
import com.Jakibah.Poseidon.Engine.Utils.Maths;
import com.Jakibah.Poseidon.Games.OceanumPirata.DataBank;
import com.Jakibah.Poseidon.Games.OceanumPirata.Player;
import com.Jakibah.Poseidon.Games.OceanumPirata.World.Chunk;
import com.Jakibah.Poseidon.Games.OceanumPirata.World.Tile;
import com.Jakibah.Poseidon.Games.OceanumPirata.World.TileType;
import com.Jakibah.Poseidon.Games.OceanumPirata.World.World;

public class PlayerController extends Component {

	private int speed = 10;

	public PlayerController(Entity parent) {
		super(parent, "PlayerController");

	}
	// && !Maths.isColliding(t.getPosition().x, t.getPosition().y,
	// this.getParent().getPosition().x, this.getParent().getPosition().y))

	@Override
	public void update() {
		if (Window.Keyboard[GLFW.GLFW_KEY_W] == true) {
			Move(0, -speed);
		}

		if (Window.Keyboard[GLFW.GLFW_KEY_A] == true) {
			Move(-speed, 0);

		}
		if (Window.Keyboard[GLFW.GLFW_KEY_S] == true) {
			Move(0, speed);
		}
		if (Window.Keyboard[GLFW.GLFW_KEY_D] == true) {
			Move(speed, 0);
		}
		Vector2f mouse = World.mouseToWorldCoords(((Player)this.getParent()));
		Chunk c = World.getChunkAtCoords(mouse.x, mouse.y);
		Tile t = c.anyCollision(mouse, c.getTiles());
		t.setModel(DataBank.TEST_TILE);
		}

	public void Move(float x, float y) {

		
			for (int i = 0; i < (Math.abs(x)) - 1; i++) {
				if (x > 0) {
				Chunk c = World.getChunkAtCoords(this.getParent().getPosition().x + 1, this.getParent().getPosition().y);
				this.getParent().increasePosition(1, 0, 0);
				if (Chunk.anyCollision(((Player) this.getParent()).getCollider(), c.getTiles())) {
					
					this.getParent().increasePosition(-1, 0, 0);
				}
			}
		 else if (x < 0) {
				Chunk c = World.getChunkAtCoords(this.getParent().getPosition().x -1, this.getParent().getPosition().y);
			this.getParent().increasePosition(-1, 0, 0);
			if (Chunk.anyCollision(((Player) this.getParent()).getCollider(), c.getTiles())) {
				
				this.getParent().increasePosition(1, 0, 0);
			}
		}
		}
		
			for (int i = 0; i < (Math.abs(y)) - 1; i++) {
				if (y > 0) {
					Chunk c = World.getChunkAtCoords(this.getParent().getPosition().x , this.getParent().getPosition().y+1);
				this.getParent().increasePosition(0, 1, 0);
				if (Chunk.anyCollision(((Player) this.getParent()).getCollider(), c.getTiles())) {
					
					this.getParent().increasePosition(0, -1, 0);
				}
			}
		 else if (y < 0) {
				Chunk c = World.getChunkAtCoords(this.getParent().getPosition().x, this.getParent().getPosition().y-1);
			this.getParent().increasePosition(0, -1, 0);
			if (Chunk.anyCollision(((Player) this.getParent()).getCollider(), c.getTiles())) {
				
				this.getParent().increasePosition(0, 1, 0);
			}
		}

	}}

}
