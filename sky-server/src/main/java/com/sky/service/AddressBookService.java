package com.sky.service;

import com.sky.entity.AddressBook;

import java.util.List;

/**
 * @Author: Dai
 * @Date: 2024/08/12 10:20
 * @Description: AddressBookService
 * @Version: 1.0
 */
public interface AddressBookService {

    /**
     * 查询当前用户的所有地址
     * @param addressBook
     * @return
     */
    List<AddressBook> list(AddressBook addressBook);

    /**
     * 新增地址
     * @param addressBook
     */
    void save(AddressBook addressBook);

    /**
     * 根据id查询地址
     * @param id
     * @return
     */
    AddressBook getById(Long id);

    /**
     * 修改地址
     * @param addressBook
     */
    void update(AddressBook addressBook);

    /**
     * 设置默认地址
     * @param addressBook
     */
    void setDefault(AddressBook addressBook);

    /**
     * 根据id删除地址
     * @param id
     */
    void deleteById(Long id);
}
