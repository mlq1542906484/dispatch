package com.jiadun.dispatch.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fit.utils.se.EmptyUtils;
import com.jiadun.dispatch.entity.DisIssuedInstance;
import com.jiadun.dispatch.entity.SysArea;
import com.jiadun.dispatch.mapper.SysAreaMapper;
import com.jiadun.dispatch.service.SysAreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiadun.dispatch.vo.sys.res.SysAreaTreeVo;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gen
 * @since 2019-06-20
 */
@Service
public class SysAreaServiceImpl extends ServiceImpl<SysAreaMapper, SysArea> implements SysAreaService {

    @Override
    public List<SysAreaTreeVo> getAreaTree() {
        return baseMapper.getAreaTree();
    }

    @Override
    public List<String> getAreaTree(List<String> result, String code) {
        List<String> lins = new ArrayList<>();
        SysArea sysArea = baseMapper.selectOne(Wrappers.<SysArea>query().lambda().eq(SysArea::getCode,code));
        if(EmptyUtils.isNotEmpty(sysArea)){
//            lins.add(code);
//            lins.addAll(result);
//            result.clear();
//            result.addAll(lins);
            result.add(code);
        }
        if(EmptyUtils.isNotEmpty(sysArea.getPCode())&&!"0".equals(sysArea.getPCode())){
            getAreaTree(result,sysArea.getPCode());
        }
        return result;
    }

    public static void main(String[] args) {
        String code1 = "5100000";
        String code2 = "5100001";
        String code3 = "5100002";

        List<String> list = new ArrayList<>();
        list.add(code3);
        list.add(code2);
        list.add(code1);
//        list.sort(String::compareTo);
        for (String code : list){
            System.out.println(code);
        }



    }

    @Override
    public String findAreaCode(String persionArea, Integer deptLevel) {

//        deptLevel(级别,1:省厅,2:市级,3:区县)

        //地址:XX省XX市XX区
        Map<String,String> map = addressResolution(persionArea);
        String provinceCode = null;
        String cityCode = null;
        String countyCode = null;

        //获取省份
        String province = map.get("province");
        if(EmptyUtils.isNotEmpty(province)){
            SysArea sysArea = super.getOne(Wrappers.<SysArea>query().lambda().eq(SysArea::getType,1).eq(SysArea::getName,province));
            if(EmptyUtils.isNotEmpty(sysArea)){
                provinceCode = sysArea.getCode();
                if(deptLevel.equals(1)){
                    return provinceCode;
                }
                //获取市
                String city = map.get("city");
                if(EmptyUtils.isNotEmpty(city)){
                    sysArea = super.getOne(Wrappers.<SysArea>query().lambda().eq(SysArea::getPCode,provinceCode).eq(SysArea::getName,city));
                    if(EmptyUtils.isNotEmpty(sysArea)){
                        cityCode = sysArea.getCode();
                        if(deptLevel.equals(2)){
                            return cityCode;
                        }
                        //获取县
                        String county = map.get("county");
                        if(EmptyUtils.isNotEmpty(county)){
                            sysArea = super.getOne(Wrappers.<SysArea>query().lambda().eq(SysArea::getPCode,cityCode).eq(SysArea::getName,county));
                            if(EmptyUtils.isNotEmpty(sysArea)){
                                countyCode = sysArea.getCode();
                                if(deptLevel.equals(3)){
                                    return countyCode;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }


    /**
     * 解析地址
     * @author lin
     * @param address
     * @return
     */
    private Map<String,String> addressResolution(String address){
        String regex="(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)?(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
        Matcher m = Pattern.compile(regex).matcher(address);
        String province = null,city = null,county = null,town = null,village = null;
        Map<String,String> row=null;
        if(m.find()){
            row = new LinkedHashMap<String,String>();
            province = m.group("province");
            row.put("province", province == null?"":province.trim());
            city = m.group("city");
            row.put("city", city == null ? "" : city.trim());
            county = m.group("county");
            row.put("county", county==null?"":county.trim());
            town = m.group("town");
            row.put("town", town==null?"":town.trim());
            village = m.group("village");
            row.put("village", village==null?"":village.trim());
        }
        return row;
    }

}
