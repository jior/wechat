package com.glaf.wechat.query;

import java.util.*;
import com.glaf.core.query.DataQuery;

public class WxFollowerQuery extends DataQuery {
        private static final long serialVersionUID = 1L;
	protected List<Long> ids;
  	protected Long accountId;
  	protected Long accountIdGreaterThanOrEqual;
  	protected Long accountIdLessThanOrEqual;
  	protected List<Long> accountIds;
  	protected String actorId;
  	protected String actorIdLike;
  	protected List<String> actorIds;
  	protected String openId;
  	protected String openIdLike;
  	protected List<String> openIds;
  	protected String nickName;
  	protected String nickNameLike;
  	protected List<String> nickNames;
  	protected String sex;
  	protected String sexLike;
  	protected List<String> sexs;
  	protected String mobile;
  	protected String mobileLike;
  	protected List<String> mobiles;
  	protected String mail;
  	protected String mailLike;
  	protected List<String> mails;
  	protected String telephone;
  	protected String telephoneLike;
  	protected List<String> telephones;
  	protected String headimgurl;
  	protected String headimgurlLike;
  	protected List<String> headimgurls;
  	protected String province;
  	protected String provinceLike;
  	protected List<String> provinces;
  	protected String city;
  	protected String cityLike;
  	protected List<String> citys;
  	protected String country;
  	protected String countryLike;
  	protected List<String> countrys;
  	protected String language;
  	protected String languageLike;
  	protected List<String> languages;
  	protected Integer locked;
  	protected Integer lockedGreaterThanOrEqual;
  	protected Integer lockedLessThanOrEqual;
  	protected List<Integer> lockeds;
  	protected String remark;
  	protected String remarkLike;
  	protected List<String> remarks;
  	protected Long subscribeTime;
  	protected Long subscribeTimeGreaterThanOrEqual;
  	protected Long subscribeTimeLessThanOrEqual;
  	protected List<Long> subscribeTimes;
  	protected Date createDate;
  	protected Date createDateGreaterThanOrEqual;
  	protected Date createDateLessThanOrEqual;
  	protected List<Date> createDates;

    public WxFollowerQuery() {

    }


    public Long getAccountId(){
        return accountId;
    }

    public Long getAccountIdGreaterThanOrEqual(){
        return accountIdGreaterThanOrEqual;
    }

    public Long getAccountIdLessThanOrEqual(){
	return accountIdLessThanOrEqual;
    }

    public List<Long> getAccountIds(){
	return accountIds;
    }


    public String getActorId(){
        return actorId;
    }

    public String getActorIdLike(){
	    if (actorIdLike != null && actorIdLike.trim().length() > 0) {
		if (!actorIdLike.startsWith("%")) {
		    actorIdLike = "%" + actorIdLike;
		}
		if (!actorIdLike.endsWith("%")) {
		   actorIdLike = actorIdLike + "%";
		}
	    }
	return actorIdLike;
    }

    public List<String> getActorIds(){
	return actorIds;
    }


    public String getOpenId(){
        return openId;
    }

    public String getOpenIdLike(){
	    if (openIdLike != null && openIdLike.trim().length() > 0) {
		if (!openIdLike.startsWith("%")) {
		    openIdLike = "%" + openIdLike;
		}
		if (!openIdLike.endsWith("%")) {
		   openIdLike = openIdLike + "%";
		}
	    }
	return openIdLike;
    }

    public List<String> getOpenIds(){
	return openIds;
    }


    public String getNickName(){
        return nickName;
    }

    public String getNickNameLike(){
	    if (nickNameLike != null && nickNameLike.trim().length() > 0) {
		if (!nickNameLike.startsWith("%")) {
		    nickNameLike = "%" + nickNameLike;
		}
		if (!nickNameLike.endsWith("%")) {
		   nickNameLike = nickNameLike + "%";
		}
	    }
	return nickNameLike;
    }

    public List<String> getNickNames(){
	return nickNames;
    }


    public String getSex(){
        return sex;
    }

    public String getSexLike(){
	    if (sexLike != null && sexLike.trim().length() > 0) {
		if (!sexLike.startsWith("%")) {
		    sexLike = "%" + sexLike;
		}
		if (!sexLike.endsWith("%")) {
		   sexLike = sexLike + "%";
		}
	    }
	return sexLike;
    }

    public List<String> getSexs(){
	return sexs;
    }


    public String getMobile(){
        return mobile;
    }

    public String getMobileLike(){
	    if (mobileLike != null && mobileLike.trim().length() > 0) {
		if (!mobileLike.startsWith("%")) {
		    mobileLike = "%" + mobileLike;
		}
		if (!mobileLike.endsWith("%")) {
		   mobileLike = mobileLike + "%";
		}
	    }
	return mobileLike;
    }

    public List<String> getMobiles(){
	return mobiles;
    }


    public String getMail(){
        return mail;
    }

    public String getMailLike(){
	    if (mailLike != null && mailLike.trim().length() > 0) {
		if (!mailLike.startsWith("%")) {
		    mailLike = "%" + mailLike;
		}
		if (!mailLike.endsWith("%")) {
		   mailLike = mailLike + "%";
		}
	    }
	return mailLike;
    }

    public List<String> getMails(){
	return mails;
    }


    public String getTelephone(){
        return telephone;
    }

    public String getTelephoneLike(){
	    if (telephoneLike != null && telephoneLike.trim().length() > 0) {
		if (!telephoneLike.startsWith("%")) {
		    telephoneLike = "%" + telephoneLike;
		}
		if (!telephoneLike.endsWith("%")) {
		   telephoneLike = telephoneLike + "%";
		}
	    }
	return telephoneLike;
    }

    public List<String> getTelephones(){
	return telephones;
    }


    public String getHeadimgurl(){
        return headimgurl;
    }

    public String getHeadimgurlLike(){
	    if (headimgurlLike != null && headimgurlLike.trim().length() > 0) {
		if (!headimgurlLike.startsWith("%")) {
		    headimgurlLike = "%" + headimgurlLike;
		}
		if (!headimgurlLike.endsWith("%")) {
		   headimgurlLike = headimgurlLike + "%";
		}
	    }
	return headimgurlLike;
    }

    public List<String> getHeadimgurls(){
	return headimgurls;
    }


    public String getProvince(){
        return province;
    }

    public String getProvinceLike(){
	    if (provinceLike != null && provinceLike.trim().length() > 0) {
		if (!provinceLike.startsWith("%")) {
		    provinceLike = "%" + provinceLike;
		}
		if (!provinceLike.endsWith("%")) {
		   provinceLike = provinceLike + "%";
		}
	    }
	return provinceLike;
    }

    public List<String> getProvinces(){
	return provinces;
    }


    public String getCity(){
        return city;
    }

    public String getCityLike(){
	    if (cityLike != null && cityLike.trim().length() > 0) {
		if (!cityLike.startsWith("%")) {
		    cityLike = "%" + cityLike;
		}
		if (!cityLike.endsWith("%")) {
		   cityLike = cityLike + "%";
		}
	    }
	return cityLike;
    }

    public List<String> getCitys(){
	return citys;
    }


    public String getCountry(){
        return country;
    }

    public String getCountryLike(){
	    if (countryLike != null && countryLike.trim().length() > 0) {
		if (!countryLike.startsWith("%")) {
		    countryLike = "%" + countryLike;
		}
		if (!countryLike.endsWith("%")) {
		   countryLike = countryLike + "%";
		}
	    }
	return countryLike;
    }

    public List<String> getCountrys(){
	return countrys;
    }


    public String getLanguage(){
        return language;
    }

    public String getLanguageLike(){
	    if (languageLike != null && languageLike.trim().length() > 0) {
		if (!languageLike.startsWith("%")) {
		    languageLike = "%" + languageLike;
		}
		if (!languageLike.endsWith("%")) {
		   languageLike = languageLike + "%";
		}
	    }
	return languageLike;
    }

    public List<String> getLanguages(){
	return languages;
    }


    public Integer getLocked(){
        return locked;
    }

    public Integer getLockedGreaterThanOrEqual(){
        return lockedGreaterThanOrEqual;
    }

    public Integer getLockedLessThanOrEqual(){
	return lockedLessThanOrEqual;
    }

    public List<Integer> getLockeds(){
	return lockeds;
    }


    public String getRemark(){
        return remark;
    }

    public String getRemarkLike(){
	    if (remarkLike != null && remarkLike.trim().length() > 0) {
		if (!remarkLike.startsWith("%")) {
		    remarkLike = "%" + remarkLike;
		}
		if (!remarkLike.endsWith("%")) {
		   remarkLike = remarkLike + "%";
		}
	    }
	return remarkLike;
    }

    public List<String> getRemarks(){
	return remarks;
    }


    public Long getSubscribeTime(){
        return subscribeTime;
    }

    public Long getSubscribeTimeGreaterThanOrEqual(){
        return subscribeTimeGreaterThanOrEqual;
    }

    public Long getSubscribeTimeLessThanOrEqual(){
	return subscribeTimeLessThanOrEqual;
    }

    public List<Long> getSubscribeTimes(){
	return subscribeTimes;
    }


    public Date getCreateDate(){
        return createDate;
    }

    public Date getCreateDateGreaterThanOrEqual(){
        return createDateGreaterThanOrEqual;
    }

    public Date getCreateDateLessThanOrEqual(){
	return createDateLessThanOrEqual;
    }

    public List<Date> getCreateDates(){
	return createDates;
    }


 

    public void setAccountId(Long accountId){
        this.accountId = accountId;
    }

    public void setAccountIdGreaterThanOrEqual(Long accountIdGreaterThanOrEqual){
        this.accountIdGreaterThanOrEqual = accountIdGreaterThanOrEqual;
    }

    public void setAccountIdLessThanOrEqual(Long accountIdLessThanOrEqual){
	this.accountIdLessThanOrEqual = accountIdLessThanOrEqual;
    }

    public void setAccountIds(List<Long> accountIds){
        this.accountIds = accountIds;
    }


    public void setActorId(String actorId){
        this.actorId = actorId;
    }

    public void setActorIdLike( String actorIdLike){
	this.actorIdLike = actorIdLike;
    }

    public void setActorIds(List<String> actorIds){
        this.actorIds = actorIds;
    }


    public void setOpenId(String openId){
        this.openId = openId;
    }

    public void setOpenIdLike( String openIdLike){
	this.openIdLike = openIdLike;
    }

    public void setOpenIds(List<String> openIds){
        this.openIds = openIds;
    }


    public void setNickName(String nickName){
        this.nickName = nickName;
    }

    public void setNickNameLike( String nickNameLike){
	this.nickNameLike = nickNameLike;
    }

    public void setNickNames(List<String> nickNames){
        this.nickNames = nickNames;
    }


    public void setSex(String sex){
        this.sex = sex;
    }

    public void setSexLike( String sexLike){
	this.sexLike = sexLike;
    }

    public void setSexs(List<String> sexs){
        this.sexs = sexs;
    }


    public void setMobile(String mobile){
        this.mobile = mobile;
    }

    public void setMobileLike( String mobileLike){
	this.mobileLike = mobileLike;
    }

    public void setMobiles(List<String> mobiles){
        this.mobiles = mobiles;
    }


    public void setMail(String mail){
        this.mail = mail;
    }

    public void setMailLike( String mailLike){
	this.mailLike = mailLike;
    }

    public void setMails(List<String> mails){
        this.mails = mails;
    }


    public void setTelephone(String telephone){
        this.telephone = telephone;
    }

    public void setTelephoneLike( String telephoneLike){
	this.telephoneLike = telephoneLike;
    }

    public void setTelephones(List<String> telephones){
        this.telephones = telephones;
    }


    public void setHeadimgurl(String headimgurl){
        this.headimgurl = headimgurl;
    }

    public void setHeadimgurlLike( String headimgurlLike){
	this.headimgurlLike = headimgurlLike;
    }

    public void setHeadimgurls(List<String> headimgurls){
        this.headimgurls = headimgurls;
    }


    public void setProvince(String province){
        this.province = province;
    }

    public void setProvinceLike( String provinceLike){
	this.provinceLike = provinceLike;
    }

    public void setProvinces(List<String> provinces){
        this.provinces = provinces;
    }


    public void setCity(String city){
        this.city = city;
    }

    public void setCityLike( String cityLike){
	this.cityLike = cityLike;
    }

    public void setCitys(List<String> citys){
        this.citys = citys;
    }


    public void setCountry(String country){
        this.country = country;
    }

    public void setCountryLike( String countryLike){
	this.countryLike = countryLike;
    }

    public void setCountrys(List<String> countrys){
        this.countrys = countrys;
    }


    public void setLanguage(String language){
        this.language = language;
    }

    public void setLanguageLike( String languageLike){
	this.languageLike = languageLike;
    }

    public void setLanguages(List<String> languages){
        this.languages = languages;
    }


    public void setLocked(Integer locked){
        this.locked = locked;
    }

    public void setLockedGreaterThanOrEqual(Integer lockedGreaterThanOrEqual){
        this.lockedGreaterThanOrEqual = lockedGreaterThanOrEqual;
    }

    public void setLockedLessThanOrEqual(Integer lockedLessThanOrEqual){
	this.lockedLessThanOrEqual = lockedLessThanOrEqual;
    }

    public void setLockeds(List<Integer> lockeds){
        this.lockeds = lockeds;
    }


    public void setRemark(String remark){
        this.remark = remark;
    }

    public void setRemarkLike( String remarkLike){
	this.remarkLike = remarkLike;
    }

    public void setRemarks(List<String> remarks){
        this.remarks = remarks;
    }


    public void setSubscribeTime(Long subscribeTime){
        this.subscribeTime = subscribeTime;
    }

    public void setSubscribeTimeGreaterThanOrEqual(Long subscribeTimeGreaterThanOrEqual){
        this.subscribeTimeGreaterThanOrEqual = subscribeTimeGreaterThanOrEqual;
    }

    public void setSubscribeTimeLessThanOrEqual(Long subscribeTimeLessThanOrEqual){
	this.subscribeTimeLessThanOrEqual = subscribeTimeLessThanOrEqual;
    }

    public void setSubscribeTimes(List<Long> subscribeTimes){
        this.subscribeTimes = subscribeTimes;
    }


    public void setCreateDate(Date createDate){
        this.createDate = createDate;
    }

    public void setCreateDateGreaterThanOrEqual(Date createDateGreaterThanOrEqual){
        this.createDateGreaterThanOrEqual = createDateGreaterThanOrEqual;
    }

    public void setCreateDateLessThanOrEqual(Date createDateLessThanOrEqual){
	this.createDateLessThanOrEqual = createDateLessThanOrEqual;
    }

    public void setCreateDates(List<Date> createDates){
        this.createDates = createDates;
    }




    public WxFollowerQuery accountId(Long accountId){
	if (accountId == null) {
            throw new RuntimeException("accountId is null");
        }         
	this.accountId = accountId;
	return this;
    }

    public WxFollowerQuery accountIdGreaterThanOrEqual(Long accountIdGreaterThanOrEqual){
	if (accountIdGreaterThanOrEqual == null) {
	    throw new RuntimeException("accountId is null");
        }         
	this.accountIdGreaterThanOrEqual = accountIdGreaterThanOrEqual;
        return this;
    }

    public WxFollowerQuery accountIdLessThanOrEqual(Long accountIdLessThanOrEqual){
        if (accountIdLessThanOrEqual == null) {
            throw new RuntimeException("accountId is null");
        }
        this.accountIdLessThanOrEqual = accountIdLessThanOrEqual;
        return this;
    }

    public WxFollowerQuery accountIds(List<Long> accountIds){
        if (accountIds == null) {
            throw new RuntimeException("accountIds is empty ");
        }
        this.accountIds = accountIds;
        return this;
    }


    public WxFollowerQuery actorId(String actorId){
	if (actorId == null) {
	    throw new RuntimeException("actorId is null");
        }         
	this.actorId = actorId;
	return this;
    }

    public WxFollowerQuery actorIdLike( String actorIdLike){
        if (actorIdLike == null) {
            throw new RuntimeException("actorId is null");
        }
        this.actorIdLike = actorIdLike;
        return this;
    }

    public WxFollowerQuery actorIds(List<String> actorIds){
        if (actorIds == null) {
            throw new RuntimeException("actorIds is empty ");
        }
        this.actorIds = actorIds;
        return this;
    }


    public WxFollowerQuery openId(String openId){
	if (openId == null) {
	    throw new RuntimeException("openId is null");
        }         
	this.openId = openId;
	return this;
    }

    public WxFollowerQuery openIdLike( String openIdLike){
        if (openIdLike == null) {
            throw new RuntimeException("openId is null");
        }
        this.openIdLike = openIdLike;
        return this;
    }

    public WxFollowerQuery openIds(List<String> openIds){
        if (openIds == null) {
            throw new RuntimeException("openIds is empty ");
        }
        this.openIds = openIds;
        return this;
    }


    public WxFollowerQuery nickName(String nickName){
	if (nickName == null) {
	    throw new RuntimeException("nickName is null");
        }         
	this.nickName = nickName;
	return this;
    }

    public WxFollowerQuery nickNameLike( String nickNameLike){
        if (nickNameLike == null) {
            throw new RuntimeException("nickName is null");
        }
        this.nickNameLike = nickNameLike;
        return this;
    }

    public WxFollowerQuery nickNames(List<String> nickNames){
        if (nickNames == null) {
            throw new RuntimeException("nickNames is empty ");
        }
        this.nickNames = nickNames;
        return this;
    }


    public WxFollowerQuery sex(String sex){
	if (sex == null) {
	    throw new RuntimeException("sex is null");
        }         
	this.sex = sex;
	return this;
    }

    public WxFollowerQuery sexLike( String sexLike){
        if (sexLike == null) {
            throw new RuntimeException("sex is null");
        }
        this.sexLike = sexLike;
        return this;
    }

    public WxFollowerQuery sexs(List<String> sexs){
        if (sexs == null) {
            throw new RuntimeException("sexs is empty ");
        }
        this.sexs = sexs;
        return this;
    }


    public WxFollowerQuery mobile(String mobile){
	if (mobile == null) {
	    throw new RuntimeException("mobile is null");
        }         
	this.mobile = mobile;
	return this;
    }

    public WxFollowerQuery mobileLike( String mobileLike){
        if (mobileLike == null) {
            throw new RuntimeException("mobile is null");
        }
        this.mobileLike = mobileLike;
        return this;
    }

    public WxFollowerQuery mobiles(List<String> mobiles){
        if (mobiles == null) {
            throw new RuntimeException("mobiles is empty ");
        }
        this.mobiles = mobiles;
        return this;
    }


    public WxFollowerQuery mail(String mail){
	if (mail == null) {
	    throw new RuntimeException("mail is null");
        }         
	this.mail = mail;
	return this;
    }

    public WxFollowerQuery mailLike( String mailLike){
        if (mailLike == null) {
            throw new RuntimeException("mail is null");
        }
        this.mailLike = mailLike;
        return this;
    }

    public WxFollowerQuery mails(List<String> mails){
        if (mails == null) {
            throw new RuntimeException("mails is empty ");
        }
        this.mails = mails;
        return this;
    }


    public WxFollowerQuery telephone(String telephone){
	if (telephone == null) {
	    throw new RuntimeException("telephone is null");
        }         
	this.telephone = telephone;
	return this;
    }

    public WxFollowerQuery telephoneLike( String telephoneLike){
        if (telephoneLike == null) {
            throw new RuntimeException("telephone is null");
        }
        this.telephoneLike = telephoneLike;
        return this;
    }

    public WxFollowerQuery telephones(List<String> telephones){
        if (telephones == null) {
            throw new RuntimeException("telephones is empty ");
        }
        this.telephones = telephones;
        return this;
    }


    public WxFollowerQuery headimgurl(String headimgurl){
	if (headimgurl == null) {
	    throw new RuntimeException("headimgurl is null");
        }         
	this.headimgurl = headimgurl;
	return this;
    }

    public WxFollowerQuery headimgurlLike( String headimgurlLike){
        if (headimgurlLike == null) {
            throw new RuntimeException("headimgurl is null");
        }
        this.headimgurlLike = headimgurlLike;
        return this;
    }

    public WxFollowerQuery headimgurls(List<String> headimgurls){
        if (headimgurls == null) {
            throw new RuntimeException("headimgurls is empty ");
        }
        this.headimgurls = headimgurls;
        return this;
    }


    public WxFollowerQuery province(String province){
	if (province == null) {
	    throw new RuntimeException("province is null");
        }         
	this.province = province;
	return this;
    }

    public WxFollowerQuery provinceLike( String provinceLike){
        if (provinceLike == null) {
            throw new RuntimeException("province is null");
        }
        this.provinceLike = provinceLike;
        return this;
    }

    public WxFollowerQuery provinces(List<String> provinces){
        if (provinces == null) {
            throw new RuntimeException("provinces is empty ");
        }
        this.provinces = provinces;
        return this;
    }


    public WxFollowerQuery city(String city){
	if (city == null) {
	    throw new RuntimeException("city is null");
        }         
	this.city = city;
	return this;
    }

    public WxFollowerQuery cityLike( String cityLike){
        if (cityLike == null) {
            throw new RuntimeException("city is null");
        }
        this.cityLike = cityLike;
        return this;
    }

    public WxFollowerQuery citys(List<String> citys){
        if (citys == null) {
            throw new RuntimeException("citys is empty ");
        }
        this.citys = citys;
        return this;
    }


    public WxFollowerQuery country(String country){
	if (country == null) {
	    throw new RuntimeException("country is null");
        }         
	this.country = country;
	return this;
    }

    public WxFollowerQuery countryLike( String countryLike){
        if (countryLike == null) {
            throw new RuntimeException("country is null");
        }
        this.countryLike = countryLike;
        return this;
    }

    public WxFollowerQuery countrys(List<String> countrys){
        if (countrys == null) {
            throw new RuntimeException("countrys is empty ");
        }
        this.countrys = countrys;
        return this;
    }


    public WxFollowerQuery language(String language){
	if (language == null) {
	    throw new RuntimeException("language is null");
        }         
	this.language = language;
	return this;
    }

    public WxFollowerQuery languageLike( String languageLike){
        if (languageLike == null) {
            throw new RuntimeException("language is null");
        }
        this.languageLike = languageLike;
        return this;
    }

    public WxFollowerQuery languages(List<String> languages){
        if (languages == null) {
            throw new RuntimeException("languages is empty ");
        }
        this.languages = languages;
        return this;
    }


    public WxFollowerQuery locked(Integer locked){
	if (locked == null) {
            throw new RuntimeException("locked is null");
        }         
	this.locked = locked;
	return this;
    }

    public WxFollowerQuery lockedGreaterThanOrEqual(Integer lockedGreaterThanOrEqual){
	if (lockedGreaterThanOrEqual == null) {
	    throw new RuntimeException("locked is null");
        }         
	this.lockedGreaterThanOrEqual = lockedGreaterThanOrEqual;
        return this;
    }

    public WxFollowerQuery lockedLessThanOrEqual(Integer lockedLessThanOrEqual){
        if (lockedLessThanOrEqual == null) {
            throw new RuntimeException("locked is null");
        }
        this.lockedLessThanOrEqual = lockedLessThanOrEqual;
        return this;
    }

    public WxFollowerQuery lockeds(List<Integer> lockeds){
        if (lockeds == null) {
            throw new RuntimeException("lockeds is empty ");
        }
        this.lockeds = lockeds;
        return this;
    }


    public WxFollowerQuery remark(String remark){
	if (remark == null) {
	    throw new RuntimeException("remark is null");
        }         
	this.remark = remark;
	return this;
    }

    public WxFollowerQuery remarkLike( String remarkLike){
        if (remarkLike == null) {
            throw new RuntimeException("remark is null");
        }
        this.remarkLike = remarkLike;
        return this;
    }

    public WxFollowerQuery remarks(List<String> remarks){
        if (remarks == null) {
            throw new RuntimeException("remarks is empty ");
        }
        this.remarks = remarks;
        return this;
    }


    public WxFollowerQuery subscribeTime(Long subscribeTime){
	if (subscribeTime == null) {
            throw new RuntimeException("subscribeTime is null");
        }         
	this.subscribeTime = subscribeTime;
	return this;
    }

    public WxFollowerQuery subscribeTimeGreaterThanOrEqual(Long subscribeTimeGreaterThanOrEqual){
	if (subscribeTimeGreaterThanOrEqual == null) {
	    throw new RuntimeException("subscribeTime is null");
        }         
	this.subscribeTimeGreaterThanOrEqual = subscribeTimeGreaterThanOrEqual;
        return this;
    }

    public WxFollowerQuery subscribeTimeLessThanOrEqual(Long subscribeTimeLessThanOrEqual){
        if (subscribeTimeLessThanOrEqual == null) {
            throw new RuntimeException("subscribeTime is null");
        }
        this.subscribeTimeLessThanOrEqual = subscribeTimeLessThanOrEqual;
        return this;
    }

    public WxFollowerQuery subscribeTimes(List<Long> subscribeTimes){
        if (subscribeTimes == null) {
            throw new RuntimeException("subscribeTimes is empty ");
        }
        this.subscribeTimes = subscribeTimes;
        return this;
    }


    public WxFollowerQuery createDate(Date createDate){
	if (createDate == null) {
            throw new RuntimeException("createDate is null");
        }         
	this.createDate = createDate;
	return this;
    }

    public WxFollowerQuery createDateGreaterThanOrEqual(Date createDateGreaterThanOrEqual){
	if (createDateGreaterThanOrEqual == null) {
	    throw new RuntimeException("createDate is null");
        }         
	this.createDateGreaterThanOrEqual = createDateGreaterThanOrEqual;
        return this;
    }

    public WxFollowerQuery createDateLessThanOrEqual(Date createDateLessThanOrEqual){
        if (createDateLessThanOrEqual == null) {
            throw new RuntimeException("createDate is null");
        }
        this.createDateLessThanOrEqual = createDateLessThanOrEqual;
        return this;
    }

    public WxFollowerQuery createDates(List<Date> createDates){
        if (createDates == null) {
            throw new RuntimeException("createDates is empty ");
        }
        this.createDates = createDates;
        return this;
    }



    public String getOrderBy() {
        if (sortColumn != null) {
			String a_x = " asc ";
			if (sortOrder != null) {
				a_x = sortOrder;
			}

            if ("accountId".equals(sortColumn)) {
                orderBy = "E.ACCOUNTID_" + a_x;
            } 

            if ("actorId".equals(sortColumn)) {
                orderBy = "E.ACTORID_" + a_x;
            } 

            if ("openId".equals(sortColumn)) {
                orderBy = "E.OPENID_" + a_x;
            } 

            if ("nickName".equals(sortColumn)) {
                orderBy = "E.NICKNAME_" + a_x;
            } 

            if ("sex".equals(sortColumn)) {
                orderBy = "E.SEX_" + a_x;
            } 

            if ("mobile".equals(sortColumn)) {
                orderBy = "E.MOBILE_" + a_x;
            } 

            if ("mail".equals(sortColumn)) {
                orderBy = "E.MAIL_" + a_x;
            } 

            if ("telephone".equals(sortColumn)) {
                orderBy = "E.TELEPHONE_" + a_x;
            } 

            if ("headimgurl".equals(sortColumn)) {
                orderBy = "E.HEADIMGURL_" + a_x;
            } 

            if ("province".equals(sortColumn)) {
                orderBy = "E.PROVINCE_" + a_x;
            } 

            if ("city".equals(sortColumn)) {
                orderBy = "E.CITY_" + a_x;
            } 

            if ("country".equals(sortColumn)) {
                orderBy = "E.COUNTRY_" + a_x;
            } 

            if ("language".equals(sortColumn)) {
                orderBy = "E.LANGUAGE_" + a_x;
            } 

            if ("locked".equals(sortColumn)) {
                orderBy = "E.LOCKED_" + a_x;
            } 

            if ("remark".equals(sortColumn)) {
                orderBy = "E.REMARK" + a_x;
            } 

            if ("subscribeTime".equals(sortColumn)) {
                orderBy = "E.SUBSCRIBETIME_" + a_x;
            } 

            if ("createDate".equals(sortColumn)) {
                orderBy = "E.CREATEDATE_" + a_x;
            } 

        }
        return orderBy;
    }

    @Override
    public void initQueryColumns(){
        super.initQueryColumns();
        addColumn("id", "ID_");
        addColumn("accountId", "ACCOUNTID_");
        addColumn("actorId", "ACTORID_");
        addColumn("openId", "OPENID_");
        addColumn("nickName", "NICKNAME_");
        addColumn("sex", "SEX_");
        addColumn("mobile", "MOBILE_");
        addColumn("mail", "MAIL_");
        addColumn("telephone", "TELEPHONE_");
        addColumn("headimgurl", "HEADIMGURL_");
        addColumn("province", "PROVINCE_");
        addColumn("city", "CITY_");
        addColumn("country", "COUNTRY_");
        addColumn("language", "LANGUAGE_");
        addColumn("locked", "LOCKED_");
        addColumn("remark", "REMARK");
        addColumn("subscribeTime", "SUBSCRIBETIME_");
        addColumn("createDate", "CREATEDATE_");
    }

}