package com.ftn.Knjizara.kontroleri;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

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

import com.ftn.Knjizara.model.Korisnik;
import com.ftn.Knjizara.model.KupljenaKnjiga;
import com.ftn.Knjizara.model.Kupovina;
import com.ftn.Knjizara.model.LoyaltyKartica;
import com.ftn.Knjizara.model.SpecijalniDatum;
import com.ftn.Knjizara.model.Zanr;
import com.ftn.Knjizara.service.SpecijalniDatumService;
import com.ftn.Knjizara.service.ZanrService;

@Controller
@RequestMapping(value="/SpecijalniDatum")
public class SpecijalniDatumKontroler {
	
	
	@Autowired
	private SpecijalniDatumService specijalniService;
	
	
	
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
		List<SpecijalniDatum> specijalniDatumi = specijalniService.findAll();

		// prosleđivanje
		ModelAndView rezultat = new ModelAndView("specijalniDatum");
		rezultat.addObject("specijalniDatumi", specijalniDatumi);

		return rezultat;
	}
	
	
	
	@PostMapping("/Dodavanje")
	public void Dodavanje(HttpServletRequest request,@RequestParam(required=false) Date datum,@RequestParam(required=false) Integer popust, HttpServletResponse response,HttpSession session) throws IOException {
		
		SpecijalniDatum specijalni = new SpecijalniDatum(1L, datum, popust);
		specijalniService.save(specijalni);
		response.sendRedirect(baseURL); 

	//	loyaltyKarticaService.update(loyalty);


	
}
	
	
	

}
