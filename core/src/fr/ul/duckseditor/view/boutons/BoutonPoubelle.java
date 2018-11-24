package fr.ul.duckseditor.view.boutons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.ul.duckseditor.dataFactory.TextureFactory;
import fr.ul.duckseditor.model.Monde;

/**
 * @author KLEINHENTZ 'Kryffin' Nicolas
 */
public class BoutonPoubelle extends Bouton {

    /**
     * Constructeur appelant le constructeur de Bouton
     * @param monde
     * @param position
     */
    public BoutonPoubelle(Monde monde, Vector2 position) {
        super(monde, position);
    }

    /**
     * Methode d'affichage du bouton à l'écran
     * @param sb SpriteBatch utilisé pour l'affichage
     */
    public void draw (SpriteBatch sb) {
        sb.draw(TextureFactory.getTrash(), corps.getPosition().x, corps.getPosition().y, width, height);
    }

    /**
     * rien à faire
     */
    @Override
    public void action() {
        //rien
    }

    @Override
    public String toString() {
        return "BoutonPoubelle";
    }

}
