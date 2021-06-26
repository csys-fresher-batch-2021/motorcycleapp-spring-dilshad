package in.dilshad.dao;

import org.springframework.data.repository.CrudRepository;
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

}
