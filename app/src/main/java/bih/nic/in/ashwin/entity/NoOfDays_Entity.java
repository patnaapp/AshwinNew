package bih.nic.in.ashwin.entity;

import android.content.Intent;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class NoOfDays_Entity implements KvmSerializable, Serializable {

    public static Class<Activity_entity> NoOfDays_CLASS = Activity_entity.class;
    private String _Fc_aId = "";
    private String DesigId = "";
    private String facilitator_id = "";
    private String AshaFacilitatorSalry_aId = "";
    private String FYearID = "";
    private String MonthId = "";
    private String _Fc_Name = "";
    private String _Father_NAme = "";
    private String _HSC_Name = "";
    private int _Centre_Amount = 0;
    private int _state_Amount = 0;
    private int _total_Amount = 0;
    private int _centre_addition_Amt =0;
    private int _centre_deducted_Amt = 0;
    private int _state_deducted_Amt = 0;
    private int _state_additiond_Amt = 0;
    private int _no_ofDays = 0;
    private String _state_remarks_addition = "";
    private String _state_remarks_deduction = "";
    private String _centre_remarks_deduction = "";
    private String _centre_remarks_add = "";

    public NoOfDays_Entity() {

    }
    public NoOfDays_Entity(SoapObject sobj) {
        this._Fc_aId = sobj.getProperty("AshaFacilitator_aId").toString();

        if (sobj.getProperty("Name").toString().equals("NA")){
            this._Fc_Name="";
        }
        else {
            this._Fc_Name = sobj.getProperty("Name").toString();
        }

        if (sobj.getProperty("FHName").toString().equals("NA")){
            this._Father_NAme="";
        }
        else {
            this._Father_NAme = sobj.getProperty("FHName").toString();
        }
        if (sobj.getProperty("DesigId").toString().equals("NA")){
            this.DesigId="";
        }
        else {
            this.DesigId = sobj.getProperty("DesigId").toString();
        }


        this.facilitator_id = sobj.getProperty("AshaFacilitatorId").toString();
        this.AshaFacilitatorSalry_aId = sobj.getProperty("AshaFacilitatorSalry_aId").toString();
        this.FYearID = sobj.getProperty("FYearID").toString();
        this.MonthId = sobj.getProperty("MonthId").toString();
        this._HSC_Name = sobj.getProperty("HSCName").toString();
        if (sobj.getProperty("TotalAmt_Central").toString().equals("NA")){
            this._Centre_Amount=0;
        }
        else {
            this._Centre_Amount = Integer.parseInt(sobj.getProperty("TotalAmt_Central").toString());
        }
        if (sobj.getProperty("TotalAmt_State").toString().equals("NA")){
            this._state_Amount=0;
        }
        else {
            this._state_Amount = Integer.parseInt(sobj.getProperty("TotalAmt_State").toString());
        }

        if (sobj.getProperty("FinalAmt").toString().equals("NA")){
            this._total_Amount=0;
        }
        else {
            this._total_Amount = Integer.parseInt(sobj.getProperty("FinalAmt").toString());
        }
        if (sobj.getProperty("AddAmt_Central").toString().equals("NA")){
            this._centre_addition_Amt=0;
        }
        else {
            this._centre_addition_Amt = Integer.parseInt(sobj.getProperty("AddAmt_Central").toString());
        }
        if (sobj.getProperty("DeductAmt_Central").toString().equals("NA")){
            this._centre_deducted_Amt=0;
        }
        else {
            this._centre_deducted_Amt = Integer.parseInt(sobj.getProperty("DeductAmt_Central").toString());
        }
        if (sobj.getProperty("DeductAmt_State").toString().equals("NA")){
            this._state_deducted_Amt=0;
        }
        else {
            this._state_deducted_Amt = Integer.parseInt(sobj.getProperty("DeductAmt_State").toString());
        }

        if (sobj.getProperty("AddAmt_State").toString().equals("NA")){
            this._state_additiond_Amt=0;
        }
        else {
            this._state_additiond_Amt = Integer.parseInt(sobj.getProperty("AddAmt_State").toString());
        }

//        if (sobj.getProperty("AddAmt_State").toString().equals("NA")){
//            this._state_additiond_Amt=0;
//        }
//        else {
//            this._state_additiond_Amt = Integer.parseInt(sobj.getProperty("AddAmt_State").toString());
//        }


       // this._no_ofDays = Integer.parseInt(sobj.getProperty("AcitivtyType").toString());
        if (sobj.getProperty("AddRemarks_State").toString().equals("NA")){
            this._state_remarks_addition="";
        }
        else {
            this._state_remarks_addition = sobj.getProperty("AddRemarks_State").toString();
        }

      //  this._state_remarks_deduction = sobj.getProperty("AcitivtyType").toString();
        if (sobj.getProperty("DeductRemarks_Central").toString().equals("NA")){
            this._centre_remarks_deduction="";
        }
        else {
            this._centre_remarks_deduction = sobj.getProperty("DeductRemarks_Central").toString();

        }
        if (sobj.getProperty("AddRemarks_Central").toString().equals("NA")){
            this._centre_remarks_add="";
        }
        else {
            this._centre_remarks_add = sobj.getProperty("AddRemarks_Central").toString();
        }


    }

    public String get_Fc_aId() {
        return _Fc_aId;
    }

    public void set_Fc_aId(String _Fc_aId) {
        this._Fc_aId = _Fc_aId;
    }

    public String getDesigId() {
        return DesigId;
    }

    public void setDesigId(String desigId) {
        DesigId = desigId;
    }

    public String getFacilitator_id() {
        return facilitator_id;
    }

    public void setFacilitator_id(String facilitator_id) {
        this.facilitator_id = facilitator_id;
    }

    public String getAshaFacilitatorSalry_aId() {
        return AshaFacilitatorSalry_aId;
    }

    public void setAshaFacilitatorSalry_aId(String ashaFacilitatorSalry_aId) {
        AshaFacilitatorSalry_aId = ashaFacilitatorSalry_aId;
    }

    public String getFYearID() {
        return FYearID;
    }

    public void setFYearID(String FYearID) {
        this.FYearID = FYearID;
    }

    public String getMonthId() {
        return MonthId;
    }

    public void setMonthId(String monthId) {
        MonthId = monthId;
    }

    public String get_Fc_Name() {
        return _Fc_Name;
    }

    public void set_Fc_Name(String _Fc_Name) {
        this._Fc_Name = _Fc_Name;
    }

    public String get_Father_NAme() {
        return _Father_NAme;
    }

    public void set_Father_NAme(String _Father_NAme) {
        this._Father_NAme = _Father_NAme;
    }

    public int get_Centre_Amount() {
        return _Centre_Amount;
    }

    public void set_Centre_Amount(int _Centre_Amount) {
        this._Centre_Amount = _Centre_Amount;
    }

    public int get_state_Amount() {
        return _state_Amount;
    }

    public void set_state_Amount(int _state_Amount) {
        this._state_Amount = _state_Amount;
    }

    public int get_total_Amount() {
        return _total_Amount;
    }

    public void set_total_Amount(int _total_Amount) {
        this._total_Amount = _total_Amount;
    }

    public int get_centre_addition_Amt() {
        return _centre_addition_Amt;
    }

    public void set_centre_addition_Amt(int _centre_addition_Amt) {
        this._centre_addition_Amt = _centre_addition_Amt;
    }

    public int get_centre_deducted_Amt() {
        return _centre_deducted_Amt;
    }

    public void set_centre_deducted_Amt(int _centre_deducted_Amt) {
        this._centre_deducted_Amt = _centre_deducted_Amt;
    }

    public int get_state_deducted_Amt() {
        return _state_deducted_Amt;
    }

    public void set_state_deducted_Amt(int _state_deducted_Amt) {
        this._state_deducted_Amt = _state_deducted_Amt;
    }

    public int get_state_additiond_Amt() {
        return _state_additiond_Amt;
    }

    public void set_state_additiond_Amt(int _state_additiond_Amt) {
        this._state_additiond_Amt = _state_additiond_Amt;
    }

    public String get_state_remarks_addition() {
        return _state_remarks_addition;
    }

    public void set_state_remarks_addition(String _state_remarks_addition) {
        this._state_remarks_addition = _state_remarks_addition;
    }

    public String get_state_remarks_deduction() {
        return _state_remarks_deduction;
    }

    public void set_state_remarks_deduction(String _state_remarks_deduction) {
        this._state_remarks_deduction = _state_remarks_deduction;
    }

    public String get_centre_remarks_deduction() {
        return _centre_remarks_deduction;
    }

    public void set_centre_remarks_deduction(String _centre_remarks_deduction) {
        this._centre_remarks_deduction = _centre_remarks_deduction;
    }

    public String get_centre_remarks_add() {
        return _centre_remarks_add;
    }

    public void set_centre_remarks_add(String _centre_remarks_add) {
        this._centre_remarks_add = _centre_remarks_add;
    }

    public int get_no_ofDays() {
        return _no_ofDays;
    }

    public void set_no_ofDays(int _no_ofDays) {
        this._no_ofDays = _no_ofDays;
    }

    @Override
    public Object getProperty(int i) {
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 0;
    }

    @Override
    public void setProperty(int i, Object o) {

    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {

    }

    public String get_HSC_Name() {
        return _HSC_Name;
    }

    public void set_HSC_Name(String _HSC_Name) {
        this._HSC_Name = _HSC_Name;
    }
}
