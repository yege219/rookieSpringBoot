package com.rookie;

import com.rookie.dto.CarDTO;
import com.rookie.dto.ProductDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RookieApplicationTests {

	@Autowired
	@Qualifier("testJdbcTemplate")
	private JdbcTemplate testJdbc;

	@Autowired
	@Qualifier("docker23JdbcTemplate")
	private JdbcTemplate dockerJdbc;

	@Test
	public void test() {

		// 查询测试的数据库
		String sql = "select * from union_product where id = '3';";
		List<ProductDTO> productDTOList = testJdbc.query(sql, new BeanPropertyRowMapper<>(ProductDTO.class));


		System.out.println(productDTOList.get(0).getProductCode());


		// 查询docker的数据库
		String sqlQ = "select * from cf_car_info where id = '2c9280905e1d0c26015e1d0ca0ea0001';";
		List<CarDTO> carDTOList = dockerJdbc.query(sqlQ, new BeanPropertyRowMapper<>(CarDTO.class));
		System.out.println(carDTOList.get(0).getCreator_name());

	}

}
