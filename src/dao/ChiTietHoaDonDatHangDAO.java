package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietHoaDonDatHang;
import entity.HoaDonDatHang;
import entity.SanPham;

public class ChiTietHoaDonDatHangDAO{
	public ArrayList<ChiTietHoaDonDatHang> layThongTin() {
		// TODO Auto-generated method stub
		ArrayList<ChiTietHoaDonDatHang> dsChiTietHoaDon = new ArrayList<ChiTietHoaDonDatHang>();
		try {
			/*
			 * Ket noi SQL
			 */
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			/*
			 * Thuc Thi Cau lenh SQL
			 */
			String SQL = "SELECT * FROM ChiTietHDDH";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(SQL);
			while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(1));
                HoaDonDatHang hd = new HoaDonDatHang(rs.getString(2));
                int soLuong = rs.getInt(3);
                ChiTietHoaDonDatHang ct = new ChiTietHoaDonDatHang(sp,hd,soLuong);
                dsChiTietHoaDon.add(ct);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsChiTietHoaDon;
	}

	public boolean themDonHang(ChiTietHoaDonDatHang ct) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String SQL = "INSERT INTO ChiTietHDDH VALUES (?,?,?)";
		int n = 0;
		try {
			statement = con.prepareStatement(SQL);
            statement.setString(1, ct.getSanPham().getMaSanPham());
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
		
	public ArrayList<ChiTietHoaDonDatHang> TimHoaDon(String id) {
		ArrayList<ChiTietHoaDonDatHang> ds = new ArrayList<ChiTietHoaDonDatHang>();

		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {

			String sql = "select * from ChiTietHDDH where maHoaDonDH=?";
			statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {

				SanPham sp = new SanPham(rs.getString(1));
                HoaDonDatHang hd = new HoaDonDatHang(rs.getString(2));
                int soLuong = rs.getInt(3);
                ChiTietHoaDonDatHang ct = new ChiTietHoaDonDatHang(sp,hd,soLuong);
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
