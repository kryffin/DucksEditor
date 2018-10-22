package fr.ul.duckseditor.model.objets.bloc;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.duckseditor.DucksEditor;

public class Carre extends Bloc {

    public Carre (World monde, Vector2 position) {
        super(monde, position);

        width = DucksEditor.widthToScreen(2);
        height = DucksEditor.heightToScreen(2);

        PolygonShape carre = new PolygonShape();
        carre.setAsBox(width, height); //2UM
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = carre;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.5f; // Make it bounce a little bit
        Fixture fixture = corps.createFixture(fixtureDef);
        carre.dispose();
    }

}
