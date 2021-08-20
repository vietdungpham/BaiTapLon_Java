/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dnstoolfinal;

import java.util.ArrayList;
/**
 *
 * @author longy
 */
public class Sensor {
    private int x,y;
    private double radius;
    private float angle, timeLife,cosAnpla,sinAnlpa;
    private ArrayList<Integer> listADJ =  new ArrayList();
    public Sensor() {
    }

    public Sensor(int x, int y, double radius, float angle, float timeLife,float cosAnpla,float sinAnpla) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.angle = angle;
        this.timeLife = timeLife;
        this.cosAnpla = cosAnpla;
        this.sinAnlpa = sinAnpla;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getTimeLife() {
        return timeLife;
    }

    public void setTimeLife(float timeLife) {
        this.timeLife = timeLife;
    }

    public int getDx() {
        return (int) (x-8*sinAnlpa);
    }


    public int getDy() {
        return (int) (y+8*(cosAnpla)-1);
    }


    public int getX0() {
        return x+(int)(radius/2);
    }


    public int getY0() {
        return y+(int)(radius/2);
    }

    public void setCosAnpla(float cosAnpla) {
        this.cosAnpla = cosAnpla;
    }

    public void setSinAnlpa(float sinAnlpa) {
        this.sinAnlpa = sinAnlpa;
    }


    public int getDx0() {
        return (int) (getX0()-radius*sinAnlpa);
    }


    public int getDy0() {
        return (int) (getY0()+radius*(cosAnpla-1));
    }

    
    public boolean checkConnect(Sensor s)
    {
        double r = 2*this.radius;
        //Khoảng cách từ s1 đến s2
        double d =  Math.sqrt(Math.pow( (this.getX0()-s.getX0()) ,2 )+ Math.pow( (this.getY0()-s.getY0()) ,2));
        //Khoảng cách từ s2 tới D
        double d1 =  Math.sqrt(Math.pow( (this.getDx0()-s.getX0()) ,2 )+ Math.pow( (this.getDy0()-s.getY0()) ,2));
        double sD = d*r*((d*d + r*r - d1*d1)/(2*d*r));
        return d<=r && sD >= (d*Math.cos(angle));
    }
    public void addADJSensor(int key){
        listADJ.add(key);
    }
    public ArrayList getListADJ(){
        return listADJ;
    }
    public double dist(Sensor s){
        return Math.sqrt(Math.pow( (this.getX()-s.getX()) ,2 )+ Math.pow( (this.getY()-s.getY()) ,2));
    }
}
