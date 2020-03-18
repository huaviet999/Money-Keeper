package com.example.local.mapper;

public interface BaseMapper<E,M> {
    M mapToModel(E e);
    E mapFromModel(M m);
}
