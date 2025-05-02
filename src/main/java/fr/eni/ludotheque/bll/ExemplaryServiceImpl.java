package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Exemplary;
import org.springframework.stereotype.Service;

@Service
public class ExemplaryServiceImpl implements ExemplaryService {
    @Override
    public void changeIsRentableState(Exemplary exemplary) {
        exemplary.setIsRentable(!exemplary.getIsRentable());
    }
}
