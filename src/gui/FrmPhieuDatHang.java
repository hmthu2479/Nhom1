package gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;



import com.toedter.calendar.JDateChooser;

import dao.ChiTietHoaDonDAO;
import dao.ChiTietPhieuDatHangDAO;
import dao.DanhMucDAO;
import dao.HoaDonDAO;
import dao.PhieuDatHangDAO;
import dao.KhachHangDAO;
import dao.NhaCungCapDAO;
import dao.NhanVienDAO;
import dao.SanPhamDAO;
import entity.ChiTietHoaDon;
import entity.ChiTietPhieuDatHang;
import entity.DanhMucSanPham;
import entity.HoaDon;
import entity.PhieuDatHang;
import entity.KhachHang;
import entity.NhaCungCapSanPham;
import entity.NhanVien;
import entity.SanPham;

public class FrmPhieuDatHang extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTable tblChiTietPhieu;
	private JTable tblPhieu;
    private DefaultTableModel modelPhieu, modelCTPhieu;
	private NhaCungCapDAO ncc_DAO;

	private KhachHangDAO kh_dao;
	private SanPhamDAO sp_dao;
	private DanhMucDAO dm_dao;
	private JButton btnLamMoi;
	private JDateChooser dateNgayDat;
	private JButton btnTimPhieu;
	private JLabel lblDonHang;
	private NhanVienDAO nv_dao;
	protected String MaDonHang;
	protected int r;
	private Locale localeVN = new Locale("vi", "VN");
	NumberFormat vn = NumberFormat.getInstance(localeVN);
	private PhieuDatHangDAO phieu_dao;
	private ChiTietPhieuDatHangDAO ctphieu_dao;
	private JButton btnThanhToan;
	private HoaDonDAO hd_dao;


	public FrmPhieuDatHang() {

		setLayout(null);
        kh_dao = new KhachHangDAO();
		sp_dao = new SanPhamDAO();
		dm_dao = new DanhMucDAO();
		nv_dao = new NhanVienDAO();
		phieu_dao = new PhieuDatHangDAO();
		ctphieu_dao = new ChiTietPhieuDatHangDAO();
		ncc_DAO = new NhaCungCapDAO();
		hd_dao = new HoaDonDAO();
		
		JPanel panelTitle = new JPanel();
		JLabel lblTitLe = new JLabel("DANH SÁCH PHIẾU");
		lblTitLe.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitLe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitLe.setFont(new Font("Arial", Font.BOLD, 25));
		panelTitle.add(lblTitLe);
		panelTitle.setBounds(0, 0, 1540, 60);
		panelTitle.setBackground(new Color(148, 162, 242));
		lblTitLe.setForeground(Color.WHITE);
		add(panelTitle);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(0, 57, 1540, 125);
		add(panel_1);
		panel_1.setLayout(null);


		btnTimPhieu = new JButton("Tìm Phiếu");
		btnTimPhieu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTimPhieu.setBounds(450, 65, 150, 40);
		panel_1.add(btnTimPhieu);


		JLabel lblNgayDat = new JLabel("Ngày Đặt");
		lblNgayDat.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgayDat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNgayDat.setBounds(100, 65, 150, 33);
		panel_1.add(lblNgayDat);

		dateNgayDat = new JDateChooser();
		panel_1.add(dateNgayDat);
		dateNgayDat.setDateFormatString("yyyy-MM-dd");
		dateNgayDat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dateNgayDat.setBounds(208, 65, 200, 40);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 178, 33);
		panel_1.add(panel);

		JLabel lblNewLabel = new JLabel("Tìm Phiếu");
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

		btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThanhToan.setBounds(1267, 65, 150, 40);
		panel_1.add(btnThanhToan);
		
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBounds(657, 65, 150, 40);
		panel_1.add(btnLamMoi);
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tblPhieu.setRowSorter(null);

			}
		});
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 192, 1540, 247);
		add(scrollPane_1);
		String[] header1 = { "Mã Phiếu", "Tên Khách Hàng", "Số Điện Thoại", "Nhân Viên Lập Phiếu", "Ngày Lập Phiếu","Ngày hàng về dự kiến","Tổng Tiền" };
		modelPhieu = new DefaultTableModel(header1, 0);
		tblPhieu = new JTable(modelPhieu);
		tblPhieu.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 16));
		tblPhieu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane_1.setViewportView(tblPhieu);
		tblPhieu.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int r = tblPhieu.getSelectedRow();
		        String maPhieu = modelPhieu.getValueAt(r, 0).toString();
		        dateNgayDat.setDate((Date) modelPhieu.getValueAt(r, 4));
		        DocDuLieuCTDH(maPhieu);
		    }
		});

		//TODO dọc    dữ liệu
		docDuLieuHD();


		JLabel lblChiTietHD = new JLabel("CHI TIẾT PHIẾU");
		lblChiTietHD.setHorizontalAlignment(SwingConstants.CENTER);
		lblChiTietHD.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblChiTietHD.setBounds(0, 470, 1540, 37);
		add(lblChiTietHD);

		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(0, 506, 1540, 329);
		add(scrollPane_1_1);
		String[] header = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Nhà Cung cấp", "Giá Bán", "Số Lượng", "Danh Mục"};
		modelCTPhieu = new DefaultTableModel(header, 0);
		tblChiTietPhieu = new JTable(modelCTPhieu);
		tblChiTietPhieu.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 16));
		tblChiTietPhieu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane_1_1.setViewportView(tblChiTietPhieu);
		btnTimPhieu.addActionListener(this);

		lblDonHang = new JLabel("");
		lblDonHang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDonHang.setBounds(117, 470, 126, 37);
		add(lblDonHang);
		btnLamMoi.addActionListener(this);
		btnTimPhieu.addActionListener(this);
		btnThanhToan.addActionListener(this);

		// thay đổi màu của tất cả các buttom thành màu #716DF2 và chữ của các buttom thành màu trắng
		btnTimPhieu.setBackground(new Color(113, 109, 242));
		btnTimPhieu.setForeground(Color.WHITE);
		btnLamMoi.setBackground(new Color(113, 109, 242));
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setBackground(new Color(113, 109, 242));
		btnLamMoi.setForeground(Color.WHITE);
		btnThanhToan.setBackground(new Color(113, 109, 242));
		btnThanhToan.setForeground(Color.WHITE);
		btnThanhToan.addActionListener(this);
		
		tblPhieu.setBackground(new Color(236, 242, 255));
		tblChiTietPhieu.setBackground(new Color(236, 242, 255));
		tblPhieu.getTableHeader().setBackground(new Color(148, 162, 242));
		tblChiTietPhieu.getTableHeader().setBackground(new Color(148, 162, 242));
		panel_1.setBackground(new Color(236, 242, 255));

		setBackground(new Color(149, 189, 255));


	}




	public void docDuLieuHD() {
	    modelPhieu.setRowCount(0);
	    ArrayList<PhieuDatHang> dsPhieu = phieu_dao.layThongTin();
	    for (PhieuDatHang p : dsPhieu) {
	        KhachHang kh = kh_dao.TimKhachHang(p.getKhachHang().getMaKH());
	        NhanVien nv = nv_dao.TimNhanVien(p.getNhanVien().getMaNhanVien());
	        ArrayList<ChiTietPhieuDatHang> dsCT = ctphieu_dao.TimCTPhieu(p.getmaPhieu());
	        double tong = 0;
	        for (ChiTietPhieuDatHang ct : dsCT) {
	            SanPham sp = sp_dao.timSanPham(ct.getSanPham().getMaSanPham()).get(0);
	            tong += ct.getSoLuong() * sp.getGiaBan();
	        }
	        String TongTien = vn.format(tong) + " VND";
	        modelPhieu.addRow(new Object[] { p.getmaPhieu(), kh.getTenKH(), kh.getSoDT(), nv.getHoTen(), p.getngayLap(), p.getNgayNhanHangDuKien(), TongTien });
	    }
	}

	public void DocDuLieuCTDH(String id) {
		ArrayList<ChiTietPhieuDatHang> dsCT = ctphieu_dao.TimCTPhieu(id);
		ArrayList<DanhMucSanPham> dsDM = dm_dao.layThongTin();
		ArrayList<NhaCungCapSanPham> dsNCC = ncc_DAO.layThongTin();
		modelCTPhieu.setRowCount(0);

		for (ChiTietPhieuDatHang ct : dsCT) {
			ArrayList<SanPham> dssp = sp_dao.timSanPham(ct.getSanPham().getMaSanPham());
			for (SanPham sp : dssp) {
				for(DanhMucSanPham dm: dsDM) {
					if(dm.getMaDanhMuc().equals(sp.getDanhMucSanPham().getMaDanhMuc())) {
						for(NhaCungCapSanPham ncc: dsNCC) {
							if(ncc.getMaNhaCungCap().equals(sp.getNhaCungCapSanPham().getMaNhaCungCap())) {
								modelCTPhieu.addRow(new Object[] { sp.getMaSanPham(), sp.getTenSanPham(), ncc.getTenNCC(), sp.getGiaBan(),
										ct.getSoLuong(), dm.getTenDanhMuc() });
							}
						}
					}
				}
			}
		}

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnTimPhieu)){
			
			String ngayDat = "";

		    if (dateNgayDat.getDate() != null) {
		        ngayDat = dateNgayDat.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
		    }

		    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(modelPhieu);
		    tblPhieu.setRowSorter(sorter);
		    List<RowFilter<Object,Object>> filters = new ArrayList<>();
		    
		    if (!ngayDat.isEmpty()) {
		        filters.add(RowFilter.regexFilter(ngayDat, 4));
		    }
		    
		    RowFilter<Object,Object> filter = RowFilter.andFilter(filters);
		    sorter.setRowFilter(filter);
		}
		if (o.equals(btnThanhToan)) {
		    int selectedRow = tblPhieu.getSelectedRow();

		    if (selectedRow != -1) {
		    	// Lấy mã từ bảng
		        String maPhieu = modelPhieu.getValueAt(selectedRow, 0).toString();
		        // Tìm phiếu từ mã
		        ArrayList<PhieuDatHang> phieuDatHangList = phieu_dao.timPhieu(maPhieu);
		        // Tìm chi tiết phiếu từ mã
		        List<ChiTietPhieuDatHang> chiTietPhieuList = ctphieu_dao.TimCTPhieu(maPhieu);
		        // Tạo mã HD
		        String maHoaDon = hd_dao.taoMaHD(maPhieu);
		        // Tạo hóa đơn từ phiếu
		        boolean success = hd_dao.themHoaDonTuPhieu(phieuDatHangList, chiTietPhieuList, maHoaDon);

		        if (success) {
		            boolean deleteSuccess = phieu_dao.xoaPhieu(maPhieu);
		            if (deleteSuccess) {
		                docDuLieuHD();
		                modelCTPhieu.setRowCount(0);
		                JOptionPane.showMessageDialog(null, "Thanh toán thành công !");
		            } else {
		                JOptionPane.showMessageDialog(null, "Lỗi xảy ra khi xóa phiếu sau khi thanh toán.");
		            }
		            return;
		        } else {
		            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi tạo hóa đơn từ phiếu đặt hàng.");
		        }
		    }
		}


		else if(o.equals(btnLamMoi)){
			// xóa dữ liệu trong các ô text
			dateNgayDat.setDate(null);
			tblPhieu.setRowSorter(null);
			docDuLieuHD();
		}
		
		
    }


    
}
