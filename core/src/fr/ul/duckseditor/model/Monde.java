package fr.ul.duckseditor.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import fr.ul.duckseditor.DucksEditor;
import fr.ul.duckseditor.model.objets.Objet;
import fr.ul.duckseditor.model.objets.bloc.Carre;
import fr.ul.duckseditor.model.objets.bloc.Rectangle;
import fr.ul.duckseditor.model.objets.personnage.Bandit;
import fr.ul.duckseditor.model.objets.personnage.Prisonnier;

import java.util.ArrayList;

public class Monde {

    private World monde;
    private static Body[] bords;
    private ArrayList<Objet> objets;
    private ArrayList<Objet> objetsRun;

    public Monde () {
        monde = new World(new Vector2(0.f, -10.f), false);

        bords = new Body[4];

        constructionBords();

        //corps

        objets = new ArrayList<Objet>();

        Objet p = new Prisonnier(monde, new Vector2(450.f, 700.f));
        objets.add(p);

        Objet b = new Bandit(monde, new Vector2(700.f, 800.f));
        objets.add(b);

        Objet c = new Carre(monde, new Vector2(700.f, 500.f));
        objets.add(c);

        Objet r = new Rectangle(monde, new Vector2(800.f, 500.f));
        objets.add(r);
    }

    public void spawn (Objet o) {
        objets.add(o);
    }

    public void spawnCarre (int x, int y) {
        objets.add(new Carre(monde, new Vector2((float)x, (float)y)));
    }

    public void ajouterCarre () {
        Carre c = new Carre(getMonde(), new Vector2(0.f, 0.f));
    }

    private void constructionBords () {
        //bord au sol

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(new Vector2(0.f, 0.f));

        bords[0] = monde.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(DucksEditor.SCREEN_WIDTH, DucksEditor.SCREEN_HEIGHT/6);
        bords[0].createFixture(shape, 0.0f);

        //bord gauche

        def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(new Vector2(-5.f, 0.f));

        bords[1] = monde.createBody(def);

        shape = new PolygonShape();
        shape.setAsBox(5.f, DucksEditor.SCREEN_HEIGHT * 2); //size of 5 outside the screen in width
        bords[1].createFixture(shape, 0.0f);

        //bord droit

        def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(new Vector2(DucksEditor.SCREEN_WIDTH, 0.f));

        bords[2] = monde.createBody(def);

        shape = new PolygonShape();
        shape.setAsBox(5.f, DucksEditor.SCREEN_HEIGHT * 2); //size of 5 outside the screen in width
        bords[2].createFixture(shape, 0.0f);

        //bord du haut

        def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(new Vector2(0.f, DucksEditor.SCREEN_HEIGHT * 2));

        bords[3] = monde.createBody(def);

        shape = new PolygonShape();
        shape.setAsBox(DucksEditor.SCREEN_WIDTH, 5.f); //size of 5 outside the screen in width
        bords[3].createFixture(shape, 0.0f);
        shape.dispose();
    }

    public World getMonde() {
        return monde;
    }

    public void draw (ShapeRenderer sr) {
        for (Objet o : objets) {
            o.draw(sr);
            o.drawLinearVelocity(sr);
        }
    }

    public void draw (SpriteBatch sb) {
        for (Objet o : objets) {
            o.draw(sb);
        }
    }

    public void dispose () {
        monde.dispose();
    }

    public Objet getObjet (int i) {
        return objets.get(i);
    }

    public void run () {
        objetsRun = new ArrayList<Objet>(objets);
    }

}
