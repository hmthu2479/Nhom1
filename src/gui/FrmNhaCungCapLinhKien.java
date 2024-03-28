package gui;

import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import dao.NhaCungCapDAO;
import entity.DanhMucLinhKien;
import entity.NhaCungCapLinhKien;

public class FrmNhaCungCapLinhKien extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton btnLuu;
	private  JButton btnHuy;
	private JTable table;
	private JTextField txtMaNCC;
	private JTextField txtTenNCC;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private DefaultTableModel model;
	private NhaCungCapDAO ncc_Dao;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnTim;
	private JButton btnLamMoi;
	private JLabel lblNewLabel_1;

	/**
	 * Create the panel.
	 */


	public FrmNhaCungCapLinhKien() {
		setMaximumSize(new Dimension(1500, 1030));
		setMinimumSize(new Dimension(1500, 1030));
		setMaximumSize(new Dimension(1500, 1030));

		/**
		 *
		 */

		ncc_Dao = new NhaCungCapDAO();
		setSize(new Dimension(1550, 845));
		setLayout(null);

		JPanel panelThongTin = new JPanel();
		panelThongTin.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelThongTin.setBounds(0, 55, 1540, 235);
		add(panelThongTin);
		panelThongTin.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 178, 33);
		panelThongTin.add(panel);

		JLabel lblNewLabel = new JLabel("Thông tin");
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

		btnTim = new JButton("Tìm Kiếm");
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTim.setBounds(90, 115, 200, 50);
		panelThongTin.add(btnTim);

		JLabel lblNewLabel_2 = new JLabel("Mã Nhà Cung Cấp");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(35, 45, 145, 40);
		panelThongTin.add(lblNewLabel_2);

		txtMaNCC = new JTextField();
		txtMaNCC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaNCC.setBounds(167, 45, 200, 40);
		panelThongTin.add(txtMaNCC);
		txtMaNCC.setColumns(10);

		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThem.setBounds(390, 115, 200, 50);
		panelThongTin.add(btnThem);

		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXoa.setBounds(679, 115, 200, 50);
		panelThongTin.add(btnXoa);

		JLabel lblNewLabel_2_1 = new JLabel("Tên Nhà Cung Cấp");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1.setBounds(426, 45, 141, 40);
		panelThongTin.add(lblNewLabel_2_1);

		txtTenNCC = new JTextField();
		txtTenNCC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenNCC.setColumns(10);
		txtTenNCC.setBounds(571, 45, 200, 40);
		panelThongTin.add(txtTenNCC);

		JLabel lblNewLabel_2_2 = new JLabel("Số Điện Thoại");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_2.setBounds(828, 45, 107, 40);
		panelThongTin.add(lblNewLabel_2_2);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSDT.setColumns(10);
		txtSDT.setBounds(938, 45, 200, 40);
		panelThongTin.add(txtSDT);

		JLabel lblNewLabel_2_3 = new JLabel("Địa Chỉ");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_3.setBounds(1226, 45, 66, 40);
		panelThongTin.add(lblNewLabel_2_3);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(1302, 45, 200, 40);
		panelThongTin.add(txtDiaChi);

		btnSua = new JButton("Cập Nhật");
		btnSua.setBounds(1250, 115, 200, 50);
		panelThongTin.add(btnSua);
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 16));

		btnLamMoi = new JButton("Làm Mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLamMoi.setBounds(969, 115, 200, 50);
		panelThongTin.add(btnLamMoi);


		btnLuu = new JButton("Lưu");
		btnLuu.setVisible(false);
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLuu.setBounds(450, 115, 200, 50);
		panelThongTin.add(btnLuu);

		btnHuy = new JButton("Hủy");
		btnHuy.setVisible(false);
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnHuy.setBounds(700, 115, 200, 50);
		panelThongTin.add(btnHuy);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 360, 1540, 465);
		add(scrollPane_1);
		String[] Header = { "Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ", "Số Điện Thoại" };
		model = new DefaultTableModel(Header, 0);
		table = new JTable(model);
		table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				txtMaNCC.setText(model.getValueAt(row, 0).toString());				;
				txtTenNCC.setText(model.getValueAt(row, 1).toString());
				txtDiaChi.setText(model.getValueAt(row, 2).toString());
				txtSDT.setText(model.getValueAt(row, 3).toString());
				txtMaNCC.setEditable(false);
			}
		});
		scrollPane_1.setViewportView(table);
		DocDuLieu();

		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnSua.addActionListener(this);
		btnHuy.addActionListener(this);
		btnLuu.addActionListener(this);

		JLabel lblTitLe_1 = new JLabel("DANH SÁCH NHÀ CUNG CẤP");
		lblTitLe_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitLe_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitLe_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitLe_1.setBounds(0, 315, 1540, 45);
		add(lblTitLe_1);

		JPanel panelTitle = new JPanel();

		JLabel lblTitLe = new JLabel("THÔNG TIN NHÀ CUNG CẤP");
		lblTitLe.setBounds(0, 20, 1540, 48);
		add(panelTitle);
		lblTitLe.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitLe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitLe.setFont(new Font("Arial", Font.BOLD, 25));
		panelTitle.add(lblTitLe);
		panelTitle.setBounds(0, 0, 1540, 60);
		panelTitle.setBackground(new Color(148, 162, 242));
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
		panelThongTin.setBackground(new Color(236, 242, 255));
		lblTitLe_1.setForeground(Color.WHITE);
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

	public void DocDuLieu() {
		// TODO Auto-generated method stub
		ArrayList<NhaCungCapLinhKien> dsNCC = ncc_Dao.layThongTin();
		model.setRowCount(0);
		for (NhaCungCapLinhKien ncc : dsNCC) {
			model.addRow(
					new Object[] { ncc.getMaNhaCungCap(), ncc.getTenNCC(), ncc.getDiaCHi(), ncc.getSoDienThoai() });
		}
	}

	public void XoaTrang() {
		txtMaNCC.setText("");
		txtTenNCC.setText("");
		txtSDT.setText("");
		txtDiaChi.setText("");
		txtMaNCC.setEditable(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
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
			txtMaNCC.setEditable(false);
			txtMaNCC.setText(TaoMaNhaCungCap());

			n = 1;
		}
		if(o.equals(btnLuu)){
			if(n==1){
				if(validData()){
					String maNCC = txtMaNCC.getText();
					String TenNCC = txtTenNCC.getText();
					String DiaChi = txtDiaChi.getText();
					String SDT = txtSDT.getText();
					NhaCungCapLinhKien ncc = new NhaCungCapLinhKien(maNCC, TenNCC, DiaChi, SDT);
					if(ncc_Dao.ThemNhaCungCap(ncc)){
						JOptionPane.showMessageDialog(null, "Thêm thành công");
						DocDuLieu();
						XoaTrang();
						btnTim.setVisible(true);
						btnThem.setVisible(true);
						btnSua.setVisible(true);
						btnXoa.setVisible(true);
						btnLamMoi.setVisible(true);

						btnLuu.setVisible(false);
						btnHuy.setVisible(false);
					}

				}
			}
		}
		if(o.equals(btnHuy)){
			btnTim.setVisible(true);
			btnThem.setVisible(true);
			btnSua.setVisible(true);
			btnXoa.setVisible(true);
			btnLamMoi.setVisible(true);

			btnLuu.setVisible(false);
			btnHuy.setVisible(false);
			DocDuLieu();
			XoaTrang();
		}
		if (o.equals(btnXoa)) {
			int row = table.getSelectedRow();
			if(row>=0) {
				String maNCC = (String) model.getValueAt(row, 0);
				int action = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", null,
						JOptionPane.YES_NO_OPTION);
				if (action == JOptionPane.YES_OPTION) {
					if(ncc_Dao.xoa(maNCC)) {
						model.removeRow(row);
						JOptionPane.showMessageDialog(null, "Xóa thành công");
						XoaTrang();
					}
				}
			}else JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp cần xóa");
		}
		if (o.equals(btnSua )) {
			int row = table.getSelectedRow();
			if(row>=0) {
				String maNCC = (String) model.getValueAt(row, 0);
				String TenNCC = (String) model.getValueAt(row, 1);
				String DiaChi = (String) model.getValueAt(row, 2);
				String SDT = (String) model.getValueAt(row, 3);
				if(validData()){
					NhaCungCapLinhKien ncc = new NhaCungCapLinhKien(maNCC, TenNCC, DiaChi, SDT);
					int action = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn cập nhật?", null,
							JOptionPane.YES_NO_OPTION);
					if (action == JOptionPane.YES_OPTION) {
						if(ncc_Dao.Sua(ncc)) {
							JOptionPane.showMessageDialog(null, "Cập nhật thành công");
							DocDuLieu();
							XoaTrang();
						}
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập chọn nhà cung cấp cần cập nhật");
			}

		}
		if (o.equals(btnLamMoi)) {
			table.setRowSorter(null);
			XoaTrang();
			DocDuLieu();
			txtMaNCC.setEditable(true);

		}
		if (o.equals(btnTim)) {
			if(txtMaNCC.getText().equals("") && txtTenNCC.getText().equals("") && txtDiaChi.getText().equals("") && txtSDT.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin tìm kiếm");
			else {
				String maNCC = txtMaNCC.getText();
				String TenNCC = txtTenNCC.getText();
				String DiaChi = txtDiaChi.getText();
				String SDT = txtSDT.getText();
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
				table.setRowSorter(sorter);
				List<RowFilter<Object, Object>> filters = new ArrayList<>();
				filters.add(RowFilter.regexFilter(TenNCC, 1));
				filters.add(RowFilter.regexFilter(DiaChi, 2));
				filters.add(RowFilter.regexFilter(SDT, 3));
				filters.add(RowFilter.regexFilter(maNCC, 0));
				RowFilter<Object, Object> af = RowFilter.andFilter(filters);
				sorter.setRowFilter(af);
			}

		}
	}
	public String TaoMaNhaCungCap() {
		// TODO Auto-generated method stub
		String MaNCC = "";
		double n = (Math.random()) * ((10000 - 1) + 1) + 1;
		int i = (int) n, a = 0;

		do {
			if (i < 10) {
				MaNCC = "NCC000" + i;
			}
			if (i < 100) {
				MaNCC = "NCC00" + i;
			}
			if (i < 1000) {
				MaNCC = "NCC0" + i;
			}
			if (i < 10000) {
				MaNCC = "NCC" + i;
			}


			if ( !ncc_Dao.KiemTraMa(MaNCC)) {
				a = 0;
			} else
				a = 1;
		} while (a != 0);
		txtMaNCC.setText(MaNCC);
		return MaNCC;

	}
	private boolean validData() {
		String tenNCC = txtTenNCC.getText().trim();
		String sdt = txtSDT.getText().trim();
		String diachi = txtDiaChi.getText().trim();

		if(!(tenNCC.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Error: Tên nhà cung cấp không được bỏ trống");
			txtTenNCC.requestFocus();
			return false;
		}
		// tên nhà cung cấp không được chứa kỳ tự đặc biệt
		if(!(tenNCC.length() > 0 && tenNCC.matches("[a-zA-Z0-9 ]+"))) {
			JOptionPane.showMessageDialog(this, "Error: Tên nhà cung cấp không được chứa ký tự đặc biệt");
			txtTenNCC.requestFocus();
			return false;
		}
		if(!(sdt.length() > 0 && sdt.matches("0\\d{9}"))) {
			JOptionPane.showMessageDialog(this, "Error: Số điện thoại nhà cung cấp theo mẫu: 0\\d{9}");
			txtSDT.requestFocus();
			return false;
		}
		if(!(diachi.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Error: Địa chỉ nhà cung cấp không được bỏ trống");
			txtDiaChi.requestFocus();
			return false;
		}

		return true;
	}
}
