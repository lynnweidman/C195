package model;

/**Class that contains Constructors and Getters and Setters to support NamesUtil. */
public class NamesModel {
    private int id;
    private String name;

/** Constructor to supply names.
 * @param name  name */
    public NamesModel(String name) {
        this.name = name;
    }

    /** Constructor to supply Ids and names.
     * @param id id
     * @param name name */
    public NamesModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**Constructor to supply ID.
     * @param id id*/
    public NamesModel(int id) {
        this.id = id;

    }

    /** Getter for ID.
     * @return id */
    public int getId() {
        return id;
    }

    /** Setter for ID
     * @param id id */
    public void setId(int id) {
        this.id = id;
    }

/** Getter for Name.
 * @return  name */
    public String getName() {
        return name;
    }

    /** Setter for Name.
     * @param name name */
    public void setName(String name) {
        this.name = name;
    }

    /** Converts name to a String.
     * @return name */
    public String toString() {
        return name;
    }
}
