package org.example.DataAccessLayer;

import org.example.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerDAO extends JpaRepository<Owner, Integer> {
    @Override
    void deleteById(Integer id);
}
