package dao;

import connectDB.ConnectDB;
import entity.NhanVien;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class NhanVienDAO {
    //lấy danh sách nhân viên
    public ArrayList<NhanVien> layThongTin(){
        ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
        try{
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String SQL = "SELECT * FROM NhanVien";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()){
                String maNV = rs.getString(1);
                String tenNV = rs.getString(2);
                Date ngaySinh = rs.getDate(3);
                String diaChi = rs.getString(4);
                String soDienThoai = rs.getString(5);
                String email = rs.getString(6);
                NhanVien nv = new NhanVien(maNV,tenNV,ngaySinh,diaChi,soDienThoai,email);
                dsNhanVien.add(nv);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dsNhanVien;
    }
    //thêm nhân viên
    public boolean themNhanVien(NhanVien nhanVien){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;

        String SQL = "INSERT INTO NhanVien VALUES (?,?,?,?,?,?)";
        int n = 0;
        try{
            statement = con.prepareStatement(SQL);
            statement.setString(1,nhanVien.getMaNhanVien());
            statement.setString(2,nhanVien.getHoTen());
            LocalDate localDate;
            localDate = nhanVien.getNgaySinh().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            statement.setDate(3, java.sql.Date.valueOf(localDate));
            statement.setString(4,nhanVien.getDiaChi());
            statement.setString(5,nhanVien.getSoDT());
            statement.setString(6,nhanVien.geteMail());
            n = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return n>0;
    }
    // Xóa nhân viên
    public boolean xoaNhanVien(String maNV){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        int n=0;
        try{
            String SQL = "DELETE FROM NhanVien WHERE maNhanVien = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,maNV);
            n = statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return n > 0;
    }
    //sửa nhân viên
    public boolean capNhatNhanVien(NhanVien nhanVien){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        int n = 0;
        try{
            String SQL = "UPDATE NhanVien SET hoTen = ?, ngaySinh = ?, diaChi = ?, soDT = ?, eMail = ? WHERE maNhanVien = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,nhanVien.getHoTen());
            LocalDate localDate;
            localDate = nhanVien.getNgaySinh().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            statement.setDate(2, java.sql.Date.valueOf(localDate));
            statement.setString(3,nhanVien.getDiaChi());
            statement.setString(4,nhanVien.getSoDT());
            statement.setString(5,nhanVien.geteMail());
            statement.setString(6,nhanVien.getMaNhanVien());
            n = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return n>0;
    }
    public NhanVien TimNhanVien(String id) {
		NhanVien nv = new NhanVien();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {

			String sql = "select * from NhanVien where maNhanVien=?";
			statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {

				String maNV = rs.getString(1);
				String HoTenNV = rs.getString(2);
				Date ngaySinh = rs.getDate(3);
				String DiaChi = rs.getString(4);
				String SDT = rs.getString(5);
                String email = rs.getString(6);

				nv = new NhanVien(maNV, HoTenNV, ngaySinh, DiaChi, SDT, email);
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
		return nv;
	}
    //kiểm tra mã nhân viên
    public boolean kiemTraMaNV(String maNV){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        ResultSet rs = null;
        try{
            String SQL = "SELECT * FROM NhanVien WHERE maNhanVien = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,maNV);
            rs = statement.executeQuery();
            if (rs.next()){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
