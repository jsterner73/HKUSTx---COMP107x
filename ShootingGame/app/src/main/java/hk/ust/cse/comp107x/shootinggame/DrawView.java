package hk.ust.cse.comp107x.shootinggame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by muppala on 23/5/15.
 */
public class DrawView extends View {

    private int width, height;

    Context mContext;

    // We can have multiple bullets and explosions
    // keep track of them in ArrayList
    ArrayList<Bullet> bullets;
    ArrayList<Explosion> explosions;
    Cannon cannon;
    AndroidGuy androidGuy;

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        // create a cannon object
        cannon = new Cannon(Color.BLUE,mContext);

        // create arraylists to keep track of bullets and explosions
        bullets = new ArrayList<Bullet> ();
        explosions = new ArrayList<Explosion>();

        // create the falling Android Guy
        androidGuy = new AndroidGuy(Color.RED, mContext);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawGameBoard(canvas);

        // Delay for a short while before forcing another redraw of the screen
        // If we update the screen every 30 ms, it's sufficiently fast 33 fps > 24 fps
        // needed for the human eye to see the motion as continuous.
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
        }

        // A call to invalidate causes the Android framework to call the onDraw
        // method of the DrawView
        // Thus every time the screen is refreshed, the framework is again
        // forced to call the onDraw
        // method. This creates the animation on the screen to simulate the game
        // playing
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = w;
        height = h;

        cannon.setBounds(0,0,width,height);
        androidGuy.setBounds(0,0,width,height);
        for (int i = 0; i < bullets.size(); i++ ) {
            bullets.get(i).setBounds(0,0,width,height);
        }

    }

    public void drawGameBoard(Canvas canvas) {
        canvas.drawColor(Color.WHITE);     //if you want another background color
        // Draw the cannon
        // TODO

        // Draw all the bullets
        // TODO

        // Draw all the explosions, at those locations where the bullet
        // hits the Android Guy
        // TODO

        // If the Android Guy is falling, check to see if any of the bullets
        // hit the Guy. Draw the Android Guy
        // TODO

    }

    // Move the cannon left or right
    public void moveCannonLeft() {
        cannon.moveLeft();
    }

    public void moveCannonRight() {
        cannon.moveRight();
    }

    // Whenever the user shoots a bullet, create a new bullet moving upwards
    public void shootCannon() {

        bullets.add(new Bullet(Color.RED, mContext, cannon.getPosition(), (float) (height-40)));

    }
}
