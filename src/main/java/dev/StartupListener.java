package dev;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.domain.Collegue;
import dev.domain.LigneDeFrais;
import dev.domain.Mission;
import dev.domain.Nature;
import dev.domain.NatureFrais;
import dev.domain.Role;
import dev.domain.RoleCollegue;
import dev.domain.Status;
import dev.domain.Transport;
import dev.domain.Version;
import dev.repository.CollegueRepo;
import dev.repository.LigneDeFraisRepo;
import dev.repository.MissionRepo;
import dev.repository.NatureRepo;
import dev.repository.TransportRepo;
import dev.repository.VersionRepo;

/**
 * Code de démarrage de l'application. Insertion de jeux de données.
 */
@Component
public class StartupListener {

	private String appVersion;
	private VersionRepo versionRepo;
	private PasswordEncoder passwordEncoder;
	private CollegueRepo collegueRepo;
	private TransportRepo transportRepo;
	private NatureRepo natureRepo;
	private LigneDeFraisRepo ligneDeFraisRepo;
	private MissionRepo missionRepo;

	public StartupListener(@Value("${app.version}") String appVersion, VersionRepo versionRepo,
			PasswordEncoder passwordEncoder, CollegueRepo collegueRepo, TransportRepo transportRepo,
			NatureRepo natureRepo, LigneDeFraisRepo ligneDeFraisRepo, MissionRepo missionRepo) {

		this.appVersion = appVersion;
		this.versionRepo = versionRepo;
		this.passwordEncoder = passwordEncoder;

		this.collegueRepo = collegueRepo;
		this.transportRepo = transportRepo;
		this.natureRepo = natureRepo;
		this.ligneDeFraisRepo = ligneDeFraisRepo;
		this.missionRepo = missionRepo;
	}

	@EventListener(ContextRefreshedEvent.class)
	public void onStart() {
		this.versionRepo.save(new Version(appVersion));

		// Création de deux utilisateurs

		Collegue col1 = new Collegue();
		col1.setNom("Admin");
		col1.setPrenom("DEV");
		col1.setEmail("admin@dev.fr");
		col1.setMotDePasse(passwordEncoder.encode("superpass"));
		col1.setRoles(Arrays.asList(new RoleCollegue(col1, Role.ROLE_ADMINISTRATEUR),
				new RoleCollegue(col1, Role.ROLE_UTILISATEUR)));
		this.collegueRepo.save(col1);

		Collegue col2 = new Collegue();
		col2.setNom("User");
		col2.setPrenom("DEV");
		col2.setEmail("user@dev.fr");
		col2.setMotDePasse(passwordEncoder.encode("superpass"));
		col2.setRoles(Arrays.asList(new RoleCollegue(col2, Role.ROLE_UTILISATEUR)));
		this.collegueRepo.save(col2);

		Collegue col3 = new Collegue();

		col3.setNom("Manager");
		col3.setPrenom("DEV");
		col3.setEmail("manager@dev.fr");
		col3.setMotDePasse(passwordEncoder.encode("superpass"));
		col3.setRoles(Arrays.asList(new RoleCollegue(col3, Role.ROLE_UTILISATEUR),
				new RoleCollegue(col3, Role.ROLE_MANAGER)));
		this.collegueRepo.save(col3);

		Transport tr1 = new Transport();
		tr1.setLibelle("Avion");
		this.transportRepo.save(tr1);

		Transport tr2 = new Transport();
		tr2.setLibelle("Covoiturage");
		this.transportRepo.save(tr2);

		Transport tr3 = new Transport();
		tr3.setLibelle("Train");
		this.transportRepo.save(tr3);

		Transport tr4 = new Transport();
		tr4.setLibelle("Voiture de service");
		this.transportRepo.save(tr4);

		Nature n1 = new Nature();

		n1.setLibelle("Conseil");
		n1.setEstFacture(true);
		n1.setEstPrime(true);
		n1.setTjm(800);
		n1.setValeurPrime(new BigDecimal(5));

		this.natureRepo.save(n1);

		Nature n2 = new Nature();

		n2.setLibelle("Formation");
		n2.setEstFacture(true);
		n2.setEstPrime(false);
		n2.setTjm(1000);
		n2.setValeurPrime(new BigDecimal(0));
		this.natureRepo.save(n2);

		Nature n3 = new Nature();

		n3.setLibelle("Expertise technique");
		n3.setEstFacture(false);
		n3.setEstPrime(false);
		n3.setTjm(0);
		n3.setValeurPrime(new BigDecimal(0));
		this.natureRepo.save(n3);

		Mission miss1 = new Mission();
		miss1.setCollegue(col1);
		miss1.setDateDebut(LocalDate.of(2020, 3, 2));
		miss1.setDateFin(LocalDate.of(2020, 3, 2));
		miss1.setNature(n1);
		miss1.setStatus(Status.INITIALE);
		miss1.setTransport(tr2);
		miss1.setVilleArrivee("Paris");
		miss1.setVilleDepart("Lille");
		this.missionRepo.save(miss1);

		Mission miss2 = new Mission();
		miss2.setCollegue(col2);
		miss2.setDateDebut(LocalDate.of(2020, 3, 8));
		miss2.setDateFin(LocalDate.of(2020, 3, 12));
		miss2.setNature(n2);
		miss2.setStatus(Status.INITIALE);
		miss2.setTransport(tr3);
		miss2.setVilleArrivee("Paris");
		miss2.setVilleDepart("Lille");
		this.missionRepo.save(miss2);

		Mission miss3 = new Mission();
		miss3.setCollegue(col2);
		miss3.setDateDebut(LocalDate.of(2020, 4, 1));
		miss3.setDateFin(LocalDate.of(2020, 4, 15));
		miss3.setNature(n2);
		miss3.setStatus(Status.REJETEE);
		miss3.setTransport(tr3);
		miss3.setVilleArrivee("Paris");
		miss3.setVilleDepart("Berlin");
		this.missionRepo.save(miss3);
		
		LigneDeFrais frais1 = new LigneDeFrais(LocalDate.parse("2020-02-24"),NatureFrais.Hotel,200,miss1);
		this.ligneDeFraisRepo.save(frais1);
		
		LigneDeFrais frais2 = new LigneDeFrais(LocalDate.parse("2020-02-24"),NatureFrais.PetitDejeuner,15,miss1);
		this.ligneDeFraisRepo.save(frais2);
		
		LigneDeFrais frais3 = new LigneDeFrais(LocalDate.parse("2020-02-24"),NatureFrais.Transport,90,miss1);
		this.ligneDeFraisRepo.save(frais3);
		
		LigneDeFrais frais4 = new LigneDeFrais(LocalDate.parse("2020-02-24"),NatureFrais.Restaurant,30,miss1);
		this.ligneDeFraisRepo.save(frais4);
		
		

		n1.setDateFin(LocalDate.now().minusDays(1));
		this.natureRepo.save(n1);
		n1.setTjm(2000);
		this.natureRepo.save(
				new Nature(n1.getLibelle(), n1.isEstFacture(), n1.isEstPrime(), n1.getTjm(), n1.getValeurPrime()));


	}


}

