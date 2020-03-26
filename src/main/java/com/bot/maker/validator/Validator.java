package com.bot.maker.validator;

public interface Validator<T> {

    void validate(T entity);
}
