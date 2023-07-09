package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.CustomerpaymentDao;
import lk.weerathunga.denimcenter.entity.Customer;
import lk.weerathunga.denimcenter.entity.Customerpayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/customerpayments")
public class CustomerpaymentController {

    @Autowired
    private CustomerpaymentDao customerpaymentdao;

    @GetMapping(produces = "application/json")
    public List<Customerpayment> get(@RequestParam HashMap<String, String> params) {

        String invoiceid = params.get("invoiceid");
        String customerid = params.get("customerid");
        String paymentstatusid = params.get("paymentstatusid");


        List<Customerpayment> customerpayments = this.customerpaymentdao.findAll();

        if(params.isEmpty()) return customerpayments;

        Stream<Customerpayment> estream = customerpayments.stream();

        if(invoiceid!=null) estream = estream.filter(e -> e.getInvoice().getId()==Integer.parseInt(invoiceid));
        if(customerid!=null) estream = estream.filter(e -> e.getCustomer().getId()==Integer.parseInt(customerid));
        if(paymentstatusid!=null) estream = estream.filter(e -> e.getPaymentstatus().getId()==Integer.parseInt(paymentstatusid));

        return estream.collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> add(@RequestBody Customerpayment customerpayment){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        if(errors=="")
            customerpaymentdao.save(customerpayment);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(customerpayment.getId()));
        response.put("url","/customerpayments/"+customerpayment.getId());
        response.put("errors",errors);

        return response;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Customerpayment customerpayment){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        if(errors=="") customerpaymentdao.save(customerpayment);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(customerpayment.getId()));
        response.put("url","/customerpayments/"+customerpayment.getId());
        response.put("errors",errors);

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){
        System.out.println(id);
        HashMap<String,String> response = new HashMap<>();
        String errors="";
        Customerpayment pay = customerpaymentdao.findAllById(id);
        if(pay==null)
            errors = errors+"<br> customerpayment Does Not Existed";
        if(errors=="") customerpaymentdao.delete(pay);
        else errors = "Server Validation Errors : <br> "+errors;
        response.put("id",String.valueOf(id));
        response.put("url","/customerpayments/"+id);
        response.put("errors",errors);
        return response;
    }
}
