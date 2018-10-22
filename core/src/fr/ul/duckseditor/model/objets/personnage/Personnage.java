package fr.ul.duckseditor.model.objets.personnage;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.duckseditor.model.objets.Objet;

public abstract class Personnage extends Objet {

    protected int pv;
    protected float radius = 10.f;

    public Personnage (World monde, Vector2 position) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(new Vector2(position));

        corps = monde.createBody(def);

        CircleShape cercle = new CircleShape();
        cercle.setRadius(radius);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = cercle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.5f; // Make it bounce a little bit
        Fixture fixture = corps.createFixture(fixtureDef);
        cercle.dispose();
    }

    @Override
    public void draw(ShapeRenderer sr) {
        sr.setColor(Color.RED);
        sr.circle(getCorps().getPosition().x, getCorps().getPosition().y, radius);
    }

    @Override
    public void drawLinearVelocity(ShapeRenderer sr) {
        sr.setColor(Color.BLUE);
        float x = getCorps().getPosition().x, y = getCorps().getPosition().y;
        sr.line(x, y, x + getCorps().getLinearVelocity().x, y + getCorps().getLinearVelocity().y);
    }

}
