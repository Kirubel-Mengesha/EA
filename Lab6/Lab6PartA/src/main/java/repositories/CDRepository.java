package repositories;

import domain.CD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CDRepository extends JpaRepository<CD, Long> , JpaSpecificationExecutor<CD> {
    //method name queries
    List<CD> findByArtistAndPriceLessThan(String artist, double price);

    //named queries
    @Query(name = "CD.findAllByArtist")
    List<CD> findAllByArtist(@Param("artist") String artist);

    //JPQL Queries
    @Query("SELECT cd FROM CD cd WHERE cd.artist = :artist AND cd.price > :amount")
    List<CD> findByArtistAndPriceGreaterThan(@Param("artist") String artist, @Param("amount") double amount);

    //Native Queries
    @Query(value = "SELECT * FROM CD WHERE artist = 'U2'", nativeQuery = true)
    List<CD> findAllByArtistU2();
}
