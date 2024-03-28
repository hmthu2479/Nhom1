package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import connectDB.ConnectDB;
import entity.DanhMucLinhKien;
import javax.swing.*;

public class DanhMucDAO {
	public ArrayList<DanhMucLinhKien> layThongTin() {
		// TODO Auto-generated method stub
		ArrayList<DanhMucLinhKien> dsDM = new ArrayList<DanhMucLinhKien>();
		try {
			/*
			 * Ket noi SQL
			 */
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			/*
			 * Thuc Thi Cau lenh SQL
			 */
			String SQL = "SELECT * FROM DanhMucLinhKien";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(SQL);
			while (rs.next()) {
				String maDM = rs.getString(1);
				String tenDM = rs.getString(2);

				DanhMucLinhKien dm = new DanhMucLinhKien(maDM, tenDM);
				dsDM.add(dm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsDM;
	}

	/*public DanhMucLinhKien getDanhMucTheoTen(String TenDanhMuc) {
		DanhMucLinhKien dm = null;
		ConnectDB.getInstance();
		Connection connection = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "Select * from DanhMucMucLinhKien where tenDanhMuc=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, TenDanhMuc);
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				String maDM = result.getString(1);
				String tenDM = result.getString(2);
				dm = new DanhMucLinhKien(maDM, tenDM);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dm;
	}*/

	public boolean themDanhMuc(DanhMucLinhKien dm) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String SQL = "INSERT INTO DanhMucLinhKien VALUES(?,?)";
		int n = 0;
		try {
			statement = con.prepareStatement(SQL);
			statement.setString(1, dm.getMaDanhMuc());
			statement.setString(2, dm.getTenDanhMuc());

			n = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	public boolean xoaDanhMuc(String maDanhMuc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("delete from DanhMucLinhKien where maDanhMuc=? ");
			statement.setString(1, maDanhMuc);
			n = statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Danh mục này đang có thông tin linh kiện. Không thể xóa");
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	public boolean capNhapDanhMuc(DanhMucLinhKien dm) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("UPDATE DanhMucLinhKien set tenDanhMuc=? WHERE maDanhMuc=?");
			statement.setString(2, dm.getMaDanhMuc());
			statement.setString(1, dm.getTenDanhMuc());
			n=statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return n > 0;
	}
	public boolean kiemTraMaDM(String id) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {

			String sql = "select * from DanhMucLinhKien where maDanhMuc=?";
			statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {

				return true;
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
