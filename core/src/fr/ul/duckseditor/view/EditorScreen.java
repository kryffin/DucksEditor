package fr.ul.duckseditor.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import fr.ul.duckseditor.DucksEditor;
import fr.ul.duckseditor.dataFactory.TextureFactory;
import fr.ul.duckseditor.listener.Listener;
import fr.ul.duckseditor.model.Monde;
import fr.ul.duckseditor.model.objets.Objet;

public class EditorScreen extends ScreenAdapter {

    private Listener listener;
    private SpriteBatch sb;
    private ShapeRenderer sr;
    private OrthographicCamera camera;
    private Monde monde;
    private EditorPanel ep;
    private boolean touched;
    private long beginTime;
    private long timeDiff;
    private int sleepTime;
    private boolean running;

    public EditorScreen() {
        sb = new SpriteBatch();
        sr = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, DucksEditor.UM_WIDTH, DucksEditor.UM_HEIGHT);
        camera.update();
        sb.setProjectionMatrix(camera.combined);
        sr.setProjectionMatrix(camera.combined);

        monde = new Monde();

        ep = new EditorPanel(monde, this);
        listener = new Listener(this, ep);
        Gdx.input.setInputProcessor(listener);

        running = false;
    }

    public void render(float delta) {

        //initialisation de la limite fps

        beginTime = System.currentTimeMillis();
        delta = Gdx.graphics.getDeltaTime();

        // gestion des entrées utilisateur

        //géré par le Listener

        // mise à jour du monde

        for (Objet o : monde.getObjets()) {
            o.setType(running);
        }

        monde.getMonde().step(delta, 10, 10);

        // affichage

        sb.begin();

        sb.draw(TextureFactory.getBackground(), 0, 0, DucksEditor.UM_WIDTH, DucksEditor.UM_HEIGHT); //affiche le fond et l'étire
        ep.draw(sb);
        monde.draw(sb);

        Matrix4 debugMatrix=new Matrix4(camera.combined);
        Box2DDebugRenderer debugRenderer=new Box2DDebugRenderer();
        //debugRenderer.render(monde.getMonde(), debugMatrix);

        sb.end();

        //limite fps

        timeDiff = System.currentTimeMillis() - beginTime;
        sleepTime = (int) ((1 / DucksEditor.FPS) - timeDiff);

        if (sleepTime > 0) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            }
        }
    }

    public void dispose() {
        monde.dispose();
        sb.dispose();
        sr.dispose();
    }

    public void setRunning(boolean b) {
        running = b;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Monde getMonde() {
        return monde;
    }

    public EditorPanel getEditorPanel() {
        return ep;
    }

}
