package com.ims.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ims.dto.ErrorDTO;
import com.ims.dto.Resource;

/**
 * The Class ExceptionHandlerControllerAdvice.
 */
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

	/**
	 * Handle resource not found.
	 *
	 * @return the response entity
	 */
	
	@ExceptionHandler({ ResourceNotFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ResponseEntity<Resource<ErrorDTO>> handleResourceNotFound(ResourceNotFoundException exception) {
		
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setMessage(exception.getMessage());
		errorDTO.setCode("404");
		
		Resource<ErrorDTO> resource = new Resource<>();
		resource.setData(errorDTO);
		resource.setSuccess(false);
		
		return new ResponseEntity<>(resource, HttpStatus.NOT_FOUND);
	}
	
	

	@ExceptionHandler({ BadRequestException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ResponseEntity<Resource<ErrorDTO>> handleBadRequest(BadRequestException exception) {
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setMessage(exception.getMessage());
		errorDTO.setCode("400");
		
		Resource<ErrorDTO> resource = new Resource<>();
		resource.setData(errorDTO);
		resource.setSuccess(false);
		return new ResponseEntity<>(resource,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ UnAuthorizedeException.class })
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public @ResponseBody ResponseEntity<Resource<ErrorDTO>> handleUnAuthorizedRequest(UnAuthorizedeException exception) {
		
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setMessage(exception.getMessage());
		errorDTO.setCode("401");
		
		Resource<ErrorDTO> resource = new Resource<>();
		resource.setData(errorDTO);
		resource.setSuccess(false);
		
		return new ResponseEntity<>(resource,HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler({ ConflictException.class })
	@ResponseStatus(HttpStatus.CONFLICT )
	public @ResponseBody ResponseEntity<Resource<ErrorDTO>> handleConflict(ConflictException exception) {
		
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setMessage(exception.getMessage());
		errorDTO.setCode("409");
		
		Resource<ErrorDTO> resource = new Resource<>();
		resource.setData(errorDTO);
		resource.setSuccess(false);
		
		return new ResponseEntity<>(resource, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler({UnprocessableEntity.class})
	public @ResponseBody ResponseEntity<Resource<ErrorDTO>> handleFeildsRequried(UnprocessableEntity exception){
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setMessage(exception.getMessage());
		errorDTO.setCode("422");
		
		Resource<ErrorDTO> resource = new Resource<>();
		resource.setData(errorDTO);
		resource.setSuccess(false);
		return new ResponseEntity<>(resource,HttpStatus.UNPROCESSABLE_ENTITY);
	}

}
