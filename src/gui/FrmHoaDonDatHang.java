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

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import com.toedter.calendar.JDateChooser;

import dao.ChiTietHoaDonDAO;
import dao.ChiTietHoaDonDatHangDAO;
import dao.DanhMucDAO;
import dao.HoaDonDAO;
import dao.HoaDonDatHangDAO;
import dao.KhachHangDAO;
import dao.NhaCungCapDAO;
import dao.NhanVienDAO;
import dao.SanPhamDAO;
import entity.ChiTietHoaDon;
import entity.ChiTietHoaDonDatHang;
import entity.DanhMucSanPham;
import entity.HoaDon;
import entity.HoaDonDatHang;
import entity.KhachHang;
import entity.NhaCungCapSanPham;
import entity.NhanVien;
import entity.SanPham;

public class FrmHoaDonDatHang extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTable tblChITietHoaDon;
	private JTable tblHoaDon;
	private JTextField txtHoaDon;
    private JComboBox<Object> cBNhanVienThanhToan;
	private JTextField txtSDTKH;
    private DefaultTableModel modelHoaDon, modelCTHoaDon;
	private NhaCungCapDAO ncc_DAO;

	private KhachHangDAO kh_dao;
	private SanPhamDAO sp_dao;
	private DanhMucDAO dm_dao;
	private JButton btnLamMoi;
	private JTextField txtSDT;
	private JDateChooser dateNgayDat;

	private JButton btnTimHoaDon;
	private JLabel lblDonHang;
	private JComboBox<String> cbNhanVien;
	private NhanVienDAO nv_dao;
	protected String MaDonHang;
	protected int r;
	private Locale localeVN = new Locale("vi", "VN");
	NumberFormat vn = NumberFormat.getInstance(localeVN);
	private JButton btnInHoaDon;
	private HoaDonDatHangDAO hddh_dao;
	private ChiTietHoaDonDatHangDAO cthddh_dao;


	public FrmHoaDonDatHang() {
		setMaximumSize(new Dimension(1500, 1030));
		setMinimumSize(new Dimension(1500, 1030));
		setMaximumSize(new Dimension(1500, 1030));
		setName("Bán hàng");
		/**
		 *
		 */

		setSize(new Dimension(1550, 845));
		setLayout(null);
        kh_dao = new KhachHangDAO();
		sp_dao = new SanPhamDAO();
		dm_dao = new DanhMucDAO();
		nv_dao = new NhanVienDAO();
		hddh_dao = new HoaDonDatHangDAO();
		cthddh_dao = new ChiTietHoaDonDatHangDAO();
		ncc_DAO = new NhaCungCapDAO();
		JPanel panelTitle = new JPanel();
		JLabel lblTitLe = new JLabel("DANH SÁCH HOÁ ĐƠN");
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

		txtHoaDon = new JTextField();
		txtHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtHoaDon.setColumns(10);
		txtHoaDon.setBounds(43, 65, 200, 40);
		panel_1.add(txtHoaDon);

		JLabel lblMaHoaDon = new JLabel("Mã Hóa Đơn");
		lblMaHoaDon.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaHoaDon.setBounds(44, 35, 188, 33);
		panel_1.add(lblMaHoaDon);

		btnTimHoaDon = new JButton("Tìm Hóa Đơn");
		btnTimHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTimHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTimHoaDon.setBounds(966, 65, 150, 40);
		panel_1.add(btnTimHoaDon);

		JLabel lblNhanVienThanhToan = new JLabel("Nhân Viên Thanh toán");
		lblNhanVienThanhToan.setHorizontalAlignment(SwingConstants.LEFT);
		lblNhanVienThanhToan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNhanVienThanhToan.setBounds(745, 35, 194, 33);
		panel_1.add(lblNhanVienThanhToan);

		cBNhanVienThanhToan = new JComboBox<Object>();
		cBNhanVienThanhToan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cBNhanVienThanhToan.setBounds(745, 65, 200, 40);
		ArrayList<NhanVien> dsnv = nv_dao.layThongTin();
		for (NhanVien nv : dsnv) {

			cBNhanVienThanhToan.addItem(nv.getHoTen());
		}
		cBNhanVienThanhToan.addItem("Tất cả");
		panel_1.add(cBNhanVienThanhToan);

		JLabel lblSDTKH = new JLabel("Số điện thoại khách hàng");
		lblSDTKH.setHorizontalAlignment(SwingConstants.LEFT);
		lblSDTKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSDTKH.setBounds(269, 35, 188, 33);
		panel_1.add(lblSDTKH);

		txtSDTKH = new JTextField();
		txtSDTKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSDTKH.setColumns(10);
		txtSDTKH.setBounds(268, 65, 200, 40);
		panel_1.add(txtSDTKH);

		JLabel lblNgayDat = new JLabel("Ngày Đặt");
		lblNgayDat.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgayDat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNgayDat.setBounds(509, 35, 188, 33);
		panel_1.add(lblNgayDat);

		dateNgayDat = new JDateChooser();
		panel_1.add(dateNgayDat);
		dateNgayDat.setDateFormatString("yyyy-MM-dd");
		dateNgayDat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dateNgayDat.setBounds(508, 65, 200, 40);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 178, 33);
		panel_1.add(panel);

		JLabel lblNewLabel = new JLabel("Tìm Hóa Đơn");
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

		btnInHoaDon = new JButton("In Hóa Đơn");
		btnInHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnInHoaDon.setBounds(1167, 65, 150, 40);
		panel_1.add(btnInHoaDon);
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBounds(1357, 65, 150, 40);
		panel_1.add(btnLamMoi);
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tblHoaDon.setRowSorter(null);

			}
		});
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 192, 1540, 247);
		add(scrollPane_1);
		String[] header1 = { "Mã Hóa Đơn", "Tên Khách Hàng", "Số Điện Thoại", "Nhân Viên Lập Đơn", "Ngày Lập Đơn","Ngày hàng về dự kiến","Tổng Tiền" };
		modelHoaDon = new DefaultTableModel(header1, 0);
		tblHoaDon = new JTable(modelHoaDon);
		tblHoaDon.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 16));
		tblHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane_1.setViewportView(tblHoaDon);
		tblHoaDon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int r = tblHoaDon.getSelectedRow();
				String MaHoaDon = modelHoaDon.getValueAt(r, 0).toString();
				txtHoaDon.setText(modelHoaDon.getValueAt(r, 0).toString());
				txtSDTKH.setText(modelHoaDon.getValueAt(r, 2).toString());
				dateNgayDat.setDate((Date) modelHoaDon.getValueAt(r, 4));
				cBNhanVienThanhToan.setSelectedItem(modelHoaDon.getValueAt(r, 3));
				DocDuLieuCTDH(MaHoaDon);

			}
		});
		//TODO dọc    dữ liệu
		docDuLieuHD();


		JLabel lblChiTietHD = new JLabel("CHI TIẾT HÓA ĐƠN");
		lblChiTietHD.setHorizontalAlignment(SwingConstants.CENTER);
		lblChiTietHD.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblChiTietHD.setBounds(0, 470, 1540, 37);
		add(lblChiTietHD);

		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(0, 506, 1540, 329);
		add(scrollPane_1_1);
		String[] header = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Nhà Cung cấp", "Giá Bán", "Số Lượng", "Danh Mục"};
		modelCTHoaDon = new DefaultTableModel(header, 0);
		tblChITietHoaDon = new JTable(modelCTHoaDon);
		tblChITietHoaDon.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 16));
		tblChITietHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane_1_1.setViewportView(tblChITietHoaDon);
		btnTimHoaDon.addActionListener(this);

		lblDonHang = new JLabel("");
		lblDonHang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDonHang.setBounds(117, 470, 126, 37);
		add(lblDonHang);
		btnInHoaDon.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnTimHoaDon.addActionListener(this);

		// thay đổi màu của tất cả các buttom thành màu #716DF2 và chữ của các buttom thành màu trắng
		btnTimHoaDon.setBackground(new Color(113, 109, 242));
		btnTimHoaDon.setForeground(Color.WHITE);
		btnInHoaDon.setBackground(new Color(113, 109, 242));
		btnInHoaDon.setForeground(Color.WHITE);
		btnLamMoi.setBackground(new Color(113, 109, 242));
		btnLamMoi.setForeground(Color.WHITE);
		btnTimHoaDon.addActionListener(this);
		btnInHoaDon.addActionListener(this);
		cBNhanVienThanhToan.setBackground(new Color(148, 162, 242));
		cBNhanVienThanhToan.setForeground(Color.WHITE);

		tblHoaDon.setBackground(new Color(236, 242, 255));
		tblChITietHoaDon.setBackground(new Color(236, 242, 255));
		tblHoaDon.getTableHeader().setBackground(new Color(148, 162, 242));
		tblChITietHoaDon.getTableHeader().setBackground(new Color(148, 162, 242));
		panel_1.setBackground(new Color(236, 242, 255));
		// thêm icon vào button với đường dẫn src/icon
		btnTimHoaDon.setIcon(new ImageIcon("src/icon/search.png"));
		btnInHoaDon.setIcon(new ImageIcon("src/icon/completed2.png"));
		btnLamMoi.setIcon(new ImageIcon("src/icon/loading.png"));
		setBackground(new Color(149, 189, 255));

	}




	public void docDuLieuHD() {
		// TODO Auto-generated method stub
		ArrayList<HoaDonDatHang> dsHD = hddh_dao.layThongTin();
		modelHoaDon.setRowCount(0);
		double tong =0;
		for (HoaDonDatHang hd : dsHD) {
			KhachHang kh = kh_dao.TimKhachHang(hd.getKhachHang().getMaKH());
			NhanVien nv = nv_dao.TimNhanVien(hd.getNhanVien().getMaNhanVien());
			ArrayList<ChiTietHoaDonDatHang> dsCT = cthddh_dao.TimHoaDon(hd.getMaHoaDon());
			for (ChiTietHoaDonDatHang ct : dsCT) {
				ArrayList<SanPham> dssp = sp_dao.timmSanPham(ct.getSanPham().getMaSanPham());
				for(SanPham sp : dssp) {
					tong += ct.getSoLuong() * sp.getGiaBan();
				}
			}
			String TongTien = vn.format(tong)+" VND";
			modelHoaDon.addRow(
					new Object[] { hd.getMaHoaDon(), kh.getTenKH(), kh.getSoDT(), nv.getHoTen(), hd.getNgayLapHD(),hd.getNgayNhanHangDuKien(),TongTien });
		}
	}
	public void DocDuLieuCTDH(String id) {
		ArrayList<ChiTietHoaDonDatHang> dsCT = cthddh_dao.TimHoaDon(id);
		ArrayList<DanhMucSanPham> dsDM = dm_dao.layThongTin();
		ArrayList<NhaCungCapSanPham> dsNCC = ncc_DAO.layThongTin();
		modelCTHoaDon.setRowCount(0);

		for (ChiTietHoaDonDatHang ct : dsCT) {
			// lấy dữ liệu chi tiết hóa đơn đưa vào bảng modelCTHoaDon
			ArrayList<SanPham> dssp = sp_dao.timmSanPham(ct.getSanPham().getMaSanPham());
			for (SanPham sp : dssp) {
				for(DanhMucSanPham dm: dsDM) {
					if(dm.getMaDanhMuc().equals(sp.getDanhMucSanPham().getMaDanhMuc())) {
						for(NhaCungCapSanPham ncc: dsNCC) {
							if(ncc.getMaNhaCungCap().equals(sp.getNhaCungCapSanPham().getMaNhaCungCap())) {
								modelCTHoaDon.addRow(new Object[] { sp.getMaSanPham(), sp.getTenSanPham(), ncc.getTenNCC(), sp.getGiaBan(),
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
		if(o.equals(btnTimHoaDon)){
			String maHD = txtHoaDon.getText();
			String soDT = txtSDTKH.getText();
			String ngayDat;
			if(dateNgayDat.getDate()==null){
				ngayDat="";
			}
			String nv = cBNhanVienThanhToan.getSelectedItem().toString();
			//   nếu nhân viênbangwfg tất cả thì truyền vào null
			if (dateNgayDat.getDate() == null) {
					 ngayDat = "";
				} else {
					ngayDat = dateNgayDat.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
				}
			TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(modelHoaDon);
			tblHoaDon.setRowSorter(sorter);
			List<RowFilter<Object,Object>>filters = new ArrayList<>();
			filters.add(RowFilter.regexFilter(maHD, 0));
			filters.add(RowFilter.regexFilter(soDT, 2));
			filters.add(RowFilter.regexFilter(ngayDat, 4));
			filters.add(RowFilter.regexFilter(nv, 3));
			RowFilter<Object,Object> filter = RowFilter.andFilter(filters);
			sorter.setRowFilter(filter);

		}
		if(o.equals(btnLamMoi)){
			// xóa dữ liệu trong các ô text
			txtHoaDon.setText("");
			txtSDTKH.setText("");
			dateNgayDat.setDate(null);
			cBNhanVienThanhToan.setSelectedItem("Tất cả");
			tblHoaDon.setRowSorter(null);
			docDuLieuHD();
		}
		if (o.equals(btnInHoaDon)) {
            try {
                generatePDFBill();
                JOptionPane.showMessageDialog(this, "Hóa đơn đã được in thành công!");          
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi in hóa đơn!");
            }
        } 
    }

	public void generatePDFBill() throws IOException {
	    // Get the main bill information
	    String maHD = txtHoaDon.getText();
	    String soDT = txtSDTKH.getText();
	    String nv = cBNhanVienThanhToan.getSelectedItem().toString();
	    String ngayDat = "";
	    if (dateNgayDat.getDate() != null) {
	        ngayDat = new SimpleDateFormat("yyyy-MM-dd").format(dateNgayDat.getDate());
	    }

	    // Retrieve the details of ChiTietHoaDon
	    ArrayList<ChiTietHoaDonDatHang> chiTietList = cthddh_dao.TimHoaDon(maHD);

	    // Create a PDF document
	    try (PDDocument document = new PDDocument()) {
	        PDPage page = new PDPage();
	        document.addPage(page);

	        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
	            PDType0Font font = PDType0Font.load(document, getClass().getResourceAsStream("/lib/Helvetica.ttf"));
	            contentStream.setFont(font, 12);

	            contentStream.beginText();
	            contentStream.newLineAtOffset(100, 700);
	            contentStream.showText("HÓA ĐƠN");
	            contentStream.newLine(); // Move to the next line
	            contentStream.endText();

	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            String billDate = sdf.format(new Date());

	            contentStream.beginText();
	            contentStream.newLineAtOffset(100, 680);
	            contentStream.showText("Ngày: " + billDate);
	            contentStream.newLineAtOffset(0, -20); // Move to the next line
	            contentStream.showText("Mã Hóa Đơn: " + maHD);
	            contentStream.newLineAtOffset(0, -20); // Move to the next line
	            contentStream.showText("Số điện thoại khách hàng: " + soDT);
	            contentStream.newLineAtOffset(0, -20); // Move to the next line
	            contentStream.showText("Nhân viên thanh toán: " + nv);
	            contentStream.newLineAtOffset(0, -20); // Move to the next line
	            contentStream.endText();


	            // Print details of ChiTietHoaDon
	            int yOffset = 600; // Initial offset for details
	            for (ChiTietHoaDonDatHang chiTiet : chiTietList) {
	                contentStream.beginText();
	                contentStream.newLineAtOffset(100, yOffset);
	                contentStream.showText("Mã sản phẩm: " + chiTiet.getSanPham().getMaSanPham());
	                yOffset -= 20; // Move to the next line
	                contentStream.newLineAtOffset(0, -20);
	                contentStream.showText("Tên sản phẩm: " + chiTiet.getSanPham().getTenSanPham());
	                yOffset -= 20; // Move to the next line
	                // Add more details as needed
	                contentStream.endText();
	                yOffset -= 20; // Adjust the offset for the next detail
	            }
	        }

	        // Save and open the document
	        String fileName = "Bill.pdf";
	        document.save(fileName);
	        Desktop.getDesktop().open(new File(fileName));
	    }
	}



    
}
