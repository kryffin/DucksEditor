package fr.ul.duckseditor.control;

import fr.ul.duckseditor.view.EditorScreen;

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
    private int currentFile;

    /**
     * Constructeur liant l'EditorScreen et analysant le chemin pour y trouver les fichiers de sauvegarde présents
     * @param es
     */
    public FileChooser (EditorScreen es) {
        this.es = es;
        savePath = "...";
        currentFileNames = new ArrayList<String>(); //check and register current file names
        currentFile = -1; //check current file
    }

}
