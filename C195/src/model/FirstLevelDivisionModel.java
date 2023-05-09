package model;

import javafx.scene.Node;


/** Class that contains constructors and Setters and Getters for getting the First Level Divisions. */
public class FirstLevelDivisionModel  {
    public int dbDivisionId;
    public String dbDivisions;

    /** Constructor.
     * @param dbDivisionId dbDivisionId
     * @param dbDivisions dbDivisions */
    public FirstLevelDivisionModel(int dbDivisionId, String dbDivisions) {
        this.dbDivisionId = dbDivisionId;
        this.dbDivisions = dbDivisions;
    }

    public FirstLevelDivisionModel(String dbDivisions) {
        this.dbDivisions = dbDivisions;
    }

    public FirstLevelDivisionModel(int dbivisionId) {
        this.dbDivisionId = dbivisionId;
    }

    /** Getsw the database DivisionId.
     * @return dbDivisionId */
    public int getDbDivisionId() {
        return dbDivisionId;
    }

    /** Gets the database Divisions.
     * @return dbDivisions */
    public String getDbDivisions() {
        return dbDivisions;
    }

    /** Sets the dbDivisions.
     * @param dbDivisions dbDivisions */
    public void setDbDivisions(String dbDivisions) {
        this.dbDivisions = dbDivisions;
    }

    /**Converts dbDivisions to String.
     * @return dbDivisions */
    public String toString() {
        return dbDivisions;
    }


}





