package fr.ul.duckseditor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import fr.ul.duckseditor.view.EditorScreen;

public class DucksEditor extends Game {

	public static int SCREEN_WIDTH = 1920;
	public static int SCREEN_HEIGHT = 1080;
	public static int UM_WIDTH = 64;
	public static int UM_HEIGHT = 36;
	public static int FPS = 30;

    private EditorScreen es;

    @Override
	public void create () {
		es = new EditorScreen();
		setScreen(es);
	}

    @Override
    public void dispose() {
        es.dispose();
    }

    /**
     * Conversion d'une position en UM en position sur l'écran
     * @param position position à convertir
     * @return position convertie
     */
    public static Vector2 positionToScreen (Vector2 position) {
        float x = position.x, y = position.y;
        float ratioX = SCREEN_HEIGHT / UM_WIDTH, ratioY = SCREEN_HEIGHT / UM_HEIGHT;
        x *= ratioX;
        y *= ratioY;
        return new Vector2(x, y);
    }

    /**
     * Conversion d'une position sur l'écran en position en UM
     * @param position position à convertir
     * @return position convertie
     */
    public static Vector2 positionToUM (Vector2 position) {
        float x = position.x, y = position.y;
        x /= SCREEN_WIDTH;
        y /= SCREEN_HEIGHT;
        x *= UM_WIDTH;
        y *= UM_HEIGHT;
        return new Vector2(x, y);
    }

    /**
     * Convertit une valeur de largeur en UM en position sur l'écran
     * @param UM valeur en UM
     * @return valeur sur l'écran
     */
    public static float widthToScreen (int UM) {
        float ratio = SCREEN_WIDTH / UM_WIDTH;
        return UM * ratio;
    }

    /**
     * Convertit une valeur de hauteur en UM en position sur l'écran
     * @param UM valeur en UM
     * @return valeur sur l'écran
     */
    public static float heightToScreen (int UM) {
        float ratio = SCREEN_HEIGHT / UM_HEIGHT;
        return UM * ratio;
    }

    /**
     * Convertit une valeur de largeur de l'écran en position UM
     * @param screen valeur de l'écran
     * @return valeur en UM
     */
    public static int widthToUM (float screen) {
        screen /= SCREEN_WIDTH;
        screen *= UM_WIDTH;
        return (int) screen;
    }

    /**
     * Convertit une valeur de hauteur de l'écran en position UM
     * @param screen valeur de l'écran
     * @return valeur en UM
     */
    public static int heightToUM (float screen) {
        screen /= SCREEN_HEIGHT;
        screen *= UM_HEIGHT;
        return (int) screen;
    }

}
