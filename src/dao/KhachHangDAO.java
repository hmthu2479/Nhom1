package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;

import entity.KhachHang;

import javax.swing.*;


public class KhachHangDAO {
    public ArrayList <KhachHang> layThongTin(){
        ArrayList <KhachHang> dsKhachHang = new ArrayList<KhachHang>();
        try {
            /*
            Ket noi SQL
             */
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            /*
            Thuc thi cau lenh SQL
             */
            String SQL = "SELECT * FROM KhachHang";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()){
                String maKH = rs.getString(1);
                String tenKH = rs.getString(2);
                String soDT = rs.getString(3);
                String eMail = rs.getString(4);
                String diaChi = rs.getString(5);


               KhachHang kh = new KhachHang(maKH, tenKH, soDT, eMail, diaChi);

                dsKhachHang.add(kh);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dsKhachHang;
    }

	public boolean themKhachHang(KhachHang kh) {
		int n = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String SQL = "INSERT INTO KhachHang VALUES(?,?,?,?,?)";
		try {

			statement = con.prepareStatement(SQL);
			statement.setString(1, kh.getMaKH());
			statement.setString(2, kh.getTenKH());
			statement.setString(3, kh.getDiaChiKH());
			statement.setString(4, kh.geteMail());
			statement.setString(5, kh.getSoDT());
			n= statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}

	/*
    Cap nhat thong tin khach hang
     */
	public boolean capNhatKhachHang(KhachHang khachHang) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String SQL = "UPDATE KhachHang SET tenKH = ?, soDT = ?, eMail = ?, diaChi = ? WHERE maKH = ?";
			statement = con.prepareStatement(SQL);
			statement.setString(1, khachHang.getTenKH());
			statement.setString(2, khachHang.getDiaChiKH());
			statement.setString(3, khachHang.geteMail());
			statement.setString(4, khachHang.getSoDT());
			statement.setString(5, khachHang.getMaKH());
			n = statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	/*
	Xoa thong tin khach hang
	 */
	public boolean xoaKhachHang(String maKH) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String SQL = "DELETE FROM KhachHang WHERE maKH = ?";
			statement = con.prepareStatement(SQL);
			statement.setString(1, maKH);
			n = statement.executeUpdate();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Không thể xóa khách hàng này vì có thông tin trong hóa đơn");
		}
		return n > 0;
	}
	/*
	Kiem tra ma khach hang
	 */
	public boolean kiemTraMaKH(String maKH) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			String SQL = "SELECT * FROM KhachHang WHERE maKH = ?";
			statement = con.prepareStatement(SQL);
			statement.setString(1, maKH);
			rs = statement.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return false;
	}
	public KhachHang TimKhachHang(String id) {
		KhachHang kh = new KhachHang();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {

			String sql = "select * from KhachHang where maKH=?";
			statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {

				String maKH = rs.getString(1);
				String HoTenKH = rs.getString(2);
				String SDT = rs.getString(3);
				String  eMail = rs.getString(4);
				String DiaChi = rs.getString(5);

				kh = new KhachHang(maKH, HoTenKH, DiaChi,eMail, SDT);

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
		return kh;
	}



}