package com.nearnstyle.apis.role.repository.impl;

import com.nearnstyle.apis.common.repository.GenericRepositoryImpl;
import com.nearnstyle.apis.role.model.Role;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import java.util.List;

public class RoleRepositoryImpl extends GenericRepositoryImpl implements RoleRepositoryCustom {

    @Override
    public List<Role> findAll(boolean isActive) {
        String queryString = "select * from um_role_master;";
        Query query = getSession().createSQLQuery(queryString);
        return query.setResultTransformer(Transformers.aliasToBean(Role.class)).list();
    }
}
