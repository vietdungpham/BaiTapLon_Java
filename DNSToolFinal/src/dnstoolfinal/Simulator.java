/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dnstoolfinal;

import java.util.HashMap;
import javax.swing.JFrame;

/**
 *
 * @author longy
 */
public class Simulator extends JFrame{

    public Simulator (int n, int group, int w, int h, int r, HashMap<Integer,Sensor> sHashMap)
    {
        initUI(n,group,w,h,r,sHashMap);
    }
    public void initUI(int n, int group, int w, int h,int r,HashMap<Integer,Sensor> sHashMap){
        SensorNetwork sensorNetwork1 =  new SensorNetwork(n, group, sHashMap);
        add(sensorNetwork1);
        setTitle("Sensor Network Demo");
        setSize(w+r,h+r);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
    }
}
