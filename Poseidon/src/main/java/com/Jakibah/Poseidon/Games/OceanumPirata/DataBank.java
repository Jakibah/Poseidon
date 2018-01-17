package com.Jakibah.Poseidon.Games.OceanumPirata;

import com.Jakibah.Poseidon.Engine.Window;
import com.Jakibah.Poseidon.Engine.Render.RawModel;
import com.Jakibah.Poseidon.Engine.Render.Texture;
import com.Jakibah.Poseidon.Engine.Render.TexturedModel;

public class DataBank {

	private static float factorX;

	private static float factorY;

	private static int[] QUADindices = { 0, 1, 3, 3, 1, 2 };

	private static float[] QUADtextureCoords = { 0f, 0f, 0f, 1f, 1f, 1f, 1f, 0f };

	private static RawModel QUAD32;

	private static Texture TEST;
	private static Texture PLAYER_TEST;
	private static Texture SAND_TEXTURE;
	private static Texture WATER_TEXTURE;
	private static Texture GRASS_TEXTURE;

	public static TexturedModel TEST_TILE;
	public static TexturedModel PLAYER;
	public static TexturedModel SAND;
	public static TexturedModel WATER;
	public static TexturedModel GRASS;

	public static void init() {
		factorX = Window.factorX;
		factorY = Window.factorY;
		// models
		initQUADModel();
		QUAD32 = Window.loader.loadToVao(QUADvertices, QUADtextureCoords,
				QUADindices);

		// textures
		TEST = new Texture(Window.loader.loadTexture("TestTile"));
		PLAYER_TEST = new Texture(Window.loader.loadTexture("Player"));
		SAND_TEXTURE = new Texture(Window.loader.loadTexture("Sand"));
		GRASS_TEXTURE = new Texture(Window.loader.loadTexture("Grass"));
		WATER_TEXTURE = new Texture(Window.loader.loadTexture("Water"));

		// texturedmodels
		TEST_TILE = new TexturedModel(QUAD32, TEST);
		
		SAND = new TexturedModel(QUAD32, SAND_TEXTURE);
		WATER = new TexturedModel(QUAD32, WATER_TEXTURE);
		GRASS = new TexturedModel(QUAD32, GRASS_TEXTURE);
		PLAYER = new TexturedModel(QUAD32, PLAYER_TEST);

	}

	private static float[] QUADvertices = new float[12];

	private static void initQUADModel() {
		QUADvertices[0] = -factorX * 32;
		QUADvertices[1] = factorY * 32;
		QUADvertices[2] = 0f;
		QUADvertices[3] = -factorX * 32;
		QUADvertices[4] = -factorY * 32;
		QUADvertices[5] = 0f;
		QUADvertices[6] = factorX * 32;
		QUADvertices[7] = -factorY * 32;
		QUADvertices[8] = 0f;
		QUADvertices[9] = factorX * 32;
		QUADvertices[10] = factorY * 32;
		QUADvertices[11] = 0f;

	}

}
