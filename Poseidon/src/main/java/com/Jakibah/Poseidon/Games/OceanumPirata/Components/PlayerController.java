package com.Jakibah.Poseidon.Games.OceanumPirata.Components;

import org.lwjgl.glfw.GLFW;
import com.Jakibah.Poseidon.Engine.Component;
import com.Jakibah.Poseidon.Engine.Entity;
import com.Jakibah.Poseidon.Engine.Window;
import com.Jakibah.Poseidon.Engine.Utils.Maths;
import com.Jakibah.Poseidon.Games.OceanumPirata.World.Tile;
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
			Tile t = World.getChunkAtCoords(this.getParent().getPosition().x, this.getParent().getPosition().y - speed)
					.getTileAt(this.getParent().getPosition().x, this.getParent().getPosition().y - speed);
			if (!t.isSolid())
				this.getParent().increasePosition(0, -speed, 0);
		}
		if (Window.Keyboard[GLFW.GLFW_KEY_A] == true) {
			Tile t = World.getChunkAtCoords(this.getParent().getPosition().x - speed, this.getParent().getPosition().y)
					.getTileAt(this.getParent().getPosition().x - speed, this.getParent().getPosition().y);
			if (!t.isSolid())
				this.getParent().increasePosition(-speed, 0, 0);
		}
		if (Window.Keyboard[GLFW.GLFW_KEY_S] == true) {
			Tile t = World.getChunkAtCoords(this.getParent().getPosition().x, this.getParent().getPosition().y+32)
					.getTileAt(this.getParent().getPosition().x, this.getParent().getPosition().y+32);
			if (!t.isSolid())
				this.getParent().increasePosition(0, speed, 0);
		}
		if (Window.Keyboard[GLFW.GLFW_KEY_D] == true) {
			Tile t = World.getChunkAtCoords(this.getParent().getPosition().x+32, this.getParent().getPosition().y)
					.getTileAt(this.getParent().getPosition().x+32, this.getParent().getPosition().y);
			if (!t.isSolid())
				this.getParent().increasePosition(speed, 0, 0);
		}
	}

}
