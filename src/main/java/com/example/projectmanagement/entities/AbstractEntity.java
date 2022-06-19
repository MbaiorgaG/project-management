package com.example.projectmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@MappedSuperclass
@Setter
@Getter
public class AbstractEntity implements Serializable {


    private static final long serialVersionUID = -2361696892354119780L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Version
    protected int version;

    protected String delFlag = "N";

    protected Date deletedOn;


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractEntity other = (AbstractEntity) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }

    @JsonIgnore
    public List<String> getDefaultSearchFields(){
        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                        ", version=" + version +
                        ", delFlag='" + delFlag + '\'' +
                        ", deletedOn=" + deletedOn +
                        '}';
    }
}
