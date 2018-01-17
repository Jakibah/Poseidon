package com.Jakibah.Poseidon.Engine.Render;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

public class Loader {
	
	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();
	private List<Integer> textures = new ArrayList<Integer>();
	
	public RawModel loadToVao(float[] positions, float[] textureCoords, int[] indices){
		int vaoId = createVao();
		bindIndicesBuffer(indices);
		storeDataInAttributeList(0, 3,positions);
		storeDataInAttributeList(1, 2,textureCoords);
		unbindVao();
		return new RawModel(vaoId, indices.length);
	}
	
	private int createVao(){
		int vaoId = GL30.glGenVertexArrays();
		vaos.add(vaoId);
		GL30.glBindVertexArray(vaoId);
		return vaoId;
	}
	
	public void cleanUp(){
		
		for(int i : vaos){
			GL30.glDeleteVertexArrays(i);
		}
		for(int i : vbos){
			GL15.glDeleteBuffers(i);;
		}
		for(int i : textures){
			GL11.glDeleteTextures(i);
		}
	}
	
	private void storeDataInAttributeList(int attribNumber, int coordinateSize, float[] data){
		int vboId = GL15.glGenBuffers();
		vbos.add(vboId);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attribNumber, coordinateSize, GL11.GL_FLOAT, false, 0,0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
	}
	
	private void unbindVao(){
		GL30.glBindVertexArray(0);
	}
	
	private FloatBuffer storeDataInFloatBuffer(float[] data){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	private IntBuffer storeInIntBuffer(int[] data){
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	public int loadTexture(String fileName){
		int textureId = 0;
		try {
			InputStream stream = Loader.class.getResourceAsStream("/Textures/" + fileName + ".png");
			PNGDecoder decoder = new PNGDecoder(stream);
			
			ByteBuffer buf = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());
			decoder.decode(buf, decoder.getWidth() * 4, Format.RGBA);
			buf.flip();
			textureId = GL11.glGenTextures();
			textures.add(textureId);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);
			GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buf);
			GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return textureId;
	}
	
	private void  bindIndicesBuffer(int[] indices){
		int vboId = GL15.glGenBuffers();
		vbos.add(vboId);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboId);
		IntBuffer buffer = storeInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}

}
