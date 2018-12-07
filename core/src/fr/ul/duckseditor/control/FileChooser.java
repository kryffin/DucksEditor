package fr.ul.duckseditor.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import fr.ul.duckseditor.DucksEditor;
import fr.ul.duckseditor.dataFactory.TextureFactory;
import fr.ul.duckseditor.view.EditorScreen;
import fr.ul.duckseditor.view.boutons.*;
import java.io.File;
import java.util.ArrayList;

/**
 * @author KLEINHENTZ 'Kryffin' Nicolas
 */
public class FileChooser {

    /**
     * EditorScreen à lier
     */
    private EditorScreen es;

    /**
     * chemin de sauvegarde des niveaux et images
     */
    private String savePath;

    /**
     * liste des fichiers de sauvegarde déjà présent
     */
    private ArrayList<String> currentFileNames;

    /**
     * indice du prochain fichier à créer
     */
    private int nbFiles;

    private int currentFile;

    private Bouton[] boutons = new Bouton[4];

    private Texture textureBouton;

    private boolean selection;

    private InputProcessor listener;

    private World mondeSelecteur;

    /**
     * Constructeur liant l'EditorScreen et analysant le chemin pour y trouver les fichiers de sauvegarde présents
     * @param es
     */
    public FileChooser (EditorScreen es) {
        this.es = es;
        savePath = "MadDucksFiles";
        currentFileNames = new ArrayList<String>(); //check and register current file names
        nbFiles = -1; //check current file

        File f = new File(savePath);
        if (!f.exists()) {
            f.mkdir();
            System.out.println("Dossier MadDucksFiles inexistant, création...");
        } else {
            System.out.println("Dossier MadDucksFiles déjà existant.");
        }

        mondeSelecteur = new World(new Vector2(0.f, 0.f), true);

        boutons[0] = new BoutonFlecheGauche(mondeSelecteur, new Vector2(DucksEditor.UM_WIDTH / 5, DucksEditor.UM_HEIGHT / 2));
        boutons[1] = new BoutonFlecheDroite(mondeSelecteur, new Vector2((DucksEditor.UM_WIDTH / 5) * 4, DucksEditor.UM_HEIGHT / 2));
        boutons[2] = new BoutonQuitter(mondeSelecteur, new Vector2(DucksEditor.UM_WIDTH / 2 - 1, (DucksEditor.UM_HEIGHT / 5)));
        boutons[3] = new BoutonTexture(mondeSelecteur, new Vector2(DucksEditor.UM_WIDTH / 4, DucksEditor.UM_HEIGHT / 3), new Vector2(DucksEditor.UM_WIDTH / 2, DucksEditor.UM_HEIGHT / 2));

        selection = false;

        listener = new FileChooserListener(es, this, mondeSelecteur);
    }

    /**
     * Met à jour la liste des fichiers du dossier de sauvegarde
     */
    public void update () {
        FileHandle[] files = Gdx.files.local(savePath).list();

        //porcours des fichiers du dossier de sauvegarde
        for (FileHandle f : files) {
            if (!currentFileNames.contains(f.nameWithoutExtension())) {
                //si le fichier n'existe pas déjà on le créé
                currentFileNames.add(f.nameWithoutExtension());
            }
        }
        nbFiles = currentFileNames.size();
    }

    public void save (String str, boolean override) {

        while (currentFileNames.contains("Niveau_" + nbFiles)) {
            nbFiles++;
        }

        String nomFichier;
        if (!override) {
            nomFichier = "Niveau_" + nbFiles;
        } else {
            nomFichier = "Niveau_" + (nbFiles - 1);
        }

        //fichier .mdl

        FileHandle f = new FileHandle(savePath + "/" + nomFichier + ".mdl");
        f.writeString(str, false);

        //fichier .png

        byte[] pixels = ScreenUtils.getFrameBufferPixels(Gdx.graphics.getBackBufferWidth() / 6, 0, (Gdx.graphics.getBackBufferWidth() / 6) * 5, Gdx.graphics.getBackBufferHeight(), true);

        // this loop makes sure the whole screenshot is opaque and looks exactly like what the user is seeing
        for(int i = 4; i < pixels.length; i += 4) {
            pixels[i - 1] = (byte) 255;
        }

        Pixmap pixmap = new Pixmap(Gdx.graphics.getBackBufferWidth() - (Gdx.graphics.getBackBufferWidth() / 6), Gdx.graphics.getBackBufferHeight(), Pixmap.Format.RGBA8888);
        BufferUtils.copy(pixels, 0, pixmap.getPixels(), pixels.length);
        PixmapIO.writePNG(Gdx.files.local(savePath + "/" + nomFichier + ".png"), pixmap);
        pixmap.dispose();
    }

    public void load () {
        currentFile = 0;

        selection = false;

        update();

        es.setShowLoad(true);

        Gdx.input.setInputProcessor(listener);
    }

    public void next () {
        if (currentFile == currentFileNames.size() - 1) {
            currentFile = 0;
        } else {
            currentFile++;
        }
    }

    public void previous () {
        if (currentFile == 0) {
            currentFile = currentFileNames.size() - 1;
        } else {
            currentFile--;
        }
    }

    public void exit () {
        es.quitterCharger();
    }

    public void draw (SpriteBatch sb) {
        sb.draw(TextureFactory.getEditPanel(), 0.f, 0.f, DucksEditor.UM_WIDTH, DucksEditor.UM_HEIGHT);
        if (Gdx.files.local("MadDucksFiles/Niveau_" + currentFile + ".png").exists()) {
            ((BoutonTexture)boutons[3]).setTexture(new Texture(Gdx.files.local("MadDucksFiles/Niveau_" + currentFile + ".png")));
        } else {
            exit();
        }

        for (Bouton b : boutons) {
            b.draw(sb);
        }
    }

    public void action (ArrayList<Body> objetsSelectionnes) {
        for (Body body : objetsSelectionnes) {
            for (Bouton bouton : boutons) {
                if (body == bouton.getCorps() && bouton.toString().equals("BoutonFlecheGauche")) {
                    previous();
                    return;
                } else if (body == bouton.getCorps() && bouton.toString().equals("BoutonFlecheDroite")) {
                    next();
                    return;
                } else if (body == bouton.getCorps() && bouton.toString().equals("BoutonQuitter")) {
                    exit();
                    return;
                } else if (body == bouton.getCorps() && bouton.toString().equals("BoutonTexture")) {
                    if (Gdx.files.local("MadDucksFiles/Niveau_" + currentFile + ".mdl").exists()) {
                        es.chargerNiveau(Gdx.files.local("MadDucksFiles/Niveau_" + currentFile + ".mdl").readString());
                    }
                    return;
                }
            }
        }
    }

}
