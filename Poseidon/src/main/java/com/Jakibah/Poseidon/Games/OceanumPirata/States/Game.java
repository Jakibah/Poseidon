package com.Jakibah.Poseidon.Games.OceanumPirata.States;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.Jakibah.Poseidon.Engine.GameState;
import com.Jakibah.Poseidon.Engine.Window;
import com.Jakibah.Poseidon.Games.OceanumPirata.DataBank;
import com.Jakibah.Poseidon.Games.OceanumPirata.Player;
import com.Jakibah.Poseidon.Games.OceanumPirata.World.World;

public class Game extends GameState {

	Player p;
	
	@Override
	public void start() {
		 	DataBank.init();
			p = new Player(new Vector3f(1600, 1600, 0.001f),0,0,0,1);
			new World(-1039093906, new Vector2f(p.getPosition().x, p.getPosition().y), p);
	}

	@Override
	public void update() {
		Window.bindTranslationPoint(p.getPosition());
		p.update();
		World.update();

	}

	@Override
	public void stop() {
		

	}

}
