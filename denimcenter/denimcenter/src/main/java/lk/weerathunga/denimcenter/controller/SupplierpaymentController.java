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

        /*if(supplierpaymentdao.findByCode(supplierpayment.getCode())!=null)
            errors = errors+"<br> Existing Code";*/

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

        //Supplierpayment prod1 = supplierpaymentdao.findByCode(supplierpayment.getCode());

        //System.out.println("ID-"+supplierpayment.getId()+"-"+supplierpayment.getNic());
        //if(prod1!=null) System.out.println("NIC-"+prod1.getNic());

        /*if(prod1!=null && supplierpayment.getId()!=prod1.getId())
            errors = errors+"<br> Existing Code";*/

        //System.out.println(supplierpayment.getFirstname());
        //System.out.println("Err-"+errors);

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
        Supplierpayment emp1 = supplierpaymentdao.findAllById(id);
        if(emp1==null)
            errors = errors+"<br> supplierpayment Does Not Existed";
        if(errors=="") supplierpaymentdao.delete(emp1);
        else errors = "Server Validation Errors : <br> "+errors;
        response.put("id",String.valueOf(id));
        response.put("url","/supplierpayments/"+id);
        response.put("errors",errors);
        return response;
    }
}
