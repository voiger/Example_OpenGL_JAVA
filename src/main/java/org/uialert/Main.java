package org.uialert;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.awt.GLCanvas;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java.lang.Math.asin;
import static java.lang.Math.sin;
import static java.lang.StrictMath.cos;
import static java.lang.StrictMath.tan;

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
                System.out.println("FPS : " + engine.getFPS());
                rotate += (float) (delta * 0.15);
            }
            double angle = 0;
            @Override
            public void render(GL2 gl2, int width, int height) {
                gl2.glClear( GL.GL_COLOR_BUFFER_BIT );
                //рисуем треугольник, заполняющий окно
                gl2.glLoadIdentity();
                int numVertices = 200_000;
                double radius = 0.5;

                // clear the window
                gl2.glClear(GL2.GL_COLOR_BUFFER_BIT);

//        gl.glColor3f(0, 0, 0); //set pen color to black
                // approximate  a circle with a polygon
                gl2.glRotated(rotate,0,0.1f,0);
                gl2.glBegin(GL2.GL_POLYGON);
//        gl.glBegin(GL2.GL_TRIANGLE_FAN);
                {

                    double angleIncrement = 2 * Math.PI / numVertices;
                    for (int i = 0; i < numVertices; i++) {
                        angle += i * angleIncrement;
                        double x = radius * Math.cos(angle);
                        double y = radius * sin(angle);
                        gl2.glColor3f((float) 0, (float) cos((angle/numVertices)*i), (float)0);
                        gl2.glVertex2d(x, y);
                    }
                }
                gl2.glEnd();
            }
        });


    }
}