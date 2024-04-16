package dao;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.HoaDonDatHang;
import entity.KhachHang;
import entity.NhanVien;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class HoaDonDatHangDAO {
    public ArrayList<HoaDonDatHang> layThongTin(){
        ArrayList<HoaDonDatHang> dsHoaDon = new ArrayList<HoaDonDatHang>();
        try{
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM HoaDonDatHang";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                String maHoaDon = rs.getString(1);
                KhachHang kh = new KhachHang(rs.getString(2));
                NhanVien nv = new NhanVien(rs.getString(3));
                Date ngayLapHD = rs.getDate(4);
                Date ngayNhanHang = rs.getDate(5);
                HoaDonDatHang hd = new HoaDonDatHang(maHoaDon,kh,nv,ngayLapHD,ngayNhanHang);
                dsHoaDon.add(hd);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dsHoaDon;
    }
    // thêm hóa đơn
    public boolean themHoaDon(HoaDonDatHang hd){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        String sql = "INSERT INTO HoaDonDatHang VALUES (?,?,?,?,?)";
        int n = 0;
        PreparedStatement statement = null;
        try{
            statement = con.prepareStatement(sql);
            statement.setString(1,hd.getMaHoaDon());
            statement.setString(2,hd.getKhachHang().getMaKH());
            statement.setString(3,hd.getNhanVien().getMaNhanVien());
            LocalDate ngayLapHD;
            ngayLapHD= hd.getNgayLapHD().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            statement.setDate(4, java.sql.Date.valueOf(ngayLapHD));
            LocalDate ngayNhanHang = ngayLapHD.plusDays(2);
            statement.setDate(5, java.sql.Date.valueOf(ngayNhanHang));
            n = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return n>0;
    }
    public boolean kiemTraMaHD(String idHD){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try{
            String sql = "SELECT * FROM HoaDonDatHang WHERE maHoaDonDH = ?";
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
    // tìm hóa đơn
    public ArrayList<HoaDonDatHang> timHoaDon(String idHD){
        ArrayList<HoaDonDatHang> dsHoaDon = new ArrayList<HoaDonDatHang>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try{
            String SQL = "SELECT * FROM HoaDonDatHang WHERE maHoaDonDH = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,idHD);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                String maHoaDon = rs.getString(1);
                KhachHang kh = new KhachHang(rs.getString(2));
                NhanVien nv = new NhanVien(rs.getString(3));
                Date ngayLapHD = rs.getDate(4);
                Date ngayNhanHang = rs.getDate(5);
                HoaDonDatHang hd = new HoaDonDatHang(maHoaDon,kh,nv,ngayLapHD,ngayNhanHang);
                dsHoaDon.add(hd);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dsHoaDon;
    }
}
