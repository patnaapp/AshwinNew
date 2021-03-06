package bih.nic.in.ashwin.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class AshaWoker_Entity implements KvmSerializable, Serializable {

    public static Class<AshaWoker_Entity> ASHA_WORKER_CLASS = AshaWoker_Entity.class;

    private String _ASHAID, _Asha_Name,_Asha_Name_Hn,_svr_id,AshaFcId;

    public AshaWoker_Entity(SoapObject sobj) {
        this._ASHAID = sobj.getProperty("ASHAID").toString();
        this._Asha_Name = sobj.getProperty("Name").toString();
        this._Asha_Name_Hn = sobj.getProperty("Name_Hn").toString();
        this._svr_id = sobj.getProperty("SvrID").toString();

    }

    public AshaWoker_Entity(SoapObject sobj,String str) {
        this._ASHAID = sobj.getProperty("ASHAID").toString();
        this._Asha_Name = sobj.getProperty("Name").toString();
        this._Asha_Name_Hn = sobj.getProperty("Name_Hn").toString();
        this._svr_id = sobj.getProperty("SvrID").toString();

    }

    public AshaWoker_Entity(SoapObject sobj,String str,String str1) {
        this._ASHAID = sobj.getProperty("AshaWorkerID").toString();
        this._Asha_Name = sobj.getProperty("Name").toString();
        this.AshaFcId = sobj.getProperty("AshaFacilitatorID").toString();


    }


    public AshaWoker_Entity() {

    }

    @Override
    public Object getProperty(int index) {
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 0;
    }

    @Override
    public void setProperty(int index, Object value) {

    }

    @Override
    public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info) {

    }

    public String get_ASHAID() {
        return _ASHAID;
    }

    public void set_ASHAID(String _ASHAID) {
        this._ASHAID = _ASHAID;
    }

    public String get_Asha_Name() {
        return _Asha_Name;
    }

    public void set_Asha_Name(String _Asha_Name) {
        this._Asha_Name = _Asha_Name;
    }

    public String get_Asha_Name_Hn() {
        return _Asha_Name_Hn;
    }

    public void set_Asha_Name_Hn(String _Asha_Name_Hn) {
        this._Asha_Name_Hn = _Asha_Name_Hn;
    }

    public String get_svr_id() {
        return _svr_id;
    }

    public void set_svr_id(String _svr_id) {
        this._svr_id = _svr_id;
    }

    public String getAshaFcId() {
        return AshaFcId;
    }

    public void setAshaFcId(String ashaFcId) {
        AshaFcId = ashaFcId;
    }
}
