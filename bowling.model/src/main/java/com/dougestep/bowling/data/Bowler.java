package com.dougestep.bowling.data;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.io.Serializable;
import java.util.UUID;

/**
 * Represents a bowler.
 *
 * @author dougestep
 */
@SuppressFBWarnings
@SuppressWarnings("PMD")
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
     * Returns a {@link UUID} that uniquely identifying this bowler.
     *
     * @return the UUID.
     */
    public UUID getUid() {
        return uid;
    }

    /**
     * Sets the {@link UUID} that uniquely identifying this bowler.
     *
     * @param uid the UUID.
     */
    public void setUid(final UUID uid) {
        this.uid = uid;
    }

    /**
     * Returns the bowlers first name.
     *
     * @return the first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the bowlers first name.
     *
     * @param firstName the first name.
     * @return this instance.
     */
    public Bowler setFirstName(final String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Returns the bowlers last name.
     *
     * @return the last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the bowlers last name.
     *
     * @param lastName the last name.
     * @return this instance.
     */
    public Bowler setLastName(final String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

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
