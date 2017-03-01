package xmlparser.Model;

/**
 * Created by ilyalees on 24/02/2017.
 */
public class Family {
    private Mother mother;  //Mother
    private Father father;  // Father
    private String name;

    public Family () {}

    public Family(Mother mother, Father father, String name) {
        this.mother = mother;
        this.father = father;
        this.name = name;
    }

    public Mother getMother() {
        return mother;
    }

    public void setMother(Mother mother) {
        this.mother = mother;
    }

    public Father getFather() {
        return father;
    }

    public void setFather(Father father) {
        this.father = father;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Family name: " + name +
                "\t" +
                " mother: " + mother +
                "\t" +
                "/  father: " + father
                ;
    }
}
