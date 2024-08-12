package com.sky.mapper;

import com.sky.entity.AddressBook;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: Dai
 * @Date: 2024/08/12 10:25
 * @Description: AddressBookMapper
 * @Version: 1.0
 */
@Mapper
public interface AddressBookMapper {


    /**
     * 条件查询
     * @param addressBook
     * @return
     */
    List<AddressBook> list(AddressBook addressBook);


    /**
     * 新增地址
     * @param addressBook
     */
    @Insert("insert into address_book (user_id, consignee, phone, sex, province_code, province_name, city_code, city_name, district_code, district_name, detail, label, is_default) "
    + "values (#{userId},#{consignee},#{phone},#{sex},#{provinceCode},#{provinceName},#{cityCode},#{cityName},#{districtCode},#{districtName},#{detail},#{label},#{isDefault})")
    void insert(AddressBook addressBook);

    /**
     * 根据id查询地址
     * @param id
     * @return
     */
    @Select("select * from address_book where id = #{id}")
    AddressBook getById(Long id);

    /**
     * 修改地址
     * @param addressBook
     */
    void update(AddressBook addressBook);

    /**
     * 根据 用户id修改 是否默认地址
     * @param addressBook
     */
    @Update("update address_book set is_default = #{isDefault} where user_id = #{userId}")
    void updateIsDefaultByUserId(AddressBook addressBook);

    /**
     * 根据Id删除地址
     * @param id
     */
    @Delete("delete from address_book where id = #{id}")
    void deleteById(Long id);
}
