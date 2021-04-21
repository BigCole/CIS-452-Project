import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FindAccident {

	public Main m;

	public FindAccident(Main main) {
		m = main;
	}

	public String date;
	public String location;
	public ArrayList<String> involved = new ArrayList<String>();

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


	public void getByAccidentByID(int aid){
		String sql = "SELECT accident_date, city, state FROM accidents WHERE aid == ?";

		try (Connection conn = this.connect();
				PreparedStatement pstmt  = conn.prepareStatement(sql)){

			// set the value
			pstmt.setInt(1, aid);
			//
			ResultSet rs  = pstmt.executeQuery();

			// loop through the result set
			while (rs.next()) {	
				date = rs.getString("accident_date");
				location = rs.getString("city") + ", " + rs.getString("state");
			}
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void getByIvolvementByID(int aid){
		String sql = "SELECT vin, damages, driver_ssn FROM involvements WHERE aid == ?";

		try (Connection conn = this.connect();
				PreparedStatement pstmt  = conn.prepareStatement(sql)){

			// set the value
			pstmt.setInt(1, aid);
			//
			ResultSet rs  = pstmt.executeQuery();

			// loop through the result set
			while (rs.next()) {

				involved.add(rs.getString("vin").concat(":").concat(String.valueOf(rs.getInt("damages"))).concat(":").concat(rs.getString("driver_ssn")).concat(","));

			}
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}


}
