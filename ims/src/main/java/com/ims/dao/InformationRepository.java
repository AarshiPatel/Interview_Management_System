package com.ims.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ims.entity.Information;

@Repository
public interface InformationRepository  extends JpaRepository<Information, Long> {

}
