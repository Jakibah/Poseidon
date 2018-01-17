package com.Jakibah.Poseidon.Engine.Render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Jakibah.Poseidon.Engine.Entity;
import com.Jakibah.Poseidon.Engine.Light;
import com.Jakibah.Poseidon.Engine.Window;

public class MasterRenderer {
	
	private Renderer renderer = Window.renderer;
	
	private Map<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel, List<Entity>>();
	
	
	public void render(List<Light> lights){
		//load vars
		renderer.render(entities, lights);
		entities.clear();
	}
	
	public void processEntity(Entity e){
		
		TexturedModel model = e.getModel();
		List<Entity> batch = entities.get(model);
		if(batch!=null){
			batch.add(e);
			//System.out.println(batch.size());
		}else{
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add(e);
			entities.put(model, newBatch);
			//System.out.println(newBatch.size());
		}
	}

}
