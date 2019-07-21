package typy_generyczne;

public class Main {

    public static void main(String[] args) {
        BoxOne<String, Integer> alicja = new BoxOne<>("Alicja", 234567);

        System.out.println(alicja.getElement1() + "  " + alicja.getElement2());;

    }
}
