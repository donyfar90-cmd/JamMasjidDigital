# Jam Masjid Digital (Starter APK)

Project Android Jam Masjid Digital, WebView fullscreen, pengaturan web lokal, jadwal yang dapat diedit, countdown salat berikutnya, running text, penyimpanan pengaturan lokal.

## Build APK via GitHub Actions
1. Upload semua isi ZIP ini ke repository GitHub (jangan upload ZIP sebagai file tunggal; extract dahulu).
2. Tab Actions -> workflow `Build Android APK` -> Run workflow (atau push otomatis).
3. Buka workflow selesai, download artifact `JamMasjidDigital-debug-apk`.

## Panel pengaturan lokal
APK menjalankan server lokal port 8080. Pada perangkat yang menjalankan APK, webview membuka localhost. Untuk mengakses panel dari HP lain, kedua perangkat harus berada di Wi-Fi sama. Buka `http://IP-ANDROID:8080/admin`. Catatan: server starter ini belum menggunakan password admin; jangan gunakan pada Wi-Fi publik.

## Catatan status
Ini paket starter. Jadwal default masih jadwal contoh dan harus diubah ke jadwal resmi masjid melalui panel. Perhitungan jadwal astronomi otomatis, tanggal Hijriyah, audio adzan/qiroah, slideshow, dan mode countdown iqomah akan ditambahkan pada tahap lanjutan.
