package accessingdb;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RaspberrypiDataRepository extends CrudRepository<RaspberrypiData, Integer> {

	@Query("SELECT r FROM RaspberrypiData r WHERE r.timestamp BETWEEN ?1 AND ?2")
	List<RaspberrypiData> findByDateBetween(String startTime, String endTime);
	

}
