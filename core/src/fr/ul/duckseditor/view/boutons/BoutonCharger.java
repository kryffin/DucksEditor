package fr.ul.duckseditor.view.boutons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.ul.duckseditor.dataFactory.TextureFactory;
import fr.ul.duckseditor.model.Monde;

/**
 * @author KLEINHENTZ 'Kryffin' Nicolas
 */
public class BoutonCharger extends Bouton {

    /**
     * Constructeur appelant le constructeur de Bouton
     * @param monde
     * @param position
     */
    public BoutonCharger (Monde monde, Vector2 position) {
        super(monde, position);
    }

    /**
     * Methode d'affichage du bouton à l'écran
     * @param sb SpriteBatch utilisé pour l'affichage
     */
    @Override
    public void draw(SpriteBatch sb) {
        sb.draw(TextureFactory.getLoad(), corps.getPosition().x, corps.getPosition().y, width, height);
    }

    /**
     * todo action
     */
    @Override
    public void action() {
        //ouvre la fenetre de chargement
    }

    @Override
    public String toString() {
        return "BoutonCharger";
    }
}
