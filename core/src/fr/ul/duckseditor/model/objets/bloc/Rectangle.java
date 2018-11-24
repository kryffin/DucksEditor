package fr.ul.duckseditor.model.objets.bloc;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.duckseditor.dataFactory.TextureFactory;

/**
 * @author KLEINHENTZ 'Kryffin' Nicolas
 */
public class Rectangle extends Bloc {

    /**
     * Constructeur appelant le constructeur de Bloc
     * @param monde monde dans lequel placer le rectangle
     * @param position position dans laquelle placer le rectangle
     */
    public Rectangle (World monde, Vector2 position) {
        super(monde, position);

        width = 1;
        height = 4;

        PolygonShape carre = new PolygonShape();
        carre.set(new float[]{0.f, 0.f, 0.f, height, width, height, width, 0.f}); // 1x4 UM
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = carre;
        fixtureDef.density = 0.7f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.5f; // Make it bounce a little bit
        corps.createFixture(fixtureDef);
        carre.dispose();
    }

    /**
     * Méthode affichant à l'écran le rectangle
     * @param sb SpriteBatch utiliser pour l'affichage
     */
    @Override
    public void draw(SpriteBatch sb) {
        float rotation = (float)Math.toDegrees(corps.getAngle());
        Sprite s = new Sprite(TextureFactory.getBeam());
        s.setSize(width, height);
        s.setPosition(corps.getPosition().x, corps.getPosition().y);
        s.setOrigin(0.f, 0.f);
        s.setRotation(rotation);
        s.draw(sb);
    }

}
