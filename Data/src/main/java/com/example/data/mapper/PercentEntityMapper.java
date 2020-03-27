package com.example.data.mapper;

import com.example.data.entity.PercentEntity;
import com.example.domain.model.Percent;

import java.util.ArrayList;
import java.util.List;

public class PercentEntityMapper implements BaseMapper<PercentEntity, Percent> {
    CategoryEntityMapper categoryEntityMapper;

    public PercentEntityMapper(){
        categoryEntityMapper = new CategoryEntityMapper();
    }
    @Override
    public PercentEntity mapToEntity(Percent percent) {
        PercentEntity percentEntity = new PercentEntity();
        percentEntity.setSum(percent.getSum());
        percentEntity.setCategoryEntity(categoryEntityMapper.mapToEntity(percent.getCategory()));
        percentEntity.setPercent(percent.getPercent());
        return percentEntity;
    }
    public List<PercentEntity> mapToEntities(List<Percent> percentList){
        List<PercentEntity> percentEntityList = new ArrayList<>();
        for(Percent percent : percentList){
            percentEntityList.add(mapToEntity(percent));
        }
        return percentEntityList;
    }

    @Override
    public Percent mapFromEntity(PercentEntity percentEntity) {
        Percent percent = new Percent();
        percent.setSum(percentEntity.getSum());
        percent.setCategory(categoryEntityMapper.mapFromEntity(percentEntity.getCategoryEntity()));
        return percent;
    }
    public List<Percent> mapFromEntities(List<PercentEntity> percentEntities){
        List<Percent> percentList = new ArrayList<>();
        for(PercentEntity percentEntity : percentEntities){
            percentList.add(mapFromEntity(percentEntity));
        }
        return percentList;
    }
}
