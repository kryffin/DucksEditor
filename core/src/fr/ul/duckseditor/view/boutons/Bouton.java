package fr.ul.duckseditor.view.boutons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import fr.ul.duckseditor.DucksEditor;
import fr.ul.duckseditor.model.Monde;

public abstract class Bouton {

    protected Monde monde;
    protected Body corps;
    protected float width;
    protected float height;
    protected float diameter = 0.f;
    protected float x;
    protected float y;

    /**
     * Constructeur de bouton en forme de carré
     * @param monde monde dans lequel mettre le corps du bouton
     * @param position position du bouton
     */
    public Bouton (Monde monde, Vector2 position) {
        this.monde = monde;

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(new Vector2(position));

        corps = monde.getMonde().createBody(def);

        width = DucksEditor.widthToScreen(2);
        height = DucksEditor.heightToScreen(2);

        x = position.x;
        y = position.y;

        PolygonShape carre = new PolygonShape();
        carre.set(new float[]{0.f, 0.f, 0.f, height, width, height, width, 0.f}); // 2x2 UM
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = carre;
        corps.createFixture(fixtureDef);
        carre.dispose();
    }

    /**
     * Construit un bouton avec une largeur et hauteur donnée
     * @param monde monde dans lequel mettre le corps du bouton
     * @param position position du bouton
     * @param widthUM largeur en UM du bouton
     * @param heightUM hauteur en ULM du bouton
     */
    public Bouton (Monde monde, Vector2 position, int widthUM, int heightUM) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(new Vector2(position));

        corps = monde.getMonde().createBody(def);

        width = DucksEditor.widthToScreen(widthUM);
        height = DucksEditor.heightToScreen(heightUM);

        x = position.x;
        y = position.y;

        PolygonShape rectangle = new PolygonShape();
        rectangle.set(new float[]{0.f, 0.f, 0.f, height, width, height, width, 0.f}); // 2x2 UM
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangle;
        corps.createFixture(fixtureDef);
        rectangle.dispose();
    }

    /**
     * Construit un bouton cercle avec son rayon
     * @param monde monde dans lequel mettre le corps du bouton
     * @param position position du bouton
     * @param diameterUM rayon du bouton en UM
     */
    public Bouton (Monde monde, Vector2 position, int diameterUM) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(new Vector2(position));

        corps = monde.getMonde().createBody(def);

        this.diameter = DucksEditor.widthToScreen(diameterUM);
        width = diameter;
        height = diameter;

        x = position.x;
        y = position.y;

        CircleShape cercle = new CircleShape();
        cercle.setRadius(diameter/2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = cercle;
        corps.createFixture(fixtureDef);
        cercle.dispose();
    }

    public abstract void draw (SpriteBatch sb);

    public void draw (ShapeRenderer sr) {
        sr.setColor(Color.GREEN);
        if (diameter != 0.f) {
            sr.circle(corps.getPosition().x, corps.getPosition().y, diameter/2);
        } else {
            sr.rect(corps.getPosition().x, corps.getPosition().y, width, height);
        }
    }

    public void check (float mouseX, float mouseY) {
        //si c'est un cercle on teste avec son diametre
        if (diameter != 0.f) {
            if (mouseX < x + width - diameter/2 && mouseX > x - diameter/2 && mouseY < y + height - diameter/2 && mouseY > y - diameter/2) {
                action();
                System.out.println("CLICK");
            }
        } else {
            if (mouseX < x + width && mouseX > x && mouseY < y + height && mouseY > y) {
                action();
            }
        }
    }

    public void action () {
        System.out.println(toString());
    }

}
