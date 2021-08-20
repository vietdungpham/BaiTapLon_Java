/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dnstoolfinal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

/**
 *
 * @author longy
 */
public class SensorNetwork extends JPanel implements ActionListener{

    private int n,group;
    private HashMap<Integer,Sensor> listSensor = null;

    public SensorNetwork(int n, int group, HashMap<Integer, Sensor> mapSensors) {
        this.n = n;
        this.group = group;
        this.listSensor = mapSensors;
    }
    
    //Thuật toán MaxConComp
    private ArrayList<Integer> MaxConComp(HashMap<Integer,Sensor> G,int key, Sensor x,Boolean[] visted)
    {
        visted[key] = true; // Gán visted[x] =  true tức là đã thăm.
        ArrayList<Integer> v = x.getListADJ(); // Lấy danh sách các cảm biến liền kề
        ArrayList<Integer> ans = new ArrayList(); // Danh sách các cảm biến đã thăm
        int k=0;
        ans.add(key);
        // Chạy vòng while kiếm tra từng con cảm biến liền kề
        while(k <= v.size()-1)
        {
            if(!visted[v.get(k)]) {
                //Nếu cảm biến chưa được thăm, thực hiện đệ quy với chính cảm biến k.
                ArrayList<Integer> arrayList = MaxConComp(G,v.get(k),G.get(v.get(k)), visted);
                for(int i = 0;i<arrayList.size();i++)
                {
                    ans.add(arrayList.get(i));
                }
            }
            //Tăng biến k thực hiện tiếp vòng lặp.
            k++;
        }
        return ans;
    }
    private void DrawMaxConComp(int key, Sensor x,Boolean[] visted,Graphics2D g2d,int i, Color color)
    {
        //Lấy danh sách các cảm biến kết nối.
        ArrayList<Integer> C = MaxConComp(listSensor, key, x, visted);
        //Vẽ từng cảm biến lên màn hình, và nối chúng lại.
            for(int j = 0;j<C.size();j++)
            {
                Sensor s = listSensor.get(C.get(j));
                g2d.drawOval(s.getX(), s.getY(), 4, 4); 
                g2d.drawLine(s.getX()+2, s.getY()+2, s.getDx(), s.getDy());
                if(i!=0)
                {
                    g2d.setColor(color);
                    g2d.fillOval(s.getX(), s.getY(), 4, 4);
                }
                if(j!=C.size()-1)
                {
                    Sensor s1 = listSensor.get(C.get(j+1));
                    g2d.drawLine(s.getX()+2, s.getY()+2, s1.getX()+2, s1.getY()+2);
                }
            }
    }

    private void MakeSensorBetween(Sensor sC, Sensor sG1,int i, Graphics2D g2d,Color color){
                int x0Min = 0;
                int x0Max = 0;
                if(sC.getX0()>sC.getDx0())
                {
                    x0Min = sC.getDx0()+1;
                    x0Max = sC.getX0()-1;
                }
                else{
                    x0Max = sC.getDx0()-1;
                    x0Min = sC.getX0()+1;
                }
                //Random x0 thuộc vào vecto D của sC
                int x0 = (int)(Math.random() * (x0Max - x0Min+ 1) + x0Min);
                int y0 = ((-(sC.getY0()-sC.getDy0())*(x0-sC.getX0()))/(sC.getDx0()-sC.getX0()))+sC.getY0();
                int x = (int) (x0-sC.getRadius()/2);
                int y = (int) (y0-sC.getRadius()/2);
                //Khoảng cách từ điểm mới cho đến G1
                double d = Math.sqrt(Math.pow( (x0-sG1.getX0()) ,2 )+ Math.pow( (y0-sG1.getY0()) ,2));
                float sinAnpla = (float) ((x0-sG1.getX0())/d);
                float cosAnpla = (float) (-(y0-sG1.getY0())/d) +1;
                Sensor s = new Sensor(x, y, sG1.getRadius(), sG1.getAngle(), sG1.getTimeLife(), cosAnpla, sinAnpla);
                //Nếu cảm biến mới tạo có thể kết nối với cảm biến trong G1, thực hiện vẽ
                //ngược lại sẽ thực hiện đệ quy cho tới khi kết nối được với cảm biến trong G1.
                if(s.checkConnect(sG1))
                {
                    g2d.drawOval(s.getX(), s.getY(), 4, 4); 
                    g2d.drawLine(s.getX()+2, s.getY()+2, s.getDx(), s.getDy());
                    g2d.drawLine(s.getX()+2, s.getY()+2, sC.getX()+2, sC.getY()+2);
                    if(i!=0)
                    {
                        g2d.setColor(color);
                        g2d.fillOval(s.getX(), s.getY(), 4, 4);
                    }
                }
                else
                {
                    g2d.drawOval(s.getX(), s.getY(), 4, 4); 
                    g2d.drawLine(s.getX()+2, s.getY()+2, s.getDx(), s.getDy());
                    g2d.drawLine(s.getX()+2, s.getY()+2, sC.getX()+2, sC.getY()+2);
                    g2d.drawLine(s.getX()+2, s.getY()+2, sG1.getX()+2, sG1.getY()+2);
                    if(i!=0)
                    {
                        g2d.setColor(color);
                        g2d.fillOval(s.getX(), s.getY(), 4, 4);
                    }
                    MakeSensorBetween(s, sG1, i, g2d, color);
                }
    }
    private void RepairNetwork(HashMap<Integer,Sensor> listSensor, ArrayList<Integer> G, int key,Boolean[] visted,Graphics2D g2d,int i, Color color){
        Sensor rootSensor = listSensor.get(key);
        //Vẽ các sensor đã kết nối
        DrawMaxConComp(key, rootSensor, visted, g2d, i, color);
        ArrayList<Integer> C = new ArrayList();
        ArrayList<Integer> G1 = new ArrayList();
        //Loại bỏ các sensor đc kết nối ra khỏi danh sách.
        for(int j = 0;j<G.size();j++)
        {
            if(visted[G.get(j)]){
                C.add(G.get(j));
            }else G1.add(G.get(j));
        }
//        System.out.println(C.isEmpty() + " " + G1.isEmpty());
//        System.out.println(C.toString() + "\n" +G1.toString());
        if(!G1.isEmpty())
        {
            //Tìm hai cảm biến có khoảng cách ngắn nhất.
            Sensor sG1 = listSensor.get(G1.get(0));
            Sensor sC = listSensor.get(C.get(0));
            double midD = sG1.dist(sC);//min distance
            int keysC = 0;
            for(int j = 1;j<C.size();j++){
                Sensor sTemp = listSensor.get(C.get(j));
                if(sG1.dist(sTemp)<midD) 
                {
                    midD = sG1.dist(sTemp);
                    keysC = j;
                } 
            }
            sC = listSensor.get(C.get(keysC));
            //Thả cảm biến  để kết nối hai cảm biến sC và sG1 với nhau.
            MakeSensorBetween(sC, sG1, i, g2d, color);
            System.out.println(G1.get(0)+ " " + C.get(keysC) + "\n");
            RepairNetwork(listSensor, G, G1.get(0), visted, g2d, i, color);
        }
    }
    private void doDrawing(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        int n0 = n/group;
        // Chia thành các nhóm để thực hiện vẽ với từng ký hiệu khác nhau.
        for(int i=0;i<group;i++)
        {
            Color color = new Color((int)(Math.random() * 0x1000000));
            //Tìm root = sensor có ADJList.size() lớn nhất của mỗi group.
            ArrayList<Integer> G = new ArrayList();
            int maxADJ = 0;
            int keyMaxADJ = 0;
            for(int j = n0*i+1;j<=n0*(i+1);j++)
            {
                Sensor s = listSensor.get(j);
                if(s.getListADJ().size()> maxADJ){
                    maxADJ = s.getListADJ().size();
                    keyMaxADJ = j; 
                }
                G.add(j);
            }
            Sensor rootSensor = listSensor.get(keyMaxADJ);
            //Tạo mảng visted
            Boolean[] visted = new Boolean[listSensor.size()+1];
            for(int j = 1;j<=listSensor.size();j++)
            {
                visted[j] = false;
            }
            // Thực hiện gọi hàm sửa chữa.
            RepairNetwork(listSensor, G, keyMaxADJ, visted, g2d, i, color);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        repaint();
    }
    
}
