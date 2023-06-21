package com.example.torder.repository;

import com.example.torder.entity.Contents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriteRepository extends JpaRepository<Contents, Integer> {

}
