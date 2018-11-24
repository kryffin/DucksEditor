package fr.ul.duckseditor.model.objets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

public abstract class Objet {

    protected Body corps;

    public abstract void draw (SpriteBatch sb);

    public void setType (boolean running) {
        if (running) {
            corps.setType(BodyDef.BodyType.DynamicBody);
        } else {
            corps.setType(BodyDef.BodyType.StaticBody);
        }
    }

    public Body getCorps() {
        return corps;
    }
}
