package com.apollo.config.datasource;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.sql.Timestamp;

/**
 * @author ：apollo
 * @since ：Created in 2019/2/23
 *
 * mybatis-plus自定义填充公共字段
 */
public class MyMetaObjectHandler extends MetaObjectHandler {

    /**
     * 在@TableField中fill = FieldFill.INSERT
     */
    public void insertFill(MetaObject metaObject) {
        Object createTime = getFieldValByName("createTime", metaObject);//mybatis-plus版本2.0.9+

        if (createTime == null) {
            setFieldValByName("createTime", new Timestamp(System.currentTimeMillis()), metaObject);//mybatis-plus版本2.0.9+
        }

    }

    /**
     * 在@TableField中fill = FieldFill.UPDATE
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        //mybatis-plus版本2.0.9+
        setFieldValByName("updateTime", new Timestamp(System.currentTimeMillis()), metaObject);
    }

}
