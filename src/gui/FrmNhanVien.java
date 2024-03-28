package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import com.toedter.calendar.JDateChooser;

import dao.NhanVienDAO;
import entity.NhanVien;

public class FrmNhanVien extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JTextField txtEmail;
	private JTable tblNhanVien;
	private JTextField txtMaNhanVien;
	private JTextField txtHoTen;
	private JTextField txtSDT;
	private JComboBox cbxDiaChi;
	private JDateChooser dateNgaySinh;
	private DefaultTableModel model;
	private JButton btnThem, btnXoa, btnSua, btnLuu, btnHuy;
			private JButton btnLamMoi;
	private JButton btnTim;
	int n = 0;
	private NhanVienDAO nv_Dao;


	/**
	 * Create the panel.
	 */

	public FrmNhanVien () {
		setMaximumSize(new Dimension(1500, 1030));
		setMinimumSize(new Dimension(1500, 1030));
		setMaximumSize(new Dimension(1500, 1030));

		/**
		 *
		 */

		nv_Dao = new NhanVienDAO();
		setSize(new Dimension(1550, 845));
		setLayout(null);
		JPanel panelTitle = new JPanel();

		panelTitle.setBounds(0, 0, 1540, 60);
		JLabel lblTitLe = new JLabel("THÔNG TIN NHÂN VIÊN");
		lblTitLe.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitLe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitLe.setFont(new Font("Arial", Font.BOLD, 25));
		lblTitLe.setBounds(0, 10, 1540, 49);
		panelTitle.add(lblTitLe);
		add(panelTitle);
		panelTitle.setBackground(new Color(148, 162, 242));

		JPanel panelThongTin = new JPanel();
		panelThongTin.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelThongTin.setBounds(0, 57, 1540, 240);
		add(panelThongTin);
		panelThongTin.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 178, 33);
		panelThongTin.add(panel);

		JLabel lblTiTlePanel = new JLabel("Thông tin");
		panel.add(lblTiTlePanel);
		lblTiTlePanel.setHorizontalAlignment(SwingConstants.CENTER);
		lblTiTlePanel.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel lblMaNhanVien = new JLabel("Mã Nhân Viên");
		lblMaNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaNhanVien.setBounds(150, 45, 200, 40);
		panelThongTin.add(lblMaNhanVien);

		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaNhanVien.setBounds(290, 45, 200, 40);
		panelThongTin.add(txtMaNhanVien);
		txtMaNhanVien.setColumns(10);

		JLabel lblHoTen = new JLabel("Họ Và Tên");
		lblHoTen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHoTen.setBounds(600, 45, 99, 40);
		panelThongTin.add(lblHoTen);

		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(680, 45, 203, 40);
		panelThongTin.add(txtHoTen);

		JLabel lblSDT = new JLabel("Số Điện Thoại");
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSDT.setBounds(1000, 45, 132, 40);
		panelThongTin.add(lblSDT);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSDT.setColumns(10);
		txtSDT.setBounds(1150, 45, 200, 40);
		panelThongTin.add(txtSDT);

		JLabel lblNgaySinh = new JLabel("Ngày Sinh");
		lblNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNgaySinh.setBounds(1000, 100, 99, 40);
		panelThongTin.add(lblNgaySinh);

		dateNgaySinh = new JDateChooser();
		panelThongTin.add(dateNgaySinh);
		dateNgaySinh.setDateFormatString("dd-MM-yyyy");
		dateNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dateNgaySinh.setBounds(1150, 100, 200, 40);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(150, 100, 200, 40);
		panelThongTin.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEmail.setColumns(10);
		txtEmail.setBounds(290, 95, 200, 40);
		panelThongTin.add(txtEmail);

		JLabel lblDiaChi = new JLabel("Địa Chỉ");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDiaChi.setBounds(600, 100, 87, 40);
		panelThongTin.add(lblDiaChi);

		cbxDiaChi = new JComboBox<String>();
		cbxDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		// tạo 1 danh sách địa chỉ các tỉnh của việt nam lưu và combobox
		cbxDiaChi.setModel(new DefaultComboBoxModel<String>(new String[] { "Tất cả","Hà Nội", "Hồ Chí Minh", "Đà Nẵng",
				"Hải Phòng", "Cần Thơ", "An Giang", "Bà Rịa - Vũng Tàu", "Bắc Giang", "Bắc Kạn", "Bạc Liêu",
				"Bắc Ninh", "Bến Tre", "Bình Định", "Bình Dương", "Bình Phước", "Bình Thuận", "Cà Mau", "Cao Bằng",
				"Đắk Lắk", "Đắk Nông", "Điện Biên", "Đồng Nai", "Đồng Tháp", "Gia Lai", "Hà Giang", "Hà Nam",
				"Hà Tĩnh", "Hải Dương", "Hậu Giang", "Hòa Bình", "Hưng Yên", "Khánh Hòa", "Kiên Giang", "Kon Tum",
				"Lai Châu", "Lâm Đồng", "Lạng Sơn", "Lào Cai", "Long An", "Nam Định", "Nghệ An", "Ninh Bình",
				"Ninh Thuận", "Phú Thọ", "Quảng Bình", "Quảng Nam", "Quảng Ngãi", "Quảng Ninh", "Quảng Trị",
				"Sóc Trăng", "Sơn La", "Tây Ninh", "Thái Bình", "Thái Nguyên", "Thanh Hóa", "Thừa Thiên Huế",
				"Tiền Giang", "Trà Vinh", "Tuyên Quang", "Vĩnh Long", "Vĩnh Phúc", "Yên Bái" }));
		cbxDiaChi.setBounds(680, 100, 200, 40);
		panelThongTin.add(cbxDiaChi);

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

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 338, 1540, 485);
		add(scrollPane_1);
		String[] Header = { "Mã nhân viên", "Họ tên", "Địa chỉ", "Ngày Sinh", "Số điện thoại","Email" };
		model = new DefaultTableModel(Header, 0);
		tblNhanVien = new JTable(model);
		tblNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tblNhanVien.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane_1.setViewportView(tblNhanVien);
		tblNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblNhanVien.getSelectedRow();
				txtMaNhanVien.setText(model.getValueAt(row, 0).toString());
				txtHoTen.setText(model.getValueAt(row, 1).toString());
				txtSDT.setText(model.getValueAt(row, 4).toString());
				dateNgaySinh.setDate((java.util.Date) model.getValueAt(row, 3));
				cbxDiaChi.setSelectedItem(model.getValueAt(row, 2).toString());
				txtEmail.setText(model.getValueAt(row, 5).toString());
				txtMaNhanVien.setEditable(false);
			}
		});

		docDuLieu();
		JLabel lblTiTleTable = new JLabel("DANH SÁCH NHÂN VIÊN");
		lblTiTleTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblTiTleTable.setFont(new Font("Arial", Font.BOLD, 20));
		lblTiTleTable.setBounds(0, 307, 1540, 21);
		add(lblTiTleTable);

		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
		btnHuy.addActionListener(this);
		btnLamMoi.addActionListener(this);

		/*
		TODO cài đặt giao diện
		 */
		// them màu cho panel với mã màu là #94A2F2

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
		// thay đổi màu combobox thành màu #95BDFF
		cbxDiaChi.setBackground(new Color(148, 162, 242));
		cbxDiaChi.setForeground(Color.WHITE);
		// thay   đổi màu nên các panel thành #ECF2FF
		panelThongTin.setBackground(new Color(236, 242, 255));
		lblTiTleTable.setForeground(Color.WHITE);
		// thay đổi màu background thành #95BDFF
		setBackground(new Color(149, 189, 255));
		lblTitLe.setForeground(Color.WHITE);
		// thay đổi màu của table thành màu #ECF2FF
		tblNhanVien.setBackground(new Color(236, 242, 255));
		// thay  đổi màu header của table thành màu #94A2F2
		tblNhanVien.getTableHeader().setBackground(new Color(148, 162, 242));

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
		if(o.equals(btnThem)){
			btnTim.setVisible(false);
			btnThem.setVisible(false);
			btnSua.setVisible(false);
			btnXoa.setVisible(false);
			btnLamMoi.setVisible(false);

			btnLuu.setVisible(true);
			btnHuy.setVisible(true);
			txtMaNhanVien.setEditable(false);
			txtMaNhanVien.setText(taoMaNV());
			n=1;
		}
		if(o.equals(btnLuu)) {
			if (n == 1) {
				if (validData()) {
					String maNV = txtMaNhanVien.getText();
					String hoTen = txtHoTen.getText();
					String soDT = txtSDT.getText();
					Date ngaySinh = dateNgaySinh.getDate();
					String diaChi = cbxDiaChi.getSelectedItem().toString();
					String email = txtEmail.getText();
					NhanVien nv = new NhanVien(maNV, hoTen, ngaySinh, soDT, diaChi, email);
					if (nv_Dao.themNhanVien(nv)) {
						JOptionPane.showMessageDialog(null, "Thêm thành công");
						btnTim.setVisible(true);
						btnThem.setVisible(true);
						btnSua.setVisible(true);
						btnXoa.setVisible(true);

						btnLuu.setVisible(false);
						btnHuy.setVisible(false);
						btnLamMoi.setVisible(true);
						txtMaNhanVien.setEditable(true);
						docDuLieu();
						xoaTrang();
					}
				}
			}
		}
			if (o.equals(btnHuy)) {
				btnTim.setVisible(true);
				btnThem.setVisible(true);
				btnSua.setVisible(true);
				btnXoa.setVisible(true);

				btnLuu.setVisible(false);
				btnHuy.setVisible(false);
				btnLamMoi.setVisible(true);
				txtMaNhanVien.setEditable(true);

				docDuLieu();
				xoaTrang();
			}
			if(o.equals(btnLamMoi)){
				tblNhanVien.setRowSorter(null);
				docDuLieu();
				xoaTrang();
			}
			if(o.equals(btnSua)){
				int row = tblNhanVien.getSelectedRow();
				if(row == -1){
					JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần cập nhật");
					return;
				}else {
					String maNV = txtMaNhanVien.getText().toString();
					if (validData()) {
						String hoTen = txtHoTen.getText();
						String soDT = txtSDT.getText();
						Date ngaySinh = dateNgaySinh.getDate();
						String diaChi = cbxDiaChi.getSelectedItem().toString();
						String email = txtEmail.getText();
						NhanVien nv = new NhanVien(maNV, hoTen, ngaySinh, soDT, diaChi, email);
						int action = JOptionPane.showConfirmDialog(null, "Bạn có muốn cập nhật nhân viên này không?", "Cập nhật nhân viên", JOptionPane.YES_NO_OPTION);
						if(action == JOptionPane.YES_OPTION) {
							if (nv_Dao.capNhatNhanVien(nv)) {
								JOptionPane.showMessageDialog(null, "Cập nhật thành công");
								docDuLieu();
								xoaTrang();
							}
						}
					}
				}
			}
			if(o.equals(btnXoa)){
				int row = tblNhanVien.getSelectedRow();
				if(row == -1){
					JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần xóa");
					return;
				}else {
					String maNV = txtMaNhanVien.getText().toString();
					int action = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa nhân viên này không?", "Xóa nhân viên", JOptionPane.YES_NO_OPTION);
					if(action == JOptionPane.YES_OPTION) {
						if (nv_Dao.xoaNhanVien(maNV)) {
							JOptionPane.showMessageDialog(null, "Xóa thành công");
							docDuLieu();
							xoaTrang();
						}
					}
				}
			}
			if(o.equals(btnTim)){
				String maNV = txtMaNhanVien.getText();
				String hoTen = txtHoTen.getText();
				String soDT = txtSDT.getText();
				String diaChi = cbxDiaChi.getSelectedItem().toString();
				String email = txtEmail.getText();
				String ngaySinh;
				if(diaChi.equalsIgnoreCase("Tất cả")){
					diaChi = "";
				}
				if (dateNgaySinh.getDate() == null) {
					ngaySinh = "";
				} else {
					ngaySinh = dateNgaySinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
				}
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
				tblNhanVien.setRowSorter(sorter);
				List<RowFilter<Object,Object>>filters = new ArrayList<>();
				filters.add(RowFilter.regexFilter(maNV, 0));
				filters.add(RowFilter.regexFilter(hoTen, 1));
				filters.add(RowFilter.regexFilter(diaChi, 2));
				filters.add(RowFilter.regexFilter(ngaySinh, 3));
				filters.add(RowFilter.regexFilter(soDT, 4));
				filters.add(RowFilter.regexFilter(email, 5));
			RowFilter<Object,Object> filter = RowFilter.andFilter(filters);
			sorter.setRowFilter(filter);
			}

    }
	// đọc dữ liệu
	public void docDuLieu() {
		ArrayList<NhanVien> dsNV = nv_Dao.layThongTin();
		model.setRowCount(0);
		for(NhanVien nv : dsNV){
			model.addRow(new Object[] {nv.getMaNhanVien(), nv.getHoTen(), nv.getSoDT(), nv.getNgaySinh(), nv.getDiaChi(), nv.geteMail()});
		}

	}
	// xóa trắng
	public void xoaTrang() {
		txtMaNhanVien.setText("");
		txtHoTen.setText("");
		txtSDT.setText("");
		dateNgaySinh.setDate(null);
		cbxDiaChi.setSelectedIndex(0);
		txtEmail.setText("");
		txtMaNhanVien.setEditable(true);
	}
	//Hàm tạo mã nhân viên tự động
	public String taoMaNV() {
		String maNV = "";
		double n = (Math.random()) * ((10000 - 1) + 1) + 1;
		int i = (int) n, a = 0;

		do {
			if (i < 10) {
				maNV = "NV000" + i;
			}
			if (i < 100) {
				maNV = "NV00" + i;
			}
			if (i < 1000) {
				maNV = "NV0" + i;
			}
			if (i < 10000) {
				maNV = "NV" + i;
			}


			if (!nv_Dao.kiemTraMaNV(maNV)) {
				a = 0;
			} else
				a = 1;
		} while (a != 0);

		return maNV;
	}
	// kiểm tra dữ liệu nhập vào
	private boolean validData(){
		if(txtHoTen.getText().equals("")){
			JOptionPane.showMessageDialog(this, "Họ tên không được để trống");
			txtHoTen.requestFocus();
			return false;
		}
		if(txtSDT.getText().equals("")){
			JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống");
			txtSDT.requestFocus();
			return false;
		}
		if(txtEmail.getText().equals("")){
			JOptionPane.showMessageDialog(this, "Email không được để trống");
			txtEmail.requestFocus();
			return false;
		}
		if(dateNgaySinh.getDate()==null){
			JOptionPane.showMessageDialog(this, "Ngày sinh không được để trống");
			dateNgaySinh.requestFocus();
			return false;
		}
		// kiểm tra định dạng tên không được chứa kỳ tự đặc biệt
		if(!txtHoTen.getText().matches("[a-zA-Z\\s]+")){
			JOptionPane.showMessageDialog(this, "Họ tên không được chứa ký tự đặc biệt");
			txtHoTen.requestFocus();
			return false;
		}
		// kiểm tra định dạng email
		if(!txtEmail.getText().matches("\\w+@\\w+(\\.\\w+){1,2}")){
			JOptionPane.showMessageDialog(this, "Email phải có định dạng là xx@gmail.com");
			txtEmail.requestFocus();
			return false;
		}
		// ngày sinh tính tới hiện tại phải lớn hơn 18 tuổi
		if(dateNgaySinh.getDate().getYear()+1900+18>new Date().getYear()+1900){
			JOptionPane.showMessageDialog(this, "Nhân viên phải lớn hơn 18 tuổi");
			dateNgaySinh.requestFocus();
			return false;
		}
		// kiểm tra định dạng số điện thoại
		if(!txtSDT.getText().matches("0\\d{9}")){
			JOptionPane.showMessageDialog(this, "Số điện thoại phải có định dạng là 10 số 0xxxx");
			txtSDT.requestFocus();
			return false;
		}
		// địa chỉ không được chọn tất cả
		if(cbxDiaChi.getSelectedItem().toString().equalsIgnoreCase("Tất cả")){
			JOptionPane.showMessageDialog(this, "Bạn phải chọn địa chỉ");
			cbxDiaChi.requestFocus();
			return false;
		}
		return true;
	}
}