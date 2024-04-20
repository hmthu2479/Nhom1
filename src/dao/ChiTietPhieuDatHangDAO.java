package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietPhieuDatHang;
import entity.PhieuDatHang;
import entity.SanPham;

public class ChiTietPhieuDatHangDAO{
	public ArrayList<ChiTietPhieuDatHang> layThongTin() {
		// TODO Auto-generated method stub
		ArrayList<ChiTietPhieuDatHang> dsChiTietPhieu = new ArrayList<ChiTietPhieuDatHang>();
		try {
			/*
			 * Ket noi SQL
			 */
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			/*
			 * Thuc Thi Cau lenh SQL
			 */
			String SQL = "SELECT * FROM ChiTietPhieu";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(SQL);
			while (rs.next()) {
                SanPham sp = new SanPham(rs.getString(1));
                PhieuDatHang p = new PhieuDatHang(rs.getString(2));
                int soLuong = rs.getInt(3);
                ChiTietPhieuDatHang ct = new ChiTietPhieuDatHang(sp,p,soLuong);
                dsChiTietPhieu.add(ct);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsChiTietPhieu;
	}

	public boolean themDonHang(ChiTietPhieuDatHang ct) {
	    ConnectDB.getInstance();
	    Connection con = ConnectDB.getConnection();
	    PreparedStatement statement = null;
	    String SQL = "INSERT INTO ChiTietPhieu (maSP, maPhieu, soLuong) VALUES (?,?,?)";
	    int n = 0;
	    try {
	        statement = con.prepareStatement(SQL);
	        statement.setString(1, ct.getSanPham().getMaSanPham());
	        statement.setString(2, ct.getphieu().getmaPhieu());
	        statement.setInt(3, ct.getSoLuong());
	        n = statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (statement != null) {
	                statement.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return n > 0;
	}

		
	public ArrayList<ChiTietPhieuDatHang> TimPhieu(String id) {
		ArrayList<ChiTietPhieuDatHang> ds = new ArrayList<ChiTietPhieuDatHang>();

		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {

			String sql = "select * from ChiTietPhieu where maPhieu=?";
			statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {

				SanPham sp = new SanPham(rs.getString(1));
                PhieuDatHang p = new PhieuDatHang(rs.getString(2));
                int soLuong = rs.getInt(3);
                ChiTietPhieuDatHang ct = new ChiTietPhieuDatHang(sp,p,soLuong);
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
