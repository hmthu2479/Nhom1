package gui;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FrmManHinhChinh extends JFrame implements ActionListener, MouseListener {
    private static final long serialVersionUID = 1;
    private FrmSanPham frmSanPham;
    private FrmDanhMucSanPham frmDanhMucSanPham;
    private FrmNhaCungCapSanPham frmNhaCungCapSanPham;
    private FrmNhanVien frmNhanVien;
    private FrmKhachHang frmKhachHang;
    private FrmBanHang frmBanHang;
    private FrmHoaDon frmHoaDon;
    private FrmPhieuDatHang frmHoaDonDatHang;


    private JPanel contentPane;
    public FrmManHinhChinh(){
        frmSanPham = new FrmSanPham();
        frmDanhMucSanPham = new FrmDanhMucSanPham();
        frmNhaCungCapSanPham = new FrmNhaCungCapSanPham();
        frmNhanVien = new FrmNhanVien();
        frmKhachHang = new FrmKhachHang();
        frmBanHang = new FrmBanHang();
        frmHoaDon = new FrmHoaDon();
        frmHoaDonDatHang = new FrmPhieuDatHang();

        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("HỆ THỐNG QUẢN LÝ CỬA HÀNG TIỆN LỢI");
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(true);
		setExtendedState(MAXIMIZED_BOTH);
        /*===================================================================
        * 1. Tạo menu
         ==================================================================*/
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0,0,1920,50);
        // thêm màu cho menu với  mã màu là #716DF2
        menuBar.setBackground(Color.decode("#716DF2"));
        contentPane.add(menuBar);

        JMenu mnSanPham = new JMenu("SẢN PHẨM");
        mnSanPham.setFont(new Font("Arial", Font.PLAIN, 20));
        // thay đổi màu chữ thành màu trắng
        mnSanPham.setForeground(Color.WHITE);
        menuBar.add(mnSanPham);
        JMenuItem mntmThongTinSanPham = new JMenuItem("Thông tin SẢN PHẨM");
        mntmThongTinSanPham.setFont(new Font("Arial", Font.PLAIN, 20));
        // khi  chạy chương trình lên thì sẽ hiển thị màn hình mntmThongTinSanPham

        mnSanPham.add(mntmThongTinSanPham);
        JMenuItem mntmDanhMucSanPham = new JMenuItem("Danh mục");
        mntmDanhMucSanPham.setFont(new Font("Arial", Font.PLAIN, 20));
        mnSanPham.add(mntmDanhMucSanPham);
        JMenuItem mntmNhaCungCap = new JMenuItem("Nhà cung cấp");
        mntmNhaCungCap.setFont(new Font("Arial", Font.PLAIN, 20));
        mnSanPham.add(mntmNhaCungCap);
         /*
        MENU KHACH HANG
         */
        JMenu mnKhachHang = new JMenu("Khách hàng");
        mnKhachHang.setFont(new Font("Arial", Font.PLAIN, 20));
        // thay đổi màu chữ thành màu trắng
        mnKhachHang.setForeground(Color.WHITE);
        menuBar.add(mnKhachHang);

        JMenuItem mntmThongTinKhachHang = new JMenuItem("Thông tin khách hàng");
        mntmThongTinKhachHang.setFont(new Font("Arial", Font.PLAIN, 20));
        mnKhachHang.add(mntmThongTinKhachHang);
        /*
        MENU NHAN VIEN
         */
        JMenu mnNhanVien = new JMenu("Nhân viên");
        mnNhanVien.setFont(new Font("Arial", Font.PLAIN, 20));
        // thay đổi màu chữ thành màu trắng
        mnNhanVien.setForeground(Color.WHITE);
        menuBar.add(mnNhanVien);

        JMenuItem mntmThongTinNhanVien = new JMenuItem("Thông tin nhân viên");
        mntmThongTinNhanVien.setFont(new Font("Arial", Font.PLAIN, 20));
        mnNhanVien.add(mntmThongTinNhanVien);

        JMenuItem mntmBanHang = new JMenuItem("Bán hàng");
        mntmBanHang.setFont(new Font("Arial", Font.PLAIN, 20));
        mnNhanVien.add(mntmBanHang);
        JMenuItem mntmHoaDon = new JMenuItem("Xem hóa đơn");
        mntmHoaDon.setFont(new Font("Arial", Font.PLAIN, 20));
        mnNhanVien.add(mntmHoaDon);
        JMenuItem mntmDatHang = new JMenuItem("Xem danh sách đặt hàng");
        mntmDatHang.setFont(new Font("Arial", Font.PLAIN, 20));
        mnNhanVien.add(mntmDatHang);


        /*
        Liên kết các panel
         */
        JPanel panelBody = new JPanel();
        panelBody.setBounds(0,50,1550,1030);
        contentPane.add(panelBody);
        panelBody.setLayout(new BorderLayout(0,0));
       
        /*
        Liên kết form thông tin SẢN PHẨM
         */
        mntmThongTinSanPham.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelBody.removeAll();
                panelBody.add(frmSanPham);
                panelBody.validate();
            }
        });
        /*
        Liên kết form danh mục SẢN PHẨM
         */
        mntmDanhMucSanPham.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelBody.removeAll();
                panelBody.add(frmDanhMucSanPham);
                panelBody.validate();
            }
        });
        /*
        Liên kết form nhà cung cấp
         */
        mntmNhaCungCap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelBody.removeAll();
                panelBody.add(frmNhaCungCapSanPham);
                panelBody.validate();
            }
        });
        /*
        Liên kết form nhân viên
         */
        mntmThongTinNhanVien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelBody.removeAll();
                panelBody.add(frmNhanVien);
                panelBody.validate();
            }
        });

        /*
        Liên kết form bán hàng
         */
        mntmBanHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelBody.removeAll();
                panelBody.add(frmBanHang);
                panelBody.validate();
            }
        });
        /*
        Liên kết form hóa đơn
         */
        mntmHoaDon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelBody.removeAll();
                panelBody.add(frmHoaDon);
                panelBody.validate();
            }
        });
        mntmDatHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelBody.removeAll();
                panelBody.add(frmHoaDonDatHang);
                panelBody.validate();
            }
        });
/*
        Liên kết form khách hàng
         */
        mntmThongTinKhachHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelBody.removeAll();
                panelBody.add(frmKhachHang);
                panelBody.validate();
            }
        });
        mntmThongTinSanPham.doClick();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}