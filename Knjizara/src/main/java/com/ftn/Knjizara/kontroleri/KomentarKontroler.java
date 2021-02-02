package com.ftn.Knjizara.kontroleri;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
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
import com.ftn.Knjizara.model.Komentar;
import com.ftn.Knjizara.model.Korisnik;
import com.ftn.Knjizara.model.KupljenaKnjiga;
import com.ftn.Knjizara.model.SpecijalniDatum;
import com.ftn.Knjizara.model.StatusKomentara;
import com.ftn.Knjizara.service.Knjiga2Service;
import com.ftn.Knjizara.service.KomentarService;

@Controller
@RequestMapping(value="/Komentari")
public class KomentarKontroler {
	
	
	
	@Autowired
	private Knjiga2Service knjigaService;
	
	@Autowired
	private KomentarService komentarService;
	
	
	@Autowired
	private ServletContext servletContext;
	private String baseURL;
	
	
	@PostConstruct
	public void init() {
		baseURL = servletContext.getContextPath() + "/";
	}
	
	
	@GetMapping
	public ModelAndView index(HttpSession session) {
		//ako je input tipa text i ništa se ne unese 
		//a parametar metode Sting onda će vrednost parametra handeler metode biti "" što nije null
		
		// čitanje
		List<Komentar> komentari = komentarService.findAll();

		// prosleđivanje
		ModelAndView rezultat = new ModelAndView("komentariAdmin");
		rezultat.addObject("komentari", komentari);

		return rezultat;
	}
	
	
	@GetMapping("/OdbijiKomentar")
	public void OdbijanjeKomentara(Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		Komentar komentar = komentarService.findOne(id);
		
		komentar.setStatus(StatusKomentara.nijeOdobren);
		komentarService.update(komentar);
		response.sendRedirect(baseURL);

		
		

	}
	
	
	@GetMapping("/OdobriKomentar")
	public void OdobravanjeKomentara(Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		Komentar komentar = komentarService.findOne(id);
		
		komentar.setStatus(StatusKomentara.odobren);
		
		Knjiga knjiga = knjigaService.findOne(komentar.getKnjiga().getId());
		
		double ocena = knjiga.getOcena();
		
		if(ocena != 1) {
			knjiga.setOcena((ocena+komentar.getOcena())/2);
			knjigaService.update(knjiga);
		}if(ocena == 1) {
			knjiga.setOcena(komentar.getOcena());
			knjigaService.update(knjiga);
		}
		
		
		
		komentarService.update(komentar);
		response.sendRedirect(baseURL);

	}
	
	
	
	
	
	@PostMapping("/Komentarisi")
	public void dodajUKorpu(
			@RequestParam Long knjigaId,
			@RequestParam String korisnickoIme,
			@RequestParam String opis,
			@RequestParam Integer ocena,HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Korisnik korisnik = (Korisnik) request.getSession().getAttribute(KorisnikKontroler.KORISNIK_KEY);
		if(korisnik==null || korisnik.isAdministrator()==true) {
			response.sendRedirect(baseURL+"prijava.html");
			return;
		}
		Date datum =  Date.valueOf(LocalDate.now());

		
		Knjiga knjiga = knjigaService.findOne(knjigaId);
		if(knjiga==null) {
			response.sendRedirect(baseURL);
			return;
		}
		
		Komentar komentar = new Komentar(1L, opis, ocena, datum, korisnik, knjiga, StatusKomentara.naCekanju);
		
		komentarService.save(komentar);

		
		response.sendRedirect(baseURL);
		// smisliti za domaci kako da kupi n karata gde se taj broj prosledi od strane korisnika
		// i voditi racuna koliko koja projekcija ima karata
//		Karta karta = new Karta(projekcija, korisnik);
//		kartaService.save(karta);
//		
//		response.sendRedirect(bURL + "Karte"); 
//		return;
	}
	
	
	
	
	
	
	

}
