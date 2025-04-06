import java.util.Scanner;

public class SinemaSistemi {
    static final int MAX_FILM = 10;
    static final int MAX_MUSTERI = 20;

    static String[] filmAdlari = new String[MAX_FILM];
    static int[] filmSureleri = new int[MAX_FILM];
    static String[] filmTurleri = new String[MAX_FILM];
    static int filmSayisi = 0;

    static String[] musteriAdlari = new String[MAX_MUSTERI];
    static String[] musteriEmailleri = new String[MAX_MUSTERI];
    static int musteriSayisi = 0;

    static int[][] biletler = new int[MAX_MUSTERI][MAX_FILM];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int secim;
        String kullaniciTipi;

        System.out.println("----- Sinema Sistemi Ana Sayfası -----");
        System.out.print("Kullanıcı türü (satıcı/müşteri): ");
        kullaniciTipi = input.nextLine().toLowerCase();

        if(kullaniciTipi.equals("satıcı")){
            if (satıcıGiriş(input)) {
                satıcıSayfası(input);
            } else {
                System.out.println("Yanlış kullanıcı adı veya şifre. Çıkılıyor...");
            }
        }
        else if(kullaniciTipi.equals("müşteri")){
            müşteriSayfası(input);
        }
        else {
            System.out.println("Geçersiz kullanıcı türü.");
        }
    }

    static boolean satıcıGiriş(Scanner input){
        String kullanıcıAdı = "admin";
        String şifre = "admin";
        String girdiKullanıcıAdı, girdiŞifre;

        System.out.println("----- Satıcı Girişi -----");
        do {
            System.out.print("Kullanıcı adı: ");
            girdiKullanıcıAdı = input.nextLine();
            System.out.print("Şifre: ");
            girdiŞifre = input.nextLine();

            if (girdiKullanıcıAdı.equals(kullanıcıAdı) && girdiŞifre.equals(şifre)) {
                return true;
            } else {
                System.out.println("Yanlış kullanıcı adı veya şifre. Tekrar deneyin.");
            }
        } while (true);
    }

    static void satıcıSayfası(Scanner input){
        int secim;

        do{
            System.out.println("\n--- Sinema Satıcı Kayıt Sistemi ---");
            System.out.println("1. Film Ekle");
            System.out.println("2. Filmleri Listele");
            System.out.println("3. Müşterileri Listele");
            System.out.println("4. Biletleri Listele");
            System.out.println("5. Müşteri Sayfasına Git");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");
            secim = input.nextInt();
            input.nextLine();

            switch (secim){
                case 1: filmEkle(input); break;
                case 2: filmleriListele(); break;
                case 3: musterileriListele(); break;
                case 4: biletleriListele(); break;
                case 5: müşteriSayfası(input); break;
                case 0: System.out.println("Çıkılıyor..."); break;
                default: System.out.println("Geçersiz seçim!");
            }
        }while(secim != 0);
    }

    static void müşteriSayfası(Scanner input){
        int secim;

        do{
            System.out.println("\n--- Sinema Müşteri Kayıt Sistemi ---");
            System.out.println("1. Filmleri Görüntüle");
            System.out.println("2. Bilet Satın Al");
            System.out.println("3. Satıcı Sayfasına Git");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");
            secim = input.nextInt();
            input.nextLine();

            switch (secim){
                case 1: filmleriListele(); break;
                case 2: biletSatinAl(input); break;
                case 3: satıcıSayfası(input); break;
                case 0: System.out.println("Çıkılıyor..."); break;
                default: System.out.println("Geçersiz seçim!");
            }
        }while(secim != 0);
    }

    static void filmEkle(Scanner input){
        if(filmSayisi >= MAX_FILM){
            System.out.println("Maksimum film sayısına ulaşıldı!");
            return;
        }
        System.out.print("Film adı: ");
        filmAdlari[filmSayisi] = input.nextLine();
        System.out.print("Süre (dakika): ");
        filmSureleri[filmSayisi] = input.nextInt();
        input.nextLine();
        System.out.print("Tür: ");
        filmTurleri[filmSayisi] = input.nextLine();
        filmSayisi++;
        System.out.println("Film eklendi.");
    }

    static void biletSatinAl(Scanner input){
        if(musteriSayisi == 0 || filmSayisi == 0){
            System.out.println("Önce müşteri ve film eklenmelidir.");
            return;
        }

        System.out.println("Filmler:");
        for(int i = 0; i < filmSayisi; i++){
            System.out.println(i + ". " + filmAdlari[i]);
        }

        System.out.print("Film numarası: ");
        int fIndex = input.nextInt();
        if(fIndex < 0 || fIndex >= filmSayisi){
            System.out.println("Geçersiz film seçimi.");
            return;
        }

        musteriAdlari[musteriSayisi] = "Müşteri " + (musteriSayisi + 1);
        musteriEmailleri[musteriSayisi] = "musteri" + (musteriSayisi + 1) + "@mail.com";
        musteriSayisi++;

        biletler[musteriSayisi - 1][fIndex] = 1;
        System.out.println("Bilet oluşturuldu.");
    }

    static void filmleriListele(){
        System.out.println("--- Filmler ---");
        for(int i = 0; i < filmSayisi; i++){
            System.out.println(i + ". " + filmAdlari[i] + " | Süre: " + filmSureleri[i] + "dk | Tür: " + filmTurleri[i]);
        }
    }

    static void musterileriListele(){
        System.out.println("--- Müşteriler ---");
        for(int i = 0; i < musteriSayisi; i++){
            System.out.println(i + ". " + musteriAdlari[i] + " | Email: " + musteriEmailleri[i]);
        }
    }

    static void biletleriListele(){
        System.out.println("--- Biletler ---");
        for(int i = 0; i < musteriSayisi; i++){
            System.out.print(musteriAdlari[i] + " -> ");

            boolean biletVar = false;
            for(int j = 0; j < filmSayisi; j++){
                if(biletler[i][j] == 1){
                    System.out.print(filmAdlari[j] + " | ");
                    biletVar = true;
                }
            }
            if(!biletVar){
                System.out.print("Bilet yok.");
            }
            System.out.println();
        }
    }
}
