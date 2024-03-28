package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


import dao.*;
import entity.*;


public class FrmBanHang extends JPanel implements ActionListener {

	/**
	 * Create the panel.
	 */
	private static final long serialVersionUID = 1L;
	private final JButton btnLamMoiSP;
	private JTextField txtMaLK;
	private JTextField txtTenLK;
	private JTable tblDonHang, tblSanPham;
	private JTextField txtSDT;
	private JTextField txtTenKhachHang;
	private JTextField txtDiaChi;
	private JTextField txtTongTien;
	private DefaultTableModel model_sanPham, model_DonHang;
	private LinhKienDAO lk_dao;
	private JButton btnTimKiemSP, btnThem, btnXoaSanPham;
	private JComboBox<String> cBDanhMuc, cBSize, cBNCC;
	private double thanhtien;
	private int soluong;
	private JSpinner spSoLuong;
	@SuppressWarnings("deprecation")
	private Locale localeVN = new Locale("vi", "VN");
	private Locale localDF = Locale.getDefault();
	private NhaCungCapDAO ncc_dao;
	private KhachHangDAO kh_dao;
	NumberFormat vn = NumberFormat.getInstance(localeVN);
	NumberFormat DF = NumberFormat.getInstance(localDF);
	int n = 1;

	private JButton btnLamMoi;
	private JButton btnTimKH;
	private JButton btnDatHang;
	private JButton btnThanhToan;
	private DonDatHangDAO ddh_dao;

	private NhanVienDAO nv_dao;
	private HoaDonDAO hd_Dao;

	private JButton btnInHoaDon;
	private JComboBox<String> cbNhanVien;
	private DanhMucDAO dm_dao;
	protected String soLuongSP;
	private JButton btnHuy;
    private DanhMucLinhKien dsDM;
    private NhaCungCapLinhKien dsNCC;
    private JPanel pBody_1;
	private String maKH;
	private ChiTietHoaDonDAO ctHD_dao;

	/**
	 * Create the panel.
	 */

	public FrmBanHang() {
		setMaximumSize(new Dimension(1500, 1030));
		setMinimumSize(new Dimension(1500, 1030));
		setMaximumSize(new Dimension(1500, 1030));
		setName("Bán hàng");
		/**
		 *
		 */

		setSize(new Dimension(1550, 845));
		setLayout(null);
		lk_dao = new LinhKienDAO();
		ncc_dao = new NhaCungCapDAO();
		kh_dao = new KhachHangDAO();
		ddh_dao = new DonDatHangDAO();

		nv_dao = new NhanVienDAO();
		hd_Dao = new HoaDonDAO();
		ctHD_dao = new ChiTietHoaDonDAO();
		dm_dao = new DanhMucDAO();

		JPanel pMain = new JPanel();
		pMain.setSize(new Dimension(1550, 1030));
		pMain.setPreferredSize(new Dimension(1550, 1030));
		pMain.setMinimumSize(new Dimension(1550, 1030));
		pMain.setMaximumSize(new Dimension(1550, 1030));
		pMain.setBounds(0, 0, 1540, 826);
		add(pMain);
		pMain.setLayout(null);
		JPanel panelTitle = new JPanel();
		JLabel lblTitLe = new JLabel("BÁN HÀNG");
		lblTitLe.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitLe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitLe.setFont(new Font("Arial", Font.BOLD, 25));
		lblTitLe.setForeground(Color.WHITE);
		panelTitle.add(lblTitLe);
		panelTitle.setBounds(0, 0, 1540, 40);
		panelTitle.setBackground(new Color(148, 162, 242));
		pMain.add(panelTitle);

		JPanel pBody = new JPanel();
		pBody.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pBody.setBorder(new TitledBorder(null, "Tìm kiếm linh kiện", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pBody.setName("Tìm kiếm linh kiện");
		pBody.setBounds(0, 41, 826, 148);
		pMain.add(pBody);
		pBody.setLayout(null);

		txtMaLK = new JTextField();
		txtMaLK.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaLK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaLK.setBounds(130, 17, 180, 40);
		pBody.add(txtMaLK);
		txtMaLK.setColumns(10);

		JLabel lblDanhMuc = new JLabel("Danh mục");
		lblDanhMuc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDanhMuc.setBounds(10, 79, 90, 40);
		pBody.add(lblDanhMuc);

		cBDanhMuc = new JComboBox<String>();
		cBDanhMuc.setBounds(130, 80, 180, 40);
		cBDanhMuc.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ArrayList<DanhMucLinhKien> dsDM = dm_dao.layThongTin();
        for (DanhMucLinhKien dm : dsDM) {
            cBDanhMuc.addItem(dm.getTenDanhMuc());
        }
		cBDanhMuc.addItem("Tất cả");
		pBody.add(cBDanhMuc);

		JLabel lblNhaCungCap = new JLabel("Nhà Cung Cấp");
		lblNhaCungCap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNhaCungCap.setBounds(347, 81, 114, 37);
		pBody.add(lblNhaCungCap);

		cBNCC = new JComboBox<String>();
		ArrayList<NhaCungCapLinhKien> dsncc = ncc_dao.layThongTin();
		for (NhaCungCapLinhKien ncc : dsncc) {
			cBNCC.addItem(ncc.getTenNCC());
		}
		cBNCC.setBounds(464, 79, 180, 40);
		cBNCC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cBNCC.addItem("Tất cả");
		pBody.add(cBNCC);

		txtTenLK = new JTextField();
		txtTenLK.setHorizontalAlignment(SwingConstants.CENTER);
		txtTenLK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenLK.setColumns(10);
		txtTenLK.setBounds(464, 15, 180, 40);
		pBody.add(txtTenLK);


		btnTimKiemSP = new JButton("Tìm Kiếm");
		btnTimKiemSP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTimKiemSP.setBounds(666, 15, 150, 40);
		pBody.add(btnTimKiemSP);

		JLabel lblmaLK = new JLabel("Mã linh kiện");
		lblmaLK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblmaLK.setBounds(10, 19, 113, 37);
		txtMaLK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMaLK.setText("");
			}
		});
		pBody.add(lblmaLK);

		JLabel lblTenLK = new JLabel("Tên linh kiện");
		lblTenLK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenLK.setBounds(347, 17, 114, 37);
		txtTenLK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtTenLK.setText("");
			}
		});
		pBody.add(lblTenLK);

		btnLamMoiSP = new JButton("Làm mới");
		btnLamMoiSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLamMoiSP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLamMoiSP.setBounds(666, 79, 150, 40);
		pBody.add(btnLamMoiSP);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 199, 1540, 200);
		pMain.add(scrollPane);

		String[] Header = { "Mã Linh Kiện", "Tên Linh Kiện", "Nhà Cung cấp", "Thời Gian BH", "Giá Bán", "Số Lượng", "Danh Mục"};
		model_sanPham = new DefaultTableModel(Header, 0);
		tblSanPham = new JTable(model_sanPham);
		tblSanPham.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 16));
		tblSanPham.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane.setViewportView(tblSanPham);
		//DocDuLieuSP();

		tblSanPham.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblSanPham.getSelectedRow();
				txtMaLK.setText(tblSanPham.getValueAt(row, 0).toString());
				txtTenLK.setText(tblSanPham.getValueAt(row, 1).toString());
				cBNCC.setSelectedItem(tblSanPham.getValueAt(row, 2).toString());
				cBDanhMuc.setSelectedItem(tblSanPham.getValueAt(row, 6).toString());
				soLuongSP = model_sanPham.getValueAt(row, 5).toString();
			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(427, 778));
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(1113, 409, 427, 417);
		pMain.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblTongTien = new JLabel("Tổng Tiền");
		lblTongTien.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTongTien.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongTien.setBounds(39, 113, 140, 50);
		panel_1.add(lblTongTien);

		btnDatHang = new JButton("Đặt Hàng");
		btnDatHang.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDatHang.setBounds(39, 186, 160, 60);
		panel_1.add(btnDatHang);

		btnThanhToan = new JButton("Thanh Toán");
		btnThanhToan.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnThanhToan.setBounds(245, 186, 160, 60);
		panel_1.add(btnThanhToan);

		txtTongTien = new JTextField();
		txtTongTien.setColumns(10);
		txtTongTien.setBounds(189, 116, 216, 50);
		txtTongTien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(txtTongTien);

		JLabel lblNhanVienThanhToan = new JLabel("Nhân Viên Thanh toán");
		lblNhanVienThanhToan.setBounds(10, 31, 194, 33);
		panel_1.add(lblNhanVienThanhToan);
		lblNhanVienThanhToan.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhanVienThanhToan.setFont(new Font("Arial", Font.PLAIN, 16));

		cbNhanVien = new JComboBox<String>();
		ArrayList<NhanVien> dsnv = nv_dao.layThongTin();
		cbNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		for (NhanVien nv : dsnv) {

			cbNhanVien.addItem(nv.getHoTen());
		}
		cbNhanVien.setBounds(39, 62, 366, 40);
		panel_1.add(cbNhanVien);

		btnHuy = new JButton("Hủy");
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnHuy.setBounds(143, 256, 160, 60);
		panel_1.add(btnHuy);

		JLabel lblThanhToan = new JLabel("Thanh Toán");
		lblThanhToan.setBorder(UIManager.getBorder("ComboBox.border"));
		lblThanhToan.setBounds(0, 0, 169, 33);
		panel_1.add(lblThanhToan);
		lblThanhToan.setHorizontalAlignment(SwingConstants.CENTER);
		lblThanhToan.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblSL = new JLabel("Số lượng");
		lblSL.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSL.setBounds(148, 413, 71, 37);
		pMain.add(lblSL);

		spSoLuong = new JSpinner();
		spSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		spSoLuong.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spSoLuong.setBounds(231, 409, 81, 45);
		pMain.add(spSoLuong);

		btnThem = new JButton("Thêm Sản Phẩm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThem.setBounds(363, 409, 200, 50);
		pMain.add(btnThem);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLamMoi.setBounds(816, 409, 200, 50);
		pMain.add(btnLamMoi);

		btnXoaSanPham = new JButton("Xóa Sản Phẩm");
		btnXoaSanPham.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXoaSanPham.setBounds(598, 409, 200, 50);
		pMain.add(btnXoaSanPham);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setRequestFocusEnabled(false);
		scrollPane_1.setBounds(0, 494, 1103, 332);
		pMain.add(scrollPane_1);

		String[] Header1 = { "STT", "Mã linh kiện", "Tên linh kiện", "Danh mục", "Nhà cung cấp","Giá Bán", "Số Lượng", "Thành Tiền" };
		model_DonHang = new DefaultTableModel(Header1, 0);

		tblDonHang = new JTable(model_DonHang);
		tblDonHang.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 16));
		tblDonHang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane_1.setViewportView(tblDonHang);

		JLabel lblTitLe_2 = new JLabel("Sản Phẩm Đã Chọn");
		lblTitLe_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitLe_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitLe_2.setBounds(0, 460, 1104, 37);
		pMain.add(lblTitLe_2);

		pBody_1 = new JPanel();
		pBody_1.setLayout(null);
		pBody_1.setName("Tìm kiếm linh kiện");
		pBody_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pBody_1.setBorder(new TitledBorder(null, "Tìm kiếm khách hàng", TitledBorder.LEADING,

				TitledBorder.TOP, null, null));
		pBody_1.setBounds(847, 41, 693, 148);
		pMain.add(pBody_1);

		txtSDT = new JTextField();
		txtSDT.setBounds(54, 32, 264, 40);
		pBody_1.add(txtSDT);
		txtSDT.setText("Số điện thoại khách hàng\r\n");
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSDT.setColumns(10);

		btnTimKH = new JButton("Tìm");
		btnTimKH.setBounds(341, 32, 294, 40);
		pBody_1.add(btnTimKH);
		btnTimKH.setFont(new Font("Tahoma", Font.PLAIN, 16));

		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setBounds(54, 82, 264, 40);
		pBody_1.add(txtTenKhachHang);
		txtTenKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenKhachHang.setText("Tên Khách Hàng");
		txtTenKhachHang.setColumns(10);
		txtTenKhachHang.setEditable(false);
		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(341, 82, 294, 40);
		pBody_1.add(txtDiaChi);
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDiaChi.setText("Địa Chỉ");
		txtDiaChi.setColumns(10);
		txtDiaChi.setEditable(false);
		btnTimKH.addActionListener(this);
		txtSDT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSDT.setText("");
			}
		});

		btnTimKiemSP.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoaSanPham.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnDatHang.addActionListener(this);
		btnThanhToan.addActionListener(this);
		btnHuy.addActionListener(this);
		btnLamMoiSP.addActionListener(this);

		docDuLieuLinhKien();

		// thay đổi màu của tất cả các buttom thành màu #716DF2 và chữ của các buttom thành màu trắng
		btnTimKiemSP.setBackground(new Color(113, 109, 242));
		btnTimKiemSP.setForeground(Color.WHITE);
		btnThem.setBackground(new Color(113, 109, 242));
		btnThem.setForeground(Color.WHITE);
		btnXoaSanPham.setBackground(new Color(113, 109, 242));
		btnXoaSanPham.setForeground(Color.WHITE);
		btnLamMoi.setBackground(new Color(113, 109, 242));
		btnLamMoi.setForeground(Color.WHITE);
		btnDatHang.setBackground(new Color(113, 109, 242));
		btnDatHang.setForeground(Color.WHITE);
		btnThanhToan.setBackground(new Color(113, 109, 242));
		btnThanhToan.setForeground(Color.WHITE);
		btnHuy.setBackground(new Color(113, 109, 242));
		btnHuy.setForeground(Color.WHITE);
		btnLamMoiSP.setBackground(new Color(113, 109, 242));
		btnLamMoiSP.setForeground(Color.WHITE);
		btnTimKH.setBackground(new Color(113, 109, 242));
		btnTimKH.setForeground(Color.WHITE);
		// thêm icon cho các buttom với đường dẫn sr/icon
		btnTimKiemSP.setIcon(new ImageIcon("src/icon/search.png"));
		btnThem.setIcon(new ImageIcon("src/icon/plus.png"));
		btnXoaSanPham.setIcon(new ImageIcon("src/icon/delete.png"));
		btnLamMoi.setIcon(new ImageIcon("src/icon/loading.png"));
		btnDatHang.setIcon(new ImageIcon("src/icon/order2.png"));
		btnThanhToan.setIcon(new ImageIcon("src/icon/sales2.png"));
		btnHuy.setIcon(new ImageIcon("src/icon/remove.png"));
		btnLamMoiSP.setIcon(new ImageIcon("src/icon/loading.png"));
		btnTimKH.setIcon(new ImageIcon("src/icon/search.png"));
		pMain.setBackground(Color.WHITE);
		cBDanhMuc.setBackground(new Color(148, 162, 242));
		cBNCC.setBackground(new Color(148, 162, 242));
		cbNhanVien.setBackground(new Color(148, 162, 242));
		spSoLuong.setBackground(new Color(148, 162, 242));
		// thay  đổi text của các combobox thành trắng
		cBDanhMuc.setForeground(Color.WHITE);
		cBNCC.setForeground(Color.WHITE);
		cbNhanVien.setForeground(Color.WHITE);

		// thay đổi màu của table thành màu #ECF2FF
		tblDonHang.setBackground(new Color(236, 242, 255));
		tblSanPham.setBackground(new Color(236, 242, 255));
		// thay  đổi màu header của table thành màu #94A2F2
		tblDonHang.getTableHeader().setBackground(new Color(148, 162, 242));
		tblSanPham.getTableHeader().setBackground(new Color(148, 162, 242));
		pBody.setBackground(new Color(236, 242, 255));
		pBody_1.setBackground(new Color(236, 242, 255));
		panel_1.setBackground(new Color(236, 242, 255));


	}

	//TODO đọc dữ liệu linh kiện
	public void docDuLieuLinhKien() {
		ArrayList<LinhKien> dsLinhKien = lk_dao.layThongTin();
		ArrayList<NhaCungCapLinhKien> dsNhaCungCap = ncc_dao.layThongTin();
		ArrayList<DanhMucLinhKien> dsDanhMuc = dm_dao.layThongTin();
		model_sanPham.setRowCount(0);
		for (LinhKien lk : dsLinhKien) {
			for (NhaCungCapLinhKien ncc : dsNhaCungCap) {
				for (DanhMucLinhKien dm : dsDanhMuc) {
					if (ncc.getMaNhaCungCap().equalsIgnoreCase(lk.getNhaCungCapLinhKien().getMaNhaCungCap())
							&& dm.getMaDanhMuc().equalsIgnoreCase(lk.getDanhMucLinhKien().getMaDanhMuc())) {
						model_sanPham.addRow(new Object[]{lk.getMaLinhKien(), lk.getTenLinhKien(), ncc.getTenNCC(), lk.getThoiGianBaoHanh(), lk.getGiaBan(), lk.getSoLuong(), dm.getTenDanhMuc()});
					}
				}
			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnTimKiemSP)) {


			String maLK = txtMaLK.getText().toString();
			String tenLK = txtTenLK.getText().toString();
			String DanhMuc = cBDanhMuc.getSelectedItem().toString();
			String   NCC = cBNCC.getSelectedItem().toString();
			if(NCC.equalsIgnoreCase("Tất cả")) {
				NCC ="";
			}
			if(DanhMuc.equalsIgnoreCase("Tất cả")) {
				DanhMuc ="";
			}

			System.out.println(maLK);
			System.out.println(tenLK);
			System.out.println(DanhMuc);
			System.out.println(NCC);
			TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model_sanPham);
			tblSanPham.setRowSorter(sorter);
			List<RowFilter<Object, Object>> filters = new ArrayList<>();
			filters.add(RowFilter.regexFilter(maLK, 0));
			filters.add(RowFilter.regexFilter(tenLK, 1));
			filters.add(RowFilter.regexFilter(NCC, 2));
			filters.add(RowFilter.regexFilter(DanhMuc, 6));
			RowFilter<Object, Object> af = RowFilter.andFilter(filters);
			sorter.setRowFilter(af);
		}
		if (o.equals(btnThem)) {

			int row = tblSanPham.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm nào", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				String txtMaLK = tblSanPham.getValueAt(row, 0).toString();
				String txtTenLK = tblSanPham.getValueAt(row, 1).toString();
				String cBDanhMuc = tblSanPham.getValueAt(row, 6).toString();
				String cBNCC = tblSanPham.getValueAt(row, 2).toString();
				String giaban = tblSanPham.getValueAt(row, 4).toString();
				String soLuongSP = tblSanPham.getValueAt(row, 5).toString();

				soluong = (int) spSoLuong.getValue();
				thanhtien = Double.parseDouble(giaban) * soluong;
				String thanhTien = String.valueOf(thanhtien);
				int soluongsp = Integer.parseInt(soLuongSP);

				int a = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn thêm ?", null, JOptionPane.YES_NO_OPTION);
				if (a == JOptionPane.YES_OPTION) {
					if (soluong > soluongsp) {
						JOptionPane.showMessageDialog(this, "Không đủ số lượng đế đáp ứng đơn hàng", "Warning", JOptionPane.WARNING_MESSAGE);
					} else {
						model_DonHang.addRow(new Object[]{n, txtMaLK, txtTenLK, cBDanhMuc, cBNCC, giaban, soluong, thanhTien});
						n++;
						int soLuongCu = Integer.parseInt(tblSanPham.getValueAt(row, 5).toString());
						int soLuongMoi = soLuongCu - soluong;
						tblSanPham.setValueAt(soLuongMoi, row, 5);

					}
				}


				spSoLuong.setValue(1);
				thanhTien();
			}
		}
		if (o.equals(btnXoaSanPham)) {
			int row = tblDonHang.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm nào", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				int r = tblDonHang.getSelectedRow();
				int a = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa ?", null, JOptionPane.YES_NO_OPTION);
				if (a == JOptionPane.YES_OPTION) {
					// trả về số lượng ban đầu
					String maLK = tblDonHang.getValueAt(r, 1).toString();
					docDuLieuLinhKien();
					model_DonHang.removeRow(r);
					n--
					;
				}
				// trả về thành   tiền trừ đi tổng tiền  của sản phẩm đã xóa
				thanhTien();
			}


		}
		if (o.equals(btnLamMoi)) {
			tblSanPham.setRowSorter(null);
			txtTongTien.setText("");
			// xóa hết dữ liệu trong bảng đơn hàng
			int rowCount = model_DonHang.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model_DonHang.removeRow(i);
				n--;
			}
			// trả về số lượng ban đầu
			docDuLieuLinhKien();
			// trả  về thành tiền
			txtTongTien.setText("");


		}
		if(o.equals(btnLamMoiSP)){
			txtMaLK.setText("");
			txtTenLK.setText("");
			cBDanhMuc.setSelectedItem("Tất cả");
			cBNCC.setSelectedItem("Tất cả");
			tblSanPham.setRowSorter(null);
			docDuLieuLinhKien();
		}
		if (o.equals(btnTimKH)) {
			int a = 0;
			ArrayList<KhachHang> dskh = kh_dao.layThongTin();
			for (KhachHang kh : dskh) {
				if (kh.getDiaChiKH().equals(txtSDT.getText())) {
					maKH = kh.getMaKH();
					txtSDT.setText(kh.getDiaChiKH());
					txtTenKhachHang.setText(kh.getTenKH());
					txtDiaChi.setText(kh.getSoDT());
					a = 1;
				}

			}
			if (a == 0) {
				JOptionPane.showMessageDialog(null, "Không có thông tin khách hàng");
				maKH = null;
			}
		}

		if (o.equals(btnThanhToan)) {


			int model_count = model_DonHang.getRowCount();
			if (model_count == 0) {
				JOptionPane.showMessageDialog(null,
						"Bạn chưa có sản phẩm để đặt hàng, vui lòng thêm sản phẩm để đặt hàng");
			} else if (cbNhanVien.getSelectedItem().equals(null)) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên thanh toán");
			}else if(maKH == null) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin khách hàng","Warning",JOptionPane.WARNING_MESSAGE);
			} else {
				String maHD = taoMaHD();
				KhachHang kh = new KhachHang(maKH);
				// lấy ra mã nhân viên
				NhanVien nv = new NhanVien();
				ArrayList<NhanVien> dsnv = nv_dao.layThongTin();
				for (NhanVien ab : dsnv) {
					if (ab.getHoTen().equalsIgnoreCase(cbNhanVien.getSelectedItem().toString())) {
						nv = new NhanVien(ab.getMaNhanVien());
					}
				}
				Date ngayLap = Calendar.getInstance().getTime();
				// ngày giao giao dự kiến bằng ngayLap + 3 ngày
				Calendar cal = Calendar.getInstance();
				cal.setTime(ngayLap);
				cal.add(Calendar.DATE, 3);
				Date ngayGiao = cal.getTime();
				HoaDon hd = new HoaDon(maHD, kh, nv, ngayLap, ngayGiao);
				if(!hd_Dao.kiemTraMaHD(maHD)){
					if(hd_Dao.themHoaDon(hd)){
							// lấy thông tin trong bảng đơn hàng
						int row = model_DonHang.getRowCount();
						for (int j = 0; j < row; j++) {
							String maLK = model_DonHang.getValueAt(j, 1).toString();
							String tenLK = model_DonHang.getValueAt(j, 2).toString();
							String donGia = model_DonHang.getValueAt(j, 3).toString();
							String soLuong = model_DonHang.getValueAt(j, 6).toString();
							String thanhTien = model_DonHang.getValueAt(j, 5).toString();
							LinhKien lk = new LinhKien(maLK);
							int soluongsp = Integer.parseInt(soLuong);
							int sl = Integer.parseInt(soLuongSP);


							ChiTietHoaDon cthd = new ChiTietHoaDon(lk, hd,soluongsp);
							ctHD_dao.themDonHang(cthd);
							// trừ số lượng trong bảng lĩnh kiện
							lk_dao.CapNhapSoLuong(maLK, sl-soluongsp);
							docDuLieuLinhKien();

						}
							JOptionPane.showMessageDialog(null, "Thanh  toán thành công");
							// trừ số lượng trong bảng lĩnh kiện

							tblSanPham.setRowSorter(null);
							txtTongTien.setText("");
							// xóa hết dữ liệu trong bảng đơn hàng
							int rowCount = model_DonHang.getRowCount();
							for (int t = rowCount - 1; t >= 0; t--) {
								model_DonHang.removeRow(t);
								n--;
							}
							// trả về số lượng ban đầu

							// trả  về thành tiền
							txtTongTien.setText("");
					}
				}


			}

		}
		if(o.equals(btnHuy)) {
			XoaTrangALL();
		}

	}

	public void thanhTien() {
		// TODO Auto-generated method stub
		int row = model_DonHang.getRowCount();
		double TongTien = 0;
		for (int i = 0; i < row; i++) {
			try {
				String tien = model_DonHang.getValueAt(i, 5).toString();

				double Thanhtien = Double.parseDouble(tien);
				TongTien += Thanhtien;
			} catch (NumberFormatException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		String tongtien = vn.format(TongTien)+ " VND";
		txtTongTien.setText(tongtien);
	}

	public String taoMaHD() {
		String maHD = "";
		double n = (Math.random()) * ((10000 - 1) + 1) + 1;
		int i = (int) n, a = 0;

		do {
			if (i < 10) {
				maHD = "HD000" + i;
			}
			if (i < 100) {
				maHD = "HD00" + i;
			}
			if (i < 1000) {
				maHD = "HD0" + i;
			}
			if (i < 10000) {
				maHD = "HD" + i;
			}


			if (!hd_Dao.kiemTraMaHD(maHD)) {
				a = 0;
			} else
				a = 1;
		} while (a != 0);

		return maHD;

	}


	public void XoaTrangTimKiemSP() {
		txtTenLK.setText("");
		txtTenLK.setText("");
	}

	public void XoaTrangALL() {
		txtMaLK.setText("");
		txtTenLK.setText("");
		model_DonHang.setRowCount(0);
		txtSDT.setText("");
		txtTenKhachHang.setText("");
		txtDiaChi.setText("");
		txtTongTien.setText("");

	}
}

