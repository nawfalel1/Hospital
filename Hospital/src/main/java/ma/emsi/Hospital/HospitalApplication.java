package ma.emsi.Hospital;

import ma.emsi.Hospital.entities.*;
import ma.emsi.Hospital.repositories.ConsultationRepository;
import ma.emsi.Hospital.repositories.MedecinRepository;
import ma.emsi.Hospital.repositories.PatientRepository;
import ma.emsi.Hospital.repositories.RendezVousRepository;
import ma.emsi.Hospital.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}
	@Bean
	CommandLineRunner start(IHospitalService hospitalService,PatientRepository patientRepository,
							MedecinRepository medecinRepository,ConsultationRepository consultationRepository,
							RendezVousRepository rendezVousRepository){
		return args -> {
			Stream.of("Omar","Hamza","Najia")
					.forEach(name->{
						Medecin medecin=new Medecin();
						medecin.setNom(name);
						medecin.setEmail(name+(int)Math.random()*100+"@gmail.com");
						medecin.setSpecialite("Neurologue");
						hospitalService.saveMedecin(medecin);});
			Stream.of("Mohamed","Hassan","Najat")
					.forEach(name->{
						Patient patient=new Patient();
						patient.setNom(name);
						patient.setMalade(false);
						patient.setDateNaissance(new Date());
						hospitalService.savePatient(patient);});

			Patient patient=patientRepository.findById(1L).orElse(null);
			Patient patient1=patientRepository.findByNom("Mohamed");

			Medecin medecin=medecinRepository.findById(1L).orElse(null);
			Medecin medecin1=medecinRepository.findByNom("Omar");

			RendezVous rendezVous=new RendezVous();
			rendezVous.setPatient(patient);
			rendezVous.setDateRV(new Date());
			rendezVous.setMedecin(medecin);
			rendezVous.setStatus(StatusRDV.PENDING);
			hospitalService.saveRDV(rendezVous);

			Consultation consultation=new Consultation();
			consultation.setDateConsultation(new Date());
			consultation.setRendezVous(rendezVous);
			consultation.setRapportConsultation("Rapport de la consultation");
			hospitalService.saveConsultation(consultation);



		};
	}
}
