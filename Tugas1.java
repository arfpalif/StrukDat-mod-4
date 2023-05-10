import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Tugas1 {
    private HashMap<String, String[]> tabelData = new HashMap<String, String[]>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
     Tugas1 program = new Tugas1();
        program.runMenu();
    }

    private void runMenu() {
        int pilihan = 0;
        do {
            System.out.flush();
            System.out.println("Program Data Praktikan");
            System.out.println("======================");
            System.out.println("1. Tambah Data");
            System.out.println("2. Tampilkan Data");
            System.out.println("3. List NIM Praktikan");
            System.out.println("4. List Nama Asisten");
            System.out.println("5. Total Email");
            System.out.println("6. Hapus Data");
            System.out.println("7. Edit Data");
            System.out.println("0. logout");
            System.out.print("Masukkan pilihan Anda: ");
            pilihan = scanner.nextInt();
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
                    System.out.println("Terima kasih telah menggunakan program ini.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    break;
            }
            System.out.println();
        } while (pilihan != 0);
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
            System.out.print("Masukkan Email (opsional, harus menggunakan gmail.com): ");
            email = scanner.nextLine();
            if (!email.isEmpty() && !email.endsWith("@gmail.com")) {
                System.out.println("Email harus menggunakan gmail.com. Silakan coba lagi.");
            } else {
                break;
            }
        }

        //scanner inputan memasukkan nama, email di hash map sebagai value
        String[] dataPraktikan = {nama, email};
        //nim jadi key
        tabelData.put(nim, dataPraktikan);
        
        System.out.println("Data berhasil ditambahkan.");
        System.out.println("==========================");
        return true;
    }
    
    
    private void tampilData() {
        System.out.println("==========================");
        System.out.println("Data Praktikan");
        System.out.println("==============");
        System.out.println("NIM\t\tNama\t\tEmail");
        System.out.println("---------------------------------");
        for (String nim : tabelData.keySet()) {
            String[] dataPraktikan = tabelData.get(nim);
            System.out.printf("%s\t%s\t\t%s\n", nim, dataPraktikan[0], dataPraktikan[1]);
            System.out.println("==========================");
        }
    }

    private void listNimPraktikan() {
        System.out.println("==========================");
        System.out.println("List NIM Praktikan");
        System.out.println("==================");
        for (String nim : tabelData.keySet()) {
            System.out.println(nim);
            System.out.println("==========================");
        }
    }

    private void listNamaAsisten() {
        System.out.println("==========================");
        System.out.println("Daftar Nama Asisten");
        System.out.println("===================");
    
        Set<String> keys = tabelData.keySet();
        for (String key : keys) {
            String[] data = tabelData.get(key);
            System.out.println(data[0]);
            System.out.println("==========================");
        }
    }

    private int totalEmail() {
        System.out.println("==========================");
        System.out.println("Total Email Praktikan");
        int total = 0;
        Set<String> keys = tabelData.keySet();
        for (String key : keys) {
            String[] data = tabelData.get(key);
            String email = data[1];
            if (email.endsWith("@gmail.com")) {
                total++;
            }
        }
        System.out.println("Jumlah Email Praktikan yang menggunakan Gmail: " + total);
        System.out.println("==========================");
        return total;
    }
    
    private boolean hapusData() {
        System.out.println("==========================");
        System.out.println("Hapus Data Praktikan");
        System.out.println("====================");
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
        System.out.println("==========================");
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