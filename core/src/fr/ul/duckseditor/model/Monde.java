package fr.ul.duckseditor.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import fr.ul.duckseditor.DucksEditor;
import fr.ul.duckseditor.model.objets.Objet;
import fr.ul.duckseditor.model.objets.bloc.Carre;
import fr.ul.duckseditor.model.objets.bloc.Rectangle;
import fr.ul.duckseditor.model.objets.personnage.Prisonnier;
import java.util.ArrayList;

/**
 * @author KLEINHENTZ 'Kryffin' Nicolas
 */
public class Monde {

    /**
     * World contenant les objets
     */
    private World monde;

    /**
     * tableau de body contenant les 4 bords de l'écran
     */
    private static Body[] bords;

    /**
     * liste d'objets contenus dans le monde
     */
    private ArrayList<Objet> objets;

    /**
     * Constructeur vide initialisant le monde avec une gravité de -10.f ainsi que les 4 bords
     */
    public Monde () {
        monde = new World(new Vector2(0.f, -10.f), true);

        bords = new Body[4];

        constructionBords();

        //corps

        objets = new ArrayList<Objet>();
    }

    /**
     * Méthode faisant apparaitre un objet dans le monde
     * @param o objet à faire apparaitre
     */
    public void spawn (Objet o) {
        objets.add(o);
    }

    /**
     * Méthode faisant apparaitre un carre à une position donnée dans le monde
     * @param x position x de l'objet
     * @param y position y de l'objet
     */
    public void spawnCarre (int x, int y) {
        objets.add(new Carre(monde, new Vector2((float)x, (float)y)));
    }

    /**
     * Méthode faisant apparaitre un rectangle à une position donnée dans le monde
     * @param x position x de l'objet
     * @param y position y de l'objet
     */
    public void spawnRectangle (int x, int y) {
        objets.add(new Rectangle(monde, new Vector2((float)x, (float)y)));
    }

    /**
     * Méthode faisant apparaitre un prisonnier à une position donnée dans le monde
     * @param x position x de l'objet
     * @param y position y de l'objet
     */
    public void spawnPrisonnier (int x, int y) {
        objets.add(new Prisonnier(monde, new Vector2((float)x, (float)y)));
    }

    private void constructionBords () {
        //bord au sol

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(new Vector2(0.f, 0.f));

        bords[0] = monde.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(DucksEditor.UM_WIDTH, DucksEditor.UM_HEIGHT/6);
        bords[0].createFixture(shape, 0.0f);

        //bord gauche

        def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(new Vector2(-5.f, 0.f));

        bords[1] = monde.createBody(def);

        shape = new PolygonShape();
        shape.setAsBox(5.f, DucksEditor.UM_HEIGHT * 2); //size of 5 outside the screen in width
        bords[1].createFixture(shape, 0.0f);

        //bord droit

        def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(new Vector2(DucksEditor.UM_WIDTH + 5.f, 0.f));

        bords[2] = monde.createBody(def);

        shape = new PolygonShape();
        shape.setAsBox(5.f, DucksEditor.UM_HEIGHT * 2); //size of 5 outside the screen in width
        bords[2].createFixture(shape, 0.0f);

        //bord du haut

        def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(new Vector2(0.f, DucksEditor.UM_HEIGHT * 2));

        bords[3] = monde.createBody(def);

        shape = new PolygonShape();
        shape.setAsBox(DucksEditor.UM_WIDTH, 5.f); //size of 5 outside the screen in width
        bords[3].createFixture(shape, 0.0f);
        shape.dispose();
    }

    /**
     * @return World du monde
     */
    public World getMonde() {
        return monde;
    }

    /**
     * Méthode appelant la méthode d'affichage des objets du monde un par un
     * @param sb
     */
    public void draw (SpriteBatch sb) {
        for (Objet o : objets) {
            o.draw(sb);
        }
    }

    /**
     * Disposition du World
     */
    public void dispose () {
        monde.dispose();
    }

    /**
     * Suppression d'un objet du monde
     * @param o objet à supprimer du monde
     */
    public void supprimer (Objet o) {
        monde.destroyBody(o.getCorps());
        objets.remove(o);
    }

    /**
     * @return liste des objets du monde
     */
    public ArrayList<Objet> getObjets () {
        return objets;
    }

}
