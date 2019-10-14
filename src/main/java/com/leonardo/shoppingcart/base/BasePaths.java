package com.leonardo.shoppingcart.base;

public class BasePaths {
    public static final String ROOT = "/";
    public static final String APP = "ShopCartApp";

    public class Client {
        public static final String RESOURCE = "client";
        public static final String ROOT = "/" + RESOURCE;
        public static final String ID = "/{id}";
        public static final String EMAIL = "/email/{email}";
    }
}