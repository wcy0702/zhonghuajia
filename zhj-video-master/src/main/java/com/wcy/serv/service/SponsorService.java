package com.wcy.serv.service;

import org.springframework.stereotype.Service;

import com.wcy.serv.model.Sponsor;

import java.util.List;

/**
 * Sponsor Service
 * Created by Silence on 2017/4/19.
 */
@Service
public interface SponsorService {

  public List<Sponsor> list();

}
