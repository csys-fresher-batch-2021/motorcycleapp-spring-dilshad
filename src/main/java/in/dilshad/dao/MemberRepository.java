package in.dilshad.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.dilshad.model.MemberDetails;

/**
 * To store and retrieve Member details from the database.
 *
 * @author dils2654
 *
 */
@Repository
public interface MemberRepository extends CrudRepository<MemberDetails, Integer> {
	/**
	 * When valid email and password is passed, it returns the complete member
	 * details from table, else returns null.
	 *
	 * @param email
	 * @param password
	 * @return
	 */
	Optional<MemberDetails> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
