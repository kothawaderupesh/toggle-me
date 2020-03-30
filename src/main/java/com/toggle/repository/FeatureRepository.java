package com.toggle.repository;

import com.toggle.model.Feature;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "feature", path = "feature")
public interface FeatureRepository extends CrudRepository<Feature, Integer> {
    Feature findByName(@Param("name") String name);

    @Query("select f.active from Feature f where f.name = :name")
    Boolean isActive(@Param("name") String name);
}