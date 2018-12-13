package fr.ul.duckseditor.control;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import fr.ul.duckseditor.view.EditorScreen;
import java.util.ArrayList;

public class FileChooserListener implements InputProcessor {

    /**
     * EditorScreen à lier
     */
    private EditorScreen es;

    /**
     * FileChooser à manipuler
     */
    private FileChooser fc;

    /**
     * objets cliqués
     */
    private ArrayList<Body> objetsSelectionnes;

    /**
     * monde du sélectionneur de niveau
     */
    private World monde;

    /**
     * Constructeur liant l'EditorScreen, le FileChooser et le monde
     * @param es EditorScreen
     * @param fc FileChooser
     * @param monde World
     */
    public FileChooserListener (EditorScreen es, FileChooser fc, World monde) {
        this.es = es;
        this.fc = fc;
        this.monde = monde;
        objetsSelectionnes = new ArrayList<Body>();
    }

    /**
     * Méthode appelé quand une touche est appuyée
     * @param keycode code de la touche
     * @return
     */
    @Override
    public boolean keyDown(int keycode) {
        return true;
    }

    /**
     * Méthode appelé quand une touche est relachée
     * @param keycode code de la touche
     * @return
     */
    @Override
    public boolean keyUp(int keycode) {
        return true;
    }

    /**
     * Méthode appelé quand une touche est tapée
     * @param character caractère de la touche
     * @return
     */
    @Override
    public boolean keyTyped(char character) {
        return true;
    }

    /**
     * Méthode appelée quand l'écran est touché
     * @param screenX position X du contact
     * @param screenY position Y du contact
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
        monde.QueryAABB(new QueryCallback() {
            @Override
            public boolean reportFixture(Fixture fixture) {
                objetsSelectionnes.add(fixture.getBody());
                return false;
            }
        }, contact.x - 0.005f, contact.y - 0.005f, contact.x + 0.005f, contact.y + 0.005f);

        fc.action(objetsSelectionnes);

        return true;
    }

    /**
     * Méthode appelée quand l'écran est relaché
     * @param screenX position X du contact
     * @param screenY position Y du contact
     * @param pointer
     * @param button
     * @return
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        objetsSelectionnes = new ArrayList<Body>();

        return true;
    }

    /**
     * Méthode appelée quand le contact est glissé
     * @param screenX position X actuelle du contact
     * @param screenY position Y actuelle du contact
     * @param pointer
     * @return
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return true;
    }

    /**
     * Méthode appelée quand la souris est déplacée
     * @param screenX position X de la souris
     * @param screenY position Y de la souris
     * @return
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return true;
    }

    /**
     * Méthode appelée quand la molette est utilisée
     * @param amount montant
     * @return
     */
    @Override
    public boolean scrolled(int amount) {
        return true;
    }

}
