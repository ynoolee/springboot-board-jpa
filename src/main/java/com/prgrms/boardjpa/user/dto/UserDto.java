package com.prgrms.boardjpa.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import com.prgrms.boardjpa.user.Hobby;

public class UserDto {
	public record CreateRequest(@Email String email, @Nullable Hobby hobby,
								@NotBlank String name, @NotNull Integer age, @NotBlank String password) {
	}

	public record Info(Long id, String email, Hobby hobby,
					   String name, Integer age) {
	}
}