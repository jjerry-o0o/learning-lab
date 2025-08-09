package hellojpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Bread {

    @Id
    private String name;
    private int weight;
    private int star;
    private String description;

    public Bread() {}

    public Bread(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
