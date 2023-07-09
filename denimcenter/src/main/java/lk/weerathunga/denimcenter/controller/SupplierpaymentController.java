package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.SupplierpaymentDao;
import lk.weerathunga.denimcenter.entity.Supplierpayment;
import lk.weerathunga.denimcenter.entity.Supplierpayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/supplierpayments")
public class SupplierpaymentController {

    @Autowired
    private SupplierpaymentDao supplierpaymentdao;

    @GetMapping(produces = "application/json")
    public List<Supplierpayment> get(@RequestParam HashMap<String, String> params) {

        String grnid = params.get("grnid");
        String supplierid = params.get("supplierid");
        String paymentstatusid = params.get("paymentstatusid");

        List<Supplierpayment> supplierpayments = this.supplierpaymentdao.findAll();

        if(params.isEmpty()) return supplierpayments;

        Stream<Supplierpayment> estream = supplierpayments.stream();

        if(grnid!=null) estream = estream.filter(e -> e.getGrn().getId()==Integer.parseInt(grnid));
        if(supplierid!=null) estream = estream.filter(e -> e.getSupplier().getId()==Integer.parseInt(supplierid));
        if(paymentstatusid!=null) estream = estream.filter(e -> e.getPaymentstatus().getId()==Integer.parseInt(paymentstatusid));

        return estream.collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> add(@RequestBody Supplierpayment supplierpayment){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        if(errors=="")
            supplierpaymentdao.save(supplierpayment);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(supplierpayment.getId()));
        response.put("url","/supplierpayments/"+supplierpayment.getId());
        response.put("errors",errors);

        return response;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Supplierpayment supplierpayment){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        if(errors=="") supplierpaymentdao.save(supplierpayment);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(supplierpayment.getId()));
        response.put("url","/supplierpayments/"+supplierpayment.getId());
        response.put("errors",errors);

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){
        System.out.println(id);
        HashMap<String,String> response = new HashMap<>();
        String errors="";
        Supplierpayment pay = supplierpaymentdao.findAllById(id);
        if(pay==null)
            errors = errors+"<br> supplierpayment Does Not Existed";
        if(errors=="") supplierpaymentdao.delete(pay);
        else errors = "Server Validation Errors : <br> "+errors;
        response.put("id",String.valueOf(id));
        response.put("url","/supplierpayments/"+id);
        response.put("errors",errors);
        return response;
    }
}
