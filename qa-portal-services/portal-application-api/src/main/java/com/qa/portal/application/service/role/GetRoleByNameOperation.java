package com.qa.portal.application.service.role;

import com.qa.portal.application.dto.RoleDto;
import com.qa.portal.application.persistence.entity.RoleEntity;
import com.qa.portal.application.persistence.repository.RoleRepository;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.service.mapper.BaseMapper;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.stereotype.Component;

@Component
public class GetRoleByNameOperation {

    private RoleRepository roleRepository;

    private BaseMapper baseMapper;

    public GetRoleByNameOperation(RoleRepository roleRepository,
                                  BaseMapper baseMapper) {
        this.roleRepository = roleRepository;
        this.baseMapper = baseMapper;
    }

    public <Optional>RoleDto getRoleByName(String roleName) {
        RoleDto rDto = new RoleDto();
        if (roleRepository.findByName(roleName).isPresent()){
            return roleRepository.findByName(roleName)
                    .map(r -> baseMapper.mapObject(r, RoleDto.class))
                    .get();
        } else {
            System.err.println("Could not find database record for keycloak role: " + roleName + ". Ignoring.");
            return null;
        }
    }
 }
