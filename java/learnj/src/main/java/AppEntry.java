import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author michael
 * @date 2018/03/08
 */
public class AppEntry {

    static final int CONSTANT_1 = 1;

    private static byte translateHexCharacter(char hexChar) {
        if (hexChar >= 'a' && hexChar <= 'f') {
            return (byte)(hexChar - 'a' + 10);
        }
        if (hexChar >= 'A' && hexChar <= 'F') {
            return (byte)(hexChar - 'A' + 10);
        }
        if (hexChar >= '0' && hexChar <= '9') {
            return (byte)(hexChar - '0');
        }
        throw new IllegalArgumentException();
    }

    private static long getEuiFromString(String euiString) {
        long eui = 0;
        byte[] bytes = euiString.getBytes();
        for (byte single : bytes) {
            eui = (eui << 4) | translateHexCharacter((char)single);
        }
        return eui;
    }

    private static String getEuiString(long eui, boolean upperCase) {
        return String.format(upperCase ? "%016X" : "%016x", eui);
    }

    private static final String ENCODING = "UTF-8";

    private static String percentEncode(String value) throws UnsupportedEncodingException {
        return value != null ? URLEncoder.encode(value, ENCODING).replace("+", "%20").replace("*", "%2A").replace("%7E", "~") : null;
    }

    public static void main(String[] args) {

        String original_text = "https://www.aliyun.com/hello?a=你好&b=jack martin.";
        try {
            original_text = percentEncode(original_text);
        } catch (Exception e) {}

        System.out.println(original_text);

        // genSql();

        /*
        Date tt = new Date(1533141605000L);
        System.out.println(tt.toString());

        long testNumber = 0xd896e0fff00001ddL;
        String msg = String.format("Test number: %d, %X", testNumber, testNumber);
        System.out.println(msg);

        String eui64 = "D896E0FFF0001000";
        long eui64Number = getEuiFromString(eui64);
        System.out.println(String.format("Test number 2: %X", eui64Number));

        String beginEui = "D896E0FFE0000000";
        String endEui = "D896E0FFE0FFFFFF";
        long beginLong = getEuiFromString(beginEui);
        long endLong = getEuiFromString(endEui);
        long count = endLong - beginLong + 1;

        Set<TestEnum> enums = new TreeSet<>();
        enums.add(TestEnum.ENUM_1);
        enums.add(TestEnum.ENUM_3);
        enums.add(TestEnum.ENUM_1);

        String enumsString = enums.stream().map(TestEnum::toString).collect(Collectors.joining(","));

        String illegalString = "\'\"\'\"";
        String legalString = illegalString.replace("\'", "\\\'");
        legalString = legalString.replace("\"", "\\\"");

        System.out.println(String.format("Test EUI String: " + getEuiString(testNumber, true)));
        System.out.println(String.format("Test EUI String 2: " + getEuiString(testNumber, false)));
        System.out.println(String.format("Test EUI String 3: " + getEuiString(127, true)));
        System.out.println(String.format("Test EUI String 4: " + getEuiString(127, false)));

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

        Object testObj = "Hello";
        Object nullObj = null;
        String stringObj = (String)nullObj;
        System.out.println(stringObj);
        stringObj = (String)testObj;
        System.out.println(stringObj);

        try {
            try {
                throwException();
            } catch (Exception e) {
                throw e;
            } finally {
                System.out.println("After try-catch.");
            }
        } catch (Exception e) {

        }

        System.out.println("After Exception.");*/
    }

    private static void throwException() {
        throw new RuntimeException();
    }

    private static void genSql() {

        String template = "INSERT INTO gateway_tuple (category, expiration_millis, gw_eui, pin_code, hub_product_key, hub_device_name, hub_device_secret, hub_device_secret_encrypted, connection_alive, created_millis, modified_millis) VALUES ('NORMAL', 0, %d, '000000', '', '', '', 0, 0, 1535708594000, 1535708594000);";

        final long begin = -2839835125162704896L;
        final long end = -2839835125162703873L;

        System.out.println("################################################");
        for (long current = begin; current <= end; ++current) {
            String line = String.format(template, current);
            System.out.println(line);
        }
        System.out.println("################################################");
    }
}
