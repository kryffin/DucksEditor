package fr.ul.duckseditor.model.objets.personnage;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import fr.ul.duckseditor.dataFactory.TextureFactory;

public class Bandit extends Personnage {

    public Bandit(World monde, Vector2 position) {
        super(monde, position);
        pv = 20;
    }

    @Override
    public void draw(SpriteBatch sb) {
        float rotation = (float)Math.toDegrees(corps.getAngle());
        Sprite s = new Sprite(TextureFactory.getTargetBeige());
        s.setSize(diameter, diameter);
        s.setPosition(corps.getPosition().x - diameter/2, corps.getPosition().y - diameter/2);
        s.setOriginCenter();
        s.setRotation(rotation);
        s.draw(sb);
    }

}
