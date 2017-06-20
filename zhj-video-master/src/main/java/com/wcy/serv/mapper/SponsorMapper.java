package com.wcy.serv.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.wcy.serv.model.Sponsor;

import java.util.List;

/**
 * Sponsor Mapper
 * Created by Silence on 2017/4/19.
 */
@Mapper
public interface SponsorMapper {

  @Select("SELECT * FROM sponsor ORDER BY id DESC")
  List<Sponsor> list();

}
