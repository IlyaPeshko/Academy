package xmlparser.Model;

/**
 * Created by ilyalees on 24/02/2017.
 */
public class Mother extends Person {
    private String maidenName; //
    private int children;

    public Mother () {}


    public Mother(String maidenName, int children) {
        this.maidenName = maidenName;
        this.children = children;
    }

    public Mother(String name, String surname, int age, String maidenName, int children) {
        super(name, surname, age);
        this.maidenName = maidenName;
        this.children = children;
    }

    public String getMaidenName() {
        return maidenName;
    }

    public void setMaidenName(String maidenName) {
        this.maidenName = maidenName;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Mother{" + "name: " + super.getName() +
                " / surname: " + super.getSurname() +
                " / maidenName: " + maidenName +
                " / age: " + super.getAge() +
                " / children: " + children +
                '}';
    }
}
