package dev;

import dev.domain.Collegue;
import dev.domain.Mission;
import dev.domain.Nature;
import dev.domain.Role;
import dev.domain.RoleCollegue;
import dev.domain.Status;
import dev.domain.Transport;
import dev.domain.Version;
import dev.repository.CollegueRepo;
import dev.repository.MissionRepo;
import dev.repository.NatureRepo;
import dev.repository.TransportRepo;
import dev.repository.VersionRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * Code de démarrage de l'application. Insertion de jeux de données.
 */
@Component
public class StartupListener {

	private static final Status INITIALE = null;
	private String appVersion;
	private VersionRepo versionRepo;
	private PasswordEncoder passwordEncoder;
	private CollegueRepo collegueRepo;
	private NatureRepo natureRepo;
	private MissionRepo missionRepo;
	private TransportRepo transportRepo;

	public StartupListener(@Value("${app.version}") String appVersion, VersionRepo versionRepo, PasswordEncoder passwordEncoder, CollegueRepo collegueRepo, NatureRepo natureRepo, MissionRepo missionRepo, TransportRepo transportRepo) {
        this.appVersion = appVersion;
        this.versionRepo = versionRepo;
        this.passwordEncoder = passwordEncoder;
        this.collegueRepo = collegueRepo;
        this.natureRepo= natureRepo;
        this.missionRepo= missionRepo;
        this.transportRepo=transportRepo;
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

		
		// Création d'un nouveau collègue

		Collegue col3 = new Collegue();
		col2.setNom("DEMAS");
		col2.setPrenom("Jacques");
		col2.setEmail("demas@dev.fr");
		col2.setMotDePasse(passwordEncoder.encode("superpass"));
		col2.setRoles(Arrays.asList(new RoleCollegue(col3, Role.ROLE_UTILISATEUR)));
		this.collegueRepo.save(col3);

		// Création de transport

		Transport transport1 = new Transport();
		transport1.setLibelle("train");
		this.transportRepo.save(transport1);

		// Création de nature
		Nature nat1 = new Nature();

		nat1.setLibelle("Conseil");
		nat1.setEstFacture(true);
		nat1.setEstPrime(true);
		nat1.setTjm(500);
		nat1.setValeurPrime(new BigDecimal(5));
		this.natureRepo.save(nat1);
		
		Nature nat2 = new Nature();

		nat1.setLibelle("Expertise");
		nat1.setEstFacture(true);
		nat1.setEstPrime(true);
		nat1.setTjm(900);
		nat1.setValeurPrime(new BigDecimal(5));
		this.natureRepo.save(nat2);
		
		if (this.missionRepo.count() == 0) {
		// Création de mission
		Mission miss1 = new Mission();
		miss1.setDateDebut(LocalDate.of(2020, 03, 03));
		miss1.setDateFin(LocalDate.of(2020, 03, 06));
		miss1.setVilleDepart("Paris");
		miss1.setVilleArrivee("Marseille");
		miss1.setTransport(transportRepo.findByLibelle(transport1.getLibelle()));
		miss1.setCollegue(collegueRepo.findByEmail(col3.getEmail()).get());
		miss1.setNature(natureRepo.findByLibelle(nat1.getLibelle()).get());
		miss1.setStatus(INITIALE);
		miss1.setFicheDeFrais(null);
		
		this.missionRepo.save(miss1);}
	}

}
