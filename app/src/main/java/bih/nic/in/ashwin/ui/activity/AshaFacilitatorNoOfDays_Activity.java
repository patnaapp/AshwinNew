package bih.nic.in.ashwin.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import bih.nic.in.ashwin.R;
import bih.nic.in.ashwin.adaptor.AshaActivityAccpRjctAdapter;
import bih.nic.in.ashwin.adaptor.CentreAditonDeductInterface;
import bih.nic.in.ashwin.adaptor.FacilitatorNoofDays_Adapter;
import bih.nic.in.ashwin.adaptor.NoOfDaysInterface;
import bih.nic.in.ashwin.adaptor.StateAddDeductInterface;
import bih.nic.in.ashwin.database.DataBaseHelper;
import bih.nic.in.ashwin.entity.Activity_entity;
import bih.nic.in.ashwin.entity.AshaFacilitator_Entity;
import bih.nic.in.ashwin.entity.AshaWoker_Entity;
import bih.nic.in.ashwin.entity.AshaWorkEntity;
import bih.nic.in.ashwin.entity.Centralamount_entity;
import bih.nic.in.ashwin.entity.Financial_Month;
import bih.nic.in.ashwin.entity.Financial_Year;
import bih.nic.in.ashwin.entity.NoOfDays_Entity;
import bih.nic.in.ashwin.entity.Stateamount_entity;
import bih.nic.in.ashwin.utility.CommonPref;
import bih.nic.in.ashwin.web_services.WebServiceHelper;

public class AshaFacilitatorNoOfDays_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, NoOfDaysInterface {

    String faciltator_id="",facilitator_nm="",asha_worker_id="",asha_worker_nm="",fyear_id="",month_id="",user_role="",svrid="";
    TextView tv_name,tv_year,tv_month,tv_role;
    Financial_Year fyear;
    Financial_Month fmonth;
    RecyclerView rv_data;
    Button btn_submit,btn_preview;
    ArrayList<NoOfDays_Entity> fcNoOfdays;
    String version="";
    Spinner sp_worker;
    ArrayList<AshaWoker_Entity> ashaworkerList = new ArrayList<AshaWoker_Entity>();
    DataBaseHelper dbhelper;
    ArrayList<AshaFacilitator_Entity> facilitatorList = new ArrayList<AshaFacilitator_Entity>();
    FacilitatorNoofDays_Adapter adapter;
    ArrayList<NoOfDays_Entity> newArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asha_facilitator_no_of_days_);

        dbhelper = new DataBaseHelper(this);
        initialise();

        user_role = getIntent().getStringExtra("role");
        //   svrid=getIntent().getStringExtra("svr");
        fyear = (Financial_Year) getIntent().getSerializableExtra("fyear");
        fmonth = (Financial_Month) getIntent().getSerializableExtra("fmonth");

        tv_role.setText("आशा फैसिलिटेटर");
        tv_year.setText(fyear.getFinancial_year());
        tv_month.setText(fmonth.get_MonthName());
        // loadWorkerFascilatorData();
        fcNoOfdays=new ArrayList<>();
        new SynchronizeFcNoOfDays().execute();

        btn_preview.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                newArrayList=new ArrayList<>();
                for(NoOfDays_Entity land : fcNoOfdays)
                {
                    if(land.get_no_ofDays()!=0 && land.get_total_Amount()>0)
                    {
                        newArrayList.add(land);
                        Log.d("fhbdhb" ,""+land.get_no_ofDays());
                        Log.d("qty" ,""+land.get_total_Amount());
                    }
                }
                if (newArrayList.size()>0)
                {
                    Intent i=new Intent(AshaFacilitatorNoOfDays_Activity.this,PreviewFacilitatorNoOfDays_Activity.class);
                    i.putExtra("newArray", newArrayList);
                    i.putExtra("fyear", fyear);
                    i.putExtra("fmonth", fmonth);

                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"No Data For Preview",Toast.LENGTH_LONG).show();
                }

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (isRemarksEmptyForSubmit(fcNoOfdays))
                {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(AshaFacilitatorNoOfDays_Activity.this);
                    builder1.setMessage("कृपया सभी विवरण सही से भरे");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton("ओके",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    if (newArrayList.size() > 0)
                                    {
                                        new UploadFacilitatorSalary(newArrayList).execute();
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), "No Data to Upload", Toast.LENGTH_SHORT).show();
                                    }

                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
                else {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(AshaFacilitatorNoOfDays_Activity.this);
                    builder1.setMessage("क्या आप वाकई डेटा सबमिट करना चाहते हैं");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "हाँ",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    newArrayList=new ArrayList<>();
                                    for(NoOfDays_Entity land : fcNoOfdays)
                                    {
                                        if(land.get_no_ofDays()!=0 && land.get_total_Amount()>0)
                                        {
                                            newArrayList.add(land);
                                            Log.d("fhbdhb" ,""+land.get_no_ofDays());
                                            Log.d("qty" ,""+land.get_total_Amount());
                                        }
                                    }

                                    dialog.cancel();
                                }
                            });
                    builder1.setNegativeButton(
                            "नहीं",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }
            }
        });

    }

    public void initialise()
    {
        tv_name=findViewById(R.id.tv_name);
        tv_year=findViewById(R.id.tv_year);
        tv_month=findViewById(R.id.tv_month);
        tv_role=findViewById(R.id.tv_role);
        rv_data = findViewById(R.id.recyclerview_data);
        btn_submit = findViewById(R.id.btn_submit);
        btn_preview = findViewById(R.id.btn_preview);
        sp_worker = findViewById(R.id.sp_worker);
        // btn_finalize.setVisibility(View.GONE);
        sp_worker.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void loadWorkerFascilatorData(){
        if (user_role.equals("ASHAFC")){
            facilitatorList = dbhelper.getAshaFacilitatorList(CommonPref.getUserDetails(AshaFacilitatorNoOfDays_Activity.this).getHSCCode());

            ArrayList array = new ArrayList<String>();
            array.add("-Select-");
            array.add("ALL");

            for (AshaFacilitator_Entity info: facilitatorList)
            {
                // if(!info.getFinancial_year().equals("anyType{}")){
                array.add(info.get_Facilitator_Name_Hn());
                // }
            }

            ArrayAdapter adaptor = new ArrayAdapter(AshaFacilitatorNoOfDays_Activity.this, android.R.layout.simple_spinner_item, array);
            adaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_worker.setAdapter(adaptor);
            sp_worker.setSelection(1);

        }
        sp_worker.setOnItemSelectedListener(this);
    }

    public void setupRecuyclerView(ArrayList<NoOfDays_Entity> data)
    {
        rv_data.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new FacilitatorNoofDays_Adapter(AshaFacilitatorNoOfDays_Activity.this, data, fyear, fmonth,this);
        rv_data.setAdapter(adapter);
    }

    @Override
    public void onAdditionInCentre(int position, int value)
    {
        NoOfDays_Entity activity = fcNoOfdays.get(position);
        activity.set_centre_addition_Amt(value);
        fcNoOfdays.set(position, calculateAmount(activity));
    }

    @Override
    public void onDeductionInCentre(int position, int value)
    {
        NoOfDays_Entity activity = fcNoOfdays.get(position);
        activity.set_centre_deducted_Amt(value);
        fcNoOfdays.set(position, calculateAmount(activity));
    }

    @Override
    public void onAdditionRemarks(int position, String value, Boolean forstate)
    {
        NoOfDays_Entity activity = fcNoOfdays.get(position);
        if (forstate)
        {
            activity.set_state_remarks_addition(value);

        }
        else
        {
            activity.set_centre_remarks_add(value);
        }

        fcNoOfdays.set(position, activity);
    }

    @Override
    public void onDeductionRemarks(int position, String value, Boolean forstate) {
        NoOfDays_Entity activity = fcNoOfdays.get(position);
        if (forstate)
        {
            activity.set_state_remarks_deduction(value);

        }
        else
        {
            activity.set_centre_remarks_deduction(value);
        }

        fcNoOfdays.set(position, activity);

    }

    @Override
    public void onNoOfDaysChanged(int position, int days)
    {
        NoOfDays_Entity activity = fcNoOfdays.get(position);
        activity.set_no_ofDays(days);
        fcNoOfdays.set(position, calculateAmount(activity));

    }

    @Override
    public void onAdditionInState(int position, int value)
    {
        NoOfDays_Entity activity = fcNoOfdays.get(position);
        activity.set_state_additiond_Amt(value);
        fcNoOfdays.set(position, calculateAmount(activity));
    }

    @Override
    public void onDeductionInStatere(int position, int value)
    {
        NoOfDays_Entity activity = fcNoOfdays.get(position);
        activity.set_state_deducted_Amt(value);
        fcNoOfdays.set(position, calculateAmount(activity));
    }


    public NoOfDays_Entity calculateAmount(NoOfDays_Entity noofdays)
    {
        int totalamt=noofdays.get_no_ofDays()*noofdays.get_Centre_Amount();
        totalamt+=noofdays.get_state_Amount();
        totalamt+=(noofdays.get_state_additiond_Amt()-noofdays.get_state_deducted_Amt());
        totalamt+=(noofdays.get_centre_addition_Amt()-noofdays.get_centre_deducted_Amt());

        noofdays.set_total_Amount(totalamt);
        return noofdays;
    }


    private class SynchronizeFcNoOfDays extends AsyncTask<String, Void, ArrayList<NoOfDays_Entity>> {

        private final ProgressDialog dialog = new ProgressDialog(AshaFacilitatorNoOfDays_Activity.this);

        private final AlertDialog alertDialog = new AlertDialog.Builder(AshaFacilitatorNoOfDays_Activity.this).create();

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading details...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<NoOfDays_Entity> doInBackground(String... param) {

            return WebServiceHelper.getAshaFcNoOfDays(fyear.getYear_Id(),fmonth.get_MonthId(),CommonPref.getUserDetails(AshaFacilitatorNoOfDays_Activity.this).getBlockCode(),CommonPref.getUserDetails(AshaFacilitatorNoOfDays_Activity.this).getDistrictCode(),CommonPref.getUserDetails(AshaFacilitatorNoOfDays_Activity.this).getHSCCode());
        }

        @Override
        protected void onPostExecute(ArrayList<NoOfDays_Entity> result) {
            if (this.dialog.isShowing())
            {
                this.dialog.dismiss();
            }

            if (result != null)
            {
                fcNoOfdays=result;

                for(NoOfDays_Entity stateamt:fcNoOfdays){
                    stateamt.set_state_Amount(getTotalStateAmount());
                    stateamt.set_Centre_Amount(getTotalCentreAmount());
                }
                setupRecuyclerView(fcNoOfdays);

            }
        }
    }

    public int getTotalStateAmount(){
        ArrayList<Stateamount_entity> list = dbhelper.getStateAmountList("ASHAFC");

        int amount = 0;

        for(Stateamount_entity info: list){
            amount += Integer.parseInt(info.get_StateAmt());
        }

        return amount;
    }

    public int getTotalCentreAmount(){
        ArrayList<Centralamount_entity> list = dbhelper.getCentreAmountList();

        int amount = 0;

        for(Centralamount_entity info: list){
            amount += Integer.parseInt(info.get_CentralAmt());
        }

        return amount;
    }

    public  boolean isRemarksEmptyForSubmit(ArrayList<NoOfDays_Entity> arraylist)
    {
        for (NoOfDays_Entity info : arraylist)
        {
            if (info.get_no_ofDays()>0 && info.get_no_ofDays()<=20)
            {
                if (info.get_centre_addition_Amt()>0 && (info.get_centre_remarks_add().isEmpty()||info.get_centre_remarks_add()=="")){
                    return true;
                }
                else if (info.get_centre_deducted_Amt()>0 && (info.get_centre_remarks_deduction().isEmpty()||info.get_centre_remarks_deduction()==""))
                {
                    return true;
                }

                else if (info.get_centre_deducted_Amt()>0 && (info.get_centre_remarks_deduction().isEmpty()||info.get_centre_remarks_deduction()==""))
                {
                    return true;
                }
                else if (info.get_state_additiond_Amt()>0 && (info.get_state_remarks_addition().isEmpty()||info.get_state_remarks_addition()==""))
                {
                    return true;
                }

                else if (info.get_state_deducted_Amt()>0 && (info.get_state_remarks_deduction().isEmpty()||info.get_state_remarks_deduction()==""))
                {
                    return true;
                }
                else
                {
                    return false;
                }

            }
            return true;
        }
        return false;
    }


    private class UploadFacilitatorSalary extends AsyncTask<String, Void, String>
    {

        ArrayList<NoOfDays_Entity> data;
        //SchoolFacultyDetailEntity data;
        String _id;
        private final ProgressDialog dialog = new ProgressDialog(AshaFacilitatorNoOfDays_Activity.this);
        private final AlertDialog alertDialog = new AlertDialog.Builder(AshaFacilitatorNoOfDays_Activity.this).create();


        UploadFacilitatorSalary(ArrayList<NoOfDays_Entity> data)
        {
            this.data = data;
            //  this._id = data.getTeacher_Number();
        }

        @Override
        protected void onPreExecute()
        {
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("UpLoading...");
            this.dialog.show();
        }

        @Override
        protected String doInBackground(String... param)
        {
            String devicename = getDeviceName();
            String app_version = getAppVersion();
            boolean isTablet = isTablet(AshaFacilitatorNoOfDays_Activity.this);
            if (isTablet)
            {
                devicename = "Tablet::" + devicename;
                Log.e("DEVICE_TYPE", "Tablet");
            }
            else
            {
                devicename = "Mobile::" + devicename;
                Log.e("DEVICE_TYPE", "Mobile");
            }

            String username =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("uid", "");
            String res = WebServiceHelper.UploadFacilitatorSalaryDetailByHSC(getApplicationContext(),data, app_version, "",username);
            return res;
        }

        @Override
        protected void onPostExecute(String result)
        {
            if (this.dialog.isShowing())
            {
                this.dialog.dismiss();
            }
            Log.d("Responsevalue", "" + result);
            if (result != null)
            {
                String string = result;

                if (result.contains("Success"))
                {
                   /* long c = databaseHelper.deletePrincipal(diseCode);
                    long c1;
                    if (c>0){
                        c1=databaseHelper.deleteTeachersl(diseCode);
                        if (c1>0){
                         updatePrincipalDetail(diseCode,"Y");*/

                    // alterVerifiedData("N");
                    //  deletebendata();
                    AlertDialog.Builder builder = new AlertDialog.Builder(AshaFacilitatorNoOfDays_Activity.this);
                    //   builder.setIcon(R.drawable.icdslogo);
                    builder.setTitle("Success!!");
                    builder.setMessage("Verified Records Uploded Successfully");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
//                            Intent i=new Intent(AshaFacilitatorNoOfDays_Activity.this,DashboardActivity.class);
//                            i.putExtra("ForActivity",ForActivity);
//                            startActivity(i);
                            dialog.dismiss();
                            finish();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    if (!AshaFacilitatorNoOfDays_Activity.this.isFinishing())
                    {
                        dialog.show();
                    }
                }

                else if (result.equals("0"))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AshaFacilitatorNoOfDays_Activity.this);
                    builder.setMessage(result);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    if (!AshaFacilitatorNoOfDays_Activity.this.isFinishing())
                    {
                        dialog.show();
                    }
                }

                else
                {
                    Toast.makeText(getApplicationContext(), "Uploading data failed ", Toast.LENGTH_SHORT).show();
                }

            }
            else
            {
                //chk_msg_OK_networkdata("Result:null..Uploading failed.Please Try Again Later");
                Toast.makeText(getApplicationContext(), "Result Null:Uploading failed..Please Try Later", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public static String getDeviceName()
    {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer))
        {
            return model.toUpperCase();
        }
        else
        {
            return manufacturer.toUpperCase() + " " + model;
        }
    }

    public String getAppVersion()
    {
        try
        {
            version = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
//                TextView tv = (TextView)getActivity().findViewById(R.id.txtVersion_1);
//                tv.setText(getActivity().getString(R.string.app_version) + version + " ");
        }
        catch (PackageManager.NameNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return version;
    }

    public static boolean isTablet(Context context)
    {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
