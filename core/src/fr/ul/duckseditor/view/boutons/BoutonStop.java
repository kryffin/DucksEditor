package fr.ul.duckseditor.view.boutons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.ul.duckseditor.dataFactory.TextureFactory;
import fr.ul.duckseditor.model.Monde;
import fr.ul.duckseditor.view.EditorScreen;

public class BoutonStop extends Bouton {

    private EditorScreen es;

    public BoutonStop (Monde monde, Vector2 position, EditorScreen es) {
        super(monde, position);
        this.es = es;
    }

    public void draw (SpriteBatch sb) {
        sb.draw(TextureFactory.getStop(), corps.getPosition().x, corps.getPosition().y, width, height);
    }

    @Override
    public void action() {
        es.setRunning(false);
    }

    @Override
    public String toString() {
        return "BoutonStop";
    }

}
