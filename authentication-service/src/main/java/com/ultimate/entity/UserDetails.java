/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ultimate.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ULTCPU21
 */
@Entity
@Table(name = "user_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserDetails.findAll", query = "SELECT u FROM UserDetails u"),
    @NamedQuery(name = "UserDetails.findByUserNo", query = "SELECT u FROM UserDetails u WHERE u.userNo = :userNo"),
    @NamedQuery(name = "UserDetails.findByUserCode", query = "SELECT u FROM UserDetails u WHERE u.userCode = :userCode"),
    @NamedQuery(name = "UserDetails.findByUserFirstName", query = "SELECT u FROM UserDetails u WHERE u.userFirstName = :userFirstName"),
    @NamedQuery(name = "UserDetails.findByUserLastName", query = "SELECT u FROM UserDetails u WHERE u.userLastName = :userLastName"),
    @NamedQuery(name = "UserDetails.findByPassword", query = "SELECT u FROM UserDetails u WHERE u.password = :password"),
    @NamedQuery(name = "UserDetails.findByGroupNo", query = "SELECT u FROM UserDetails u WHERE u.groupNo = :groupNo"),
    @NamedQuery(name = "UserDetails.findByUserPhone", query = "SELECT u FROM UserDetails u WHERE u.userPhone = :userPhone"),
    @NamedQuery(name = "UserDetails.findByUserEmail", query = "SELECT u FROM UserDetails u WHERE u.userEmail = :userEmail"),
    @NamedQuery(name = "UserDetails.findByStartDateTime", query = "SELECT u FROM UserDetails u WHERE u.startDateTime = :startDateTime"),
    @NamedQuery(name = "UserDetails.findByEndDateTime", query = "SELECT u FROM UserDetails u WHERE u.endDateTime = :endDateTime"),
    @NamedQuery(name = "UserDetails.findByPasswordLoginAttempt", query = "SELECT u FROM UserDetails u WHERE u.passwordLoginAttempt = :passwordLoginAttempt"),
    @NamedQuery(name = "UserDetails.findByInactiveFlg", query = "SELECT u FROM UserDetails u WHERE u.inactiveFlg = :inactiveFlg"),
    @NamedQuery(name = "UserDetails.findByCrtUsrNo", query = "SELECT u FROM UserDetails u WHERE u.crtUsrNo = :crtUsrNo"),
    @NamedQuery(name = "UserDetails.findByCrtDate", query = "SELECT u FROM UserDetails u WHERE u.crtDate = :crtDate"),
    @NamedQuery(name = "UserDetails.findByCrtTrmnlNm", query = "SELECT u FROM UserDetails u WHERE u.crtTrmnlNm = :crtTrmnlNm"),
    @NamedQuery(name = "UserDetails.findByUpdUsrNo", query = "SELECT u FROM UserDetails u WHERE u.updUsrNo = :updUsrNo"),
    @NamedQuery(name = "UserDetails.findByUpdDate", query = "SELECT u FROM UserDetails u WHERE u.updDate = :updDate"),
    @NamedQuery(name = "UserDetails.findByUpdTrmnlNm", query = "SELECT u FROM UserDetails u WHERE u.updTrmnlNm = :updTrmnlNm"),
    @NamedQuery(name = "UserDetails.findByUpdCnt", query = "SELECT u FROM UserDetails u WHERE u.updCnt = :updCnt")})
public class UserDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "user_no")
    private Long userNo;
    @Basic(optional = false)
    @Column(name = "user_code")
    private String userCode;
    @Basic(optional = false)
    @Column(name = "user_first_name")
    private String userFirstName;
    @Basic(optional = false)
    @Column(name = "user_last_name")
    private String userLastName;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Column(name = "group_no")
    private Integer groupNo;
    @Column(name = "user_phone")
    private String userPhone;
    @Column(name = "user_email")
    private String userEmail;
    @Basic(optional = false)
    @Column(name = "start_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateTime;
    @Basic(optional = false)
    @Column(name = "end_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateTime;
    @Column(name = "password_login_attempt")
    private Short passwordLoginAttempt;
    @Column(name = "inactive_flg")
    private Short inactiveFlg;
    @Basic(optional = false)
    @Column(name = "crt_usr_no")
    private long crtUsrNo;
    @Basic(optional = false)
    @Column(name = "crt_date")
    @Temporal(TemporalType.DATE)
    private Date crtDate;
    @Column(name = "crt_trmnl_nm")
    private String crtTrmnlNm;
    @Column(name = "upd_usr_no")
    private BigInteger updUsrNo;
    @Column(name = "upd_date")
    @Temporal(TemporalType.DATE)
    private Date updDate;
    @Column(name = "upd_trmnl_nm")
    private String updTrmnlNm;
    @Basic(optional = false)
    @Column(name = "upd_cnt")
    private int updCnt;

    public UserDetails() {
    }

    public UserDetails(Long userNo) {
        this.userNo = userNo;
    }

    public UserDetails(Long userNo, String userCode, String userFirstName, String userLastName, String password, Date startDateTime, Date endDateTime, long crtUsrNo, Date crtDate, int updCnt) {
        this.userNo = userNo;
        this.userCode = userCode;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.password = password;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.crtUsrNo = crtUsrNo;
        this.crtDate = crtDate;
        this.updCnt = updCnt;
    }

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(Integer groupNo) {
        this.groupNo = groupNo;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Short getPasswordLoginAttempt() {
        return passwordLoginAttempt;
    }

    public void setPasswordLoginAttempt(Short passwordLoginAttempt) {
        this.passwordLoginAttempt = passwordLoginAttempt;
    }

    public Short getInactiveFlg() {
        return inactiveFlg;
    }

    public void setInactiveFlg(Short inactiveFlg) {
        this.inactiveFlg = inactiveFlg;
    }

    public long getCrtUsrNo() {
        return crtUsrNo;
    }

    public void setCrtUsrNo(long crtUsrNo) {
        this.crtUsrNo = crtUsrNo;
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public String getCrtTrmnlNm() {
        return crtTrmnlNm;
    }

    public void setCrtTrmnlNm(String crtTrmnlNm) {
        this.crtTrmnlNm = crtTrmnlNm;
    }

    public BigInteger getUpdUsrNo() {
        return updUsrNo;
    }

    public void setUpdUsrNo(BigInteger updUsrNo) {
        this.updUsrNo = updUsrNo;
    }

    public Date getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }

    public String getUpdTrmnlNm() {
        return updTrmnlNm;
    }

    public void setUpdTrmnlNm(String updTrmnlNm) {
        this.updTrmnlNm = updTrmnlNm;
    }

    public int getUpdCnt() {
        return updCnt;
    }

    public void setUpdCnt(int updCnt) {
        this.updCnt = updCnt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userNo != null ? userNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserDetails)) {
            return false;
        }
        UserDetails other = (UserDetails) object;
        if ((this.userNo == null && other.userNo != null) || (this.userNo != null && !this.userNo.equals(other.userNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ultimate.entity.UserDetails[ userNo=" + userNo + " ]";
    }
    
}
