package gui;

import dao.DanhMucDAO;
import dao.LinhKienDAO;
import dao.NhaCungCapDAO;
import entity.DanhMucLinhKien;
import entity.LinhKien;
import entity.NhaCungCapLinhKien;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
public class FrmLinhKien extends JPanel  implements ActionListener {
	private static final long serialVersionUID = 1L;
	private LinhKienDAO lk_dao;
	private DanhMucDAO danhMucDAO;
	private NhaCungCapDAO nhaCungCapDAO;
    private JTable table;
	private JTextField txtMaLinhKien;
	private JTextField txtTenLinhKien;
	private JTextField txtThoiGianBaoHanh;
	private JButton btnLamMoi;

	private JComboBox<String> cbxDanhMuc;
	private JComboBox<String> cbxNhaCungCap;
//	private JDateChooser dateNgayNhap;
	private JTextField txtGiaBan;
	private JTextField txtSoLuong;
	private DefaultTableModel model;

	private JButton btnThem;
	private JButton btnLuu;
	private JButton btnHuy;

	private JButton btnTim;
	private JButton btnXoa;
	private JButton btnSua;



	public FrmLinhKien() {
		//doc du lieu tu database
		lk_dao = new LinhKienDAO();
		//setsize
		setMaximumSize(new Dimension(1500, 1030));
		setMinimumSize(new Dimension(1500, 1030));
		setMaximumSize(new Dimension(1500, 1030));

		setSize(new Dimension(1550, 845));
		setLayout(null);
		/*
		Tiêu đề
		 */
		JPanel panelTitle = new JPanel();
		JLabel lblTitLe = new JLabel("THÔNG TIN LINH KIỆN");
		lblTitLe.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitLe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitLe.setFont(new Font("Arial", Font.BOLD, 25));
		lblTitLe.setForeground(Color.WHITE);

		panelTitle.add(lblTitLe);
		panelTitle.setBounds(0, 0, 1540, 60);

		add(panelTitle);


		JPanel panelThongTin = new JPanel();
		panelThongTin.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelThongTin.setBounds(0, 60, 1540, 235);
		add(panelThongTin);
		panelThongTin.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 180, 35);
		panelThongTin.add(panel);
		/*
		Nhap thông tin
		 */
		JLabel lbl_titlle_panel_1 = new JLabel("Thông tin");
		panel.add(lbl_titlle_panel_1);
		lbl_titlle_panel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_titlle_panel_1.setFont(new Font("Arial", Font.BOLD, 16));
		/*
		Mã linh kiện
		 */
		JLabel lblMaLinhKien = new JLabel("Mã linh kiện");
		lblMaLinhKien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaLinhKien.setBounds(80, 45, 100, 40);
		panelThongTin.add(lblMaLinhKien);

		txtMaLinhKien = new JTextField();
		txtMaLinhKien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaLinhKien.setBounds(190, 45, 200, 40);
		panelThongTin.add(txtMaLinhKien);
		txtMaLinhKien.setColumns(10);
		/*
		Tên linh kiện
		 */
		JLabel lblTenLinhKien = new JLabel("Tên linh kiện");
		lblTenLinhKien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenLinhKien.setBounds(430, 45, 99, 40);
		panelThongTin.add(lblTenLinhKien);

		txtTenLinhKien = new JTextField();
		txtTenLinhKien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenLinhKien.setColumns(10);
		txtTenLinhKien.setBounds(540, 45, 440, 40);
		panelThongTin.add(txtTenLinhKien);
		/*
		Nhà cung cấp
		 */
		JLabel lblNhaCungCap = new JLabel("Nhà cung cấp");
		lblNhaCungCap.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNhaCungCap.setBounds(1040, 100, 132, 40);
		panelThongTin.add(lblNhaCungCap);
		cbxNhaCungCap = new JComboBox<String>();
		cbxNhaCungCap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbxNhaCungCap.setBounds(1200, 100, 200, 40);
		cbxNhaCungCap.setEditable(false);
		cbxNhaCungCap.addItem("Tất cả");
		panelThongTin.add(cbxNhaCungCap);
		/*
		Thời gian bảo hành
		 */
		JLabel lblThoiGianBaoHanh = new JLabel("Thời gian BH(tháng)");
		lblThoiGianBaoHanh.setFont(new Font("Arial", Font.PLAIN, 16));
		lblThoiGianBaoHanh.setBounds(1040, 45, 200, 40);
		panelThongTin.add(lblThoiGianBaoHanh);

		txtThoiGianBaoHanh = new JTextField();
		txtThoiGianBaoHanh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtThoiGianBaoHanh.setColumns(10);
		txtThoiGianBaoHanh.setBounds(1200, 45, 200, 40);
		panelThongTin.add(txtThoiGianBaoHanh);
		/*
		Giá ban
		 */
		JLabel lblGiaBan = new JLabel("Giá Bán");
		lblGiaBan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGiaBan.setBounds(80, 100, 99, 40);
		panelThongTin.add(lblGiaBan);

		txtGiaBan = new JTextField();
		txtGiaBan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtGiaBan.setColumns(10);
		txtGiaBan.setBounds(190, 100, 200, 40);
		panelThongTin.add(txtGiaBan);
		/*
		Số lượng
		 */
		JLabel lblSoLuong = new JLabel("Số Lượng");
		lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSoLuong.setBounds(430, 100, 99, 40);
		panelThongTin.add(lblSoLuong);

		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(540, 100, 100, 40);
		panelThongTin.add(txtSoLuong);
		/*
		Danh mục
		 */
		JLabel lblDanhMuc = new JLabel("Danh mục");
		lblDanhMuc.setFont(new Font("Arial", Font.PLAIN, 16));
		lblDanhMuc.setBounds(685, 100, 99, 40);
		panelThongTin.add(lblDanhMuc);
		cbxDanhMuc = new JComboBox<String>();
		cbxDanhMuc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbxDanhMuc.setBounds(780, 100, 200, 39);
		cbxDanhMuc.setEditable(false);
		cbxDanhMuc.addItem("Tất cả");
		panelThongTin.add(cbxDanhMuc);
		/*
		Nút tìm kiếm
		 */
		btnTim = new JButton("Tìm kiếm");
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTim.setBounds(190, 165, 200, 50);
		panelThongTin.add(btnTim);
		//nút thêm
		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThem.setBounds(450, 165, 200, 50);
		panelThongTin.add(btnThem);
		// nút xóa
		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXoa.setBounds(700, 165, 200, 50);
		panelThongTin.add(btnXoa);
		//nút xóa rỗng
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLamMoi.setBounds(950, 165, 200, 50);
		panelThongTin.add(btnLamMoi);
		// nút cập nhật
		btnSua = new JButton("Cập nhật");
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSua.setBounds(1200, 165, 200, 50);
		panelThongTin.add(btnSua);
		/*
		TABLE
		 */
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 360, 1540, 465);
		add(scrollPane_1);
		String[] Header = {"Mã Linh Kiện", "Tên Linh Kiện", "Nhà Cung cấp", "Thời Gian BH", "Giá Bán", "Số Lượng", "Danh Mục"};
		model = new DefaultTableModel(Header, 0);
		table = new JTable(model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane_1.setViewportView(table);

		JLabel lblTieuDeTable = new JLabel("DANH SÁCH LINH KIỆN");
		lblTieuDeTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDeTable.setFont(new Font("Arial", Font.BOLD, 20));
		lblTieuDeTable.setBounds(0, 315, 1540, 45);
		add(lblTieuDeTable);

		btnLuu = new JButton("Lưu");
		btnLuu.setVisible(false);
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLuu.setBounds(450, 165, 200, 50);
		panelThongTin.add(btnLuu);

		btnHuy = new JButton("Hủy");
		btnHuy.setVisible(false);
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnHuy.setBounds(700, 165, 200, 50);
		panelThongTin.add(btnHuy);



		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
		btnHuy.addActionListener(this);
		btnLamMoi.addActionListener(this);
		//TODO: các phương thức xử lý
		//Nha cung cap
		nhaCungCapDAO = new NhaCungCapDAO();
		ArrayList<NhaCungCapLinhKien> dsNhaCungCap = nhaCungCapDAO.layThongTin();
		for (NhaCungCapLinhKien ncc : dsNhaCungCap) {
			cbxNhaCungCap.addItem(ncc.getTenNCC());
		}
		cbxNhaCungCap.setSelectedItem("Tất cả");
		//Danh muc
		danhMucDAO = new DanhMucDAO();
		ArrayList<DanhMucLinhKien> dsDanhMuc = danhMucDAO.layThongTin();
		for(DanhMucLinhKien dm : dsDanhMuc) {
			cbxDanhMuc.addItem(dm.getTenDanhMuc());
		}
		cbxDanhMuc.setSelectedItem("Tất cả");

		//Table
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				txtMaLinhKien.setText(model.getValueAt(i, 0).toString());
				txtTenLinhKien.setText(model.getValueAt(i, 1).toString());
				cbxNhaCungCap.setSelectedItem(model.getValueAt(i, 2).toString());
				txtThoiGianBaoHanh.setText(model.getValueAt(i, 3).toString());
				txtGiaBan.setText(model.getValueAt(i, 4).toString());
				txtSoLuong.setText(model.getValueAt(i, 5).toString());
				cbxDanhMuc.setSelectedItem(model.getValueAt(i, 6).toString());
				txtMaLinhKien.setEditable(false);
			}
		});
		docDuLieu();
		/*
		TODO cài đặt giao diện
		 */
		// them màu cho panel với mã màu là #94A2F2
		panelTitle.setBackground(new Color(148, 162, 242));
		// thay đổi màu của tất cả các buttom thành màu #716DF2 và chữ của các buttom thành màu trắng
		btnTim.setBackground(new Color(113, 109, 242));
		btnTim.setForeground(Color.WHITE);
		btnThem.setBackground(new Color(113, 109, 242));
		btnThem.setForeground(Color.WHITE);
		btnSua.setBackground(new Color(113, 109, 242));
		btnSua.setForeground(Color.WHITE);
		btnXoa.setBackground(new Color(113, 109, 242));
		btnXoa.setForeground(Color.WHITE);
		btnLuu.setBackground(new Color(113, 109, 242));
		btnLuu.setForeground(Color.WHITE);
		btnHuy.setBackground(new Color(113, 109, 242));
		btnHuy.setForeground(Color.WHITE);
		btnLamMoi.setBackground(new Color(113, 109, 242));
		btnLamMoi.setForeground(Color.WHITE);
		// thay   đổi màu nên các panel thành #ECF2FF
		panelThongTin.setBackground(new Color(236, 242, 255));
		// thay đổi màu của combobox thành màu #94A2F2
		cbxNhaCungCap.setBackground(new Color(148, 162, 242));
		cbxDanhMuc.setBackground(new Color(148, 162, 242));
		// thay  đổi màu chữ combobox thành màu trắng
		cbxNhaCungCap.setForeground(Color.WHITE);
		cbxDanhMuc.setForeground(Color.WHITE);
		// thay đổi màu background thành #95BDFF
		setBackground(new Color(149, 189, 255));
		lblTieuDeTable.setForeground(Color.WHITE);
		// thay đổi màu của table thành màu #ECF2FF
		table.setBackground(new Color(236, 242, 255));
		// thay  đổi màu header của table thành màu #94A2F2
		table.getTableHeader().setBackground(new Color(148, 162, 242));
		panelThongTin.setBackground(new Color(236, 242, 255));

		// thêm icon vào các buttom với đường dẫn  là src/icon
		btnTim.setIcon(new ImageIcon("src/icon/search.png"));
		btnThem.setIcon(new ImageIcon("src/icon/plus.png"));
		btnSua.setIcon(new ImageIcon("src/icon/update.png"));
		btnXoa.setIcon(new ImageIcon("src/icon/delete.png"));
		btnLuu.setIcon(new ImageIcon("src/icon/luu.png"));
		btnHuy.setIcon(new ImageIcon("src/icon/remove.png"));
		btnLamMoi.setIcon(new ImageIcon("src/icon/loading.png"));
	}

    @Override
    public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		int n = 1;
		if(o.equals(btnThem)) {
			btnTim.setVisible(false);
			btnThem.setVisible(false);
			btnSua.setVisible(false);
			btnXoa.setVisible(false);
			btnLamMoi.setVisible(false);

			btnLuu.setVisible(true);
			btnHuy.setVisible(true);
			txtMaLinhKien.setEditable(false);
			txtMaLinhKien.setText(taoMaLinhKien());

			n = 1;
		}
		if(o.equals(btnLuu)){
			if(n==1){
				if(validData()) {
					String maLinhKien = txtMaLinhKien.getText();
					String tenLinhKien = txtTenLinhKien.getText();
					ArrayList<NhaCungCapLinhKien> dsNhaCungCap = nhaCungCapDAO.layThongTin();
					NhaCungCapLinhKien nhacungcap = new NhaCungCapLinhKien();
					for (NhaCungCapLinhKien ncc1 : dsNhaCungCap) {
						if (cbxNhaCungCap.getSelectedItem().toString().equalsIgnoreCase(ncc1.getTenNCC())) {
							nhacungcap = new NhaCungCapLinhKien(ncc1.getMaNhaCungCap());
						}
					}
					int thoiGianBaoHanh = Integer.parseInt(txtThoiGianBaoHanh.getText());
					double giaBan = Double.parseDouble(txtGiaBan.getText());
					int soLuong = Integer.parseInt(txtSoLuong.getText());
					ArrayList<DanhMucLinhKien> dsDanhMuc = danhMucDAO.layThongTin();
					DanhMucLinhKien danhMuc = new DanhMucLinhKien();
					for (DanhMucLinhKien dm1 : dsDanhMuc) {
						if (cbxDanhMuc.getSelectedItem().toString().equalsIgnoreCase(dm1.getTenDanhMuc())) {
							danhMuc = new DanhMucLinhKien(dm1.getMaDanhMuc());
						}
					}
					LinhKien lk = new LinhKien(maLinhKien, tenLinhKien, soLuong, giaBan, thoiGianBaoHanh, danhMuc, nhacungcap);
					if (lk_dao.themLinhKien(lk)) {
						JOptionPane.showMessageDialog(null, "Thêm thành công");
						btnTim.setVisible(true);
						btnThem.setVisible(true);
						btnSua.setVisible(true);
						btnXoa.setVisible(true);

						btnLuu.setVisible(false);
						btnHuy.setVisible(false);
						btnLamMoi.setVisible(true);
						txtMaLinhKien.setEditable(true);
						docDuLieu();
						XoaTrang();
					}
				}
			}
		}
		if(o.equals(btnHuy)){
			btnTim.setVisible(true);
			btnThem.setVisible(true);
			btnSua.setVisible(true);
			btnXoa.setVisible(true);

			btnLuu.setVisible(false);
			btnHuy.setVisible(false);
			btnLamMoi.setVisible(true);
			txtMaLinhKien.setEditable(true);

			docDuLieu();
			XoaTrang();
		}
		if(o.equals(btnXoa)){
			int i = table.getSelectedRow();
			if(i>=0){
				String maLinhKien = model.getValueAt(i, 0).toString();
				int action = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không?", "Xóa", JOptionPane.YES_NO_OPTION);
				if(action == JOptionPane.YES_OPTION) {
					if(lk_dao.xoaLinhKien(maLinhKien)) {
						JOptionPane.showMessageDialog(null, "Xóa thành công");
						docDuLieu();
						XoaTrang();
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn linh kiện cần xóa");
			}
		}
		if(o.equals(btnLamMoi)){
			table.setRowSorter(null);
			docDuLieu();
			XoaTrang();
			txtMaLinhKien.setEditable(true);
		}
		if(o.equals(btnSua)){
			int i = table.getSelectedRow();
			if(i>=0){
				if(validData()) {
					String maLinhKien = txtMaLinhKien.getText();
					String tenLinhKien = txtTenLinhKien.getText();
					ArrayList<NhaCungCapLinhKien> dsNhaCungCap = nhaCungCapDAO.layThongTin();
					NhaCungCapLinhKien nhacungcap = new NhaCungCapLinhKien();
					for (NhaCungCapLinhKien ncc1 : dsNhaCungCap) {
						if (cbxNhaCungCap.getSelectedItem().toString().equalsIgnoreCase(ncc1.getTenNCC())) {
							nhacungcap = new NhaCungCapLinhKien(ncc1.getMaNhaCungCap());
						}
					}
					int thoiGianBaoHanh = Integer.parseInt(txtThoiGianBaoHanh.getText());
					double giaBan = Double.parseDouble(txtGiaBan.getText());
					int soLuong = Integer.parseInt(txtSoLuong.getText());
					ArrayList<DanhMucLinhKien> dsDanhMuc = danhMucDAO.layThongTin();
					DanhMucLinhKien danhMuc = new DanhMucLinhKien();
					for (DanhMucLinhKien dm1 : dsDanhMuc) {
						if (cbxDanhMuc.getSelectedItem().toString().equalsIgnoreCase(dm1.getTenDanhMuc())) {
							danhMuc = new DanhMucLinhKien(dm1.getMaDanhMuc());
						}
					}
					LinhKien lk = new LinhKien(maLinhKien, tenLinhKien, soLuong, giaBan, thoiGianBaoHanh, danhMuc, nhacungcap);
					int action = JOptionPane.showConfirmDialog(null, "Bạn có muốn cập nhật không?", "Cập nhật", JOptionPane.YES_NO_OPTION);
					if(action == JOptionPane.YES_OPTION) {
						if (lk_dao.capNhatLinhKien(lk)) {
							JOptionPane.showMessageDialog(null, "Cập nhật thành công");
							docDuLieu();
							XoaTrang();
						}
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn linh kiện cần cập nhật");
			}
		}
		if(o.equals(btnTim)) {

			String maLinhKien = txtMaLinhKien.getText();
			String tenLinhKien = txtTenLinhKien.getText();
			String nhaCungCap = cbxNhaCungCap.getSelectedItem().toString();
			String thoiGianBaoHanh = txtThoiGianBaoHanh.getText();
			String giaBan = txtGiaBan.getText();
			String soLuong = txtSoLuong.getText();
			String danhMuc = cbxDanhMuc.getSelectedItem().toString();
			if(nhaCungCap.equalsIgnoreCase("Tất cả")){
				nhaCungCap = "";
			}
			if(danhMuc.equalsIgnoreCase("Tất cả")){
				danhMuc = "";
			}
			TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
			table.setRowSorter(sorter);
			List<RowFilter<Object,Object>>filters = new ArrayList<>();
			filters.add(RowFilter.regexFilter(maLinhKien, 0));
			filters.add(RowFilter.regexFilter(tenLinhKien, 1));
			filters.add(RowFilter.regexFilter(nhaCungCap, 2));
			filters.add(RowFilter.regexFilter(thoiGianBaoHanh, 3));
			filters.add(RowFilter.regexFilter(giaBan, 4));
			filters.add(RowFilter.regexFilter(soLuong, 5));
			filters.add(RowFilter.regexFilter(danhMuc, 6));
			RowFilter<Object,Object> filter = RowFilter.andFilter(filters);
			sorter.setRowFilter(filter);


		}
    }
	public void XoaTrang() {
		txtMaLinhKien.setText("");
		txtTenLinhKien.setText("");
		cbxDanhMuc.setSelectedItem("Tất cả");
		cbxNhaCungCap.setSelectedItem("Tất cả");
		txtThoiGianBaoHanh.setText("");
		txtGiaBan.setText("");
		txtSoLuong.setText("");
		txtMaLinhKien.setEditable(true);

	}
	/*
	ĐỌC DỮ LIỆU TỪ DATABASE VÀ HIỂN THỊ LÊN TABLE
	 */
	public void docDuLieu() {
		ArrayList<LinhKien> dsLinhKien = lk_dao.layThongTin();
		ArrayList<NhaCungCapLinhKien> dsNhaCungCap = nhaCungCapDAO.layThongTin();
		ArrayList<DanhMucLinhKien> dsDanhMuc = danhMucDAO.layThongTin();
		model.setRowCount(0);
		for (LinhKien lk : dsLinhKien) {
			for (NhaCungCapLinhKien ncc : dsNhaCungCap) {
				for (DanhMucLinhKien dm : dsDanhMuc) {
					if (ncc.getMaNhaCungCap().equalsIgnoreCase(lk.getNhaCungCapLinhKien().getMaNhaCungCap())
							&& dm.getMaDanhMuc().equalsIgnoreCase(lk.getDanhMucLinhKien().getMaDanhMuc())) {
						model.addRow(new Object[]{lk.getMaLinhKien(), lk.getTenLinhKien(), ncc.getTenNCC(), lk.getThoiGianBaoHanh(), lk.getGiaBan(), lk.getSoLuong(), dm.getTenDanhMuc()});
					}
				}
			}
		}
	}
/*
TODO: Tạo mã linh kiên
 */
	public String taoMaLinhKien() {

		String maLK = "";
		double n = (Math.random()) * ((10000 - 1) + 1) + 1;
		int i = (int) n, a = 0;

		do {
			if (i < 10) {
				maLK = "LK000" + i;
			}
			if (i < 100) {
				maLK = "LK00" + i;
			}
			if (i < 1000) {
				maLK = "LK0" + i;
			}
			if (i < 10000) {
				maLK = "LK" + i;
			}


			if (!lk_dao.kiemTraMaLinhKien(maLK)) {
				a = 0;
			} else
				a = 1;
		} while (a != 0);

		return maLK;

	}
	private boolean validData() {
		if (txtTenLinhKien.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Tên linh kiện không được để trống");
			txtTenLinhKien.selectAll();
			txtTenLinhKien.requestFocus();
			return false;
		}
		if(!txtTenLinhKien.getText().matches("[a-zA-Z0-9\\s]+")) {
			JOptionPane.showMessageDialog(null, "Tên linh kiện không được chứa ký tự đặc biệt");
			txtTenLinhKien.selectAll();
			txtTenLinhKien.requestFocus();
			return false;
		}
		if (txtThoiGianBaoHanh.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Thời gian bảo hành không được để trống");
			txtThoiGianBaoHanh.selectAll();
			txtThoiGianBaoHanh.requestFocus();
			return false;
		}
		if(!txtThoiGianBaoHanh.getText().matches("[0-9]+")) {
			JOptionPane.showMessageDialog(null, "Thời gian bảo hành phải là số nguyên");
			txtThoiGianBaoHanh.selectAll();
			txtThoiGianBaoHanh.requestFocus();
			return false;
		}
		if (txtGiaBan.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Giá bán không được để trống");
			txtGiaBan.selectAll();
			txtGiaBan.requestFocus();
			return false;
		}
		if(!txtGiaBan.getText().matches("^[1-9]\\d*(\\.\\d+)?$")) {
			JOptionPane.showMessageDialog(null, "Giá bán phải là số nguyên. Ví dụ: 10000.00");
			txtGiaBan.selectAll();
			txtGiaBan.requestFocus();
			return false;
		}
		if (txtSoLuong.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Số lượng không được để trống");
			txtSoLuong.selectAll();
			txtSoLuong.requestFocus();
			return false;
		}
		if(!txtSoLuong.getText().matches("[0-9]+")) {
			JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên.");
			txtSoLuong.selectAll();
			txtSoLuong.requestFocus();
			return false;
		}
		if(cbxNhaCungCap.getSelectedItem().toString().equalsIgnoreCase("Tất cả")) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp");
			return false;
		}
		if(cbxDanhMuc.getSelectedItem().toString().equalsIgnoreCase("Tất cả")) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn danh mục");
			return false;
		}
		return true;
	}

}
