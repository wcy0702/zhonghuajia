package com.wcy.serv.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wcy.serv.mapper.SponsorMapper;
import com.wcy.serv.model.Sponsor;
import com.wcy.serv.service.SponsorService;

import java.util.List;

import javax.annotation.Resource;

/**
 * Sponsor Service
 * Created by Silence on 2017/4/19.
 */
@Service
@AllArgsConstructor
public class SponsorServiceImpl implements SponsorService{

	 @Resource
  private  SponsorMapper mapper;

  @Override
  public List<Sponsor> list() {
    return mapper.list();
  }

}
