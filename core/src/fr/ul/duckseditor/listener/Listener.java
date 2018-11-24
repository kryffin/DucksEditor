package fr.ul.duckseditor.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import fr.ul.duckseditor.model.objets.Objet;
import fr.ul.duckseditor.view.EditorPanel;
import fr.ul.duckseditor.view.EditorScreen;
import fr.ul.duckseditor.view.boutons.Bouton;
import java.util.ArrayList;

/**
 * @author KLEINHENTZ 'Kryffin' Nicolas
 */
public class Listener implements InputProcessor {

    /**
     * EditorScreen relié pour consultation/modification
     */
    private EditorScreen es;

    /**
     * EditorPanel relié pour consultation/modification
     */
    private EditorPanel ep;

    /**
     * Liste des objets sélectionnés par un clic
     */
    private ArrayList<Body> objetsSelectionnes;

    /**
     * Position du porécédent contact pour le cas d'un glisser/déposer
     */
    private Vector2 lastContactPos;

    /**
     * Constructeur liant l'EditorScreen et l'EditorPanel
     * @param es EditorScreen à lier
     * @param ep EditorPanel à lier
     */
    public Listener (EditorScreen es, EditorPanel ep) {
        this.es = es;
        this.ep = ep;
        objetsSelectionnes = new ArrayList<Body>();
    }

    /**
     * Méthode appelée lors de l'appui d'une touche sur le clavier
     * @param keycode code de la touche appuyée
     * @return
     */
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE) {
            es.dispose();
            Gdx.app.exit();
        }
        return true;
    }

    /**
     * Méthode appelée lors du relachement d'une touche du clavier
     * @param keycode code de la touche relachée
     * @return
     */
    @Override
    public boolean keyUp(int keycode) {
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return true;
    }

    /**
     * Méthode appelée lors du clic de souris ou d'un appui tactile
     * @param screenX position x du contact
     * @param screenY position y du contact
     * @param pointer
     * @param button
     * @return
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //récupération du contact dans un vecteur
        Vector3 contact = new Vector3(screenX, screenY, 0.f);
        es.getCamera().unproject(contact);

        //récupération des body des objets selectionnés
        es.getMonde().getMonde().QueryAABB(new QueryCallback() {
            @Override
            public boolean reportFixture(Fixture fixture) {
                objetsSelectionnes.add(fixture.getBody());
                return false;
            }
        }, contact.x - 0.005f, contact.y - 0.005f, contact.x + 0.005f, contact.y + 0.005f);

        //parcours des objets selectionnés
        for (Body oSelectionne : objetsSelectionnes) {
            //parcours des objets du monde
            for (Objet oMonde : es.getMonde().getObjets()) {
                if (oMonde.getCorps() == oSelectionne) {
                    //retenue de la position du contact et arrêt de l'animation si un objet a été sélectionné
                    lastContactPos = new Vector2(contact.x, contact.y);
                    es.setRunning(false);
                }
            }
            //parcours des boutons de l'EditorPanel
            for (Bouton b : ep.getBoutons()) {
                if (b.getCorps() == oSelectionne) {
                    //appel de l'action du bouton cliqué
                    b.action();
                }
            }
        }

        return true;
    }

    /**
     * Méthode appelée lorsque que le clic/contact tactile est relaché
     * @param screenX position x du contact
     * @param screenY position y du contact
     * @param pointer
     * @param button
     * @return
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        //récupération du contact dans un vecteur
        Vector3 contact = new Vector3(screenX, screenY, 0.f);
        es.getCamera().unproject(contact);

        //parcours des fixture de la poubelle et test si le relachement du contact y a été produit
        for (Fixture f : ep.getPoubelle().getCorps().getFixtureList()) {
            if (f.testPoint(contact.x, contact.y)) {
                //suppression des objets selectionnés si relachés sur la poubelle
                es.supprimerObjets(objetsSelectionnes);
            }
        }

        objetsSelectionnes = new ArrayList<Body>();

        return true;
    }

    /**
     * Méthode appelée lors du glissé avec le clic/doigt
     * @param screenX position x du contact
     * @param screenY position y du contact
     * @param pointer
     * @return
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        //récupération du contact dans un vecteur
        Vector3 mouse = new Vector3(screenX, screenY, 0.f);
        es.getCamera().unproject(mouse);

        //parcours des objets delectionnés
        for (Body oSelectionne : objetsSelectionnes) {
            //parcours des objets du monde
            for (Objet oMonde : es.getMonde().getObjets()) {
                if (oMonde.getCorps() == oSelectionne) {
                    //création d'un vecteur entre le précédent contact et l'actuel et déplacement des objets selectionnés par rapport à ce vecteur
                    Objet o = (Objet)oSelectionne.getUserData();

                    Vector2 vector = new Vector2(mouse.x - lastContactPos.x, mouse.y - lastContactPos.y);
                    o.getCorps().setTransform(o.getCorps().getPosition().x + vector.x, o.getCorps().getPosition().y + vector.y, o.getCorps().getAngle());

                    //mise à jour du dernier contact
                    lastContactPos.set(mouse.x, mouse.y);
                }
            }
        }

        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return true;
    }
}
