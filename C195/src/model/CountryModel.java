package model;


/** Class that contains Constructors and GEtters and Setters for getting the countries and country Ids. */
public class CountryModel {
        private int countryId;
        private String countryName;

/** Constructor to supply countryID and countryName.
 * @param countryId countryId
 * @param countryName countryName */
        public CountryModel(int countryId, String countryName) {
            this.countryId = countryId;
            this.countryName = countryName;
        }

    public CountryModel(String countryName) {
        this.countryName = countryName;
    }

        /** Getter for countryId.
         * @return countryId */
        public int getCountryId() {
            return countryId;
        }

/** Getter for countryName.
 * @return countryName */
        public String getCountryName() {
            return countryName;
        }

        /** Setter for countryName.
         @param countryName countryName */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /** Converts countryName to String.
     * @return countryName */
   public String toString() {
            return countryName;
        }
    }

