package com.Jakibah.Poseidon.Engine.Utils;

import java.awt.Rectangle;
import java.util.List;

import org.joml.Matrix4f;
import org.joml.Rectanglef;
import org.joml.Vector3f;

import com.Jakibah.Poseidon.Engine.Entity;
import com.Jakibah.Poseidon.Engine.Light;
import com.Jakibah.Poseidon.Engine.Window;

public class Maths {

	public static Matrix4f createTransformationMatrix(Vector3f translation,
			float rx, float ry, float rz, float scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.identity().translate(translation)
				.rotateX((float) Math.toRadians(rx))
				.rotateY((float) Math.toRadians(ry))
				.rotateZ((float) Math.toRadians(rz)).scale(scale);

		return matrix;

	}
	public static float LookAt(float myx, float myy, float x, float y) {

		double angleTemp = Math.atan2(y - myy, x - myx);
		return (float) Math.toDegrees(angleTemp) - 90;

	}


	
	
	
	public static Matrix4f createProjectionMatrix(float FOV, float Z_NEAR, float Z_FAR) {
		Matrix4f matrix = new Matrix4f();
		matrix.identity();
		float yscale = (float) ((1f / Math.tan(Math.toRadians(FOV/2f))) * Window.aspectRatio);
		float xscale = yscale / Window.aspectRatio;
		float frustum = Z_FAR/Z_NEAR;
		
		matrix.m00(xscale);
		matrix.m11(yscale);
		matrix.m22(-((Z_FAR + Z_NEAR)/frustum));
		matrix.m23(-1);
		matrix.m32(-(2 * Z_NEAR * Z_FAR) / frustum);
		matrix.m33(0);
		return matrix;
	}
	
	public static float calcDistance(float x, float y, float x1, float y1) {
		float xDist = x - x1;
		float yDist = y - y1;
		float xPowered = (float) Math.pow(xDist, 2);
		float yPowered = (float) Math.pow(yDist, 2);
		return (float) Math.sqrt(xPowered+yPowered);
		
	}


	public static boolean isColliding(Rectangle a, Rectangle b) {
		boolean toReturn = false;
		
		
		if(a.intersects(b)) {
			toReturn = true;
		}
		
		return toReturn;
	}


	public static Vector3f calcLight(Entity entity, List<Light> lights) {
		float total = 0;
		
		for(Light l : lights) {
			float dist = calcDistance(l.getPosition().x, l.getPosition().y, entity.getPosition().x, entity.getPosition().y);
			
			if(dist < l.getRadius()) {
				total += (l.getBrightness()) / dist;
			}
		}
		Vector3f lightVec = new Vector3f(total,total,total);
		return lightVec;
	}

}
