package corso.Sessioni.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {
	
	public Database(String percorso, String user, String pass) {
		apriConnection(percorso, user, pass);
	}
	
	private Connection conn;
	
	private void apriConnection(String percorso, String user, String pass) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(percorso, user, pass);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Classe Driver NON TROVATA, controlla il jar");
		} catch(SQLException e) {
			e.printStackTrace();
			System.err.println("Errore nella connessione al Database");
		}
		
	}
	
	public void chiudiConnection() {
		if(conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Errore nella chiusura della Connection");
			}
	}
	
	private void chiudiItems(PreparedStatement ps, ResultSet rs) {
		try {
			if(ps != null) ps.close();
			if(rs != null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<HashMap<String, Object>> eseguiQuery(String query, String... params) {
		
		ArrayList<HashMap<String, Object>> listaMappe = new ArrayList<HashMap<String, Object>>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(query);
			
			for(int i = 0; i < params.length; i++)
				ps.setString(i+1, params[i]);
			
			rs = ps.executeQuery();
			
			HashMap<String, Object> mappa = new HashMap<String, Object>();
			HashMap<String, Object> record;
			while(rs.next()) {
				record = (HashMap<String, Object>) mappa.clone();
				
				for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					record.put(rs.getMetaData().getColumnLabel(i), rs.getObject(i));
				}
				
				listaMappe.add(record);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			chiudiItems(ps, rs);
		}
		
		return listaMappe;
	}
	
	public boolean eseguiUpdate(String query, String... params) {
		
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(query);
			
			for(int i = 0; i < params.length; i++)
				ps.setString(i+1, params[i]);
			
			ps.executeUpdate();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			chiudiItems(ps, null);
		}
		
		return false;
	}

}
