package com.algafood.cursoapi.exceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;

//import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algafood.cursoapi.domain.exception.EntidadeEmUsoException;
import com.algafood.cursoapi.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.cursoapi.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

@ControllerAdvice
public class APiExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String MSG_ERRO_GENERICO_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. "
	        + "Tente novamente e se o problema persistir, entre em contato "
	        + "com o administrador do sistema.";

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);

		} else if (rootCause instanceof PropertyBindingException) {

			return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);

		}
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		var detail = "O corpo da requisição está inválido. Verifique o erro da sintaxe.";

		Problem problem = createProblemBilder(status, problemType, detail).build();

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);

	}

	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;

		String path = joinPath(ex.getPath());

		var detail = String.format(
				"A propriedade '%s' recebeu o valor '%s', "
						+ "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo '%s",
				path, ex.getValue(), ex.getTargetType().getSimpleName());

		Problem problem = createProblemBilder(status, problemType, detail)
				.userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
				.build();
		return handleExceptionInternal(ex, problem, headers, status, request);
		
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleIUncaught(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		ProblemType problemType = ProblemType.ERRP_DE_SISTEMA;
		var detail = MSG_ERRO_GENERICO_USUARIO_FINAL;
		Problem problem = createProblemBilder(status, problemType, detail).build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(),status, request);
	}
			
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
		}

		return super.handleTypeMismatch(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		ProblemType problemType =ProblemType.RECURSO_NAO_ENCONTRADO;
		  
	    String detail = String.format("O recurso %s, que você tentou acessar, é inexistente.", 
			ex.getRequestURL());
		Problem problem = createProblemBilder(status, problemType, detail)
				.userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
				.build();
		return handleExceptionInternal(ex, problem,headers, status, request);
	}
	
	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;

		var detail = String.format("O parâmetro de URL'%s' recebeu o valor '%s', "
						+ "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo '%s",
				ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

		Problem problem = createProblemBilder(status, problemType, detail)
				.userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
				.build();
		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		String path = joinPath(ex.getPath());

		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;

		var detail = String.format("A propriedade '%s' não existe." + " Corrija ou remova e tente novamente ", path);

		Problem problem = createProblemBilder(status, problemType, detail)
				.userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
				.build();
		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	private String joinPath(List<Reference> reference) {
		return reference.stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));
	}

	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {

		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
		var detail = ex.getMessage();

		Problem problem = createProblemBilder(status, problemType, detail)
				.userMessage(detail)
				.build();

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);

	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex,
			WebRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		var detail = ex.getMessage();

		Problem problem = createProblemBilder(status, problemType, detail)
				.userMessage(detail)
				.build();

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.ERRO_NEGOCIO;
		var detail = ex.getMessage();

		Problem problem = createProblemBilder(status, problemType, detail).build();

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);

	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (body == null) {
			body = Problem.builder()
					.timestamp(LocalDateTime.now())
					.title(status.getReasonPhrase())
					.status(status.value())
					.build();
		} else if (body instanceof String) {
			body = Problem.builder()
					.timestamp(LocalDateTime.now())
					.title(status.getReasonPhrase())
					.status(status.value())
					.build();
		}

		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	private Problem.ProblemBuilder createProblemBilder(HttpStatus status, ProblemType problemType, String detail) {
		return Problem.builder()
				.timestamp(LocalDateTime.now())
				.status(status.value())
				.type(problemType.getUri())
				.title(problemType.getTitle())
				
				.detail(detail);
	}

}
