package com.example.data.mapper;

public interface BaseMapper<E,M> {
    E mapToEntity(M m);
    M mapFromEntity(E e);
}
