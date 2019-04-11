 import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.*;
import java.rmi.*;
import java.io.*;
import java.net.*;

import java.rmi.server.*;

public class Agenda extends JFrame implements ActionListener {

    private JButton Add, Delete, Consultar, Listar, Close;
    private JFrame frame;
    static Conection rmiObjeto;

    public Agenda() {
        super(" Agenda ");

Container contentPane = getContentPane();
JPanel panel = new JPanel();
JPanel panel1 = new JPanel();
contentPane.add(panel, BorderLayout.NORTH);
panel.setLayout(new FlowLayout(FlowLayout.CENTER));
contentPane.add(panel1, BorderLayout.CENTER);
panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
JLabel label1 = new JLabel("Simple Address Book");
        JLabel label2 = new JLabel ("By Vinicius Sipoli Reinert");
        panel.add(label1);
        panel1.add(label2);

        JPanel panel2 = new JPanel();
        contentPane.add(panel2, BorderLayout.SOUTH);

        Add = new JButton("Add");
        Add.setActionCommand("Add");
        panel2.add(Add);

        Delete = new JButton("Delete");
        Delete.setActionCommand("Delete");
        panel2.add(Delete);

        Consultar = new JButton("Search");
        Consultar.setActionCommand("consultar");
        panel2.add(Consultar);

        Listar = new JButton("List");
        Listar.setActionCommand("listar");
        panel2.add(Listar);

Close = new JButton("Close");
        Close.setActionCommand("Close");
        panel2.add(Close);

        Add.addActionListener(this);
        Delete.addActionListener(this);
        Consultar.addActionListener(this);
        Listar.addActionListener(this);
        Close.addActionListener(this);

        setSize(420,150);
        show();
    }//Agenda

    public void actionPerformed(ActionEvent ex)
    {
String nome,sobrenome,email,telefone,res;


try{


        if (ex.getActionCommand() == "Add")
        {
         	nome=JOptionPane.showInputDialog("nome: ");
         	sobrenome=JOptionPane.showInputDialog("sobrenome: ");
         	email=JOptionPane.showInputDialog("Email: ");
         	telefone=JOptionPane.showInputDialog("telefone: ");
rmiObjeto.Add(nome,sobrenome,email,telefone);
JOptionPane.showMessageDialog(frame,"Data Added!","Warning",JOptionPane.PLAIN_MESSAGE);
            System.out.println("add");
        }// Add


        if (ex.getActionCommand() == "Delete")
        {
           	nome =JOptionPane.showInputDialog("nome a Delete:");
           	sobrenome =JOptionPane.showInputDialog("Apellido a Delete:");
   	rmiObjeto.Delete(nome,sobrenome);
   	JOptionPane.showMessageDialog(frame,"Data Deleted!","Warning",JOptionPane.PLAIN_MESSAGE);
           System.out.println("del");
        }// Delete

        if (ex.getActionCommand() == "consultar")
        {
           nome =JOptionPane.showInputDialog("nome a Buscar:");
   res=rmiObjeto.consultar(nome);
   JOptionPane.showMessageDialog(null, res,"Consulta",JOptionPane.PLAIN_MESSAGE);
   System.out.println("Search");
        }//consultar


        if (ex.getActionCommand() == "listar")
        {
           res=rmiObjeto.listar();
           JOptionPane.showMessageDialog(null, res,"Listado",JOptionPane.PLAIN_MESSAGE);
           System.out.println("List");
        }//listar


        if (ex.getActionCommand() == "Close")
        {
           JOptionPane.showMessageDialog(frame,"GoodBye!!");
           System.out.println("Quit program");
           System.exit(0);

        }//Close
} catch (Exception e) {//catch (java.rmi.RemoteException e) {
   System.out.println("Exception of Client:" + e.getMessage());
   e.printStackTrace();
}  //catch

    }//action performed


    public static void main(String args[])
    {
        Agenda app = new Agenda();

       app.addWindowListener(new WindowAdapter(){
               public void windowClosing(WindowEvent e)
               {
                   System.exit(0);
               }//window closing
            }//window adapter
        );

     try{

    rmiObjeto = ((Conection) Naming.lookup("http://127.0.0.1:8080/agenda"));
    System.out.println("conected.");

}catch (Exception e) {
   System.out.println("Exception of Client:" + e.getMessage());
   e.printStackTrace();
}  //catch

     }//end main
}

