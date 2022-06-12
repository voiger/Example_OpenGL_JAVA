package org.uialert;

import com.jogamp.opengl.GL2;

public interface EngineListener {
    void update(double delta);
    void render(GL2 gl2, int width, int height);
}
