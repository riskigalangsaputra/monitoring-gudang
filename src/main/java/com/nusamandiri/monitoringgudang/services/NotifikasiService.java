package com.nusamandiri.monitoringgudang.services;

import com.nusamandiri.monitoringgudang.dao.NotifikasiDao;
import com.nusamandiri.monitoringgudang.dto.NotifikasiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author galang
 */
@Service
public class NotifikasiService {
    @Autowired
    private NotifikasiDao notifikasiDao;

//    public List<NotifikasiDto> getAll(String username) {
//        return notifikasiDao.findAllOrderByDesc(username);
//    }
}
