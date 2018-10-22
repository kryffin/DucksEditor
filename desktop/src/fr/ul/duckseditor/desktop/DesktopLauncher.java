package fr.ul.duckseditor.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fr.ul.duckseditor.DucksEditor;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.a = 8; //codage du canal alpha sur 8 bits
        config.width = DucksEditor.SCREEN_WIDTH; //largeur de l'écran
        config.height = DucksEditor.SCREEN_HEIGHT; //hauteur de l'écran
		new LwjglApplication(new DucksEditor(), config);
	}
}
