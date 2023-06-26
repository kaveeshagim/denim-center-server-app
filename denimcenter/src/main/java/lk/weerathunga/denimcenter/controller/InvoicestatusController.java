package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.InvoicestatusDao;
import lk.weerathunga.denimcenter.entity.Invoicestatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/invoicestatuses")
public class InvoicestatusController {

    @Autowired
    private InvoicestatusDao invoicestatusDao;

    @GetMapping(path = "/list", produces = "application/json")
    public List<Invoicestatus> get() {

        List<Invoicestatus> invoicestatuses = this.invoicestatusDao.findAll();

        invoicestatuses = invoicestatuses.stream().map(
                invoicestatus -> { Invoicestatus d = new Invoicestatus();
                    d.setId(invoicestatus.getId());
                    d.setName(invoicestatus.getName());
                    return d; }
        ).collect(Collectors.toList());

        return invoicestatuses;
    }
}

