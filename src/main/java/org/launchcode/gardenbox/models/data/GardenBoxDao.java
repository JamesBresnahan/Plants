package org.launchcode.gardenbox.models.data;

import org.launchcode.gardenbox.models.GardenBox;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional

public interface GardenBoxDao extends CrudRepository<GardenBox, Integer> {
}
