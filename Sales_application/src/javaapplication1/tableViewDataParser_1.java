/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.sql.ResultSetMetaData;
import javax.swing.JFrame;

/**
 *
 * @author Nithaparan
 */
public class tableViewDataParser_1 {
    Object[] coloumnName,rowName;
    jdbcConnect connectDB;
    java.sql.PreparedStatement ps;
    java.sql.Statement stmt; 
    String SQL; 
    
    Object[][] tableData;
    
   public void dataParser(String sqlString){
        
       
        int coloumnCount=0;
        int rowCount=0;
            connectDB=new jdbcConnect();
            connectDB.connectDB();
            try{
            stmt=connectDB.con.createStatement();
            SQL=sqlString;
            connectDB.rs=stmt.executeQuery(SQL);
             ResultSetMetaData rsmd = connectDB.rs.getMetaData();
             coloumnCount=connectDB.rs.getMetaData().getColumnCount();
             
             
             
             coloumnName=new Object[coloumnCount];
             
           for(int i=0;i<coloumnCount;i++){
             coloumnName[i] = rsmd.getColumnName(i+1);
           }
            while(connectDB.rs.next()){
                 rowCount++;
             }
            connectDB.rs.beforeFirst();
            connectDB.rs.next();
             tableData=new Object[rowCount][coloumnCount];
             for(int a=0;a<rowCount;a++){
                 for(int b=0;b<coloumnCount;b++){
                     tableData[a][b]=(Object) connectDB.rs.getString((String)coloumnName[b]);
                     
                // System.out.println(connectDB.rs.getString((String)coloumnName[b]));
                 
                 }
                 connectDB.rs.next();
             }
            
            }
            catch(Exception e){
                e.printStackTrace();
             }
        
        detailsInTableView_1 ff=new detailsInTableView_1(coloumnName,rowCount,coloumnCount,tableData);
       
        ff.setSize(1210,650);
        ff.setLocationRelativeTo(null);
        //ff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ff.setVisible(true);
       
    }
    
}
