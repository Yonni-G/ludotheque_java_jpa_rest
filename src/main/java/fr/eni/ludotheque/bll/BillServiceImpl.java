package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Bill;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BillServiceImpl implements BillService {

    public void setBillPaid(Bill bill) {
        if (bill.getPaidAt() == null) {
            bill.setPaidAt(new Date());
        }
    }
}
