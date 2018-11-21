package fr.ul.duckseditor.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import fr.ul.duckseditor.DucksEditor;
import fr.ul.duckseditor.dataFactory.TextureFactory;
import fr.ul.duckseditor.model.Monde;
import fr.ul.duckseditor.model.Spawner;

public class EditorScreen extends ScreenAdapter {

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

    public EditorScreen () {
        sb = new SpriteBatch();
        sr = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, DucksEditor.SCREEN_WIDTH, DucksEditor.SCREEN_HEIGHT);
        camera.update();
        sb.setProjectionMatrix(camera.combined);
        sr.setProjectionMatrix(camera.combined);

        monde = new Monde();

        ep = new EditorPanel(monde, this);

        Spawner.getInstance().setMonde(monde);
        Spawner.getInstance().setSpriteBatch(sb);
        Spawner.getInstance().setCamera(camera);

        running = false;
    }

    public void render (float delta) {

        //initialisation de la limite fps

        beginTime = System.currentTimeMillis();
        delta = Gdx.graphics.getDeltaTime();

        // gestion des entrées utilisateur

        if (Gdx.input.isTouched()) {
            Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0.f);
            camera.unproject(mouse);
            ep.check(mouse.x, mouse.y);
        }
        Spawner.getInstance().manage();

        // mise à jour du monde

        if (running) {
            monde.getMonde().step(Gdx.graphics.getDeltaTime(), 10, 10);
        }

        // affichage

        sb.begin();
        sb.draw(TextureFactory.getBackground(), 0, 0, DucksEditor.SCREEN_WIDTH, DucksEditor.SCREEN_HEIGHT); //affiche le fond et l'étire
        ep.draw(sb);
        monde.draw(sb);
        Spawner.getInstance().draw();
        sb.end();

        sr.begin(ShapeRenderer.ShapeType.Line);
        ep.draw(sr);
        monde.draw(sr);
        sr.end();

        //limite fps

        timeDiff = System.currentTimeMillis() - beginTime;
        sleepTime = (int)((1/DucksEditor.FPS) - timeDiff);

        if(sleepTime > 0){
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {}
        }
    }

    public void dispose () {
        monde.dispose();
        sb.dispose();
        sr.dispose();
    }

    public void run () {
        running = true;
        monde.run();
    }

}
