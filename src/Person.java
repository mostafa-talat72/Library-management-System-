import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import net.proteanit.sql.DbUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
public abstract class Person {
   Connection con;
   PreparedStatement com,com1,com2,com3,com4,com5,com6,com7,com8;
   ResultSet rst,rst1,rst2,rst3,rst4,rst5,rst6,rst7,rst8;
   SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy ");  
   java.util.Date date = new java.util.Date();  
   JFrame fram=new JFrame();
   JTextField tex1=new JTextField(30);
   JTextField tex2=new JTextField(30);
   JTextField tex3=new JTextField(30);
   JTextField tex4=new JTextField(30);
   JTextField tex5=new JTextField(30);
   JLabel la1=new JLabel();
   JLabel la2=new JLabel();
   JLabel la3=new JLabel();
   JLabel la4=new JLabel();
   JLabel la5=new JLabel();
   JButton bt1=new JButton();
   JRadioButton rd1=new JRadioButton();
   JRadioButton rd2=new JRadioButton();
   JRadioButton rd3=new JRadioButton();
   JRadioButton rd4=new JRadioButton();
   ButtonGroup bg=new  ButtonGroup();
   JPanel pan1=new JPanel();
   JPanel pan2=new JPanel();
   JPanel pan3=new JPanel();
   JPanel pan4=new JPanel();
   JPanel pan5=new JPanel();
   JPanel pan6=new JPanel();
   JPanel pan7=new JPanel();

  String type="";
/**********************************************************************************************/  
    public Person() {
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            con =DriverManager.getConnection("jdbc:ucanaccess://Library.accdb"); 
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());        }
    }
/***************************************************************************************************/ 
    public void searchpublication(){
        try{
            fram.setTitle("Search Publications");
            fram.setSize(400,300);
            fram.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            fram.setVisible(true);
            fram.setLocation(600,300);
            fram.setResizable(false);   
            fram.setLayout(new GridLayout(3,1)); 
            la1.setText("Publication Name");
            rd1.setText("Book");
            rd2.setText("Magazine");
            rd3.setText("Booklet");
            bg.add(rd1);
            bg.add(rd2);
            bg.add(rd3);
            bt1.setText("Search");
            pan1.add(la1);
            pan1.add(tex1);
            pan2.add(rd1);
            pan2.add(rd2);
            pan2.add(rd3);
            pan3.add(bt1);
            fram.add(pan1);fram.add(pan2);fram.add(pan3);
            
            ActionListener obj=new ActionListener(){
                        @Override
                    public void actionPerformed(ActionEvent a) {
                        if(a.getSource()==rd1)
                        {
                            type=rd1.getText();
                        }
                        else if(a.getSource()==rd2)
                        {
                            type=rd2.getText();
                        }
                        else if(a.getSource()==rd3)
                        {
                            type=rd3.getText();
                        }
                        if(a.getSource()==bt1)
                        {
                            try{
                                com=con.prepareStatement("select*from Publications where publications_Name = '"+tex1.getText()+"' and publications_Type='"+type+"'");
                                rst=com.executeQuery();
                                boolean po=false;
                                while(rst.next())
                                {
                                    JOptionPane.showMessageDialog(null, "Publication Name ---> "+rst.getString("publications_Name")+"\npublications Type ---> "+rst.getString("publications_Type")
                                    +"\npublications Author ---> "+rst.getString("publications_Author")+"\nNumber Of Copies ---> "+rst.getString("Number_Of_Copies"));
                                    po=true;
                                 }
                                if(po==false)
                                        {
                                            JOptionPane.showMessageDialog(null,"This Publiction does not available");
                                        }
                             }
                             catch (Exception ex) {
                             JOptionPane.showMessageDialog(null, ex.getMessage());        }
                        }
                      }
                    };
            
            bt1.addActionListener(obj);rd1.addActionListener(obj);rd2.addActionListener(obj);rd3.addActionListener(obj);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());        }
    }
    /**
     * @param table***************************************************************************************************************************/    
    public void viewpublication(JTable table){
       try{
          com=con.prepareStatement("select * from Publications ");
          rst = com.executeQuery();
          table.setModel(DbUtils.resultSetToTableModel(rst));
      }
      catch(Exception e)
      {
          JOptionPane.showMessageDialog(null, e.getMessage());
          
      }
    }
    /**
     * @param s*
     * @return ***********************************************************************************************************************/
    public boolean checkname(String s)
    {
        if(s.length()==0)
        {
            return false;
        }
        else
        {
            char ch=s.charAt(0);
            String ss="";
            ss+=ch;
            if(StringUtils.isAlpha(ss))
             {
                 boolean po=true;
                 char[] check=s.toCharArray();
                 for(int i=0;i<s.length();i++)
                 {
                     if(!((check[i]>='A'&&check[i]<='Z')||(check[i]>='a'&&check[i]<='z')||(check[i]==' ')))
                     {
                         po=false;
                         break;
                     }
                 }
                 if(po==true)
                     return true;
             }
        }
        return false;
    }
/**
     * @param s*
     * @return ***************************************************************************************************************************************/
     public boolean checkstring(String s)
    {
        if(s.length()==0)
        {
            return false;
        }
        else
        {
            char ch=s.charAt(0);
            String ss="";
            ss+=ch;
            if(StringUtils.isAlpha(ss)||NumberUtils.isNumber(ss) )
             {
                 return true;
             }
        }
        return false;
    }    
/**
     * @param s*  
     * @return ***************************************************************************************************************************************/  
    public boolean checknumber(String s)
    {
         if(s.length()==0)
        {
            return false;
        }
        else
        {
            
            if(NumberUtils.isNumber(s) )
             {
                 int n=Integer.parseInt(s);
                 if(n>0&&n<=30)
                     return true;
             }
        }
        return false;
        
    }
/**
     * @param s*
     * @return *********************************************************************************************************************************************/
  public boolean checkID(String s)
    {
         if(s.length()==0)
        {
            return false;
        }
        else
        {
            
            if(NumberUtils.isNumber(s) )
             {
                 int n=Integer.parseInt(s);
                 if(n>0)
                     return true;
             }
        }
        return false;
        
    }  
 /**
     * @return *********************************************************************************************************************************************/
     public long curdate()
     {
         String cudate=(String)formatter.format(date);
            char[] a1 = cudate.toCharArray();
           String dd="",mm="",yy="";
           
            for(int i=0;i<cudate.length();i++)
            {
                if(i<2)
                {
                    dd+=a1[i];
                }
                else if(i>2&&i<5)
                {
                    mm+=a1[i];
                }
                else if(i>5 && i<cudate.length() &&a1[i]>='0'&&a1[i]<='9')
                {
                     
                       yy+=a1[i];    
                }
            }
            int dd1=Integer.parseInt(dd);
            
            int mm1=Integer.parseInt(mm);
            
            int yy1 = Integer.parseInt(yy);
            long count = dd1+mm1*30+yy1*365;
            return count;
     }
/**
     * @param s
     * @return *****************************************************************************************************************************************************/
    public long counttime(String s){
        char[] a = s.toCharArray();
           String d="",m="",y="";
           
            for(int i=0;i<s.length();i++)
            {
                if(i<2)
                {
                    d+=a[i];
                }
                else if(i>2&&i<5)
                {
                    m+=a[i];
                }
                else if(i>5 && i<s.length() &&a[i]>='0'&&a[i]<='9')
                {
                     
                       y+=a[i];    
                }
            }
            int d1=Integer.parseInt(d);
            
            int m1=Integer.parseInt(m);
            
            int y1 = Integer.parseInt(y);
            long count = d1+m1*30+y1*365;
            return count;
    }     
/**************************************************************************************************************************************/
    public abstract boolean searchtomakeoperation(String Pname,String Type,String Aname);    
}
