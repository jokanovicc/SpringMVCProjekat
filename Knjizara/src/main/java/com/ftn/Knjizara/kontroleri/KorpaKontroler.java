package com.ftn.Knjizara.kontroleri;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ftn.Knjizara.model.Knjiga;
import com.ftn.Knjizara.model.Korisnik;
import com.ftn.Knjizara.model.KupljenaKnjiga;
import com.ftn.Knjizara.model.Kupovina;
import com.ftn.Knjizara.model.Zanr;
import com.ftn.Knjizara.service.Knjiga2Service;
import com.ftn.Knjizara.service.KorisnikService;
import com.ftn.Knjizara.service.KupljenaKnjigaService;
import com.ftn.Knjizara.service.KupovinaService;

@Controller
@RequestMapping(value="/Korpa")
public class KorpaKontroler {
	
	public static final String KORPA_KEY = "korisnickaKorpa";
	
	@Autowired
	private KupovinaService kupovinaService;
	@Autowired
	private Knjiga2Service knjigaService;

	
	@Autowired
	private KupljenaKnjigaService kupljenaKnjigaService;
	
	@Autowired
	private ServletContext servletContext;
	private String baseURL;
	
	
	Kupovina kupovina = new Kupovina(2313L, 1.0, LocalDateTime.now(),null, 0);

	
	@PostConstruct
	public void init() {
		baseURL = servletContext.getContextPath() + "/";
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@PostMapping
	public void dodajUKorpu(
			@RequestParam String knjigaId,
			@RequestParam String korisnickoIme,
			@RequestParam int brojKnjiga,HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Korisnik korisnik = (Korisnik) request.getSession().getAttribute(KorisnikKontroler.KORISNIK_KEY);
		if(korisnik==null || korisnik.isAdministrator()==true) {
			response.sendRedirect(baseURL+"prijava.html");
			return;
		}
		
		Long idKnjige = new Long(knjigaId);
		
		if(idKnjige!=null && idKnjige<=0) {
			response.sendRedirect(baseURL);
			return;
		}
		
		Knjiga knjiga = knjigaService.findOne(idKnjige);
		if(knjiga==null) {
			response.sendRedirect(baseURL);
			return;
		}
		
		if(knjiga.getKolicina() < brojKnjiga || knjiga.getKolicina() < 0) {
			response.sendRedirect(baseURL);
			return;
			
		}
		 Random rand = new Random(); 
		Long id = (long) rand.nextInt(1000); 
		kupovina.setKorisnik(korisnik);
		Long id2 = (long) rand.nextInt(1000); 
		kupovina.setId(id2);
		KupljenaKnjiga kupljenaKnjiga = new KupljenaKnjiga(id, knjiga, brojKnjiga, 0, kupovina.getId());
		
		
		kupljenaKnjiga.setCena(brojKnjiga*knjiga.getCena());
		
		
		kupovina.getKupljeneKnjige().add(kupljenaKnjiga);
		kupovina.setBrojKnjiga(kupovina.getBrojKnjiga() + kupljenaKnjiga.getBrojPrimeraka());
		kupovina.setUkupnaCena(kupovina.getUkupnaCena() + kupljenaKnjiga.getCena());

		
		
		session.setAttribute(KORPA_KEY, kupovina);
		response.sendRedirect(baseURL);
		// smisliti za domaci kako da kupi n karata gde se taj broj prosledi od strane korisnika
		// i voditi racuna koliko koja projekcija ima karata
//		Karta karta = new Karta(projekcija, korisnik);
//		kartaService.save(karta);
//		
//		response.sendRedirect(bURL + "Karte"); 
//		return;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Kupovina kupovina = (Kupovina) request.getSession().getAttribute(KORPA_KEY);
		
		
		
		
		
		
		List<KupljenaKnjiga> kupljeneKnjige = new ArrayList<KupljenaKnjiga>();
		if(kupovina != null) {
		 kupljeneKnjige = kupovina.getKupljeneKnjige();
		 
		}
		if(kupovina == null) {
			Kupovina kupovine1 = new Kupovina(1L, 0.0, LocalDateTime.now(), null, 0);
			kupovina = kupovine1;
		}
		
		// prosleđivanje
		ModelAndView rezultat = new ModelAndView("korpa");
		rezultat.addObject("kupovina", kupovina);
		rezultat.addObject("kupljeneKnjige", kupljeneKnjige);

		return rezultat;
	}
	
	@GetMapping("/Brisanje")
	public void Brisanje(Long kupljenaKnjigaID, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Kupovina kupovina = (Kupovina) request.getSession().getAttribute(KORPA_KEY);
		
		
		
		
		
		for (KupljenaKnjiga kupljenaKnjiga : kupovina.getKupljeneKnjige()) {
			if(kupljenaKnjiga.getId().equals(kupljenaKnjigaID)) {
				kupovina.setBrojKnjiga(kupovina.getBrojKnjiga()-kupljenaKnjiga.getBrojPrimeraka());
				kupovina.setUkupnaCena(kupovina.getUkupnaCena()-kupljenaKnjiga.getCena());
				kupovina.getKupljeneKnjige().remove(kupljenaKnjiga);
				System.out.println("AAAAAAAAAAA" + kupljenaKnjiga);

				response.sendRedirect(baseURL);
				return;
			}
		}
		
		
		
		
		
		
		
		

	}
	
	
	@GetMapping("/Dodavanje")
	public void Dodavanje(Integer kupovina1,HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		Kupovina kupovina = (Kupovina) request.getSession().getAttribute(KORPA_KEY);
		Korisnik korisnik = (Korisnik) request.getSession().getAttribute(KorisnikKontroler.KORISNIK_KEY);

		kupovina.setDatumKupovine(LocalDateTime.now());
		kupovina.setKorisnik(korisnik);
		kupovinaService.save(kupovina);
		
		
		for (KupljenaKnjiga kupljenaKnjiga : kupovina.getKupljeneKnjige()) {
			kupljenaKnjiga.setKupovina(kupovina.getId());
			kupljenaKnjigaService.save(kupljenaKnjiga);
		}
		
		
		
		
		
		kupovina.getKupljeneKnjige().clear();
		response.sendRedirect(baseURL); 
		return;
	
}
}
	
	
	

