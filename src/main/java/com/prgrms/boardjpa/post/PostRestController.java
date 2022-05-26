package com.prgrms.boardjpa.post;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prgrms.boardjpa.commons.SuccessResponse;
import com.prgrms.boardjpa.post.dto.PostDto;

@RequestMapping("/api/posts")
@RestController
public class PostRestController {
	private final PostService postService;

	public PostRestController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping
	public ResponseEntity<SuccessResponse<List<PostDto.Info>>> getPostsByPaging(Pageable pageable) {
		return createSuccessResponse(
			postService.getAllByPaging(pageable),
			HttpStatus.OK);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<SuccessResponse<PostDto.Info>> store(
		@RequestBody @Valid PostDto.CreateRequest createRequest) {

		return createSuccessResponse(
			postService.store(
				createRequest.title(),
				createRequest.writerId(),
				createRequest.content()),
			HttpStatus.CREATED
		);
	}

	@PutMapping("/{postId}")
	public ResponseEntity<SuccessResponse<PostDto.Info>> edit(@PathVariable Long postId,
		@RequestBody @Valid PostDto.UpdateRequest updateRequest) {

		return createSuccessResponse(
			postService.edit(
				updateRequest.title(),
				postId,
				updateRequest.content()),
			HttpStatus.OK
		);
	}

	@GetMapping("/{postId}")
	public ResponseEntity<SuccessResponse<PostDto.Info>> showOne(@PathVariable Long postId) {
		return createSuccessResponse(
			postService.getById(postId),
			HttpStatus.OK
		);
	}

	// TODO : 상속을 이용해 풀어내야 할 필요성이 있을까?
	private <T> ResponseEntity<SuccessResponse<T>> createSuccessResponse(T body, HttpStatus status) {
		return new ResponseEntity<>(
			SuccessResponse.of(body),
			status
		);
	}
}