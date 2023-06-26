package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.PaymentstatusDao;
import lk.weerathunga.denimcenter.entity.Paymentstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/paymentstatuses")
public class PaymentstatusController {

    @Autowired
    private PaymentstatusDao paymentstatusDao;

    @GetMapping(path = "/list", produces = "application/json")
    public List<Paymentstatus> get() {

        List<Paymentstatus> paymentstatuses = this.paymentstatusDao.findAll();

        paymentstatuses = paymentstatuses.stream().map(
                paymentstatus -> { Paymentstatus d = new Paymentstatus();
                    d.setId(paymentstatus.getId());
                    d.setName(paymentstatus.getName());
                    return d; }
        ).collect(Collectors.toList());

        return paymentstatuses;
    }
}

