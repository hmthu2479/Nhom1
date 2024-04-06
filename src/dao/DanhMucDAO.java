package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import connectDB.ConnectDB;
import entity.DanhMucSanPham;
import javax.swing.*;

public class DanhMucDAO {
	public ArrayList<DanhMucSanPham> layThongTin() {
		ArrayList<DanhMucSanPham> dsDM = new ArrayList<DanhMucSanPham>();
		try {

			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String SQL = "SELECT * FROM DanhMucSanPham";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(SQL);
			while (rs.next()) {
				String maDM = rs.getString(1);
				String tenDM = rs.getString(2);

				DanhMucSanPham dm = new DanhMucSanPham(maDM, tenDM);
				dsDM.add(dm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsDM;
	}

	/*public DanhMucSanPham getDanhMucTheoTen(String TenDanhMuc) {
		DanhMucSanPham dm = null;
		ConnectDB.getInstance();
		Connection connection = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "Select * from DanhMucMucSanPham where tenDanhMuc=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, TenDanhMuc);
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				String maDM = result.getString(1);
				String tenDM = result.getString(2);
				dm = new DanhMucSanPham(maDM, tenDM);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dm;
	}*/

	public boolean themDanhMuc(DanhMucSanPham dm) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String SQL = "INSERT INTO DanhMucSanPham VALUES(?,?)";
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
			statement = con.prepareStatement("delete from DanhMucSanPham where maDanhMuc=? ");
			statement.setString(1, maDanhMuc);
			n = statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Danh mục này đang có thông tin sản phẩm. Không thể xóa");
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

	public boolean capNhapDanhMuc(DanhMucSanPham dm) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("UPDATE DanhMucSanPham set tenDanhMuc=? WHERE maDanhMuc=?");
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

			String sql = "select * from DanhMucSanPham where maDanhMuc=?";
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
