package fr.ul.duckseditor.model.objets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

/**
 * @author KLEINHENTZ 'Kryffin' Nicolas
 */
public abstract class Objet {

    /**
     * corps de l'objet
     */
    protected Body corps;

    /**
     * Méthode affichant l'objet à l'écran
     * @param sb SpriteBatch permettant l'affichage de l'objet
     */
    public abstract void draw (SpriteBatch sb);

    /**
     * Setter sur le type de body
     * @param running vrai si l'animation est en marche, faux sinon
     */
    public void setType (boolean running) {
        if (running) {
            corps.setType(BodyDef.BodyType.DynamicBody);
        } else {
            corps.setType(BodyDef.BodyType.StaticBody);
        }
    }

    /**
     * @return corps de l'objet
     */
    public Body getCorps() {
        return corps;
    }
}
