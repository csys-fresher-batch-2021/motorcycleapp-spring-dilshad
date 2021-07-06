package in.dilshad.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import in.dilshad.dao.impl.BikeRepositoryJDBCTemplate;

@Component
public class DAOFactory {
	private DAOFactory() {

	}
	private static IBikeRepository bikeDAO = new BikeRepositoryJDBCTemplate();

	public static IBikeRepository getBikeRepositoryInstance() {
		return bikeDAO;
	}
}
