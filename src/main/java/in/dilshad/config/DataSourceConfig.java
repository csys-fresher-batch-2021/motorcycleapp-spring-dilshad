package in.dilshad.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Data Source Configuration for establishing connection with Database
 *
 * @author dils2654
 *
 */
@Configuration
public class DataSourceConfig {

	/**
	 * To configure the usage of JDBC template to avoid boiler plate code
	 *
	 * @param dataSource
	 * @return
	 */
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;

	}


}
