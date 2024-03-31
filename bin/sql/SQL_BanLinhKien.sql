CREATE TABLE NhaCungCapLinhKien (
    MaNhaCungCap NVARCHAR(20) PRIMARY KEY,
    TenNCC NVARCHAR(100) NOT NULL,
    DiaChi NVARCHAR(200) NOT NULL,
    SoDienThoai NVARCHAR(20) NOT NULL
);
GO

CREATE TABLE DanhMucLinhKien (
    maDanhMuc NVARCHAR(20) PRIMARY KEY,
    tenDanhMuc NVARCHAR(100) NOT NULL
)
GO
CREATE TABLE LinhKien (
    maLinhKien NVARCHAR(20) PRIMARY KEY,
    tenLinhKien NVARCHAR(100) NOT NULL,
    soLuong INT NOT NULL,
    giaBan FLOAT NOT NULL,
    thoiGianBaoHanh INT NOT NULL,
    maDanhMuc NVARCHAR(20) NOT NULL,
    maNhaCungCap NVARCHAR(20) NOT NULL,
    FOREIGN KEY (maDanhMuc) REFERENCES DanhMucLinhKien(maDanhMuc),
    FOREIGN KEY (maNhaCungCap) REFERENCES NhaCungCapLinhKien(MaNhaCungCap)
);

GO
CREATE TABLE KhachHang (
    maKH NVARCHAR(20) PRIMARY KEY,
    tenKH NVARCHAR(100) NOT NULL,
    soDT NVARCHAR(20) NOT NULL,
    eMail NVARCHAR(50) NOT NULL,
    diaChi NVARCHAR(200) NOT NULL
);
GO
CREATE TABLE NhanVien (
    maNhanVien NVARCHAR(20) PRIMARY KEY,
    hoTen NVARCHAR(100) NOT NULL,
    ngaySinh DATE NOT NULL,
    diaChi NVARCHAR(200) NOT NULL,
    soDT NVARCHAR(20) NOT NULL,
    eMail NVARCHAR(50) NOT NULL
);
GO
CREATE TABLE HoaDon (
    maHoaDon NVARCHAR(20) PRIMARY KEY,
    maKH NVARCHAR(20) NOT NULL,
    maNV NVARCHAR(20) NOT NULL,
    ngayGiaoDuKien DATE NOT NULL,
    ngayLapHD DATE NOT NULL,
    FOREIGN KEY (maKH) REFERENCES KhachHang(maKH),
    FOREIGN KEY (maNV) REFERENCES NhanVien(maNhanVien)
);

GO
CREATE TABLE ChiTietHD (
    maLinhKien NVARCHAR(20) NOT NULL,
    maHoaDon NVARCHAR(20) NOT NULL,
    soLuong INT NOT NULL,
    PRIMARY KEY (maLinhKien, maHoaDon),
    FOREIGN KEY (maLinhKien) REFERENCES LinhKien(maLinhKien),
    FOREIGN KEY (maHoaDon) REFERENCES HoaDon(maHoaDon),
);

-- Dữ liệu mẫu cho bảng NhaCungCapLinhKien
INSERT INTO NhaCungCapLinhKien (MaNhaCungCap, TenNCC, DiaChi, SoDienThoai) VALUES
    ('NCC0001', 'Công ty TNHH Linh kiện số 1', 'Số 10, Đường ABC, Quận XYZ, TP. Hồ Chí Minh', '0123456789'),
    ('NCC0002', 'Công ty TNHH Linh kiện số 2', 'Số 20, Đường DEF, Quận UVW, TP. Hà Nội', '0123456788'),
    ('NCC0003', 'Công ty TNHH Linh kiện số 3', 'Số 30, Đường GHI, Quận RST, TP. Đà Nẵng', '0123456787'),
    ('NCC0004', 'Công ty TNHH Linh kiện số 4', 'Số 40, Đường JKL, Quận MNO, TP. Cần Thơ', '0123456786');

-- Dữ liệu mẫu cho bảng DanhMucLinhKien
INSERT INTO DanhMucLinhKien (maDanhMuc, tenDanhMuc) VALUES
    ('DM0001', 'Vi xử lý'),
    ('DM0002', 'Bo mạch chủ'),
    ('DM0003', 'Ổ cứng'),
    ('DM0004', 'RAM'),
    ('DM0005', 'Card đồ họa');

-- Dữ liệu mẫu cho bảng LinhKien
INSERT INTO LinhKien (maLinhKien, tenLinhKien, soLuong, giaBan, thoiGianBaoHanh, maDanhMuc, maNhaCungCap) VALUES
    ('LK0001', 'Intel Core i7-10700K', 50, 9000000, 12, 'DM0001', 'NCC0001'),
    ('LK0002', 'ASUS Prime B450M-A', 100, 2000000, 24, 'DM0002', 'NCC0002'),
    ('LK0003', 'WD Blue 1TB 7200rpm', 200, 1500000, 36, 'DM0003', 'NCC0003'),
    ('LK0004', 'Corsair Vengeance LPX 16GB DDR4', 150, 3000000, 24, 'DM0004', 'NCC0004'),
    ('LK0005', 'NVIDIA GeForce RTX 3060 Ti', 75, 12000000, 12, 'DM0005', 'NCC0001');
INSERT INTO KhachHang(maKH, tenKH, soDT, eMail, diaChi)
VALUES
('KH0001', 'Nguyễn Văn A', '0123456789', 'nguyenvana@gmail.com', 'Số 10 Nguyễn Chí Thanh, Hà Nội'),
('KH0002', 'Trần Thị B', '0234567890', 'tranthib@gmail.com', 'Số 20 Hoàng Quốc Việt, Hà Nội'),
('KH0003', 'Lê Văn C', '0345678901', 'levanc@gmail.com', 'Số 30 Trần Duy Hưng, Hà Nội');

INSERT INTO NhanVien(maNhanVien, hoTen, ngaySinh, diaChi, soDT, eMail)
VALUES
('NV0001', 'Phạm Văn D', '1990-01-01', 'Hà Nội', '0987654321', 'phamvand@gmail.com'),
('NV0002', 'Hoàng Thị E', '1995-05-05', 'Hà Nội', '0987654322', 'hoangthie@gmail.com');
