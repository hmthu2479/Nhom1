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
import dao.KeDAO;
import entity.Ke;

public class FrmKe extends JPanel implements ActionListener {
	private static final long serialVersionUID =1L;
	private KeDAO k_dao;
	private JTextField txtTK;
	private JTextField txtMK;
	private JTextField txtVT;
	private DefaultTableModel model;
	private JTable table;
	private JButton btnHuy;
	private JButton btnLuu;
	private JButton btnSua;
	private JButton btnTim;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnLamMoi;
	
	public FrmKe() {
		
		k_dao = new KeDAO();
		setLayout(null);
		
		JPanel panelTitle = new JPanel();

		JLabel lblTitLe = new JLabel("THÔNG TIN KỆ SẢN PHẨM");
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
		panel.setBounds(0, 0, 100, 35);
		panelThongTin.add(panel);
		
		JLabel lblNewLabel = new JLabel("Kệ");
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		
		
		JLabel lblNewLabel_2 = new JLabel("Tên Kệ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(600, 45, 131, 40);
		panelThongTin.add(lblNewLabel_2);
		
		
		txtTK = new JTextField();
		txtTK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTK.setBounds(675, 44, 200, 40);
		panelThongTin.add(txtTK);
		txtTK.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Mã Kệ");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1.setBounds(300, 45, 131, 40);
		panelThongTin.add(lblNewLabel_2_1);

		txtMK = new JTextField();
		txtMK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMK.setColumns(10);
		txtMK.setBounds(375, 45, 200, 40);
		panelThongTin.add(txtMK);
		
		JLabel lblNewLabel_2_2 = new JLabel("Vị Trí");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_2.setBounds(900, 45, 131, 40);
		panelThongTin.add(lblNewLabel_2_2);

		txtVT = new JTextField();
		txtVT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtVT.setColumns(10);
		txtVT.setBounds(975, 45, 200, 40);
		panelThongTin.add(txtVT);
		
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

		String[] Header = { "Mã Kệ", "Tên Kệ", "Vị trí" };
		model = new DefaultTableModel(Header, 0);
		table = new JTable(model);
		table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 16));
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				txtMK.setText(model.getValueAt(row, 0).toString());
				txtTK.setText(model.getValueAt(row, 1).toString());
				txtVT.setText(model.getValueAt(row, 2).toString());
				txtMK.setEditable(false);

			}
		});
		scrollPane_1.setViewportView(table);
		//TODO: đọc dữ liệu từ database
		DocDuLieu();

		JLabel lblTieuDeTable = new JLabel("DANH SÁCH KỆ");
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
		ArrayList<Ke> dsk = k_dao.laythongtin();
		model.setRowCount(0);
		for(Ke k : dsk) {
			model.addRow(new Object[] {k.getMaKe(),k.getTenKe(),k.getVitri()});
		}
	}
	public void xoaTrang() {
		txtMK.setText("");
		txtTK.setText("");
		txtVT.setText("");
		txtMK.setEditable(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		int n=1;
		if(o.equals(btnThem)) {
			btnTim.setVisible(false);
			btnThem.setVisible(false);
			btnSua.setVisible(false);
			btnXoa.setVisible(false);
			btnLamMoi.setVisible(false);

			btnLuu.setVisible(true);
			btnHuy.setVisible(true);

			txtMK.setEditable(false);
			txtMK.setText(TaoMaKe());
			n=1;
		}
		if(o.equals(btnLuu)) {
			if(n==1) {
				if(validData()) {
					String maK = txtMK.getText();
					String tenK = txtTK.getText();
					String vt = txtVT.getText();
					ArrayList<Ke> dsk = k_dao.laythongtin();
					Ke k = new Ke(maK,tenK,vt);
					if(k_dao.kiemtraKe(maK)) {
						JOptionPane.showMessageDialog(this, "Trung ma!");
					}else {
						if(k_dao.themKe(k)) {
						JOptionPane.showMessageDialog(this, "Thêm kệ thành công");
					}
					}
					
					
					btnTim.setVisible(true);
					btnSua.setVisible(true);
					btnXoa.setVisible(true);
					btnLamMoi.setVisible(true);
					btnThem.setVisible(true);

					btnLuu.setVisible(false);
					btnHuy.setVisible(false);
					
					txtMK.setEditable(true);
					
					DocDuLieu();
					xoaTrang();
				}
			}
		}
		if(o.equals(btnHuy)) {
			btnTim.setVisible(true);
			btnSua.setVisible(true);
			btnXoa.setVisible(true);
			btnLamMoi.setVisible(true);
			btnThem.setVisible(true);

			btnLuu.setVisible(false);
			btnHuy.setVisible(false);
			
			
			txtMK.setEditable(true);
			
			DocDuLieu();
			xoaTrang();
			
		}
		if(o.equals(btnLamMoi)) {
			table.setRowSorter(null);
			DocDuLieu();
			xoaTrang();
			txtMK.setEditable(true);
		}
		if(o.equals(btnXoa)) {
			int row = table.getSelectedRow();
			if(row >=0) {
				String maK = (String) model.getValueAt(row, 0);
				int action = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?",null,
						JOptionPane.YES_NO_OPTION);
				if(action == JOptionPane.YES_OPTION) {
					if(k_dao.xoaKe(maK)) {
						JOptionPane.showMessageDialog(null, "Xóa thành công");
						DocDuLieu();
						xoaTrang();
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn danh mục cần xóa");
			}
		}
		if(o.equals(btnSua)) {
			int row = table.getSelectedRow();
			if(row>=0) {
				String maK = txtMK.getText();
				String tenK = txtTK.getText();
				String vt = txtVT.getText();
				if(validData()) {
					Ke k = new Ke(maK,tenK,vt);
					int action = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn cập nhật?",null,
							JOptionPane.YES_NO_OPTION);
					if(action == JOptionPane.YES_OPTION) {
						if(k_dao.capnhatKe(k)) {
							JOptionPane.showMessageDialog(null, "Cập nhật danh mục thành công");
							DocDuLieu();
							xoaTrang();	
						}
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập chọn danh mục cần cập nhật");
			}
		}
		if(o.equals(btnTim)) {
			if(txtMK.getText().equals("")&& txtTK.getText().equals("")&& txtVT.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin tìm kiếm");
				return ;
			}else {
				String maK = txtMK.getText();
				String tenK = txtTK.getText();
				String vt = txtVT.getText();
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
				table.setRowSorter(sorter);
				List<RowFilter<Object, Object>> filters	= new ArrayList<>();
				filters.add(RowFilter.regexFilter(maK, 0));
				filters.add(RowFilter.regexFilter(tenK, 1));
				filters.add(RowFilter.regexFilter(vt, 2));
				RowFilter<Object, Object> af = RowFilter.andFilter(filters);
				sorter.setRowFilter(af);
				}
		}
	}
	public String TaoMaKe() {
		// TODO Auto-generated method stub
		String maK = "";
		double n = (Math.random())*((1000-1)+1)+1;
		int i = (int) n,a=0;
		
		do {
			if(i<10) {
				maK = "K000"+i;
			}
			if(i<100) {
				maK = "K00"+i;
			}
			if(i<1000) {
				maK = "K0"+i;
			}
			if(i<10000) {
				maK = "K"+i;
			}
			if(!k_dao.kiemtraKe(maK)) {
				a = 0;
			}else {
				a=1;
			}
		}while(a !=0);
		txtMK.setText(maK);
		return maK;
	}
	private boolean validData() {
		String tenK = txtTK.getText().trim();
		String vt = txtVT.getText().trim();
		if(!(tenK.length()>0)) {
			JOptionPane.showMessageDialog(this, "Tên kệ không được để trống");
			txtTK.requestFocus();
			return false;
		}
		
		if(!(vt.length()>0)) {
			JOptionPane.showMessageDialog(null, "Vị trí không được để trống");
			txtVT.requestFocus();
			return false;
		}
		if(!vt.matches("^[a-zA-Z0-9\\s]+$")) {
			JOptionPane.showMessageDialog(this, "Vị trí chỉ được chứa các chữ cái, số và khoảng trắng");
		}
		return true;
	}
}
