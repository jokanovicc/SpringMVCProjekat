package com.ftn.Knjizara.kontroleri;

import java.io.IOException;

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
	@GetMapping(value="/Logout")
	public void logout(HttpSession session, HttpServletResponse response) throws IOException {
		// odjava	
		session.invalidate();
		
		response.sendRedirect(baseURL);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
