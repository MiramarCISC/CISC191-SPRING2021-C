package edu.sdccd.cisc191.c.GUI;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {

    // Sprite width and height
    private static final int SPRITE_W = 79;
    private static final int SPRITE_H = 123;

    public Canvas getSprite(int charPosition) {
        // Create a canvas to insert player sprite
        Canvas canvas = new Canvas(SPRITE_W, SPRITE_H);
        Image sprite = new Image("cards.png");
        GraphicsContext g = canvas.getGraphicsContext2D();

        // Determine the sprite to use from the sprite sheet
        g.drawImage(sprite, (79 * charPosition), 0, 79, 123,
                0, 0, 79, 123);

        return canvas;
    }
}
