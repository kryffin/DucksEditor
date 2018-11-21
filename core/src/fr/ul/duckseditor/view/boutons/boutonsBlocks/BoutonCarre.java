package fr.ul.duckseditor.view.boutons.boutonsBlocks;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.ul.duckseditor.dataFactory.TextureFactory;
import fr.ul.duckseditor.model.Monde;
import fr.ul.duckseditor.model.Spawner;
import fr.ul.duckseditor.view.boutons.Bouton;

public class BoutonCarre extends Bouton {

    private OrthographicCamera camera;

    public BoutonCarre (Monde monde, Vector2 position) {
        super(monde, position);
    }

    @Override
    public void draw(SpriteBatch sb) {
        sb.draw(TextureFactory.getBlock(), corps.getPosition().x, corps.getPosition().y, width, height);
    }

    @Override
    public void action() {
        System.out.println("CARRE");
        Spawner.getInstance().spawnCarre();
    }

    @Override
    public String toString() {
        return "BoutonCarre";
    }

}
