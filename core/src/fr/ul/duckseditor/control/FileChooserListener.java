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

    private EditorScreen es;

    private FileChooser fc;

    private ArrayList<Body> objetsSelectionnes;

    private World monde;

    public FileChooserListener (EditorScreen es, FileChooser fc, World monde) {
        this.es = es;
        this.fc = fc;
        this.monde = monde;
        objetsSelectionnes = new ArrayList<Body>();
    }

    @Override
    public boolean keyDown(int keycode) {
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return true;
    }

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

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        objetsSelectionnes = new ArrayList<Body>();

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
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
