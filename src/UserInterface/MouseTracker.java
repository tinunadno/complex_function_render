package UserInterface;

import FunctionExecution.Complex;
import Render.RenderEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class MouseTracker implements Runnable{
    private RenderEngine engine;
    private int pressedButton;
    private BufferedImage trackingScreen;
    private JLabel updatingLabel;
    public MouseTracker(RenderEngine engine, BufferedImage trackingScreen, JLabel updatingLabel){
        this.engine=engine;
        pressedButton=-1;
        this.trackingScreen=trackingScreen;
        this.updatingLabel=updatingLabel;
    }
    public void setPressedButton(int pressedButton){
        this.pressedButton=pressedButton;
    }
    @Override
    public void run(){
        //tracking
        PointerInfo a=MouseInfo.getPointerInfo();
        Point previousPoint=a.getLocation();
        float screenRatio=trackingScreen.getWidth()/(float)trackingScreen.getHeight();
        while(pressedButton!=-1){
            a=MouseInfo.getPointerInfo();
            Point currentPoint=a.getLocation();
            double xDifference=(currentPoint.x- previousPoint.x)/(double)trackingScreen.getWidth()*(-1)*screenRatio;
            double yDifference=(currentPoint.y- previousPoint.y)/(double)trackingScreen.getHeight()*(-1);
            engine.setDisplacement(new Complex(xDifference, yDifference));

            engine.render(trackingScreen);

            updatingLabel.repaint();

            previousPoint=a.getLocation();
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){}
        }
    }
}
