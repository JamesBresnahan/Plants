package org.launchcode.gardenbox.models.data;

import org.launchcode.gardenbox.models.Plant;
import org.launchcode.gardenbox.models.PlantType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PlantDao extends CrudRepository<Plant, Integer> {

    List<Plant> findByName(String name);

    List<Plant> findByType(PlantType type);

}
