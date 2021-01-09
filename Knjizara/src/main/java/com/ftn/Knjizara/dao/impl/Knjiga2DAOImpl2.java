package com.ftn.Knjizara.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ftn.Knjizara.dao.KnjigaDAO2;
import com.ftn.Knjizara.dao.ZanrDAO;
import com.ftn.Knjizara.model.Knjiga;
import com.ftn.Knjizara.model.Zanr;


@Repository
public class Knjiga2DAOImpl2 implements KnjigaDAO2 {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Autowired
	private ZanrDAO zanrDAO; 
	
	
	private class KnjigaZanrRowCallBackHandler implements RowCallbackHandler {

		private Map<Long, Knjiga> knjige = new LinkedHashMap<>();
		
		@Override
		public void processRow(ResultSet rs) throws SQLException {
			int index = 1;
            Long knjigaID = rs.getLong(index++);
            String naziv = rs.getString(index++);
            int isbn = rs.getInt(index++);
            String izdavac = rs.getString(index++);
            String autor = rs.getString(index++);
            int godinaIzdavanje = rs.getInt(index++);
            String opis = rs.getString(index++);
            Double cena = rs.getDouble(index++);
            int brojStrana = rs.getInt(index++);
            String jezik = rs.getString(index++);
            Double ocena = rs.getDouble(index++);
            String tipPoveza = rs.getString(index++);
            String pismo = rs.getString(index++);
            String slika = rs.getString(index++);

			Knjiga knjiga = knjige.get(knjigaID);
			if (knjiga == null) {
				knjiga = new Knjiga(knjigaID, naziv, isbn, izdavac, autor, godinaIzdavanje, slika, opis, cena, brojStrana, tipPoveza, pismo, jezik, ocena);
				knjige.put(knjiga.getId(), knjiga); // dodavanje u kolekciju
			}

			Long zanrId = rs.getLong(index++);
			String zanrNaziv = rs.getString(index++);
			String zanrOpis = rs.getString(index++);
			Zanr zanr = new Zanr(zanrId, zanrNaziv,zanrOpis);
			knjiga.getZanrovi().add(zanr);
		}

		public List<Knjiga> getKnjige() {
			return new ArrayList<>(knjige.values());
		}

	}
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public Knjiga findOne(Long id) {
		String sql = 
				"SELECT * FROM knjige f " + 
				"LEFT JOIN knjigaZanr fz ON fz.knjigaId = f.id " + 
				"LEFT JOIN zanrovi z ON fz.zanrId = z.id " + 
				"WHERE f.id = ? " + 
				"ORDER BY f.id";
		
		KnjigaZanrRowCallBackHandler rowCallbackHandler = new KnjigaZanrRowCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler, id);

		return rowCallbackHandler.getKnjige().get(0);
	}

	@Override
	public List<Knjiga> findAll() {
		String sql = 
				"SELECT * FROM knjige f " + 
				"LEFT JOIN knjigaZanr fz ON fz.knjigaId = f.id " + 
				"LEFT JOIN zanrovi z ON fz.zanrId = z.id " + 
				"ORDER BY f.id";

		KnjigaZanrRowCallBackHandler rowCallbackHandler = new KnjigaZanrRowCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler);

		return rowCallbackHandler.getKnjige();
	}
	
	
	
	private class KnjigaZanrRowMapper implements RowMapper<Long []> {

		@Override
		public Long [] mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
			Long knjigaId = rs.getLong(index++);
			Long zanrId = rs.getLong(index++);

			Long [] filmZanr = {knjigaId, zanrId};
			return filmZanr;
		}
	}
	

	@Override
	public int save(Knjiga knjiga) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Knjiga knjiga) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private class KnjigaRowMapper implements RowMapper<Knjiga> {

		@Override
		public Knjiga mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
            Long knjigaID = rs.getLong(index++);
            String naziv = rs.getString(index++);
            int isbn = rs.getInt(index++);
            String izdavac = rs.getString(index++);
            String autor = rs.getString(index++);
            int godinaIzdavanje = rs.getInt(index++);
            String opis = rs.getString(index++);
            Double cena = rs.getDouble(index++);
            int brojStrana = rs.getInt(index++);
            String jezik = rs.getString(index++);
            Double ocena = rs.getDouble(index++);
            String tipPoveza = rs.getString(index++);
            String pismo = rs.getString(index++);
            String slika = rs.getString(index++);

            Knjiga knjiga =  new Knjiga(knjigaID, naziv, isbn, izdavac, autor, godinaIzdavanje, slika, opis, cena, brojStrana, tipPoveza, pismo, jezik, ocena);
            return knjiga;
		}

	}

	@Override
	public List<Knjiga> find(String naziv, Long zanrId, String autor, String jezik, Integer cenaOd, Integer cenaDo) {
		ArrayList<Object> listaArgumenata = new ArrayList<Object>();
		
		String sql = "SELECT * FROM filmovi f "; 
		
		StringBuffer whereSql = new StringBuffer(" WHERE ");
		boolean imaArgumenata = false;
		
		if(naziv!=null) {
			naziv = "%" + naziv + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("f.naziv LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(naziv);
		}

		if(autor!=null) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("f.autor LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(autor);
		}
		
		if(jezik!=null) {
			jezik = "%" + jezik + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("f.jezik LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(jezik);
		}
		
		
		
		
		if(cenaDo != null) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("f.cena <= ?");
			imaArgumenata = true;
			listaArgumenata.add(cenaDo);
		}
		
		if(cenaOd != null) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("f.cena >= ?");
			imaArgumenata = true;
			listaArgumenata.add(cenaDo);
		}
		
		if(imaArgumenata)
			sql=sql + whereSql.toString()+" ORDER BY f.id";
		else
			sql=sql + " ORDER BY f.id";
		System.out.println(sql);
		
		List<Knjiga> knjige = jdbcTemplate.query(sql, listaArgumenata.toArray(), new KnjigaRowMapper());
		for (Knjiga knjiga : knjige) {
			
			knjiga.setZanrovi(findKnjigaZanr(knjiga.getId(), null));
		}
		//ako se treži film sa određenim žanrom  
		// tada se taj žanr mora nalaziti u listi žanrova od filma
		if(zanrId!=null)
			for (Iterator iterator = knjige.iterator(); iterator.hasNext();) {
				Knjiga knjiga = (Knjiga) iterator.next();
				boolean zaBrisanje = true;
				for (Zanr zanr : knjiga.getZanrovi()) {
					if(zanr.getId() == zanrId) {
						zaBrisanje = false;
						break;
					}
				}
				if(zaBrisanje)
					iterator.remove();
			}
		
		return knjige;
	}
	
	
	private List<Zanr> findKnjigaZanr(Long knjigaId, Long zanrId) {
		
		List<Zanr> znaroviKnjige = new ArrayList<Zanr>();
		
		ArrayList<Object> listaArgumenata = new ArrayList<Object>();
		
		String sql = 
				"SELECT fz.knjigaId, fz.zanrId FROM knjigaZanr fz ";
		
		StringBuffer whereSql = new StringBuffer(" WHERE ");
		boolean imaArgumenata = false;
		
		if(knjigaId!=null) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("fz.knjigaId = ?");
			imaArgumenata = true;
			listaArgumenata.add(knjigaId);
		}
		
		if(zanrId!=null) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("fz.zanrId = ?");
			imaArgumenata = true;
			listaArgumenata.add(zanrId);
		}

		if(imaArgumenata)
			sql=sql + whereSql.toString()+" ORDER BY fz.knjigaId";
		else
			sql=sql + " ORDER BY fz.knjigaId";
		System.out.println(sql);
		
		@SuppressWarnings("deprecation")
		List<Long[]> knjigaZanrovi = jdbcTemplate.query(sql, listaArgumenata.toArray(), new KnjigaZanrRowMapper()); 
				
		for (Long[] fz : knjigaZanrovi) {
			znaroviKnjige.add(zanrDAO.findOne(fz[1]));
		}
		return znaroviKnjige;
	}

}
