package thread;

import view.MyList;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.geom.Line2D;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MyThread extends Thread {
    MyList ml;
    Graphics g;

    public MyThread(MyList ml){
        this.ml = ml;
    }

    public void run(){
        while (true){

            try {

                ml.myDataBase.fillTab(ml.tableModel);
                int nbrPersonne = (ml.tableModel.getRowCount());////ml.myDataBase.getPersones();
                g = ml.cv.getGraphics();
                axesXY(nbrPersonne, "Nbr", "Genre");
                histogramme(nbrPersonne);

                sleep(500);
                g.clearRect(0, 0, ml.cv.getWidth(), ml.cv.getHeight());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }


    private void drawArrowHead(Graphics2D g2, Point tip, Point tail, Color color)
    {
        g2.setPaint(color);
        double dy = tip.y - tail.y;
        double dx = tip.x - tail.x;
        double barb = 20;
        double phi = Math.toRadians(20);
        double theta = Math.atan2(dy, dx);
        double x, y, rho = theta + phi;
        for(int j = 0; j < 2; j++)
        {
            x = tip.x - barb * Math.cos(rho);
            y = tip.y - barb * Math.sin(rho);
            g2.draw(new Line2D.Double(tip.x, tip.y, x, y));
            rho = theta - phi;
        }
    }
    public void histogramme(int nbrPersonnes){
        Dimension d = ml.cv.getSize();
        // rectangle des homme
        int nbrHomme = nbrH(this.ml.tableModel);//ml.myDataBase.getPersones("Homme");
        int nbrFemale = nbrPersonnes-nbrHomme;////ml.myDataBase.getPersones("Female");
        double prctHomme = ((double) nbrHomme/(double)nbrPersonnes)*100;
        double prctFemale = ((double)nbrFemale / (double)nbrPersonnes)*100;
        g.setColor(Color.BLUE);
        g.fillRect(150,(d.height/2)+100-nbrHomme,100,nbrHomme);
        g.fillRect(d.width-50,100,10,10);
        g.drawString("Male",d.width-100,100);
        // rectangle des female
        g.setColor(Color.PINK);
        g.fillRect(d.width-50,80,10,10);
        g.drawString("Female",d.width-100,80);
        g.fillRect(400,(d.height/2)+100-nbrFemale,100,nbrFemale);
        // pourcentage
        g.setColor(Color.RED);
        g.drawString(prctHomme+"",150,10);
        g.drawString(prctFemale+"",400,10);

    }

    int nbrH(DefaultTableModel model) {
        int taille=model.getRowCount();
        int nbr=0;
        for(int i=0; i<taille; i++) if(model.getValueAt(i, 2).equals("Homme")) nbr++;
        return nbr;
    }

    public void axesXY(int nbrPersonnes, String labelX, String labelY){
        Dimension d = ml.cv.getSize();

        // Courbe
        g.setColor(Color.RED);
        g.drawString(labelX, 25, 20);
        g.drawString(labelY, d.width-60, (d.height/2)+120);
        g.setColor(Color.GREEN);
        g.drawLine(50,(d.height/2)+100,d.width-50,(d.height/2)+100);
        drawArrowHead((Graphics2D) g,new Point(d.width-50,(d.height/2)+100),new Point(50,(d.height/2)+100),Color.GREEN);
        drawArrowHead((Graphics2D) g,new Point(50,20),new Point(50,(d.height/2)+100),Color.GREEN);
        g.drawLine(50,(d.height/2)+100,50,20);
        // nbr Personne
        g.setColor(Color.YELLOW);
        g.drawLine(25, (d.height/2)+100 - nbrPersonnes, 75, (d.height/2)+100 - nbrPersonnes);
        g.drawString(nbrPersonnes+"", 80, (d.height/2)+100 - nbrPersonnes);
    }
}
