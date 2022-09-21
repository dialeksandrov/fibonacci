package com.aleksandrov.fibonacci.domain.model;

import com.aleksandrov.fibonacci.domain.enums.Status;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author DAleksandrov
 * @created 21.09.2022
 **/
@Data
public class RestResponse {
    @ApiModelProperty(notes = "Internal status")
    private Status status;

    @ApiModelProperty(notes = "Response data")
    private Object data;

    @ApiModelProperty(notes = "Message")
    private String message;

    public static RestResponse error(String message) {
        RestResponse responseModel = new RestResponse();
        responseModel.setStatus(Status.ERROR);
        responseModel.setMessage(message);
        return responseModel;
    }

    public static RestResponse success(Object data, String message) {
        RestResponse response = new RestResponse();
        response.setStatus(Status.SUCCESS);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    public static RestResponse success(Object data) {
        RestResponse response = new RestResponse();
        response.setStatus(Status.SUCCESS);
        response.setData(data);
        return response;
    }
}
