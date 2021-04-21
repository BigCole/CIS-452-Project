import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchAccidents {

	public Main m;

	public SearchAccidents(Main main) {
		m = main;
	}
	
	public ArrayList<Integer> currentAIDs = new ArrayList<Integer>();
	
	public ArrayList<String> finalSet = new ArrayList<String>();
	
	public String date1 = "1998-01-01";
	public String date2 = "2020-12-31";
	public int avgDmg1 = 0;
	public int avgDmg2 = Integer.MAX_VALUE;
	public int totalDmg1 = 0;
	public int totalDmg2 = Integer.MAX_VALUE;
	
	public ArrayList<Integer> justAID = new ArrayList<Integer>();
	public HashMap<Integer, Integer> sharedAID = new HashMap<Integer, Integer>();
	public HashMap<Integer, Integer> totalDmg = new HashMap<Integer, Integer>();
	public HashMap<Integer, Integer> avgDmg = new HashMap<Integer, Integer>();

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

	
	public void getByAccidentByDateRange(){
		String sql = "SELECT aid FROM accidents WHERE accident_date between '" + date1 + "' AND '" + date2 + "'";

		try (Connection conn = this.connect();
				PreparedStatement pstmt  = conn.prepareStatement(sql)){

			
			ResultSet rs  = pstmt.executeQuery();

			// loop through the result set
			while (rs.next()) {	
				currentAIDs.add(rs.getInt("aid"));
			}
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void getByAccidentByAverageDamageRange(){
		String sql = "SELECT aid, damages, COUNT(aid) FROM involvements GROUP BY aid";

		try (Connection conn = this.connect();
				PreparedStatement pstmt  = conn.prepareStatement(sql)){

			
			ResultSet rs  = pstmt.executeQuery();

			//HashMap<Integer, Integer> dmg = new HashMap<Integer, Integer>();
			
			// loop through the result set
			while (rs.next()) {
				justAID.add(rs.getInt("aid"));
				sharedAID.put(rs.getInt("aid"), rs.getInt("COUNT(aid)"));
				//System.out.println(rs.getString("aid") + "\t" + rs.getInt("damages") + "\t" + rs.getInt("COUNT(aid)"));
			}
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void getByAccidentByAvg(){
		String sql = "SELECT aid, damages FROM involvements";
		try (Connection conn = this.connect();
				PreparedStatement pstmt  = conn.prepareStatement(sql)){

			
			ResultSet rs  = pstmt.executeQuery();

			// loop through the result set
			while (rs.next()) {	
				if(sharedAID.containsKey(rs.getInt("aid"))) {
					if(totalDmg.containsKey(rs.getInt("aid"))) {
						totalDmg.put(rs.getInt("aid"), totalDmg.get(rs.getInt("aid")) + rs.getInt("damages"));
					} else {
						totalDmg.put(rs.getInt("aid"), rs.getInt("damages"));
					}
				}
			}
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void getAvg() {
		for(int i = 0; i < sharedAID.size(); i++) {
			avgDmg.put(justAID.get(i), totalDmg.get(justAID.get(i))/sharedAID.get(justAID.get(i)));
			//System.out.println("AID: " + justAID.get(i) + "\t" + "Avg: " + totalDmg.get(justAID.get(i))/sharedAID.get(justAID.get(i)));
		
			if(currentAIDs.contains(justAID.get(i)) && (avgDmg1 > totalDmg.get(justAID.get(i))/sharedAID.get(justAID.get(i)) || avgDmg2 < totalDmg.get(justAID.get(i))/sharedAID.get(justAID.get(i)))) {
				currentAIDs.remove(currentAIDs.indexOf(justAID.get(i)));
			}
		
		}
	}
	
	public void getTotal() {
		for(int i = 0; i < justAID.size(); i++) {
			if(totalDmg.get(justAID.get(i)) < totalDmg1 || totalDmg.get(justAID.get(i)) > totalDmg2) {
				currentAIDs.remove(currentAIDs.indexOf(justAID.get(i)));
			}
		}
	}
	
	public void getFinalSet(int aid){
		String sql = "SELECT aid, accident_date, city, state FROM accidents where aid == ?";

		try (Connection conn = this.connect();
				PreparedStatement pstmt  = conn.prepareStatement(sql)){

			// set the value
			pstmt.setInt(1, aid);
			//
			ResultSet rs  = pstmt.executeQuery();

			// loop through the result set
			while (rs.next()) {	
				finalSet.add(String.valueOf(rs.getInt("aid")).concat(":").concat(rs.getString("accident_date")).concat(":").concat(rs.getString("city")).concat(", ").concat(rs.getString("state")));
			}
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
