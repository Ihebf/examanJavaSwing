package thread;

import view.MyList;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
                int nbrPersonne = ml.myDataBase.getPersones();
                g = ml.cv.getGraphics();
                axesXY(nbrPersonne, "Nbr", "Genre");
                histogramme(nbrPersonne);
                ml.myDataBase.fillTab(ml.tableModel);
                sleep(10000);
                g.clearRect(0, 0, ml.cv.getWidth(), ml.cv.getHeight());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }



    public void histogramme(int nbrPersonnes){
        Dimension d = ml.cv.getSize();

        // rectangle des homme
        int nbrHomme = ml.myDataBase.getPersones("Homme");
        int nbrFemale = ml.myDataBase.getPersones("Female");;
        g.setColor(Color.BLUE);
        g.fillRect(150,(d.height/2)+100-nbrHomme,100,nbrHomme);
        // rectangle des female
        g.setColor(Color.PINK);
        g.fillRect(400,(d.height/2)+100-nbrFemale,100,nbrFemale);
    }

    public void axesXY(int nbrPersonnes, String labelX, String labelY){
        Dimension d = ml.cv.getSize();

        // Courbe
        g.setColor(Color.RED);
        g.drawString(labelX, 25, 20);
        g.drawString(labelY, d.width-60, (d.height/2)+120);
        g.setColor(Color.GREEN);
        g.drawLine(50,(d.height/2)+100,d.width-50,(d.height/2)+100);
        g.drawLine(50,(d.height/2)+100,50,20);
        // nbr Personne
        g.setColor(Color.YELLOW);
        g.drawLine(25, (d.height/2)+100 - nbrPersonnes, 75, (d.height/2)+100 - nbrPersonnes);
        g.drawString(nbrPersonnes+"", 80, (d.height/2)+100 - nbrPersonnes);
    }
}
