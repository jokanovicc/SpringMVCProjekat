package com.ftn.Knjizara.dao;

import com.ftn.Knjizara.model.KupljenaKnjiga;
import com.ftn.Knjizara.model.Kupovina;

import java.sql.Date;
import java.util.List;

public interface KupljenaKnjigaDAO {

    public KupljenaKnjiga findOne(Long id);
    public List<KupljenaKnjiga> findAll();
    public KupljenaKnjiga  izvuciIzvestavanja(Date datum1, Date datum2);
    public List<KupljenaKnjiga> izvuciKupljeneKnjige(Long id);
    public List<KupljenaKnjiga> izvuciIzvestavanja2(Date datum1, Date datum2);

    public int save(KupljenaKnjiga kupljenaKnjiga);
    public int update(KupljenaKnjiga kupljenaKnjiga);
    public int delete(Long id);
}
