package com.imooc.ad.service.impl;

import com.imooc.ad.constant.Constants;
import com.imooc.ad.dao.AdUserRepository;
import com.imooc.ad.entity.AdUser;
import com.imooc.ad.exception.AdException;
import com.imooc.ad.service.IUserService;
import com.imooc.ad.utils.CommonUtils;
import com.imooc.ad.vo.CommonResponse;
import com.imooc.ad.vo.CreateUserRequest;
import com.imooc.ad.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/5/4 10:18
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    private final AdUserRepository userRepository;

    public UserServiceImpl(AdUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) throws AdException {

        if (!request.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdUser oldUser = userRepository.findByUsername(request.getUsername());

        if (oldUser != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }

        AdUser newUser = userRepository.save(new AdUser(request.getUsername(), CommonUtils.md5(request.getUsername())));

        return new CreateUserResponse(
                newUser.getId(), newUser.getUsername(),
                newUser.getToken(), newUser.getCreateTime(),
                newUser.getUpdateTime());
    }
}
