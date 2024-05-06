package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.toedter.calendar.JDateChooser;

import dao.ChiTietHoaDonDAO;
import dao.DanhMucDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.NhaCungCapDAO;
import dao.NhanVienDAO;
import dao.SanPhamDAO;
import entity.ChiTietHoaDon;
import entity.DanhMucSanPham;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhaCungCapSanPham;
import entity.NhanVien;
import entity.SanPham;


public class FrmHoaDon extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTable tblChITietHoaDon;
	private JTable tblHoaDon;
    private DefaultTableModel modelHoaDon, modelCTHoaDon;
	private NhaCungCapDAO ncc_DAO;

	private ChiTietHoaDonDAO cthd_dao;
	private KhachHangDAO kh_dao;
	private SanPhamDAO sp_dao;
	private DanhMucDAO dm_dao;
	private JButton btnLamMoi;
	private JDateChooser dateNgayDat;

	private JButton btnTimHoaDon;
	private JLabel lblDonHang;
	private NhanVienDAO nv_dao;
	private HoaDonDAO hd_dao;
	protected String MaDonHang;
	protected int r;
	private Locale localeVN = new Locale("vi", "VN");
	NumberFormat vn = NumberFormat.getInstance(localeVN);
	private JButton btnInHoaDon;


	public FrmHoaDon() {
		setLayout(null);
        kh_dao = new KhachHangDAO();
		sp_dao = new SanPhamDAO();
		dm_dao = new DanhMucDAO();
		nv_dao = new NhanVienDAO();
		hd_dao = new HoaDonDAO();
		cthd_dao = new ChiTietHoaDonDAO();
		ncc_DAO = new NhaCungCapDAO();
		JPanel panelTitle = new JPanel();
		JLabel lblTitLe = new JLabel("DANH SÁCH HOÁ ĐƠN");
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


		btnTimHoaDon = new JButton("Tìm Hóa Đơn");
		btnTimHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTimHoaDon.setBounds(450, 65, 150, 40);
		panel_1.add(btnTimHoaDon);


		JLabel lblNgayDat = new JLabel("Ngày Lập");
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

		JLabel lblNewLabel = new JLabel("Tìm Hóa Đơn");
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

		btnInHoaDon = new JButton("In Hóa Đơn");
		btnInHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnInHoaDon.setBounds(1267, 65, 150, 40);
		panel_1.add(btnInHoaDon);
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBounds(657, 65, 150, 40);
		panel_1.add(btnLamMoi);
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tblHoaDon.setRowSorter(null);

			}
		});
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 182, 1540, 247);
		add(scrollPane_1);
		String[] header1 = { "Mã Hóa Đơn", "Tên Khách Hàng", "Số Điện Thoại", "Nhân Viên Lập Đơn", "Ngày Thanh Toán","Tổng Tiền" };
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
				dateNgayDat.setDate((Date) modelHoaDon.getValueAt(r, 4));
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

		tblHoaDon.setBackground(new Color(236, 242, 255));
		tblChITietHoaDon.setBackground(new Color(236, 242, 255));
		tblHoaDon.getTableHeader().setBackground(new Color(148, 162, 242));
		tblChITietHoaDon.getTableHeader().setBackground(new Color(148, 162, 242));
		panel_1.setBackground(new Color(236, 242, 255));

		setBackground(new Color(149, 189, 255));

	}




	public void docDuLieuHD() {
	    modelHoaDon.setRowCount(0);
	    ArrayList<HoaDon> dsHD = hd_dao.layThongTin();
	    for (HoaDon hd : dsHD) {
	        KhachHang kh = kh_dao.TimKhachHang(hd.getKhachHang().getMaKH());
	        NhanVien nv = nv_dao.TimNhanVien(hd.getNhanVien().getMaNhanVien());
	        ArrayList<ChiTietHoaDon> dsCT = cthd_dao.TimCTHoaDon(hd.getMaHoaDon());
	        double tong = 0;
	        for (ChiTietHoaDon ct : dsCT) {
	            SanPham sp = sp_dao.timSanPham(ct.getSanPham().getMaSanPham()).get(0);
	            tong += ct.getSoLuong() * sp.getGiaBan();
	        }
	        String TongTien = vn.format(tong) + " VND";
	        modelHoaDon.addRow(new Object[] { hd.getMaHoaDon(), kh.getTenKH(), kh.getSoDT(), nv.getHoTen(), hd.getNgayLapHD(), TongTien });
	    }
	}


	public void DocDuLieuCTDH(String id) {
		ArrayList<ChiTietHoaDon> dsCT = cthd_dao.TimCTHoaDon(id);
		ArrayList<DanhMucSanPham> dsDM = dm_dao.layThongTin();
		ArrayList<NhaCungCapSanPham> dsNCC = ncc_DAO.layThongTin();
		modelCTHoaDon.setRowCount(0);

		for (ChiTietHoaDon ct : dsCT) {
			// lấy dữ liệu chi tiết hóa đơn đưa vào bảng modelCTHoaDon
			ArrayList<SanPham> dssp = sp_dao.timSanPham(ct.getSanPham().getMaSanPham());
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
		if (o.equals(btnTimHoaDon)) {
		    String ngayDat = "";

		    if (dateNgayDat.getDate() != null) {
		        ngayDat = dateNgayDat.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
		    }

		    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(modelHoaDon);
		    tblHoaDon.setRowSorter(sorter);
		    List<RowFilter<Object,Object>> filters = new ArrayList<>();
		    
		    if (!ngayDat.isEmpty()) {
		        filters.add(RowFilter.regexFilter(ngayDat, 4));
		    }
		    
		    RowFilter<Object,Object> filter = RowFilter.andFilter(filters);
		    sorter.setRowFilter(filter);
		}

		if(o.equals(btnLamMoi)){
			// xóa dữ liệu trong các ô text

			dateNgayDat.setDate(null);
			tblHoaDon.setRowSorter(null);
			docDuLieuHD();
		}
		if (o.equals(btnInHoaDon)) {
		    try {
		        //Tạo pdf
		        PDDocument document = new PDDocument();
		        // Tạo trang trống
		        PDPage page = new PDPage();
		        document.addPage(page);
		        // Thêm font
		        PDType0Font font = PDType0Font.load(document, new File("src\\lib\\Helvetica.ttf")); 
		        
		        PDPageContentStream contentStream = new PDPageContentStream(document, page);

		        contentStream.beginText();
		        contentStream.setFont(font, 12);
		        contentStream.newLineAtOffset(50, 750);

		        contentStream.showText("Ma HD: " + tblHoaDon.getValueAt(0, 0).toString());
		        contentStream.newLineAtOffset(0, -20);
		        contentStream.showText("Ngay Lap: " + tblHoaDon.getValueAt(0, 4).toString());
		        contentStream.newLineAtOffset(0, -20);
		        contentStream.showText("Nhan vien: " + tblHoaDon.getValueAt(0, 3).toString());
		        contentStream.newLineAtOffset(0, -20);
		        contentStream.showText("Khach hang: " + tblHoaDon.getValueAt(0, 1).toString());
		        contentStream.newLineAtOffset(0, -20);
		        contentStream.showText("=====================================================================");
		        contentStream.newLineAtOffset(10, -20);

		        // Headers
		        contentStream.showText("Ma SP");
		        contentStream.newLineAtOffset(110, 0);
		        contentStream.showText("Ten SanPham");
		        contentStream.newLineAtOffset(140, 0);
		        contentStream.showText("GiaBan");
		        contentStream.newLineAtOffset(95, 0);
		        contentStream.showText("SL");
		        contentStream.newLineAtOffset(80, 0);
		        contentStream.showText("ThanhTien");
		        contentStream.newLineAtOffset(-430, -20);

		        for (int i = 0; i < tblChITietHoaDon.getRowCount(); i++) {
		            float yOffset = 0;
		            float xOffsetTenSP = 160; 
		            float xOffsets = 95;
		            float thanhTien = Float.parseFloat(tblChITietHoaDon.getValueAt(i, 3).toString()) * Float.parseFloat(tblChITietHoaDon.getValueAt(i, 4).toString()); // Calculate ThanhTien

		            for (int j = 0; j < tblChITietHoaDon.getColumnCount(); j++) {
		                if (j == 2 || j == 5) {
		                    continue; //skip cột 2 vs 5
		                }
		                float xOffset = (j == 1) ? xOffsetTenSP : xOffsets;

		                contentStream.showText(tblChITietHoaDon.getValueAt(i, j).toString());
		                contentStream.newLineAtOffset(xOffset, yOffset);
		            }

		            contentStream.showText(String.valueOf(thanhTien));
		            contentStream.newLineAtOffset(-445, -20);
		        }

		        contentStream.newLineAtOffset(90, -20);
		        contentStream.showText("==============================================");
		        contentStream.newLineAtOffset(70, -20);
		        contentStream.showText("Tong Tien: " + tblHoaDon.getValueAt(0, 5).toString());
		        contentStream.newLineAtOffset(15, -20);
		        contentStream.showText("Cam on quy khach !!!!!!!!");

		        contentStream.endText();
		        contentStream.close();

		        // Lưu
		        String filePath = "src\\bill.pdf";
		        document.save(filePath);
		        document.close();

		        JOptionPane.showMessageDialog(null, "Xuất bill thành công !");
		    } catch (IOException ex) {
		        ex.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Lỗi không thể xuất bill");
		    }
		}
	}

}

