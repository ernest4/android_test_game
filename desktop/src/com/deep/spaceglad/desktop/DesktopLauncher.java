package com.deep.spaceglad.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.deep.spaceglad.Core;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
		config.title = "Space Gladiators";
		final int SCALE_FACTOR = 2;
		config.width = 1920/SCALE_FACTOR;
		config.height = 1080/SCALE_FACTOR;
		new LwjglApplication(new Core(), config);
	}
}
