package fr.ul.duckseditor.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import fr.ul.duckseditor.DucksEditor;
import fr.ul.duckseditor.dataFactory.TextureFactory;
import fr.ul.duckseditor.model.Monde;
import fr.ul.duckseditor.view.boutons.*;
import fr.ul.duckseditor.view.boutons.boutonsBlocks.BoutonCarre;
import fr.ul.duckseditor.view.boutons.boutonsBlocks.BoutonRectangle;
import fr.ul.duckseditor.view.boutons.boutonsPersonnages.BoutonPrisonnier;
import java.util.ArrayList;

/**
 * @author KLEINHENTZ 'Kryffin' Nicolas
 */
public class EditorPanel {

    /**
     * lmargeur du panneau
     */
    private float width;

    /**
     * hauteur du panneau
     */
    private float height;

    /**
     * liste des boutons du panneau
     */
    private ArrayList<Bouton> boutons;

    /**
     * Constructeur avec le monde dans lequel ajouter les boutons ainsi que l'EditorScreen dans lequel ajouter le panneau
     * @param monde
     * @param es
     */
    public EditorPanel (Monde monde, EditorScreen es) {
        width = DucksEditor.UM_WIDTH / 6;
        height = DucksEditor.UM_HEIGHT;

        boutons = new ArrayList<Bouton>();

        //première ligne
        boutons.add(new BoutonPoubelle(monde, new Vector2(width/4 - (width/4) + (width/40), DucksEditor.UM_HEIGHT - 2 - 1.f)));
        boutons.add(new BoutonFlecheGauche(monde, new Vector2((width/4) * 2 - (width/4) + (width/40), DucksEditor.UM_HEIGHT - 2 - 1.f)));
        boutons.add(new BoutonFlecheDroite(monde, new Vector2((width/4) * 3 - (width/4) + (width/40), DucksEditor.UM_HEIGHT - 2 - 1.f)));
        boutons.add(new BoutonQuitter(monde, new Vector2((width/4) * 4 - (width/4) + (width/40), DucksEditor.UM_HEIGHT - 2 - 1.f)));

        //seconde ligne
        boutons.add(new BoutonCharger(monde, new Vector2(width/4 - (width/4) + (width/40), DucksEditor.UM_HEIGHT - 4 - 2.f)));
        boutons.add(new BoutonSauvegarder(monde, new Vector2((width/4) * 2 - (width/4) + (width/40), DucksEditor.UM_HEIGHT - 4 - 2.f)));
        boutons.add(new BoutonRemplacer(monde, new Vector2((width/4) * 3 - (width/4) + (width/40), DucksEditor.UM_HEIGHT - 4 - 2.f)));

        //troisième ligne
        boutons.add(new BoutonJouer(monde, new Vector2(width/4 - (width/4) + (width/40), DucksEditor.UM_HEIGHT - 6 - 3.f), es));
        boutons.add(new BoutonStop(monde, new Vector2((width/4) * 2 - (width/4) + (width/40), DucksEditor.UM_HEIGHT - 6 - 3.f), es));

        //troisième ligne
        boutons.add(new BoutonCarre(monde, new Vector2(width/4 - (width/4) + (width/40), DucksEditor.UM_HEIGHT - 8 - 4.f)));
        boutons.add(new BoutonRectangle(monde, new Vector2((width/4) * 2 - (width/4) + (width/40), DucksEditor.UM_HEIGHT - 8 - 6.f)));
        boutons.add(new BoutonPrisonnier(monde, new Vector2((width/4) * 3 - (width/4) + (width/40), DucksEditor.UM_HEIGHT - 8 - 4.f)));

    }

    /**
     * Méthode affichant le fond du panneau puis appel l'affichage des boutons un a un
     * @param sb
     */
    public void draw (SpriteBatch sb) {
        sb.setColor(1.f, 1.f, 1.f, 0.75f);
        sb.draw(TextureFactory.getEditPanel(), 0.f, 0.f, width, height);
        sb.setColor(1.f, 1.f, 1.f, 1.f);
        for (Bouton b : boutons) {
            b.draw(sb);
        }
    }

    public void draw (ShapeRenderer sr) {
        for (Bouton b : boutons) {
            b.draw(sr);
        }
    }

    /**
     * @return liste des boutons du panneau
     */
    public ArrayList<Bouton> getBoutons () {
        return boutons;
    }

    /**
     * @return bouton poubelle
     */
    public Bouton getPoubelle () {
        return boutons.get(0);
    }

}
