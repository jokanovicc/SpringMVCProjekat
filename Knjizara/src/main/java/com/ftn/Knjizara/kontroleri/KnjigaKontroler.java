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
@RequestMapping(value="/")
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
			@RequestParam(required=false) String isbn,





			HttpSession session)  throws IOException {
		
		
		//ako je input tipa text i ništa se ne unese 
		//a parametar metode Sting onda će vrednost parametra handeler metode biti "" što nije null

		if(naziv!=null && naziv.trim().equals(""))
			naziv=null;
		
		// čitanje
		List<Knjiga> knjige = knjiga2Service.find(naziv, zanrId, autor, jezik, cenaOd, cenaDo,isbn);
		List<Zanr> zanrovi = zanrService.findAll();

		System.out.println(knjige);
		
		// prosleđivanje
		ModelAndView rezultat = new ModelAndView("index");
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
	public ModelAndView create(@RequestParam String naziv, @RequestParam(required=false) Double cena,
			
			@RequestParam String autor,
			@RequestParam String izdavac,
			@RequestParam String kratakOpis,
			@RequestParam String tipPoveza,
			@RequestParam String pismo,
			@RequestParam String jezik,
			@RequestParam String slika,
			@RequestParam (required=false) Integer godinaIzdavanja,
			@RequestParam (required=false) Integer brojStrana,
			@RequestParam(required=false) Integer kolicina,

			@RequestParam(name="zanrId", required=false) Long[] zanrIds, 
			HttpSession session, HttpServletResponse response) throws Exception {
		
		// autentikacija, autorizacija
		
		
		try {
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikKontroler.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL);

		}
		
		
		long number = (long) Math.floor(Math.random() * 9000000000000L) + 1000000000000L;

		/*
		 * if (naziv == null || naziv.equals("") || autor.equals("") ||
		 * izdavac.equals("") || jezik.equals("") || zanrIds==null || cena < 5 ||
		 * kolicina ==0 || slika.equals("") ||
		 * String.valueOf(godinaIzdavanja).equals("") ||
		 * String.valueOf(kolicina).equals("")|| String.valueOf(cena).equals("") ||
		 * String.valueOf(brojStrana).equals("") || godinaIzdavanja > 2020 || brojStrana
		 * < 50 || jezik.equals("") || kratakOpis.equals("")) {
		 * response.sendRedirect(baseURL + "Knjige/Create");
		 * System.out.println("Pogresan unos"); return; }
		 */

		if (naziv.equals("") || autor.equals("")) {
			throw new Exception("Korisničko ime i autor ne smeju biti prazni!");
		}
		if (izdavac.equals("") || kratakOpis.equals("")) {
			throw new Exception("Izdavac i opis ne smeju biti prazni!");
		}
		
		if (pismo.equals("") || jezik.equals("")) {
			throw new Exception("Pismo i jezik ne smeju biti prazni!");
		}
		
		if (kolicina == null || godinaIzdavanja == null || cena == null) {
			throw new Exception("Prazno polje kolicine!");
		}
		
		
		if (kolicina < 1 || brojStrana < 10 || godinaIzdavanja < 1950 || godinaIzdavanja > 2020) {
			throw new Exception("Godina izdavanja, broj strana, kolicina ne smeju biti 0!");
		}
		
		if (slika.equals("") || zanrIds==null || cena == null) {
			throw new Exception("Niste odabrali zanrove!");
		}
		

		Knjiga knjiga = new Knjiga(naziv,Long.toString(number), izdavac, autor, godinaIzdavanja, slika, kratakOpis, cena, brojStrana, tipPoveza, pismo, jezik, 1,kolicina);
		System.out.println(knjiga);
		knjiga.setZanrovi(zanrService.find(zanrIds));
		knjiga2Service.save(knjiga);

		response.sendRedirect(baseURL);
		return null;
		}catch (Exception ex) {
			// ispis greške
			String poruka = ex.getMessage();
			if (poruka == "") {
				poruka = "Neuspešno dodavanje knjige!";
			}

			// prosleđivanje
			ModelAndView rezultat = new ModelAndView("dodavanjeKnjige");
			// čitanje
			List<Zanr> zanrovi = zanrService.findAll();
			rezultat.addObject("zanrovi", zanrovi);
			rezultat.addObject("poruka", poruka);

			return rezultat;
		}
	}
	
	@PostMapping(value="/Edit")
	public void edit(@RequestParam Long id, 
			@RequestParam String naziv, @RequestParam(required=false) Double cena,
			@RequestParam String autor,
			@RequestParam String izdavac,
			@RequestParam String kratakOpis,
			@RequestParam String tipPoveza,
			@RequestParam String pismo,
			@RequestParam String jezik,
			@RequestParam String slika,
			@RequestParam(required=false) Integer godinaIzdavanja,
			@RequestParam String isbn,
			@RequestParam(required=false) Double ocena,
			@RequestParam(required=false) Integer brojStrana,
		
			@RequestParam(name="zanrId", required=false) Long[] zanrIds, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikKontroler.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL);
			return;
		}

		// validacija
		Knjiga knjiga = knjiga2Service.findOne(id);
		if (knjiga == null) {
			response.sendRedirect(baseURL);
			return;
		}	
		if (naziv == null || naziv.equals("") || autor.equals("") || isbn.equals("")|| izdavac.equals("") || jezik.equals("") || zanrIds==null || godinaIzdavanja == null || ocena == null || godinaIzdavanja < 1950 || godinaIzdavanja > 2020 ||  brojStrana == null || brojStrana < 10 ||  cena < 5) {
			response.sendRedirect(baseURL + "Details?id=" + id);
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

		response.sendRedirect(baseURL + "Details?id=" + id);	}
	
	@PostMapping(value="/EditKolicina")
	public void editKolicina(@RequestParam Long id, 

			@RequestParam(required=false) Integer kolicina,
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikKontroler.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL);
			return;
		}

		// validacija
		Knjiga knjiga = knjiga2Service.findOne(id);
		if (knjiga == null) {
			response.sendRedirect(baseURL);
			return;
		}	
		if (kolicina == null ||  kolicina > 500) {
			response.sendRedirect(baseURL + "Details?id=" + id);
			return;
		}
		System.out.println(knjiga.getKolicina()+ " " +kolicina);
		Integer kolicina1 = knjiga.getKolicina() + kolicina;

		// izmena
		knjiga.setKolicina(kolicina1);
		knjiga2Service.update(knjiga);

		response.sendRedirect(baseURL + "Details?id=" + id);
	}
	
	
	
	
}
