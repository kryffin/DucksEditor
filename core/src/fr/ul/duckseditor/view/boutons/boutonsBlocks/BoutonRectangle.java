package fr.ul.duckseditor.view.boutons.boutonsBlocks;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.ul.duckseditor.DucksEditor;
import fr.ul.duckseditor.dataFactory.TextureFactory;
import fr.ul.duckseditor.model.Monde;
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
        monde.spawnRectangle(DucksEditor.UM_WIDTH/2, DucksEditor.UM_HEIGHT/2);
    }

    @Override
    public String toString() {
        return "BoutonRectangle";
    }

}
