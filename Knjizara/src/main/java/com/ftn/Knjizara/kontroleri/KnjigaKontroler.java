package com.ftn.Knjizara.kontroleri;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.ftn.Knjizara.model.Knjiga;
import com.ftn.Knjizara.model.Zanr;
import com.ftn.Knjizara.service.KnjigaService;
import com.ftn.Knjizara.service.ZanrService;



@Controller
@RequestMapping(value="/Knjige")
public class KnjigaKontroler implements ServletContextAware {

	@Autowired
	private KnjigaService knjigaService;
	
	@Autowired
	private ZanrService zanrService;
	@Autowired
	private ServletContext servletContext;
	private String baseURL;
	
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		
	}
	
	@PostConstruct
	public void init() {
		baseURL = servletContext.getContextPath() + "/";
	}
	
	@GetMapping
	public ModelAndView index(
			@RequestParam(required=false) String naziv,
			@RequestParam(required=false) String izdavac,
			@RequestParam(required=false) String autor,
			@RequestParam(required=false) String kratakOpis,
			@RequestParam(required=false) String tipPoveza,
			@RequestParam(required=false) String pismo,
			@RequestParam(required=false) String jezik,
			@RequestParam(required=false) String slika,
			HttpSession session)  throws IOException {
		//ako je input tipa text i ništa se ne unese 
		//a parametar metode Sting onda će vrednost parametra handeler metode biti "" što nije null

		if(naziv!=null && naziv.trim().equals(""))
			naziv=null;
		
		// čitanje
		List<Knjiga> knjige = knjigaService.find(naziv);
		
		System.out.println(knjige);
		
		// prosleđivanje
		ModelAndView rezultat = new ModelAndView("knjige");
		rezultat.addObject("knjige", knjige);
		
		return rezultat;
	}
	
}
