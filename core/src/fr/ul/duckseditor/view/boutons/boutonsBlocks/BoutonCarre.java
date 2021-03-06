package fr.ul.duckseditor.view.boutons.boutonsBlocks;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.ul.duckseditor.DucksEditor;
import fr.ul.duckseditor.dataFactory.TextureFactory;
import fr.ul.duckseditor.model.Monde;
import fr.ul.duckseditor.view.boutons.Bouton;

/**
 * @author KLEINHENTZ 'Kryffin' Nicolas
 */
public class BoutonCarre extends Bouton {

    /**
     * Constructeur appelant le constructeur de Bouton
     * @param monde
     * @param position
     */
    public BoutonCarre (Monde monde, Vector2 position) {
        super(monde, position);
    }

    /**
     * Methode d'affichage du bouton à l'écran
     * @param sb SpriteBatch utilisé pour l'affichage
     */
    public void draw(SpriteBatch sb) {
        sb.draw(TextureFactory.getBlock(), corps.getPosition().x, corps.getPosition().y, width, height);
    }

    @Override
    public String toString() {
        return "BoutonCarre";
    }

}
