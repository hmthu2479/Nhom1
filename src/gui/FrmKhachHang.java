package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.KhachHangDAO;

import entity.KhachHang;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class FrmKhachHang extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtMaKH;
	private JTextField txtTenKH;
	private JTextField txtSoDT;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	private JTable table;
	private JButton btnLamMoi;
	private DefaultTableModel model;

	private JButton btnThem;
	private JButton btnLuu;
	private JButton btnHuy;

	private JButton btnTim;
	private JButton btnXoa;
	private JButton btnSua;

	private KhachHangDAO kh_dao = new KhachHangDAO();
	private List<KhachHang> khachHangs = new ArrayList<KhachHang>();

	public FrmKhachHang() {

		kh_dao = new KhachHangDAO();
		//Thiết lập layout
		setMaximumSize(new Dimension(1500, 1030));
		setMinimumSize(new Dimension(1500, 1030));
		setMaximumSize(new Dimension(1500, 1030));
		setSize(new Dimension(1550, 845));
		setLayout(null);

		// thiết lập các thành phần GUI

		JPanel panelTitle = new JPanel();
		JLabel lblTitLe = new JLabel("THÔNG TIN KHÁCH HÀNG");
		lblTitLe.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitLe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitLe.setFont(new Font("Arial", Font.BOLD, 25));
		lblTitLe.setForeground(Color.WHITE);
		panelTitle.add(lblTitLe);
		panelTitle.setBounds(0, 0, 1540, 60);
		panelTitle.setBackground(Color.RED);
		add(panelTitle);
		panelTitle.setBackground(new Color(148, 162, 242));


		JPanel panelKhachHang = new JPanel();
		panelKhachHang.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelKhachHang.setBounds(0, 57, 1540, 237);
		add(panelKhachHang);
		panelKhachHang.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 178, 33);
		panelKhachHang.add(panel);

		JLabel lbl_titlle_panel_1 = new JLabel("Thông tin khách hàng");
		panel.add(lbl_titlle_panel_1);
		lbl_titlle_panel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_titlle_panel_1.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel lblMaKH = new JLabel("Mã Khách Hàng");
		lblMaKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaKH.setBounds(150, 45, 200, 40);
		panelKhachHang.add(lblMaKH);

		txtMaKH = new JTextField();
		txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaKH.setBounds(290, 45, 200, 40);
		panelKhachHang.add(txtMaKH);
		txtMaKH.setColumns(10);

		JLabel lblTenKH = new JLabel("Họ Tên ");
		lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenKH.setBounds(600, 45, 99, 40);
		panelKhachHang.add(lblTenKH);

		txtTenKH = new JTextField();
		txtTenKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(680, 45, 200, 40);
		panelKhachHang.add(txtTenKH);

		JLabel lblSoDT = new JLabel("Số Điện Thoại");
		lblSoDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSoDT.setBounds(1000, 45, 132, 40);
		panelKhachHang.add(lblSoDT);

		txtSoDT = new JTextField();
		txtSoDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoDT.setColumns(10);
		txtSoDT.setBounds(1150, 45, 200, 40);
		panelKhachHang.add(txtSoDT);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(150, 100, 200, 40);
		panelKhachHang.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEmail.setColumns(10);
		txtEmail.setBounds(290, 100, 200, 40);
		panelKhachHang.add(txtEmail);


		JLabel lblDiaChi = new JLabel("Địa Chỉ");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDiaChi.setBounds(600, 100, 99, 40);
		panelKhachHang.add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(680, 100, 300, 40);
		panelKhachHang.add(txtDiaChi);


		btnTim = new JButton("Tìm kiếm");
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTim.setBounds(200, 165, 200, 50);
		panelKhachHang.add(btnTim);
		//nút thêm
		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThem.setBounds(450, 165, 200, 50);
		panelKhachHang.add(btnThem);
		// nút xóa
		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXoa.setBounds(700, 165, 200, 50);
		panelKhachHang.add(btnXoa);
		//nút xóa rỗng
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLamMoi.setBounds(950, 165, 200, 50);
		panelKhachHang.add(btnLamMoi);
		// nút cập nhật
		btnSua = new JButton("Cập nhật");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSua.setBounds(1200, 165, 200, 50);
		panelKhachHang.add(btnSua);


		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 359, 1540, 464);
		add(scrollPane_1);
		String[] Header = {"Mã Khách Hàng", "Tên Khách hàng", "Số Điện Thoại", "Email", "Địa Chỉ"};
		model = new DefaultTableModel(Header, 0);
		table = new JTable(model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane_1.setViewportView(table);

		JLabel lblTitle_Table = new JLabel("DANH SÁCH KHÁCH HÀNG");
		lblTitle_Table.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle_Table.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitle_Table.setBounds(0, 315, 1540, 45);
		add(lblTitle_Table);

		btnLuu = new JButton("Lưu");
		btnLuu.setVisible(false);
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLuu.setBounds(450, 165, 200, 50);
		panelKhachHang.add(btnLuu);

		btnHuy = new JButton("Hủy");
		btnHuy.setVisible(false);
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnHuy.setBounds(700, 165, 200, 50);
		panelKhachHang.add(btnHuy);


		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		;
		btnLuu.addActionListener(this);
		btnHuy.addActionListener(this);
		btnLamMoi.addActionListener(this);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				txtMaKH.setText(model.getValueAt(i, 0).toString());
				txtTenKH.setText(model.getValueAt(i, 1).toString());
				txtSoDT.setText(model.getValueAt(i, 2).toString());
				txtEmail.setText(model.getValueAt(i, 3).toString());
				txtDiaChi.setText(model.getValueAt(i, 4).toString());
				txtMaKH.setEditable(false);
			}
		});
		docDuLieu();

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
		// thay   đổi màu nên các panel thành #ECF2FF
		panelKhachHang.setBackground(new Color(236, 242, 255));
		lblTitle_Table.setForeground(Color.WHITE);
		// thay đổi màu background thành #95BDFF
		setBackground(new Color(149, 189, 255));
		lblTitLe.setForeground(Color.WHITE);
		// thay đổi màu của table thành màu #ECF2FF
		table.setBackground(new Color(236, 242, 255));
		// thay  đổi màu header của table thành màu #94A2F2
		table.getTableHeader().setBackground(new Color(148, 162, 242));
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

		// TODO Auto-generated method stub
		Object o = e.getSource();
		int n = 1;
		if (o.equals(btnThem)) {
			btnTim.setVisible(false);
			btnThem.setVisible(false);
			btnSua.setVisible(false);
			btnXoa.setVisible(false);
			btnLamMoi.setVisible(false);

			btnLuu.setVisible(true);
			btnHuy.setVisible(true);
			txtMaKH.setEditable(false);
			txtMaKH.setText(taoMaKH());
			n = 1;
		}
		if (o.equals(btnLuu)) {
			if (n == 1) {
				if(validData()){
					String maKH = txtMaKH.getText();
					String tenKH = txtTenKH.getText();
					String soDT = txtSoDT.getText();
					String email = txtEmail.getText();
					String diaChi = txtDiaChi.getText();
					KhachHang kh = new KhachHang(maKH, tenKH, soDT, email, diaChi);
					kh_dao = new KhachHangDAO();
					if(kh_dao.themKhachHang(kh)) {
						JOptionPane.showMessageDialog(null, "Thêm thành công");
						btnTim.setVisible(true);
						btnThem.setVisible(true);
						btnSua.setVisible(true);
						btnXoa.setVisible(true);

						btnLuu.setVisible(false);
						btnHuy.setVisible(false);
						btnLamMoi.setVisible(true);
						txtMaKH.setEditable(true);
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
			txtMaKH.setEditable(true);
			XoaTrang();
		}
		if(o.equals(btnXoa)) {
			int i = table.getSelectedRow();
			if(i>=0) {
				int action = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không?", "Xóa", JOptionPane.YES_NO_OPTION);
				String maKH = model.getValueAt(i, 0).toString();
				if(action == JOptionPane.YES_OPTION) {
					if (kh_dao.xoaKhachHang(maKH)) {
						JOptionPane.showMessageDialog(null, "Xóa thành công");
						docDuLieu();
						XoaTrang();
					}
				}
			}else JOptionPane.showMessageDialog(null, "Chọn khách hàng cần xóa");
		}
		if(o.equals(btnSua)) {
			int i = table.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "Chọn khách hàng cần cập nhật");
				return;
			}else {
				if(validData()) {
					String maKH = txtMaKH.getText();
					String tenKH = txtTenKH.getText();
					String soDT = txtSoDT.getText();
					String email = txtEmail.getText();
					String diaChi = txtDiaChi.getText();
					KhachHang kh = new KhachHang(maKH, tenKH, soDT, email, diaChi);
					kh_dao = new KhachHangDAO();
					int action = JOptionPane.showConfirmDialog(null, "Bạn có muốn cập nhật không?", "Cập nhật", JOptionPane.YES_NO_OPTION);
					if(action == JOptionPane.YES_OPTION) {
						if (kh_dao.capNhatKhachHang(kh)) {
							JOptionPane.showMessageDialog(null, "Cập nhật thành công");
							docDuLieu();
							XoaTrang();
						}
					}
				}
			}
		}
		if(o.equals(btnLamMoi)) {
			table.setRowSorter(null);
			docDuLieu();
			XoaTrang();
			txtMaKH.setEditable(true);
		}
		if(o.equals(btnTim)) {
			if(txtMaKH.getText().equals("") && txtTenKH.getText().equals("") && txtSoDT.getText().equals("") && txtEmail.getText().equals("") && txtDiaChi.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Nhập thông tin cần tìm");
				return;
			}else{
				String maKH = txtMaKH.getText();
				String tenKH = txtTenKH.getText();
				String soDT = txtSoDT.getText();
				String email = txtEmail.getText();
				String diaChi = txtDiaChi.getText();
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
				table.setRowSorter(sorter);
				List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(2);
				filters.add(RowFilter.regexFilter(maKH, 0));
				filters.add(RowFilter.regexFilter(tenKH, 1));
				filters.add(RowFilter.regexFilter(soDT, 2));
				filters.add(RowFilter.regexFilter(email, 3));
				filters.add(RowFilter.regexFilter(diaChi, 4));
				RowFilter<Object, Object> filter = RowFilter.andFilter(filters);
				sorter.setRowFilter(filter);
			}
		}

	}

	/*
	Dùng để đọc dữ liệu từ database lên table
	 */
	public void docDuLieu() {
		model.setRowCount(0);
		ArrayList<KhachHang> list = kh_dao.layThongTin();
		for (KhachHang kh : list) {
			String[] row = {kh.getMaKH(), kh.getTenKH(), kh.getDiaChiKH(), kh.geteMail(), kh.getSoDT()};
			model.addRow(row);
		}
	}
	public void XoaTrang() {
		 txtMaKH.setText("");
		 txtTenKH.setText("");
		 txtSoDT.setText("");
		 txtEmail.setText("");
		 txtDiaChi.setText("");
		 txtMaKH.setEditable(true);
	}
	/*
	Tạo mã khách hàng tự động
	 */
	public String taoMaKH() {
		String maKH = "";
		double n = (Math.random()) * ((10000 - 1) + 1) + 1;
		int i = (int) n, a = 0;
		do {
			if (i < 10) {
				maKH = "KH000" + i;
			}
			if (i < 100) {
				maKH = "KH00" + i;
			}
			if (i < 1000) {
				maKH = "KH0" + i;
			}
			if (i < 10000) {
				maKH = "KH" + i;
			}


			if (!kh_dao.kiemTraMaKH(maKH)) {
				a = 0;
			} else
				a = 1;
		} while (a != 0);
		return maKH;
	}
	/*
	Kiem tra du lieu nhap vao
	 */
	public boolean validData(){
		if(txtTenKH.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Tên khách hàng không được để trống");
			txtTenKH.requestFocus();
			return false;
		}
		if(!txtTenKH.getText().matches("[a-zA-Z\\s]+")){
			JOptionPane.showMessageDialog(null, "Tên khách hàng không được chứa số và ký tự đặc biệt");
			txtTenKH.requestFocus();
			txtTenKH.selectAll();
			return false;
		}
		if(txtSoDT.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Số điện thoại không được để trống");
			txtSoDT.requestFocus();
			return false;
		}
		//Số điện thoại phải là số và có 10 chữ số và bắt đầu bằng số 0
		if(!txtSoDT.getText().matches("0[0-9]{9}")){
			JOptionPane.showMessageDialog(null, "Số điện thoại phải là số và có 10 chữ số và bắt đầu bằng số 0");
			txtSoDT.requestFocus();
			txtSoDT.selectAll();
			return false;
		}
		if(txtEmail.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Email không được để trống");
			txtEmail.requestFocus();
			return false;
		}
		//Email phải đúng định dạng
		if(!txtEmail.getText().matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+")){
			JOptionPane.showMessageDialog(null, "Email phải đúng định dạng xxx@gmail.com");
			txtEmail.requestFocus();
			txtEmail.selectAll();
			return false;
		}
		if(txtDiaChi.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Địa chỉ không được để trống");
			txtDiaChi.requestFocus();
			return false;
		}

		return true;
	}
}






