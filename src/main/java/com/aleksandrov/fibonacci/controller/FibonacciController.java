package com.aleksandrov.fibonacci.controller;

import com.aleksandrov.fibonacci.domain.model.FibonacciRequest;
import com.aleksandrov.fibonacci.domain.model.RestResponse;
import com.aleksandrov.fibonacci.service.FibonacciService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @author DAleksandrov
 * @created 21.09.2022
 **/
@Service
@RequestMapping(value = "/fibonacci")
@Api(value = "Fibonacci sequence controller", description = "REST Apis related to the Fibonacci number sequence")
public class FibonacciController {

    public static final String POSITIVE_MSG = "Число не может быть меньше 0";
    private static final Logger LOGGER = LoggerFactory.getLogger(FibonacciController.class);

    private final FibonacciService fibonacciService;

    public FibonacciController(FibonacciService fibonacciService) {
        this.fibonacciService = fibonacciService;
    }

    @ApiOperation(value = "Get Fibonacci sequence", response = RestResponse.class, tags = "getFibonacciSequence")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 400, message = "Error|Wrong number format"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Error")})
    @PostMapping(value = "/calculate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse> getFibonacciSequence(@Valid @RequestBody FibonacciRequest request){
        try {
            String result = fibonacciService.getFibonacciSequence(request);
            return new ResponseEntity<>(RestResponse.success(result), HttpStatus.OK) ;
        } catch (ExecutionException e){
            LOGGER.error("Error on calculate fibonacci sequence cause {}", e.getMessage());
            return new ResponseEntity<>(RestResponse.error("Произошла ошибка"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler
    public ResponseEntity<RestResponse> handleException(MethodArgumentNotValidException e){
        List<FieldError> errors = e.getFieldErrors().stream().limit(1).collect(Collectors.toList());
        String message = errors.stream().map(x -> x.getDefaultMessage() + " ").collect(Collectors.joining());
        return new ResponseEntity<>(RestResponse.error(message), HttpStatus.BAD_REQUEST);
    }
}
