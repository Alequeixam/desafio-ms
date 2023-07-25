package br.com.challenge.msproduct.service;

import br.com.challenge.msproduct.exception.ResourceNotFoundException;
import br.com.challenge.msproduct.repository.RoleRepository;
import br.com.challenge.msproduct.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private RoleRepository roleRepository;
    private ModelMapper mapper;

    public RoleService(RoleRepository roleRepository, ModelMapper mapper) {
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    public Role createRole(Role role) {

        List<Role> allRoles = roleRepository.findAll();
        for (Role r: allRoles) {
            if (r.getName().equals(role.getName())) {
                throw new RuntimeException("Role already registered");
            }
        }
        return roleRepository.save(role);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(Long roleId) {
        return roleRepository.findById(roleId).orElseThrow(() ->
                new ResourceNotFoundException("Role", "id", roleId));
    }

    public Role updateRole(Long roleId, Role role) {
        Role updatedRole = roleRepository.findById(roleId).orElseThrow(() ->
                new ResourceNotFoundException("Role", "id", roleId));
        updatedRole.setId(roleId);
        updatedRole.setName(role.getName());
        return updatedRole;
    }

    public void deleteRole(Long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() ->
                new ResourceNotFoundException("Role", "id", roleId));
        roleRepository.delete(role);
    }
}
