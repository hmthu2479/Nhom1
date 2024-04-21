package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import entity.Ke;

public class KeDAO {
	public ArrayList<Ke> laythongtin(){
		ArrayList<Ke> dsK = new ArrayList<Ke>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from Ke";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String make = rs.getString(1);
				String tenke = rs.getString(2);
				String vt = rs.getString(3);
				
				Ke k = new Ke(make, tenke, vt);
				dsK.add(k);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dsK;
	}
	public boolean themKe(Ke k) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "insert into Ke values(?,?,?)";
		int n=0;
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, k.getMaKe());
			statement.setString(2, k.getTenKe());
			statement.setString(3, k.getVitri());
			
			n = statement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return n>0;
		
	}
	public boolean xoaKe(String maKe) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "Delete from Ke where maKe=?";
		int n=0;
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, maKe);
			n = statement.executeUpdate();
			statement.close();
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Kệ này đang có thông tin sản phẩm. Không thể xóa");
		}finally {
			try {
				statement.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return n>0;
		}
	public boolean capnhatKe(Ke k) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n =0;
		try {
			String sql = "Update Ke set tenKe =? , vitri=? where maKe=?";
			statement.setString(3, k.getMaKe());
			statement.setString(1, k.getTenKe());
			statement.setString(2, k.getVitri());
			
			n = statement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return n >0;
	}
	public boolean kiemtraKe(String maKe) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "select * from Ke where maKe = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, maKe);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
