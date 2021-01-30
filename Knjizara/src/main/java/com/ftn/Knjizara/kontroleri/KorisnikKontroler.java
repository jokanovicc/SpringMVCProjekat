package com.ftn.Knjizara.kontroleri;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ftn.Knjizara.model.Korisnik;
import com.ftn.Knjizara.model.KupljenaKnjiga;
import com.ftn.Knjizara.model.Kupovina;
import com.ftn.Knjizara.model.LoyaltyKartica;
import com.ftn.Knjizara.service.KorisnikService;
import com.ftn.Knjizara.service.KupljenaKnjigaService;
import com.ftn.Knjizara.service.KupovinaService;
import com.ftn.Knjizara.service.LoyaltyKarticaService;



@Controller
@RequestMapping(value="/Korisnici")
public class KorisnikKontroler {
	
	
	public static final String KORISNIK_KEY = "prijavljeniKorisnik";
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private ServletContext servletContext;
	private String baseURL;
	
	
	@Autowired
	private LoyaltyKarticaService loyaltyKarticaService;
	
	@Autowired
	private KupovinaService kupovinaService;
	
	@Autowired
	private KupljenaKnjigaService kupljenaKnjigaService;

	private Object kupljeneKnjige;
	
	@PostConstruct
	public void init() {	
		baseURL = servletContext.getContextPath() + "/";			
	}
	
	
	
	@GetMapping
	public ModelAndView index(
			@RequestParam(required=false) String korisnickoIme,
			@RequestParam(required=false) String eMail,
			@RequestParam(required=false) String lozinka,
			@RequestParam(required=false) String ime,
			@RequestParam(required=false) String prezime,
			@RequestParam(required=false) String adresa,
			@RequestParam(required=false) String brojTelefona,
			@RequestParam(required=false) Date datumRodjenja,
			@RequestParam(required=false) LocalDateTime datumRegistracije,
			@RequestParam(required=false) boolean administrator,
			@RequestParam(required=false) boolean blokiran,



			HttpSession session, HttpServletResponse response) throws IOException {		
		// autentikacija, autorzacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikKontroler.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL);
			return null;
		}
		List<LoyaltyKartica> kartice = new ArrayList<LoyaltyKartica>();
		kartice = loyaltyKarticaService.findAll();

		// čitanje
		List<Korisnik> korisnici = korisnikService.findAll();

		// prosleđivanje
		ModelAndView rezultat = new ModelAndView("korisnici");
		rezultat.addObject("korisnici", korisnici);
		rezultat.addObject("lojalti", kartice);


		return rezultat;
	}

	@GetMapping(value="/Details")
	public ModelAndView details(@RequestParam String korisnickoIme, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikKontroler.KORISNIK_KEY);
		// samo administrator može da vidi druge korisnike; svaki korisnik može da vidi sebe
		if (prijavljeniKorisnik == null || (!prijavljeniKorisnik.isAdministrator() && !prijavljeniKorisnik.getKorisnickoIme().equals(korisnickoIme))) {
			response.sendRedirect(baseURL + "Korisnici");
			return null;
		}

		// validacija
		Korisnik korisnik = korisnikService.findOne(korisnickoIme);
		if (korisnik == null) {
			response.sendRedirect(baseURL + "Korisnici");
			return null;
		}
		
		
		List<KupljenaKnjiga> kupljeneKnjige = new ArrayList<KupljenaKnjiga>();
		
		List<Kupovina> kupovine = kupovinaService.findAllzaKorisnika(korisnik.getKorisnickoIme());
		for (Kupovina kupovina : kupovine) {
			kupljeneKnjige = kupljenaKnjigaService.izvuciKupljeneKnjige(kupovina.getId());
		}
		
		List<LoyaltyKartica> kartice = new ArrayList<LoyaltyKartica>();
		kartice = loyaltyKarticaService.findAll();
		
		
		// prosleđivanje
		ModelAndView rezultat = new ModelAndView("korisnik");
		rezultat.addObject("korisnik", korisnik);
		rezultat.addObject("kupovine", kupovine);
		rezultat.addObject("kupljeneKnjige", kupljeneKnjige);
		rezultat.addObject("lojalti", kartice);


		return rezultat;
	}
	
	
	@GetMapping(value="/DetailsKupovine")
	public ModelAndView detailsKupovine(@RequestParam Long id,
			HttpSession session, HttpServletResponse response) throws IOException {


		Kupovina kupovina = kupovinaService.findOne(id);
		List<KupljenaKnjiga> kupljeneKnjige = kupovina.getKupljeneKnjige();
		kupljeneKnjige = kupljenaKnjigaService.izvuciKupljeneKnjige(kupovina.getId());
		
		
		
		// prosleđivanje
		ModelAndView rezultat = new ModelAndView("kupovinaDetalji");
		rezultat.addObject("kupovina", kupovina);	
		rezultat.addObject("kupljeneKnjige", kupljeneKnjige);	

		return rezultat;
	}
	
	
	@PostMapping(value="/Izvestavanje")
	public ModelAndView izvestavanje(@RequestParam Date datum1,@RequestParam Date datum2,
			HttpSession session, HttpServletResponse response) throws IOException {


		List<KupljenaKnjiga> izvestavanje = kupljenaKnjigaService.izvuciIzvestavanja2(datum1, datum2);
		KupljenaKnjiga kg = kupljenaKnjigaService.izvuciIzvestavanja(datum1, datum2);
		
		// prosleđivanje
		ModelAndView rezultat = new ModelAndView("izvestavanje");
		rezultat.addObject("izvestavanje", izvestavanje);
		rezultat.addObject("kg", kg);	


		return rezultat;
	}
	
	
	
	
	
	
	
	@GetMapping(value="/Create")
	public String create(HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikKontroler.KORISNIK_KEY);
		// samo administrator može da kreira korisnike
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL);
			return "korisnici";
		}

		return "dodavanjeKorisnika";
	}
	
	
	

	@PostMapping(value="/Edit")
	public void edit(@RequestParam(required=false) String korisnickoIme, @RequestParam(required=false) String administrator, @RequestParam(required=false) String blokiran,
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikKontroler.KORISNIK_KEY);
		// samo administrator može da menja druge korisnike; svaki korisnik može da menja sebe
		if (prijavljeniKorisnik == null || (!prijavljeniKorisnik.isAdministrator() && !prijavljeniKorisnik.getKorisnickoIme().equals(korisnickoIme))) {
			response.sendRedirect(baseURL + "Korisnici");
			return;
		}

		// validacija
		Korisnik korisnik = korisnikService.findOne(korisnickoIme);
		if (korisnik == null) {
			response.sendRedirect(baseURL + "Korisnici");
			return;
		}

		// privilegije može menjati samo administrator i to drugim korisnicima
		if (prijavljeniKorisnik.isAdministrator() && !prijavljeniKorisnik.equals(korisnik)) {
			korisnik.setAdministrator(administrator != null);
			korisnik.setBlokiran(blokiran != null);
		}
		korisnikService.update(korisnik);

		// sigurnost
		if (!prijavljeniKorisnik.equals(korisnik)) {
			// TODO odjaviti korisnika
		}

		if (prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Korisnici");
		} else {
			response.sendRedirect(baseURL);
		}
	}
	
	@PostMapping(value="/Login")
	public ModelAndView postLogin(@RequestParam String korisnickoIme, @RequestParam String lozinka, 
			HttpSession session, HttpServletResponse response) throws IOException {
		try {
			// validacija
			Korisnik korisnik = korisnikService.findOne(korisnickoIme, lozinka);
			System.out.println(korisnik);
			if (korisnik == null) {
				throw new Exception("Neispravno korisničko ime ili lozinka!");
			}			
			
			 

			
			// prijava
			session.setAttribute(KorisnikKontroler.KORISNIK_KEY, korisnik);
			
			response.sendRedirect(baseURL);
			return null;
		} catch (Exception ex) {
			// ispis greške
			String poruka = ex.getMessage();
			if (poruka == "") {
				poruka = "Neuspešna prijava!";
			}
			
			System.out.println("jesam li ovde?");
			
			
			// prosleđivanje
			ModelAndView rezultat = new ModelAndView("prijava");
			rezultat.addObject("poruka", poruka);
			

			return rezultat;
		}
	}
	
	
	
	@PostMapping(value="/Register")
	public ModelAndView register(@RequestParam String korisnickoIme, @RequestParam String lozinka,
			@RequestParam String eMail, @RequestParam String ponovljenaLozinka,@RequestParam Date datumRodjenja,
			
			@RequestParam String ime, @RequestParam String prezime,@RequestParam String adresa,
			@RequestParam String brojTelefona,
			
			
			
			HttpSession session, HttpServletResponse response) throws IOException {
		try {
			// validacija
			Korisnik postojeciKorisnik = korisnikService.findOne(korisnickoIme);
			if (postojeciKorisnik != null) {
				throw new Exception("Korisničko ime već postoji!");
			}
			if (korisnickoIme.equals("") || lozinka.equals("")) {
				throw new Exception("Korisničko ime i lozinka ne smeju biti prazni!");
			}
			if (!lozinka.equals(ponovljenaLozinka)) {
				throw new Exception("Lozinke se ne podudaraju!");
			}
			if (eMail.equals("")) {
				throw new Exception("E-mail ne sme biti prazan");
			}
			if (!eMail.contains("@")) {
				throw new Exception("email mora imati bar @");
			}
			if (adresa.equals("")) {
				throw new Exception("adresa ne sme biti prazna!");
			}
			if (brojTelefona.equals("")) {
				throw new Exception("Neispravan format broja telefona ili broj telefona je prazan!");
			}
			if (ponovljenaLozinka.equals("")) {
				throw new Exception("polje lozinka ne sme biti prazan!");
			}
			if (prezime.equals("")) {
				throw new Exception("prezime ne sme biti prazan!");
			}
			if (ime.equals("")) {
				throw new Exception("ime ne sme biti prazan!");
			}
			
			if (datumRodjenja == null) {
				throw new Exception("popunite datum");
			}
			
			Date sqlDate2 = Date.valueOf(LocalDate.of(2003, Month.JULY, 12));
			Date sqlDate3 = Date.valueOf(LocalDate.of(1920, Month.JULY, 12));
			if (datumRodjenja.after(sqlDate2) || datumRodjenja.before(sqlDate3)) {
				throw new Exception("Neispravan datum rodjenja!");
			}


			// registracija
			Korisnik korisnik = new Korisnik(korisnickoIme, lozinka, eMail, ime, prezime, adresa, brojTelefona, datumRodjenja, LocalDateTime.now(), false,false,false);
			korisnikService.save(korisnik);

			response.sendRedirect(baseURL + "prijava.html");
			return null;
		} catch (Exception ex) {
			// ispis greške
			String poruka = ex.getMessage();
			if (poruka == "") {
				poruka = "Neuspešna registracija!";
			}

			// prosleđivanje
			ModelAndView rezultat = new ModelAndView("registracija");
			rezultat.addObject("poruka", poruka);

			return rezultat;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping(value="/Logout")
	public void logout(HttpSession session, HttpServletResponse response) throws IOException {
		// odjava	
		session.invalidate();
		
		response.sendRedirect(baseURL);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
