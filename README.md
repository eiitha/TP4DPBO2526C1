# TP4DPBO2526C1 - Data Actor
## Janji
Saya Jillena Surbakti dengan NIM 2501485 mengerjakan Tugas Praktikum 4 pada Mata Kuliah Desain dan Pemrograman Berorientasi Objek (DPBO) untuk keberkahan-Nya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin

## Struktur Folder
```text
TP4DPBO2526C1/
├── README.md
│
├── PeopleData/
│   ├── PeopleData.iml
│   └── src/
│       ├── Person.java
│       ├── PeopleMenu.java
│       └── PeopleMenu.form
│
└── Dokumentasi/             
    ├── cancel.mp4
    ├── Delete.mp4
    ├── error_1.mp4
    ├── error_2.mp4
    ├── error_3.mp4
    ├── add.mp4
    └── update.mp4            

```

## Desain
- **Read Data**: Menampilkan daftar aktor secara dinamis di dalam `JTable` lengkap dengan penomoran otomatis.
- **Create (Add) Data**: Menambahkan data aktor baru ke dalam tabel dengan validasi input.
- **Update Data**: Mengklik baris pada tabel akan mengisi otomatis form input untuk mempermudah proses pengeditan data.
- **Delete Data**: Menghapus data aktor yang dipilih disertai **dialog konfirmasi (pop-up confirmation)** sebelum data dihapus dari sistem.
- **Form Reset (Cancel)**: Mengosongkan seluruh kolom input dan mengembalikan fungsi tombol utama ke mode awal (Add).
- **Validasi Input & Error Handling**:
  - **Format ID**: ID wajib diawali teks `ACT` dan diikuti oleh angka (contoh: `ACT005`).
  - **Pemeriksaan Duplikasi**: Mencegah penambahan data dengan ID Aktor yang sudah terdaftar.
  - **Validasi Numerik**: Memastikan kolom Tahun Lahir hanya dapat diisi oleh karakter angka.
  - **Input Wajib Isi**: Menampilkan pesan peringatan jika ada kolom input teks yang belum diisi.
- **Komponen UI Tambahan**:
  - `JComboBox`: Komponen dropdown pilihan untuk **Genre Spesialis** dan **Status Awards**.
  - `JRadioButton`: Komponen pilihan jenis kelamin (**Gender**) yang dikelompokkan dengan `ButtonGroup` (`Male` / `Female`).

---

## Alur Kerja Program

1. **Inisialisasi Aplikasi**:
* Program berjalan dengan memuat data sampel awal aktor yang tersimpan di dalam `ArrayList<Person>`.
* Tabel `JTable` merender data secara otomatis, sedangkan dropdown `JComboBox` dan radio button `JRadioButton` disiapkan dengan pilihan default.
* Tombol **Delete** disembunyikan secara otomatis sampai ada baris tabel yang dipilih.

2. **Menambahkan Data Baru (Insert)**:
* Lengkapi seluruh kolom input (Actor ID, Actor Name, Birth Year, Genre, Award Status, dan Gender).
* Klik tombol **Add**. Program akan memeriksa format ID (`ACT...`), keunikan ID, kelengkapan form, dan validasi angka pada tahun lahir.
* Jika validasi berhasil, data tersimpan, tabel diperbarui, pop-up sukses muncul, dan form kembali bersih.

3. **Mengubah Data (Update)**:
* Klik salah satu baris pada `JTable`. Seluruh data baris tersebut akan dimuat ke kolom input form.
* Tombol **Add** akan berubah tulisan menjadi **Update**, dan tombol **Delete** akan dimunculkan.
* Ubah data yang diinginkan lalu tekan tombol **Update** untuk menyimpan perubahan.

4. **Menghapus Data (Delete)**:
* Pilih baris data pada tabel yang ingin dihapus, lalu klik tombol **Delete**.
* Pop-up dialog konfirmasi (`JOptionPane`) akan muncul menanyakan kepastian hapus data. Data hanya terhapus jika pengguna memilih opsi *Yes*.
  
5. **Membatalkan / Mengosongkan Form (Cancel)**:
* Klik tombol **Cancel** kapan saja untuk mengosongkan seluruh isi kolom input dan mengembalikan status tombol ke fungsi **Add**.

---

## Dokumentasi 
### Cancel


https://github.com/user-attachments/assets/14bc87fb-6b41-429b-b782-7d2398bfab71


### Delete


https://github.com/user-attachments/assets/597df4a3-6357-488f-a2cb-17edcc21b226


### Error 1
Jika user memasukkan ID yang sama dengan data yang sudah ada


https://github.com/user-attachments/assets/c6cd5a25-88f7-47dd-a569-2dff5c31f05f


### Error 2
Jika user memasukkan ID bukan dengan awalan 'ACT'


https://github.com/user-attachments/assets/93026821-5fed-47ac-ac20-6ff3d4bbf38d


### Error 3
Jika user memasukkan Birth Year bukan menggunakan angka


https://github.com/user-attachments/assets/a03f9024-a2c2-4ff6-a10a-f79fc7ca1a07


### Update


https://github.com/user-attachments/assets/36dec033-ef9e-4ce9-8c2d-415cc2527c79


### Add


https://github.com/user-attachments/assets/a6b34444-2792-4413-86e7-ed7dd9cba41f




