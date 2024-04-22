package dao;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.ChiTietPhieuDatHang;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatHang;
import entity.SanPham;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonDAO {
	public ArrayList<HoaDon> layThongTin(){
        ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
        try{
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM HoaDon";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                String maHoaDon = rs.getString(1);
                KhachHang kh = new KhachHang(rs.getString(2));
                NhanVien nv = new NhanVien(rs.getString(3));
                Date ngayLapHD = rs.getDate(4);
                HoaDon hd = new HoaDon(maHoaDon,kh,nv,ngayLapHD);
                dsHoaDon.add(hd);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dsHoaDon;
    }
    // thêm hóa đơn
	public boolean themHoaDon(HoaDon hd){
	    ConnectDB.getInstance();
	    Connection con = ConnectDB.getConnection();
	    String sql = "INSERT INTO HoaDon VALUES (?,?,?,?)";
	    int n = 0;
	    PreparedStatement statement = null;
	    try{
	        statement = con.prepareStatement(sql);
	        statement.setString(1, hd.getMaHoaDon());
	        statement.setString(2, hd.getKhachHang().getMaKH());
	        statement.setString(3, hd.getNhanVien().getMaNhanVien());
	        statement.setDate(4, new java.sql.Date(hd.getNgayLapHD().getTime())); 
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
            String sql = "SELECT * FROM HoaDon WHERE MaHoaDon = ?";
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
    public ArrayList<HoaDon> timHoaDon(String idHD){
        ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try{
            String SQL = "SELECT * FROM HoaDon WHERE MaHoaDon = ?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,idHD);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                String maHoaDon = rs.getString(1);
                KhachHang kh = new KhachHang(rs.getString(2));
                NhanVien nv = new NhanVien(rs.getString(3));
                Date ngayLapHD = rs.getDate(4);
                HoaDon hd = new HoaDon(maHoaDon,kh,nv,ngayLapHD);
                dsHoaDon.add(hd);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dsHoaDon;
    }

    public String taoMaHD(String maPhieu) {
        return "HD_" + maPhieu;
    }

    public boolean themHoaDonTuPhieu(ArrayList<PhieuDatHang> phieuDatHangList, List<ChiTietPhieuDatHang> chiTietPhieuList, String maHoaDon) {
        Connection con = null;
        ChiTietHoaDonDAO ctHoaDonDAO = new ChiTietHoaDonDAO(); 

        try {
            con = ConnectDB.getConnection();

            // Lấy thông tin từ danh sách phiếu đặt hàng
            PhieuDatHang phieuDatHang = phieuDatHangList.get(0); 
            KhachHang kh = phieuDatHang.getKhachHang();
            NhanVien nv = phieuDatHang.getNhanVien();
            Date ngayLapHD = phieuDatHang.getngayLap();
            HoaDon hoaDon = new HoaDon(maHoaDon, kh, nv, ngayLapHD);

            // Thêm hóa đơn
            if (!themHoaDon(hoaDon)) {
                return false;
            }

            // Thêm các chi tiết hóa đơn từ danh sách chi tiết phiếu
            for (ChiTietPhieuDatHang chiTiet : chiTietPhieuList) {
                SanPham sanPham = chiTiet.getSanPham();
                int soLuong = chiTiet.getSoLuong();
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(sanPham, hoaDon, soLuong);
                
                // Nếu thêm chi tiết hóa đơn thất bại, trả về false
                if (!ctHoaDonDAO.themCTHoaDon(chiTietHoaDon)) {
                    return false;
                }
            }
            // Trả về true nếu tất cả thành công
            return true;
        } finally {
        }
    }


}
