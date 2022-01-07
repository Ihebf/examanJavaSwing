package view;

import controller.MyDataBase;
import controller.MyEvent;

import javax.swing.*;
import java.awt.*;

public class MyForm extends JFrame {

    MyEvent event;
    public JTextField tfName = new JTextField();
    public JRadioButton r1=new JRadioButton("Male");
    public JRadioButton r2=new JRadioButton("Female");
    public JButton list = new JButton("List");
    public JButton add = new JButton("Add");

    private JPanel northPanel(){
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel examen = new JLabel("Examen");
        northPanel.add(examen);

        return northPanel;
    }

    private JPanel centerPanel(){
        JPanel centerPanel = new JPanel(new GridLayout(2,2));
        JPanel rPanel = new JPanel(new GridLayout(1,2));

        JLabel name = new JLabel("Name: ");
        JLabel genre = new JLabel("Genre: ");


        ButtonGroup bg=new ButtonGroup();
        bg.add(r1);
        bg.add(r2);

        rPanel.add(r1);
        rPanel.add(r2);

        centerPanel.add(name);
        centerPanel.add(tfName);
        centerPanel.add(genre);
        centerPanel.add(rPanel);
        centerPanel.setBorder(BorderFactory.createTitledBorder("Informations"));

        return centerPanel;
    }

    private JPanel southPanel(){
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel panel = new JPanel(new GridLayout(1,2));

        list.addActionListener(e->{
            try {
                event.actionPerformed(e);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        add.addActionListener(e->{
            try {
                event.actionPerformed(e);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        panel.add(list);
        panel.add(add);

        southPanel.add(panel);

        return southPanel;
    }

    public MyForm(){
        event = new MyEvent(this);

        this.setTitle("Examen");
        this.setSize(500, 180);
        this.setLocationRelativeTo(getParent());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.add(northPanel(),BorderLayout.NORTH);
        this.add(centerPanel(),BorderLayout.CENTER);
        this.add(southPanel(),BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MyForm();
    }
}
