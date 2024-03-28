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
    private FrmTrangChu frmTrangChu;
    private FrmLinhKien frmlinhkien;
    private FrmDanhMucLinhKien frmDanhMucLinhKien;
    private FrmNhaCungCapLinhKien frmNhaCungCapLinhKien;
    private FrmNhanVien frmNhanVien;
    private FrmKhachHang frmKhachHang;
    private FrmBanHang frmBanHang;
    private FrmHoaDon frmHoaDon;
    private FrmDonDatHang frmDonDatHang;


    private JPanel contentPane;
    public FrmManHinhChinh(){
        frmTrangChu =  new FrmTrangChu();
        frmlinhkien = new FrmLinhKien();
        frmDanhMucLinhKien = new FrmDanhMucLinhKien();
        frmNhaCungCapLinhKien = new FrmNhaCungCapLinhKien();
        frmNhanVien = new FrmNhanVien();
        frmKhachHang = new FrmKhachHang();
        frmBanHang = new FrmBanHang();
        frmHoaDon = new FrmHoaDon();
        frmDonDatHang = new FrmDonDatHang();

        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("HỆ THỐNG QUẢN LÝ BÁN LINH KIỆN");
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
        JMenu mnTrangChu = new JMenu("Trang chủ");
        mnTrangChu.setFont(new Font("Arial", Font.PLAIN, 20));
        // thay đổi màu chữ thành màu trắng
        mnTrangChu.setForeground(Color.WHITE);
        menuBar.add(mnTrangChu);
        // thêm icon
        ImageIcon iconTC = new ImageIcon("src/icon/home2.png");
        mnTrangChu.setIcon(iconTC);
        /*
        MENU LINH KIEN
         */
        JMenu mnLinhKien = new JMenu("Linh kiện");
        mnLinhKien.setFont(new Font("Arial", Font.PLAIN, 20));
        // thay đổi màu chữ thành màu trắng
        mnLinhKien.setForeground(Color.WHITE);
        menuBar.add(mnLinhKien);
        ImageIcon iconLK = new ImageIcon("src/icon/development2.png");
        mnLinhKien.setIcon(iconLK);
        JMenuItem mntmThongTinLinhKien = new JMenuItem("Thông tin linh kiện");
        mntmThongTinLinhKien.setFont(new Font("Arial", Font.PLAIN, 20));
        // khi  chạy chương trình lên thì sẽ hiển thị màn hình mntmThongTinLinhKien





        mnLinhKien.add(mntmThongTinLinhKien);
        ImageIcon iconTT = new ImageIcon("src/icon/product3.png");
        mntmThongTinLinhKien.setIcon(iconTT);
        JMenuItem mntmDanhMuclinhKien = new JMenuItem("Danh mục");
        mntmDanhMuclinhKien.setFont(new Font("Arial", Font.PLAIN, 20));
        mnLinhKien.add(mntmDanhMuclinhKien);
        ImageIcon iconDM = new ImageIcon("src/icon/dm2.png");
        mntmDanhMuclinhKien.setIcon(iconDM);
        JMenuItem mntmNhaCungCap = new JMenuItem("Nhà cung cấp");
        mntmNhaCungCap.setFont(new Font("Arial", Font.PLAIN, 20));
        ImageIcon iconNCC = new ImageIcon("src/icon/ncc.png");
        mntmNhaCungCap.setIcon(iconNCC);
        mnLinhKien.add(mntmNhaCungCap);
         /*
        MENU KHACH HANG
         */
        JMenu mnKhachHang = new JMenu("Khách hàng");
        mnKhachHang.setFont(new Font("Arial", Font.PLAIN, 20));
        // thay đổi màu chữ thành màu trắng
        mnKhachHang.setForeground(Color.WHITE);
        menuBar.add(mnKhachHang);
        ImageIcon iconKH = new ImageIcon("src/icon/customer2.png");
        mnKhachHang.setIcon(iconKH);

        JMenuItem mntmThongTinKhachHang = new JMenuItem("Thông tin khách hàng");
        mntmThongTinKhachHang.setFont(new Font("Arial", Font.PLAIN, 20));
        ImageIcon iconTTKH = new ImageIcon("src/icon/cs2.png");
        mntmThongTinKhachHang.setIcon(iconTTKH);
        mnKhachHang.add(mntmThongTinKhachHang);
        /*
        MENU NHAN VIEN
         */
        JMenu mnNhanVien = new JMenu("Nhân viên");
        mnNhanVien.setFont(new Font("Arial", Font.PLAIN, 20));
        // thay đổi màu chữ thành màu trắng
        mnNhanVien.setForeground(Color.WHITE);
        menuBar.add(mnNhanVien);
        ImageIcon iconNV = new ImageIcon("src/icon/division2.png");
        mnNhanVien.setIcon(iconNV);

        JMenuItem mntmThongTinNhanVien = new JMenuItem("Thông tin nhân viên");
        mntmThongTinNhanVien.setFont(new Font("Arial", Font.PLAIN, 20));
        ImageIcon iconTTNV = new ImageIcon("src/icon/e2.png");
        mntmThongTinNhanVien.setIcon(iconTTNV);
        mnNhanVien.add(mntmThongTinNhanVien);

        JMenuItem mntmBanHang = new JMenuItem("Bán hàng");
        mntmBanHang.setFont(new Font("Arial", Font.PLAIN, 20));
        mnNhanVien.add(mntmBanHang);
        ImageIcon iconBH = new ImageIcon("src/icon/sales2.png");
        mntmBanHang.setIcon(iconBH);
        JMenuItem mntmHoaDon = new JMenuItem("Xem hóa đơn");
        mntmHoaDon.setFont(new Font("Arial", Font.PLAIN, 20));
        mnNhanVien.add(mntmHoaDon);
        ImageIcon iconHD = new ImageIcon("src/icon/completed2.png");
        mntmHoaDon.setIcon(iconHD);
        JMenuItem mntmDonDatHang = new JMenuItem("Xem đơn đặt hàng");
        mntmDonDatHang.setFont(new Font("Arial", Font.PLAIN, 20));
        mnNhanVien.add(mntmDonDatHang);
        ImageIcon iconDDH = new ImageIcon("src/icon/order2.png");
        mntmDonDatHang.setIcon(iconDDH);

        /*
        Liên kết các panel
         */
        JPanel panelBody = new JPanel();
        panelBody.setBounds(0,50,1550,1030);
        contentPane.add(panelBody);
        panelBody.setLayout(new BorderLayout(0,0));
        /*
        Liên kết form trang chủ
         */
        mnTrangChu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelBody.removeAll();
                panelBody.add(new FrmTrangChu());
                panelBody.validate();
                contentPane.add(panelBody);
            }
        });
        /*
        Liên kết form thông tin linh kiện
         */
        mntmThongTinLinhKien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelBody.removeAll();
                panelBody.add(frmlinhkien);
                panelBody.validate();
            }
        });
        /*
        Liên kết form danh mục linh kiện
         */
        mntmDanhMuclinhKien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelBody.removeAll();
                panelBody.add(frmDanhMucLinhKien);
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
                panelBody.add(frmNhaCungCapLinhKien);
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
        /*
        Liên kết form đơn đặt hàng
         */
        mntmDonDatHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelBody.removeAll();
                panelBody.add(frmDonDatHang);
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
        mntmThongTinLinhKien.doClick();
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