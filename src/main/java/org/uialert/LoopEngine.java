package org.uialert;

import com.jogamp.opengl.awt.GLCanvas;

public class LoopEngine implements Runnable{

//    public static long gameTime = 0;
//    protected long startTime = System.nanoTime();
    final int TARGET_FPS = (int) Config.FPS;
    final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

    EngineListener engineListener;
    GLCanvas glcanvas;

    public LoopEngine(EngineListener engineListener, GLCanvas glcanvas) {
        this.engineListener = engineListener;
        this.glcanvas = glcanvas;
    }

    public void setEngineListener(EngineListener engineListener) {
        this.engineListener = engineListener;
    }

    @Override
    public void run() {
        long lastLoopTime = System.nanoTime();

        long lastFpsTime = 0;

        while(true){
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            if(updateLength < OPTIMAL_TIME){
                continue;
            }
            lastLoopTime = now;
            double delta = updateLength / ((double)OPTIMAL_TIME);

            lastFpsTime += updateLength;
            if(lastFpsTime >= 1000000000){
                lastFpsTime = 0;
            }

            engineListener.update(delta);
            glcanvas.display();
        }
    }
}
