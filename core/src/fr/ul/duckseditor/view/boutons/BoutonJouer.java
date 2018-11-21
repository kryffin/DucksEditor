package fr.ul.duckseditor.view.boutons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import fr.ul.duckseditor.dataFactory.TextureFactory;
import fr.ul.duckseditor.model.Monde;
import fr.ul.duckseditor.view.EditorPanel;
import fr.ul.duckseditor.view.EditorScreen;

public class BoutonJouer extends Bouton {

    private EditorScreen es;

    public BoutonJouer (Monde monde, Vector2 position, EditorScreen es) {
        super(monde, position);
        this.es = es;
    }

    public void draw (SpriteBatch sb) {
        sb.draw(TextureFactory.getPlay(), corps.getPosition().x, corps.getPosition().y, width, height);
    }

    @Override
    public void action() {
        es.setRunning(true);
    }

    @Override
    public String toString() {
        return "BoutonJouer";
    }

}
