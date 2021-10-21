
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

public class Manager extends Person{
   boolean po=false,po1=false;
/****************************************************************************************************/  
    public Manager() {
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            con =DriverManager.getConnection("jdbc:ucanaccess://Library.accdb"); 
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());        }
    }
/***************************************************************************************************/ 
    public void addpublication(){
        try{
            po=false;po1=false;
            fram.setTitle("Add Publications");
            fram.setSize(500,300);
            fram.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            fram.setVisible(true);
            fram.setLocation(600,300);
            fram.setResizable(false);   
            fram.setLayout(new GridLayout(6,2)); 
            la1.setText("Publication Name");
            la2.setText("Publication Type");
            la3.setText("Number Of copies");
            la5.setText("Publication Author");
            rd1.setText("Book");
            rd2.setText("Magazine");
            rd3.setText("Booklet");
            bg.add(rd1);
            bg.add(rd2);
            bg.add(rd3);
            la4.setText("                  Error in your input "
                    + "\n         Hint : 0<The Number of copies<=30 ");
            la4.setVisible(false);
            la4.setForeground(Color.red);
            bt1.setText("Add");
            pan1.add(la1);
            pan1.add(tex1);
            pan2.add(la2);
            pan2.add(rd1);
            pan2.add(rd2);
            pan2.add(rd3);
            pan3.add(la3);
            pan3.add(tex3); 
            pan4.add(bt1);
            pan5.add(la5);
            pan5.add(tex5);
            fram.add(la4);fram.add(pan1);fram.add(pan2);fram.add(pan5);fram.add(pan3);fram.add(pan4);
            
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
                                if(checkstring(tex1.getText())==false||checkname(tex5.getText())==false||checknumber(tex3.getText())==false)
                                {
                                    la4.setVisible(true);
                                }
                                else
                                {
                                    com1=con.prepareStatement("select*from Publications where publications_Name='"+tex1.getText()+"' and publications_Type='"+type+"' and publications_Author ='"+tex5.getText()+"' ");
                                    rst1=com1.executeQuery();
                                    po=false;
                                    while(rst1.next())
                                    {
                                        JOptionPane.showMessageDialog(null,"This publication already exists"+"\nPublication Name ---> "+rst1.getString("publications_Name")+"\nPublication Type ---> "
                                        +rst1.getString("publications_Type")+"\npublications Author ---> "+rst1.getString("publications_Author")+"\nNumber Of Copies ---> "+rst1.getString("Number_Of_Copies"));
                                        po=true;
                                    }
                                    if(po==false)
                                    {
                                        com=con.prepareStatement("insert into Publications values ('"+tex1.getText()+"','"+type+"','"+tex5.getText()+"','"+tex3.getText()+"')");
                                        com.execute();
                                        cleartext();
                                        JOptionPane.showMessageDialog(null,"sucess"); 
                                        la4.setVisible(false);
                                      
                                    }
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
        po=false;po1=false;
    }
/**************************************************************************************************************************/      
    public void modifypublication(){
      try{
          po=false;po1=false;
          boolean check=false;
          String book="Book",magazine="Magazine",booklet="Booklet";
          String pname="",ptype="",pauthor="";
             pname= JOptionPane.showInputDialog(null,"Enter the name of the Publication you want to edit :");
             if(checkstring(pname)==true)
             {
                 ptype= JOptionPane.showInputDialog(null,"Enter the type of the Publication you want to edit (Book,Magazine,Booklet):");
                 if(book.equalsIgnoreCase(ptype)||magazine.equalsIgnoreCase(ptype)||booklet.equalsIgnoreCase(ptype))
                 {
                     com5=con.prepareStatement("select*from Publications where publications_Name='"+pname+"' and publications_Type='"+ptype+"' ");
                     rst5= com5.executeQuery();
                     boolean chtype=false;
                     while(rst5.next())
                     {
                        chtype=true;
                     }
                     if(chtype==true)
                     {
                     pauthor= JOptionPane.showInputDialog(null,"Enter the author of the Publication you want to edit :");
                     if(checkname(pauthor)==true)
                     {
                             check=true;
                     }
                     else
                     {
                         JOptionPane.showMessageDialog(null,"You must Enter correct Author Name");
                     }
                    }
                     else{
                         JOptionPane.showMessageDialog(null, "This publications dose not available");
                     } 
                 }
                 else
                 {
                     JOptionPane.showMessageDialog(null,"You must Enter correct Publication Type (Book,Magazine,Booklet)");
                 }
             }
             else
             {
                 JOptionPane.showMessageDialog(null,"You must Enter correct Publication name");
             }
             if(check==true)
             if(searchtomakeoperation(pname,ptype,pauthor)==true)
             {
                if(countnumberofcopies(pname,ptype,pauthor)==true)
                {
                    String pname1= JOptionPane.showInputDialog(null,"Enter the new edit of the name of the Publication  :");
                    if(checkstring(pname1)== true)
                    {
                        String ptype1= JOptionPane.showInputDialog(null,"Enter the new edit of the type of the Publication (Book,Magazine,Booklet):");
                        if(book.equalsIgnoreCase(ptype1)||magazine.equalsIgnoreCase(ptype1)||booklet.equalsIgnoreCase(ptype1))
                        {
                             String pauthor1= JOptionPane.showInputDialog(null,"Enter the new edit of the author of the Publication :");
                             if(checkname(pauthor1)==true)
                             {
                                    com2=con.prepareStatement("select*from Publications where publications_Name='"+pname1+"' and publications_Type='"+ptype1+"' and publications_Author ='"+pauthor1+"' ");
                                    rst2=com2.executeQuery();
                                    po=false;
                                    while(rst2.next())
                                    {
                                        JOptionPane.showMessageDialog(null,"This publication already exists"+"\nPublication Name ---> "+rst2.getString("publications_Name")+"\nPublication Type ---> "
                                        +rst2.getString("publications_Type")+"\npublications Author ---> "+rst2.getString("publications_Author")+"\nNumber Of Copies ---> "+rst2.getString("Number_Of_Copies"));
                                        po=true;
                                    }
                                    if(po==false)
                                    {
                                        String numofpublication= JOptionPane.showInputDialog(null,"Enter the new edit on the number of Copies:- 0<Number<=30:");
                                        if(checknumber(numofpublication)==true)
                                        {
                                            com1=con.prepareStatement("Update Publications set publications_Name='"+pname1+"' , publications_Type='"+ptype1+"' , publications_Author ='"+pauthor1+"',Number_Of_Copies='"+numofpublication+"' where publications_Name='"+pname+"' and publications_Type='"+ptype+"' and publications_Author ='"+pauthor+"'");
                                            com1.execute();
                                            JOptionPane.showMessageDialog(null,"sucess");
                                        }
                                        else
                                        {
                                            JOptionPane.showMessageDialog(null,"You must Enter Corrct Number of Copies 0<Number<=30");
                                        }
                                    }
                             }
                             else
                             {
                                 JOptionPane.showMessageDialog(null,"You must Enter correct Author Name");
                             }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"You must Enter correct Publication Type (Book,Magazine,Booklet)");
                        }
                   
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"You must Enter correct Publication name");
                    }
                    
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "You must wait until all publications return");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "This publications dose not available");
            }
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());  }
      po=false;po1=false;
    }    
/**************************************************************************************************************************/      
    public void deletepublication(){
      try{
          po=false;po1=false;
            String pname= JOptionPane.showInputDialog(null,"Enter the name of the Publication you want to delete :");
            if(checkstring(pname)== true)
            {
                String ptype= JOptionPane.showInputDialog(null,"Enter the type of the Publication you want to delete (Book,Magazine,Booklet):");
                String book="Book",magazine="Magazine",booklet="Booklet";
                if(book.equalsIgnoreCase(ptype)||magazine.equalsIgnoreCase(ptype)||booklet.equalsIgnoreCase(ptype))
                {
                    com5=con.prepareStatement("select*from Publications where publications_Name='"+pname+"' and publications_Type='"+ptype+"' ");
                     rst5= com5.executeQuery();
                     boolean chtype=false;
                     while(rst5.next())
                     {
                        chtype=true;
                     }
                     if(chtype==true)
                     {  
                    String pauthor= JOptionPane.showInputDialog(null,"Enter the author of the Publication you delete to edit :");
                    if(checkname(pauthor)==true)
                    {
                        if(searchtomakeoperation(pname,ptype,pauthor)==true)
                        {
                          if(countnumberofcopies(pname,ptype,pauthor)==true)
                          {
                              com=con.prepareStatement("delete from Publications where publications_Name='"+pname+"' and publications_Type='"+ptype+"' and publications_Author ='"+pauthor+"' ");
                              com.execute();
                              JOptionPane.showMessageDialog(null,"sucess");
                          }
                          else
                          {
                              JOptionPane.showMessageDialog(null, "You must wait until all publications return");
                          }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "This publications dose not available");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"You must Enter correct Author Name");
                    }
                }
                     else
                     {
                         JOptionPane.showMessageDialog(null, "This publications dose not available");
                     }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"You must Enter correct Publication Type (Book,Magazine,Booklet)");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"You must Enter correct Publication name");
            }
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());  }
      po=false;po1=false;
    } 
/****************************************************************************************************************************************************************************/
public void returnpublication(){
       po=false;po1=false;
       try {  
            String studentid= JOptionPane.showInputDialog(null,"Enter Student ID :");
            com=con.prepareStatement("select*from BorrowedPublications where Student_ID ='"+studentid+"' ");
            rst = com.executeQuery();
            String Redate="";
            boolean po3=false;
            while(rst.next()){
                po3=true;
            }
            if(po3==true)
            {
                com2=con.prepareStatement("select*from BorrowedPublications where Student_ID ='"+studentid+"' ");
                rst2 = com2.executeQuery();
                rst3 = com2.executeQuery();
                while(rst2.next()){
                Redate=rst2.getString("Return_Date");
               }
            long fine=curdate()-counttime(Redate);
            if(fine<0)
                fine*=0;
            else
                fine=20;
            while(rst3.next()){
                
                 JOptionPane.showMessageDialog(null,"Studen ID --> "+rst3.getString("Student_ID")+"\nStudent Name --> "+rst3.getString("Student_Name")+"\nPublications Type --> "+rst3.getString("Publications_Type" )+"\nPublications_Name --> "+rst3.getString("Publications_Name" )
                         +"\nPublications Author --> "+rst3.getString("Publications_Author")+"\nBorrow Date --> "+rst3.getString("Borrow_Date")+"\nReturn Date  --> "+rst3.getString("Return_Date")+"\nFine -->" +fine*2+" LE");
                 com4=con.prepareStatement("delete from OverBorrow where Student_ID='"+studentid+"'");
                 com4.execute();
                }
               com5=con.prepareStatement("select*from BorrowedPublications where Student_ID ='"+studentid+"' ");
               rst5=com5.executeQuery();
               
                String publicationname="",publicationtype="",publicationauthor="";
               while(rst5.next())
               {
                   publicationtype=rst5.getString("Publications_Type");
                   publicationname=rst5.getString("Publications_Name");
                   publicationauthor=rst5.getString("Publications_Author");
               }
                com6=con.prepareStatement("delete from BorrowedPublications where Student_ID ='"+studentid+"'");
                com6.execute();
                com7=con.prepareStatement("select*from Publications where publications_Name='"+publicationname+"' and publications_Type='"+publicationtype+"' and publications_Author ='"+publicationauthor+"'  ");
               rst7=com7.executeQuery();
               String numberofpublication="";
               while(rst7.next())
               {
                   numberofpublication=rst7.getString("Number_Of_Copies");
                   
                   
               }
               int nofpublication=Integer.parseInt(numberofpublication);
               
                nofpublication++;
                com8=con.prepareStatement("Update Publications set  Number_Of_Copies = '"+nofpublication+"' where publications_Name='"+publicationname+"' and publications_Type='"+publicationtype+"' and publications_Author ='"+publicationauthor+"'");
                com8.execute();
            }
            else 
            {
                JOptionPane.showMessageDialog(null,"This student does not exist");
                
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());        }
       po=false;po1=false;
    }    
/****************************************************************************************************************************************/   
    public void overborrow(){
        po=false;po1=false;
        try{
            
            com=con.prepareStatement("select*from BorrowedPublications");
            rst=com.executeQuery();
            String ReDate="";
            while(rst.next())
            {
                ReDate=rst.getString("Return_Date");
                if(curdate()-counttime(ReDate)>0)
                {
                    
                    com5=con.prepareStatement("select*from OverBorrow where Student_ID='"+rst.getString("Student_ID")+"'");
                    rst5=com5.executeQuery();
                    po=false;
                    while(rst5.next())
                    {
                        po=true;
                    }
                    if(po==false)
                    {
                        com1=con.prepareStatement("insert into OverBorrow values ('"+rst.getString("Student_ID")+"','"+rst.getString("Student_Name")+"','"+rst.getString("Publications_Type")+"','"+rst.getString("Publications_Name")+"','"+rst.getString("Publications_Author")+"','"+rst.getString("Borrow_Date")+"','"+rst.getString("Return_Date")+"','"+20+"')");
                        com1.execute();
                        ReDate="";
                    }
                    
                }
            }
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());        }
        po=false;po1=false;
    }
/*****************************************************************************************************************************/
    public void addStudent(){
        po=false;po1=false;
        try{
            String studentid= JOptionPane.showInputDialog(null,"Enter Student ID :");
            if(checkID(studentid)==false)
            {
                JOptionPane.showMessageDialog(null,"You must Enter Correct ID");
            }
            else
            {
                com=con.prepareStatement("select * from Student where Student_ID='"+studentid+"'");
                rst = com.executeQuery();
                po=false;
                while(rst.next()){
                        JOptionPane.showMessageDialog(null,"This student already exist"+"\nStudent ID --->"+rst.getString("Student_ID")+"\nStudent Name --->"+rst.getString("Student_Name"));
                        po=true;
                    }
                if(po==false)
                {
                    String sname= JOptionPane.showInputDialog(null,"Enter Student Name :");
                    if(checkname(sname)==false)
                    {
                        JOptionPane.showMessageDialog(null,"You must Enter Correct Name");
                    }
                    else
                    {            
                        com1=con.prepareStatement("insert into Student values ('"+studentid+"','"+sname+"')");
                        com1.execute();
                         JOptionPane.showMessageDialog(null,"sucess");
                    }
                }
            }
            
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());        }
        po=false;po1=false;
    }
 /******************************************************************************************************************************************/
    public void modifystudent(){
        po=false;po1=false;
        try{
            String studentid= JOptionPane.showInputDialog(null,"Enter Student ID you want to edit :");
            if(checkID(studentid)==false)
            {
                JOptionPane.showMessageDialog(null,"You must Enter Correct Student ID");
            }
            else
            {
                com=con.prepareStatement("select * from Student where Student_ID='"+studentid+"'");
                rst = com.executeQuery();
                po=false;
                while(rst.next()){
                        po=true;
                    }
                if(po==true)
                {
                    com1=con.prepareStatement("select*from BorrowedPublications where Student_ID ='"+studentid+"' ");
                    rst1=com1.executeQuery();
                    while(rst1.next())
                    {
                        po1=true;
                    }
                    if(po1==true)
                    {
                        JOptionPane.showMessageDialog(null,"You must wait for this student to return the publications he borrowed");
                    }
                    else
                    {
                        String studentid1= JOptionPane.showInputDialog(null,"Enter The New Student ID");
                     if(checkID(studentid1)==false)
                      {
                          JOptionPane.showMessageDialog(null,"You must Enter Correct Student ID");
                      }
                     else
                     {
                         String studentname= JOptionPane.showInputDialog(null,"Enter The New Student Name:");
                         if(checkname(studentname)==false)
                         {
                             JOptionPane.showMessageDialog(null,"You must Enter Correct Student Name");
                         }
                         else
                         {
                              com4=con.prepareStatement("Update Student set Student_ID = '"+studentid1+"' , Student_Name = '"+studentname+"' where Student_ID ='"+studentid+"'");
                              com4.execute();
                              JOptionPane.showMessageDialog(null,"sucess");
                         }
                     }
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"This Student dose not exist");
                }
            }
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());        }
        po=false;po1=false;
    }
/****************************************************************************************************************************************************************/
    public void deletestudent(){
        po=false;po1=false;
        try{
            String studentid= JOptionPane.showInputDialog(null,"Enter Student ID you want to edit :");
            if(checkID(studentid)==false)
            {
                JOptionPane.showMessageDialog(null,"You must Enter Correct Student ID");
            }
            else
            {
                com=con.prepareStatement("select * from Student where Student_ID='"+studentid+"'");
                rst = com.executeQuery();
                while(rst.next()){
                        po=true;
                    }
                if(po==true)
                {
                    com1=con.prepareStatement("select*from BorrowedPublications where Student_ID ='"+studentid+"' ");
                    rst1=com1.executeQuery();
                    while(rst1.next())
                    {
                        po1=true;
                    }
                    if(po1==true)
                    {
                        JOptionPane.showMessageDialog(null,"You must wait for this student to return the publications he borrowed");
                    }
                    else
                    {
                        com4=con.prepareStatement("delete from Student where Student_ID ='"+studentid+"'");
                        com4.execute();
                        JOptionPane.showMessageDialog(null,"sucess");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"This Student dose not exist");
                }
            }
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());        }
        po=false;po1=false;
    }
/**
     * @param Pname*
     * @param Type
     * @param Aname
     * @return ********************************************************************************************************************************************************************/
   @Override
    public boolean searchtomakeoperation(String Pname,String Type,String Aname){
        try{
            po=false;
            com=con.prepareStatement("select*from Publications where publications_Name = '"+Pname+"' and publications_Type='"+Type+"' and publications_Author='"+Aname+"'");
            rst=com.executeQuery();
            po=false;
            while(rst.next())
            {
                po=true;
            }
              if(po==true)
              {
                  return true;
              }
              
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());        }  
        return false;
        
    }   
/**
     * @param Pname
     * @param Type
     * @param Aname
     * @return *******************************************************************************************************************************************************************************/    
    public boolean countnumberofcopies(String Pname,String Type,String Aname){
        try{
            po=false;
            int count=0;
            if(true)
            {
                po=false;
                com1=con.prepareStatement("select*from BorrowedPublications where publications_Name = '"+Pname+"' and publications_Type='"+Type+"' and publications_Author='"+Aname+"' ");
                rst1=com1.executeQuery();
                while(rst1.next())
                {
                    count++;
                }
                if(count==0)
                    return true;
            }
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());        }  
        return false;
    }
/**
     * @param table***************************************************************************************************************************/    
    public void viewborrowpublication(JTable table){
       try{
          com=con.prepareStatement("select * from BorrowedPublications ");
          rst = com.executeQuery();
          table.setModel(DbUtils.resultSetToTableModel(rst));
      }
      catch(Exception e)
      {
          JOptionPane.showMessageDialog(null, e.getMessage());
          
      }
    }
/**
     * @param table***************************************************************************************************************************/    
    public void viewoverborrowpublication(JTable table){
       try{
          com=con.prepareStatement("select * from OverBorrow ");
          rst = com.executeQuery();
          table.setModel(DbUtils.resultSetToTableModel(rst));
      }
      catch(Exception e)
      {
          JOptionPane.showMessageDialog(null, e.getMessage());
          
      }
    }    
/**
     * @param table***************************************************************************************************************************/  
     public void viewstudent(JTable table){
         try{
               com=con.prepareStatement("select * from Student");
               rst = com.executeQuery();
               table.setModel(DbUtils.resultSetToTableModel(rst));
         }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());        }
         
    }    
/***********************************************************************************************************************************************************************/
    private void cleartext(){
        tex1.setText("");
        tex2.setText("");
        tex3.setText("");
        tex4.setText("");
        tex5.setText("");
        
    }   
}
