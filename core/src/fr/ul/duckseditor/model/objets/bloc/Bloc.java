package fr.ul.duckseditor.model.objets.bloc;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.duckseditor.model.objets.Objet;

/**
 * @author KLEINHENTZ 'Kryffin' Nicolas
 */
public abstract class Bloc extends Objet {

    /**
     * largeur du bloc
     */
    protected float width;

    /**
     * hauteur du bloc
     */
    protected float height;

    /**
     * Constructeur avec le monde dans lequel placer l'objet ainsi que la position de création
     * @param monde monde où placer le bloc
     * @param position position où l'y placer
     */
    public Bloc (World monde, Vector2 position) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(new Vector2(position));

        corps = monde.createBody(def);
        corps.setUserData(this);
    }

}
