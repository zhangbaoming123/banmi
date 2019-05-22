package com.example.lenovo.banmi.base;

public interface ResultBack<T> {
    void onSucces(T bean);
    void onFails(String s);
}
