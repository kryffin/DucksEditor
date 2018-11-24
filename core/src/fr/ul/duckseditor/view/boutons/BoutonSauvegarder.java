package fr.ul.duckseditor.view.boutons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.ul.duckseditor.dataFactory.TextureFactory;
import fr.ul.duckseditor.model.Monde;

/**
 * @author KLEINHENTZ 'Kryffin' Nicolas
 */
public class BoutonSauvegarder extends Bouton {

    /**
     * Constructeur appelant le constructeur de Bouton
     * @param monde
     * @param position
     */
    public BoutonSauvegarder (Monde monde, Vector2 position) {
        super(monde, position);
    }

    /**
     * Methode d'affichage du bouton à l'écran
     * @param sb SpriteBatch utilisé pour l'affichage
     */
    public void draw(SpriteBatch sb) {
        sb.draw(TextureFactory.getSave(), corps.getPosition().x, corps.getPosition().y, width, height);
    }

    /**
     * todo action
     */
    @Override
    public void action() {
        //sauvegarde le niveau
    }

    @Override
    public String toString() {
        return "BoutonSauvegarder";
    }

}
