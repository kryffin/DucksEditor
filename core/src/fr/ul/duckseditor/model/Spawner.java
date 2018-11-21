package fr.ul.duckseditor.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import fr.ul.duckseditor.model.objets.Objet;
import fr.ul.duckseditor.model.objets.bloc.Carre;
import fr.ul.duckseditor.model.objets.bloc.Rectangle;
import fr.ul.duckseditor.model.objets.personnage.Prisonnier;

public class Spawner {

    private static Spawner instance = new Spawner();

    private Spawner () {
        objet = null;
    }

    private Monde monde;
    private SpriteBatch sb;
    private OrthographicCamera camera;
    private Objet objet;
    private boolean touche = false;
    private short type; //0 carre 1 rectangle 2 prisonnier

    public static Spawner getInstance () {
        return instance;
    }

    public void setCamera (OrthographicCamera camera) {
        this.instance.camera = camera;
    }

    public void setMonde (Monde monde) {
        this.monde = monde;
    }

    public void setSpriteBatch (SpriteBatch sb) {
        this.sb = sb;
    }

    public void spawnCarre () {
        if (objet == null) {
            Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0.f);
            camera.unproject(mouse);
            objet = new Carre(monde.getMonde(), new Vector2(mouse.x, mouse.y));
            type = 0;
        }
    }

    public void spawnRectangle () {
        if (objet == null) {
            Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0.f);
            camera.unproject(mouse);
            objet = new Rectangle(monde.getMonde(), new Vector2(mouse.x, mouse.y));
            System.out.println("Spawn rectangle");
            type = 1;
        }
    }

    public void spawnPrisonnier () {
        if (objet == null) {
            Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0.f);
            camera.unproject(mouse);
            objet = new Prisonnier(monde.getMonde(), new Vector2(mouse.x, mouse.y));
            System.out.println("Spawn prisonnier");
            type = 2;
        }
    }

    public void draw () {
        if (objet == null) {
            return;
        }

        objet.draw(sb);
    }

    public void manage () {
        if (Gdx.input.isTouched()) {
            touche = true;
        } else {
            touche = false;
        }

        if (objet != null && touche) {
            Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0.f);
            camera.unproject(mouse);

            switch (type) {
                case 0:
                    objet = new Carre(monde.getMonde(), new Vector2(mouse.x, mouse.y));
                    break;

                case 1:
                    objet = new Rectangle(monde.getMonde(), new Vector2(mouse.x, mouse.y));
                    break;

                case 2:
                    objet = new Prisonnier(monde.getMonde(), new Vector2(mouse.x, mouse.y));
                    break;

                default:
                    break;
            }

            System.out.println("Deplacement carre : x" + mouse.x + " : y" + mouse.y);
        } else if (objet != null && !touche) {
            //placer l'objet
            monde.spawn(objet);
            touche = false;
            objet = null;
        }
    }

}
