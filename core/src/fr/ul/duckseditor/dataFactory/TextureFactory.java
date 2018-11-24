package fr.ul.duckseditor.dataFactory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * @author KLEINHENTZ 'Kryffin' Nicolas
 */
public class TextureFactory {

    private static Texture background = new Texture(Gdx.files.internal("images/background.png"));
    private static Texture beam = new Texture(Gdx.files.internal("images/beam.png"));
    private static Texture block = new Texture(Gdx.files.internal("images/block.png"));
    private static Texture cancel = new Texture(Gdx.files.internal("images/cancel.png"));
    private static Texture duck = new Texture(Gdx.files.internal("images/duck.png"));
    private static Texture editPanel = new Texture(Gdx.files.internal("images/editPanel.png"));
    private static Texture leftArrow = new Texture(Gdx.files.internal("images/leftarrow.png"));
    private static Texture load = new Texture(Gdx.files.internal("images/Load.png"));
    private static Texture play = new Texture(Gdx.files.internal("images/Play.png"));
    private static Texture rewrite = new Texture(Gdx.files.internal("images/Rewrite.png"));
    private static Texture rightArrow = new Texture(Gdx.files.internal("images/rightarrow.png"));
    private static Texture save = new Texture(Gdx.files.internal("images/Save.png"));
    private static Texture stop = new Texture(Gdx.files.internal("images/Stop.png"));
    private static Texture targetBeige = new Texture(Gdx.files.internal("images/targetbeige.png"));
    private static Texture targetBlue = new Texture(Gdx.files.internal("images/targetblue.png"));
    private static Texture trash = new Texture(Gdx.files.internal("images/trash.png"));

    public static Texture getBackground() {
        return background;
    }

    public static Texture getBeam() {
        return beam;
    }

    public static Texture getBlock() {
        return block;
    }

    public static Texture getCancel() {
        return cancel;
    }

    public static Texture getDuck() {
        return duck;
    }

    public static Texture getEditPanel() {
        return editPanel;
    }

    public static Texture getLeftArrow() {
        return leftArrow;
    }

    public static Texture getLoad() {
        return load;
    }

    public static Texture getPlay() {
        return play;
    }

    public static Texture getRewrite() {
        return rewrite;
    }

    public static Texture getRightArrow() {
        return rightArrow;
    }

    public static Texture getSave() {
        return save;
    }

    public static Texture getStop() {
        return stop;
    }

    public static Texture getTargetBeige() {
        return targetBeige;
    }

    public static Texture getTargetBlue() {
        return targetBlue;
    }

    public static Texture getTrash() {
        return trash;
    }
}
