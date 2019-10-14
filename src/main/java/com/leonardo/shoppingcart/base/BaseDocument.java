package com.leonardo.shoppingcart.base;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;

public abstract class BaseDocument<T extends Serializable> implements Persistable<T> {

    @Id
    protected T id;

    @Override
    public boolean isNew() {
        return id == null;
    }

    public T getId() {
        return id;
    }
}
