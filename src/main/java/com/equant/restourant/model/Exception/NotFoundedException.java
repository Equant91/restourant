package com.equant.restourant.model.Exception;


import lombok.Getter;

public class NotFoundedException extends  RuntimeException {

    @Getter
    String description;

    public NotFoundedException(String description) {
        this.description = description;
    }

    @Override
    public String getMessage() {
        return getDescription();
    }
}
