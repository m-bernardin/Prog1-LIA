/**
 * @author Mathieu Bernardin
 */
public enum Units {
    MILLILITER("mL"),
    CUP("c"),
    TABLESPOON("tbsp"),
    TEASPOON("tsp"),
    INDIVIDUAL("idvn"),
    FLUID_OUNCE("fl.oz."),;

    private String unitAbreviation;

    Units(String abreviation) {
        unitAbreviation=abreviation;
    }

    public String toString()
    {
        return unitAbreviation;
    }
}
