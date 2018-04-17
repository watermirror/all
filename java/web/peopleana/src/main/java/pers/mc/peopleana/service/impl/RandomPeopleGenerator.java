package pers.mc.peopleana.service.impl;

import pers.mc.peopleana.domain.po.Person;

import java.time.LocalDate;
import java.util.Random;

public class RandomPeopleGenerator {
    private static final String[] MALE_FIRST_NAMES = {
            "Michael", "Peter", "Jack", "Ben", "Steve", "Bill", "William", "Larry", "Kalvin", "Henry"
    };

    private static final String[] FEMALE_FIRST_NAMES = {
            "Amanda", "Angy", "Lisa", "Emily", "Samantha", "Lucy", "Rose", "Susan", "Kate", "Sandra"
    };

    private static final String[] LAST_NAMES = {
            "Green", "Brown", "Bishop", "Che", "King", "Shoemaker", "Black", "Rich", "Khan", "Solomon"
    };

    private static final int EARLIEST_BIRTH_YEAR = 1950;
    private static final int LATES_BIRTH_YEAR = LocalDate.now().getYear();

    private static final String[] TEL_NO_PREFIXES = {
            "133", "135", "136", "137", "138", "139", "186", "189"
    };

    private static final String[] EMAIL_HOSTS = {
            "yahoo.com", "gmail.com", "csdn.cn", "apple.com", "alibaba-inc.com", "sohu.com", "amazon.com"
    };

    private static final String[] ADDR_STREETS = {
            "Rd. Roman", "St. King", "Dolphin Bay", "St. Jason", "Ave. 5th", "Rd. Qiwang", "St. Horse Mart"
    };

    private static final String[] ADDR_DETAIL = {
            "221-B", "11# 2-405", "35-2-1001", "19-A-2207", "4# 509", "1# 4S-265", "8# 6N-89"
    };

    private static final String[] ADDR_CITIES = {
            "Hangzhou, China", "Los Angeles, US", "London, UK", "Paris, FR", "Rome, Italy", "Venice, Italy",
            "Florence, Italy", "Shanghai, China"
    };

    private static Random random = new Random();

    /**
     * Generate a random person.
     * @return a new random person.
     */
    public static Person generateRandomPerson() {
        Person person = new Person();
        person.setMale(generateMale());
        person.setFirstName(generateFirstName(person.isMale()));
        person.setLastName(generateLastName());
        person.setBirthday(generateBirthday());
        person.setEmail(generateEmail(person.getFirstName(), person.getLastName()));
        person.setTelNo(generateTelNo());
        person.setMailAddr(generateMailAddr());
        return person;
    }

    private static String generateFirstName(boolean isMale) {
        return randomlyGet(isMale ? MALE_FIRST_NAMES : FEMALE_FIRST_NAMES);
    }

    private static String generateLastName() {
        return randomlyGet(LAST_NAMES);
    }

    private static boolean generateMale() {
        return random.nextBoolean();
    }

    private static LocalDate generateBirthday() {
        int year = random.nextInt(LATES_BIRTH_YEAR - EARLIEST_BIRTH_YEAR) + EARLIEST_BIRTH_YEAR;
        int month = random.nextInt(12) + 1;
        LocalDate firstDayOfBirthMonth = LocalDate.of(year, month, 1);

        int day = random.nextInt(firstDayOfBirthMonth.lengthOfMonth()) + 1;
        return LocalDate.of(year, month, day);
    }

    private static String generateTelNo() {
        String telNo = randomlyGet(TEL_NO_PREFIXES);
        telNo += "-";
        telNo += generateTelNoTetrad();
        telNo += "-";
        telNo += generateTelNoTetrad();
        return telNo;
    }

    private static String generateTelNoTetrad() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 4; ++i) {
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }

    private static String generateEmail(String firstName, String lastName) {
        String email = firstName.toLowerCase();
        email += ".";
        email += lastName.toLowerCase();
        email += ".";
        email += random.nextInt(10000);
        email += "@";
        email += randomlyGet(EMAIL_HOSTS);
        return email;
    }

    private static String generateMailAddr() {
        String addr = randomlyGet(ADDR_STREETS);
        addr += " ";
        addr += randomlyGet(ADDR_DETAIL);
        addr += ", ";
        addr += randomlyGet(ADDR_CITIES);
        addr += ".";
        return addr;
    }

    private static String randomlyGet(String[] source) {
        int index = random.nextInt(source.length);
        return source[index];
    }
}
