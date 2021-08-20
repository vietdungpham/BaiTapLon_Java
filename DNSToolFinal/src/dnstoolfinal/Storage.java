/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dnstoolfinal;

import java.text.DecimalFormat;

/**
 *
 * @author longy
 */
public class Storage {
    private double r,anpla;
    private float h,w,p;
    private int t,t0;
    private DecimalFormat df = new DecimalFormat("0.00");

    public Storage() {
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getAnpla() {
        return anpla;
    }

    public void setAnpla(double anpla) {
        this.anpla = Math.toRadians(anpla);
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    public float getP() {
        return p;
    }

    public void setP(float p) {
        this.p = p;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public int getT0() {
        return t0;
    }

    public void setT0(int t0) {
        this.t0 = t0;
    }

    public int getN0()
    {
        double S = h*w;
        double n0 = ((Math.log(1-p))/((Math.log(S - anpla*Math.pow(r, 2.0)))-(Math.log(S))));
        return ((int) Math.round(n0));
    }
    
    public int getGroup()
    {
        if(t%t0==0) return (int)(t/t0);
        else return (int)((t/t0)+1);
    }
    
    public int getN()
    {
        return this.getGroup()*this.getN0();
    }
    @Override
    public String toString() {
        return "Setting:\n"+
                "------------------------------------\n"+
                "|  Area: " + h*w +"m2\n"+
                "|  p: " + p + "\n"+
                "|  T: " + t + "s\n"+
                "|  n-Group: " + getGroup() + "\n"+
                "|  N0: " + getN0() + "\n"+
                "|  r: " + r +"m\n"+
                "|  anpla:" + df.format(anpla) + "(radian)\n"+
                "|  R: " + r*2 + "m\n"+
                "|  T0: " + t0 + "s\n"+
                "------------------------------------";
    }
    
   
}
