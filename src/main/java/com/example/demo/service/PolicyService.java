package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Policy;
import com.example.demo.repository.PolicyRepository;

@Service
public class PolicyService {

    private final PolicyRepository policyRepository;

    @Autowired
    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }


    	public Policy getPolicyById(Long id) {
    	    Optional<Policy> policy = policyRepository.findById(id);
    	    if (policy.isPresent()) {
    	        return policy.get();
    	    } else {
    	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Policy not found");
    	    }
    	}


    public Policy createPolicy(Policy policy) {
        return policyRepository.save(policy);
    }

    public Policy updatePolicy(Long id, Policy policy) {
        Optional<Policy> existingPolicyOptional = policyRepository.findById(id);
        if (existingPolicyOptional.isPresent()) {
            Policy existingPolicy = existingPolicyOptional.get();
            existingPolicy.setPolicyNumber(policy.getPolicyNumber());
            existingPolicy.setPolicyType(policy.getPolicyType());
            existingPolicy.setStartDate(policy.getStartDate());
            existingPolicy.setEndDate(policy.getEndDate());
            existingPolicy.setPremiumAmount(policy.getPremiumAmount());
            return policyRepository.save(existingPolicy);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Policy not found");
        }
    }

    public void deletePolicy(Long id) {
        policyRepository.deleteById(id);
    }
}