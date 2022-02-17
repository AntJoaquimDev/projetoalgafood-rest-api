package com.algafood.cursoapi.exceptionHandler;

import javax.net.ssl.SSLEngineResult.Status;

//import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algafood.cursoapi.domain.exception.EntidadeEmUsoException;
import com.algafood.cursoapi.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.cursoapi.domain.exception.NegocioException;

@ControllerAdvice
public class APiExceptionHandler extends ResponseEntityExceptionHandler{

	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?>handleEntidadeEmUsoExceptionException(
			EntidadeEmUsoException ex,WebRequest request){	
		
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(),	HttpStatus.CONFLICT , request);
	
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?>handleEntidadeNaoEncontradaException(
			EntidadeNaoEncontradaException ex, WebRequest request){
		
		HttpStatus status= HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
		var detail = ex.getMessage();
		
		Problem problem = createProblemBilder(status, problemType, detail)
				.build();
		/*  Padronizando o formato de problemas no corpo de respostas com a RFC 7807
		Problem problem = Problem.builder()
				.status(status.value())
				.type("http://algafood.com.br/entidade-nao-encontrada")
				.title("Entidade não encontrada")
				.detail(ex.getMessage())
				.build();
				*/
		
		return handleExceptionInternal(ex, problem, new HttpHeaders()
				,status , request);
		
	}
	

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?>handleNegocioException(
			NegocioException ex, WebRequest request){
		
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(),HttpStatus.BAD_REQUEST , request);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if (body == null) {
			body = Problem.builder()
					
					.title(status.getReasonPhrase())
					.status(status.value())
					.build();
		} else if(body instanceof String) {
			body = Problem.builder()					
					.title(status.getReasonPhrase())
					.status(status.value())
					.build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private Problem.ProblemBuilder createProblemBilder(HttpStatus status, 
			ProblemType problemType,String detail) {
		return Problem.builder()
				.status(status.value())
				.type(problemType.getUri())
				.title(problemType.getTitle())
				.detail(detail);
				
	}
}
