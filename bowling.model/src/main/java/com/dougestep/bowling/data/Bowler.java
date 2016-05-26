package com.dougestep.bowling.data;

import java.io.Serializable;
import java.util.UUID;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Represents a bowler.
 *
 * @author dougestep
 */
public class Bowler implements Serializable {
    private static final long serialVersionUID = -7660000888725336033L;
    private UUID uid;
    private String firstName;
    private String lastName;

    /**
     * Creates an instance of this class.
     */
    public Bowler() {
    }

    /**
     * Creates an instance of this class.
     * @param uid unique ID for the bowler.
     */
    public Bowler(final UUID uid) {
        setUid(uid);
    }

    /**
     * Creates an instance of this class.
     * @param uid unique ID for the bowler.
     * @param firstName sets the bowlers first name.
     * @param lastName sets the bowlers last name.
     */
    public Bowler(final UUID uid, final String firstName, final String lastName) {
        this(firstName, lastName);
        setUid(uid);
    }

    /**
     * Creates an instance of this class.
     * @param firstName sets the bowlers first name.
     * @param lastName sets the bowlers last name.
     */
    public Bowler(final String firstName, final String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    /**
     * Returns a {@link UUID} that uniquely identifying this bowler.
     * @return the UUID.
     */
    public UUID getUid() {
        return uid;
    }

    /**
     * Sets the {@link UUID} that uniquely identifying this bowler.
     * @param uid the UUID.
     */
    public void setUid(final UUID uid) {
        this.uid = uid;
    }

    /**
     * Returns the bowlers first name.
     * @return the first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the bowlers first name.
     * @param firstName the first name.
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the bowlers last name.
     * @return the last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the bowlers last name.
     * @param lastName the last name.
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }

        final Bowler other = (Bowler) obj;
        return Objects.equal(this.uid, other.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uid);
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper str = MoreObjects.toStringHelper(this);
        str.add("firstName", firstName);
        str.add("lastName", lastName);
        return str.toString();
    }
}
