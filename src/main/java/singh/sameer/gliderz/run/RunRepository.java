package singh.sameer.gliderz.run;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface RunRepository extends ListCrudRepository<Run, Integer> {

//    If a custom method is required then
//    @Query("select * from run where location= :location")   custom query also possible
    List<Run> findAllByLocation(String location);
}
