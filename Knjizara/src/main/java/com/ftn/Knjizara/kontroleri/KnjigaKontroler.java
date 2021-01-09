package com.ftn.Knjizara.kontroleri;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.ftn.Knjizara.service.Knjiga2Service;
import com.ftn.Knjizara.service.ZanrService;



@Controller
@RequestMapping(value="/Knjige")
public class KnjigaKontroler implements ServletContextAware {

	@Autowired
	private Knjiga2Service knjiga2Service;
	
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
			@RequestParam(required=false) Long zanrId, 
			@RequestParam(required=false) String izdavac,
			@RequestParam(required=false) String autor,
			@RequestParam(required=false) String kratakOpis,
			@RequestParam(required=false) String tipPoveza,
			@RequestParam(required=false) String pismo,
			@RequestParam(required=false) String jezik,
			@RequestParam(required=false) String slika,
			@RequestParam(required=false) Integer cenaDo,
			@RequestParam(required=false) Integer cenaOd, 


			HttpSession session)  throws IOException {
		//ako je input tipa text i ništa se ne unese 
		//a parametar metode Sting onda će vrednost parametra handeler metode biti "" što nije null

		if(naziv!=null && naziv.trim().equals(""))
			naziv=null;
		
		// čitanje
		List<Knjiga> knjige = knjiga2Service.find(naziv, zanrId, autor, jezik, cenaOd, cenaDo);
		List<Zanr> zanrovi = zanrService.findAll();

		System.out.println(knjige);
		
		// prosleđivanje
		ModelAndView rezultat = new ModelAndView("knjige");
		rezultat.addObject("knjige", knjige);
		rezultat.addObject("zanrovi", zanrovi);
		
		return rezultat;
	}
	
	
	
	@GetMapping(value="/Details")
	@SuppressWarnings("unchecked")
	public ModelAndView details(@RequestParam Long id, 
			HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// čitanje
		Knjiga knjiga = knjiga2Service.findOne(id);
		List<Zanr> zanrovi = zanrService.findAll();


		// prosleđivanje
		ModelAndView rezultat = new ModelAndView("knjiga");
		rezultat.addObject("knjiga", knjiga);
		rezultat.addObject("zanrovi", zanrovi);

		return rezultat;
	}

	
}
