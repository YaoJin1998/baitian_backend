package com.xmut.eduservice.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data //填充set，get方法
@Builder  //跟下面一个相当于提供一个链式的创建对象 设置参数
@Accessors(chain = true)
@TableName("bms_billboard") //表名
@NoArgsConstructor  //无参的构造
@AllArgsConstructor //有参的构造
public class BmsBillboard implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)  //自增长
    private Integer id;

    /**
     * 公告牌
     */
    @TableField("content")
    private String content;

    /**
     * 公告时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT) //自动填充
    private Date createTime;

    /**
     * 1：展示中，0：过期
     */

    @Builder.Default  //默认值
    @TableField("`show`")  //填入字段
    private boolean show = false;

}
