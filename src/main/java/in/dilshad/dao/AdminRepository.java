package in.dilshad.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.dilshad.model.AdminDetails;

@Repository
public interface AdminRepository extends CrudRepository<AdminDetails, String> {

}
