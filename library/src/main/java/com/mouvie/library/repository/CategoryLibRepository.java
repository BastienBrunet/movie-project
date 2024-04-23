package com.mouvie.library.repository;

import com.mouvie.library.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryLibRepository extends JpaRepository<Category, String> {
}
