package fr.ul.duckseditor.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import fr.ul.duckseditor.DucksEditor;
import fr.ul.duckseditor.control.FileChooser;
import fr.ul.duckseditor.dataFactory.TextureFactory;
import fr.ul.duckseditor.control.Listener;
import fr.ul.duckseditor.model.Monde;
import fr.ul.duckseditor.model.objets.Objet;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author KLEINHENTZ 'Kryffin' Nicolas
 */
public class EditorScreen extends ScreenAdapter {

    /**
     * ecouteur sur le jeu (touches clavier/souris/tactile)
     */
    private Listener listener;

    /**
     * SpriteBatch pour l'affichage des éléments graphiques
     */
    private SpriteBatch sb;

    /**
     * camera visant le jeu
     */
    private OrthographicCamera camera;

    /**
     * Monde contenant les objets du jeu
     */
    private Monde monde;

    /**
     * panneau contenant les boutons
     */
    private EditorPanel ep;

    /**
     * temps de début pour la gestion des FPS
     */
    private long beginTime;

    /**
     * différentiel de temps pour la gestion des FPS
     */
    private long timeDiff;

    /**
     * temps d'attente pour la gestion des FPS
     */
    private int sleepTime;

    /**
     * vrai si l'animation marche, faux sinon
     */
    private boolean running;

    /**
     * gestionnaire des fichiers de sauvegarde
     */
    private FileChooser fc;

    private boolean showLoad;

    /**
     * Constructeur vide initialisant l'écran
     */
    public EditorScreen() {
        sb = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, DucksEditor.UM_WIDTH, DucksEditor.UM_HEIGHT);
        camera.update();
        sb.setProjectionMatrix(camera.combined);

        monde = new Monde(this);

        ep = new EditorPanel(monde, this);
        listener = new Listener(this, ep);
        Gdx.input.setInputProcessor(listener);

        running = false;

        fc = new FileChooser(this);

        showLoad = false;
    }

    /**
     * Méthode affichant les éléments graphiques
     * @param delta temps utilisé pour le calcul FPS
     */
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
        if (showLoad) {
            fc.draw(sb);
        } else {
            sb.draw(TextureFactory.getBackground(), 0, 0, DucksEditor.UM_WIDTH, DucksEditor.UM_HEIGHT); //affiche le fond et l'étire
            ep.draw(sb);
            monde.draw(sb);
            System.out.println(monde.getObjets());
        }

        //affichage debug
        Matrix4 debugMatrix = new Matrix4(camera.combined);
        Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
        //debugRenderer.render(monde.getMonde(), debugMatrix);

        sb.end();

        //gestion des FPS

        timeDiff = System.currentTimeMillis() - beginTime;
        sleepTime = (int) ((1 / DucksEditor.FPS) - timeDiff);

        if (sleepTime > 0) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * Disposition de l'écran, du monde et du SpriteBatch
     */
    public void dispose() {
        monde.dispose();
        sb.dispose();
    }

    /**
     * Methode supprimant des objets du monde
     * @param objets objets à supprimer
     */
    public void supprimerObjets (ArrayList<Body> objets) {
        //iterator sur les objets pour itérer malgré la modification concurente de la liste
        Iterator<Objet> iteratorMonde = monde.getObjets().iterator();

        while (iteratorMonde.hasNext()) {
            Objet o = iteratorMonde.next();
            for (Body b : objets) {

                if (o.getCorps() == b) {
                    iteratorMonde.remove(); //suppression de l'élément dans l'itérateur
                    monde.supprimer(o); //suppression de l'objet dans le monde
                }

            }
        }
    }

    public void sauvegarder (boolean override) {
        StringBuilder str = new StringBuilder();
        for (Objet o : monde.getObjets()) {
            str.append(o.toString() + "\n");
        }

        System.out.println(str.toString());

        fc.update();

        fc.save(str.toString(), override);
    }

    public void charger () {
        fc.load();
    }

    public void chargerNiveau (String niveau) {
        monde.clean();

        String[] lines = niveau.split(System.getProperty("line.separator"));
        for (int i = 0; i < lines.length; i++) {
            System.out.println(lines[i]);
            String[] description = lines[i].split(":");
            if (description[0].equals("Rectangle")) {
                monde.spawnRectangle(Float.parseFloat(description[1]), Float.parseFloat(description[2]), Float.parseFloat(description[3]));
                System.out.println("Spawn de rectangle");
            } else if (description[0].equals("Carre")) {
                monde.spawnCarre(Float.parseFloat(description[1]), Float.parseFloat(description[2]), Float.parseFloat(description[3]));
                System.out.println("Sapwn de carre");
            } else if (description[0].equals("Prisonnier")) {
                monde.spawnPrisonnier(Float.parseFloat(description[1]), Float.parseFloat(description[2]), Float.parseFloat(description[3]));
                System.out.println("spawn de prisonnier");
            }
        }

        System.out.println(monde.getObjets());

        quitterCharger();
    }

    public void quitterCharger () {
        System.out.println("Fin de sélection de niveau");
        Gdx.input.setInputProcessor(listener);
        setShowLoad(false);
    }

    /**
     * Setter sur l'animation de l'écran
     * @param b vrai si l'animation marche, faux sinon
     */
    public void setRunning(boolean b) {
        running = b;
    }

    /**
     * @return camera de l'écran
     */
    public OrthographicCamera getCamera() {
        return camera;
    }

    /**
     * @return Monde contenant les objets
     */
    public Monde getMonde() {
        return monde;
    }

    /**
     * @return EditorPanel du jeu
     */
    public EditorPanel getEditorPanel() {
        return ep;
    }

    public void setShowLoad (boolean b) {
        showLoad = b;
    }

}
