-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Waktu pembuatan: 06 Mar 2020 pada 17.26
-- Versi server: 10.3.15-MariaDB
-- Versi PHP: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `app_book`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `buku`
--

CREATE TABLE `buku` (
  `id_buku` varchar(30) NOT NULL,
  `judul` varchar(30) NOT NULL,
  `pengarang` varchar(30) NOT NULL,
  `penerbit` varchar(30) NOT NULL,
  `kategori` varchar(30) NOT NULL,
  `isbn` text NOT NULL,
  `stok` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `buku`
--

INSERT INTO `buku` (`id_buku`, `judul`, `pengarang`, `penerbit`, `kategori`, `isbn`, `stok`) VALUES
('B0001', 'Bahasa Indonesia', 'Hikigaya', 'Gramedia', 'Pelajaran', '123 777 999', 100),
('B0002', 'Matematika', 'hikigaya', 'Gramedia', 'Pelajaran', '333 8888', 100),
('B0003', 'PC komplite', 'hikigaya haciman', 'Erlangga', 'Novel', '3444 666 6666', 100),
('B0004', 'Kancil', 'doni', 'Xstudio', 'Komik', '123-5555-7777-322', 100),
('B0005', 'C++', 'andrew j', 'erlangga', 'Pelajaran', '122 322', 100);

-- --------------------------------------------------------

--
-- Struktur dari tabel `kategori_buku`
--

CREATE TABLE `kategori_buku` (
  `id` varchar(20) NOT NULL,
  `nama_kategori` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `kategori_buku`
--

INSERT INTO `kategori_buku` (`id`, `nama_kategori`) VALUES
('A01', 'Komik'),
('A02', 'Novel'),
('A03', 'Pelajaran'),
('A04', 'Sastra');

-- --------------------------------------------------------

--
-- Struktur dari tabel `login`
--

CREATE TABLE `login` (
  `id` varchar(50) NOT NULL,
  `nama_depan` varchar(50) NOT NULL,
  `nama_belakang` varchar(50) NOT NULL,
  `password` varchar(30) NOT NULL,
  `hak` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `login`
--

INSERT INTO `login` (`id`, `nama_depan`, `nama_belakang`, `password`, `hak`) VALUES
('admin', 'admin', 'admin', 'admin123', 'admin'),
('user', 'user', 'user', 'user123', 'user'),
('admin', 'admin', 'admin', 'admin123', 'admin'),
('user', 'user', 'user', 'user123', 'user');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengembalian`
--

CREATE TABLE `pengembalian` (
  `id_pinjam` varchar(30) NOT NULL,
  `npm` varchar(30) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `id_buku` varchar(30) NOT NULL,
  `judul` varchar(30) NOT NULL,
  `jumlah` int(30) NOT NULL,
  `tgl_pinjam` varchar(30) NOT NULL,
  `tgl_balik` varchar(30) NOT NULL,
  `status` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pengembalian`
--

INSERT INTO `pengembalian` (`id_pinjam`, `npm`, `nama`, `id_buku`, `judul`, `jumlah`, `tgl_pinjam`, `tgl_balik`, `status`) VALUES
('P0001', '201643500', 'Andika', 'B0005', 'C++', 2, '06 Maret 2020', '10 Maret 2020', 'sudah kembali');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengunjung`
--

CREATE TABLE `pengunjung` (
  `id` varchar(50) NOT NULL,
  `nis` varchar(50) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `telpon` varchar(30) NOT NULL,
  `tanggal` varchar(200) NOT NULL,
  `jam` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pengunjung`
--

INSERT INTO `pengunjung` (`id`, `nis`, `nama`, `telpon`, `tanggal`, `jam`) VALUES
('P01', '201643500199', 'ali', '0878 7897 0878', '16-2-2020', '22:13:30'),
('P02', '201643500', 'Andika', '0878 9999 8888', '25-2-2020', '21:00:39'),
('P03', '201643500199', 'budi', '021 8888', '25-2-2020', '23:05:39'),
('P04', '201643500199', 'budi', '021 8888', '6-3-2020', '22:50:45'),
('P05', '201643500', 'Andika', '0878 9999 8888', '6-3-2020', '22:51:02'),
('P06', '201643500', 'Andika', '0878 9999 8888', '6-3-2020', '23:12:32'),
('P07', '201643500', 'Andika', '0878 9999 8888', '6-3-2020', '23:14:48'),
('P08', '201643500', 'Andika', '0878 9999 8888', '6-3-2020', '23:15:09');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pinjaman`
--

CREATE TABLE `pinjaman` (
  `id_pinjam` varchar(30) NOT NULL,
  `npm` varchar(30) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `id_buku` varchar(30) NOT NULL,
  `judul` varchar(30) NOT NULL,
  `jumlah` int(30) NOT NULL,
  `tgl_pinjam` varchar(30) NOT NULL,
  `tgl_balik` varchar(30) NOT NULL,
  `status` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pinjaman`
--

INSERT INTO `pinjaman` (`id_pinjam`, `npm`, `nama`, `id_buku`, `judul`, `jumlah`, `tgl_pinjam`, `tgl_balik`, `status`) VALUES
('P0001', '201643500187', 'Sonia', 'B0004', 'Kancil', 2, '06 Maret 2020', '10 Maret 2020', 'Belum Kembali'),
('P0002', '201643500187', 'Sonia', 'B0005', 'C++', 3, '06 Maret 2020', '09 Maret 2020', 'Belum Kembali'),
('P0003', '201643500191', 'Doni', 'B0005', 'C++', 2, '06 Maret 2020', '08 Maret 2020', 'Belum Kembali'),
('P0004', '201634501', 'Susi', 'B0004', 'Kancil', 1, '06 Maret 2020', '10 Maret 2020', 'Belum Kembali'),
('P0005', '201643500191', 'Doni', 'B0005', 'C++', 1, '06 Maret 2020', '08 Maret 2020', 'Belum Kembali'),
('P0006', '201643500', 'Andika', 'B0005', 'C++', 2, '06 Maret 2020', '10 Maret 2020', 'Belum Kembali');

-- --------------------------------------------------------

--
-- Struktur dari tabel `siswa`
--

CREATE TABLE `siswa` (
  `nis` varchar(50) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `alamat` varchar(200) NOT NULL,
  `no_hp` varchar(30) NOT NULL,
  `jenis_kelamin` varchar(40) NOT NULL,
  `kelas` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `siswa`
--

INSERT INTO `siswa` (`nis`, `nama`, `alamat`, `no_hp`, `jenis_kelamin`, `kelas`) VALUES
('201634501', 'Susi', 'jl cantik no 45 Jakarta Barat', '021 7666566', 'Perempuan', 'X'),
('201643500', 'Andika', 'jl swadaya 2b no 45 jakarta timur', '0878 9999 8888', 'Laki-Laki', 'X'),
('201643500187', 'Sonia', 'Jakarta Barata', '021 88888', 'Perempuan', 'XI'),
('201643500191', 'Doni', 'JAwa Timur', '021 7777', 'Laki-Laki', 'X'),
('201643500199', 'budi', 'jakarta selatan', '021 8888', 'Laki-Laki', 'XII'),
('2016435009', 'Budi Sudorsono', 'Jl Kangen no 13 Jakarta Timur', '0812 999999999', 'Laki-Laki', 'XI'),
('2016435123', 'Anita Sari', 'jl cendana no 55 Jawa Barat', '0878 767754563', 'Perempuan', 'XII');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`id_buku`);

--
-- Indeks untuk tabel `kategori_buku`
--
ALTER TABLE `kategori_buku`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `pengembalian`
--
ALTER TABLE `pengembalian`
  ADD PRIMARY KEY (`id_pinjam`);

--
-- Indeks untuk tabel `pengunjung`
--
ALTER TABLE `pengunjung`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `pinjaman`
--
ALTER TABLE `pinjaman`
  ADD PRIMARY KEY (`id_pinjam`);

--
-- Indeks untuk tabel `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`nis`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
