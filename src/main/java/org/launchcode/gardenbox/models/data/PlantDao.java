package org.launchcode.gardenbox.models.data;

import org.launchcode.gardenbox.models.Plant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PlantDao extends CrudRepository<Plant, Integer> {

}
