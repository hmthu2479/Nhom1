package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.DonDatHang;
import entity.HoaDon;
import entity.LinhKien;

public class ChiTietHoaDonDAO{
	public ArrayList<ChiTietHoaDon> layThongTin() {
		// TODO Auto-generated method stub
		ArrayList<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
		try {
			/*
			 * Ket noi SQL
			 */
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			/*
			 * Thuc Thi Cau lenh SQL
			 */
			String SQL = "SELECT * FROM ChiTietHD";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(SQL);
			while (rs.next()) {
                LinhKien lk = new LinhKien(rs.getString(1));
                HoaDon hd = new HoaDon(rs.getString(2));
                int soLuong = rs.getInt(3);
                ChiTietHoaDon ct = new ChiTietHoaDon(lk,hd,soLuong);
                dsChiTietHoaDon.add(ct);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsChiTietHoaDon;
	}

	public boolean themDonHang(ChiTietHoaDon ct) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String SQL = "INSERT INTO ChiTietHD VALUES (?,?,?)";
		int n = 0;
		try {
			statement = con.prepareStatement(SQL);
            statement.setString(1, ct.getLinhKien().getMaLinhKien());
            statement.setString(2, ct.getHoaDon().getMaHoaDon());
            statement.setInt(3, ct.getSoLuong());
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
		public ArrayList<ChiTietHoaDon> TimHoaDon(String id) {
		ArrayList<ChiTietHoaDon> ds = new ArrayList<ChiTietHoaDon>();

		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {

			String sql = "select * from ChiTietHD where maHoaDon=?";
			statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {

				LinhKien lk = new LinhKien(rs.getString(1));
                HoaDon hd = new HoaDon(rs.getString(2));
                int soLuong = rs.getInt(3);
                ChiTietHoaDon ct = new ChiTietHoaDon(lk,hd,soLuong);
                ds.add(ct);

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
		return ds;
	}
}
