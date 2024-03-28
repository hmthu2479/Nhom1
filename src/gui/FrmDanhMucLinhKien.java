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
import dao.DanhMucDAO;
import entity.DanhMucLinhKien;
import entity.KhachHang;

public class FrmDanhMucLinhKien extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtMDM;
	private JTextField txtTDM;
	private DefaultTableModel model;
	private DanhMucDAO dm_dao;
	private JButton btnThem;
	private JButton btnLuu;
	private JButton btnHuy;

	private JButton btnTim;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnLamMoi;

	/**
	 * Create the panel.
	 */

	public FrmDanhMucLinhKien() {
		setMaximumSize(new Dimension(1500, 1030));
		setMinimumSize(new Dimension(1500, 1030));
		setMaximumSize(new Dimension(1500, 1030));
		setName("Bán hàng");
		/**
		 *
		 */

		dm_dao = new DanhMucDAO();
		setSize(new Dimension(1550, 845));
		setLayout(null);

		JPanel panelTitle = new JPanel();

		JLabel lblTitLe = new JLabel("THÔNG TIN DANH MỤC LINH KIỆN");
		lblTitLe.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitLe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitLe.setFont(new Font("Arial", Font.BOLD, 25));
		lblTitLe.setBounds(0, 10, 1540, 45);
		panelTitle.add(lblTitLe);
		panelTitle.setBounds(0, 0, 1540, 60);
		add(panelTitle);
		panelTitle.setBackground(new Color(148, 162, 242));

		JPanel panelThongTin = new JPanel();
		panelThongTin.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelThongTin.setBounds(0, 55, 1540, 235);
		add(panelThongTin);
		panelThongTin.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 180, 35);
		panelThongTin.add(panel);

		JLabel lblNewLabel = new JLabel("Danh Mục");
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));



		JLabel lblNewLabel_2 = new JLabel("Tên Danh Mục");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(817, 45, 131, 40);
		panelThongTin.add(lblNewLabel_2);

		txtTDM = new JTextField();
		txtTDM.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTDM.setBounds(939, 44, 200, 40);
		panelThongTin.add(txtTDM);
		txtTDM.setColumns(10);

		JLabel lblNewLabel_2_1 = new JLabel("Mã Danh Mục");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1.setBounds(350, 45, 131, 40);
		panelThongTin.add(lblNewLabel_2_1);

		txtMDM = new JTextField();
		txtMDM.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMDM.setColumns(10);
		txtMDM.setBounds(475, 45, 200, 40);
		panelThongTin.add(txtMDM);

		btnTim = new JButton("Tìm kiếm");
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTim.setBounds(200, 115, 200, 50);
		panelThongTin.add(btnTim);
		//nút thêm
		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThem.setBounds(450, 115, 200, 50);
		panelThongTin.add(btnThem);
		// nút xóa
		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXoa.setBounds(700, 115, 200, 50);
		panelThongTin.add(btnXoa);
		//nút xóa rỗng
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLamMoi.setBounds(950, 115, 200, 50);
		panelThongTin.add(btnLamMoi);
		// nút cập nhật
		btnSua = new JButton("Cập nhật");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSua.setBounds(1200, 115, 200, 50);
		panelThongTin.add(btnSua);

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

		String[] Header = { "Mã Danh Mục", "Tên Danh Mục" };
		model = new DefaultTableModel(Header, 0);
		table = new JTable(model);
		table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 16));
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				txtMDM.setText(model.getValueAt(row, 0).toString());
				txtTDM.setText(model.getValueAt(row, 1).toString());
				txtMDM.setEditable(false);

			}
		});
		scrollPane_1.setViewportView(table);
		//TODO: đọc dữ liệu từ database
		DocDuLieu();

		JLabel lblTieuDeTable = new JLabel("DANH SÁCH DANH MỤC");
		lblTieuDeTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDeTable.setFont(new Font("Arial", Font.BOLD, 20));
		lblTieuDeTable.setBounds(0, 315, 1540, 45);
		add(lblTieuDeTable);



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
		// thay   đổi màu nên các panel thành #ECF2FF
		panelThongTin.setBackground(new Color(236, 242, 255));
		lblTieuDeTable.setForeground(Color.WHITE);
		// thay đổi màu background thành #95BDFF
		setBackground(new Color(149, 189, 255));
		lblTitLe.setForeground(Color.WHITE);
		// thay đổi màu của table thành màu #ECF2FF
		table.setBackground(new Color(236, 242, 255));
		// thay  đổi màu header của table thành màu #94A2F2
		table.getTableHeader().setBackground(new Color(148, 162, 242));
		lblTitLe.setBackground(new Color(148, 162, 242));

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
		ArrayList<DanhMucLinhKien> dsDM = dm_dao.layThongTin();
		model.setRowCount(0);
		for (DanhMucLinhKien dm : dsDM) {
			model.addRow(new Object[] { dm.getMaDanhMuc(), dm.getTenDanhMuc() });
		}
	}

	public void XoaTrang() {
		txtMDM.setText("");
		txtTDM.setText("");
		txtMDM.setEditable(true);
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

			txtMDM.setEditable(false);
			txtMDM.setText(TaoMaDanhMuc());
			n=1;
		}
		if(o.equals(btnLuu)){
			if(n==1){
				if(validData()){
					String maDM = txtMDM.getText();
					String TenDM = txtTDM.getText();
					ArrayList<DanhMucLinhKien> dsDM = dm_dao.layThongTin();
					DanhMucLinhKien dm = new DanhMucLinhKien(maDM, TenDM);
					dm_dao.themDanhMuc(dm);
					JOptionPane.showMessageDialog(this,"Thêm danh mục thành công");
					btnTim.setVisible(true);
					btnSua.setVisible(true);
					btnXoa.setVisible(true);
					btnLamMoi.setVisible(true);
					btnThem.setVisible(true);

					btnLuu.setVisible(false);
					btnHuy.setVisible(false);

					txtMDM.setEditable(true);

					DocDuLieu();
					XoaTrang();

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

			txtMDM.setEditable(true);
			DocDuLieu();
			XoaTrang();

		}
		if(o.equals(btnLamMoi)){
			table.setRowSorter(null);
			DocDuLieu();
			XoaTrang();
			txtMDM.setEditable(true);
		}
		if (o.equals(btnXoa)) {
			int row = table.getSelectedRow();
			if(row>=0) {
				String maNCC = (String) model.getValueAt(row, 0);
				int action = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", null,
						JOptionPane.YES_NO_OPTION);
				if (action == JOptionPane.YES_OPTION) {
					if (dm_dao.xoaDanhMuc(maNCC)) {
						JOptionPane.showMessageDialog(null, "Xóa thành công");
						DocDuLieu();
						XoaTrang();
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn danh mục cần xóa");
			}
		}
		if (o.equals(btnSua)) {
			int row = table.getSelectedRow();
			if(row>=0) {
				String maDM = txtMDM.getText();
				String TenDM = txtTDM.getText();
				if (validData()) {
					DanhMucLinhKien dm = new DanhMucLinhKien(maDM, TenDM);
					int action = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn cập nhật?", null,
							JOptionPane.YES_NO_OPTION);
					if (action == JOptionPane.YES_OPTION) {
						if (dm_dao.capNhapDanhMuc(dm)) {
							JOptionPane.showMessageDialog(null, "Cập nhật danh mục thành công");
							DocDuLieu();
							XoaTrang();
						}
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập chọn danh mục cần cập nhật");
			}

		}
		if (o.equals(btnTim)) {
			if(txtMDM.getText().equals("") && txtTDM.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin tìm kiếm");
				return;
			}else {
				String maDM = txtMDM.getText();
				String TenDM = txtTDM.getText();
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
				table.setRowSorter(sorter);
				List<RowFilter<Object, Object>> filters = new ArrayList<>();
				filters.add(RowFilter.regexFilter(maDM, 0));
				filters.add(RowFilter.regexFilter(TenDM, 1));
				RowFilter<Object, Object> af = RowFilter.andFilter(filters);
				sorter.setRowFilter(af);
			}

		}
	}
	public String TaoMaDanhMuc() {
		// TODO Auto-generated method stub
		String MaDM = "";
		double n = (Math.random()) * ((10000 - 1) + 1) + 1;
		int i = (int) n, a = 0;

		do {
			if (i < 10) {
				MaDM = "DM000" + i;
			}
			if (i < 100) {
				MaDM = "DM00" + i;
			}
			if (i < 1000) {
				MaDM = "DM0" + i;
			}
			if (i < 10000) {
				MaDM = "DM" + i;
			}


			if ( !dm_dao.kiemTraMaDM(MaDM)) {
				a = 0;
			} else
				a = 1;
		} while (a != 0);
		txtMDM.setText(MaDM);
		return MaDM;

	}
	private boolean validData() {
		String tenDM = txtTDM.getText().trim();

		if(!(tenDM.length() > 0)) {
			//hiển thị thông báo lỗi, tên danh mục không được để trống
			JOptionPane.showMessageDialog(this, "Tên danh mục không được để trống");
			txtTDM.requestFocus();
			return false;
		}

		// kiểm tra tên danh mục có chứa ký tự đặc biệt hay không
		if (!tenDM.matches("^[a-zA-Z0-9\\s]+$")) {
			JOptionPane.showMessageDialog(this, "Tên danh mục chỉ được chứa các chữ cái, số và khoảng trắng");
			txtTDM.requestFocus();
			return false;
		}

		return true;
	}

}
