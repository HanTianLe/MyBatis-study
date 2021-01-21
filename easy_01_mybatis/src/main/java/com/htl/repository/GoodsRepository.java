package com.htl.repository;

import com.htl.entity.Goods;

public interface GoodsRepository {
    public Goods findById(long id);

}
