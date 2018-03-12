/**
 * @author michael
 * @date 2018/03/08
 */
public class AppEntry {

    static final int CONSTANT_1 = 1;

    public static void main(String[] args) {
        String name1 = "Hello";
        String name2 = "Hello";
        System.out.println((name1 == name2) + " > Helle == Hello");
        System.out.println(name1.equals(name2) + " > Hello equals Hello");

        name1 = new String("Hello");
        name2 = new String("Hello");
        System.out.println((name1 == name2) + " > new Hello == new Hello");
        System.out.println(name1.equals(name2) + " > new Hello equals new Hello");

        Integer int1 = 15;
        Integer int2 = 15;
        System.out.println((int1 == int2) + " > 15 == 15");
        System.out.println( int1.equals(int2) + " > 15 == 15");

        int1 = new Integer(15);
        int2 = new Integer(15);
        System.out.println((int1 == int2) + " > 15 == 15");
        System.out.println( int1.equals(int2) + " > 15 == 15");

        System.out.println(CONSTANT_1);
        // Cannot change CONSTANT_1 like below.
        // CONSTANT_1 = 2;
    }
}
