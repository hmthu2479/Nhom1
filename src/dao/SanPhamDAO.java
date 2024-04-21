package dao;

import connectDB.ConnectDB;
import entity.*;

import javax.swing.*;
import java.lang.reflect.Array;
import java.sql.*;
import java.util.ArrayList;

public class SanPhamDAO {
    public ArrayList <SanPham> layThongTin(){
        ArrayList <SanPham> dsSanPham = new ArrayList<SanPham>();
        try {
            /*
            Ket noi SQL
             */
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            /*
            Thuc thi cau lenh SQL
             */
            String SQL = "SELECT * FROM SanPham";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()){
                String masp = rs.getString(1);
                String tensp = rs.getString(2);
                int soLuong = rs.getInt(3);
                double giaBan = rs.getDouble(4);
                DanhMucSanPham danhMuc = new DanhMucSanPham(rs.getString(5));
                NhaCungCapSanPham nhaCungCap = new NhaCungCapSanPham(rs.getString(6));
                Ke ke = new Ke(rs.getString(7));
                SanPham sp = new SanPham(masp, tensp,soLuong,giaBan,danhMuc,nhaCungCap,ke);
                dsSanPham.add(sp);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dsSanPham;
    }
    /*
    TODO: Phương thức thêm sản phẩm
     */
    public boolean themSanPham(SanPham SanPham)  {
        //thêm sản phẩm
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        String SQL = "INSERT INTO SanPham VALUES (?,?,?,?,?,?,?)";
        int n = 0;
        try {

            statement = con.prepareStatement(SQL);
            statement.setString(1,SanPham.getMaSanPham());
            statement.setString(2,SanPham.getTenSanPham());
            statement.setInt(3,SanPham.getSoLuong());
            statement.setDouble(4,SanPham.getGiaBan());
            statement.setString(5,SanPham.getDanhMucSanPham().getMaDanhMuc());
            statement.setString(6,SanPham.getNhaCungCapSanPham().getMaNhaCungCap());
            statement.setString(7,SanPham.getKe().getMaKe());
            n = statement.executeUpdate();

            }catch (SQLException e){
                e.printStackTrace();
            }finally {
            try {
                statement.close();
            } catch (Exception e2) {
                // TODO: handle exception
                e2.printStackTrace();
            }
        }
        return n > 0;
    }
    /*
    TODO kiểm tra mã sản phẩm đã tồn tại chưa
     */
    public boolean kiemTraMaSanPham(String masp) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT * FROM SanPham WHERE maSanPham=?";
            statement = con.prepareStatement(SQL);
            statement.setString(1, masp);
            rs = statement.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }
    public boolean xoaSanPham(String masp){
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;
        try {
            String SQL = "DELETE FROM SanPham WHERE maSanPham=?";
            statement = con.prepareStatement(SQL);
            statement.setString(1,masp);
            n = statement.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không thể xóa sản phẩm này vì sản phẩm này đã được sử dụng trong hóa đơn");
        }finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return n > 0;
    }
    /*
    TODO Cập nhật sản phẩm
     */
    public boolean capNhatSanPham(SanPham SanPham) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;
        try {
            String SQL = "UPDATE SanPham SET tenSanPham=?, soLuong=?, giaBan=?, maDanhMuc=?, maNhaCungCap=? , ke=? WHERE maSanPham=?";
            statement = con.prepareStatement(SQL);
            statement.setString(1, SanPham.getTenSanPham());
            statement.setInt(2, SanPham.getSoLuong());
            statement.setDouble(3, SanPham.getGiaBan());
            statement.setString(4, SanPham.getDanhMucSanPham().getMaDanhMuc());
            statement.setString(5, SanPham.getNhaCungCapSanPham().getMaNhaCungCap());
            statement.setString(7, SanPham.getMaSanPham());
            statement.setString(6, SanPham.getKe().getMaKe());
            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return n > 0;
    }
    public boolean CapNhapSoLuong(String maSP,int SoLuong) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql="UPDATE SanPham set soLuong=? WHERE maSanPham=?";
		int n =0;
		try {
			statement = con.prepareStatement(sql);

			statement.setInt(1, SoLuong);
			statement.setString(2, maSP);

			n = statement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return n>0;
	}
    // tìm sản phẩm
    public ArrayList<SanPham> timSanPham(String id) {
		ArrayList<SanPham> ds = new ArrayList<SanPham>();

		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {

			String sql = "select * from SanPham where maSanPham=?";
			statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {

				String masp = rs.getString(1);
                String tensp = rs.getString(2);
                int soLuong = rs.getInt(3);
                double giaBan = rs.getDouble(4);
                DanhMucSanPham danhMuc = new DanhMucSanPham(rs.getString(5));
                NhaCungCapSanPham nhaCungCap = new NhaCungCapSanPham(rs.getString(6));
                Ke ke = new Ke(rs.getString(7));
                SanPham sp = new SanPham(masp, tensp,soLuong,giaBan,danhMuc,nhaCungCap,ke);
                ds.add(sp);

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
