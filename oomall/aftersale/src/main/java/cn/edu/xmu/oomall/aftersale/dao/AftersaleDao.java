package cn.edu.xmu.oomall.aftersale.dao;

import cn.edu.xmu.oomall.aftersale.dao.bo.Aftersale;
import cn.edu.xmu.oomall.aftersale.mapper.AftersalePOMapper;
import cn.edu.xmu.oomall.aftersale.mapper.po.AftersalePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AftersaleDao {

    @Autowired
    private AftersalePOMapper aftersalePOMapper;

    public Aftersale findById(Long id) {
        Optional<AftersalePo> po = aftersalePOMapper.findById(id);
        return po.map(Aftersale::new).orElse(null);
    }

    public void save(Aftersale aftersale) {
        AftersalePo po = aftersalePOMapper.findById(aftersale.getId()).orElse(new AftersalePo());
        po.setStatus(aftersale.getStatus());
        po.setConclusion(aftersale.getConclusion());
        // Update other fields if necessary
        aftersalePOMapper.save(po);
    }
}
