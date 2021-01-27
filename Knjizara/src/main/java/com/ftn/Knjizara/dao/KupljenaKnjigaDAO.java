package com.ftn.Knjizara.dao;

import com.ftn.Knjizara.model.KupljenaKnjiga;
import com.ftn.Knjizara.model.Kupovina;

import java.util.List;

public interface KupljenaKnjigaDAO {

    public KupljenaKnjiga findOne(Long id);
    public List<KupljenaKnjiga> findAll();
    public List<KupljenaKnjiga> izvuciKupljeneKnjige(Long id);
    public int save(KupljenaKnjiga kupljenaKnjiga);
    public int update(KupljenaKnjiga kupljenaKnjiga);
    public int delete(Long id);
}
