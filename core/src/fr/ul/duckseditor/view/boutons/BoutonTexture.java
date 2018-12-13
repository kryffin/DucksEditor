package fr.ul.duckseditor.view.boutons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import fr.ul.duckseditor.dataFactory.TextureFactory;

public class BoutonTexture extends Bouton {

    /**
     * Image du bouton
     */
    private Texture texture;

    /**
     * Constructeur créant le bouton à une position donnée dans un monde donné
     * @param world monde
     * @param position position du bouton
     * @param size taille de l'image
     */
    public BoutonTexture (World world, Vector2 position, Vector2 size) {
        super(world, position, size);
    }

    /**
     * Affiche le bouton à l'écran
     * @param sb SpriteBatch utilisé pour l'affichage
     */
    @Override
    public void draw(SpriteBatch sb) {
        if (texture == null) {
            sb.draw(TextureFactory.getBlock(), corps.getPosition().x, corps.getPosition().y, width, height);
        } else {
            sb.draw(texture, corps.getPosition().x, corps.getPosition().y, width, height);
        }
    }

    /**
     * Change l'image du bouton
     * @param texture image à donner au bouton
     */
    public void setTexture (Texture texture) {
        this.texture = texture;
    }

    @Override
    public String toString() {
        return "BoutonTexture";
    }
}
