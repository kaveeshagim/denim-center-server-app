package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.InvoiceDao;
import lk.weerathunga.denimcenter.entity.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceDao invoicedao;

    @GetMapping(produces = "application/json")
    public List<Invoice> get(@RequestParam HashMap<String, String> params) {

        String number = params.get("number");
        String customerid = params.get("customerid");
        String corderid = params.get("corderid");
        String invoicestatusid = params.get("invoicestatusid");


        List<Invoice> invoices = this.invoicedao.findAll();

        if(params.isEmpty()) return invoices;

        Stream<Invoice> estream = invoices.stream();

        if(number!=null) estream = estream.filter(e -> e.getNumber().equals(number));
        if(customerid!=null) estream = estream.filter(e -> e.getCustomer().getId()==Integer.parseInt(customerid));
        if(corderid!=null) estream = estream.filter(e -> e.getCorder().getId()==Integer.parseInt(corderid));
        if(invoicestatusid!=null) estream = estream.filter(e -> e.getInvoicestatus().getId()==Integer.parseInt(invoicestatusid));


        return estream.collect(Collectors.toList());
    }

    @GetMapping(path = "/list", produces = "application/json")
    public List<Invoice> get() {

        List<Invoice> invoices = this.invoicedao.findAll();

        invoices = invoices.stream().map(
                invoice -> {Invoice s = new Invoice();
                    s.setId(invoice.getId());
                    s.setNumber(invoice.getNumber());
                    s.setPaidamount(invoice.getPaidamount());

                    return s; }
        ).collect(Collectors.toList());

        return invoices;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> add(@RequestBody Invoice invoice){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        if(invoicedao.findByNumber(invoice.getNumber())!=null)
            errors = errors+"<br> Existing Number";

        if(errors=="")
            invoicedao.save(invoice);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(invoice.getId()));
        response.put("url","/invoices/"+invoice.getId());
        response.put("errors",errors);

        return response;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Invoice invoice){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        Invoice prod1 = invoicedao.findByNumber(invoice.getNumber());

        //System.out.println("ID-"+invoice.getId()+"-"+invoice.getNic());
        //if(prod1!=null) System.out.println("NIC-"+prod1.getNic());

        if(prod1!=null && invoice.getId()!=prod1.getId())
            errors = errors+"<br> Existing Number";

        //System.out.println(invoice.getFirstname());
        //System.out.println("Err-"+errors);

        if(errors=="") invoicedao.save(invoice);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(invoice.getId()));
        response.put("url","/invoices/"+invoice.getId());
        response.put("errors",errors);

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){
        System.out.println(id);
        HashMap<String,String> response = new HashMap<>();
        String errors="";
        Invoice emp1 = invoicedao.findAllById(id);
        if(emp1==null)
            errors = errors+"<br> invoice Does Not Existed";
        if(errors=="") invoicedao.delete(emp1);
        else errors = "Server Validation Errors : <br> "+errors;
        response.put("id",String.valueOf(id));
        response.put("url","/invoices/"+id);
        response.put("errors",errors);
        return response;
    }
}
