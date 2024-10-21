package Render;

import FunctionExecution.Complex;

import java.awt.image.BufferedImage;

public interface RenderEngine {
    void setDisplacement(Complex displacement);
    Complex getDisplacement();
    void render(BufferedImage image);
}
