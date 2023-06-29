package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.CustomerreturnDao;
import lk.weerathunga.denimcenter.entity.Customer;
import lk.weerathunga.denimcenter.entity.Customerreturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/customerreturns")
public class CustomerreturnController {

    @Autowired
    private CustomerreturnDao customerreturndao;

    @GetMapping(produces = "application/json")
    public List<Customerreturn> get(@RequestParam HashMap<String, String> params) {


        String invoiceid = params.get("invoiceid");
        String customerid = params.get("customerid");


        List<Customerreturn> customerreturns = this.customerreturndao.findAll();

        if(params.isEmpty()) return customerreturns;

        Stream<Customerreturn> estream = customerreturns.stream();

        if(invoiceid!=null) estream = estream.filter(e -> e.getInvoice().getId()==Integer.parseInt(invoiceid));
        if(customerid!=null) estream = estream.filter(e -> e.getCustomer().getId()==Integer.parseInt(customerid));


        return estream.collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> add(@RequestBody Customerreturn customerreturn){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        /*if(customerreturndao.findByCode(customerreturn.getCode())!=null)
            errors = errors+"<br> Existing Code";

        if(errors=="")
            customerreturndao.save(customerreturn);
        else errors = "Server Validation Errors : <br> "+errors;*/

        response.put("id", String.valueOf(customerreturn.getId()));
        response.put("url","/customerreturns/"+customerreturn.getId());
        response.put("errors",errors);

        return response;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Customerreturn customerreturn){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        /*Customerreturn prod1 = customerreturndao.findByCode(customerreturn.getCode());

        //System.out.println("ID-"+customerreturn.getId()+"-"+customerreturn.getNic());
        //if(prod1!=null) System.out.println("NIC-"+prod1.getNic());

        if(prod1!=null && customerreturn.getId()!=prod1.getId())
            errors = errors+"<br> Existing Code";

        //System.out.println(customerreturn.getFirstname());
        //System.out.println("Err-"+errors);

        if(errors=="") customerreturndao.save(customerreturn);
        else errors = "Server Validation Errors : <br> "+errors;*/

        response.put("id", String.valueOf(customerreturn.getId()));
        response.put("url","/customerreturns/"+customerreturn.getId());
        response.put("errors",errors);

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){
        System.out.println(id);
        HashMap<String,String> response = new HashMap<>();
        String errors="";
        Customerreturn emp1 = customerreturndao.findAllById(id);
        if(emp1==null)
            errors = errors+"<br> customer Does Not Existed";
        if(errors=="") customerreturndao.delete(emp1);
        else errors = "Server Validation Errors : <br> "+errors;
        response.put("id",String.valueOf(id));
        response.put("url","/customerreturns/"+id);
        response.put("errors",errors);
        return response;
    }
}
