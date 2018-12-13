package fr.ul.duckseditor.model.objets.personnage;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import fr.ul.duckseditor.dataFactory.TextureFactory;

/**
 * @author KLEINHENTZ 'Kryffin' Nicolas
 */
public class Prisonnier extends Personnage {

    /**
     * Constructeur appelant le constructeur de Personnage
     * @param monde monde dans lequel placer le personnage
     * @param position position dans laquelle l'y placer
     */
    public Prisonnier (World monde, Vector2 position) {
        super(monde, position);
        pv = 20;
    }

    /**
     * Constructeur appelé pour le chargement d'un niveau
     * @param monde monde dans lequel construire le prisonnier
     * @param position position du prisonnier
     * @param angle rotation du prisonnier
     */
    public Prisonnier (World monde, Vector2 position, float angle) {
        this(monde, position);
        getCorps().setTransform(position.x, position.y, angle);
    }

    /**
     * Méthode affichant à l'écran le prisonnier
     * @param sb SpriteBatch utiliser pour l'affichage
     */
    @Override
    public void draw(SpriteBatch sb) {
        float rotation = (float)Math.toDegrees(corps.getAngle());
        Sprite s = new Sprite(TextureFactory.getTargetBlue());
        s.setSize(diameter, diameter);
        s.setPosition(corps.getPosition().x - diameter/2, corps.getPosition().y - diameter/2);
        s.setOriginCenter();
        s.setRotation(rotation);
        s.draw(sb);
    }

    @Override
    public String toString() {
        return "Prisonnier:" + corps.getPosition().x + ":" + corps.getPosition().y + ":" + corps.getAngle();
    }

}
