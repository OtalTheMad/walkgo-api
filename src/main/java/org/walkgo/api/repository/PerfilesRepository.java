package org.walkgo.api.repository;

import org.walkgo.api.model.Perfiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilesRepository extends JpaRepository<Perfiles, Integer> {
}