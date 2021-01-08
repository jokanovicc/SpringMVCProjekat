package com.ftn.Knjizara.kontroleri;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ftn.Knjizara.model.Zanr;
import com.ftn.Knjizara.service.ZanrService;



@Controller
@RequestMapping(value="/Zanrovi")
public class ZanroviKontroler {
	
	
	@Autowired
	private ZanrService zanrService;
	
	@Autowired
	private ServletContext servletContext;
	private String baseURL;
	
	
	@PostConstruct
	public void init() {
		baseURL = servletContext.getContextPath() + "/";
	}
	
	
	
	
	@GetMapping
	public ModelAndView index(@RequestParam(required=false) String naziv,String opis, HttpSession session) {
		//ako je input tipa text i ništa se ne unese 
		//a parametar metode Sting onda će vrednost parametra handeler metode biti "" što nije null
		if(naziv!=null && naziv.trim().equals(""))
			naziv=null;
		
		// čitanje
		List<Zanr> zanrovi = zanrService.find(naziv);

		// prosleđivanje
		ModelAndView rezultat = new ModelAndView("zanrovi");
		rezultat.addObject("zanrovi", zanrovi);

		return rezultat;
	}
	
	
	
	
	
	
	

}
