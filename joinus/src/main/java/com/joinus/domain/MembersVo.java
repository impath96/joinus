package com.joinus.domain;

import java.sql.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembersVo {
	
	private Integer member_no;
	
	@NotBlank(message = "이메일을 입력해주세요.")
	@Email(message = "이메일 형식이 올바르지 않습니다.")
	private String member_email;
	
	@NotBlank(message = "비밀번호를 입력해주세요")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
	private String member_pass;
	
	@NotBlank(message = "이름을 입력해주세요.")
	private String member_name;
	private String member_location;
	private String member_tel;
	private String member_image;
	private Date member_regdate;
	private Integer member_role;
	private String member_type;
	
	
}
