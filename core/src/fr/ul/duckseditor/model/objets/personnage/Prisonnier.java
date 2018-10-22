package fr.ul.duckseditor.model.objets.personnage;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Prisonnier extends Personnage {

    public Prisonnier (World monde, Vector2 position) {
        super(monde, position);
        pv = 20;
    }

}
