package fr.ul.duckseditor.view.boutons.boutonsBlocks;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import fr.ul.duckseditor.dataFactory.TextureFactory;
import fr.ul.duckseditor.model.Monde;
import fr.ul.duckseditor.model.Spawner;
import fr.ul.duckseditor.view.boutons.Bouton;

public class BoutonRectangle extends Bouton {

    private OrthographicCamera camera;

    public BoutonRectangle (Monde monde, Vector2 position) {
        super(monde, position, 1, 4);
    }

    @Override
    public void draw(SpriteBatch sb) {
        sb.draw(TextureFactory.getBeam(), corps.getPosition().x, corps.getPosition().y, width, height);
    }

    @Override
    public void action() {
        Spawner.getInstance().spawnRectangle();
    }

    @Override
    public String toString() {
        return "BoutonRectangle";
    }

}
