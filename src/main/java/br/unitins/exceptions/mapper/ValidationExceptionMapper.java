package br.unitins.exceptions.mapper;

import java.time.LocalDateTime;

import br.unitins.exceptions.ProblemDetail;
import br.unitins.exceptions.ValidationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException exception) {
        ProblemDetail detail = new ProblemDetail(
                "https://example.com/problem/validation",
                exception.getTitle(),
                exception.getStatus(),
                exception.getMessage(),
                null,
                LocalDateTime.now(),
                exception.getErrors());

        return Response.status(exception.getStatus())
                .entity(detail)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
