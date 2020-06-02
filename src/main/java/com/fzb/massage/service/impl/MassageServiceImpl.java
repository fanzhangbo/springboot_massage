package com.fzb.massage.service.impl;

import com.fzb.massage.entity.Massage;
import com.fzb.massage.mapper.MassageMapper;
import com.fzb.massage.service.MassageService;
import com.fzb.massage.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@Service
public class MassageServiceImpl implements MassageService {

    @Autowired
    private MassageMapper massageMapper;

    @Override
    public List<Massage> listMassage(Map condition) {
        return this.massageMapper.listMassage(condition);
    }

    @Override
    public Massage findMassageById(Long id) {
        return this.massageMapper.findMassageById(id);
    }

    @Override
    public void addMassage(Massage massage) {
        massage.setCreateTime(new Date());
        this.massageMapper.addMassage(massage);
    }

    @Override
    public void updateMassage(Massage massage) {
        this.massageMapper.updateMassage(massage);
    }

    @Override
    public void delMassage(Long id) {
        // 删除对应图片
        Massage massageById = this.massageMapper.findMassageById(id);
        if (null != massageById) {
            this.massageMapper.delMassage(massageById.getId());

            if (null != massageById.getImg()) {
                new FileUtil().deleteFile(massageById.getImg());
            }
        }

    }
}
