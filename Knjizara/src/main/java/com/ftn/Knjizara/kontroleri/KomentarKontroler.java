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
import com.ftn.Knjizara.model.Kupovina;
import com.ftn.Knjizara.model.SpecijalniDatum;
import com.ftn.Knjizara.model.StatusKomentara;
import com.ftn.Knjizara.model.Zanr;
import com.ftn.Knjizara.service.Knjiga2Service;
import com.ftn.Knjizara.service.KomentarService;
import com.ftn.Knjizara.service.KupovinaService;
import com.ftn.Knjizara.service.ZanrService;

@Controller
@RequestMapping(value="/Komentari")
public class KomentarKontroler {
	
	
	
	@Autowired
	private Knjiga2Service knjigaService;
	
	@Autowired
	private KomentarService komentarService;
	@Autowired
	private KupovinaService kupovinaService;
	
	@Autowired
	private ServletContext servletContext;
	private String baseURL;
	@Autowired
	private ZanrService zanrService;
	
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
	public ModelAndView dodajUKorpu(
			@RequestParam Long knjigaId,
			@RequestParam String korisnickoIme,
			@RequestParam String opis,
			@RequestParam Integer ocena,HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
						
					Korisnik korisnik = (Korisnik) request.getSession().getAttribute(KorisnikKontroler.KORISNIK_KEY);
					if(korisnik==null || korisnik.isAdministrator()==true) {
						response.sendRedirect(baseURL+"prijava.html");
					}
					Date datum =  Date.valueOf(LocalDate.now());
			
					
					Knjiga knjiga = knjigaService.findOne(knjigaId);
					if(knjiga==null) {
						response.sendRedirect(baseURL);
					}
					
					
					if (jelKupio(korisnik) == false) {
						throw new Exception("Ne mozete komentarisati knjigu koju niste kupili");
					}
					if (jelKomantarisao(korisnik,knjiga) == false) {
						throw new Exception("Ne mozete 2 puta komentarisati istu knjigu!");
					}
					
					
					Komentar komentar = new Komentar(1L, opis, ocena, datum, korisnik, knjiga, StatusKomentara.naCekanju);
					
					komentarService.save(komentar);
					response.sendRedirect(baseURL);
					return null;
					
					
					
					
		}catch (Exception ex) {
			// ispis greške
			String poruka = ex.getMessage();
			if (poruka == "") {
				poruka = "Neuspešno dodavanje komentara!";
			}
			
			Knjiga knjiga = knjigaService.findOne(knjigaId);
			List<Zanr> zanrovi = zanrService.findAll();


			// prosleđivanje
			ModelAndView rezultat = new ModelAndView("knjiga");
			// čitanje
			rezultat.addObject("knjiga", knjiga);

			rezultat.addObject("poruka", poruka);
			rezultat.addObject("zanrovi", zanrovi);


			return rezultat;
		}
	}
	
	
	public boolean jelKupio(Korisnik korisnik) {
		List<Kupovina> kupovine = kupovinaService.findAll();
		for (Kupovina kupovina : kupovine) {
			if(kupovina.getKorisnik().getKorisnickoIme().equals(korisnik.getKorisnickoIme())) {
				return true;
			}
			
		}
		return false;
	}
	
	
	
	public boolean jelKomantarisao(Korisnik korisnik,Knjiga knjiga) {
		List<Komentar> komentari = komentarService.findAll();
		for (Komentar komentar : komentari) {
			if(komentar.getKorisnik().getKorisnickoIme().equals(korisnik.getKorisnickoIme()) && knjiga.getId() == komentar.getKnjiga().getId()) {
					System.out.println("KOMENTARISANA JE VEC");
					return false;
			}
			
		}
		System.out.println("NIJE");

		return true;
	}
	
	
	

}
