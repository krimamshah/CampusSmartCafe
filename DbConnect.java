
package testmain;

/*
 * File: DbConnect.java
 * -------------------------
 * This class connects the application to the database and fetches the data required for the application.
 * This class inserts, updates or deletes entries fromt he database.
 *
 * Key Methods:
 *     public Class getColumnClass(int column)
 *     public void setQuery(String query)
 *     public int getRowCount()
 *     public String getColumnName(int column)
 *     public Object getValueAt(int rowIndex, int columnIndex)
 *     
 *
 * Parameters:
 *     String Query
 *
 * Returns:
 *     Returns tht data from the select query as a AbstractTableModel object.
 *
 */

import java.sql.*;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
public  class DbConnect extends AbstractTableModel {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection con;
    private Statement st;
    private ResultSet rs;
    private ResultSetMetaData metaData;
    private int numberOfRows;
    private boolean connectedToDatabase = false;
    static final String DATABASE_URL = "jdbc:mysql://localhost:3307/test";
    static final String USERNAME = "root";
    static final String PASSWORD = "Kreemalav@777";
    
   // Constructor to establish connection with the database and set the query.
    public DbConnect(String query ) throws SQLException{
        try{
           Class.forName("com.mysql.jdbc.Driver");
            
            con = DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
            st = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
            
            connectedToDatabase = true;
            
            setQuery(query);
                    
            
        }catch(Exception ex){
            System.out.println("Error:"+ex);
        }
    }

    // Constructor to establish connection with the database.
    public DbConnect() {
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            con = DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
            st = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
            
            connectedToDatabase = true;
          }catch(Exception ex){
            System.out.println("Error:"+ex);
        }  
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // Fetches column class
    public Class getColumnClass(int column) throws IllegalStateException{
        
        if(!connectedToDatabase)
            throw new IllegalStateException("Not Connected to Database");
        try{
        String className = metaData.getColumnClassName(column+1);
        return Class.forName(className);
        }catch(Exception ex){
         ex.printStackTrace();
        }
        return Object.class;
    }
    
    //Sets query and obtains the resultSet
    public void setQuery(String query) throws SQLException,IllegalStateException {
        
       if(!connectedToDatabase)
           throw new IllegalStateException( "Not Connected to Database" );
        
        rs = st.executeQuery(query);
       metaData = rs.getMetaData();
        rs.last();
       numberOfRows = rs.getRow();
        
        //fireTableStructureChanged();
    }
    
    public void updateQuery(String query) throws SQLException,IllegalStateException {
        
        if(!connectedToDatabase)
            throw new IllegalStateException( "Not Connected to Database" );
         
         st.executeUpdate(query);
         //metaData = rs.getMetaData();
         //rs.last();
         //numberOfRows = rs.getRow();
         
         //fireTableStructureChanged();
     }
    
    
    public int getNumberOfRows(){
        return this.numberOfRows;
    }
    public ResultSet getRs(){
        return this.rs;    
    }
    
    public Statement getSt(){
        return this.st;   
    }
       
    public Connection getCon(){
        return this.con;
    }
    
    public void setRs(ResultSet rs){
        this.rs = rs;
    }
    

    // fetches the number of rows.
     @Override
    public int getRowCount() {//throw new UnsupportedOperationException("Not supported yet.");
         if(!connectedToDatabase)
           throw new IllegalStateException("Not connected to database ");
        return getNumberOfRows();

    }
    
    // fetches the column name.
    @Override 
    public String getColumnName(int column) throws IllegalStateException{
        if(!connectedToDatabase){
            throw new IllegalStateException("Not connected to database ");
        }
        try{
            return metaData.getColumnName(column+1);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return "";
    }

    // fetches the number of columns in the table.
    @Override
    public int getColumnCount() throws IllegalStateException {
        
        if(!connectedToDatabase)
            throw new IllegalStateException("Not connected to database "); //To change body of generated methods, choose Tools | Templates.
        try{
            return metaData.getColumnCount();
        } catch (SQLException ex) {
            //Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return 0;
    }

    //fetches the value of the particular row and column.
    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
         throws IllegalStateException
    {
        if(!connectedToDatabase)
            throw new IllegalStateException( "Not Connected to Database" );
        try{
            rs.absolute(rowIndex +1);
            return rs.getObject(columnIndex+1);
        }catch (SQLException ex) {
            //Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return "";
       
    }
    
    public void disconnectFromDatabase()
{
     if ( connectedToDatabase )
    {
// close Statement and Connection
       try
        {
           rs.close();
           st.close();
           con.close();
        } // end try
       catch ( SQLException sqlException )
        {
            sqlException.printStackTrace();
        } // end catch
         finally // update database connection status
        {
               connectedToDatabase = false;
        } // end finally
       } // end if
} // end method disconnectFromDatabase
        
    
   
  public String displayData() throws SQLException{
       String result = "";
      
          for(int i=0; i< this.getNumberOfRows();i++)
          {
              for(int j=0;j<this.getColumnCount();j++){
                    result  += getValueAt(i,j)+" ";
              
          }
              //result += "\n"; 
          
      }
      return result;
  }
  
  public DefaultTableModel displayMenuTable() throws SQLException{
         boolean temp = false;
          DefaultTableModel displayDataTable = new DefaultTableModel();
         // temp = getRs().next();
          ResultSetMetaData metaData = getRs().getMetaData();
             int columns = metaData.getColumnCount();
              String[]columnNames = new String[columns];
              for(int i=1;i<=columns;i++){
                  columnNames[i-1]= metaData.getColumnName(i);
                       
              }
              displayDataTable.setColumnIdentifiers(columnNames);
             // displayDataTable.addColumn("Select");
             //JCheckBox[] cafeCheckBox = new JCheckBox[getNumberOfRows()];
             //temp = getRs().next(); 
             //now populate the data
              int count = 1;
              while(getRs().next()){
            	 
              //for(int j=0;j<getNumberOfRows();j++){
            	  //if(getRs().next()){
                 String[] rowData = new String[columns];
            	  //columns = displayDataTable.getColumnCount();
            	 //Object[] rowData = new Object[columns];
                  for(int i=1;i<=columns;i++){
                	/* if(i==columns){
                		 
                	   rowData[i-1] = false;
                		  //rowData[i-1] = Boolean.class;
                		 // rowData[i-1] = cafeCheckBox[count].toString();
                		  //count++;
                		 //rowData[i-1] = getRs().getString(i);
                	  }
                	  else{*/
                          rowData[i-1] = getRs().getString(i);
                	 // }
                  }
                   displayDataTable.addRow(rowData);
            	  }
              
         
      return displayDataTable;
  }
}

