package fr.ul.duckseditor.model.objets.bloc;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.duckseditor.model.objets.Objet;

public abstract class Bloc extends Objet {

    protected float width;
    protected float height;

    public Bloc (World monde, Vector2 position) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(new Vector2(position));

        corps = monde.createBody(def);
        corps.setUserData(this);
    }

}
