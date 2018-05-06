package com.alex;

import com.alex.entity.vo.ResultVO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {

		Map map = new HashMap();
		ResultVO<Map> resultVO = new ResultVO<>();
		resultVO.setData(map);

		SpringApplication.run(BlogApplication.class, args);
	}
}
