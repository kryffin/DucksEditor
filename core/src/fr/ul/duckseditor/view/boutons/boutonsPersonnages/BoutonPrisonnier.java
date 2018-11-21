package fr.ul.duckseditor.view.boutons.boutonsPersonnages;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import fr.ul.duckseditor.DucksEditor;
import fr.ul.duckseditor.dataFactory.TextureFactory;
import fr.ul.duckseditor.model.Monde;
import fr.ul.duckseditor.model.Spawner;
import fr.ul.duckseditor.view.boutons.Bouton;

public class BoutonPrisonnier extends Bouton {

    public BoutonPrisonnier (Monde monde, Vector2 position) {
        super(monde, position, 1);
    }

    @Override
    public void draw(SpriteBatch sb) {
        sb.draw(TextureFactory.getTargetBlue(), corps.getPosition().x - diameter/2, corps.getPosition().y - diameter/2, diameter, diameter);
    }

    @Override
    public void action() {
        Spawner.getInstance().spawnPrisonnier();
    }

    @Override
    public String toString() {
        return "BoutonPrisonnier";
    }

}
