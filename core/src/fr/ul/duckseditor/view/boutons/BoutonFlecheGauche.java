package fr.ul.duckseditor.view.boutons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import fr.ul.duckseditor.dataFactory.TextureFactory;
import fr.ul.duckseditor.model.Monde;

/**
 * @author KLEINHENTZ 'Kryffin' Nicolas
 */
public class BoutonFlecheGauche extends Bouton {

    /**
     * Constructeur appelant le constructeur de Bouton
     * @param world
     * @param position
     */
    public BoutonFlecheGauche(World world, Vector2 position) {
        super(world, position);
    }

    /**
     * Methode d'affichage du bouton à l'écran
     * @param sb SpriteBatch utilisé pour l'affichage
     */
    public void draw (SpriteBatch sb) {
        sb.draw(TextureFactory.getLeftArrow(), corps.getPosition().x, corps.getPosition().y, width, height);
    }

    @Override
    public String toString() {
        return "BoutonFlecheGauche";
    }

}
