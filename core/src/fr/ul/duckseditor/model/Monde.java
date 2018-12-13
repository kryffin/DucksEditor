package fr.ul.duckseditor.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import fr.ul.duckseditor.DucksEditor;
import fr.ul.duckseditor.model.objets.Objet;
import fr.ul.duckseditor.model.objets.bloc.Carre;
import fr.ul.duckseditor.model.objets.bloc.Rectangle;
import fr.ul.duckseditor.model.objets.personnage.Prisonnier;
import fr.ul.duckseditor.view.EditorScreen;
import fr.ul.duckseditor.view.boutons.Bouton;

import java.util.ArrayList;

/**
 * @author KLEINHENTZ 'Kryffin' Nicolas
 */
public class Monde {

    private EditorScreen es;

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
    public Monde (EditorScreen es) {
        this.es = es;

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
    public void spawnCarre (float x, float y) {
        objets.add(new Carre(monde, new Vector2(x, y)));
    }

    /**
     * Méthode faisant apparaitre un carré à une position et un angle donné dans le monde
     * @param x position x de l'objet
     * @param y position y de l'objet
     * @param angle rotation du carré
     */
    public void spawnCarre (float x, float y, float angle) {
        objets.add(new Carre(monde, new Vector2(x, y), angle));
    }

    /**
     * Méthode faisant apparaitre un rectangle à une position donnée dans le monde
     * @param x position x de l'objet
     * @param y position y de l'objet
     */
    public void spawnRectangle (float x, float y) {
        objets.add(new Rectangle(monde, new Vector2(x, y)));
    }

    /**
     * Méthode faisant apparaitre un rectangle à une position et un angle donné dans le monde
     * @param x position x de l'objet
     * @param y position y de l'objet
     * @param angle rotation du rectangle
     */
    public void spawnRectangle (float x, float y, float angle) {
        objets.add(new Rectangle(monde, new Vector2(x, y), angle));
    }

    /**
     * Méthode faisant apparaitre un prisonnier à une position donnée dans le monde
     * @param x position x de l'objet
     * @param y position y de l'objet
     */
    public void spawnPrisonnier (float x, float y) {
        objets.add(new Prisonnier(monde, new Vector2(x, y)));
    }

    /**
     * Méthode faisant apparaitre un prisonnier à une position et un angle donné dans le monde
     * @param x position x de l'objet
     * @param y position y de l'objet
     * @param angle rotation du prisonnier
     */
    public void spawnPrisonnier (float x, float y, float angle) {
        objets.add(new Prisonnier(monde, new Vector2(x, y), angle));
    }

    /**
     * Construit les bords du niveau
     */
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
     * Appel des actions correspondantes aux boutons cliqués
     * @param objetsSelectionnes boutons cliqués
     * @param boutons boutons du panneau
     */
    public void action (ArrayList<Body> objetsSelectionnes, ArrayList<Bouton> boutons) {
        for (Body body : objetsSelectionnes) {
            for (Bouton bouton : boutons) {
                if (body == bouton.getCorps() && bouton.toString().equals("BoutonSauvegarder")) {
                    es.sauvegarder(false);
                } else if (body == bouton.getCorps() && bouton.toString().equals("BoutonRemplacer")) {
                    es.sauvegarder(true);
                } else if (body == bouton.getCorps() && bouton.toString().equals("BoutonCharger")) {
                    es.charger();
                } else if (body == bouton.getCorps() && bouton.toString().equals("BoutonCarre")) {
                    spawnCarre(DucksEditor.UM_WIDTH/2, DucksEditor.UM_HEIGHT/2);
                } else if (body == bouton.getCorps() && bouton.toString().equals("BoutonRectangle")) {
                    spawnRectangle(DucksEditor.UM_WIDTH/2, DucksEditor.UM_HEIGHT/2);
                } else if (body == bouton.getCorps() && bouton.toString().equals("BoutonPrisonnier")) {
                    spawnPrisonnier(DucksEditor.UM_WIDTH/2, DucksEditor.UM_HEIGHT/2);
                } else if (body == bouton.getCorps() && bouton.toString().equals("BoutonJouer")) {
                    es.setRunning(true);
                } else if (body == bouton.getCorps() && bouton.toString().equals("BoutonStop")) {
                    es.setRunning(false);
                }
            }
        }
    }

    /**
     * Détruit les objets du niveau
     */
    public void clean () {
        for (Objet o : objets) {
            monde.destroyBody(o.getCorps());
        }
        objets = new ArrayList<Objet>();
    }

    /**
     * @return une Pixmap définissant le screenshot du niveau
     */
    public Pixmap getScreenshot () {
        byte[] pixels = ScreenUtils.getFrameBufferPixels(Gdx.graphics.getWidth() / 6, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), true);

        Pixmap screenShot = new Pixmap(Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), Pixmap.Format.RGBA8888);
        BufferUtils.copy(pixels, 0, screenShot.getPixels(), pixels.length);

        return screenShot;
    }

    /**
     * @return liste des objets du monde
     */
    public ArrayList<Objet> getObjets () {
        return objets;
    }

}
