package org.example.DataAccessLayer;

import org.example.entities.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatDAO extends JpaRepository<Cat, Integer> {
    @Override
    void deleteById(Integer id);
}
