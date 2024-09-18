package com.acme.algafood.api.exceptionhandler;

import com.acme.algafood.domain.exception.EntidadeEmUsoException;
import com.acme.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.acme.algafood.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {


        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;

        String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe";

        var problem = createProblemBuilder(status, problemType, detail).build();
//        return super.handleHttpMessageNotReadable(ex, headers, status, request);
        return handleExceptionInternal(
                ex, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradoException(EntidadeNaoEncontradaException ex,
                                                                  WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        String detail = ex.getMessage();
        ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;

        var problem = createProblemBuilder(status, problemType, detail).build();
        return handleExceptionInternal(
                ex, problem, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex,
                                                          WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        String detail = ex.getMessage();

        ProblemType problemType = ProblemType.ENTIDADE_EM_USO;

        var problem = createProblemBuilder(status, problemType, detail).build();
        return handleExceptionInternal(
                ex, problem, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(NegocioException ex,
                                                    WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String detail = ex.getMessage();

        ProblemType problemType = ProblemType.ERRO_NEGOCIO;

        var problem = createProblemBuilder(status, problemType, detail).build();
        return handleExceptionInternal(
                ex, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

//    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//    public ResponseEntity<?> tratarHttpMediaTypeNotSupportedException(){
//        Problema problema = Problema
//                .builder()
//                .dataHora(LocalDateTime.now())
//                .mensagem("O tipo de mídia não aceito!")
//                .build();
//        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(problema);
//    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        if(body == null) {
            body = Problem
                    .builder()
                    .status(status.value())
                    .title(status.getReasonPhrase())
                    .build();
        } else if (body instanceof String) {
            body = Problem
                    .builder()
                    .status(status.value())
                    .title(body.toString())
                    .build();
        }

        //return new ResponseEntity<>(body, headers, status);
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status,
                                                        ProblemType problemType,
                                                        String detail) {
        return Problem.builder()
                .status(status.value())
                .type(problemType.getTitle())
                .detail(detail)
                .type(problemType.getUri());
    }

}
