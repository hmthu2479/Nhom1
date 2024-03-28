package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.NumberFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import com.toedter.calendar.JDateChooser;
import dao.*;

import dao.ChiTietHoaDonDAO;
import dao.DanhMucDAO;
import dao.DonDatHangDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.NhanVienDAO;
import dao.LinhKienDAO;
import entity.ChiTietHoaDon;
import entity.ChiTietHoaDon;
import entity.DanhMucLinhKien;
import entity.DonDatHang;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.LinhKien;

public class FrmDonDatHang extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTable tblChiTietDatHang;
	private JTable tblDatHang;
	private JTextField txtMaDonDatHang;
	private JTextField txtTongTien;
	private JTextField txtTienNhan;
	private JTextField txtTienTraLai;

	private JTextField txtKH;
	private DefaultTableModel modelDathang, modelCTDatHang;
	private DonDatHangDAO ddh_dao;

	private ChiTietHoaDonDAO cthd_dao;
	private KhachHangDAO kh_dao;
	private LinhKienDAO lk_dao;
	private DanhMucDAO dm_dao;
	private JButton btnLamMoi;
	private JTextField txtSDT;
	private JDateChooser dateNgayDat;
	private JButton btnThanhToan;
	private JButton btnInHoaDon;
	private JButton btnTm;
	private JComboBox<String> cbNhanVien;
	private NhanVienDAO nv_dao;
	private HoaDonDAO hd_dao;
	protected String MaDonHang;
	protected int r;
	private Locale localeVN = new Locale("vi", "VN");
	NumberFormat vn = NumberFormat.getInstance(localeVN);

	/**
	 * Create the panel.
	 */

	public FrmDonDatHang() {
		setMaximumSize(new Dimension(1500, 1030));
		setMinimumSize(new Dimension(1500, 1030));
		setMaximumSize(new Dimension(1500, 1030));
		setName("Bán hàng");
		/**
		 *
		 */

		setSize(new Dimension(1550, 845));
		setLayout(null);
		ddh_dao = new DonDatHangDAO();

		kh_dao = new KhachHangDAO();
		lk_dao = new LinhKienDAO();
		dm_dao = new DanhMucDAO();
		nv_dao = new NhanVienDAO();
		hd_dao = new HoaDonDAO();
		cthd_dao = new ChiTietHoaDonDAO();
		JPanel panelTieuDe = new JPanel();
		JLabel lblTitLe = new JLabel("DANH SÁCH ĐƠN ĐẶT HÀNG");
		lblTitLe.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitLe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitLe.setFont(new Font("Arial", Font.BOLD, 25));
		lblTitLe.setBounds(0, 10, 1540, 37);
		panelTieuDe.add(lblTitLe);
		panelTieuDe.setBounds(0, 0, 1540, 60);
		add(panelTieuDe);
		panelTieuDe.setBackground(new Color(148, 162, 242));
		lblTitLe.setForeground(Color.WHITE);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(0, 58, 1540, 238);
		add(panel_1);
		panel_1.setLayout(null);

		txtMaDonDatHang = new JTextField();
		txtMaDonDatHang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaDonDatHang.setColumns(10);
		txtMaDonDatHang.setBounds(74, 60, 200, 40);
		panel_1.add(txtMaDonDatHang);

		JLabel lblMaDonDatHang = new JLabel("Mã Đơn Đặt Hàng");
		lblMaDonDatHang.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaDonDatHang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaDonDatHang.setBounds(74, 30, 188, 33);
		panel_1.add(lblMaDonDatHang);

		btnTm = new JButton("Tìm Đơn Đặt Hàng");
		btnTm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTm.setBounds(84, 190, 250, 40);
		panel_1.add(btnTm);

		JLabel lblTenKhachHang = new JLabel("Tên Khách Hàng");
		lblTenKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenKhachHang.setBounds(403, 30, 194, 33);
		panel_1.add(lblTenKhachHang);

		txtTongTien = new JTextField();
		txtTongTien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTongTien.setColumns(10);
		txtTongTien.setBounds(1017, 89, 216, 40);
		panel_1.add(txtTongTien);

		txtTienNhan = new JTextField();
		txtTienNhan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTienNhan.setColumns(10);
		txtTienNhan.setBounds(1017, 133, 216, 40);
		txtTienNhan.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

			}
		});
		panel_1.add(txtTienNhan);

		txtTienTraLai = new JTextField();
		txtTienTraLai.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTienTraLai.setColumns(10);
		txtTienTraLai.setBounds(1018, 177, 216, 40);
		panel_1.add(txtTienTraLai);

		JLabel lblSDT = new JLabel("Số điện thoại khách hàng");
		lblSDT.setHorizontalAlignment(SwingConstants.LEFT);
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSDT.setBounds(74, 110, 188, 33);
		panel_1.add(lblSDT);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSDT.setColumns(10);
		txtSDT.setBounds(74, 140, 200, 40);
		panel_1.add(txtSDT);

		JLabel lblNgayDat = new JLabel("Ngày Đặt");
		lblNgayDat.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgayDat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNgayDat.setBounds(403, 110, 188, 33);
		panel_1.add(lblNgayDat);

		dateNgayDat = new JDateChooser();
		panel_1.add(dateNgayDat);
		dateNgayDat.setDateFormatString("yyyy-MM-dd");
		dateNgayDat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dateNgayDat.setBounds(403, 140, 200, 40);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 178, 33);
		panel_1.add(panel);

		JLabel lblNewLabel = new JLabel("Tìm Đơn Đặt Hàng");
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(813, 89, 194, 40);
		panel_1.add(panel_2);

		JLabel lblTongTien = new JLabel("Tổng Tiền");
		panel_2.add(lblTongTien);
		lblTongTien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTongTien.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_1.setBounds(813, 133, 194, 40);
		panel_1.add(panel_2_1);

		JLabel lblTieNhan = new JLabel("Tiền Nhận");
		panel_2_1.add(lblTieNhan);
		lblTieNhan.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieNhan.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JPanel panel_2_2 = new JPanel();
		panel_2_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_2.setBounds(813, 177, 195, 40);
		panel_1.add(panel_2_2);

		JLabel lblTienTraLai = new JLabel("Tiền Trả Lại");
		panel_2_2.add(lblTienTraLai);
		lblTienTraLai.setHorizontalAlignment(SwingConstants.CENTER);
		lblTienTraLai.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(768, 0, 4, 306);
		panel_1.add(panel_3);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(768, 0, 188, 33);
		panel_1.add(panel_4);

		JLabel lblThanhToan = new JLabel("Thanh Toán");
		lblThanhToan.setHorizontalAlignment(SwingConstants.CENTER);
		lblThanhToan.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_4.add(lblThanhToan);

		btnThanhToan = new JButton("Thanh Toán");
		btnThanhToan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThanhToan.setBounds(1309, 95, 178, 63);
		panel_1.add(btnThanhToan);

		txtKH = new JTextField();
		txtKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtKH.setColumns(10);
		txtKH.setBounds(403, 60, 200, 40);
		panel_1.add(txtKH);

		JPanel panel_2_3 = new JPanel();
		panel_2_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2_3.setBounds(813, 45, 194, 40);
		panel_1.add(panel_2_3);

		JLabel lblTenNhanVien = new JLabel("Tên Nhân Viên Thanh Toán");
		lblTenNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2_3.add(lblTenNhanVien);

		cbNhanVien = new JComboBox<String>();
		ArrayList<NhanVien> dsnv = nv_dao.layThongTin();
		cbNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		for (NhanVien nv : dsnv) {

			cbNhanVien.addItem(nv.getHoTen());
		}
		cbNhanVien.setBounds(1017, 45, 216, 40);
		panel_1.add(cbNhanVien);

				btnLamMoi = new JButton("Làm mới dữ liệu");
				btnLamMoi.setBounds(375, 190, 250, 40);
				panel_1.add(btnLamMoi);
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tblDatHang.setRowSorter(null);

			}
		});

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 306, 1540, 183);
		add(scrollPane_1);
		String[] Header = { "Mã Đơn Đặt Hàng", "Tên Khách Hàng", "Số Điện Thoại", "Ngày Đặt", "Ghi Chú" };
		modelDathang = new DefaultTableModel(Header, 0);
		tblDatHang = new JTable(modelDathang);
		tblDatHang.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 16));
		tblDatHang.setFont(new Font("Tahoma", Font.PLAIN, 16));

		scrollPane_1.setViewportView(tblDatHang);

		tblDatHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				r = tblDatHang.getSelectedRow();
				MaDonHang = modelDathang.getValueAt(r, 0).toString();
				txtMaDonDatHang.setText(modelDathang.getValueAt(r, 0).toString());
				txtKH.setText(modelDathang.getValueAt(r, 1).toString());
				txtSDT.setText(modelDathang.getValueAt(r, 2).toString());
				dateNgayDat.setDate((Date) modelDathang.getValueAt(r, 3));

			}
		});
		JLabel lblNewLabel_5 = new JLabel("CHI TIẾT ĐƠN ĐẶT HÀNG");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_5.setBounds(0, 487, 1540, 42);
		add(lblNewLabel_5);

		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(0, 526, 1540, 309);
		add(scrollPane_1_1);
		String[] header1 = { "Mã Linh Kiện", "Tên Linh Kiện", "Nhà Cung cấp", "Thời Gian BH", "Giá Bán", "Số Lượng", "Danh Mục"};
		modelCTDatHang = new DefaultTableModel(header1, 0);
		tblChiTietDatHang = new JTable(modelCTDatHang);
		tblChiTietDatHang.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 16));
		tblChiTietDatHang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane_1_1.setViewportView(tblChiTietDatHang);
		btnTm.addActionListener(this);
		btnThanhToan.addActionListener(this);
		// thay đổi màu của tất cả các buttom thành màu #716DF2 và chữ của các buttom thành màu trắng
		btnTm.setBackground(new Color(113, 109, 242));
		btnTm.setForeground(Color.WHITE);
		btnThanhToan.setBackground(new Color(113, 109, 242));
		btnThanhToan.setForeground(Color.WHITE);
		btnLamMoi.setBackground(new Color(113, 109, 242));
		btnLamMoi.setForeground(Color.WHITE);
		btnTm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThanhToan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		// thêm icon cho các buttom với đường dẫn sr/icon
		btnTm.setIcon(new ImageIcon("src/icon/search.png"));
		btnThanhToan.setIcon(new ImageIcon("src/icon/sales2.png"));
		btnLamMoi.setIcon(new ImageIcon("src/icon/loading.png"));
		// thay đổi màu của table thành màu #ECF2FF
		tblDatHang.setBackground(new Color(236, 242, 255));
		tblChiTietDatHang.setBackground(new Color(236, 242, 255));
		// thay  đổi màu header của table thành màu #94A2F2
		tblDatHang.getTableHeader().setBackground(new Color(148, 162, 242));
		tblChiTietDatHang.getTableHeader().setBackground(new Color(148, 162, 242));
		panel_1.setBackground(new Color(236, 242, 255));
		panel_2.setBackground(new Color(236, 242, 255));
		// thay đổi màu combo box thành màu #ECF2FF
		cbNhanVien.setBackground(new Color(148, 162, 242));
		cbNhanVien.setForeground(Color.WHITE);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
    }
}
