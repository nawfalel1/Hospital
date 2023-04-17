package ma.emsi.Hospital.service;

import ma.emsi.Hospital.entities.Consultation;
import ma.emsi.Hospital.entities.Medecin;
import ma.emsi.Hospital.entities.Patient;
import ma.emsi.Hospital.entities.RendezVous;

public interface IHospitalService {
    public Patient savePatient(Patient patient);
    public Medecin saveMedecin(Medecin medecin);
    public RendezVous saveRDV(RendezVous rendezVous);
    public Consultation saveConsultation(Consultation consultation);
}
