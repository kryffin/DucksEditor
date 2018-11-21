package fr.ul.duckseditor.view.boutons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import fr.ul.duckseditor.dataFactory.TextureFactory;
import fr.ul.duckseditor.model.Monde;

public class BoutonSauvegarder extends Bouton {

    public BoutonSauvegarder (Monde monde, Vector2 position) {
        super(monde, position);
    }

    @Override
    public void draw(SpriteBatch sb) {
        sb.draw(TextureFactory.getSave(), corps.getPosition().x, corps.getPosition().y, width, height);
    }

    @Override
    public String toString() {
        return "BoutonSauvegarder";
    }

}
