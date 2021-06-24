package in.dilshad.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.dilshad.model.MemberDetails;

@Repository
public interface MemberRepository extends CrudRepository<MemberDetails, Integer> {

}
