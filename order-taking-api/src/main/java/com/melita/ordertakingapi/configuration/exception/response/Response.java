package com.melita.ordertakingapi.configuration.exception.response;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {
    private T data;
    private List<Error> errors = new ArrayList<>();

    public static Response withDate(Object data) {
        Response response = new Response<>();
        response.setData(data);
        return response;
    }

    public void add(Error error) {
        errors.add(error);
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public List<Error> getErrors() {
        return errors;
    }
}