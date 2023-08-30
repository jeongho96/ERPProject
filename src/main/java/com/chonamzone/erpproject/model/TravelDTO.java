package com.chonamzone.erpproject.model;

//이곳은 출장신청서 모델 테이블이다.
//컬럼명에 노란줄은 아직 사용전이라 뜨는 것이다.
//컨트롤러 따로 / 모델안에 서비스, DAO, DTO /뷰는 template 이다. 
//노션에 공부모음 230830 적어놓았다. 
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TravelDTO {

	
	private int d_seq;
	
	private String t_location;
	
	private String t_reason;
	
	private String t_accommodation;
	
	private int t_trans_cost;
	
	private int t_food_cost;
	
	private int t_accommodation_cost;
	
	private int t_etc_cost;
	
	private String t_start_date;
	
	private String t_end_date;
}
