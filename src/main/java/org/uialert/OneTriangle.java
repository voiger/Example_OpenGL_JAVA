package org.uialert;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;

public class OneTriangle {


    protected static void render(GL2 gl2, int width, int height) {
        gl2.glClear(GL.GL_COLOR_BUFFER_BIT);

        //рисуем треугольник, заполняющий окно
        gl2.glLoadIdentity();
        gl2.glBegin(GL2.GL_QUADS);
        gl2.glColor3f(1, 0, 0);
        gl2.glVertex2f(0, 0);
        gl2.glColor3f(0, 1, 0);
        gl2.glVertex2f(width, 0);
        gl2.glColor3f(0, 0, 1);
        gl2.glVertex2f(width, height);
        gl2.glColor3f(0, 0, 1);
        gl2.glVertex2f(0, height);
        gl2.glEnd();
    }
}
