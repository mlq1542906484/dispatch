package com.jiadun.dispatch.service.impl;

import org.springframework.stereotype.Service;

/**
 * @Author: caiping
 * @Description:
 * @Date: Create in  2019/6/20 16:10
 */
@Service("per")
public class Per {

    public boolean hasPermissions(String authority){
        return true;
    }

}
