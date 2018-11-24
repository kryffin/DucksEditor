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

    @Override
    public void draw(ShapeRenderer sr) {
        sr.setColor(Color.GREEN);
        sr.rect(getCorps().getPosition().x, getCorps().getPosition().y, width, height);
    }

    @Override
    public void drawLinearVelocity(ShapeRenderer sr) {
        sr.setColor(Color.BLUE);
        float x = getCorps().getPosition().x + (width/2), y = getCorps().getPosition().y + (height/2);
        sr.line(x, y, x + getCorps().getLinearVelocity().x, y + getCorps().getLinearVelocity().y);
    }

}
