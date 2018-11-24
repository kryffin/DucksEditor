package fr.ul.duckseditor.view.boutons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.ul.duckseditor.dataFactory.TextureFactory;
import fr.ul.duckseditor.model.Monde;

/**
 * @author KLEINHENTZ 'Kryffin' Nicolas
 */
public class BoutonFlecheDroite extends Bouton {

    /**
     * Constructeur appelant le constructeur de Bouton
     * @param monde
     * @param position
     */
    public BoutonFlecheDroite (Monde monde, Vector2 position) {
        super(monde, position);
    }

    /**
     * Methode d'affichage du bouton à l'écran
     * @param sb SpriteBatch utilisé pour l'affichage
     */
    public void draw (SpriteBatch sb) {
        sb.draw(TextureFactory.getRightArrow(), corps.getPosition().x, corps.getPosition().y, width, height);
    }

    /**
     * todo action
     */
    @Override
    public void action() {
        //passe sur le niveau de droite
    }

    @Override
    public String toString() {
        return "BoutonFlecheDroite";
    }

}
