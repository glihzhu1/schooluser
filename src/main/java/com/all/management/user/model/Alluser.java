package com.all.management.user.model;
import org.springframework.roo.addon.javabean.annotations.RooEquals;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooSerializable;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;

import com.all.management.user.util.AlluserTransformer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import io.springlets.format.EntityFormat;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;


@Entity
@EntityFormat
/**
 * = Alluser
 TODO Auto-generated class documentation
 *
 */
//@RooJavaBean
//@RooToString
//@RooJpaEntity
//@RooEquals(isJpaEntity = true)
//@RooSerializable
public class Alluser implements Serializable {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Version
    private Integer version;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @NotNull
    @Column(name = "login_id")
    @Size(max = 200)
    private String loginId;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @NotNull
    @Column(name = "password_hash")
    @Size(max = 200)
    private String passwordHash;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @NotNull
    @Column(name = "email")
    @Size(max = 200)
    private String email;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @NotNull
    @Column(name = "app_role")
    @Size(max = 50)
    private String appRole;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Column(name = "user_active")
    private Boolean userActive;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Column(name = "last_update_date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private Calendar lastUpdateDate;

    
	public Alluser() {
		super();
	}

	public Alluser(Alluser user) {
		this.id = user.id;
		this.loginId = user.loginId;
		this.passwordHash = user.passwordHash;
		this.email = user.email;
		this.appRole = user.appRole;
		this.userActive = user.userActive;
	}
    
    

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    private static final long serialVersionUID = 1L;

	/**
     * This `equals` implementation is specific for JPA entities and uses 
     * the entity identifier for it, following the article in 
     * https://vladmihalcea.com/2016/06/06/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
     * 
     * @param obj
     * @return Boolean
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        // instanceof is false if the instance is null
        if (!(obj instanceof Alluser)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((Alluser) obj).getId());
    }

	/**
     * This `hashCode` implementation is specific for JPA entities and uses a fixed `int` value to be able 
     * to identify the entity in collections after a new id is assigned to the entity, following the article in 
     * https://vladmihalcea.com/2016/06/06/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
     * 
     * @return Integer
     */
    public int hashCode() {
        return 31;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return String
     */
    public String toString() {
        return "Alluser {" + 
                "id='" + id + '\'' + 
                ", version='" + version + '\'' + 
                ", loginId='" + loginId + '\'' + 
                ", passwordHash='" + passwordHash + '\'' + 
                ", email='" + email + '\'' + 
                ", appRole='" + appRole + '\'' + 
                ", userActive='" + userActive + '\'' + 
                ", lastUpdateDate='" + lastUpdateDate + '\'' + 
                ", serialVersionUID='" + serialVersionUID + '\'' + 
                ", ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE='" + ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE + '\'' + 
                ", ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE='" + ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE + '\'' + "}" + super.toString();
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return Long
     */
    public Long getId() {
        return this.id;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return Integer
     */
    public Integer getVersion() {
        return this.version;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param version
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return String
     */
    public String getLoginId() {
        return this.loginId;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param loginId
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return String
     */
    public String getPasswordHash() {
        return this.passwordHash;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param passwordHash
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return String
     */
    public String getEmail() {
        return this.email;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return String
     */
    public String getAppRole() {
        return this.appRole;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param appRole
     */
    public void setAppRole(String appRole) {
        this.appRole = appRole;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return Boolean
     */
    public Boolean getUserActive() {
        return this.userActive;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param userActive
     */
    public void setUserActive(Boolean userActive) {
        this.userActive = userActive;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return Calendar
     */
    public Calendar getLastUpdateDate() {
        return this.lastUpdateDate;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param lastUpdateDate
     */
    public void setLastUpdateDate(Calendar lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    public static final String ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE = "The given Iterable of items to add can't be null!";

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    public static final String ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE = "The given Iterable of items to add can't be null!";

    public static Alluser fromJsonToAlluser(String json) {
        return new JSONDeserializer<Alluser>()
        		.use(null, Alluser.class).deserialize(json);
    }
    
    public String toJson() {
        return new JSONSerializer().exclude("*.class")
        .transform(new AlluserTransformer(), Alluser.class).serialize(this);
    }
}
