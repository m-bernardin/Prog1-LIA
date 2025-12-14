/**
 * @author Mathieu Bernardin
 */
public enum Units {
    MILLILITER(" mL"),
    CUP(" c"),
    TABLESPOON(" tbsp"),
    TEASPOON(" tsp"),
    INDIVIDUAL(" piece"),
    FLUID_OUNCE(" fl.oz."),;
    /**
     * The abreviated form of this Unit
     */
    private String unitAbreviation;
    /**
     * Default constructor fro this class.
     * @param abreviation - the abreviation of this Unit
     */
    Units(String abreviation) {
        unitAbreviation=abreviation;
    }
    /**
     * Formats this Unit as an understandable String.
     * @return the formatted String.
     */
    public String toString()
    {
        return unitAbreviation;
    }
    /**
     * Evaluates wether this unit is essentiually the same as another.
     * @param unit - the unit with whihc to compare this one
     * @return True if they are equal; false otherwise
     */
    public boolean equals(Units unit){
        return this.toString().trim().toLowerCase().equals(unit.toString().trim().toLowerCase());
    }
}
