package controller;

import model.Person;
import view.MyForm;
import view.MyList;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MyEvent {
    MyForm myForm;
    MyDataBase myDataBase;

    public MyEvent(MyForm myForm){
        this.myForm = myForm;
    }

    public void actionPerformed(ActionEvent event) throws Exception {
        if(event.getSource() == myForm.list){
            new MyList();
        }else if(event.getSource() == myForm.add){
            myDataBase = new MyDataBase();
            if(myForm.r1.isSelected())
                myDataBase.add(new Person(0,myForm.tfName.getText(),"Homme"));
            else if(myForm.r2.isSelected())
                myDataBase.add(new Person(0,myForm.tfName.getText(),"Female"));
            else
                throw new Exception("Please selecte");
        }
    }

}
