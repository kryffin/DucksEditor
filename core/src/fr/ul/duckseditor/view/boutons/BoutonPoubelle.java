package fr.ul.duckseditor.view.boutons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.ul.duckseditor.dataFactory.TextureFactory;
import fr.ul.duckseditor.model.Monde;

public class BoutonPoubelle extends Bouton {

    public BoutonPoubelle(Monde monde, Vector2 position) {
        super(monde, position);
    }

    public void draw (SpriteBatch sb) {
        sb.draw(TextureFactory.getTrash(), corps.getPosition().x, corps.getPosition().y, width, height);
    }

    @Override
    public void action() {
        //rien
    }

    @Override
    public String toString() {
        return "BoutonPoubelle";
    }

}
