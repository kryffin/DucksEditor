package fr.ul.duckseditor.view.boutons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import fr.ul.duckseditor.dataFactory.TextureFactory;
import fr.ul.duckseditor.model.Monde;

public class BoutonQuitter extends Bouton {

    public BoutonQuitter (Monde monde, Vector2 position) {
        super(monde, position);
    }

    public void draw (SpriteBatch sb) {
        sb.draw(TextureFactory.getCancel(), corps.getPosition().x, corps.getPosition().y, width, height);
    }

    @Override
    public String toString() {
        return "BoutonQuitter";
    }

}
