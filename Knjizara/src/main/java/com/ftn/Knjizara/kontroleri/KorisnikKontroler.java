package com.ftn.Knjizara.kontroleri;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
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
import com.ftn.Knjizara.service.KorisnikService;



@Controller
@RequestMapping(value="/Korisnici")
public class KorisnikKontroler {
	
	
	public static final String KORISNIK_KEY = "prijavljeniKorisnik";
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private ServletContext servletContext;
	private String baseURL;
	
	@PostConstruct
	public void init() {	
		baseURL = servletContext.getContextPath() + "/";			
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
			
			@SuppressWarnings("deprecation")
			Date datum = new Date(2002, 02, 30);
			if (datumRodjenja.after(datum)) {
				throw new Exception("Neispravan datum rodjenja!");
			}


			// registracija
			Korisnik korisnik = new Korisnik(korisnickoIme, lozinka, eMail, ime, prezime, adresa, brojTelefona, datumRodjenja, LocalDateTime.now(), false);
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
