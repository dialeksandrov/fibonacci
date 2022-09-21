package com.aleksandrov.fibonacci.domain.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

/**
 * @author DAleksandrov
 * @created 21.09.2022
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FibonacciRequest {

    @ApiModelProperty(notes = "First number of sequence")
    @Positive(message = "Первое число не может быть меньше нуля")
    private int firstNumber;

    @ApiModelProperty(notes = "Second number of sequence")
    @Positive(message = "Второе число не может быть меньше нуля")
    private int secondNumber;

    @ApiModelProperty(notes = "Range of sequence")
    @Positive(message = "Длинна последовательности не может быть меньше нуля")
    private int range;
}
