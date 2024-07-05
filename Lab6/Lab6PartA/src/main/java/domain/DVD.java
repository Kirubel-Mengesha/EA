package domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DVD extends Product{
    private String genre;

    public DVD(String genre) {
        this.genre = genre;
    }

    public DVD(String name, double price, String genre) {
        super(name, price);
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "DVD{" +
                "genre='" + genre + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
