package org.uialert;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

public class Engine {
    private final GLCanvas glcanvas;
    private EventsEngine events;
    private LoopEngine loopEngine;
    private EngineListener engineListener = new EngineListener() {
        @Override
        public void update(double delta) {
            //System.out.println("Произошёл Update");
        }

        @Override
        public void render(GL2 gl2, int width, int height) {
            //System.out.println("Произошёл Render");
        }
    };


    public Engine() {

        GLProfile glprofile = GLProfile.get(GLProfile.GL2);
        GLCapabilities glcapabilities = new GLCapabilities(glprofile);
        glcanvas = new GLCanvas(glcapabilities);
        loopEngine = new LoopEngine(engineListener,glcanvas);
        glcanvas.addGLEventListener(new GLEventListener() {

            @Override
            public void reshape(GLAutoDrawable glautodrawable, int x, int y, int width, int height) {
                setup(glautodrawable.getGL().getGL2(), width, height);
                //System.out.println("Ddd");
            }

            @Override
            public void init(GLAutoDrawable glautodrawable) {
                //events.init(glautodrawable, glcanvas);
            }

            @Override
            public void dispose(GLAutoDrawable glautodrawable) {
            }

            @Override
            public void display(GLAutoDrawable glautodrawable) {
                engineListener.render(glautodrawable.getGL().getGL2(), glautodrawable.getSurfaceWidth(), glautodrawable.getSurfaceHeight());
            }
        });

        Thread thread = new Thread(loopEngine);
        thread.start();

    }

    private static void setup(GL2 gl2, int width, int height) {
        //настройка сетки отсчёта
        //если всё закоментировать то система координат будет начинться с середины экрана
        gl2.glMatrixMode(GL2.GL_PROJECTION);
        gl2.glLoadIdentity();

        // начало системы координат в левом нижнем углу с шириной и высотой, как и окно
        GLU glu = new GLU();
        glu.gluOrtho2D(0.0f, width, 0.0f, height);

        gl2.glMatrixMode(GL2.GL_MODELVIEW);
        gl2.glLoadIdentity();

        gl2.glViewport(0, 0, width, height);
    }

    public GLCanvas getCanvas() {
        return glcanvas;
    }

    public void setEvents(EventsEngine events) {
        this.events = events;
    }

    public void setEngineListener(EngineListener engineListener) {
        this.engineListener = engineListener;
        loopEngine.setEngineListener(engineListener);
    }

    public void dispose() {
    }

    public int getFPS(){
//        loopEngine.
        return loopEngine.fps;
    }

    interface EventsEngine {
        void init(GLAutoDrawable glautodrawable);
    }
}
