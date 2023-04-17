package ma.emsi.Hospital.repositories;

import ma.emsi.Hospital.entities.Consultation;
import ma.emsi.Hospital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
}
