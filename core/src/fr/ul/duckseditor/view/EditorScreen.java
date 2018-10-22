package fr.ul.duckseditor.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import fr.ul.duckseditor.DucksEditor;
import fr.ul.duckseditor.dataFactory.TextureFactory;
import fr.ul.duckseditor.model.Monde;
import fr.ul.duckseditor.model.objets.Objet;

public class EditorScreen extends ScreenAdapter {

    private SpriteBatch sb;
    private ShapeRenderer sr;
    private OrthographicCamera camera;
    private Monde monde;

    public EditorScreen () {
        sb = new SpriteBatch();
        sr = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, DucksEditor.SCREEN_WIDTH, DucksEditor.SCREEN_HEIGHT);
        camera.update();
        sb.setProjectionMatrix(camera.combined);

        monde = new Monde();
    }

    public void render (float delta) {

        // gestion des entrées utilisateur

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            monde.getMonde().setGravity(new Vector2(0.f, 10.f));
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            monde.getMonde().setGravity(new Vector2(0.f, -10.f));
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            monde.getMonde().setGravity(new Vector2(10.f, 0.f));
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            monde.getMonde().setGravity(new Vector2(-10.f, 0.f));
        }

        // mise à jour du monde

        monde.getMonde().step(Gdx.graphics.getDeltaTime(), 10, 10);

        // affichage

        sb.begin();
        sb.draw(TextureFactory.getBackground(), 0, 0, DucksEditor.SCREEN_WIDTH, DucksEditor.SCREEN_HEIGHT); //affiche le fond et l'étire
        sb.end();

        sr.setColor(Color.RED);
        sr.setProjectionMatrix(camera.combined);

        sr.begin(ShapeRenderer.ShapeType.Line);
        monde.draw(sr);
        sr.end();
    }

    public void dispose () {
        sb.dispose();
        sr.dispose();
    }

}
