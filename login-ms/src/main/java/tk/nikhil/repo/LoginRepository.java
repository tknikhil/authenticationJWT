package tk.nikhil.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tk.nikhil.model.LoginDTO;
@Repository
public interface LoginRepository extends JpaRepository<LoginDTO, Integer>{

}
