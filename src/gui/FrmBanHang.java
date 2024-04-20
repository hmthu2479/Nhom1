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

	private static final long serialVersionUID = 1L;
	private final JButton btnLamMoiSP;
	private JTextField txtTenSP;
	private JTable tblDonHang, tblSanPham;
	private JTextField txtSDT;
	private JTextField txtTenKhachHang;
	private JTextField txtDiaChi;
	private JTextField txtTongTien;
	private DefaultTableModel model_sanPham, model_DonHang;
	private SanPhamDAO sp_dao;
	private JButton btnTimKiemSP, btnThem, btnXoaSanPham;
	private JComboBox<String> cBDanhMuc, cBNCC;
	private double thanhtien;
	private int soluong;
	private JSpinner spSoLuong;
	private Locale localeVN = new Locale("vi", "VN");
	private Locale localDF = Locale.getDefault();
	private NhaCungCapDAO ncc_dao;
	private KhachHangDAO kh_dao;
	NumberFormat vn = NumberFormat.getInstance(localeVN);
	NumberFormat DF = NumberFormat.getInstance(localDF);
	int n = 1;

	private JButton btnLamMoi;
	private JButton btnTimKH;
	private JButton btnThanhToan;
	private NhanVienDAO nv_dao;
	private HoaDonDAO hd_Dao;
	private PhieuDatHangDAO phieu_Dao;
	private JComboBox<String> cbNhanVien;
	private DanhMucDAO dm_dao;
	protected String soLuongSP;
	private JButton btnHuy;
    private JPanel pBody_1;
	private String maKH;
	private ChiTietHoaDonDAO ctHD_dao;
	private ChiTietPhieuDatHangDAO ctphieu_Dao;
	private JButton btnDatHang;


	public FrmBanHang() {

		setLayout(null);
		sp_dao = new SanPhamDAO();
		ncc_dao = new NhaCungCapDAO();
		kh_dao = new KhachHangDAO();

		nv_dao = new NhanVienDAO();
		hd_Dao = new HoaDonDAO();
		ctHD_dao = new ChiTietHoaDonDAO();
		dm_dao = new DanhMucDAO();
		phieu_Dao = new PhieuDatHangDAO();
		ctphieu_Dao = new ChiTietPhieuDatHangDAO();


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
		pBody.setBorder(new TitledBorder(null, "Tìm kiếm Sản Phẩm", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pBody.setName("Tìm kiếm Sản Phẩm");
		pBody.setBounds(10, 41, 826, 148);
		pMain.add(pBody);
		pBody.setLayout(null);

		txtTenSP = new JTextField();
		txtTenSP.setHorizontalAlignment(SwingConstants.CENTER);
		txtTenSP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenSP.setColumns(10);
		txtTenSP.setBounds(235, 57, 180, 40); 
		pBody.add(txtTenSP);

		btnTimKiemSP = new JButton("Tìm Kiếm");
		btnTimKiemSP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTimKiemSP.setBounds(430, 57, 150, 40); 
		pBody.add(btnTimKiemSP);

		btnLamMoiSP = new JButton("Làm mới");
		btnLamMoiSP.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    }
		});
		btnLamMoiSP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLamMoiSP.setBounds(640, 57, 150, 40);
		pBody.add(btnLamMoiSP);

		JLabel lblTenLK = new JLabel("Nhập tên sản phẩm cần tìm");
		lblTenLK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenLK.setBounds(20, 57, 204, 37); 
		txtTenSP.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        txtTenSP.setText("");
		    }
		});
		pBody.add(lblTenLK);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 199, 1540, 200);
		pMain.add(scrollPane);

		String[] Header = { "Mã Sản Phẩm", "Tên Sản Phẩm", "Nhà Cung cấp", "Giá Bán", "Số Lượng", "Danh Mục"};
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
				txtTenSP.setText(tblSanPham.getValueAt(row, 1).toString());
				soLuongSP = model_sanPham.getValueAt(row, 4).toString();
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

		btnThanhToan = new JButton("Thanh Toán");
		btnThanhToan.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnThanhToan.setBounds(50, 186, 160, 60);
		panel_1.add(btnThanhToan);
		
		btnDatHang = new JButton("Đặt hàng");
		btnDatHang.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDatHang.setBounds(235, 186, 160, 60);
		panel_1.add(btnDatHang);

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

		String[] Header1 = { "STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Danh mục", "Nhà cung cấp","Giá Bán", "Số Lượng", "Thành Tiền" };
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
		pBody_1.setName("Tìm kiếm Sản Phẩm");
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
		btnThanhToan.addActionListener(this);
		btnHuy.addActionListener(this);
		btnLamMoiSP.addActionListener(this);
		btnDatHang.addActionListener(this);

		docDuLieuSanPham();

		// thay đổi màu của tất cả các buttom thành màu #716DF2 và chữ của các buttom thành màu trắng
		btnTimKiemSP.setBackground(new Color(113, 109, 242));
		btnTimKiemSP.setForeground(Color.WHITE);
		btnThem.setBackground(new Color(113, 109, 242));
		btnThem.setForeground(Color.WHITE);
		btnXoaSanPham.setBackground(new Color(113, 109, 242));
		btnXoaSanPham.setForeground(Color.WHITE);
		btnLamMoi.setBackground(new Color(113, 109, 242));
		btnLamMoi.setForeground(Color.WHITE);
		btnThanhToan.setBackground(new Color(113, 109, 242));
		btnThanhToan.setForeground(Color.WHITE);
		btnDatHang.setBackground(new Color(113, 109, 242));
		btnDatHang.setForeground(Color.WHITE);
		btnHuy.setBackground(new Color(113, 109, 242));
		btnHuy.setForeground(Color.WHITE);
		btnLamMoiSP.setBackground(new Color(113, 109, 242));
		btnLamMoiSP.setForeground(Color.WHITE);
		btnTimKH.setBackground(new Color(113, 109, 242));
		btnTimKH.setForeground(Color.WHITE);

		pMain.setBackground(Color.WHITE);
		cbNhanVien.setBackground(new Color(148, 162, 242));
		spSoLuong.setBackground(new Color(148, 162, 242));
		// thay  đổi text của các combobox thành trắng
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
		//TODO đọc dữ liệu Sản Phẩm
			public void docDuLieuSanPham() {
			    ArrayList<SanPham> dsSanPham = sp_dao.layThongTin();
			    model_sanPham.setRowCount(0);
			    for (SanPham sp : dsSanPham) {
			        String tenNCC = layTenNCC(sp.getNhaCungCapSanPham().getMaNhaCungCap());
			        String tenDM = layTenDanhMuc(sp.getDanhMucSanPham().getMaDanhMuc());
			        model_sanPham.addRow(new Object[]{
			            sp.getMaSanPham(), 
			            sp.getTenSanPham(), 
			            tenNCC, 
			            sp.getGiaBan(), 
			            sp.getSoLuong(), 
			            tenDM
			        });
			    }
			}


			private String layTenNCC(String maNhaCungCap) {
			    for (NhaCungCapSanPham ncc : ncc_dao.layThongTin()) {
			        if (ncc.getMaNhaCungCap().equalsIgnoreCase(maNhaCungCap)) {
			            return ncc.getTenNCC();
			        }
			    }
			    return "";
			}
		
			private String layTenDanhMuc(String maDanhMuc) {
			    for (DanhMucSanPham dm : dm_dao.layThongTin()) {
			        if (dm.getMaDanhMuc().equalsIgnoreCase(maDanhMuc)) {
			            return dm.getTenDanhMuc();
			        }
			    }
			    return "";
			}
		
		@Override
		public void actionPerformed(ActionEvent e) {
		    Object o = e.getSource();
		    if (o.equals(btnTimKiemSP)) {
		        String tenSP = txtTenSP.getText().toString();
		        model_sanPham.setRowCount(0);
	
		        for (SanPham sp : sp_dao.layThongTin()) {
		            if (sp.getTenSanPham().contains(tenSP)) {
		                String tenNCC = layTenNCC(sp.getNhaCungCapSanPham().getMaNhaCungCap());
		                String tenDM = layTenDanhMuc(sp.getDanhMucSanPham().getMaDanhMuc());
		                
		                model_sanPham.addRow(new Object[]{
		                    sp.getMaSanPham(), 
		                    sp.getTenSanPham(), 
		                    tenNCC, 
		                    sp.getGiaBan(), 
		                    sp.getSoLuong(), 
		                    tenDM
		                });
		            }
		        }
		    }
	

	
		if (o.equals(btnThem)) {
		    int row = tblSanPham.getSelectedRow();
		    if (row == -1) {
		        JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm nào", "Warning", JOptionPane.WARNING_MESSAGE);
		    } else {
		        String txtMaSP = tblSanPham.getValueAt(row, 0).toString();
		        String txtTenSP = tblSanPham.getValueAt(row, 1).toString();
		        String cBDanhMuc = tblSanPham.getValueAt(row, 5).toString();
		        String cBNCC = tblSanPham.getValueAt(row, 2).toString();
		        String giaban = tblSanPham.getValueAt(row, 3).toString();
		        String soLuongSP = tblSanPham.getValueAt(row, 4).toString();

		        soluong = (int) spSoLuong.getValue();
		        thanhtien = Double.parseDouble(giaban) * soluong;
		        String thanhTien = String.valueOf(thanhtien);
		        int soluongsp = Integer.parseInt(soLuongSP);

		        int a = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn thêm ?", null, JOptionPane.YES_NO_OPTION);
		        if (a == JOptionPane.YES_OPTION) {
		            int soLuongNhap = (int) spSoLuong.getValue();
		            if (soLuongNhap > soluongsp) {
		                JOptionPane.showMessageDialog(this, "Số lượng sản phẩm trong kho không đủ, vui lòng đặt hàng", "Warning", JOptionPane.WARNING_MESSAGE);
		                model_DonHang.addRow(new Object[]{n, txtMaSP, txtTenSP, cBDanhMuc, cBNCC, giaban, soLuongNhap, thanhTien});
		                n++;

		            } else {
		                model_DonHang.addRow(new Object[]{n, txtMaSP, txtTenSP, cBDanhMuc, cBNCC, giaban, soLuongNhap, thanhTien});
		                n++;
			            int soLuongCu = Integer.parseInt(tblSanPham.getValueAt(row, 4).toString());
		                int soLuongMoi = soLuongCu - soLuongNhap;
		                tblSanPham.setValueAt(soLuongMoi, row, 4);
		                
		            }

		        }
		    }
		    

		    spSoLuong.setValue(1);
		    thanhTien();
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
					String maSP = tblDonHang.getValueAt(r, 1).toString();
					docDuLieuSanPham();
					model_DonHang.removeRow(r);
					n--
					;
				}
				// trả về thành tiền trừ đi tổng tiền  của sản phẩm đã xóa
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
			docDuLieuSanPham();
			// trả  về thành tiền
			txtTongTien.setText("");


		}
		if(o.equals(btnLamMoiSP)){
			//txtMaSP.setText("");
			txtTenSP.setText("");
			cBDanhMuc.setSelectedItem("Tất cả");
			cBNCC.setSelectedItem("Tất cả");
			tblSanPham.setRowSorter(null);
			docDuLieuSanPham();
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
				JOptionPane.showMessageDialog(null, "Bạn chưa có sản phẩm để đặt hàng, vui lòng thêm sản phẩm để đặt hàng");
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
				HoaDon hd = new HoaDon(maHD, kh, nv, ngayLap);
				if(!hd_Dao.kiemTraMaHD(maHD)){
					if(hd_Dao.themHoaDon(hd)){
							// lấy thông tin trong bảng đơn hàng
						int row = model_DonHang.getRowCount();
						for (int j = 0; j < row; j++) {
							String maSP = model_DonHang.getValueAt(j, 1).toString();
							String soLuong = model_DonHang.getValueAt(j, 6).toString();

							SanPham sp = new SanPham(maSP);
							int soluongsp = Integer.parseInt(soLuong);
							int sl = Integer.parseInt(soLuongSP);


							ChiTietHoaDon cthd = new ChiTietHoaDon(sp, hd,soluongsp);
							ctHD_dao.themCTHoaDon(cthd);
							// trừ số lượng trong bảng sản phẩm
							sp_dao.CapNhapSoLuong(maSP, sl-soluongsp);
							docDuLieuSanPham();

						}
							JOptionPane.showMessageDialog(null, "Thanh  toán thành công");
							// trừ số lượng trong bảng sản phẩm

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

			if (o.equals(btnDatHang)) {

				int count = model_DonHang.getRowCount();
				if (count == 0) {
					JOptionPane.showMessageDialog(null, "Bạn chưa có sản phẩm để đặt hàng, vui lòng thêm sản phẩm để đặt hàng");
				} else if (cbNhanVien.getSelectedItem().equals(null)) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên thanh toán");
				}else if(maKH == null) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin khách hàng","Warning",JOptionPane.WARNING_MESSAGE);
				} else {
					String maphieu = taoMaPhieu();
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
					Date ngayNhanHang = Calendar.getInstance().getTime();
					PhieuDatHang phieu = new PhieuDatHang(maphieu, kh, nv, ngayLap,ngayNhanHang);
					if(!phieu_Dao.kiemTraMaPhieu(maphieu)){
						if(phieu_Dao.themPhieu(phieu)){
								// lấy thông tin trong bảng đơn hàng
							int row = model_DonHang.getRowCount();
							for (int j = 0; j < row; j++) {
								String maSP = model_DonHang.getValueAt(j, 1).toString();
								String soLuong = model_DonHang.getValueAt(j, 6).toString();

								SanPham sp = new SanPham(maSP);
								int soluongsp = Integer.parseInt(soLuong);

								ChiTietPhieuDatHang ctphieu = new ChiTietPhieuDatHang(sp, phieu,soluongsp);
								ctphieu_Dao.themCTPhieu(ctphieu);
								docDuLieuSanPham();

							}
								JOptionPane.showMessageDialog(null, "Đặt hàng thành công");
								// trừ số lượng trong bảng sản phẩm

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
		    int row = model_DonHang.getRowCount();
		    double TongTien = 0;
		    for (int i = 0; i < row; i++) {
		        try {
		            String gia = model_DonHang.getValueAt(i, 5).toString();
		            String soLuong = model_DonHang.getValueAt(i, 6).toString();

		            double giaValue = Double.parseDouble(gia);
		            int soLuongValue = Integer.parseInt(soLuong);

		            double thanhTien = giaValue * soLuongValue;
		            TongTien += thanhTien;
		        } catch (NumberFormatException e) {
		            // Handle exception
		            e.printStackTrace();
		        }
		    }
		    String tongTien = vn.format(TongTien) + " VND";
		    txtTongTien.setText(tongTien);
		}


		public String taoMaHD() {
		    Random rand = new Random();
		    String maHD;
		    do {
		        int randomNumber = rand.nextInt(9999);
		        maHD = String.format("HD%04d", randomNumber);
		    } while (hd_Dao.kiemTraMaHD(maHD));
		    return maHD;
		}
		
		public String taoMaPhieu() {
		    Random rand = new Random();
		    String maphieu;
		    do {
		        int randomNumber = rand.nextInt(9999);
		        maphieu = String.format("PH%04d", randomNumber);
		    } while (phieu_Dao.kiemTraMaPhieu(maphieu));
		    return maphieu;
		}


	public void XoaTrangTimKiemSP() {
		txtTenSP.setText("");
	}

	public void XoaTrangALL() {
		txtTenSP.setText("");
		model_DonHang.setRowCount(0);
		txtSDT.setText("");
		txtTenKhachHang.setText("");
		txtDiaChi.setText("");
		txtTongTien.setText("");

	}
}
