package pers.mc.peopleana.domain.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import pers.mc.peopleana.service.DateTimeService;
import pers.mc.peopleana.service.exception.DateTimeServiceDisabledException;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * Persistent object corresponding to table "people" in DB.
 * @version 18.2.1.0
 * @author Michael Che
 */
public class Person {

    /**
     * Id of record in DB.
     */
    private long id;

    /**
     * First name of a person.
     */
    private String firstName;

    /**
     * Last name of a person.
     */
    private String lastName;

    /**
     * Mark whether a person is male.
     */
    private boolean isMale;

    /**
     * The birthday date of a person.
     */
    private LocalDate birthday;

    /**
     * The telephone number of a person.
     */
    private String telNo;

    /**
     * The email address of a person.
     */
    private String email;

    /**
     * The mail address of a person.
     */
    private String mailAddr;

    /**
     * The calculated age in years.
     * It will be null til {@link #updateAge(DateTimeService)} has been called already.
     */
    private transient Integer age;

    /**
     * Update age in years.
     * @param service a {@link DateTimeService}.
     */
    public void updateAge(DateTimeService service) {
        LocalDate today = null;
        try {
            today = service.getCurrentDate();
        } catch (DateTimeServiceDisabledException e) {
            age = null;
            return;
        }
        Period period = Period.between(getBirthday(), today);
        age = period.getYears();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    @JsonIgnore
    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * Get the birthday in string with format like yyyy-MM-dd.
     * @return the birthday as a string.
     */
    @JsonProperty("birthday")
    public String getFormattedBirthday() {
        return birthday.format(DateTimeFormatter.ofPattern("d LLL yyyy"));
    }

    /**
     * Get the age in years of a person.
     * @return the age in years.
     */
    public Integer getAge() {
        return age;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMailAddr() {
        return mailAddr;
    }

    public void setMailAddr(String mailAddr) {
        this.mailAddr = mailAddr;
    }

    /**
     * Override {@code toString()} for adapting {@code Person} to some cache system just like Redis, Tair and etc.
     * @return a formatted string in JSON.
     */
    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(this);
        } catch (Exception e) {
            jsonString = "";
        }
        return jsonString;
    }
}
