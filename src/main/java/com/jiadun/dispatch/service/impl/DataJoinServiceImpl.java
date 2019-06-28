package com.jiadun.dispatch.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fit.utils.se.StringUtil;
import com.jiadun.dispatch.entity.DisPersonnelDiscoverData;
import com.jiadun.dispatch.entity.DisWarningData;
import com.jiadun.dispatch.service.DisAutoConfigService;
import com.jiadun.dispatch.service.DisPersonnelDiscoverDataService;
import com.jiadun.dispatch.service.DisWarningDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.security.auth.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
@Slf4j
public class DataJoinServiceImpl {


    @Autowired
    private DisWarningDataService disWarningDataService;

    @Autowired
    private DisPersonnelDiscoverDataService disPersonnelDiscoverDataService;

    @Autowired
    private DisAutoConfigService disAutoConfigService;

    /**
     * @describe: json转换mapper
     * @author: hcl
     * @date: 2018/8/10 15:19
     */
    @Autowired
    private ObjectMapper objectMapper;

    private int count = 0;

    private DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");


    /**
     * @describe: 预警消息接收
     * @author: hcl  
     * @date: 2019/6/26 10:26  
     * @param: [record]  
     * @return void  
     */
    @KafkaListener(topics="yujing")
    public void yujing(ConsumerRecord<?,String> record){
        try {
            DisWarningData disWarningData = objectMapper.readValue(record.value(),DisWarningData.class);
            disWarningData.setCode(this.getCode("YJ"));
            disWarningData.setITime(LocalDateTime.now());
            disWarningDataService.save(disWarningData);
            disAutoConfigService.createOperation(2,disWarningData.getId(),null,null,null,true);

        } catch (IOException e) {
            log.error("接收kafka数据转换异常:内容“{}”",record.value(),e);
        }
    }


    /**
     * @describe: 人员发现消息接收
     * @author: hcl  
     * @date: 2019/6/26 10:26  
     * @param: [record]  
     * @return void  
     */
    @KafkaListener(topics="renyuanfaxian")
    public void renyuanfaxian(ConsumerRecord<?,String> record){
        try {
            DisPersonnelDiscoverData disPersonnelDiscoverData = objectMapper.readValue(record.value(),DisPersonnelDiscoverData.class);
            disPersonnelDiscoverData.setCode(this.getCode("YP"));
            disPersonnelDiscoverData.setITime(LocalDateTime.now());
            disPersonnelDiscoverDataService.save(disPersonnelDiscoverData);
            disAutoConfigService.createOperation(1,disPersonnelDiscoverData.getId(),null,null,null,true);
        } catch (IOException e) {
            log.error("接收kafka数据转换异常:内容“{}”",record.value(),e);
        }
    }



    /**
     * @describe: 生成编号
     * @author: hcl
     * @date: 2019/6/26 10:51
     * @param: [prefix]
     * @return java.lang.String
     */
    private synchronized String getCode(String prefix){
        if(count > 9999){
            count = 0;
        }
        StringBuilder sb = new StringBuilder();
        return sb.append(prefix).append(LocalDateTime.now().format(df)).append(StringUtil.replenish(new Integer(++count).toString(),4,StringUtil.BEFORE,null)).toString();
    }


}
