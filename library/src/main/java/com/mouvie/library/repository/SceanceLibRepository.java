package com.mouvie.library.repository;

import com.mouvie.library.model.Sceance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SceanceLibRepository extends JpaRepository<Sceance, String> {
}
