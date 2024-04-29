import java.util.HashSet;

public class Test {
    public static void main(String[] args) {
        HashSet<String> set1 = new HashSet<>();
        HashSet<String> set2;

        set1.add("1");
        set1.add("2");
        set1.add("3");

        set2 = (HashSet<String>) set1.clone();
        set2.remove("1");

        for (String i : set1) {
            System.out.println(i);
        }

        for (String i : set2) {
            System.out.println(i);
        }
    }
}
