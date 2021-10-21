import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login {
   JFrame fram=new JFrame();
   Student s=new Student();
   Manager m=new Manager();
   JButton ref=new JButton("Refresh");
   JButton bb=new JButton("Login Page");
   viewpublicationform vp=new viewpublicationform();
   viewborrowpublication vbp=new viewborrowpublication();
   viewoverborrow vbo=new viewoverborrow();
   viewstudent vs=new viewstudent();
   JButton bt1=new JButton();
   JButton bt2=new JButton();
   JButton bt3=new JButton();
   JButton bt4=new JButton();
   JButton bt5=new JButton();
   JButton bt6=new JButton();
   JButton bt7=new JButton();
   JButton bt8=new JButton();
   JButton bt9=new JButton();
   JPanel pan1=new JPanel();
   JPanel pan2=new JPanel();
   JPanel pan3=new JPanel();
   JPanel pan4=new JPanel();
   JPanel panlogin=new JPanel();
   
/************************************************************************************************************************************/                           
    public void start(){
         Login ll=new Login();
         JButton ss=new JButton("Start");
         fram.setTitle("Start Page");
         fram.setSize(300,100);
         fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         fram.setVisible(true);
         fram.setLocation(600,300);
         fram.setResizable(false);
         fram.setLayout(new FlowLayout());
         fram.add(ss);
         ss.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                   if(a.getSource()==ss)
                   {
                       fram.dispose();
                       ll.login();
                       
                   }
            }
         });
    }
/**************************************************************************************************************************/    
     public void login(){
                    Login ll=new Login();
                    JRadioButton rd1=new JRadioButton("Manager");
                    JRadioButton rd2=new JRadioButton("Student");
                    ButtonGroup bt=new  ButtonGroup();
                    bt.add(rd1);
                    bt.add(rd2);
                    fram.setTitle("Login Page");
                    fram.setSize(300,100);
                    fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    fram.setVisible(true);
                    fram.setLocation(600,300);
                    fram.setResizable(false);   
                    fram.setLayout(new FlowLayout());  
                    pan1.add(rd1);
                    pan2.add(rd2);
                    fram.add(pan1);
                    fram.add(pan2);
                    
                    ActionListener obj=new ActionListener(){
                        @Override
                    public void actionPerformed(ActionEvent a) {
                        if(a.getSource()==rd1)
                        {
                            fram.dispose();
                            ll.managerlogin();
                           
                        }
                        else if(a.getSource()==rd2)
                        {
                            fram.dispose();
                            ll.student();
                        }
                 
                       }
                    };
                   rd1.addActionListener(obj);
                   rd2.addActionListener(obj);
         
    }
 /**************************************************************************************************************************/    
     public void managerlogin(){
                    Login ll=new Login();
                    JFrame framp=new JFrame();
                    framp.setTitle("Manager Login Page");
                    framp.setSize(400,200);
                    framp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    framp.setLayout(new FlowLayout());
                    framp.setVisible(true);
                    framp.setLocation(600,300);
                    framp.setResizable(false); 
                    JPasswordField pass=new JPasswordField(30);
                    JTextField user =new JTextField(30);
                    JButton login=new JButton("Login");
                    JPanel panel1=new JPanel();
                    JPanel panel2=new JPanel();
                    JPanel panel3=new JPanel();
                    JLabel userl=new JLabel("UserName");
                    JLabel passl=new JLabel("Passward ");
                        JLabel correct=new JLabel("The username or password is incorrect");
                        framp.add(correct);
                        correct.setForeground(Color.red);
                        correct.setVisible(false);
                        bb.setBackground(Color.red);
                        bb.setForeground(Color.BLUE);
                    panel1.add(userl);
                    panel1.add(user);
                    panel2.add(passl);
                    panel2.add(pass);
                    panel3.add(login);
                    panel3.add(bb);
                    framp.add(panel1);
                    framp.add(panel2);
                    framp.add(panel3);
                    String passwardu1="012345",username1="talat",passwardu2="12345",username2="mokn3",passwardu3="123456",username3="Eng.Ahmed";
                    ActionListener objp=new ActionListener(){
                        @Override
                    public void actionPerformed(ActionEvent a) {
                           if(a.getSource()==login)
                           {
                               String password ="",use=user.getText();
                   char[] passw=pass.getPassword();
                   for(int i=0;i<passw.length;i++)
                   {
                       password+=passw[i];
                   }
                    if(password.equals(passwardu1)&&use.equals(username1)||password.equals(passwardu2)&&use.equals(username2)||password.equals(passwardu3)&&use.equals(username3))
                    {
                           framp.dispose();
                           ll.manager();
                    }
                    else
                    {
                        correct.setVisible(true);
                    }
                           }
                           else if(a.getSource()==bb)
                           {
                               framp.dispose()  ;
                               ll.login();
                           }
                       }
                    };
                    login.addActionListener(objp);
                    bb.addActionListener(objp);
    }
/**************************************************************************************************************************/         
     public void manager(){
                           Login ll =new Login();
                           fram.setTitle("Manager Page");
                           fram.setSize(500,400);
                           fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                           fram.setLayout(new GridLayout(5,1));
                           fram.setVisible(true);
                           fram.setLocation(500,200);
                           fram.setResizable(false); 
                           bb.setBackground(Color.red);
                           bb.setForeground(Color.BLUE);
                           bt1.setText("Add New Publications");
                           bt2.setText("Modify Publications");
                           bt3.setText("Delet Publications");
                           bt4.setText("Return Publications");
                           bt5.setText("View Publications");
                           bt6.setText("View Borrowed Publications");
                           bt7.setText("Search Publications");
                           bt8.setText("Students");
                           bt9.setText("Over-Period Borrowed Publications");
                           ref.setBackground(Color.GREEN);
                           ref.setForeground(Color.black);
                           pan1.add(bt1);pan1.add(bt2);pan1.add(bt3);pan2.add(bt4);pan2.add(bt7);pan3.add(bt6);pan3.add(bt5);pan3.add(bt8);pan4.add(bt9);
                           panlogin.add(bb);panlogin.add(ref);
                           fram.add(pan1);fram.add(pan2);fram.add(pan3);fram.add(pan4);fram.add(panlogin);
                           ActionListener obj=new ActionListener(){
                               @Override
                           public void actionPerformed(ActionEvent a) {
                               if(a.getSource()==bt1)
                               {
                                   m.addpublication();
                               }
                               else if(a.getSource()==bt2)
                               {
                                   m.modifypublication();
                               }
                               else if(a.getSource()==bt3)
                               {
                                   m.deletepublication();
                               }
                               else if(a.getSource()==bt4)
                               {
                                   m.returnpublication();
                               }
                               else if(a.getSource()==bt5)
                               {
                                   vp.setVisible(true);
                               }
                               else if(a.getSource()==bt6)
                               {
                                   vbp.setVisible(true);
                               }
                               else if(a.getSource()==bt7)
                               {
                                   m.searchpublication();
                               }
                               else if(a.getSource()==bt8)
                               {
                                   fram.dispose();
                                   ll.allstudent();
                               }
                               else if(a.getSource()==bt9)
                               {
                                   m.overborrow();
                                   vbo.setVisible(true);
                               }
                               else if(a.getSource()==bb)
                               {
                                   fram.dispose();
                                   ll.login();
                               }
                               else if(a.getSource()==ref)
                               {
                                   fram.dispose();
                                  ll.manager();
                               }
                           }
                    };
        bt1.addActionListener(obj);bt2.addActionListener(obj);bt3.addActionListener(obj);bt4.addActionListener(obj);bt5.addActionListener(obj);
        bt6.addActionListener(obj);bt7.addActionListener(obj);bt8.addActionListener(obj);bt9.addActionListener(obj);bb.addActionListener(obj);
        ref.addActionListener(obj);
     }
 /**************************************************************************************************************************/
     public void student(){
                    Login ll=new Login();
                    fram.setTitle("Student Page");
                    fram.setSize(500,200);
                    fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    fram.setVisible(true);
                    fram.setLocation(500,300);
                    fram.setResizable(false); 
                    fram.setLayout(new GridLayout(2,1));
                    bb.setBackground(Color.red);
                    bb.setForeground(Color.BLUE);
                    bt1.setText("View Publications");
                    bt2.setText("Borrowed Publications");
                    bt3.setText("Search Publications");
                    ref.setBackground(Color.GREEN);
                    ref.setForeground(Color.black);
                    pan1.setLayout(new FlowLayout());
                    pan1.add(bt1);
                    pan1.add(bt3);
                    pan1.add(bt2);
                    panlogin.add(bb);
                    panlogin.add(ref);
                    fram.add(pan1);
                    fram.add(panlogin);
                    ActionListener obj1=new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent a) {
                            if(a.getSource()==bt1)
                            {
                                vp.setVisible(true);
                            }
                            else if(a.getSource()==bt2)
                            {
                                s.borrowpublication();
                            }
                            else if(a.getSource()==bt3)
                            {
                                s.searchpublication();
                            }
                            else if(a.getSource()==bb)
                            {
                                fram.dispose();
                                ll.login();
                            }
                            else if(a.getSource()==ref)
                            {
                                fram.dispose();
                                ll.student();
                            }
                        }
                    };
                    bt1.addActionListener(obj1);bt2.addActionListener(obj1);bt3.addActionListener(obj1);bb.addActionListener(obj1);ref.addActionListener(obj1); 
    }
/*************************************************************************************************************************************/
   public void allstudent(){
                    viewstudent st=new viewstudent();
                    Login ll=new Login();
                    fram.setTitle("Student Operation Page");
                    fram.setSize(500,200);
                    fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    fram.setVisible(true);
                    fram.setLocation(500,300);
                    fram.setResizable(false); 
                    fram.setLayout(new GridLayout(2,1));
                    bt4.setBackground(Color.red);
                    bt4.setForeground(Color.BLUE);
                    ref.setBackground(Color.GREEN);
                    ref.setForeground(Color.black);
                    bt1.setText("Add New Student");
                    bt2.setText("View Students");
                    bt3.setText("Modify Student");
                    bt5.setText("Delete Student");
                    bt4.setText("Home Page");
                    pan1.add(bt1);pan1.add(bt3);pan1.add(bt5);pan1.add(bt2);panlogin.add(bt4);panlogin.add(ref);
                    fram.add(pan1);
                    fram.add(panlogin);
                    ActionListener obj1=new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent a) {
                            if(a.getSource()==bt1)
                            {
                                m.addStudent();
                            }
                            else if(a.getSource()==bt2)
                            {
                                st.setVisible(true);
                            }
                            else if(a.getSource()==bt3)
                            {
                                m.modifystudent();
                            }
                            else if(a.getSource()==bt5)
                            {
                                m.deletestudent();
                            }
                            else if(a.getSource()==ref)
                            {
                                fram.dispose();
                                ll.allstudent();
                            }
                            else if(a.getSource()==bt4)
                            {
                                fram.dispose();
                                ll.manager();
                            }
                        }
                    };
          bt1.addActionListener(obj1);bt3.addActionListener(obj1);bt5.addActionListener(obj1);bt2.addActionListener(obj1);bt4.addActionListener(obj1);ref.addActionListener(obj1);
    }
/*************************************************************************************************************************************/
}
