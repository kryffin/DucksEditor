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

public class Listener implements InputProcessor {

    private EditorScreen es;
    private EditorPanel ep;
    private ArrayList<Body> objetsSelectionnes;
    private Vector2 lastMousePos;

    public Listener (EditorScreen es, EditorPanel ep) {
        this.es = es;
        this.ep = ep;
        objetsSelectionnes = new ArrayList<Body>();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE) {
            es.dispose();
            Gdx.app.exit();
        }
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
        Vector3 mouse = new Vector3(screenX, screenY, 0.f);
        es.getCamera().unproject(mouse);

        es.getMonde().getMonde().QueryAABB(new QueryCallback() {
            @Override
            public boolean reportFixture(Fixture fixture) {
                objetsSelectionnes.add(fixture.getBody());
                return false;
            }
        }, mouse.x - 0.005f, mouse.y - 0.005f, mouse.x + 0.005f, mouse.y + 0.005f);

        for (Body oSelectionne : objetsSelectionnes) {
            for (Objet oMonde : es.getMonde().getObjets()) {
                if (oMonde.getCorps() == oSelectionne) {
                    lastMousePos = new Vector2(mouse.x, mouse.y);
                    es.setRunning(false);
                }
            }
            for (Bouton b : ep.getBoutons()) {
                if (b.getCorps() == oSelectionne) {
                    b.action();
                }
            }
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        objetsSelectionnes = new ArrayList<Body>();
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector3 mouse = new Vector3(screenX, screenY, 0.f);
        es.getCamera().unproject(mouse);

        for (Body oSelectionne : objetsSelectionnes) {
            for (Objet oMonde : es.getMonde().getObjets()) {
                if (oMonde.getCorps() == oSelectionne) {
                    Objet o = (Objet)oSelectionne.getUserData();

                    Vector2 vector = new Vector2(mouse.x - lastMousePos.x, mouse.y - lastMousePos.y);
                    o.getCorps().setTransform(o.getCorps().getPosition().x + vector.x, o.getCorps().getPosition().y + vector.y, o.getCorps().getAngle());

                    lastMousePos.set(mouse.x, mouse.y);
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
