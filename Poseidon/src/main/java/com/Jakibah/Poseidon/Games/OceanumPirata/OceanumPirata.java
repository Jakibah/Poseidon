package com.Jakibah.Poseidon.Games.OceanumPirata;

import com.Jakibah.Poseidon.Engine.App;
import com.Jakibah.Poseidon.Engine.GameState;
import com.Jakibah.Poseidon.Engine.Window;
import com.Jakibah.Poseidon.Games.OceanumPirata.States.Game;

public class OceanumPirata extends App {
	
	
	public OceanumPirata(GameState startState) {
		super(startState);
	}

	@Override
	public void start() {
		super.start();
	}
	
	@Override
	public void update() {
		super.update();
		Window.masterRenderer.render(activeState.lights);
		}

	

	@Override
	public void stop() {
		super.stop();
	}

	public static void main(String[] args) {
		Window.createCanvas("Oceanum Pirata", 2160/2, 1440/2, new OceanumPirata(new Game()));

	}

}
