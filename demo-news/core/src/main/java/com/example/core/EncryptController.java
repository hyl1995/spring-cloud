package com.example.core;

import com.example.core.CipherUtil.EncryptOpenService;
import com.example.core.CipherUtil.KeyRequest;
import com.example.core.CipherUtil.KeyResponse;
import com.example.core.CipherUtil.RSAResponse;
import com.example.core.model.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("open/encrypt")
public class EncryptController extends BaseController {

    @Autowired
    private EncryptOpenService encryptOpenService;

    @RequestMapping(value = "getRSA",method = RequestMethod.POST)    //@DisabledEncrypt
    public Result<RSAResponse> getRSA(){
        return encryptOpenService.getRSA();
    }

    @RequestMapping(value = "getKey",method = RequestMethod.POST)    //@DisabledEncrypt
    public Result<KeyResponse> getKey(@Valid @RequestBody KeyRequest request, BindingResult result)throws Exception{
        validate(result);
        return encryptOpenService.getKey(request);
    }
}
