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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.ftn.Knjizara.model.Knjiga;
import com.ftn.Knjizara.model.Korisnik;
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
			@RequestParam(required=false) String kriterijumSortiranja,
			@RequestParam(required=false) String ascDesc,




			HttpSession session)  throws IOException {
		//ako je input tipa text i ništa se ne unese 
		//a parametar metode Sting onda će vrednost parametra handeler metode biti "" što nije null

		if(naziv!=null && naziv.trim().equals(""))
			naziv=null;
		
		// čitanje
		List<Knjiga> knjige = knjiga2Service.find(naziv, zanrId, autor, jezik, cenaOd, cenaDo);
		if (kriterijumSortiranja != null && ascDesc != null) {
			knjige = knjiga2Service.sort(kriterijumSortiranja, ascDesc, knjige);
			
		}
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

	
	
	@GetMapping(value="/Create")
	public ModelAndView create(HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikKontroler.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Knjige");
			return null;
		}

		// čitanje
		List<Zanr> zanrovi = zanrService.findAll();

		// prosleđivanje
		ModelAndView rezultat = new ModelAndView("dodavanjeKnjige");
		rezultat.addObject("zanrovi", zanrovi);

		return rezultat;
	}
	
	
	@PostMapping(value="/Create")
	public void create(@RequestParam String naziv, @RequestParam double cena,
			
			@RequestParam String autor,
			@RequestParam String izdavac,
			@RequestParam String kratakOpis,
			@RequestParam String tipPoveza,
			@RequestParam String pismo,
			@RequestParam String jezik,
			@RequestParam String slika,
			@RequestParam int godinaIzdavanja,

			@RequestParam int brojStrana,
			@RequestParam int kolicina,

			@RequestParam(name="zanrId", required=false) Long[] zanrIds, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikKontroler.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Knjige");
			return;
		}
		
		
		long number = (long) Math.floor(Math.random() * 9000000000000L) + 1000000000000L;

		


		Knjiga knjiga = new Knjiga(naziv,Long.toString(number), izdavac, autor, godinaIzdavanja, slika, kratakOpis, cena, brojStrana, tipPoveza, pismo, jezik, 1,kolicina);
		System.out.println(knjiga);
		knjiga.setZanrovi(zanrService.find(zanrIds));
		knjiga2Service.save(knjiga);

		response.sendRedirect(baseURL + "Knjige");
	}
	
	@PostMapping(value="/Edit")
	public void edit(@RequestParam Long id, 
			@RequestParam String naziv, @RequestParam double cena,
			@RequestParam String autor,
			@RequestParam String izdavac,
			@RequestParam String kratakOpis,
			@RequestParam String tipPoveza,
			@RequestParam String pismo,
			@RequestParam String jezik,
			@RequestParam String slika,
			@RequestParam int godinaIzdavanja,
			@RequestParam String isbn,
			@RequestParam double ocena,
			@RequestParam int brojStrana,
		
			@RequestParam(name="zanrId", required=false) Long[] zanrIds, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikKontroler.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Knjige");
			return;
		}

		// validacija
		Knjiga knjiga = knjiga2Service.findOne(id);
		if (knjiga == null) {
			response.sendRedirect(baseURL + "Knjige");
			return;
		}	
		if (naziv == null || naziv.equals("") || autor.equals("") || isbn.equals("")|| izdavac.equals("") || jezik.equals("") ||   cena < 5) {
			response.sendRedirect(baseURL + "Knjige/Details?id=" + id);
			return;
		}

		// izmena
		knjiga.setNaziv(naziv);
		knjiga.setAutor(autor);
		knjiga.setCena(cena);
		knjiga.setBrojStrana(brojStrana);
		knjiga.setGodinaIzdavanja(godinaIzdavanja);
		knjiga.setIzdavackaKuca(izdavac);
		knjiga.setKratakOpis(kratakOpis);
		knjiga.setJezik(jezik);
		knjiga.setSlikaKnjige(slika);
		knjiga.setOcena(ocena);
		knjiga.setPismo(pismo);
		knjiga.setTipPoveza(tipPoveza);
		knjiga.setZanrovi(zanrService.find(zanrIds));
		knjiga2Service.update(knjiga);

		response.sendRedirect(baseURL + "Knjige");
	}
	
	
	
	
}
