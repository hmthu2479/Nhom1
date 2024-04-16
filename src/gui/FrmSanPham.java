package gui;

import dao.DanhMucDAO;
import dao.SanPhamDAO;
import dao.NhaCungCapDAO;
import entity.DanhMucSanPham;
import entity.SanPham;
import entity.NhaCungCapSanPham;

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
public class FrmSanPham extends JPanel  implements ActionListener {
	private static final long serialVersionUID = 1L;
	private SanPhamDAO sp_dao;
	private DanhMucDAO danhMucDAO;
	private NhaCungCapDAO nhaCungCapDAO;
    private JTable table;
	private JTextField txtMaSanPham;
	private JTextField txtTenSanPham;
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
	private JTextField txtKe;



	public FrmSanPham() {
		//doc du lieu tu database
		sp_dao = new SanPhamDAO();
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
		JLabel lblTitLe = new JLabel("THÔNG TIN SẢN PHẨM");
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
		Mã sản phẩm
		 */
		JLabel lblMaSanPham = new JLabel("Mã sản phẩm:");
		lblMaSanPham.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaSanPham.setBounds(80, 45, 120, 40);
		panelThongTin.add(lblMaSanPham);

		txtMaSanPham = new JTextField();
		txtMaSanPham.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaSanPham.setBounds(190, 45, 200, 40);
		panelThongTin.add(txtMaSanPham);
		txtMaSanPham.setColumns(10);
		/*
		Tên sản phẩm
		 */
		JLabel lblTenSanPham = new JLabel("Tên sản phẩm:");
		lblTenSanPham.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenSanPham.setBounds(430, 45, 120, 40);
		panelThongTin.add(lblTenSanPham);

		txtTenSanPham = new JTextField();
		txtTenSanPham.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenSanPham.setColumns(10);
		txtTenSanPham.setBounds(540, 45, 240, 40);
		panelThongTin.add(txtTenSanPham);
		/*
		Tên sản phẩm
		 */
		JLabel lblKe = new JLabel("Thuộc kệ:");
		lblKe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblKe.setBounds(830, 45, 120, 40);
		panelThongTin.add(lblKe);

		txtKe = new JTextField();
		txtKe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtKe.setColumns(10);
		txtKe.setBounds(910, 45, 240, 40);
		panelThongTin.add(txtKe);
		/*
		Nhà cung cấp
		 */
		JLabel lblNhaCungCap = new JLabel("Nhà cung cấp:");
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
		Giá ban
		 */
		JLabel lblGiaBan = new JLabel("Giá Bán:");
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
		JLabel lblSoLuong = new JLabel("Số Lượng:");
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
		JLabel lblDanhMuc = new JLabel("Danh mục:");
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
		String[] Header = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Nhà Cung cấp", "Giá Bán", "Số Lượng", "Danh Mục", "Thuộc kệ"};
		model = new DefaultTableModel(Header, 0);
		table = new JTable(model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane_1.setViewportView(table);

		JLabel lblTieuDeTable = new JLabel("DANH SÁCH SẢN PHẨM");
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
		ArrayList<NhaCungCapSanPham> dsNhaCungCap = nhaCungCapDAO.layThongTin();
		for (NhaCungCapSanPham ncc : dsNhaCungCap) {
			cbxNhaCungCap.addItem(ncc.getTenNCC());
		}
		cbxNhaCungCap.setSelectedItem("Tất cả");
		//Danh muc
		danhMucDAO = new DanhMucDAO();
		ArrayList<DanhMucSanPham> dsDanhMuc = danhMucDAO.layThongTin();
		for(DanhMucSanPham dm : dsDanhMuc) {
			cbxDanhMuc.addItem(dm.getTenDanhMuc());
		}
		cbxDanhMuc.setSelectedItem("Tất cả");

		//Table
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				txtMaSanPham.setText(model.getValueAt(i, 0).toString());
				txtTenSanPham.setText(model.getValueAt(i, 1).toString());
				cbxNhaCungCap.setSelectedItem(model.getValueAt(i, 2).toString());
				txtGiaBan.setText(model.getValueAt(i, 4).toString());
				txtSoLuong.setText(model.getValueAt(i, 5).toString());
				cbxDanhMuc.setSelectedItem(model.getValueAt(i, 6).toString());
				txtMaSanPham.setEditable(false);
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


			n = 1;
		}
		if(o.equals(btnLuu)){
			if(n==1){
				if(validData()) {
					String maSanPham = txtMaSanPham.getText();
					String tenSanPham = txtTenSanPham.getText();
					String ke = txtKe.getText();
					ArrayList<NhaCungCapSanPham> dsNhaCungCap = nhaCungCapDAO.layThongTin();
					NhaCungCapSanPham nhacungcap = new NhaCungCapSanPham();
					for (NhaCungCapSanPham ncc1 : dsNhaCungCap) {
						if (cbxNhaCungCap.getSelectedItem().toString().equalsIgnoreCase(ncc1.getTenNCC())) {
							nhacungcap = new NhaCungCapSanPham(ncc1.getMaNhaCungCap());
						}
					}
					double giaBan = Double.parseDouble(txtGiaBan.getText());
					int soLuong = Integer.parseInt(txtSoLuong.getText());
					ArrayList<DanhMucSanPham> dsDanhMuc = danhMucDAO.layThongTin();
					DanhMucSanPham danhMuc = new DanhMucSanPham();
					for (DanhMucSanPham dm1 : dsDanhMuc) {
						if (cbxDanhMuc.getSelectedItem().toString().equalsIgnoreCase(dm1.getTenDanhMuc())) {
							danhMuc = new DanhMucSanPham(dm1.getMaDanhMuc());
						}
					}
					SanPham sp = new SanPham(maSanPham, tenSanPham, soLuong, giaBan, danhMuc, nhacungcap,ke);
					if (sp_dao.themSanPham(sp)) {
						JOptionPane.showMessageDialog(null, "Thêm thành công");
						btnTim.setVisible(true);
						btnThem.setVisible(true);
						btnSua.setVisible(true);
						btnXoa.setVisible(true);

						btnLuu.setVisible(false);
						btnHuy.setVisible(false);
						btnLamMoi.setVisible(true);

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
			txtMaSanPham.setEditable(true);

			docDuLieu();
			XoaTrang();
		}
		if(o.equals(btnXoa)){
			int i = table.getSelectedRow();
			if(i>=0){
				String maSanPham = model.getValueAt(i, 0).toString();
				int action = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không?", "Xóa", JOptionPane.YES_NO_OPTION);
				if(action == JOptionPane.YES_OPTION) {
					if(sp_dao.xoaSanPham(maSanPham)) {
						JOptionPane.showMessageDialog(null, "Xóa thành công");
						docDuLieu();
						XoaTrang();
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần xóa");
			}
		}
		if(o.equals(btnLamMoi)){
			table.setRowSorter(null);
			docDuLieu();
			XoaTrang();
			txtMaSanPham.setEditable(true);
		}
		if(o.equals(btnSua)){
			int i = table.getSelectedRow();
			if(i>=0){
				if(validData()) {
					String maSanPham = txtMaSanPham.getText();
					String tenSanPham = txtTenSanPham.getText();
					String ke = txtKe.getText();
					ArrayList<NhaCungCapSanPham> dsNhaCungCap = nhaCungCapDAO.layThongTin();
					NhaCungCapSanPham nhacungcap = new NhaCungCapSanPham();
					for (NhaCungCapSanPham ncc1 : dsNhaCungCap) {
						if (cbxNhaCungCap.getSelectedItem().toString().equalsIgnoreCase(ncc1.getTenNCC())) {
							nhacungcap = new NhaCungCapSanPham(ncc1.getMaNhaCungCap());
						}
					}
					double giaBan = Double.parseDouble(txtGiaBan.getText());
					int soLuong = Integer.parseInt(txtSoLuong.getText());
					ArrayList<DanhMucSanPham> dsDanhMuc = danhMucDAO.layThongTin();
					DanhMucSanPham danhMuc = new DanhMucSanPham();
					for (DanhMucSanPham dm1 : dsDanhMuc) {
						if (cbxDanhMuc.getSelectedItem().toString().equalsIgnoreCase(dm1.getTenDanhMuc())) {
							danhMuc = new DanhMucSanPham(dm1.getMaDanhMuc());
						}
					}
					SanPham sp = new SanPham(maSanPham, tenSanPham, soLuong, giaBan, danhMuc, nhacungcap,ke);
					int action = JOptionPane.showConfirmDialog(null, "Bạn có muốn cập nhật không?", "Cập nhật", JOptionPane.YES_NO_OPTION);
					if(action == JOptionPane.YES_OPTION) {
						if (sp_dao.capNhatSanPham(sp)) {
							JOptionPane.showMessageDialog(null, "Cập nhật thành công");
							docDuLieu();
							XoaTrang();
						}
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần cập nhật");
			}
		}
		if(o.equals(btnTim)) {

			String maSanPham = txtMaSanPham.getText();
			String tenSanPham = txtTenSanPham.getText();
			String nhaCungCap = cbxNhaCungCap.getSelectedItem().toString();
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
			filters.add(RowFilter.regexFilter(maSanPham, 0));
			filters.add(RowFilter.regexFilter(tenSanPham, 1));
			filters.add(RowFilter.regexFilter(nhaCungCap, 2));
			filters.add(RowFilter.regexFilter(giaBan, 4));
			filters.add(RowFilter.regexFilter(soLuong, 5));
			filters.add(RowFilter.regexFilter(danhMuc, 6));
			RowFilter<Object,Object> filter = RowFilter.andFilter(filters);
			sorter.setRowFilter(filter);


		}
    }
	public void XoaTrang() {
		txtMaSanPham.setText("");
		txtTenSanPham.setText("");
		cbxDanhMuc.setSelectedItem("Tất cả");
		cbxNhaCungCap.setSelectedItem("Tất cả");
		txtGiaBan.setText("");
		txtSoLuong.setText("");
		txtMaSanPham.setEditable(true);

	}
	/*
	ĐỌC DỮ LIỆU TỪ DATABASE VÀ HIỂN THỊ LÊN TABLE
	 */
	public void docDuLieu() {
		ArrayList<SanPham> dsSanPham = sp_dao.layThongTin();
		ArrayList<NhaCungCapSanPham> dsNhaCungCap = nhaCungCapDAO.layThongTin();
		ArrayList<DanhMucSanPham> dsDanhMuc = danhMucDAO.layThongTin();
		model.setRowCount(0);
		for (SanPham sp : dsSanPham) {
			for (NhaCungCapSanPham ncc : dsNhaCungCap) {
				for (DanhMucSanPham dm : dsDanhMuc) {
					if (ncc.getMaNhaCungCap().equalsIgnoreCase(sp.getNhaCungCapSanPham().getMaNhaCungCap())
							&& dm.getMaDanhMuc().equalsIgnoreCase(sp.getDanhMucSanPham().getMaDanhMuc())) {
						model.addRow(new Object[]{sp.getMaSanPham(), sp.getTenSanPham(), ncc.getTenNCC(), sp.getGiaBan(), sp.getSoLuong(), dm.getTenDanhMuc(),sp.getKe()});
					}
				}
			}
		}
	}
	
	private boolean validData() {
		if (txtTenSanPham.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Tên sản phẩm không được để trống");
			txtTenSanPham.selectAll();
			txtTenSanPham.requestFocus();
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
