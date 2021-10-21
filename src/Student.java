
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

public class Student extends Person{
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy ");  
  private java.util.Date date = new java.util.Date();  
  private Calendar c = Calendar.getInstance();
  boolean po=false;
    /****************************************************************************************************************/  
    public Student() {
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            con =DriverManager.getConnection("jdbc:ucanaccess://Library.accdb"); 
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());        }
    }
/**************************************************************************************************************/    
     public void borrowpublication(){
       try {
           String studentid= JOptionPane.showInputDialog(null,"Enter student ID :");
               if(checkID(studentid)==false)
               {
                   JOptionPane.showMessageDialog(null,"You must Enter correct ID");
               }
               else
               {
                        com=con.prepareStatement("select * from Student where Student_ID='"+studentid+"'");
                        rst=com.executeQuery();
                        boolean chid=false;
                        String studentname="";
                        while(rst.next())
                        {
                            studentname=rst.getString("Student_Name");
                            chid=true;
                        }
                        if(chid==true)
                        {
                            com1=con.prepareStatement("select * from BorrowedPublications where Student_ID='"+studentid+"'");
                            rst1 = com1.executeQuery();
                            boolean st=false;
                            while(rst1.next())
                            {
                                st=true;
                            }
                            if(st==true)
                            {
                                JOptionPane.showMessageDialog(null, "You must return the book that is with you in order to borrow another book"); 
                            }
                            else
                            {
                                
                                
                                String pname= JOptionPane.showInputDialog(null,"Enter the name of the Publication you want:");
                                if(checkstring(pname)== true)
                                {
                                    String ptype= JOptionPane.showInputDialog(null,"Enter the type of the Publication you want (Book,Magazine,Booklet):");
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
                                        String pauthor= JOptionPane.showInputDialog(null,"Enter the author of the Publication you want :");
                                        if(checkname(pauthor)==true)
                                        {
                                            if(searchtomakeoperation(pname,ptype,pauthor)==true)
                                            {
                                              int count=countnumberofcopies(pname,ptype,pauthor);
                                              if(count>0)
                                              {   
                                                  c.setTime(date);
                                                  if(book.equalsIgnoreCase(ptype))
                                                  c.add(Calendar.DATE, 20); 
                                                  else if(magazine.equalsIgnoreCase(ptype)||booklet.equalsIgnoreCase(ptype))
                                                  c.add(Calendar.DATE, 12); 
                                                  Date currentDatePlus = c.getTime();
                                                  com8=con.prepareStatement("insert into BorrowedPublications values ('"+studentid+"','"+studentname+"','"+ptype+"','"+pname+"','"+pauthor+"','"+formatter.format(date)+"','"+formatter.format(currentDatePlus)+"')");
                                                  com8.execute();
                                                  com6=con.prepareStatement("select * from BorrowedPublications where Student_ID='"+studentid+"'");
                                                  rst6 = com6.executeQuery();
                                                  while(rst6.next()){
                                                  JOptionPane.showMessageDialog(null,"Studen ID --> "+rst6.getString("Student_ID")+"\nStuden Name --> "+rst6.getString("Student_Name")+"\nPublications Type --> "+rst6.getString("Publications_Type" )
                                                          +"\nPublications Name --> "+rst6.getString("Publications_Name" )+"\nPublications Author --> "+rst6.getString("Publications_Author" )+"\nBorrow Date --> "+rst6.getString("Borrow_Date")+"\nReturn Date  --> "+rst6.getString("Return_Date"));
                                                   }
                                                  com2=con.prepareStatement("select*from Publications where publications_Name='"+pname+"' and publications_Type='"+ptype+"' and publications_Author ='"+pauthor+"' ");
                                                  rst2= com2.executeQuery();
                                                  String numberofpublication="";
                                                  while(rst2.next()){
                                                           numberofpublication=rst2.getString("Number_Of_Copies");
                                                   }
                                                  int nofpublication=Integer.parseInt(numberofpublication);
                                                  nofpublication--;
                                                  com3=con.prepareStatement("Update Publications set  Number_Of_Copies = '"+nofpublication+"' where publications_Name='"+pname+"' and publications_Type='"+ptype+"' and publications_Author ='"+pauthor+"'");
                                                  com3.execute();
                                              }
                                              else
                                              {
                                                  JOptionPane.showMessageDialog(null,"This publication does not available");
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
                            }

                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"Thid Student dose not exist");
                        }
               }
    
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
/**
     * @param Pname
     * @param Type
     * @param Aname
     * @return *******************************************************************************************************************************************************************************/    
    public int countnumberofcopies(String Pname,String Type,String Aname){
        int count=0;
        try{
            po=false;
            if(true)
            {
                com=con.prepareStatement("select*from Publications where publications_Name = '"+Pname+"' and publications_Type='"+Type+"' and publications_Author='"+Aname+"' ");
                rst=com.executeQuery();
                while(rst.next())
                {
                    count++;
                }
            }
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());        }  
        return count;
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
}
