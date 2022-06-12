package org.uialert;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.awt.GLCanvas;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    static float rotate = 0;

    public static void main(String[] args) {

        final Frame frame = new Frame("Ex");
        Engine engine = new Engine();
        frame.add(engine.getCanvas());
        frame.setSize(640, 480);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowevent) {
                try {
                    engine.dispose();
                    frame.remove(engine.getCanvas());
                } catch (Exception e) {
                    //throw new RuntimeException(e);
                }
                frame.dispose();
                System.exit(0);
            }
        });

        engine.setEngineListener(new EngineListener() {
            @Override
            public void update(double delta) {
                System.out.println(engine.getFPS());
                rotate += (float) (delta * 0.15);
            }

            @Override
            public void render(GL2 gl2, int width, int height) {
                gl2.glClear( GL.GL_COLOR_BUFFER_BIT );
                //рисуем треугольник, заполняющий окно
                gl2.glLoadIdentity();
                //gl2.glRotatef(rotate,0f,height /2,0);
                gl2.glBegin( GL2.GL_QUADS );
                gl2.glColor3f( 1, 0, 0 );
                gl2.glVertex2f( 0, 0 );
                gl2.glColor3f( 0, 1, 0 );
                gl2.glVertex2f( width, 0 );
                gl2.glColor3f( 0, 0, 1 );
                gl2.glVertex2f( width / 2, height );
                gl2.glColor3f( 0, 0, 1 );
                gl2.glVertex2f( width / 2, height );
                gl2.glEnd();
            }
        });


    }
}