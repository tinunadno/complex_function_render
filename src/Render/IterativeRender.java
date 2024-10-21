package Render;

import FunctionExecution.Complex;
import FunctionExecution.ConstantManager;
import FunctionExecution.ParsingTree;

import java.awt.image.BufferedImage;
import java.text.ParseException;

public class IterativeRender implements RenderEngine{
    private ParsingTree function;
    private int iterationCount=10;
    private double range=2;
    private double scale=2;
    private Complex displacement=new Complex(0,0);

    @Override
    public void render(BufferedImage image){
        double screenRatio=image.getWidth()/ (double)image.getHeight();
        for(int i=0;i< image.getHeight();i++){
            for(int j=0;j<image.getWidth();j++){

                Complex c=new Complex((j/(double)image.getWidth()-0.5)*scale*screenRatio, (i/(double)image.getHeight()-0.5)*scale);
                c=c.add(displacement);
                ConstantManager.addConstant("c", c);
                Complex z=c;
                for(int k=0;k<iterationCount;k++){
                    ConstantManager.addConstant("z", z);
                    //z=f(c, z);
                    z= function.compile();
                    if(z.module()>range)break;
                }
                if(z.module()<=range)image.setRGB(j, i, 255);
                else image.setRGB(j, i, 0);

            }
        }
        //System.out.println("done");
    }

    public void setFunction(ParsingTree function){
        System.out.println(function);
        this.function=function;
    }

    public void setIterationCount(int iterationCount){
        System.out.println(iterationCount);
        this.iterationCount=iterationCount;
    }

    public void setRange(int range){
        System.out.println(range);
        this.range=range;
    }
    @Override
    public void setDisplacement(Complex displacement){

        this.displacement=this.displacement.add(displacement.mult(scale));
    }
    @Override
    public Complex getDisplacement(){return displacement;}

    public void addScale(int value){

        if(value<0)scale*=2.0;
        else scale/=2.0;

        System.out.println(scale);
    }
}
