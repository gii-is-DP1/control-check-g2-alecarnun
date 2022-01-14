package org.springframework.samples.petclinic.feeding;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.stereotype.Service;

@Service
public class FeedingService {

    @Autowired
    FeedingRepository feedingRepo;
    
    public List<Feeding> getAll(){
        return feedingRepo.findAll();
    }

    public List<FeedingType> getAllFeedingTypes(){
        return feedingRepo.findAllFeedingTypes();
    }

    public FeedingType getFeedingType(String typeName) {
        return feedingRepo.findFeedingTypeByName(typeName);
    }

    @Transactional(rollbackOn = UnfeasibleFeedingException.class)
    public Feeding save(Feeding p) throws UnfeasibleFeedingException {
        Pet pet=p.getPet();
        PetType petType = p.getFeedingType().getPetType();
            if (!pet.getType().equals(petType)) {            	
            	throw new UnfeasibleFeedingException();
            

        }else{
        return feedingRepo.save(p);    
    }}

    
}
