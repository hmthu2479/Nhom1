package dao;

import java.sql.*;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhaCungCapLinhKien;

import javax.swing.*;

public class NhaCungCapDAO {
	public ArrayList<NhaCungCapLinhKien> layThongTin() {
		ArrayList<NhaCungCapLinhKien> dsNCC = new ArrayList<NhaCungCapLinhKien>();
		try {
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getConnection();

		String sql = "select * from NhaCungCapLinhKien";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String maNCC = rs.getString(1);
				String TenNCC = rs.getString(2);
				String DiaChi = rs.getString(3);
				String soDienThoai = rs.getString(4);

				NhaCungCapLinhKien ncc = new NhaCungCapLinhKien(maNCC, TenNCC, DiaChi, soDienThoai);
				dsNCC.add(ncc);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNCC;
	}



	public boolean ThemNhaCungCap(NhaCungCapLinhKien ncc) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String SQL = "INSERT INTO NhaCungCapLinhKien VALUES(?,?,?,?)";
		int n = 0;
		try {
			statement = con.prepareStatement(SQL);
			statement.setString(1, ncc.getMaNhaCungCap());
			statement.setString(2, ncc.getTenNCC());
			statement.setString(3, ncc.getDiaCHi());
			statement.setString(4, ncc.getSoDienThoai());

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

	public boolean xoa(String maNCC) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement("delete from NhaCungCapLinhKien where MaNhaCungCap=? ");
			statement.setString(1, maNCC);
			n = statement.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Không thể xóa nhà cung cấp này vì có thông tin linh kiện");
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

	public boolean Sua(NhaCungCapLinhKien ncc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			statement = con.prepareStatement(
					"UPDATE NhaCungCapLinhKien set TenNCC=?,SoDienThoai=?,DiaChi=? WHERE MaNhaCungCap=?");
			statement.setString(4, ncc.getMaNhaCungCap());
			statement.setString(1, ncc.getTenNCC());
			statement.setString(2, ncc.getSoDienThoai());
			statement.setString(3, ncc.getDiaCHi());
			n = statement.executeUpdate();
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
	public boolean KiemTraMa(String id) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {

			String sql = "select * from NhaCungCapLinhKien where MaNhaCungCap=?";
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
