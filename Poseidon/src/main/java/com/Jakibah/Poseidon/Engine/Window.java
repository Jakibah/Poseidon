package com.Jakibah.Poseidon.Engine;

import org.joml.Vector3f;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import com.Jakibah.Poseidon.Engine.Render.Loader;
import com.Jakibah.Poseidon.Engine.Render.MasterRenderer;
import com.Jakibah.Poseidon.Engine.Render.Renderer;
import com.Jakibah.Poseidon.Engine.Shaders.StaticShader;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {

	// The window handle
	private static long window;

	public static float aspectRatio = 0;

	public static Loader loader;
	public static Renderer renderer;
	public static StaticShader shader;
	public static MasterRenderer masterRenderer;
	public static float factorX, factorY;
	public static boolean[] Keyboard = new boolean[100];
	public static int width, height;

	public static void createCanvas(String name, int Width, int Height, App app) {
		System.out.println("Hello LWJGL " + Version.getVersion() + "!");
		width = Width;
		height = Height;
		factorX = 1f / width;
		factorY = 1f / height;

		create(name, width, height);
		loop(app);

		// Free the window callbacks and destroy the window
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);

		// Terminate GLFW and free the error callback
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}

	private static void create(String name, int width, int height) {
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if (!glfwInit())
			throw new IllegalStateException("Unable to initialize GLFW");

		// Configure GLFW
		glfwDefaultWindowHints(); // optional, the current window hints are
									// already the default
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden
													// after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be
													// resizable

		// Create the window
		window = glfwCreateWindow(width, height, name, NULL, NULL);
		aspectRatio = width / height;
		if (window == NULL)
			throw new RuntimeException("Failed to create the GLFW window");

		// Setup a key callback. It will be called every time a key is pressed,
		// repeated or released.
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if (key < Keyboard.length && action == GLFW.GLFW_PRESS) {
				Keyboard[key] = true;
			}
			if (key < Keyboard.length && action == GLFW.GLFW_RELEASE) {
				Keyboard[key] = false;
			}

		});
		
		//GLFW.glfwSetMouseButtonCallback(window, cbfun);
		

		// Get the thread stack and push a new frame
		try (MemoryStack stack = stackPush()) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(window, pWidth, pHeight);

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(window, (vidmode.width() - pWidth.get(0)) / 2,
					(vidmode.height() - pHeight.get(0)) / 2);
		} // the stack frame is popped automatically

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);
		// Enable v-sync
		glfwSwapInterval(1);

		// Make the window visible
		glfwShowWindow(window);
	}
	public static Vector3f transPoint = new Vector3f(0,0,0);
	public static void bindTranslationPoint(Vector3f trans){
		transPoint = trans;
	}

	private static void loop(App app) {
		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		GL.createCapabilities();

		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		loader = new Loader();

		shader = new StaticShader();
		renderer = new Renderer(70, 0.01f, 1000f);
		masterRenderer = new MasterRenderer();
		app.start();

		while (!glfwWindowShouldClose(window)) {
			renderer.prepare();
			shader.start();
			shader.loadSun(Sun.light);
			shader.stop();
			// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			app.update();
			// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			glfwSwapBuffers(window); // swap the color buffers

			// Poll for window events. The key callback above will only be
			// invoked during this call.
			glfwPollEvents();
		}
		app.stop();
		shader.cleanUp();
		loader.cleanUp();
	}

}