package com.Jakibah.Poseidon.Engine;

import java.util.ArrayList;
import java.util.List;

public abstract class GameState {
	
	public List<Light> lights = new ArrayList<Light>();
	
	public static GameState changeState(GameState actual, GameState newState) {
		actual.stop();
		newState.start();
		return newState;
	}
	
	public abstract void start();
	public abstract void update();
	public abstract void stop();

}
