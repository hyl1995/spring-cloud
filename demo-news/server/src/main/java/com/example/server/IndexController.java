package com.example.server;

import com.example.core.apiconf.ApiVersion;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiVersion(1)
@RequestMapping("/server/{version}")
public class IndexController {


}
