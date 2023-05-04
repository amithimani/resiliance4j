package dev.knowledgecafe.supplier.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @GetMapping
    public String service() {
        return "Serving request from Supplier Service";
    }

}
