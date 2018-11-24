package fr.ul.duckseditor.view.boutons.boutonsPersonnages;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.ul.duckseditor.DucksEditor;
import fr.ul.duckseditor.dataFactory.TextureFactory;
import fr.ul.duckseditor.model.Monde;
import fr.ul.duckseditor.view.boutons.Bouton;

/**
 * @author KLEINHENTZ 'Kryffin' Nicolas
 */
public class BoutonPrisonnier extends Bouton {

    /**
     * Constructeur appelant le constructeur de Bouton
     * @param monde
     * @param position
     */
    public BoutonPrisonnier (Monde monde, Vector2 position) {
        super(monde, position, 1);
    }

    /**
     * Methode d'affichage du bouton à l'écran
     * @param sb SpriteBatch utilisé pour l'affichage
     */
    public void draw(SpriteBatch sb) {
        sb.draw(TextureFactory.getTargetBlue(), corps.getPosition().x - diameter/2, corps.getPosition().y - diameter/2, diameter, diameter);
    }

    /**
     * Fais apparaitre un prisonnier au milieu de l'écran lors de l'action du bouton
     */
    @Override
    public void action() {
        monde.spawnPrisonnier(DucksEditor.UM_WIDTH/2, DucksEditor.UM_HEIGHT/2);
    }

    @Override
    public String toString() {
        return "BoutonPrisonnier";
    }

}
