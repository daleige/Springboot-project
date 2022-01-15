package com.chenyangqi;

import com.chenyangqi.server.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoApi {

    @Autowired
    private DemoService demoServer;

    @GetMapping("/query")
    public Long query(Long id) {
        return demoServer.query(id);
    }
}
