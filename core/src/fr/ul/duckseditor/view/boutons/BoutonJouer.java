package fr.ul.duckseditor.view.boutons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.ul.duckseditor.dataFactory.TextureFactory;
import fr.ul.duckseditor.model.Monde;
import fr.ul.duckseditor.view.EditorScreen;

/**
 * @author KLEINHENTZ 'Kryffin' Nicolas
 */
public class BoutonJouer extends Bouton {

    /**
     * EditorScreen à controler
     */
    private EditorScreen es;

    /**
     * Constructeur appelant le constructeur de Bouton
     * @param monde
     * @param position
     * @param es EditorScreen à controler
     */
    public BoutonJouer (Monde monde, Vector2 position, EditorScreen es) {
        super(monde, position);
        this.es = es;
    }

    /**
     * Methode d'affichage du bouton à l'écran
     * @param sb SpriteBatch utilisé pour l'affichage
     */
    public void draw (SpriteBatch sb) {
        sb.draw(TextureFactory.getPlay(), corps.getPosition().x, corps.getPosition().y, width, height);
    }

    /**
     * Lance l'animation de l'EditorScreen lors du clic
     */
    @Override
    public void action() {
        es.setRunning(true);
    }

    @Override
    public String toString() {
        return "BoutonJouer";
    }

}
