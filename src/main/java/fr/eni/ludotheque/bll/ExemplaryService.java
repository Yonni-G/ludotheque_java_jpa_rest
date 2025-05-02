package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Exemplary;

public interface ExemplaryService {
    void changeIsRentableState(Exemplary exemplary);
}
