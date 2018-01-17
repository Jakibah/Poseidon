package com.Jakibah.Poseidon.Engine;

import org.joml.Vector3f;

public class Sun {
	
	public static Vector3f light = new Vector3f(0,0,0);
	
	public static void setLight(float lightLevel){
		light = new Vector3f(1-lightLevel,1-lightLevel,1-lightLevel);
	}

}
