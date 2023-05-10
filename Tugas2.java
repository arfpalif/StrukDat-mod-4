import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Tugas2 {
    private HashMap<String, String[]> tabelData = new HashMap<String, String[]>();
    private HashMap<String, String> tabelSesiLogin = new HashMap<String, String>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Tugas2 program = new Tugas2();
        // tambahkan data login
        program.tabelSesiLogin.put("alifraihan@umm.ac.id", "alif");
        program.tabelSesiLogin.put("alifrfp@umm.ac.id", "alif");
        program.runMenu();
    }

    public void runMenu() {
        boolean isLoginSuccess = false;
        while (!isLoginSuccess) {
            System.out.flush();
            System.out.print("Masukkan email: ");
            String email = scanner.nextLine();
            System.out.print("Masukkan password: ");
            String password = scanner.nextLine();
    
            // cek apakah data email dan password sesuai dengan tabelSesiLogin
            if (tabelSesiLogin.containsKey(email) && tabelSesiLogin.get(email).equals(password)){ //get itu untuk mengambil key nya, tapi kalo equals ambil baca valuenya
                // cek apakah email menggunakan domain "@umm.ac.id"
                if (email.endsWith("@umm.ac.id")) {
                    System.out.println("Login berhasil.");
                    System.out.println("==========================");
                    boolean isRunning = true;
                    waitForEnter();
                    while (isRunning) {
                        System.out.println("==========================");
                        System.out.println("Program Data Praktikan");
                        System.out.println("==========================");
                        System.out.println("1. Tambah Data");
                        System.out.println("2. Tampilkan Data");
                        System.out.println("3. List NIM Praktikan");
                        System.out.println("4. List Nama Asisten");
                        System.out.println("5. Total Email");
                        System.out.println("6. Hapus Data");
                        System.out.println("7. Edit Data");
                        System.out.println("0. logout");
                        System.out.print("Masukkan pilihan Anda: ");
                        int pilihan = scanner.nextInt();
                        scanner.nextLine();
        
                        switch (pilihan) {
                            case 1:
                                tambahData();
                                waitForEnter();
                                break;
                            case 2:
                                tampilData();
                                waitForEnter();
                                break;
                            case 3:
                                listNimPraktikan();
                                waitForEnter();
                                break;
                            case 4:
                                listNamaAsisten();
                                waitForEnter();
                                break;
                            case 5:
                                totalEmail();
                                waitForEnter();
                                break;
                            case 6:
                                hapusData();
                                waitForEnter();
                                break;
                            case 7:
                                editData();
                                waitForEnter();
                                break;
                            case 0:
                                System.out.println("Logout berhasil.");
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                                break;
                                }
                    }
                } else {
                    System.out.println("Login gagal: email tidak menggunakan domain '@umm.ac.id'");
                    System.out.print("Coba lagi? (y/n): ");
                    String ulangi = scanner.nextLine();
                    if (!ulangi.equalsIgnoreCase("y")) {
                        break;
                    }
                }
            } else {
                System.out.println("Login gagal: email atau password salah.");
                System.out.print("Coba lagi? (y/n): ");
                String ulangi = scanner.nextLine();
                if (!ulangi.equalsIgnoreCase("y")) {
                    break;
                }
            }
        }
    }
    
    
    private boolean tambahData() {
        System.out.println("==========================");
        System.out.println("Tambah Data Praktikan");
        System.out.println("=====================");
        String nim;
        String nama;
        String email = "";
        
        while (true) {
            System.out.print("Masukkan NIM (dimulai dengan IF dan diikuti 7 angka): ");
            nim = scanner.nextLine();
            if (!nim.matches("IF\\d{7}")) {
                System.out.println("NIM harus dimulai dengan IF dan diikuti 7 angka. Silakan coba lagi.");
            } else if (tabelData.containsKey(nim)) {
                System.out.println("NIM sudah terdaftar. Silakan coba lagi.");
            } else {
                break;
            }
        }
            
        while (true) {
            System.out.print("Masukkan Nama: ");
            nama = scanner.nextLine();
            if (nama.isEmpty()) {
                System.out.println("Nama tidak boleh kosong. Silakan coba lagi.");
            } else {
                break;
            }
        }
            
        while (true) {
            System.out.print("Masukkan Email (opsional, harus menggunakan @umm.ac.id): ");
            email = scanner.nextLine();
            if (!email.isEmpty() && !email.endsWith("@umm.ac.id")) {
                System.out.println("Email harus menggunakan @umm.ac.id. Silakan coba lagi.");
            } else {
                break;
            }
        }
        
        String[] dataPraktikan = {nama, email};
        tabelData.put(nim, dataPraktikan);
        
        System.out.println("Data berhasil ditambahkan.");
        System.out.println("==========================");
        return true;
    }
    
    private void tampilData() {
        System.out.println("|                  Data Praktikan                     |");
        System.out.println("|      NIM     |       Nama        |       Email      |");
    
        for (String nim : tabelData.keySet()) {
            String[] dataPraktikan = tabelData.get(nim);
            System.out.printf("| %-13s| %-18s| %-17s|\n", nim, dataPraktikan[0], dataPraktikan[1]);
        }
        System.out.println(" =====================================================");
    }
    
    

    private void listNimPraktikan() {
        System.out.println("==========================");
        System.out.println("List NIM Praktikan");
        System.out.println("==========================");
        for (String nim : tabelData.keySet()) {
            System.out.println(nim);
            System.out.println("==========================");
        }
    }

    private void listNamaAsisten() {
        System.out.println("==========================");
        System.out.println("Daftar Nama Asisten");
        System.out.println("==========================");
    
        Set<String> keys = tabelData.keySet();
        for (String key : keys) {
            String[] data = tabelData.get(key);
            String nama = data[0];
            System.out.println(nama);
            System.out.println("==========================");
        }
    }

    private int totalEmail() {
        System.out.println("==========================");
        System.out.println("Total Email Praktikan");
        System.out.println("==========================");
        int total = 0;
        Set<String> keys = tabelData.keySet();
        for (String key : keys) {
            String[] data = tabelData.get(key);
            String email = data[1];
            if (email.endsWith("@umm.ac.id")) {
                total++;
            }
        }
        System.out.println("Jumlah Email Praktikan yang menggunakan Email: " + total);
        System.out.println("==========================");
        return total;
    }
    
    private boolean hapusData() {
        System.out.println("==========================");
        System.out.println("Hapus Data Praktikan");
        System.out.println("==========================");
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();
        if (tabelData.containsKey(nim)) {
            tabelData.remove(nim);
            System.out.println("Data berhasil dihapus.");
            System.out.println("==========================");
            return true;
        } else {
            System.out.println("Data dengan NIM " + nim + " tidak ditemukan.");
            System.out.println("==========================");
            return false;
        }
    }
    
    private void editData() {
        System.out.println("Edit Data Praktikan");
        System.out.println("===================");
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();
        if (tabelData.containsKey(nim)) {
            String[] data = tabelData.get(nim);
            System.out.print("Masukkan Nama Baru (" + data[0] + "): ");
            String namaBaru = scanner.nextLine();
            System.out.print("Masukkan Email Baru (" + data[1] + "): ");
            String emailBaru = scanner.nextLine();
            String[] dataBaru = {namaBaru, emailBaru};
            tabelData.put(nim, dataBaru);
            System.out.println("Data berhasil diubah.");
            System.out.println("==========================");
        } else {
            System.out.println("Data dengan NIM " + nim + " tidak ditemukan.");
            System.out.println("==========================");
        }
    }
    public static void waitForEnter() {
        Scanner enter = new Scanner(System.in);
        System.out.print("Press Enter to continue...");
        enter.nextLine();
    }
}