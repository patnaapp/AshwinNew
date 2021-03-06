package bih.nic.in.ashwin.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class AshaWorkEntity implements KvmSerializable, Serializable {

    public static Class<AshaWorkEntity> AshaWorkEntity_CLASS = AshaWorkEntity.class;

    private String AshaWorkerName,AshaActivityId,ActivityDesc,AcitivtyCategoryId,AcitivtyCategoryDesc,ActivityAmt,MonthName,FinYear,ActivityDate,RegisterId,Volume,RegisterPageNo,RegisterDesc,PageSerialNo,RegisterDate,VerificationStatus,AshaWorkerId,IsFinalize,_IsANMFinalize,_anm_id,_rejectedRemarks;
    String appVersion,iemi,districtCode, blockCode, PanchayatCode, awcId,entryType,ActivityId, noOfBenif, remark,workdmCode;
    private String FYearId,MonthId,ActTypeId,NoOfBeneficiary,ActivityRate,Remarks,AcitivtyType,entryBy,HSCCODE,ActivityPlace,OtherDist,OtherBlock,IsForwaded,Abbr,FieldName,Name_Hn;

    public AshaWorkEntity(SoapObject sobj) {
        this.AshaActivityId = sobj.getProperty("AshaActivityId").toString();
        this.ActivityDesc = sobj.getProperty("ActivityDesc").toString();
        this.AcitivtyCategoryId = sobj.getProperty("AcitivtyCategoryId").toString();
        this.AcitivtyCategoryDesc = sobj.getProperty("AcitivtyCategoryDesc").toString();
        this.ActivityAmt = sobj.getProperty("ActivityAmt").toString();
        this.MonthName = sobj.getProperty("MonthName").toString();
        this.FinYear = sobj.getProperty("FinYear").toString();
        this.ActivityDate = sobj.getProperty("ActivityDate").toString();
        this.RegisterId = sobj.getProperty("RegisterId").toString();
        this.Volume = sobj.getProperty("Volume").toString();
        this.RegisterPageNo = sobj.getProperty("RegisterPageNo").toString();
        this.RegisterDesc = sobj.getProperty("RegisterDesc").toString();
        this.PageSerialNo = sobj.getProperty("PageSerialNo").toString();
        this.RegisterDate = sobj.getProperty("RegisterDate").toString();
        this.VerificationStatus = sobj.getProperty("VerificationStatus").toString();
        this.AshaWorkerId = sobj.getProperty("AshaWorkerId").toString();
        this.IsFinalize = sobj.getProperty("IsFinalize").toString();
        this._IsANMFinalize = sobj.getProperty("ANMVerified").toString();
      //  this._anm_id = sobj.getProperty("Sal_a_Id").toString();
        this.ActTypeId = sobj.getProperty("ActTypeId").toString();
        this.NoOfBeneficiary = sobj.getProperty("NoOfBeneficiary").toString();
        this.ActivityRate = sobj.getProperty("ActivityRate").toString();
        this.Remarks = sobj.getProperty("Remarks").toString();
        this.AcitivtyType = sobj.getProperty("AcitivtyType").toString();
        this.ActivityId = sobj.getProperty("ActivityId").toString();
        this.ActivityPlace = sobj.getProperty("ActivityPlace").toString();
        this.OtherDist = sobj.getProperty("OtherDist").toString();
        this.OtherBlock = sobj.getProperty("OtherBlock").toString();
        this.FieldName = sobj.getProperty("FieldName").toString();

        try {
            this._rejectedRemarks = sobj.getProperty("RejectedReason").toString();
        }catch (Exception e){
            this._rejectedRemarks="NA";
        }
        //this.IsForwaded = sobj.getProperty("IsForwaded").toString();

    }
    public  AshaWorkEntity(SoapObject sobj ,String monthly) {
        if (monthly.equals("1"))
        {
            this.AshaActivityId = sobj.getProperty("AshaActivityId").toString();
            this.ActivityDesc = sobj.getProperty("ActivityDesc").toString();
            this.AcitivtyCategoryId = sobj.getProperty("AcitivtyCategoryId").toString();
            this.AcitivtyCategoryDesc = sobj.getProperty("AcitivtyCategoryDesc").toString();
            this.ActivityAmt = sobj.getProperty("ActivityAmt").toString();
            this.MonthName = sobj.getProperty("MonthName").toString();
            this.FinYear = sobj.getProperty("FinYear").toString();
            this.ActivityId = sobj.getProperty("ActivityId").toString();
            this.AcitivtyType = sobj.getProperty("AcitivtyType").toString();
            this.VerificationStatus =sobj.getProperty("VerificationStatus").toString();

            //  this.VerificationStatus ="P";
            this.IsFinalize = sobj.getProperty("IsFinalize").toString();
            this.Abbr = sobj.getProperty("Abbr").toString();
            this.NoOfBeneficiary = sobj.getProperty("NoOfBeneficiary").toString();
            this.ActivityRate = sobj.getProperty("ActivityRate").toString();
            try {
                this._rejectedRemarks = sobj.getProperty("RejectedReason").toString();
            }catch (Exception e){
                this._rejectedRemarks="NA";
            }
            //this.IsFinalize = "N";
            //    this._IsANMFinalize =sobj.getProperty("ANMVerified").toString();
            // this._IsANMFinalize ="N";
        }
        else if (monthly.equals("2"))
        {
            this.AshaActivityId = sobj.getProperty("AshaActivityId").toString();
            this.ActivityDesc = sobj.getProperty("ActivityDesc").toString();
            this.AcitivtyCategoryId = sobj.getProperty("AcitivtyCategoryId").toString();
            this.AcitivtyCategoryDesc = sobj.getProperty("AcitivtyCategoryDesc").toString();
            this.ActivityAmt = sobj.getProperty("ActivityAmt").toString();
            this.MonthName = sobj.getProperty("MonthName").toString();
            this.FinYear = sobj.getProperty("FinYear").toString();
            this.ActivityDate = sobj.getProperty("ActivityDate").toString();
            this.RegisterId = sobj.getProperty("RegisterId").toString();
            this.Volume = sobj.getProperty("Volume").toString();
            this.RegisterPageNo = sobj.getProperty("RegisterPageNo").toString();
            this.RegisterDesc = sobj.getProperty("RegisterDesc").toString();
            this.PageSerialNo = sobj.getProperty("PageSerialNo").toString();
            this.RegisterDate = sobj.getProperty("RegisterDate").toString();
            this.VerificationStatus = sobj.getProperty("VerificationStatus").toString();
            this.AshaWorkerId = sobj.getProperty("AshaWorkerId").toString();
            this.AshaWorkerName = sobj.getProperty("Name").toString();
            this.IsFinalize = sobj.getProperty("IsFinalize").toString();
            this._IsANMFinalize = sobj.getProperty("ANMVerified").toString();
            //  this._anm_id = sobj.getProperty("Sal_a_Id").toString();
            this.ActTypeId = sobj.getProperty("ActTypeId").toString();
            this.NoOfBeneficiary = sobj.getProperty("NoOfBeneficiary").toString();
            this.ActivityRate = sobj.getProperty("ActivityRate").toString();
            this.Remarks = sobj.getProperty("Remarks").toString();
            this.AcitivtyType = sobj.getProperty("AcitivtyType").toString();
            this.ActivityId = sobj.getProperty("ActivityId").toString();
            this.ActivityPlace = sobj.getProperty("ActivityPlace").toString();
            this.OtherDist = sobj.getProperty("OtherDist").toString();
            this.OtherBlock = sobj.getProperty("OtherBlock").toString();
            this.IsForwaded = sobj.getProperty("IsForwaded").toString();
            try {
                this._rejectedRemarks = sobj.getProperty("RejectedReason").toString();
            }catch (Exception e){
                this._rejectedRemarks="NA";
            }

            try{
                this.FieldName = sobj.getProperty("FieldName").toString();
            }catch (Exception e){
                this.FieldName = "लाभार्थी की संख्या";
            }

        }
        else if (monthly.equals("4")) {
            this.Name_Hn = sobj.getProperty("Name_Hn").toString();

            this.districtCode = sobj.getProperty("DistrictCode").toString();
            this.blockCode = sobj.getProperty("BlockCode").toString();
            this.AshaActivityId = sobj.getProperty("AshaActivityId").toString();
            this.AcitivtyCategoryDesc = sobj.getProperty("AcitivtyCategoryDesc").toString();
            this.ActivityDesc = sobj.getProperty("ActivityDesc").toString();
            this.ActivityAmt = sobj.getProperty("ActivityAmt").toString();
            this.MonthName = sobj.getProperty("MonthName").toString();
            this.FinYear = sobj.getProperty("FinYear").toString();
           // this.ActivityDate = sobj.getProperty("ActivityDate").toString();
            this.AshaWorkerId = sobj.getProperty("AshaWorkerId").toString();
            this.AcitivtyCategoryId = sobj.getProperty("AcitivtyCategoryId").toString();
            this.ActivityId = sobj.getProperty("ActivityId").toString();
            //this.ActivityId = sobj.getProperty("Sal_a_Id").toString();
            this.ActTypeId = sobj.getProperty("ActTypeId").toString();
            this.AcitivtyType = sobj.getProperty("AcitivtyType").toString();
            this.VerificationStatus = sobj.getProperty("VerificationStatus").toString();
            this.IsFinalize = sobj.getProperty("IsFinalize").toString();
            this.ActivityPlace = sobj.getProperty("ActivityPlace").toString();
            this.OtherDist = sobj.getProperty("OtherDist").toString();
            this.OtherBlock = sobj.getProperty("OtherBlock").toString();
            this.Abbr = sobj.getProperty("Abbr").toString();
            this.NoOfBeneficiary = sobj.getProperty("NoOfBeneficiary").toString();
            this.ActivityRate = sobj.getProperty("ActivityRate").toString();
            this.Remarks = sobj.getProperty("Remarks").toString();

//            this.MonthId = sobj.getProperty("MonthId").toString();
//            this.FYearId = sobj.getProperty("FYearId").toString();
//
//            this.RegisterId = sobj.getProperty("RegisterId").toString();
//            this.Volume = sobj.getProperty("Volume").toString();
//            this.RegisterPageNo = sobj.getProperty("RegisterPageNo").toString();
//            this.RegisterDesc = sobj.getProperty("RegisterDesc").toString();
//            //  this._anm_id = sobj.getProperty("Sal_a_Id").toString();
//            this.PageSerialNo = sobj.getProperty("PageSerialNo").toString();
//            this.RegisterDate = sobj.getProperty("RegisterDate").toString();

        }

    }

    public AshaWorkEntity()
    {

    }

    @Override
    public Object getProperty(int index)
    {
        return null;
    }

    @Override
    public int getPropertyCount()
    {
        return 0;
    }

    @Override
    public void setProperty(int index, Object value) {

    }

    @Override
    public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info) {

    }

    public String getAbbr() {
        return Abbr;
    }

    public void setAbbr(String abbr) {
        Abbr = abbr;
    }

    public String getActivityPlace() {
        return ActivityPlace;
    }

    public void setActivityPlace(String activityPlace) {
        ActivityPlace = activityPlace;
    }

    public String getOtherDist() {
        return OtherDist;
    }

    public void setOtherDist(String otherDist) {
        OtherDist = otherDist;
    }

    public String getOtherBlock() {
        return OtherBlock;
    }

    public void setOtherBlock(String otherBlock) {
        OtherBlock = otherBlock;
    }

    public String getHSCCODE() {
        return HSCCODE;
    }

    public void setHSCCODE(String HSCCODE) {
        this.HSCCODE = HSCCODE;
    }

    public String getWorkdmCode() {
        return workdmCode;
    }

    public void setWorkdmCode(String workdmCode) {
        this.workdmCode = workdmCode;
    }

    public String getEntryBy() {
        return entryBy;
    }

    public void setEntryBy(String entryBy) {
        this.entryBy = entryBy;
    }

    public String getNoOfBenif() {
        return noOfBenif;
    }

    public void setNoOfBenif(String noOfBenif) {
        this.noOfBenif = noOfBenif;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsFinalize() {
        return IsFinalize;
    }

    public void setIsFinalize(String isFinalize) {
        IsFinalize = isFinalize;
    }

    public String getActivityId() {
        return ActivityId;
    }

    public void setActivityId(String activityId) {
        ActivityId = activityId;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getBlockCode() {
        return blockCode;
    }

    public void setBlockCode(String blockCode) {
        this.blockCode = blockCode;
    }

    public String getPanchayatCode() {
        return PanchayatCode;
    }

    public void setPanchayatCode(String panchayatCode) {
        PanchayatCode = panchayatCode;
    }

    public String getAwcId() {
        return awcId;
    }

    public void setAwcId(String awcId) {
        this.awcId = awcId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getIemi() {
        return iemi;
    }

    public void setIemi(String iemi) {
        this.iemi = iemi;
    }

    public String getAshaActivityId() {
        return AshaActivityId;
    }

    public void setAshaActivityId(String ashaActivityId) {
        AshaActivityId = ashaActivityId;
    }

    public String getActivityDesc() {
        return ActivityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        ActivityDesc = activityDesc;
    }

    public String getAcitivtyCategoryId() {
        return AcitivtyCategoryId;
    }

    public void setAcitivtyCategoryId(String acitivtyCategoryId) {
        AcitivtyCategoryId = acitivtyCategoryId;
    }

    public String getAcitivtyCategoryDesc() {
        return AcitivtyCategoryDesc;
    }

    public void setAcitivtyCategoryDesc(String acitivtyCategoryDesc) {
        AcitivtyCategoryDesc = acitivtyCategoryDesc;
    }

    public String getActivityAmt() {
        return ActivityAmt;
    }

    public void setActivityAmt(String activityAmt) {
        ActivityAmt = activityAmt;
    }

    public String getMonthName() {
        return MonthName;
    }

    public void setMonthName(String monthName) {
        MonthName = monthName;
    }

    public String getFinYear() {
        return FinYear;
    }

    public void setFinYear(String finYear) {
        FinYear = finYear;
    }

    public String getActivityDate() {
        return ActivityDate;
    }

    public void setActivityDate(String activityDate) {
        ActivityDate = activityDate;
    }

    public String getRegisterId() {
        return RegisterId;
    }

    public void setRegisterId(String registerId) {
        RegisterId = registerId;
    }

    public String getVolume() {
        return Volume;
    }

    public void setVolume(String volume) {
        Volume = volume;
    }

    public String getRegisterPageNo() {
        return RegisterPageNo;
    }

    public void setRegisterPageNo(String registerPageNo) {
        RegisterPageNo = registerPageNo;
    }

    public String getRegisterDesc() {
        return RegisterDesc;
    }

    public void setRegisterDesc(String registerDesc) {
        RegisterDesc = registerDesc;
    }

    public String getPageSerialNo() {
        return PageSerialNo;
    }

    public void setPageSerialNo(String pageSerialNo) {
        PageSerialNo = pageSerialNo;
    }

    public String getRegisterDate() {
        return RegisterDate;
    }

    public void setRegisterDate(String registerDate) {
        RegisterDate = registerDate;
    }

    public String getVerificationStatus() {
        return VerificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        VerificationStatus = verificationStatus;
    }

    public String getAshaWorkerId() {
        return AshaWorkerId;
    }

    public void setAshaWorkerId(String ashaWorkerId) {
        AshaWorkerId = ashaWorkerId;
    }

    public String get_IsANMFinalize() {
        return _IsANMFinalize;
    }

    public void set_IsANMFinalize(String _IsANMFinalize) {
        this._IsANMFinalize = _IsANMFinalize;
    }

    public String get_anm_id() {
        return _anm_id;
    }

    public void set_anm_id(String _anm_id) {
        this._anm_id = _anm_id;
    }

    public static Class<AshaWorkEntity> getAshaWorkEntity_CLASS() {
        return AshaWorkEntity_CLASS;
    }

    public static void setAshaWorkEntity_CLASS(Class<AshaWorkEntity> ashaWorkEntity_CLASS) {
        AshaWorkEntity_CLASS = ashaWorkEntity_CLASS;
    }

    public String getActTypeId() {
        return ActTypeId;
    }

    public void setActTypeId(String actTypeId) {
        ActTypeId = actTypeId;
    }

    public String getNoOfBeneficiary() {
        return NoOfBeneficiary;
    }

    public void setNoOfBeneficiary(String noOfBeneficiary) {
        NoOfBeneficiary = noOfBeneficiary;
    }

    public String getActivityRate() {
        return ActivityRate;
    }

    public void setActivityRate(String activityRate) {
        ActivityRate = activityRate;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getAcitivtyType() {
        return AcitivtyType;
    }

    public void setAcitivtyType(String acitivtyType) {
        AcitivtyType = acitivtyType;
    }

    public String get_rejectedRemarks() {
        return _rejectedRemarks;
    }

    public void set_rejectedRemarks(String _rejectedRemarks) {
        this._rejectedRemarks = _rejectedRemarks;
    }

    public String getIsForwaded() {
        return IsForwaded;
    }

    public void setIsForwaded(String isForwaded) {
        IsForwaded = isForwaded;
    }

    public String getFieldName() {
        return FieldName;
    }

    public void setFieldName(String fieldName) {
        FieldName = fieldName;
    }

    public String getFYearId() {
        return FYearId;
    }

    public void setFYearId(String FYearId) {
        this.FYearId = FYearId;
    }

    public String getMonthId() {
        return MonthId;
    }

    public void setMonthId(String monthId) {
        MonthId = monthId;
    }

    public String getName_Hn() {
        return Name_Hn;
    }

    public void setName_Hn(String name_Hn) {
        Name_Hn = name_Hn;
    }

    public String getAshaWorkerName() {
        return AshaWorkerName;
    }

    public void setAshaWorkerName(String ashaWorkerName) {
        AshaWorkerName = ashaWorkerName;
    }
}
