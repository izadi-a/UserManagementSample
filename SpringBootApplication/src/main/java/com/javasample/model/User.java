package com.javasample.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <h1>User</h1>
 * Represents a User.
 *
 * @author Izadi Ali
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "User")
public class User {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @NotNull(message = "Name is mandatory")
    @Column(name = "NAME")
    private String name;
    @Column(name = "FAMILY")
    private String family;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "SALARY")
    private double salary;

    /**
     * Creates a user.
     */
    public User() {
    }

    /**
     * Creates a user based on all params.
     */
    public User(Integer id, String name, String family, String userName, double salary) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.userName = userName;
        this.salary = salary;
    }

    /**
     * Gets the user’s id.
     *
     * @return An Integer representing the user’s id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the user’s id.
     *
     * @param id An integer containing the user’s id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the user’s name.
     *
     * @return A string representing the user’s name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user’s name.
     *
     * @param name A string representing the user’s name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the user’s family.
     *
     * @return A string representing the user’s family.
     */
    public String getFamily() {
        return family;
    }

    /**
     * Sets the user’s family.
     *
     * @param family A string representing the user’s family.
     */
    public void setFamily(String family) {
        this.family = family;
    }

    /**
     * Gets the user’s user name.
     *
     * @return A string representing the user’s user name.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets the user’s user name.
     *
     * @param userName A string representing the user’s user name.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the user’s salary.
     *
     * @return A double representing the user’s salary.
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Gets the user’s last name.
     *
     * @param salary A double representing the user’s salary.
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Gets a hash code value for the user.
     *
     * @return A hash code value for the user.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        long temp;
        temp = Double.doubleToLongBits(salary);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Indicates whether some other user is "equal to" this one.
     *
     * @param obj the reference object with which to compare.
     * @return true if this user is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
