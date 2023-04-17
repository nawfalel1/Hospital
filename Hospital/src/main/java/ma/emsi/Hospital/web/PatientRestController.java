package ma.emsi.Hospital.web;

import ma.emsi.Hospital.entities.Patient;
import ma.emsi.Hospital.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientRestController {
    Patient patient;
    @Autowired
    private PatientRepository patientRepository;
    @GetMapping("/showPatients")
    public List<Patient> patientList(){
        return patientRepository.findAll();
    }
    @DeleteMapping("/deletePatients/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientRepository.deleteById(id);
    }
    @PutMapping("/updatePatients/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient newPatient) {
        return patientRepository.findById(id)
                .map(patient -> {
                    patient.setNom(newPatient.getNom());
                    // Mettre à jour les autres attributs du patient si nécessaire
                    return patientRepository.save(patient);
                })
                .orElseGet(() -> {
                    newPatient.setId(id);
                    return patientRepository.save(newPatient);
                });
    }

}
