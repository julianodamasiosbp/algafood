package com.acme.algafood.api;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkhost")
public class HostCheckController {

    @GetMapping()
    public String checkHost() throws UnknownHostException {
        return "Hostname: " + InetAddress.getLocalHost().getHostName() 
            + " - IP Address: " + InetAddress.getLocalHost().getHostAddress();
    }

}
