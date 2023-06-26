package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.PaymentmethodDao;
import lk.weerathunga.denimcenter.entity.Paymentmethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/paymentmethods")
public class PaymentmethodController {

    @Autowired
    private PaymentmethodDao paymentmethodDao;

    @GetMapping(path = "/list", produces = "application/json")
    public List<Paymentmethod> get() {

        List<Paymentmethod> paymentmethods = this.paymentmethodDao.findAll();

        paymentmethods = paymentmethods.stream().map(
                paymentmethod -> { Paymentmethod d = new Paymentmethod();
                    d.setId(paymentmethod.getId());
                    d.setName(paymentmethod.getName());
                    return d; }
        ).collect(Collectors.toList());

        return paymentmethods;
    }
}

