package com.example.demo.service;

import com.example.demo.entity.Registration;
import com.example.demo.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;

    // Constructor Injection
    public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public Registration saveRegistration(Registration registration) {
        return registrationRepository.save(registration);
    }

    @Override
    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    @Override
    public Optional<Registration> getRegistrationById(Long id) {
        return registrationRepository.findById(id);
    }

    @Override
    public void deleteRegistration(Long id) {
        registrationRepository.deleteById(id);
    }

    @Override
    public Registration updatePaymentStatus(Long id, String paymentStatus) {

        Optional<Registration> optional = registrationRepository.findById(id);

        if (optional.isPresent()) {
            Registration registration = optional.get();
            registration.setPaymentStatus(paymentStatus);
            return registrationRepository.save(registration);
        } else {
            throw new RuntimeException("Registration not found with id: " + id);
        }
    }
}
