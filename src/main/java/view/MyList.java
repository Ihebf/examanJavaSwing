package view;

import controller.MyDataBase;
import controller.MyEvent;
import thread.MyThread;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MyList extends JFrame {

    public Graphics g;
    public Canvas cv = new Canvas();
    MyThread mt;
    public MyDataBase myDataBase;
    String col[] = {"Id","Name","Genre"};
    public DefaultTableModel tableModel = new DefaultTableModel(col, 0);
    public JTable table = new JTable(tableModel);
    public JScrollPane sp=new JScrollPane(table);

    private JPanel northPanel(){
        JPanel northPanel = new JPanel();

        northPanel.add(sp);
        northPanel.setBorder(BorderFactory.createTitledBorder("User list"));

        return northPanel;
    }

    public MyList() {
        myDataBase = new MyDataBase();

        this.setTitle("Examen");
        //this.setSize(800, 800);
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        this.setLocationRelativeTo(getParent());
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JPanel southPanel = new JPanel();

        cv.setSize(400, 400);
        cv.setBackground(Color.BLACK);
        southPanel.add(cv);
        mt = new MyThread(this);
        this.add(northPanel(),BorderLayout.NORTH);
        this.add(cv,BorderLayout.CENTER);
        this.setVisible(true);

        g = cv.getGraphics();
        mt.start();
    }

    public static void main(String[] args) throws InterruptedException {
        new MyList();
    }
}
