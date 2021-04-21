import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddAccident {

	public Main m;
	
	public AddAccident(Main main) {
		m = main;
	}
	
	private Connection connect() {

        // SQLite connection string
        String url = "jdbc:sqlite:C:\\Users\\Colew_000\\Desktop\\autosDB.sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
	
    public void insertAccident(int aid, String date, String city, String state) {
        String sql = "INSERT INTO accidents(aid,accident_date,city,state) VALUES(?,?,?,?)";
        
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	
        	
        	pstmt.setInt(1, aid);
            pstmt.setString(2, date);
            pstmt.setString(3, city);
            pstmt.setString(4, state);
            pstmt.executeUpdate();
            
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void insertInvolvement(int aid, String vin, int damages, String driver_ssn) {
        String sql = "INSERT INTO involvements(aid,vin,damages,driver_ssn) VALUES(?,?,?,?)";
        
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	
        	
        	pstmt.setInt(1, aid);
            pstmt.setString(2, vin);
            pstmt.setInt(3, damages);
            pstmt.setString(4, driver_ssn);
            pstmt.executeUpdate();
            
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void rowCount(String table) throws Exception {
        String mysqlUrl = "jdbc:sqlite:C:\\Users\\Colew_000\\Desktop\\autosDB.sqlite";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "password");
        Statement stmt = con.createStatement();
        String query = "select count(*) from " + table;
        ResultSet rs = stmt.executeQuery(query);
        rs.next();
        rows = rs.getInt(1);
        con.close();
     }
    
    public int rows;
	
}
