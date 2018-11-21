package fr.ul.duckseditor.model.objets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class Objet {

    protected Body corps;

    public abstract void draw (ShapeRenderer sr);

    public abstract void draw (SpriteBatch sb);

    public abstract void drawLinearVelocity (ShapeRenderer sr);

    public Body getCorps() {
        return corps;
    }
}
