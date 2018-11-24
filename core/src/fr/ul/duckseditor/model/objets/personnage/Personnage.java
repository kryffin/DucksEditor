package fr.ul.duckseditor.model.objets.personnage;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.duckseditor.model.objets.Objet;

/**
 * @author KLEINHENTZ 'Kryffin' Nicolas
 */
public abstract class Personnage extends Objet {

    /**
     * points de vie du personnage (pas utilisé)
     */
    protected int pv;

    /**
     * diametre du cercle représentant le personnage
     */
    protected float diameter = 1;

    /**
     * Constructeur avec le monde dans lequel placer le personnage ainsi que sa position
     * @param monde monde dans lequel placer le personnage
     * @param position position où l'y placer
     */
    public Personnage (World monde, Vector2 position) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(new Vector2(position));

        corps = monde.createBody(def);
        corps.setUserData(this);

        CircleShape cercle = new CircleShape();
        cercle.setRadius(diameter/2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = cercle;
        fixtureDef.density = 0.7f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.5f; // Make it bounce a little bit
        corps.createFixture(fixtureDef);
        cercle.dispose();
    }

}
