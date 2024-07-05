package domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name = "CD.findAllByArtist", query = "SELECT cd FROM CD cd WHERE cd.artist = :artist")
public class CD extends Product{

    private String artist;

    public CD(String artist) {
        this.artist = artist;
    }

    public CD(String name, double price, String artist) {
        super(name, price);
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "CD{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", artist='" + artist + '\'' +
                '}';
    }
}

