package dao;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.PhieuDatHang;
import entity.KhachHang;
import entity.NhanVien;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class PhieuDatHangDAO {
    public ArrayList<PhieuDatHang> layThongTin(){
        ArrayList<PhieuDatHang> dsPhieu = new ArrayList<PhieuDatHang>();
        try{
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM PhieuDatHang";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                String maPhieu = rs.getString(1);
                KhachHang kh = new KhachHang(rs.getString(2));
                NhanVien nv = new NhanVien(rs.getString(3));
                Date ngayLap = rs.getDate(4);
                Date ngayNhanHang = rs.getDate(5);
                PhieuDatHang hd = new PhieuDatHang(maPhieu,kh,nv,ngayLap,ngayNhanHang);
                dsPhieu.add(hd);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dsPhieu;
    }
    // thêm hóa đơn
    public boolean themPhieu(PhieuDatHang hd){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String sql = "INSERT INTO PhieuDatHang VALUES (?,?,?,?,?)";
        int n = 0;
        PreparedStatement statement = null;
        try{
            statement = con.prepareStatement(sql);
            statement.setString(1,hd.getmaPhieu());
            statement.setString(2,hd.getKhachHang().getMaKH());
            statement.setString(3,hd.getNhanVien().getMaNhanVien());
            LocalDate ngayLapHD;
            ngayLapHD= hd.getngayLap().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            statement.setDate(4, java.sql.Date.valueOf(ngayLapHD));
            LocalDate ngayNhanHang = ngayLapHD.plusDays(2);
            statement.setDate(5, java.sql.Date.valueOf(ngayNhanHang));
            n = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return n>0;
    }


    public boolean xoaPhieu(String maPhieu) {
        Connection con = null;
        PreparedStatement deleteChiTietStatement = null;
        PreparedStatement deletePhieuStatement = null;
        int n = 0;
        try {
            con = ConnectDB.getInstance().getConnection();
            con.setAutoCommit(false); // Start transaction
            
            // First, delete related entries from ChiTietPhieuDatHang table
            String deleteChiTietSQL = "DELETE FROM ChiTietPhieu WHERE maPhieu = ?";
            deleteChiTietStatement = con.prepareStatement(deleteChiTietSQL);
            deleteChiTietStatement.setString(1, maPhieu);
            n = deleteChiTietStatement.executeUpdate();
            
            // Then, delete the PhieuDatHang entry
            String deletePhieuSQL = "DELETE FROM PhieuDatHang WHERE maPhieu = ?";
            deletePhieuStatement = con.prepareStatement(deletePhieuSQL);
            deletePhieuStatement.setString(1, maPhieu);
            n = deletePhieuStatement.executeUpdate();
            
            con.commit(); // Commit transaction
        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback(); // Rollback transaction if there's an error
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException(e);
        
        }
        return n > 0;
    }


    public boolean kiemTraMaPhieu(String idHD){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try{
            String sql = "SELECT * FROM PhieuDatHang WHERE maPhieu = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1,idHD);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public ArrayList<PhieuDatHang> timPhieu(String idHD){
        ArrayList<PhieuDatHang> dsPhieu = new ArrayList<PhieuDatHang>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try{
            String SQL = "SELECT * FROM PhieuDatHang WHERE maPhieu = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,idHD);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                String maPhieu = rs.getString(1);
                KhachHang kh = new KhachHang(rs.getString(2));
                NhanVien nv = new NhanVien(rs.getString(3));
                Date ngayLap = rs.getDate(4);
                Date ngayNhanHang = rs.getDate(5);
                PhieuDatHang hd = new PhieuDatHang(maPhieu,kh,nv,ngayLap,ngayNhanHang);
                dsPhieu.add(hd);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dsPhieu;
    }
}
