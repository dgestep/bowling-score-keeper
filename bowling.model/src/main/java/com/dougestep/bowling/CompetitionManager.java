package com.dougestep.bowling;

import com.dougestep.bowling.data.Competition;

/**
 * Defines a class which manages a competition between a series of {@link CompetitionEntity}'s.
 *
 * @author dougestep
 */
public interface CompetitionManager {

    void addCompetition(Competition competition);
}
