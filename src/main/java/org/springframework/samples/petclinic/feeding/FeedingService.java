package org.springframework.samples.petclinic.feeding;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    public Feeding save(Feeding p) throws UnfeasibleFeedingException {
        return null;       
    }

    
}
