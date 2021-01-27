package com.ftn.Knjizara.dao;

import java.util.List;

import com.ftn.Knjizara.model.Kupovina;



public interface KupovinaDAO {
    public Kupovina findOne(Long id);
    public List<Kupovina> findAll();
    public int save(Kupovina kupovina);
    public int update(Kupovina kupovina);
    public int delete(Long id);

}
