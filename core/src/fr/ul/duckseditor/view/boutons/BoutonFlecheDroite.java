package fr.ul.duckseditor.view.boutons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import fr.ul.duckseditor.dataFactory.TextureFactory;
import fr.ul.duckseditor.model.Monde;

public class BoutonFlecheDroite extends Bouton {

    public BoutonFlecheDroite (Monde monde, Vector2 position) {
        super(monde, position);
    }

    public void draw (SpriteBatch sb) {
        sb.draw(TextureFactory.getRightArrow(), corps.getPosition().x, corps.getPosition().y, width, height);
    }

    @Override
    public void action() {
        //passe sur le niveau de droite
    }

    @Override
    public String toString() {
        return "BoutonFlecheDroite";
    }

}
