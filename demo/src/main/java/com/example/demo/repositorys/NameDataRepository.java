package com.example.demo.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameDataRepository<NameData> extends JpaRepository<NameData, Long> {

}