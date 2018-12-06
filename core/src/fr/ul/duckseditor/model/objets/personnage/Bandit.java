package fr.ul.duckseditor.model.objets.personnage;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import fr.ul.duckseditor.dataFactory.TextureFactory;

/**
 * @author KLEINHENTZ 'Kryffin' Nicolas
 */
public class Bandit extends Personnage {

    /**
     * Constructeur appelant le constructeur de Personnage
     * @param monde monde dans lequel placer le personnage
     * @param position position dans laquelle l'y placer
     */
    public Bandit(World monde, Vector2 position) {
        super(monde, position);
        pv = 20;
    }

    /**
     * Méthode affichant à l'écran le bandit
     * @param sb SpriteBatch utiliser pour l'affichage
     */
    @Override
    public void draw(SpriteBatch sb) {
        float rotation = (float)Math.toDegrees(corps.getAngle());
        Sprite s = new Sprite(TextureFactory.getTargetBeige());
        s.setSize(diameter, diameter);
        s.setPosition(corps.getPosition().x - diameter/2, corps.getPosition().y - diameter/2);
        s.setOriginCenter();
        s.setRotation(rotation);
        s.draw(sb);
    }

    @Override
    public String toString() {
        return "Bandit:" + corps.getPosition().x + ":" + corps.getPosition().y + ":" + corps.getAngle();
    }

}
