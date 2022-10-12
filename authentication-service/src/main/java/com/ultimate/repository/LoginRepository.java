package com.ultimate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ultimate.model.LoginDTO;

/**
 * @author Nikhil TK
 */

@Repository
public interface LoginRepository extends JpaRepository<LoginDTO, String> {
	LoginDTO findByUserCode(String username);
}
