package com.springframework.domain.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author summer
 */
@Setter
@Getter
public abstract class BaseVo implements Serializable {
    private static final long serialVersionUID = -5171757666178554032L;
    public Long id;
    public String createdBy;
    public String lastModifiedBy;
    public Date createdTime;
    public Date lastModifiedTime;
    @Override
    public String toString() {
        return "BaseDTO.BaseDTOBuilder(id=" + this.id + ",createdBy=" + this.createdBy + ", lastModifiedBy=" + this.lastModifiedBy + ", createdTime=" + this.createdTime + ", lastModifiedTime=" + this.lastModifiedTime + ")";
    }
}