package fr.ul.duckseditor.view.boutons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import fr.ul.duckseditor.dataFactory.TextureFactory;

public class BoutonTexture extends Bouton {

    private Texture texture;

    public BoutonTexture (World world, Vector2 position, Vector2 size) {
        super(world, position, size);
    }

    @Override
    public void draw(SpriteBatch sb) {
        if (texture == null) {
            sb.draw(TextureFactory.getBlock(), corps.getPosition().x, corps.getPosition().y, width, height);
        } else {
            sb.draw(texture, corps.getPosition().x, corps.getPosition().y, width, height);
        }
    }

    public void setTexture (Texture texture) {
        this.texture = texture;
    }

    @Override
    public String toString() {
        return "BoutonTexture";
    }
}
