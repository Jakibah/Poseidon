package com.Jakibah.Poseidon.Engine;

public abstract class App {
	
	public GameState activeState;
	
	/*
	 *An application. 
	 *Every game that uses the Poseidon library is an application. 
	 * */
	 
	
	public App(GameState startState) {
		activeState = startState;
	}
	
	public void start() {
		activeState.start();
	}
	public void update() {
		activeState.update();	
	}
	public void stop() {
		activeState.stop();

	}
	
	
	
}
